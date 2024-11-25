package com.example.pertemuan13

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan13.database.Note
import com.example.pertemuan13.database.NoteDao
import com.example.pertemuan13.database.NoteRoomDatabase
import com.example.pertemuan13.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    // Variabel untuk akses database dan executor service
    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updateId: Int = 0 // Menyimpan ID catatan yang akan diupdate

    // Binding layout XML
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding untuk layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi ExecutorService dan database
        executorService = java.util.concurrent.Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        // Setup button dan interaksi UI
        with(binding) {
            // Tombol Add untuk menambahkan catatan baru
            btnAdd.setOnClickListener(View.OnClickListener {
                insert(
                    Note(
                        title = edtTitle.text.toString(),
                        description = edtDesc.text.toString(),
                        date = edtDate.text.toString()
                    )
                )
                setEmptyField()
            })

            // Tombol Update untuk memperbarui catatan
            btnUpdate.setOnClickListener {
                update(
                    Note(
                        id = updateId,
                        title = edtTitle.text.toString(),
                        description = edtDesc.text.toString(),
                        date = edtDate.text.toString()
                    )
                )
                updateId = 0 // Reset ID update
                setEmptyField()
            }

            // Menangani klik pada ListView untuk update data
            listView.setOnItemClickListener { adapterView, _, i, _ ->
                val item = adapterView.adapter.getItem(i) as Note
                updateId = item.id
                edtTitle.setText(item.title)
                edtDesc.setText(item.description)
                edtDate.setText(item.date)
            }

            // Menangani long click pada ListView untuk menghapus catatan
            listView.setOnItemLongClickListener { adapterView, _, i, _ ->
                val item = adapterView.adapter.getItem(i) as Note
                delete(item)
                true
            }
        }

        // Memanggil getAllNotes untuk menampilkan data di UI saat aplikasi dijalankan
        getAllNotes()
    }

    // Fungsi untuk mengambil semua catatan dari database dan menampilkannya di ListView
    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this) { notes ->
            val adapter = ArrayAdapter<Note>(
                this,
                android.R.layout.simple_list_item_1, notes
            )
            binding.listView.adapter = adapter
        }
    }

    // Fungsi untuk menambahkan catatan baru ke database
    private fun insert(note: Note) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }

    // Fungsi untuk menghapus catatan dari database
    private fun delete(note: Note) {
        executorService.execute {
            mNotesDao.delete(note)
        }
    }

    // Fungsi untuk memperbarui catatan di database
    private fun update(note: Note) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }

    // Fungsi untuk mereset form pada UI
    private fun setEmptyField() {
        with(binding) {
            edtTitle.setText("")
            edtDesc.setText("")
            edtDate.setText("")
        }
    }

    // Fungsi untuk memperbarui daftar catatan di UI setiap kali activity di-resume
    override fun onResume() {
        super.onResume()
        getAllNotes()
    }
}