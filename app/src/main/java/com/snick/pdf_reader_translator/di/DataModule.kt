package com.snick.pdf_reader_translator.di

import com.snick.pdf_reader_translator.model.FilesRepository
import com.snick.pdf_reader_translator.model.StorageDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {



    @Binds
    abstract fun bindRepository(repository: FilesRepository.Base): FilesRepository

    @Binds
    abstract fun bindStorageDataSource(
        storageDataSource: StorageDataSource.Base
    ): StorageDataSource

}