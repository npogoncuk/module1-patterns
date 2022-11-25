package depo.time_strategy

import route.Route
import transport.Bus
import transport.Plane
import transport.Train
import transport.Transport
import java.time.LocalTime
import kotlin.random.Random

sealed interface TimeStrategy {

    fun calculateTimeToGet(route: Route): LocalTime?

    object TrainTimeStrategy : TimeStrategy {
        override fun calculateTimeToGet(route: Route): LocalTime {
            println("Calculating time for train to get")
            return LocalTime.of(Random.nextInt(10), Random.nextInt(60))
        }
    }

    object PlaneTimeStrategy : TimeStrategy {
        override fun calculateTimeToGet(route: Route): LocalTime {
            println("Calculating time for plane to get")
            return LocalTime.of(Random.nextInt(3), Random.nextInt(60))
        }
    }

    object BusTimeStrategy : TimeStrategy {
        override fun calculateTimeToGet(route: Route): LocalTime {
            println("Calculating time for bus to get")
            return LocalTime.of(Random.nextInt(5), Random.nextInt(60))
        }
    }

    object TransportAirAlert : TimeStrategy {
        override fun calculateTimeToGet(route: Route): LocalTime? {
            println("Calculating time for bus to get")
            return null
        }
    }
}

val Transport.timeStrategy
    get() = when(this) {
            is Bus -> TimeStrategy.BusTimeStrategy
            is Train -> TimeStrategy.TrainTimeStrategy
            is Plane -> TimeStrategy.PlaneTimeStrategy
            else -> throw UnsupportedOperationException()
        }
