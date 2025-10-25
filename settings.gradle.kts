/*
 * BU PROJE AYAR DOSYASIDIR.
 * Projenin hangi modülleri içerdiğini (include(":app"))
 * ve kütüphaneleri nereden bulacağını (repositories) tanımlar.
 */
pluginManagement {
    repositories {
        google() // Google'ın kütüphane deposu
        mavenCentral() // Maven'in ana kütüphane deposu
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

// Projenin ana adını belirler
rootProject.name = "GunSonuApp"

// !!! EN ÖNEMLİ SATIR !!!
// "app" adındaki klasörün, bu projeye dahil bir modül olduğunu belirtir.
include(":app")
