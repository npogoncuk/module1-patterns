package transport.transport_registry

import route.AvailableRoutes
import transport.Bus
import transport.Plane
import transport.Train
import transport.Transport


private const val BUS_TAG = "bus_tag"
private const val TRAIN_TAG = "train_tag"
private const val PLANE_TAG = "plane_tag"

private val availableRoutes = AvailableRoutes.getInstance()

private val BUS_PROTOTYPE = Bus(30, 42, 10, AvailableRoutes.getInstance().LvivTernopilRoute)
private val TRAIN_PROTOTYPE = Train(100, 20, AvailableRoutes.getInstance().KharkivLviv)
private val PLANE_PROTOTYPE = Plane(200, 30, AvailableRoutes.getInstance().LvivKyivRoute)

object TransportRegistry {
    private val prototypes = mutableMapOf<String, MutableList<Transport>>(
        BUS_TAG to mutableListOf(BUS_PROTOTYPE),
        TRAIN_TAG to mutableListOf(TRAIN_PROTOTYPE),
        PLANE_TAG to mutableListOf(PLANE_PROTOTYPE)
    )
    private val busPrototypes
        get() = prototypes[BUS_TAG]!!
    private val trainPrototypes
        get() = prototypes[TRAIN_TAG]!!
    private val planePrototypes
        get() = prototypes[PLANE_TAG]!!
    private val allPrototypes
        get() = busPrototypes + trainPrototypes + planePrototypes

    fun addBusPrototype(bus: Bus) {
        busPrototypes += bus
    }

    fun addPlanePrototype(plane: Plane) {
        planePrototypes += plane
    }

    fun addTrainPrototype(train: Train) {
        trainPrototypes += train
    }

    fun getAllTransport(): List<Transport> = (busPrototypes + planePrototypes + trainPrototypes).map { it.copy() }

    fun getTransportByFilter(filter: (Transport) -> Boolean): List<Transport> {
        return allPrototypes.filter { filter(it) }.map { it.copy() }
    }

    val buses: List<Transport>
        get() = busPrototypes

    val trains: List<Transport>
        get() = trainPrototypes

    val planes: List<Transport>
        get() = planePrototypes
}