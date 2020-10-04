package Classes

//Extending sum for lists
fun List<Double>.sum () :Double {
    var result = 0.00
    this.forEach { el ->
        result += el
    }
    return result
}