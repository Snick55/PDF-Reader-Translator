package com.snick.pdf_reader_translator.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snick.pdf_reader_translator.model.FilesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FilesViewModel @Inject constructor (
    private val repository: FilesRepository
): ViewModel() {


    private val _pdfFiles = MutableLiveData<List<File>>()
    val pdfFiles: LiveData<List<File>> = _pdfFiles



    fun getPdfFiles(file: File) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getFiles(file)
        withContext(Dispatchers.Main){
            _pdfFiles.value = result
        }
    }

}