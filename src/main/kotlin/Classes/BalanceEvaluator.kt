package Classes

class BalanceEvaluator (val balance:BalanceCollector,val fatturato :Double) {

    fun returnOnEquity () :Double {
        return (balance.result()/balance.netWealth()) * 100
    }
    fun returnOnInvestment () :Double {
        return (balance.operativeEarnings()/(balance.netWealth() + balance.longDebts())) * 100
    }
    fun returnOnSales () :Double {
        return (balance.operativeEarnings()/fatturato) * 100
    }





}