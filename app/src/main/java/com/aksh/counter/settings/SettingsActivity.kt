package com.aksh.counter.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.aksh.counter.R

class SettingsActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun View.onClickAnimation()    = startActivity(AnimationActivity::class.java)
    fun View.onClickButtons()      = startActivity(ButtonsActivity::class.java)
    fun View.onClickCounterCycle() = startActivity(CounterCycleActivity::class.java)
    fun View.onClickTextSize()     = startActivity(TextSizeActivity::class.java)

    private fun <C: Activity> startActivity(cls: Class<C>) = startActivity(Intent(this, cls))
}