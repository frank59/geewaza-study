#!/bin/bash
PROJECT_HOME='/opt/webapp/geewaza-web-test/WEB-INF'
VMPARAMS='-Xmx1024m -server -XX:PermSize=100m'
CLASSPATH='.:classes:bin:lib/*:target/test-class:target/classes'
MAIN_CLASS='com.geewaza.study.test.web.dubbo.main.HelloProviderMain'


cd $PROJECT_HOME
nohup $JAVA_HOME/bin/java -cp $CLASSPATH $VMPARAMS $MAIN_CLASS >> /opt/logs/HelloProviderMain_nohup.log &