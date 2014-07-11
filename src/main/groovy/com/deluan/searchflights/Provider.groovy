package com.deluan.searchflights

class Provider {
    private String dataFile
    private Parser parser

    Provider(String dataFile, Parser parser) {
        this.dataFile = dataFile
        this.parser = parser
    }

    List<Flight> search(String origin, String destination) {
        def result = []
        new File(dataFile).eachLine { line ->
            def data = parser.splitLine(line)

            if (matchesCriteria(data, origin, destination)) {
                result << createFlight(data)
            }
        }
        return result
    }

    private createFlight(String[] data) {
        new Flight(
                origin: data[0],
                destination: data[2],
                departureTime: parser.parseDate(data[1]),
                destinationTime: parser.parseDate(data[3]),
                price: parser.parsePrice(data[4])
        )
    }

    private matchesCriteria(String[] data, String origin, String destination) {
        origin == data[0] && destination == data[2]
    }
}
