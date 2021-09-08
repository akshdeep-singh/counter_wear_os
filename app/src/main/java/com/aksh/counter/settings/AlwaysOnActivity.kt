package com.aksh.counter.settings

import android.app.Activity
import android.os.Bundle

import com.aksh.counter.databinding.ActivityAlwaysOnBinding

class AlwaysOnActivity: Activity() {
    private lateinit var binding: ActivityAlwaysOnBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settings = Settings(this)

        binding = ActivityAlwaysOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aoS.apply {
            isChecked = settings.isCounterAlwaysOnEnabled
            setOnCheckedChangeListener { _, isChecked ->
                settings.isCounterAlwaysOnEnabled = isChecked
            }
        }
    }
}