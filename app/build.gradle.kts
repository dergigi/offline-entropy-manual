import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

fun gitShortHash(): String = try {
    val process = ProcessBuilder("git", "rev-parse", "--short", "HEAD")
        .directory(rootProject.projectDir)
        .redirectErrorStream(true)
        .start()
    process.waitFor()
    process.inputStream.bufferedReader().readText().trim().ifEmpty { "unknown" }
} catch (_: Exception) {
    "unknown"
}

fun localProp(name: String): String? {
    val file = rootProject.file("local.properties")
    if (!file.exists()) return System.getenv(name)
    val props = Properties()
    file.inputStream().use { props.load(it) }
    return props.getProperty(name) ?: System.getenv(name)
}

android {
    namespace = "org.dergigi.offlineentropymanual"
    compileSdk = 35
    buildToolsVersion = "35.0.0"

    defaultConfig {
        applicationId = "org.dergigi.offlineentropymanual"
        minSdk = 26
        targetSdk = 35
        versionCode = 4
        versionName = "1.2.1"
        buildConfigField("String", "GIT_HASH", "\"${gitShortHash()}\"")
    }

    val storeFilePath = localProp("OEM_STORE_FILE")
    val storePassword = localProp("OEM_STORE_PASSWORD")
    val keyAlias = localProp("OEM_KEY_ALIAS")
    val keyPassword = localProp("OEM_KEY_PASSWORD")
    val hasReleaseSigning =
        !storeFilePath.isNullOrBlank() &&
            !storePassword.isNullOrBlank() &&
            !keyAlias.isNullOrBlank() &&
            !keyPassword.isNullOrBlank() &&
            file(storeFilePath).exists()

    if (hasReleaseSigning) {
        signingConfigs {
            create("release") {
                storeFile = file(storeFilePath!!)
                this.storePassword = storePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    androidResources {
        noCompress += "pdf"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
