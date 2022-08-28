package com.nomads.ethanolmixcalculator

import android.view.View
import android.widget.Spinner
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

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
            clearOutputValues()
            notifyPropertyChanged(BR.tankVolumeValue)
        }

    // binding for UoM current dropdown value
    @get:Bindable
    var tankVolumeUomPosition: Int
        get() = calculator.tankVolume.uom.ordinal
        set(value) {
            val enumValues = Volume.UoM.values()
            if (value >= enumValues.count())
                return

            val newVal = enumValues[value]
            if (newVal == calculator.tankVolume.uom)
                return;

            calculator.tankVolume = Volume(calculator.tankVolume.value, newVal)
            clearOutputValues()
            notifyPropertyChanged(BR.tankVolumeUomPosition)
        }

    // binding for UoM dropdown options
    @Bindable
    fun getVolumeUomOptions() : Array<String> = Volume.UoM.values().map { it.name }.toTypedArray()

    // hack for UoM changed to clear output values. The 'correct' implementation seems to be with @BindingAdapter annotation...
    fun onTankSizeUomChanged() {
        clearOutputValues()
    }

    @get:Bindable
    var currentFuelPercentage: String
        get() = (calculator.currentFuelPercentage * 100.0).roundToInt().toString()
        set(value) {
            val tmp = value.toIntOrNull() ?: return
            val newVal = tmp / 100.0
            if (newVal == calculator.currentFuelPercentage)
                return

            calculator.currentFuelPercentage = newVal
            clearOutputValues()
            notifyPropertyChanged(BR.currentFuelPercentage)
        }

    @get:Bindable
    var currentEthanolPercentage: String
        get() = (calculator.currentEthanolPercentage * 100.0).roundToInt().toString()
        set(value) {
            val tmp = value.toIntOrNull() ?: return
            val newVal = tmp / 100.0
            if (newVal == calculator.currentEthanolPercentage)
                return

            calculator.currentEthanolPercentage = newVal
            clearOutputValues()
            notifyPropertyChanged(BR.currentEthanolPercentage)
        }

    @get:Bindable
    var gasEthanolPercentage: String
        get() = (calculator.gasEthanolPercentage * 100.0).roundToInt().toString()
        set(value) {
            val tmp = value.toIntOrNull() ?: return
            val newVal = tmp / 100.0
            if (newVal == calculator.gasEthanolPercentage)
                return

            calculator.gasEthanolPercentage = newVal
            clearOutputValues()
            notifyPropertyChanged(BR.gasEthanolPercentage)
        }

    @get:Bindable
    var e85EthanolPercentage: String
        get() = (calculator.e85EthanolPercentage * 100.0).roundToInt().toString()
        set(value) {
            val tmp = value.toIntOrNull() ?: return
            val newVal = tmp / 100.0
            if (newVal == calculator.e85EthanolPercentage)
                return

            calculator.e85EthanolPercentage = newVal
            clearOutputValues()
            notifyPropertyChanged(BR.e85EthanolPercentage)
        }

    @get:Bindable
    var targetEthanolPercentage: String
        get() = (calculator.targetEthanolPercentage * 100.0).roundToInt().toString()
        set(value) {
            val tmp = value.toIntOrNull() ?: return
            val newVal = tmp / 100.0
            if (newVal == calculator.targetEthanolPercentage)
                return

            calculator.targetEthanolPercentage = newVal
            clearOutputValues()
            notifyPropertyChanged(BR.targetEthanolPercentage)
        }

    @get:Bindable
    var addGasMessage: String = ""
        set(value) {
            if (value == field)
                return

            field = value
            notifyPropertyChanged(BR.addGasMessage)
        }

    @get:Bindable
    var addE85Message: String = ""
        set(value) {
            if (value == field)
                return

            field = value
            notifyPropertyChanged(BR.addE85Message)
        }

    fun onCalculateButtonClicked(view: View) {
        val result = calculator.calculateMix()
        setOutputValues(result)

        Snackbar.make(view, "Calculation complete :)", Snackbar.LENGTH_LONG)
                .show()
    }

    private fun setOutputValues(resultToShow: EthanolMixCalculator.Result) {
        addGasMessage = "You should add ${resultToShow.pumpGasToAdd.toString(2)} of gas"
        addE85Message = "You should add ${resultToShow.e85ToAdd.toString(2)} of E85"
    }

    private fun clearOutputValues() {
        addGasMessage = ""
        addE85Message = ""
    }
}