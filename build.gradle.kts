plugins {
    alias(hlaeja.plugins.kotlin.jvm)
    alias(hlaeja.plugins.kotlin.spring)
    alias(hlaeja.plugins.spring.boot)
    alias(hlaeja.plugins.spring.dependency.management)
    alias(hlaeja.plugins.library)
}

dependencies {
    implementation(hlaeja.jjwt.api)
    implementation(hlaeja.kotlin.logging)
    implementation(hlaeja.springboot.starter)

    runtimeOnly(hlaeja.jjwt.impl)
    runtimeOnly(hlaeja.jjwt.jackson)

    testImplementation(hlaeja.assertj.core)
    testImplementation(hlaeja.mockk)
    testImplementation(hlaeja.kotlin.test.junit5)
    testImplementation(hlaeja.springboot.starter.test)

    testRuntimeOnly(hlaeja.junit.platform.launcher)
}

group = "ltd.hlaeja.library"
