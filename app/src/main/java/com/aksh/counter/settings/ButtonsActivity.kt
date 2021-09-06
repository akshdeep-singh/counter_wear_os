package com.aksh.counter.settings

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.ObservableBoolean
import androidx.wear.input.WearableButtons

import com.aksh.counter.databinding.ActivityButtonsBinding
import com.aksh.counter.settings.Settings.CounterAction

class ButtonsActivity: Activity(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityButtonsBinding
    private lateinit var settings: Settings
    private lateinit var isButton1Enabled: ObservableBoolean
    private lateinit var isButton2Enabled: ObservableBoolean
    private lateinit var isButton3Enabled: ObservableBoolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settings = Settings(this)
        isButton1Enabled = ObservableBoolean(settings.isButton1Enabled)
        isButton2Enabled = ObservableBoolean(settings.isButton2Enabled)
        isButton3Enabled = ObservableBoolean(settings.isButton3Enabled)

        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.isButton1Present = isButtonAvailable(KeyEvent.KEYCODE_STEM_1)
        binding.isButton2Present = isButtonAvailable(KeyEvent.KEYCODE_STEM_2)
        binding.isButton3Present = isButtonAvailable(KeyEvent.KEYCODE_STEM_3)

        binding.isButton1Enabled = isButton1Enabled
        binding.isButton2Enabled = isButton2Enabled
        binding.isButton3Enabled = isButton3Enabled

        binding.button1Icon = getButtonIcon(KeyEvent.KEYCODE_STEM_1)
        binding.button2Icon = getButtonIcon(KeyEvent.KEYCODE_STEM_2)
        binding.button3Icon = getButtonIcon(KeyEvent.KEYCODE_STEM_3)

        binding.b1S.isChecked = isButton1Enabled.get()
        binding.b2S.isChecked = isButton2Enabled.get()
        binding.b3S.isChecked = isButton3Enabled.get()

        binding.b1S.setOnCheckedChangeListener(this)
        binding.b2S.setOnCheckedChangeListener(this)
        binding.b3S.setOnCheckedChangeListener(this)

        when (settings.button1Action) {
            CounterAction.INCREMENT -> binding.b1I.isChecked = true
            CounterAction.DECREMENT -> binding.b1D.isChecked = true
            CounterAction.RESET     -> binding.b1R.isChecked = true
        }

        when (settings.button2Action) {
            CounterAction.INCREMENT -> binding.b2I.isChecked = true
            CounterAction.DECREMENT -> binding.b2D.isChecked = true
            CounterAction.RESET     -> binding.b2R.isChecked = true
        }

        when (settings.button3Action) {
            CounterAction.INCREMENT -> binding.b3I.isChecked = true
            CounterAction.DECREMENT -> binding.b3D.isChecked = true
            CounterAction.RESET     -> binding.b3R.isChecked = true
        }
    }

    override fun onCheckedChanged(button: CompoundButton, isChecked: Boolean) {
        when (button) {
            binding.b1S -> {
                settings.isButton1Enabled = isChecked
                isButton1Enabled.set(isChecked)
            }

            binding.b2S -> {
                settings.isButton2Enabled = isChecked
                isButton2Enabled.set(isChecked)
            }

            binding.b3S -> {
                settings.isButton3Enabled = isChecked
                isButton3Enabled.set(isChecked)
            }
        }
    }

    fun View.onClick() {
        when (this) {
            binding.b1I -> settings.button1Action = CounterAction.INCREMENT
            binding.b1D -> settings.button1Action = CounterAction.DECREMENT
            binding.b1R -> settings.button1Action = CounterAction.RESET

            binding.b2I -> settings.button2Action = CounterAction.INCREMENT
            binding.b2D -> settings.button2Action = CounterAction.DECREMENT
            binding.b2R -> settings.button2Action = CounterAction.RESET

            binding.b3I -> settings.button3Action = CounterAction.INCREMENT
            binding.b3D -> settings.button3Action = CounterAction.DECREMENT
            binding.b3R -> settings.button3Action = CounterAction.RESET
        }
    }

    private fun isButtonAvailable(keyCode: Int): Boolean =
        WearableButtons.getButtonInfo(this, keyCode) != null

    private fun getButtonIcon(keyCode: Int): Drawable? =
        WearableButtons.getButtonIcon(this, keyCode)
}