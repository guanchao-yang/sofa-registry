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

fi

CONFIG_PATH=./configs

graalvm_home=~/tools/graalvm-ce-19.0.0
svm=$graalvm_home/bin/native-image

#$graalvm_home/bin/java  -agentlib:native-image-agent=config-output-dir=$CONFIG_PATH -jar server/server/meta/target/registry-server-meta-executable.jar


CONFIG_OPT="--no-server --allow-incomplete-classpath --report-unsupported-elements-at-runtime"
CONFIG_OPT="${CONFIG_OPT} --enable-url-protocols=http"
#CONFIG_OPT="${CONFIG_OPT} -H:+PrintClassInitialization"
CONFIG_OPT="${CONFIG_OPT} -H:+ReportExceptionStackTraces"
CONFIG_OPT="${CONFIG_OPT} -Dorg.springframework.boot.logging.LoggingSystem=none"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.boot.logging.LoggingSystem"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.util.Assert"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.util.ClassUtils"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.OutputStreamAppender"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.LayoutBase"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.pattern.FormattingConverter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.pattern.DynamicConverter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.Logger"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.ConsoleAppender"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.pattern.ClassicConverter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.encoder.EncoderBase"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.encoder.LayoutWrappingEncoder"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.Level"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.LoggerContext"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.ContextBase"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.slf4j.helpers.SubstituteLoggerFactory"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.slf4j.impl.StaticLoggerBinder"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.slf4j.helpers.NOPLoggerFactory"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.slf4j.helpers.Util"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.slf4j.LoggerFactory"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.spi.LoggerContextVO"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.spi.TurboFilterList"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.util.ContextSelectorStaticBinder"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.BasicStatusManager"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.joran.spi.ConsoleTarget\$1"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.selector.DefaultContextSelector"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.helpers.CyclicBuffer"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.joran.spi.ConsoleTarget\$2"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.spi.AppenderAttachableImpl"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.spi.LogbackLock"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.status.InfoStatus"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.BasicConfigurator"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.COWArrayList"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.layout.TTLLLayout"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.spi.FilterAttachableImpl"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.pattern.ThrowableProxyConverter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.CachingDateFormatter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.lmax.disruptor.RingBufferPad"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.OptionHelper"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.status.StatusUtil"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=sun.reflect.misc.Trampoline"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.util.ContextInitializer"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.util.EnvUtil"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.EnvUtil"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.classic.util.LoggerNameUtil"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.StatusPrinter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.Loader"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.lmax.disruptor.util.Util"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=ch.qos.logback.core.util.StatusListenerConfigHelper"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.util.unit.DataSize"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.core.annotation.AnnotationFilter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.util.StringUtils"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.core.annotation.PackagesAnnotationFilter"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory\$SpringBootConfiguration"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=org.springframework.boot.logging.LoggingSystem\$NoOpLoggingSystem"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=io.netty.channel.unix.LimitsStaticallyReferencedJniMethods"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=io.netty.channel.unix.ErrorsStaticallyReferencedJniMethods"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.ExtensionRegistry"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.ExtensionLite"
SVM_OPT="${SVM_OPT} --initialize-at-build-time=com.google.protobuf.Extension"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.process.internal.AbstractExecutorProvidersConfigurator"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.server.ModelProcessorConfigurator"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=org.glassfish.jersey.model.internal.ComponentBag"
#SVM_OPT="${SVM_OPT} --initialize-at-build-time"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.handler.ssl.JdkAlpnApplicationProtocolNegotiator"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.handler.ssl.JdkNpnSslEngine"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.handler.ssl.JdkAlpnSslEngine"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=io.netty.util.internal.JavassistTypeParameterMatcherGenerator"
#SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.alibaba.fastjson.serializer.JodaCodec"

#SVM_OPT="${SVM_OPT} --rerun-class-initialization-at-runtime=io.netty.handler.ssl.util.SelfSignedCertificate"
#SVM_OPT="${SVM_OPT} --rerun-class-initialization-at-runtime=io.netty.handler.ssl.util.ThreadLocalInsecureRandom"
#SVM_OPT="${SVM_OPT} --rerun-class-initialization-at-runtime=com.alibaba.fastjson.serializer.SerializeConfig"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.alipay.sofa.jraft.util.internal.UnsafeUtil"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.google.protobuf.UnsafeUtil"
SVM_OPT="${SVM_OPT} --initialize-at-run-time=com.lmax.disruptor.RingBufferFields"
#SVM_OPT="${SVM_OPT} --rerun-class-initialization-at-runtime=com.lmax.disruptor.RingBufferFields"

WORKDIR=`pwd`

export LIBPATH=`find tmp/BOOT-INF/lib | tr '\n' ':'`
export CP=./tmp:./tmp/BOOT-INF/classes:$LIBPATH:$CONFIG_PATH

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
$svm $CONFIG_OPT $SVM_OPT -cp $CP com.alipay.sofa.registry.server.meta.MetaApplication
