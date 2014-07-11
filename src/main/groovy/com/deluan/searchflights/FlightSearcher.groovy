package com.deluan.searchflights

import groovyx.gpars.GParsPool

class FlightSearcher {

    private List<Provider> providers

    FlightSearcher(List<Provider> providers) {
        this.providers = providers
    }

    List<Flight> search(String origin, String destination) {
        if (providers.empty) {
            return []
        }

        GParsPool.withPool(providers.size()) {

            providers.collectParallel { provider ->
                provider.search(origin, destination)
            }.flatten().unique().sort()

        }
    }

}
