package id.transnetsumbar.cuaca.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.transnetsumbar.cuaca.network.ApiInterface
import id.transnetsumbar.cuaca.repository.MainRepository
import id.transnetsumbar.cuaca.repository.MainRepositoryImpl
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 6/11/20.
 */

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        @NetworkModule.NetworkScope apiInterface: ApiInterface
    ): MainRepository {
        return MainRepositoryImpl(apiInterface)
    }
}