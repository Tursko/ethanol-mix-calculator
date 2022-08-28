package com.nomads.ethanolmixcalculator

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Console

// TODO should this be : ViewModel(), Observable by BaseObservable()?
class FirstFragmentViewModel(private val calculator: EthanolMixCalculator) : BaseObservable() {

    @get:Bindable
    var tankVolumeValue: String
        get() = calculator.tankVolume.value.toString()
        set(value) {
            val newVal = value.toDoubleOrNull()
            if (newVal == null || newVal == calculator.tankVolume.value)
                return

            calculator.tankVolume = Volume(newVal, calculator.tankVolume.uom)

            // TODO callback for enabling/disabling buttons and stuff here?

            notifyPropertyChanged(BR.tankVolumeValue)
        }
}