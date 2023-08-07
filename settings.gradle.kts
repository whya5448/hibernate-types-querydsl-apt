enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "querydsl-ext"
include(":querydsl-ext-apt")
include(":querydsl-ext-impl")
include(":querydsl-ext-api")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

