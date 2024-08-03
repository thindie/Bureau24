package com.thindie.bureaub.di

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.thindie.bureaub.BureauBApplication
import com.thindie.bureaub.BureauBDb
import com.thindie.bureaub.MainActivity
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.presentation.addscreenflow.description.DescriptionViewModel
import com.thindie.bureaub.presentation.addscreenflow.promo.PromoViewModel
import com.thindie.bureaub.presentation.addscreenflow.success.SuccessViewModel
import com.thindie.bureaub.presentation.addscreenflow.tagging.TaggingViewModel
import com.thindie.bureaub.presentation.addscreenflow.title.TitleViewModel
import com.thindie.bureaub.presentation.bureausurfflow.surf.SurfViewModel
import com.thindie.bureaub.presentation.mainflow.main.MainViewModel
import com.thindie.bureaub.presentation.tagsflow.createtag.CreateTagViewModel
import com.thindie.bureaub.presentation.tagsflow.tags.TagsViewModel
import com.thindie.bureaub.routing.Router

class DependenciesProvider private constructor() {

    private lateinit var dbProvider: DbProvider
    private lateinit var repository: Repository
    private lateinit var db: BureauBDb
    private val router: Router = Router

    val navEventFlow = router.navEvents
    val userFlows = router.userFlows
    fun getVMFactory(viewModelClass: Class<out ViewModel>): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (viewModelClass) {
                    DescriptionViewModel::class.java -> DescriptionViewModel(
                        repository,
                        navEventFlow
                    )

                    PromoViewModel::class.java -> PromoViewModel(repository, navEventFlow)
                    SuccessViewModel::class.java -> SuccessViewModel(repository, navEventFlow)
                    TaggingViewModel::class.java -> TaggingViewModel(repository, navEventFlow)
                    TitleViewModel::class.java -> TitleViewModel(repository, navEventFlow)
                    MainViewModel::class.java -> MainViewModel(navEventFlow)
                    SurfViewModel::class.java -> SurfViewModel(repository, navEventFlow)
                    CreateTagViewModel::class.java -> CreateTagViewModel(navEventFlow, repository)
                    TagsViewModel::class.java -> TagsViewModel(navEventFlow, repository)
                    else -> error("no viewmodel found")
                } as T
            }
        }
    }


    companion object {
        fun inject(application: Application) {
            val provider = DependenciesProvider()
            (application as? BureauBApplication)?.setProvider(
                provider.apply {
                    dbProvider = DbProvider()
                    db = dbProvider.getBureauDb(application)
                    repository = Repository(db)
                }
            )
        }
    }
}

@Composable
fun getDepsProvider(): DependenciesProvider {
    return (LocalContext.current as MainActivity).provider
}

@Composable
fun viewModel(viewModelClass: Class<out ViewModel>): ViewModel {
    val factory = getDepsProvider().getVMFactory(viewModelClass)
    return androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)
}

private class DbProvider {

    private lateinit var sqlDriver: SqlDriver
    private lateinit var bureauB: BureauBDb
    private fun getSqlDriver(context: Context): SqlDriver {
        return if (::sqlDriver.isInitialized) {
            sqlDriver
        } else {
            sqlDriver = AndroidSqliteDriver(
                BureauBDb.Schema,
                context,
                name = "WeatherTitleDb"
            )
            sqlDriver
        }
    }

    fun getBureauDb(context: Context): BureauBDb {
        val sqlDriver by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            getSqlDriver(context)
        }
        return if (::bureauB.isInitialized) {
            bureauB
        } else {
            bureauB = BureauBDb(sqlDriver)
            bureauB
        }
    }
}