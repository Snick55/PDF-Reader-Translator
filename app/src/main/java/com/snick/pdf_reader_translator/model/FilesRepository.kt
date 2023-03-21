package com.snick.pdf_reader_translator.model

import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

interface FilesRepository {

  suspend  fun getFiles(file: File): List<File>



  @Singleton
    class Base @Inject constructor (private val storageDataSource: StorageDataSource): FilesRepository{


        override suspend fun getFiles(file: File): List<File> {
          return  storageDataSource.getFiles(file)
        }
    }

}