pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EffectiveMobileTestTask"
include(":app")
include(":core:core-ui")
include(":core:core-network")
include(":core:core-common")
include(":core:core-database")
include(":features:feature-auth")
include(":features:feature-main")
include(":core:core-utils")
include(":core:core-navigation")
include(":features:feature-favorites")
include(":features:feature-account")
