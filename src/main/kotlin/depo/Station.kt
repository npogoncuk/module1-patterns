package depo

import depo.time_strategy.TimeStrategy
import depo.time_strategy.timeStrategy
import route.Route
import transport.Transport
import transport.transport_registry.TransportRegistry
import java.time.LocalDateTime
import java.time.LocalTime

object Station {

    private val transportToDepartureTime = mutableMapOf<TransportPassengers, LocalDateTime>()

    init {
        with(TransportRegistry) {
            val bus = buses.first()
            val train = trains.first()
            val plane = planes.first()
            transportToDepartureTime[TransportPassengers(bus, TimeStrategy.BusTimeStrategy)] = LocalDateTime.of(2022, 11, 25, 1, 0)
            transportToDepartureTime[TransportPassengers(train, TimeStrategy.TrainTimeStrategy)] = LocalDateTime.of(2022, 11, 25, 5, 35)
            transportToDepartureTime[TransportPassengers(plane, TimeStrategy.PlaneTimeStrategy)] = LocalDateTime.of(2022, 11, 25, 17, 0)
        }
    }

    fun addTransport(transport: Transport) {
        transportToDepartureTime[TransportPassengers(transport, transport.timeStrategy)]
    }

    fun buyTicketFor(passenger: Passenger, route: Route): Ticket {
        println("$passenger is buying ticket")
        val transportPassengers = transportToDepartureTime.keys.find { it.transport.route == route } ?: kotlin.run { throw IllegalArgumentException("There is not available transport on this route")}
        transportPassengers.passengers += passenger
        return Ticket(transportPassengers.transport, transportToDepartureTime.get(transportPassengers) )
    }

    data class TransportPassengers(
        val transport: Transport,
        val timeToGetStrategy: TimeStrategy,
        val passengers: MutableList<Passenger> = mutableListOf()
    ) {
        val timeToGet: LocalTime?
            get() = timeToGetStrategy.calculateTimeToGet(transport.route)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TransportPassengers

            if (transport != other.transport) return false

            return true
        }

        override fun hashCode(): Int {
            return transport.hashCode()
        }


    }


}