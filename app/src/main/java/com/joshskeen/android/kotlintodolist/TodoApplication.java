package com.joshskeen.android.kotlintodolist;

import android.app.Application;

import timber.log.Timber;

class TodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        DaggerTodoAppComponent
                .builder()
                .todoApplicationModule(new TodoApplicationModule())
                .build()
                .inject(this);

    }
}

