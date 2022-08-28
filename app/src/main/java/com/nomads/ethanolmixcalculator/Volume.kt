package com.nomads.ethanolmixcalculator

import java.text.DecimalFormat

data class Volume(val value: Double, val uom: UoM) {

    val asGallons: Volume
        get() = convert(this, UoM.Gallons)

    val asLiters: Volume
        get() = convert(this, UoM.Liters)


    operator fun plus(other: Volume) : Volume {
        return Volume(this.value + convert(other, this.uom).value, this.uom)
    }

    operator fun minus(other: Volume) : Volume {
        return Volume(this.value - convert(other, this.uom).value, this.uom)
    }

    operator fun times(scalar: Int) : Volume {
        return Volume(this.value * scalar, this.uom)
    }

    operator fun times(scalar: Float) : Volume {
        return Volume(this.value * scalar, this.uom)
    }

    operator fun times(scalar: Double) : Volume {
        return Volume(this.value * scalar, this.uom)
    }

    operator fun div(scalar: Int) : Volume {
        return Volume(this.value / scalar, this.uom)
    }

    operator fun div(scalar: Float) : Volume {
        return Volume(this.value / scalar, this.uom)
    }

    operator fun div(scalar: Double) : Volume {
        return Volume(this.value / scalar, this.uom)
    }

    companion object Converter {
        const val litersPerGallon: Double = 3.785411784

        fun convert(volume: Volume, newUom: UoM) : Volume {
            return when (Pair(volume.uom, newUom)) {
                Pair(UoM.Gallons, UoM.Liters) -> Volume(volume.value * litersPerGallon, UoM.Liters)
                Pair(UoM.Liters, UoM.Gallons) -> Volume(volume.value / litersPerGallon, UoM.Gallons)
                Pair(UoM.Gallons, UoM.Gallons) -> Volume(volume.value, UoM.Gallons)
                Pair(UoM.Liters, UoM.Liters) -> Volume(volume.value, UoM.Liters)
                else -> throw NotImplementedError()
            }
        }
    }

    override fun toString() = "$value $uom"

    fun toString(decimalPlaces: Int): String {
        var pattern = "."
        for(i in 1..decimalPlaces)
            pattern += "0"

        val dec = DecimalFormat(pattern)
        return "${dec.format(value)} $uom"
    }

    enum class UoM {
        Liters,
        Gallons
    }
}