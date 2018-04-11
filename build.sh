#!/usr/bin/env bash
export PAYARA_MICRO=~/.m2/repository/fish/payara/extras/payara-micro/5.181/payara-micro-5.181.jar

# Build WAR
mvn clean package -Dmaven.test.skip=true

# Build Uber JAR (Disabling Hazelcast cluster)
java -jar $PAYARA_MICRO --deploy target/ROOT.war --outputuberjar target/ROOT.jar --nocluster

# Run Uber JAR
java -jar target/ROOT.jar