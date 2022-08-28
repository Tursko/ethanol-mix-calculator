package com.nomads.ethanolmixcalculator

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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
            calculator.tankVolume = Volume(calculator.tankVolume.value, newVal)
            clearOutputValues()
            notifyPropertyChanged(BR.tankVolumeUomPosition)
        }

    // binding for UoM dropdown options
    @Bindable
    fun getVolumeOptions() : Array<String> = Volume.UoM.values().map { it.name }.toTypedArray()

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

    fun clearOutputValues() {
        addGasMessage = ""
        addE85Message = ""
    }

    fun setOutputValues(resultToShow: EthanolMixCalculator.Result) {
        // TODO format floats tto show less decimal places
        addGasMessage = "You should add ${resultToShow.pumpGasToAdd.toString()} of gas"
        addE85Message = "You should add ${resultToShow.e85ToAdd.toString()} of E85"
    }
}