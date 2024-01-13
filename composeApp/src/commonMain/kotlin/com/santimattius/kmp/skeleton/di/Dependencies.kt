package com.santimattius.kmp.skeleton.di

import com.santimattius.kmp.skeleton.core.data.PictureRepository
import com.santimattius.kmp.skeleton.core.network.ServiceCreator
import com.santimattius.kmp.skeleton.features.home.HomeScreenModel
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val sharedModules = module {
    single(qualifier(AppQualifiers.BaseUrl)) { "https://api-picture.onrender.com/" }
    single(qualifier(AppQualifiers.Service)) {
        ServiceCreator(
            baseUrl = get(
                qualifier = qualifier(
                    AppQualifiers.BaseUrl
                )
            )
        ).createPictureService()
    }

    single { PictureRepository(get(qualifier(AppQualifiers.Service))) }
}

val homeModule = module {
    factory { HomeScreenModel(repository = get()) }
}


fun applicationModules() = listOf(sharedModules, homeModule)