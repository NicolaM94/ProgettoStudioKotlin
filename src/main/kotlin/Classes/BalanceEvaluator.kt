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

    //Indici patrimoniali
    fun indipendenceRate () :Double {
        return 100 * balance.netWealth()/balance.totalActive()
    }

    fun primaryStrucMargin () :Double {
       return balance.netWealth()*100/(balance.totalFixedAssets() - balance.totalMortFunds())
    }

    fun secondaryStrucMargin () :Double {
        return balance.totalFixedAssets() * 100/balance.consolidatedSources()

    }


}