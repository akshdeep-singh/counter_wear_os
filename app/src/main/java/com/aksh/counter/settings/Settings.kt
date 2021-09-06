package com.aksh.counter.settings

import android.content.Context

class Settings(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    private fun getBoolean(key: String, default: Boolean) = prefs.getBoolean(key, default)
    private fun setBoolean(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    private fun getInt(key: String, default: Int) = prefs.getInt(key, default)
    private fun setInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

    class CounterAction {
        companion object {
            const val INCREMENT = 1
            const val DECREMENT = 2
            const val RESET = 3
        }
    }

    class CounterAnimation {
        companion object {
            const val OFF = 1
            const val FADE = 2
            const val SLIDE = 3
        }
    }

    class CounterTextSize {
        companion object {
            const val SMALL = 24
            const val MEDIUM = 32
            const val LARGE = 40
        }
    }

    companion object {
        private const val KEY_ENABLED_COUNTER_ANIMATION = "keca"
        private const val KEY_ENABLED_COUNTER_ALWAYS_ON = "kecao"

        private const val KEY_ENABLED_COUNTER_CYCLE = "kecc"
        private const val KEY_ENABLED_COUNTER_CYCLE_VIBRATE = "keccv"

        private const val KEY_ENABLED_BUTTON_1 = "keb1"
        private const val KEY_ENABLED_BUTTON_2 = "keb2"
        private const val KEY_ENABLED_BUTTON_3 = "keb3"

        private const val KEY_COUNTER_ANIMATION = "kca"
        private const val KEY_COUNTER_CYCLE_LENGTH = "kccl"
        private const val KEY_COUNTER_TEXT_SIZE = "kcts"

        private const val KEY_BUTTON_1_ACTION = "kb1a"
        private const val KEY_BUTTON_2_ACTION = "kb2a"
        private const val KEY_BUTTON_3_ACTION = "kb3a"
    }

    var isCounterAnimationEnabled
        get() = getBoolean(KEY_ENABLED_COUNTER_ANIMATION, true)
        set(value) = setBoolean(KEY_ENABLED_COUNTER_ANIMATION, value)

    var isCounterAlwaysOnEnabled
        get() = getBoolean(KEY_ENABLED_COUNTER_ALWAYS_ON, false)
        set(value) = setBoolean(KEY_ENABLED_COUNTER_ALWAYS_ON, value)

    var isCounterCycleEnabled
        get() = getBoolean(KEY_ENABLED_COUNTER_CYCLE, false)
        set(value) = setBoolean(KEY_ENABLED_COUNTER_CYCLE, value)

    var isCounterCycleVibrateEnabled
        get() = getBoolean(KEY_ENABLED_COUNTER_CYCLE_VIBRATE, true)
        set(value) = setBoolean(KEY_ENABLED_COUNTER_CYCLE_VIBRATE, value)

    var isButton1Enabled
        get() = getBoolean(KEY_ENABLED_BUTTON_1, true)
        set(value) = setBoolean(KEY_ENABLED_BUTTON_1, value)

    var isButton2Enabled
        get() = getBoolean(KEY_ENABLED_BUTTON_2, false)
        set(value) = setBoolean(KEY_ENABLED_BUTTON_2, value)

    var isButton3Enabled
        get() = getBoolean(KEY_ENABLED_BUTTON_3, false)
        set(value) = setBoolean(KEY_ENABLED_BUTTON_3, value)

    var counterAnimation
        get() = getInt(KEY_COUNTER_ANIMATION, CounterAnimation.OFF)
        set(value) = setInt(KEY_COUNTER_ANIMATION, value)

    var counterCycleLength
        get() = getInt(KEY_COUNTER_CYCLE_LENGTH, 10)
        set(value) = setInt(KEY_COUNTER_CYCLE_LENGTH, value)

    var counterTextSize
        get() = getInt(KEY_COUNTER_TEXT_SIZE, CounterTextSize.MEDIUM)
        set(value) = setInt(KEY_COUNTER_TEXT_SIZE, value)

    var button1Action
        get() = getInt(KEY_BUTTON_1_ACTION, CounterAction.INCREMENT)
        set(value) = setInt(KEY_BUTTON_1_ACTION, value)

    var button2Action
        get() = getInt(KEY_BUTTON_2_ACTION, CounterAction.DECREMENT)
        set(value) = setInt(KEY_BUTTON_2_ACTION, value)

    var button3Action
        get() = getInt(KEY_BUTTON_3_ACTION, CounterAction.RESET)
        set(value) = setInt(KEY_BUTTON_3_ACTION, value)
}