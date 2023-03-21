package com.snick.pdf_reader_translator.model

import java.io.File
import java.lang.Exception

interface StorageDataSource {

    suspend fun getFiles(file: File): List<File>


    class Base : StorageDataSource {
        override suspend fun getFiles(file: File): List<File> {
            val pdfFiles = arrayListOf<File>()
            val files = file.listFiles()
            try {
                files?.forEach {
                    if (it.isDirectory && !it.isHidden) {
                        pdfFiles.addAll(getFiles(it))
                    } else {
                        if (it.name.endsWith(".pdf")) {
                            pdfFiles.add(it)
                        }
                    }
                }
            } catch (e: Exception) {

            }
            return pdfFiles
        }
    }
}