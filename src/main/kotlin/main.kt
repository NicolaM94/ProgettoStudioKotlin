import Classes.*

fun main() {

    val b = BalanceCollector("/home/nicola/Desktop/copiatest.xlsx")
    println("""Totali:
        |Attivo: ${b.totalActive()}
        |Passivo: ${b.totalPassive()}
        |Costi: ${b.totalCosts()}
        |Ricavi: ${b.totalRevenues()}
        |Crediti v. soci: ${b.creditsVsAssociates()}
        |Costi di impianto: ${b.plantCosts()} 
        |Patrimonio netto: ${b.netWealth()}
        |Debiti a lungo termine: ${b.longDebts()}
        |DEbiti a corto termine: ${b.shortDebts()}
        |Risconti e ratei attivi: ${b.activeAccrualsAndDeferrals()}
        |Risconti e ratei passivi: ${b.passiveAccrualsAndDeferrals()}
        |aaaa: ${b.associatesDebts()}
        """.trimMargin())



}