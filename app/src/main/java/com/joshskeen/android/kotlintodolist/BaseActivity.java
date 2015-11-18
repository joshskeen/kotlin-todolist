package com.joshskeen.android.kotlintodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTodoAppComponent.builder()
                .todoApplicationModule(new TodoApplicationModule())
                .build()
                .inject(this);
    }
}

