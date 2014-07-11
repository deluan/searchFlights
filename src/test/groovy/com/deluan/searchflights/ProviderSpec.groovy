package com.deluan.searchflights

import spock.lang.Specification

class ProviderSpec extends Specification {
    Provider provider

    def setup() {
        def file = this.class.getResource('/Provider1.txt').file
        def parser = new Parser(',', 'M/dd/yyyy HH:mm:ss')
        provider = new Provider(file, parser)
    }

    def "Should return an empty list when a flight is not available"() {
        expect:
          provider.search('GIG', 'YYZ') == []
    }

    def "Should find a valid matching flight"() {
        when: 'we search for an unique existing flight'
          def flights = provider.search('LAS', 'LAX')

        then: 'one flight is returned'
          flights.size() == 1
    }

    def "Should return a complete flight record"() {
        when: 'we search for an unique existing flight'
          def flight = provider.search('LHR', 'BOS').first()

        then: 'the correct flight is returned'
          flight.origin == 'LHR'
          flight.destination == 'BOS'
          formatedDate(flight.departureTime) == '21/06/2014 11:05:00'
          formatedDate(flight.destinationTime) == '21/06/2014 17:06:00'
          flight.price == 975
    }

    private static formatedDate(Date date) {
        date.format('dd/MM/yyyy HH:mm:ss')
    }
}
