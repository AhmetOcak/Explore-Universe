pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // Configure the Maven repository address for the HMS Core SDK.
        maven(url = "https://developer.huawei.com/repo/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenCentral()
        // Configure the Maven repository address for the HMS Core SDK.
        maven(url = "https://developer.huawei.com/repo/")
    }
}
rootProject.name = "SpaceApp"
include(":app")
