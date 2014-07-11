package com.deluan.searchflights

class Parser {

    private String separator
    private String dateFormat

    Parser(separator, dateFormat) {
        this.dateFormat = dateFormat
        this.separator = separator
    }

    String[] splitLine(String line) {
        line.split("\\$separator")
    }

    BigDecimal parsePrice(String priceString) {
        new BigDecimal(priceString - '$')
    }

    Date parseDate(String dateString) {
        Date.parse(dateFormat, dateString)
    }
}
