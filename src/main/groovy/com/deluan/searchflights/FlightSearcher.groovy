package com.deluan.searchflights

class FlightSearcher {

    private List<Provider> providers

    FlightSearcher(List<Provider> providers) {
        this.providers = providers
    }

    List<Flight> search(String origin, String destination) {
        providers.collect { provider ->
            provider.search(origin, destination)
        }.flatten().unique().sort()
    }

}
