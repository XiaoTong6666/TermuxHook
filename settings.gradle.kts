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
        maven("https://api.xposed.info/")
    }
}

rootProject.name = "Termux Double Click Drawer"

val localEzXHelper = System.getenv("EZXHELPER_PATH")
    ?.takeIf { it.isNotBlank() }
    ?.let(::file)
    ?: file("../EzXHelper")
if (localEzXHelper.isDirectory) {
    includeBuild(localEzXHelper)
}

include(":app")
 
