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
            .01,
        )
        Assert.assertEquals(
            "Gas",
            2.89,
            result.pumpGasToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }

    @Test
    fun calc_mixture1() {
        var tankVolume: Volume = Volume(12.4, Volume.UoM.Gallons)
        var gasEthanolPercentage: Double = 0.0
        var e85EthanolPercentage: Double = .85
        var targetEthanolPercentage: Double = .30
        var currentFuelPercentage: Double = 0.0
        var currentEthanolPercentage: Double = 0.0

        var calculator = EthanolMixCalculator(tankVolume, gasEthanolPercentage, e85EthanolPercentage, targetEthanolPercentage, currentFuelPercentage, currentEthanolPercentage)
        var result = calculator.CalculateMix();

        Assert.assertEquals(
            "e85",
            4.38,
            result.e85ToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Gas",
            8.02,
            result.pumpGasToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }

    @Test
    fun calc_mixture2() {
        var tankVolume: Volume = Volume(15.5, Volume.UoM.Gallons)
        var gasEthanolPercentage: Double = .10
        var e85EthanolPercentage: Double = .70
        var targetEthanolPercentage: Double = .30
        var currentFuelPercentage: Double = .23
        var currentEthanolPercentage: Double = .30

        var calculator = EthanolMixCalculator(tankVolume, gasEthanolPercentage, e85EthanolPercentage, targetEthanolPercentage, currentFuelPercentage, currentEthanolPercentage)
        var result = calculator.CalculateMix();

        Assert.assertEquals(
            "e85",
            3.98,
            result.e85ToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Gas",
            7.96,
            result.pumpGasToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }

    @Test
    fun calc_mixture_liters() {
        var tankVolume: Volume = Volume(58.67, Volume.UoM.Liters)
        var gasEthanolPercentage: Double = .10
        var e85EthanolPercentage: Double = .70
        var targetEthanolPercentage: Double = .30
        var currentFuelPercentage: Double = .23
        var currentEthanolPercentage: Double = .30

        var calculator = EthanolMixCalculator(tankVolume, gasEthanolPercentage, e85EthanolPercentage, targetEthanolPercentage, currentFuelPercentage, currentEthanolPercentage)
        var result = calculator.CalculateMix();

        Assert.assertEquals(
            "e85",
            15.06,
            result.e85ToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Gas",
            30.12,
            result.pumpGasToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }

    @Test
    fun calc_mixture_liters1() {
        var tankVolume: Volume = Volume(46.94, Volume.UoM.Liters)
        var gasEthanolPercentage: Double = .10
        var e85EthanolPercentage: Double = .85
        var targetEthanolPercentage: Double = .30
        var currentFuelPercentage: Double = .50
        var currentEthanolPercentage: Double = .10

        var calculator = EthanolMixCalculator(tankVolume, gasEthanolPercentage, e85EthanolPercentage, targetEthanolPercentage, currentFuelPercentage, currentEthanolPercentage)
        var result = calculator.CalculateMix();

        Assert.assertEquals(
            "e85",
            12.52,
            result.e85ToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Gas",
            10.95,
            result.pumpGasToAdd.value,
            .01,
        )
        Assert.assertEquals(
            "Mix",
            .3,
            result.finalEthanolPercentage,
            .0,
        )
    }
}