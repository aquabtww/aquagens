plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"

    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.aquabtw"
version = "1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
}

bukkit {
    name = "AquaGens"
    apiVersion = "1.19"
    authors = listOf("aquabtw")
    main = "ru.aquabtw.aquagens.AquaGens"
    commands {
        register("test") {}
    }
}

tasks {
    build { dependsOn(shadowJar) }

    shadowJar {
        //relocate("mc.obliviate", "ru.aquabtw.aquagens.frameworks")
    }
}