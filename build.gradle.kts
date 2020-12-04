plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("io.micronaut.application") version "1.2.0"
    id("com.google.cloud.tools.jib") version "2.6.0"
}

version = "0.1"
group = "com.example"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
    jcenter()
    maven("https://oss.jfrog.org/oss-snapshot-local")
}

micronaut {
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}

dependencies {
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.spring:micronaut-spring-annotation")
    kapt("io.micronaut.security:micronaut-security-annotations")
    kapt("io.dekorate:kubernetes-annotations:${dekorateVersion}")
    kapt("io.dekorate:prometheus-annotations:${dekorateVersion}")
    kapt("io.dekorate:jaeger-annotations:${dekorateVersion}")
    implementation("io.micronaut:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-multitenancy")
    implementation("io.micronaut:micronaut-discovery-client")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-elastic")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    implementation("io.micronaut.netflix:micronaut-netflix-ribbon")
    implementation("io.micronaut:micronaut-tracing")
    runtimeOnly("io.jaegertracing:jaeger-thrift")
    implementation("io.micronaut.liquibase:micronaut-liquibase")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.elasticsearch:micronaut-elasticsearch")
    implementation("io.micronaut.graphql:micronaut-graphql")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-ktor")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-jackson")
    implementation("io.micronaut.cache:micronaut-cache-hazelcast")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.micronaut.kafka:micronaut-kafka-streams")
    implementation("io.micronaut.nats:micronaut-nats")
    implementation("io.micronaut.xml:micronaut-jackson-xml")
    implementation("io.dekorate:kubernetes-annotations:${dekorateVersion}")
    implementation("io.dekorate:prometheus-annotations:${dekorateVersion}")
    implementation("io.dekorate:jaeger-annotations:${dekorateVersion}")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.apache.logging.log4j:log4j-core:2.12.1")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.12.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.postgresql:postgresql")
    testRuntimeOnly("org.testcontainers:postgresql")
}


application {
    mainClass.set("com.example.Application")
}

java {
    sourceCompatibility = JavaVersion.toVersion("14")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }

    dockerBuild{
        images = ["${System.env.DOCKER_IMAGE}:$project.version"]
    }

    jib {
        to {
            image = "gcr.io/myapp/jib-image"
        }
    }
}

