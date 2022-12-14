pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Trasor"
include(":app")
include(":core-designsystem")
include(":core-ui")
include(":core-model")
include(":feature-new-game")
include(":core-database")
include(":core-data")
include(":core-navigation")
include(":feature-update-game")
include(":feature-games")
include(":core-datastore")
include(":core-datastore")
