package com.joshskeen.android.kotlintodolist

import android.os.Bundle
import android.widget.Toast
import com.joshskeen.android.kotlintodolist.model.service.RedditOauthService

import kotlinx.android.synthetic.activity_todo.*
import timber.log.Timber

class TodoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val redditOauthService = RedditOauthService(this)
        setContentView(R.layout.activity_todo)
        activity_todo_edit_text.text = "kotlin is strange!"
        activity_todo_edit_text.setOnClickListener {
            Toast.makeText(this, "nice!", Toast.LENGTH_LONG).show()
        }
        redditOauthService.requestAccessToken().subscribe () {
            response ->
            Timber.i("--> " + response)
        }
    }


}