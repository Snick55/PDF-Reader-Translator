package com.snick.pdf_reader_translator.model

import java.io.File

interface FilesRepository {

  suspend  fun getFiles(file: File): List<File>



    class Base(private val storageDataSource: StorageDataSource): FilesRepository{


        override suspend fun getFiles(file: File): List<File> {
          return  storageDataSource.getFiles(file)
        }
    }

}