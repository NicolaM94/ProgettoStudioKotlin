package Classes

/**
 * Extension of List objects with sum function
 * */
fun List<Double>.sum () :Double {
    var result = 0.00
    this.forEach { el ->
        result += el
    }
    return result
}

fun Array<Double>.sum () :Double {
    var result = 0.00
    this.forEach { e ->
        result += e
    }
    return result
}