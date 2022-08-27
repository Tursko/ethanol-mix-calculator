package com.nomads.ethanolmixcalculator

class EthanolMixCalculator {
    class Result(val e85ToAdd: Volume, val pumpGasToAdd: Volume, val finalEthanolPercentage: Double) {}

    var tankVolume: Volume = Volume(0.0, Volume.UoM.Gallons)
    var gasEthanolPercentage: Double = 0.0
    var e85EthanolPercentage: Double = 0.0
    var targetEthanolPercentage: Double = 0.0
    var currentFuelPercentage: Double = 0.0
    var currentEthanolPercentage: Double = 0.0


    val currentFuel: Volume
        get() = tankVolume * currentFuelPercentage

    val currentEthanol: Volume
        get() = currentFuel * currentEthanolPercentage

    val targetEthanol: Volume
        get() = tankVolume * targetEthanolPercentage


    fun CalculateMix() : Result {
        val e85ToAdd = (currentEthanol + (tankVolume - currentFuel) * gasEthanolPercentage - targetEthanol) / (gasEthanolPercentage - e85EthanolPercentage)
        val pumpGasToAdd = tankVolume - currentFuel - e85ToAdd
        return Result(e85ToAdd, pumpGasToAdd, targetEthanolPercentage)
    }
}