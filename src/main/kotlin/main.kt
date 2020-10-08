import Classes.*
import tornadofx.*
import kotlin.math.round

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
        |Valore della produzione: ${b.productionValue()}
        |Revenues: ${b.operativeEarnings()}
        |Patrimonio netto : ${b.netWealth()}
        |Result: ${b.result()}
        """.trimMargin())

    val c = BalanceEvaluator(b,15420.00)
    println("Roe: ${ c.returnOnEquity() }")
    println("ROI: ${ c.returnOnInvestment() }")
    println("Primary Structure Margin: ${ c.primaryStrucMargin() }")
}