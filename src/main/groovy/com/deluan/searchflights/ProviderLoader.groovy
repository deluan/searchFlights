package com.deluan.searchflights

class ProviderLoader {
    public static final String PROVIDERS_CONFIGURATION = '/providers.properties'
    private String dataFolder
    private Properties providersConfiguration

    ProviderLoader(String dataFolder) {
        this.dataFolder = dataFolder

        loadProvidersConfiguration()
    }

    List<Provider> load() {
        def dataFiles = collectDataFiles()

        dataFiles.collect { String dataFile ->
            constructProvideFor(dataFile)
        }.findAll { it != null }
    }

    private loadProvidersConfiguration() {
        def propertiesResource = this.class.getResourceAsStream(PROVIDERS_CONFIGURATION)
        providersConfiguration = new Properties()
        providersConfiguration.load(propertiesResource)
    }

    private constructProvideFor(String dataFile) {
        def providerKey = new File(dataFile).name - 'txt'
        def separator = providersConfiguration.getProperty(providerKey + 'separator')
        def dateFormat = providersConfiguration.getProperty(providerKey + 'dateFormat')
        if (separator && dateFormat) {
            new Provider(dataFile, new Parser(separator, dateFormat))
        }
    }

    private collectDataFiles() {
        def files = new File(dataFolder).listFiles({ d, f -> f.matches("Provider.*txt") } as FilenameFilter)
        files.collect { it.absolutePath }
    }
}
