plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "ru.itis.effectivemobiletesttask"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt())
    }


    defaultConfig {
        applicationId = "ru.itis.effectivemobiletesttask"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = rootProject.extra.get("versionCode") as Int
        versionName = rootProject.extra.get("versionName") as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}
configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

dependencies {
    implementation(project(":core:core-navigation"))
    implementation(project(":core:core-ui"))
    implementation(project(":features:feature-auth"))
    implementation(project(":features:feature-main"))
    implementation(project(":features:feature-favorites"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}