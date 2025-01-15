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
        maven("https://repository.map.naver.com/archive/maven")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://repository.map.naver.com/archive/maven")
    }
}

rootProject.name = "Acon"

include(":app")

include(":feature:home")

include(":data")
include(":domain")
include(":core:designsystem")
include(":core:utils:feature")
include(":core:common")
include(":core:map")
include(":feature:onboarding")
include(":core:base")
include(":feature:spot")
include(":feature:signin")
