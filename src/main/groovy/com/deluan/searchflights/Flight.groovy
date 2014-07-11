package com.deluan.searchflights

import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable

@Immutable
@EqualsAndHashCode
class Flight implements Comparable<Flight>{
    String origin
    String destination
    Date departureTime
    Date destinationTime
    BigDecimal price


    @Override
    int compareTo(Flight that) {
        def comparison = this.price <=> that.price
        comparison ?: this.departureTime <=> that.departureTime
    }

    @Override
    public String toString() {
        "$origin --> $destination (${formatDate(departureTime)} --> ${formatDate(destinationTime)}) - ${formatedPrice}"
    }

    private formatDate(Date date) {
        date?.format('M/dd/yyyy H:mm:ss')
    }

    private getFormatedPrice() {
        '$' + price
    }
}

