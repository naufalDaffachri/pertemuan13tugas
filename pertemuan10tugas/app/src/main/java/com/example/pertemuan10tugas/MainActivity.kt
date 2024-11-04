package com.example.pertemuan10tugas

// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan10tugas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil fungsi generateDummyEmails untuk mendapatkan daftar email dummy
        val emails = generateDummyEmails()

        binding.recyclerViewEmails.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEmails.adapter = emailAdapter(emails) { email ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("email_sender", email.sender)
                putExtra("email_subject", email.subject)
                putExtra("email_body", email.body)
            }
            startActivity(intent)
        }
    }

    // Fungsi generateDummyEmails sebagai fungsi private di dalam MainActivity
    private fun generateDummyEmails(): List<Email> {
        return listOf(
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "Ronaldo",
                subject = "AKULAH GOAT TERBAIK SEPANJANG MASA",
                date = "19 Oct",
                body = "Sebagai GOAT terbaik, saya berjanji membawa tim ini juara!"
            ),
            Email(
                sender = "Messi",
                subject = "Jangan dengerin bang cr, sesat itu.",
                date = "18 Oct",
                body = "Peringatan! Jangan dengarkan perintah yang menyesatkan."
            ),
            Email(
                sender = "PT NGANG NGONG IND",
                subject = "Pemberitahuan penerimaan kerja",
                date = "17 Oct",
                body = "Selamat! Anda diterima bekerja di perusahaan kami."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "fefefufu",
                subject = "Pemberitahuan, sarapan gratis akan segera dimulai",
                date = "20 Oct",
                body = "Sarapan gratis akan segera dimulai! Kami mengundang semua tamu untuk bergabung..."
            ),
            Email(
                sender = "futufafa",
                subject = "Pemberitahuan, makan siang gratis akan segera dimulai",
                date = "16 Oct",
                body = "Makan siang gratis akan dimulai di ruang makan utama."
            )
        )
    }
}
