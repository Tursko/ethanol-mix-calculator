package com.nomads.ethanolmixcalculator

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

    // TODO uom to string converter and value formatting
    override fun toString(): String {
        return "$value $uom"
    }

    enum class UoM {
        Liters,
        Gallons
    }
}