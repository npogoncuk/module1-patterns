package transport

import route.Route

interface Transport {
    val id: Int
    val nameOfTransport: String
    val numberOfPassengers: Int
    val cargoPerPerson: Int
    val route: Route

    fun copy(): Transport

    fun asString(): String {
        return "$nameOfTransport:$id"
    }
}

class Train(
    override val numberOfPassengers: Int,
    override val cargoPerPerson: Int,
    override val route: Route
) : Transport {

    override val nameOfTransport: String
        get() = "Train"

    override val id: Int
        get() = this.hashCode()

    constructor(train: Train): this(train.numberOfPassengers, train.cargoPerPerson, train.route)

    override fun copy(): Train = Train(this).also { println("Copied train") }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Train

        if (numberOfPassengers != other.numberOfPassengers) return false
        if (cargoPerPerson != other.cargoPerPerson) return false
        if (route != other.route) return false
        if (nameOfTransport != other.nameOfTransport) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numberOfPassengers
        result = 31 * result + cargoPerPerson
        result = 31 * result + route.hashCode()
        result = 31 * result + nameOfTransport.hashCode()
        return result
    }

    override fun toString(): String = asString()
}

class Plane(
    override val numberOfPassengers: Int,
    override val cargoPerPerson: Int,
    override val route: Route
) : Transport {

    override val nameOfTransport: String
        get() = "Plane"

    override val id: Int
        get() = this.hashCode()

    constructor(plane: Plane): this(plane.numberOfPassengers, plane.cargoPerPerson, plane.route)

    override fun copy(): Plane = Plane(this).also { println("Copied plane") }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Plane

        if (numberOfPassengers != other.numberOfPassengers) return false
        if (cargoPerPerson != other.cargoPerPerson) return false
        if (route != other.route) return false
        if (nameOfTransport != other.nameOfTransport) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numberOfPassengers
        result = 31 * result + cargoPerPerson
        result = 31 * result + route.hashCode()
        result = 31 * result + nameOfTransport.hashCode()
        return result
    }

    override fun toString(): String = asString()
}

class Bus(
    val sits: Int,
    standingPassengers: Int,
    override val cargoPerPerson: Int,
    override val route: Route
) : Transport {

    override val numberOfPassengers: Int = sits + standingPassengers

    override val nameOfTransport: String
        get() = "Bus"

    override val id: Int
        get() = this.hashCode()

    constructor(bus: Bus): this(bus.sits, bus.numberOfPassengers - bus.sits, bus.cargoPerPerson, bus.route)

    override fun copy(): Bus = Bus(this).also { println("Copied bus") }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bus

        if (sits != other.sits) return false
        if (cargoPerPerson != other.cargoPerPerson) return false
        if (route != other.route) return false
        if (numberOfPassengers != other.numberOfPassengers) return false
        if (nameOfTransport != other.nameOfTransport) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sits
        result = 31 * result + cargoPerPerson
        result = 31 * result + route.hashCode()
        result = 31 * result + numberOfPassengers
        result = 31 * result + nameOfTransport.hashCode()
        return result
    }

    override fun toString(): String = asString()
}