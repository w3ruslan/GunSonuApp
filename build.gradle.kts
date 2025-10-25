/*
 * BU TÜM PROJENİN İNŞA TALİMATIDIR (KÖK DİZİN)
 * Projeyi inşa etmek için hangi "Plugin"lerin (araçların) 
 * kullanılacağını tanımlar.
 */
plugins {
    // Android Uygulama inşa etme aracını (plugin) tanımlar
    alias(libs.plugins.android.application) apply false
    
    // Kotlin dilini inşa etme aracını (plugin) tanımlar
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
