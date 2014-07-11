package com.deluan.searchflights

import spock.lang.Specification

class FlightSpec extends Specification {

    def "Should price as first sort order"() {
        given:
          def flight1 = new Flight(price: 321.00)
          def flight2 = new Flight(price: 123.00)

        expect:
          flight1 > flight2
    }

    def "Should use departure time as secondary sort order"() {
        given:
          def today = new Date()
          def tomorrow = today + 1
          def flight1 = new Flight(price: 123.00, departureTime: today)
          def flight2 = new Flight(price: 123.00, departureTime: tomorrow)

        expect:
          flight1 < flight2
    }

    def "Should be printable in a readable format"() {
        given:
          def flight = new Flight(
                  origin: 'YYC',
                  departureTime: parseDate('30/06/2014 9:30:00'),
                  destination: 'YYZ',
                  destinationTime: parseDate('30/06/2014 17:05:00'),
                  price: 535.00)

        expect:
          flight.toString() == 'YYC --> YYZ (6/30/2014 9:30:00 --> 6/30/2014 17:05:00) - $535.00'
    }

    private static parseDate(String dateString) {
        Date.parse('dd/MM/yyyy HH:mm:ss', dateString)
    }

}
