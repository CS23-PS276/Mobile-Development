package com.cs23_ps276.sahabatlansia

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cs23_ps276.sahabatlansia.databinding.ActivityRekomendationBinding

class RekomendationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRekomendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRekomendationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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


    }

    fun onRadioButtonClicked_Mobilitas(view: View) {
        when (view.id) {
            R.id.rb_sulit -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_biasa -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_mudah -> {
                binding.rbSulit.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbBiasa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbMudah.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    fun onRadioButtonClicked_Hipertensi(view: View) {
        when (view.id) {
            R.id.rb_hipertensi_ya -> {
                binding.rbHipertensiYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbHipertensiTidak.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.teal_700
                    )
                )
            }

            R.id.rb_hipertensi_tidak -> {
                binding.rbHipertensiYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbHipertensiTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Diabetes(view: View) {
        when (view.id) {
            R.id.rb_diabetes_ya -> {
                binding.rbDiabetesYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbDiabetesTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_diabetes_tidak -> {
                binding.rbDiabetesYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbDiabetesTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Reumatik(view: View) {
        when (view.id) {
            R.id.rb_reumatik_ya -> {
                binding.rbReumatikYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbReumatikTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_reumatik_tidak -> {
                binding.rbReumatikYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbReumatikTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Jantung(view: View) {
        when (view.id) {
            R.id.rb_jantung_ya -> {
                binding.rbJantungYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbJantungTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_jantung_tidak -> {
                binding.rbJantungYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbJantungTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Asma(view: View) {
        when (view.id) {
            R.id.rb_asma_ya -> {
                binding.rbAsmaYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbAsmaTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_asma_tidak -> {
                binding.rbAsmaYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbAsmaTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Stroke(view: View) {
        when (view.id) {
            R.id.rb_stroke_ya -> {
                binding.rbStrokeYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbStrokeTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_stroke_tidak -> {
                binding.rbStrokeYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbStrokeTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_Lain(view: View) {
        when (view.id) {
            R.id.rb_lain_ya -> {
                binding.rbLainYa.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.rbLainTidak.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
            }

            R.id.rb_stroke_tidak -> {
                binding.rbLainYa.setTextColor(ContextCompat.getColor(this, R.color.teal_700))
                binding.rbLainTidak.setTextColor(ContextCompat.getColor(this, R.color.white))
            }

        }
    }

    fun onRadioButtonClicked_UrusRumah(view: View) {
        when (view.id) {
            R.id.rb_tidak_perlu_urus_rumah -> {
                binding.rbTidakPerluUrusRumah.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
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
                        R.color.teal_700
                    )
                )

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
                    binding.rbSangatPerluPeriksaDokter.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                }

            }
        }

    }
