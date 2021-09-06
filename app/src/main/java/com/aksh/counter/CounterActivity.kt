package com.aksh.counter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt

import com.aksh.counter.settings.Settings.CounterAction
import com.aksh.counter.databinding.ActivityCounterBinding
import com.aksh.counter.settings.Settings
import com.aksh.counter.settings.SettingsActivity

class CounterActivity: Activity() {
    private lateinit var vibrator: Vibrator
    private lateinit var binding: ActivityCounterBinding
    private lateinit var settings: Settings
    private lateinit var isAnimationEnabled: ObservableBoolean
    private lateinit var isCycleEnabled: ObservableBoolean
    private lateinit var animation: ObservableInt
    private lateinit var textSize: ObservableInt

    private val counter = ObservableInt()
    private val _counter: Int get() = counter.get()

    private val cycleCounts = object : ObservableInt(counter) {
        override fun get() =
            if (settings.isCounterCycleEnabled)
                _counter.div(settings.counterCycleLength)
            else
                0
    }

    private val cycleCounter = object : ObservableInt(counter) {
        override fun get() =
            if (settings.isCounterCycleEnabled)
                _counter.mod(settings.counterCycleLength)
            else
                _counter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        settings = Settings(this)
        isAnimationEnabled = ObservableBoolean(settings.isCounterAnimationEnabled)
        isCycleEnabled = ObservableBoolean(settings.isCounterCycleEnabled)
        animation = ObservableInt(settings.counterAnimation)
        textSize = ObservableInt(settings.counterTextSize)

        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.isAnimationEnabled = isAnimationEnabled
        binding.isCycleEnabled = isCycleEnabled
        binding.cycle = cycleCounts
        binding.counter = cycleCounter
        binding.textSize = textSize
    }

    override fun onResume() {
        super.onResume()
        isAnimationEnabled.set(settings.isCounterAnimationEnabled)
        isCycleEnabled.set(settings.isCounterCycleEnabled)
        animation.set(settings.counterAnimation)
        textSize.set(settings.counterTextSize)
        counter.notifyChange()
    }

    // https://developer.android.com/training/wearables/user-input/physical-buttons#button-presses
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        Log.d("onKeyDown", "keyCode: $keyCode, repeatCount: ${event.repeatCount}")

        if (event.repeatCount == 0) {
            when (keyCode) {
                KeyEvent.KEYCODE_STEM_1 -> {
                    if (settings.isButton1Enabled) {
                        when (settings.button1Action) {
                            CounterAction.INCREMENT -> incCounter()
                            CounterAction.DECREMENT -> decCounter()
                            CounterAction.RESET ->     rstCounter()
                        }

                        return true
                    }
                }

                KeyEvent.KEYCODE_STEM_2 -> {
                    if (settings.isButton2Enabled) {
                        when (settings.button2Action) {
                            CounterAction.INCREMENT -> incCounter()
                            CounterAction.DECREMENT -> decCounter()
                            CounterAction.RESET ->     rstCounter()
                        }

                        return true
                    }
                }

                KeyEvent.KEYCODE_STEM_3 -> {
                    if (settings.isButton3Enabled) {
                        when (settings.button3Action) {
                            CounterAction.INCREMENT -> incCounter()
                            CounterAction.DECREMENT -> decCounter()
                            CounterAction.RESET ->     rstCounter()
                        }

                        return true
                    }
                }
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    fun View.onClickInc() = incCounter()
    fun View.onClickDec() = decCounter()
    fun View.onClickRst() = rstCounter()

    fun View.openSettings() {
        val intent = Intent(this@CounterActivity, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun setCounter(value: Int) {
        counter.set(value)

        if (settings.isCounterCycleEnabled) {
            if (cycleCounter.get() == 0) {
                if (settings.isCounterCycleVibrateEnabled) {
                    vibrate()
                }
            }
        }
    }

    private fun incCounter() {
        setCounter(_counter + 1)
    }

    private fun decCounter() {
        if (_counter > 0) {
            setCounter(_counter - 1)
        } else vibrate()
    }

    private fun rstCounter() = setCounter(0)

    private fun vibrate() {
        vibrator.vibrate(
            VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }
}