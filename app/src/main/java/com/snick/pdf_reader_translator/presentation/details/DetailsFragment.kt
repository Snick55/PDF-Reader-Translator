package com.snick.pdf_reader_translator.presentation.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.barteksc.pdfviewer.PDFView
import com.snick.pdf_reader_translator.databinding.DetailsFragmentBinding
import com.snick.pdf_reader_translator.databinding.FilesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!


    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filePath = args.filePath
        val uriPath = Uri.fromFile(File(filePath))

        binding.pdfView.fromUri(uriPath)
            .load()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}