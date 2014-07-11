package com.deluan.searchflights

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Calendar.*

class ParserSpec extends Specification {

    @Unroll
    def "Should split the line using the given separator"() {
        setup:
          def parser = new Parser(separator, '')

        expect:
          parser.splitLine(input).size() == count

        where:
          separator | input                                                   | count
          '|'       | 'JFK|6/15/2014 9:30:00|YEG|6/15/2014 17:50:00|$730.00'  | 5
          ','       | 'LHR,6-19-2014 6:30:00,BOS,6-19-2014 12:42:00,$1243.00' | 5
    }

    @Unroll
    def "Should parse dates using the given format"() {
        setup:
          def parser = new Parser('', format)

        when:
          def date = parser.parseDate(input).toCalendar()

        then:
          date.get(YEAR) == year
          date.get(MONTH) == month - 1
          date.get(DAY_OF_MONTH) == day
          date.get(HOUR_OF_DAY) == hour
          date.get(MINUTE) == minute
          date.get(SECOND) == second

        where:
          format               | input                | year | month | day | hour | minute | second
          'M/dd/yyyy HH:mm:ss' | '6/15/2014 9:30:00'  | 2014 | 6     | 15  | 9    | 30     | 0
          'M-dd-yyyy HH:mm:ss' | '1-19-2014 12:42:00' | 2014 | 1     | 19  | 12   | 42     | 00
    }

    def "Should parse currency values"() {
        expect:
          new Parser('', '').parsePrice('$123.33') == 123.33
    }
}
