package com.snick.pdf_reader_translator.presentation.files

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snick.pdf_reader_translator.R
import com.snick.pdf_reader_translator.databinding.FileItemBinding
import java.io.File

class FilesAdapter(
    private val onFileClick: (File) -> Unit
) : RecyclerView.Adapter<FilesAdapter.MyViewHolder>(),View.OnClickListener {

    private var files: List<File> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setupAdapter(list: List<File>) {
        files = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FileItemBinding.inflate(layoutInflater, parent, false)
        binding.root.setOnClickListener(this)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(files[position])
    }

    override fun getItemCount(): Int = files.size

    override fun onClick(view: View) {
        val file = view.tag as File
        onFileClick.invoke(file)
    }

    class MyViewHolder(private val binding: FileItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(file: File){
            binding.root.tag = file
            binding.pdfImage.setImageResource(R.drawable.ic_pdf_placeholder)
            binding.pdfTextview.text = file.name
        }
    }

}