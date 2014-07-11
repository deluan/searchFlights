package com.deluan.searchflights

import static java.lang.System.exit

class Main {

    static void main(String[] args) {
        def options = parseCommandLine(args)

        def flightSearcher = initializeFlightSearcher()

        String origin = options.o
        String destination = options.d

        def flights = flightSearcher.search(origin, destination)

        flights.each {Flight flight ->
            println flight
        }
    }

    private static initializeFlightSearcher() {
        def providers = new ProviderLoader(".").load()

        if (providers.empty) {
            println "No valid provider data files found in the current directory."
            exit(-2)
        }

        new FlightSearcher(providers)
    }

    private static parseCommandLine(String[] args) {
        def cli = new CliBuilder(usage: "searchFlights args", header: "\nArgs:",
                footer: "\nProject home: http://github.com/deluan/searchFlights")
        cli.with {
            o required: true, args: 1, argName: "Origin", "(mandatory)"
            d required: true, args: 1, argName: "Destination", "(mandatory)"
        }
        def options = cli.parse(args)

        if (!options) {
            exit(-1)
        }
        options
    }
}
