#!/bin/bash
if [ "$1" == "all" ]
then
mvn clean install -DskipTests
rm -rf tmp
mkdir tmp
unzip server/server/meta/target/registry-server-meta-executable.jar -d tmp/
#We have modified MethodHandle usages in jraft
cp newjars/jraft-core-1.2.3.jar tmp/BOOT-INF/lib/jraft-core-1.2.4.jar
cp newjars/spring-boot-graal-feature-0.5.0.BUILD-SNAPSHOT.jar tmp/BOOT-INF/lib/
#cp newjars/spring-context-5.2.0.M2.jar tmp/BOOT-INF/lib/spring-context-5.2.0.M2.jar
#cp newjars/spring-core-5.2.0.M2.jar tmp/BOOT-INF/lib/spring-core-5.2.0.M2.jar
fi

CONFIG_PATH=configs

# GRAALVM_HOME must be set in environment
if [ $GRAALVM_HOME ]; then
    echo "GRAALVM_HOME = $GRAALVM_HOME"
else
    echo "error : GRAALVM_HOME is not set in environment"
    exit 1
fi
svm=${GRAALVM_HOME}/bin/native-image
echo "native image path is [$svm]"
#svm=~/tools/graalvm-ee-19.0.0/jre/lib/svm/bin/native-image
#svm=~/tools/graalvm-ce-19.0.0/bin/native-image
#$graalvm_home/bin/java  -agentlib:native-image-agent=config-output-dir=$CONFIG_PATH -jar server/server/meta/target/registry-server-meta-executable.jar



WORKDIR=`pwd`

cp -R tmp/META-INF tmp/BOOT-INF/classes
export LIBPATH=`find tmp/BOOT-INF/lib | tr '\n' ':'`
export CP=tmp/BOOT-INF/classes:$LIBPATH:$CONFIG_PATH:dynClass
$GRAALVM_HOME/bin/java -agentlib:native-image-agent=config-output-dir=tmp_config -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
#mkdir dynClass
#$GRAALVM_HOME/bin/java -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
#echo $CP
#Disable unsafe usage in netty. This option is provided by netty, not an univeral solution. A more general way
#is to use Graal's substition mechenism (see "Unsafe memory access" in 
#https://medium.com/graalvm/instant-netty-startup-using-graalvm-native-image-generation-ed6f14ff7692)
#Compile to a SO file
#CONFIG_OPT="${CONFIG_OPT} --shared -H:Name=rocketMQClient"
#Specify where is the C library file which defines the data structure used in exposed API. 
#CONFIG_OPT="${CONFIG_OPT} -H:CLibraryPath=native"
#Set your own $native_image enviroment variable which should refer to the bin\native-image file in your graalvm JDK. 
#$svm $CONFIG_OPT $SVM_OPT -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
