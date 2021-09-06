package com.aksh.counter.settings

import android.app.Activity
import android.os.Bundle
import android.view.View

import com.aksh.counter.databinding.ActivityTextSizeBinding
import com.aksh.counter.settings.Settings.CounterTextSize

class TextSizeActivity: Activity() {
    private lateinit var binding: ActivityTextSizeBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settings = Settings(this)

        binding = ActivityTextSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (settings.counterTextSize) {
            CounterTextSize.SMALL  -> binding.tsS.isChecked = true
            CounterTextSize.MEDIUM -> binding.tsM.isChecked = true
            CounterTextSize.LARGE  -> binding.tsL.isChecked = true
        }
    }

    fun View.onClick() {
        when (this) {
            binding.tsS -> settings.counterTextSize = CounterTextSize.SMALL
            binding.tsM -> settings.counterTextSize = CounterTextSize.MEDIUM
            binding.tsL -> settings.counterTextSize = CounterTextSize.LARGE
        }
    }
}