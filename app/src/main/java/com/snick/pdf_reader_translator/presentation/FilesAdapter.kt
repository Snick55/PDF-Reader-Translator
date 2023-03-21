package com.snick.pdf_reader_translator.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.snick.pdf_reader_translator.R
import com.snick.pdf_reader_translator.databinding.FileItemBinding
import java.io.File

class FilesAdapter : RecyclerView.Adapter<FilesAdapter.MyViewHolder>() {

    private var files: List<File> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setupAdapter(list: List<File>) {
        files = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FileItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(files[position])
    }

    override fun getItemCount(): Int = files.size

    class MyViewHolder(private val binding: FileItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(file: File){
            binding.pdfImage.setImageResource(R.drawable.ic_pdf_placeholder)
            binding.pdfTextview.text = file.name
        }
    }

}