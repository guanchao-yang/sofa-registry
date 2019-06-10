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
cp newjars/spring-web-5.1.7.RELEASE.jar tmp/BOOT-INF/lib/
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

GC_OPT="-R:+VerboseGC -R:+PrintGC -R:+PrintGCTimeStamps -R:+PrintGCTimes"

CONFIG_OPT="--no-server --allow-incomplete-classpath --report-unsupported-elements-at-runtime --no-fallback -H:+PrintImageHeapPartitionSizes"
#CONFIG_OPT="${CONFIG_OPT} --enable-url-protocols=http"
#CONFIG_OPT="${CONFIG_OPT} -H:+PrintClassInitialization"
CONFIG_OPT="${CONFIG_OPT} -H:+ReportExceptionStackTraces"
CONFIG_OPT="${CONFIG_OPT} -Dorg.springframework.boot.logging.LoggingSystem=none"


SVM_OPT="${SVM_OPT} --initialize-at-build-time=io.netty.channel.unix.LimitsStaticallyReferencedJniMethods"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=io.netty.channel.unix.ErrorsStaticallyReferencedJniMethods"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.ExtensionRegistry"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.ExtensionLite"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.Extension"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.server.HttpOutput"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.Loader"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.log.AbstractLogger"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.log.Slf4jLog"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.log.JettyAwareLogger"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.log.Log"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.Uptime"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.eclipse.jetty.util.Uptime\$DefaultImpl"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.process.internal.AbstractExecutorProvidersConfigurator"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.server.ModelProcessorConfigurator"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.model.internal.ComponentBag"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.alipay.sofa.jraft.util.internal.UnsafeUtil"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.google.protobuf.UnsafeUtil"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.lmax.disruptor.RingBufferFields"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.alipay.hessian.internal.InternalNameBlackListFilter"

WORKDIR=`pwd`

cp -R tmp/META-INF tmp/BOOT-INF/classes
export LIBPATH=`find tmp/BOOT-INF/lib | tr '\n' ':'`
export CP=tmp/BOOT-INF/classes:$LIBPATH:$CONFIG_PATH:dynClass
#$GRAALVM_HOME/bin/java -agentlib:native-image-agent=config-output-dir=tmp_config -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
#mkdir dynClass
#$GRAALVM_HOME/bin/java -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
#echo $CP
#Disable unsafe usage in netty. This option is provided by netty, not an univeral solution. A more general way
#is to use Graal's substition mechenism (see "Unsafe memory access" in 
#https://medium.com/graalvm/instant-netty-startup-using-graalvm-native-image-generation-ed6f14ff7692)
CONFIG_OPT="${CONFIG_OPT} -Dio.netty.noUnsafe=true"
#Compile to a SO file
#CONFIG_OPT="${CONFIG_OPT} --shared -H:Name=rocketMQClient"
#Specify where is the C library file which defines the data structure used in exposed API. 
#CONFIG_OPT="${CONFIG_OPT} -H:CLibraryPath=native"
#Set your own $native_image enviroment variable which should refer to the bin\native-image file in your graalvm JDK. 
$svm $CONFIG_OPT $SVM_OPT $GC_OPT -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
