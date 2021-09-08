package com.aksh.counter.settings

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.CompoundButton
import androidx.databinding.ObservableBoolean

import com.aksh.counter.databinding.ActivityCounterCycleBinding

class CounterCycleActivity: Activity(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityCounterCycleBinding
    private lateinit var settings: Settings
    private lateinit var isEnabled: ObservableBoolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settings = Settings(this)
        isEnabled = ObservableBoolean(settings.isCounterCycleEnabled)

        binding = ActivityCounterCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.isEnabled = isEnabled
        binding.ccE.isChecked = settings.isCounterCycleEnabled
        binding.ccV.isChecked = settings.isCounterCycleVibrateEnabled
        binding.ccL.hint = settings.counterCycleLength.toString()

        binding.ccE.setOnCheckedChangeListener(this)
        binding.ccV.setOnCheckedChangeListener(this)

        binding.ccL.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                textView.text.toString().also {
                    if (it.isNotEmpty()) {
                        settings.counterCycleLength = it.toInt()
                        textView.hint = it
                    }
                }
            }

            false
        }
    }

    override fun onCheckedChanged(button: CompoundButton, isChecked: Boolean) {
        when (button) {
            binding.ccE -> {
                settings.isCounterCycleEnabled = isChecked
                isEnabled.set(isChecked)
            }

            binding.ccV -> {
                settings.isCounterCycleVibrateEnabled = isChecked
            }
        }
    }
}