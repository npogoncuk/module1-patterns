package depo

import transport.Transport
import java.time.LocalDateTime

data class Ticket(
    val transport: Transport,
    val date: LocalDateTime?
) {
    val ticketId = idCounter++
    companion object {
        private var idCounter = 0
    }
}
