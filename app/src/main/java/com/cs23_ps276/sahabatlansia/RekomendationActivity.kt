package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cs23_ps276.sahabatlansia.adapter.ListCaregiverAdapter
import com.cs23_ps276.sahabatlansia.api.ApiService
import com.cs23_ps276.sahabatlansia.api.FinderRequest
import com.cs23_ps276.sahabatlansia.api.FinderResponse
import com.cs23_ps276.sahabatlansia.api.ResultItem
import com.cs23_ps276.sahabatlansia.databinding.ActivityRekomendationBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RekomendationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRekomendationBinding
    private lateinit var apiService: ApiService
    private lateinit var caregiverAdapter: ListCaregiverAdapter


    var mobilitas: Int = 0
    var penyakitLain: Int = 0
    var hipertensi: Int = 0
    var diabetes: Int = 0
    var reumatik: Int = 0
    var jantung: Int = 0
    var asma: Int = 0
    var stroke: Int = 0
    var urusRumah: Int = 0
    var aktivitasFisik: Int = 0
    var konsumsiObat: Int = 0
    var cekKesehatan: Int = 0
    var dampingiMenjaga:Int = 0
    var pasangAlat: Int = 0
    var periksaDokter: Int = 0
    var indonesia: Int = 0
    var inggris: Int = 0
    var jawa: Int = 0
    var sunda: Int = 0
    var melayu: Int = 0
    var kota: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRekomendationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val cbIndo =  binding.cbIndonesia
        val cbInggris = binding.cbInggris
        val cbJawa = binding.cbJawa
        val cbSunda = binding.cbSunda
        val cbMelayu = binding.cbMelayu
        val tiKota = binding.textInput1
        val btn = binding.button6

        apiService = createApiService()
        caregiverAdapter = ListCaregiverAdapter(this)

        binding.rbSulit.setOnClickListener { onRadioButtonClicked_Mobilitas(binding.rbSulit) }
        binding.rbBiasa.setOnClickListener { onRadioButtonClicked_Mobilitas(binding.rbBiasa) }
        binding.rbMudah.setOnClickListener { onRadioButtonClicked_Mobilitas(binding.rbMudah) }

        binding.rbHipertensiYa.setOnClickListener { onRadioButtonClicked_Hipertensi(binding.rbHipertensiYa) }
        binding.rbHipertensiTidak.setOnClickListener { onRadioButtonClicked_Hipertensi(binding.rbHipertensiTidak) }

        binding.rbDiabetesYa.setOnClickListener { onRadioButtonClicked_Diabetes(binding.rbDiabetesYa) }
        binding.rbDiabetesTidak.setOnClickListener { onRadioButtonClicked_Diabetes(binding.rbDiabetesTidak) }

        binding.rbReumatikYa.setOnClickListener { onRadioButtonClicked_Reumatik(binding.rbReumatikYa) }
        binding.rbReumatikTidak.setOnClickListener { onRadioButtonClicked_Reumatik(binding.rbReumatikTidak) }

        binding.rbJantungYa.setOnClickListener { onRadioButtonClicked_Jantung(binding.rbJantungYa) }
        binding.rbJantungTidak.setOnClickListener { onRadioButtonClicked_Jantung(binding.rbJantungTidak) }

        binding.rbAsmaYa.setOnClickListener { onRadioButtonClicked_Asma(binding.rbAsmaYa) }
        binding.rbAsmaTidak.setOnClickListener { onRadioButtonClicked_Asma(binding.rbAsmaTidak) }

        binding.rbStrokeYa.setOnClickListener { onRadioButtonClicked_Stroke(binding.rbStrokeYa) }
        binding.rbStrokeTidak.setOnClickListener { onRadioButtonClicked_Stroke(binding.rbStrokeTidak) }

        binding.rbLainYa.setOnClickListener { onRadioButtonClicked_Lain(binding.rbLainYa) }
        binding.rbLainTidak.setOnClickListener { onRadioButtonClicked_Lain(binding.rbLainTidak) }

        binding.rbTidakPerluUrusRumah.setOnClickListener { onRadioButtonClicked_UrusRumah(binding.rbTidakPerluUrusRumah) }
        binding.rbCukupPerluUrusRumah.setOnClickListener { onRadioButtonClicked_UrusRumah(binding.rbCukupPerluUrusRumah) }
        binding.rbPerluUrusRumah.setOnClickListener { onRadioButtonClicked_UrusRumah(binding.rbPerluUrusRumah) }
        binding.rbSangatPerluUrusRumah.setOnClickListener { onRadioButtonClicked_UrusRumah(binding.rbSangatPerluUrusRumah) }

        binding.rbTidakPerluAktivitasFisik.setOnClickListener { onRadioButtonClicked_AktivitasFisik(binding.rbTidakPerluAktivitasFisik) }
        binding.rbCukupPerluAktivitasFisik.setOnClickListener { onRadioButtonClicked_AktivitasFisik(binding.rbCukupPerluAktivitasFisik) }
        binding.rbPerluAktivitasFisik.setOnClickListener { onRadioButtonClicked_AktivitasFisik(binding.rbPerluAktivitasFisik) }
        binding.rbSangatPerluAktivitasFisik.setOnClickListener { onRadioButtonClicked_AktivitasFisik(binding.rbSangatPerluAktivitasFisik) }

        binding.rbTidakPerluObatMakan.setOnClickListener { onRadioButtonClicked_KonsumsiObatMakanan(binding.rbTidakPerluObatMakan) }
        binding.rbCukupPerluObatMakan.setOnClickListener { onRadioButtonClicked_KonsumsiObatMakanan(binding.rbCukupPerluObatMakan) }
        binding.rbPerluObatMakan.setOnClickListener { onRadioButtonClicked_KonsumsiObatMakanan(binding.rbPerluObatMakan) }
        binding.rbSangatPerluObatMakan.setOnClickListener { onRadioButtonClicked_KonsumsiObatMakanan(binding.rbSangatPerluObatMakan) }

        binding.rbTidakPerluCekKesehatan.setOnClickListener { onRadioButtonClicked_CekKesehatan(binding.rbTidakPerluCekKesehatan) }
        binding.rbCukupPerluCekKesehatan.setOnClickListener { onRadioButtonClicked_CekKesehatan(binding.rbCukupPerluCekKesehatan) }
        binding.rbPerluCekKesehatan.setOnClickListener { onRadioButtonClicked_CekKesehatan(binding.rbPerluCekKesehatan) }
        binding.rbSangatPerluCekKesehatan.setOnClickListener { onRadioButtonClicked_CekKesehatan(binding.rbSangatPerluCekKesehatan) }

        binding.rbTidakPerluDampingi.setOnClickListener { onRadioButtonClicked_Menjaga(binding.rbTidakPerluDampingi) }
        binding.rbCukupPerluDampingi.setOnClickListener { onRadioButtonClicked_Menjaga(binding.rbCukupPerluDampingi) }
        binding.rbPerluDampingi.setOnClickListener { onRadioButtonClicked_Menjaga(binding.rbPerluDampingi) }
        binding.rbSangatPerluDampingi.setOnClickListener { onRadioButtonClicked_Menjaga(binding.rbSangatPerluDampingi) }

        binding.rbTidakPerluMedisKhusus.setOnClickListener { onRadioButtonClicked_AlatMedis(binding.rbTidakPerluMedisKhusus) }
        binding.rbCukupPerluMedisKhusus.setOnClickListener { onRadioButtonClicked_AlatMedis(binding.rbCukupPerluMedisKhusus) }
        binding.rbPerluMedisKhusus.setOnClickListener { onRadioButtonClicked_AlatMedis(binding.rbPerluMedisKhusus) }
        binding.rbSangatPerluMedisKhusus.setOnClickListener { onRadioButtonClicked_AlatMedis(binding.rbSangatPerluMedisKhusus) }

        binding.rbTidakPerluPeriksaDokter.setOnClickListener { onRadioButtonClicked_PeriksaDokter(binding.rbTidakPerluPeriksaDokter) }
        binding.rbCukupPerluPeriksaDokter.setOnClickListener { onRadioButtonClicked_PeriksaDokter(binding.rbCukupPerluPeriksaDokter) }
        binding.rbPerluPeriksaDokter.setOnClickListener { onRadioButtonClicked_PeriksaDokter(binding.rbPerluPeriksaDokter) }
        binding.rbSangatPerluPeriksaDokter.setOnClickListener { onRadioButtonClicked_PeriksaDokter(binding.rbSangatPerluPeriksaDokter) }


        btn.setOnClickListener{
            kota = tiKota.text.toString()

            if (cbIndo.isChecked){
                indonesia = 1
            }
            if (cbInggris.isChecked){
                inggris = 1
            }
            if (cbJawa.isChecked){
                jawa = 1
            }
            if (cbSunda.isChecked){
                sunda = 1
            }
            if (cbMelayu.isChecked){
                melayu = 1
            }
            searchCaregiver(mobilitas, penyakitLain, hipertensi, diabetes, reumatik, jantung, asma, stroke, urusRumah, aktivitasFisik, konsumsiObat, cekKesehatan, dampingiMenjaga, pasangAlat, periksaDokter, indonesia, inggris, jawa, sunda, melayu, kota)

        }
    }
    private fun createApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("API_LOG", message) // Log the message to Logcat
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY // Set the desired log level
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://caregiver-all-function-jo6twt65na-et.a.run.app")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private fun searchCaregiver(
        mobilitas: Int,
        penyakitLain: Int,
        hipertensi: Int,
        diabetes: Int,
        reumatik: Int,
        jantung: Int,
        asma: Int,
        stroke: Int,
        urusRumah: Int,
        aktivitasFisik: Int,
        konsumsiObat: Int,
        cekKesehatan: Int,
        dampingiMenjaga:Int,
        pasangAlat: Int,
        periksaDokter: Int,
        indonesia: Int,
        inggris: Int,
        jawa: Int,
        sunda: Int,
        melayu: Int,
        kota: String  ){
        val request = FinderRequest(mobilitas,penyakitLain,hipertensi,diabetes,reumatik,jantung,asma,stroke,urusRumah,aktivitasFisik,konsumsiObat,cekKesehatan,dampingiMenjaga,pasangAlat,periksaDokter,indonesia,inggris, jawa, sunda,melayu,kota)
        val intent = Intent(this, CariCaregiverActivity::class.java)

        apiService.getRecommendedCaregiver(request).enqueue(object : Callback<FinderResponse>{
            override fun onResponse(call: Call<FinderResponse>, response: Response<FinderResponse>){
                if(response.isSuccessful){
                    Toast.makeText(this@RekomendationActivity, "Success", Toast.LENGTH_SHORT).show()
                    val caregiverContent = response.body()?.result

                    if (caregiverContent != null){
                        updateRecommendedCaregiver(caregiverContent as List<ResultItem>)
                        intent.putStringArrayListExtra("caregiverId", ArrayList(caregiverContent.map { it.id.toString() }))
                    }
                    startActivity(intent)

                }
                else {
                    Toast.makeText(this@RekomendationActivity, "Failed to get recommended caregiver", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<FinderResponse>, t: Throwable) {
                Toast.makeText(this@RekomendationActivity, "Failed to communicate with the server", Toast.LENGTH_SHORT).show()

            }
        }

        )
    }

    private fun updateRecommendedCaregiver(caregiverId: List<ResultItem>){
        val caregiverRecommendation = getCaregiver(caregiverId)
        caregiverAdapter.setCaregiver(caregiverRecommendation)
    }

    private fun getCaregiver (caregiverId: List<ResultItem>): List<String>{
        return caregiverId.map{"$it"}
    }


    fun onRadioButtonClicked_Mobilitas(view: View) {
        when (view.id) {
            R.id.rb_sulit -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                mobilitas = 1
            }

            R.id.rb_biasa -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                mobilitas = 2
            }

            R.id.rb_mudah -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.white))
                mobilitas = 3
            }
        }
    }

    fun onRadioButtonClicked_Hipertensi(view: View) {
        when (view.id) {
            R.id.rb_hipertensi_ya -> {
                binding.rbHipertensiYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbHipertensiTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                hipertensi = 1
            }

            R.id.rb_hipertensi_tidak -> {
                binding.rbHipertensiYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbHipertensiTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                hipertensi = 0
            }

        }
    }

    fun onRadioButtonClicked_Diabetes(view: View) {
        when (view.id) {
            R.id.rb_diabetes_ya -> {
                binding.rbDiabetesYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbDiabetesTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                diabetes = 1
            }

            R.id.rb_diabetes_tidak -> {
                binding.rbDiabetesYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbDiabetesTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                diabetes = 0
            }
        }
    }

    fun onRadioButtonClicked_Reumatik(view: View) {
        when (view.id) {
            R.id.rb_reumatik_ya -> {
                binding.rbReumatikYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbReumatikTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                reumatik = 1
            }

            R.id.rb_reumatik_tidak -> {
                binding.rbReumatikYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbReumatikTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                reumatik = 0
            }

        }
    }

    fun onRadioButtonClicked_Jantung(view: View) {
        when (view.id) {
            R.id.rb_jantung_ya -> {
                binding.rbJantungYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbJantungTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                jantung = 1
            }

            R.id.rb_jantung_tidak -> {
                binding.rbJantungYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbJantungTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                jantung = 0
            }

        }
    }

    fun onRadioButtonClicked_Asma(view: View) {
        when (view.id) {
            R.id.rb_asma_ya -> {
                binding.rbAsmaYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbAsmaTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                asma = 1
            }

            R.id.rb_asma_tidak -> {
                binding.rbAsmaYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbAsmaTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                asma = 0

            }

        }
    }

    fun onRadioButtonClicked_Stroke(view: View) {
        when (view.id) {
            R.id.rb_stroke_ya -> {
                binding.rbStrokeYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbStrokeTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                stroke = 1
            }

            R.id.rb_stroke_tidak -> {
                binding.rbStrokeYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbStrokeTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                stroke = 0

            }

        }
    }

    fun onRadioButtonClicked_Lain(view: View) {
        when (view.id) {
            R.id.rb_lain_ya -> {
                binding.rbLainYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbLainTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                penyakitLain = 1
            }

            R.id.rb_lain_tidak -> {
                binding.rbLainYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbLainTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
                penyakitLain = 0
            }

        }
    }

    fun onRadioButtonClicked_UrusRumah(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_urus_rumah -> {
                binding.rbTidakPerluUrusRumah.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbCukupPerluUrusRumah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbPerluUrusRumah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbSangatPerluUrusRumah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                urusRumah = 1

            }

            R.id.rb_cukup_perlu_urus_rumah -> {
                binding.rbTidakPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                urusRumah = 2
            }

            R.id.rb_perlu_urus_rumah -> {
                binding.rbTidakPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluUrusRumah.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbSangatPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                urusRumah = 3
            }

            R.id.rb_sangat_perlu_urus_rumah -> {
                binding.rbTidakPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                urusRumah = 4
            }

        }
    }

    fun onRadioButtonClicked_AktivitasFisik(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_aktivitas_fisik -> {
                binding.rbTidakPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                aktivitasFisik = 1

            }

            R.id.rb_cukup_perlu_aktivitas_fisik -> {
                binding.rbTidakPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                aktivitasFisik = 2
            }

            R.id.rb_perlu_aktivitas_fisik -> {
                binding.rbTidakPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbSangatPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                aktivitasFisik = 3
            }

            R.id.rb_sangat_perlu_aktivitas_fisik -> {
                binding.rbTidakPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluAktivitasFisik.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                aktivitasFisik = 4
            }

        }
    }

    fun onRadioButtonClicked_KonsumsiObatMakanan(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_obat_makan -> {
                binding.rbTidakPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                konsumsiObat = 1

            }

            R.id.rb_cukup_perlu_obat_makan -> {
                binding.rbTidakPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                konsumsiObat = 2
            }

            R.id.rb_perlu_obat_makan -> {
                binding.rbTidakPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluObatMakan.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbSangatPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                konsumsiObat = 3
            }

            R.id.rb_sangat_perlu_obat_makan -> {
                binding.rbTidakPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluObatMakan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                konsumsiObat = 4
            }

        }
    }

    fun onRadioButtonClicked_CekKesehatan(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_cek_kesehatan -> {
                binding.rbTidakPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                cekKesehatan = 1
            }

            R.id.rb_cukup_perlu_cek_kesehatan -> {
                binding.rbTidakPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                cekKesehatan = 2
            }

            R.id.rb_perlu_cek_kesehatan -> {
                binding.rbTidakPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbSangatPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                cekKesehatan = 3
            }

            R.id.rb_sangat_perlu_cek_kesehatan -> {
                binding.rbTidakPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluCekKesehatan.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                cekKesehatan = 4
            }

        }
    }

    fun onRadioButtonClicked_Menjaga(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_dampingi -> {
                binding.rbTidakPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluDampingi.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbSangatPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                dampingiMenjaga = 1
            }

            R.id.rb_cukup_perlu_dampingi -> {
                binding.rbTidakPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluDampingi.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbSangatPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                dampingiMenjaga = 2
            }

            R.id.rb_perlu_dampingi -> {
                binding.rbTidakPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluDampingi.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbSangatPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                dampingiMenjaga = 3
            }

            R.id.rb_sangat_perlu_dampingi -> {
                binding.rbTidakPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluDampingi.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbSangatPerluDampingi.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                dampingiMenjaga = 4
            }

        }
    }

    fun onRadioButtonClicked_AlatMedis(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_medis_khusus -> {
                binding.rbTidakPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                pasangAlat = 1
            }

            R.id.rb_cukup_perlu_medis_khusus -> {
                binding.rbTidakPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                pasangAlat = 2
            }

            R.id.rb_perlu_medis_khusus -> {
                binding.rbTidakPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluMedisKhusus.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbSangatPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                pasangAlat = 3
            }

            R.id.rb_sangat_perlu_medis_khusus -> {
                binding.rbTidakPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluMedisKhusus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                pasangAlat = 4
            }

        }
    }
    fun onRadioButtonClicked_PeriksaDokter(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_periksa_dokter -> {
                binding.rbTidakPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbCukupPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                periksaDokter = 1
            }

            R.id.rb_cukup_perlu_periksa_dokter -> {
                binding.rbTidakPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                periksaDokter = 2
            }

            R.id.rb_perlu_periksa_dokter -> {
                binding.rbTidakPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.rbSangatPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                periksaDokter = 3
            }

            R.id.rb_sangat_perlu_periksa_dokter -> {
                binding.rbTidakPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbCukupPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbPerluPeriksaDokter.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
                binding.rbSangatPerluPeriksaDokter.setTextColor(ContextCompat.getColor(this, R.color.white)
                )
                periksaDokter = 4
            }

        }
    }
}
