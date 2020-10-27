package br.com.wb.salvaumavida.utils

import java.util.*

fun <T> Optional<T>.ifNotPresent(nextCall: Optional<T>): Optional<T> {
    return if (this.isPresent) this else nextCall
}