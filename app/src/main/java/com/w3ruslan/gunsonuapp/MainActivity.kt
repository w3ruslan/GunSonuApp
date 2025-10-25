/*
 * BU UYGULAMANIN BEYNİDİR (KOTLIN KODU)
 * Paket adı, Adım 2'deki Manifest dosyasıyla aynı olmalıdır
 */
package com.w3ruslan.gunsonuapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.w3ruslan.gunsonuapp.databinding.ActivityMainBinding
import java.util.Locale

/*
 * Adım 2'de (Manifest) ".MainActivity" olarak belirttiğimiz ana ekran (Activity) budur.
 */
class MainActivity : AppCompatActivity() {

    /*
     * "View Binding" denilen modern bir teknik kullanıyoruz.
     * Bu, XML'deki (tasarımdaki) tüm kutulara (EditText, TextView) 
     * kod içinden "binding." diyerek kolayca ulaşmamızı sağlar.
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Tasarımımızı (activity_main.xml) yüklüyoruz ve "binding"i hazırlıyoruz
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bütün hesaplama dinleyicilerini (listeners) kuran fonksiyonu çağır
        setupListeners()
    }

    /**
     * BÜTÜN EditText (giriş kutularını) dinleyen ana fonksiyon.
     * Herhangi bir kutuya tek bir harf/rakam bile yazılsa, 
     * bu fonksiyon içindeki "watcher" tetiklenir.
     */
    private fun setupListeners() {
        
        // "watcher" (izleyici) nesnesi
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            // Kullanıcı yazmayı bitirdiği *anda* (afterTextChanged)
            override fun afterTextChanged(s: Editable?) {
                // CB ve Nakit hesaplamalarını anında yeniden yap
                hesaplaCb()
                hesaplaNakit()
            }
        }

        // --- Bütün giriş kutularına bu "watcher"ı (izleyiciyi) ekliyoruz ---

        // CB (Sol Taraf) Dinleyicileri
        binding.etKasaCbToplam.addTextChangedListener(watcher)
        binding.etPosAvecContact.addTextChangedListener(watcher)
        binding.etPosSansContact.addTextChangedListener(watcher)

        // Nakit Sayacı (Sağ Taraf - Adetler) Dinleyicileri
        binding.et500.addTextChangedListener(watcher)
        binding.et200.addTextChangedListener(watcher)
        binding.et100.addTextChangedListener(watcher)
        binding.et50.addTextChangedListener(watcher)
        binding.et20.addTextChangedListener(watcher)
        binding.et10.addTextChangedListener(watcher)
        binding.et5.addTextChangedListener(watcher)
        binding.et1.addTextChangedListener(watcher)
        binding.etCent.addTextChangedListener(watcher)

        // Nakit Toplamları (Sağ Taraf - Tutar) Dinleyicileri
        binding.etKasaNakitToplam.addTextChangedListener(watcher)
        binding.etFondCaisse.addTextChangedListener(watcher)
    }

    /**
     * CB (Sol Taraf) hesaplamasını yapar ve sonucu renkli kutuya yazar
     */
    private fun hesaplaCb() {
        // Kutulardaki yazıları "Double" (ondalıklı sayı) tipine çevir
        // textToDouble() -> bizim aşağıda yazdığımız yardımcı fonksiyondur
        val kasaToplam = textToDouble(binding.etKasaCbToplam.text.toString())
        val posContact = textToDouble(binding.etPosAvecContact.text.toString())
        val posSansContact = textToDouble(binding.etPosSansContact.text.toString())

        val posToplam = posContact + posSansContact
        val fark = posToplam - kasaToplam

        // Sonucu formatla (örn: "0.00" veya "+10.50" veya "-5.00")
        val farkStr = String.format(Locale.US, "%.2f", fark)
        binding.tvCbFark.text = if (fark > 0) "+$farkStr" else farkStr

        // Farka göre "REGLEMENT CLIENT YAP" kutusunun rengini değiştir
        when {
            fark < 0 -> binding.cardCbFark.setCardBackgroundColor(Color.parseColor("#FFCDD2")) // Kırmızı
            fark > 0 -> binding.cardCbFark.setCardBackgroundColor(Color.parseColor("#C8E6C9")) // Yeşil
            else -> binding.cardCbFark.setCardBackgroundColor(Color.parseColor("#E0E0E0"))     // Gri (Nötr)
        }
    }

    /**
     * NAKİT (Sağ Taraf) hesaplamasını yapar ve sonucu renkli kutuya yazar
     */
    private fun hesaplaNakit() {
        // --- 1. Adım: Sayılan Toplamı Hesapla (Para Sayacı) ---
        val t500 = textToDouble(binding.et500.text.toString()) * 500
        val t200 = textToDouble(binding.et200.text.toString()) * 200
        val t100 = textToDouble(binding.et100.text.toString()) * 100
        val t50 = textToDouble(binding.et50.text.toString()) * 50
        val t20 = textToDouble(binding.et20.text.toString()) * 20
        val t10 = textToDouble(binding.et10.text.toString()) * 10
        val t5 = textToDouble(binding.et5.text.toString()) * 5
        val t1 = textToDouble(binding.et1.text.toString()) * 1
        val tCent = textToDouble(binding.etCent.text.toString()) * 0.01

        val sayilanToplam = t500 + t200 + t100 + t50 + t20 + t10 + t5 + t1 + tCent

        // Sayılan toplamı ekrandaki "Sayılan Toplam" yazısına yaz
        binding.tvSayilanToplam.text = String.format(Locale.US, "%.2f €", sayilanToplam)

        // --- 2. Adım: Beklenen Toplamı Hesapla ---
        val kasaNakit = textToDouble(binding.etKasaNakitToplam.text.toString())
        val fondCaisse = textToDouble(binding.etFondCaisse.text.toString())
        val beklenenToplam = kasaNakit + fondCaisse

        // --- 3. Adım: Farkı Bul ve Renkli Kutuya Yaz ---
        val nakitFark = sayilanToplam - beklenenToplam

        // Sonucu formatla (örn: "0.00" veya "+10.50" veya "-5.00")
        val nakitFarkStr = String.format(Locale.US, "%.2f", nakitFark)
        binding.tvNakitFark.text = if (nakitFark > 0) "+$nakitFarkStr" else nakitFarkStr

        // Farka göre "NAKİT FARKI" kutusunun rengini değiştir
        when {
            nakitFark < 0 -> binding.cardNakitFark.setCardBackgroundColor(Color.parseColor("#FFCDD2")) // Kırmızı
            nakitFark > 0 -> binding.cardNakitFark.setCardBackgroundColor(Color.parseColor("#C8E6C9")) // Yeşil
            else -> binding.cardNakitFark.setCardBackgroundColor(Color.parseColor("#E0E0E0"))     // Gri (Nötr)
        }
    }

    /**
     * YARDIMCI Fonksiyon:
     * Kutuya yazılan metni (String) alır, sayıya (Double) çevirir.
     * Eğer kutu boşsa veya geçersizse (örn: ".") 0.0 döndürür.
     */
    private fun textToDouble(text: String): Double {
        return text.toDoubleOrNull() ?: 0.0
    }
}
