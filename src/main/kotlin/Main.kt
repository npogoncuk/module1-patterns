import depo.Passenger
import depo.Station
import route.AvailableRoutes
import route.City

fun main() {
    val nazar = Passenger("Nazar")

    nazar.ticket = Station.buyTicketFor(passenger = nazar, AvailableRoutes.getInstance().LvivTernopilRoute)

    println(nazar)
}