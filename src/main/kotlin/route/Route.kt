package route

class Route(
    val fromCity: City,
    val toCity: City,
    val distance: Int,
    vararg citiesBetween: City
) {
    private val _citiesInRoute: MutableList<City> = mutableListOf(*citiesBetween)
    val citiesInRoute: List<City>
        get() = _citiesInRoute
}

class AvailableRoutes private constructor() {

    companion object {

        private var instance: AvailableRoutes? = null

        fun getInstance(): AvailableRoutes {
            if (instance == null) {
                instance = AvailableRoutes()
            }
            return instance!!
        }
    }


    val LvivTernopilRoute = Route(City.Lviv, City.Ternopil, 100)
    val LvivKyivRoute = Route(City.Lviv, City.Kyiv, 600, City.Ternopil)
    val KharkivLviv = Route(City.Kharkiv, City.Lviv, 700, City.Kyiv, City.Ternopil)
}
