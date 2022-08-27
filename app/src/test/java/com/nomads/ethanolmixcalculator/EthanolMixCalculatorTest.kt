package com.nomads.ethanolmixcalculator

import org.junit.Assert
import org.junit.Test

class EthanolMixCalculatorTest {

    @Test
    fun calc_mixture() {
        var tankVolume: Volume = Volume(12.4, Volume.UoM.Gallons)
        var gasEthanolPercentage: Double = .10
        var e85EthanolPercentage: Double = .85
        var targetEthanolPercentage: Double = .30
        var currentFuelPercentage: Double = .50
        var currentEthanolPercentage: Double = .10

        var calculator = EthanolMixCalculator(tankVolume, gasEthanolPercentage, e85EthanolPercentage, targetEthanolPercentage, currentFuelPercentage, currentEthanolPercentage)
        var result = calculator.CalculateMix();

        Assert.assertEquals(
            "e85",
            3.31,
            result.e85ToAdd.value,
            .1,
        )
        Assert.assertEquals(
            "Gas",
            2.89,
            result.pumpGasToAdd.value,
            .1,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }
}