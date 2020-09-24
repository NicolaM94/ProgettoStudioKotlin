import Classes.*

fun main() {

    val b = BalanceCollector("/home/nicola/Desktop/copiatest.xlsx")
    println("""Totali:
        |Attivo: ${b.totalActive()}
        |Passivo: ${b.totalPassive()}
        |Costi: ${b.totalCosts()}
        |Ricavi: ${b.totalRevenues()}
    """.trimMargin())

}