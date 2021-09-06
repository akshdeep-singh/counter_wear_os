package com.aksh.counter.settings

import android.app.Activity
import android.os.Bundle
import android.view.View

import com.aksh.counter.databinding.ActivityAnimationBinding
import com.aksh.counter.settings.Settings.CounterAnimation

class AnimationActivity: Activity() {
    private lateinit var binding: ActivityAnimationBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settings = Settings(this)

        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (settings.counterAnimation) {
            CounterAnimation.OFF   -> binding.animO.isChecked = true
            CounterAnimation.FADE  -> binding.animF.isChecked = true
            CounterAnimation.SLIDE -> binding.animS.isChecked = true
        }
    }

    fun View.onClick() {
        when (this) {
            binding.animO -> settings.counterAnimation = CounterAnimation.OFF
            binding.animF -> settings.counterAnimation = CounterAnimation.FADE
            binding.animS -> settings.counterAnimation = CounterAnimation.SLIDE
        }
    }
}