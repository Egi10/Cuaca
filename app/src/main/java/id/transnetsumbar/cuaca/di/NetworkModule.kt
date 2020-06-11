package id.transnetsumbar.cuaca.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.transnetsumbar.cuaca.network.ApiConfig.config
import id.transnetsumbar.cuaca.network.ApiInterface
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 6/11/20.
 */

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Qualifier
    annotation class NetworkScope

    @Singleton
    @Provides @NetworkScope
    fun provideService(): ApiInterface {
        return config()
    }
}