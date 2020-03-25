package com.voltmobi.testapp.di.components.repository

import com.voltmobi.testapp.App
import com.voltmobi.testapp.di.RepositoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [App::class],
        modules = [RepositoryModule::class, DatabaseModule::class, SharedPreferenceModule::class]
)
interface RepositoryComponent : RepositoryProvider {

    companion object {
        fun init(app: App): RepositoryProvider {
            return DaggerRepositoryComponent
                    .builder()
                    .app(app)
                    .databaseModule(DatabaseModule(app))
                    .build()
        }
    }

}