package com.example.pertemuan10tugas

// emailAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan10tugas.databinding.ItemEmailBinding


class emailAdapter(
    private val emails: List<Email>,
    private val onItemClick: (Email) -> Unit
) : RecyclerView.Adapter<emailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(private val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: Email) {
            binding.tvSender.text = email.sender
            binding.tvSubject.text = email.subject
            binding.tvDate.text = email.date
            binding.root.setOnClickListener { onItemClick(email) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = ItemEmailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    override fun getItemCount(): Int = emails.size
}
