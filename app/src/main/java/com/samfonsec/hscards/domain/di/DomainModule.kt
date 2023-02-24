package com.samfonsec.hscards.domain.di

import com.samfonsec.hscards.domain.useCase.GetCardsUseCase
import com.samfonsec.hscards.domain.useCase.GetCardsUseCaseImpl
import com.samfonsec.hscards.domain.useCase.GetClassesUseCase
import com.samfonsec.hscards.domain.useCase.GetClassesUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<GetCardsUseCase> { GetCardsUseCaseImpl(get()) }
    single<GetClassesUseCase> { GetClassesUseCaseImpl(get()) }
}
