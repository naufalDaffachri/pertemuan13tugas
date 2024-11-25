package com.example.pertemuan13tugas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan13tugas.databinding.ItemVoterBinding

class VoterAdapter(
    private val voterList: List<Voter>,
    private val onEditClick: (Voter) -> Unit,
    private val onDetailClick: (Voter) -> Unit
) : RecyclerView.Adapter<VoterAdapter.VoterViewHolder>() {

    inner class VoterViewHolder(val binding: ItemVoterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoterViewHolder {
        val binding = ItemVoterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VoterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoterViewHolder, position: Int) {
        val voter = voterList[position]
        holder.binding.voterNameTextView.text = voter.name

        holder.binding.editButton.setOnClickListener {
            onEditClick(voter)
        }

        holder.binding.detailButton.setOnClickListener {
            onDetailClick(voter)
        }
    }

    override fun getItemCount(): Int = voterList.size
}
