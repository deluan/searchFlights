package com.deluan.searchflights

import spock.lang.Specification

class FlightSearcherSpec extends Specification {
    def provider1
    def provider2
    FlightSearcher searcher

    def setup() {
        provider1 = Mock(Provider)
        provider2 = Mock(Provider)
        searcher = new FlightSearcher([provider1, provider2])
    }

    def "Should use search in all providers"() {
        when:
          searcher.search('YYZ', 'GIG')

        then:
          1 * provider1.search('YYZ', 'GIG')
          1 * provider2.search('YYZ', 'GIG')
    }

    def "Should return empty list if flight is not found"() {
        setup:
          provider1.search(_, _) >> []
          provider2.search(_, _) >> []

        expect:
          searcher.search('ANY', 'ANY') == []
    }

    def "Should return aggregated results"() {
        setup:
          provider1.search('YYZ', 'GIG') >> [aFlight('YYZ', 'GIG', 456.00)]
          provider2.search('YYZ', 'GIG') >> [aFlight('YYZ', 'GIG', 123.00)]

        when:
          def results = searcher.search('YYZ', 'GIG')

        then:
          results.size() == 2
          results[0] == aFlight('YYZ', 'GIG', 123.00)
          results[1] == aFlight('YYZ', 'GIG', 456.00)
    }

    def "Should remove duplicated results"() {
        setup:
          provider1.search('YYZ', 'GIG') >> [aFlight('YYZ', 'GIG', 456.00)]
          provider2.search('YYZ', 'GIG') >> [aFlight('YYZ', 'GIG', 456.00)]

        when:
          def results = searcher.search('YYZ', 'GIG')

        then:
          results.size() == 1
    }

    private static aFlight(String origin, String destination, BigDecimal price) {
        new Flight(origin: origin, destination: destination, price: price)
    }
}
