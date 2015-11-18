package com.joshskeen.android.kotlintodolist

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TodoApplicationModule::class))
public interface TodoAppComponent {
    fun inject(application: TodoApplication)
    fun inject(activity: BaseActivity)
}