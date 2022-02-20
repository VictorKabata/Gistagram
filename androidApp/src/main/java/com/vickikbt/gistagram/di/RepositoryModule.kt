package com.vickikbt.gistagram.di

import com.vickikbt.gistagram.repository.profile_repository.ProfileRepository
import com.vickikbt.gistagram.repository.profile_repository.ProfileRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl() }
}