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

            if (origin == data[0] && destination == data[2]) {
                result << new Flight(
                        origin: origin,
                        destination: destination,
                        departureTime: parser.parseDate(data[1]),
                        destinationTime: parser.parseDate(data[3]),
                        price: parser.parsePrice(data[4])
                )
            }
        }
        return result
    }
}
