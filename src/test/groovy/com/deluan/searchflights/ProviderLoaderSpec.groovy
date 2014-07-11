package com.deluan.searchflights

import spock.lang.Specification
import spock.lang.Unroll

class ProviderLoaderSpec extends Specification {

    def "Should return an empty list of providers if no data file is present"() {
        setup:
          def dataFolder = this.class.getResource('/com/deluan').path
          def loader = new ProviderLoader(dataFolder)

        expect:
          loader.load().size() == 0
    }

    def "Should construct one provider for each data file found"() {
        setup:
          def dataFolder = this.class.getResource('/').path
          def loader = new ProviderLoader(dataFolder)

        expect:
          loader.load().size() == 3
    }

    def "Should ignore unknown provider data files"() {
        setup:
          def dataFolder = this.class.getResource('/unknown').path
          def loader = new ProviderLoader(dataFolder)

        expect:
          loader.load().size() == 1
    }

    @Unroll
    def "Should set the correct parser for each provider"() {
        setup:
          def dataFolder = this.class.getResource('/').path
          def loader = new ProviderLoader(dataFolder)

        when:
          def providers = loader.load()

        then:
          providers[provider].search(origin, destination).size() == 1

        where:
          provider | origin | destination
          0        | 'LHR'  | 'BOS'
          1        | 'MSY'  | 'YUL'
          2        | 'JFK'  | 'YEG'
    }

}
