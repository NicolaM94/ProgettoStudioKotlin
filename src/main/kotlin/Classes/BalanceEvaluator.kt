package Classes

class BalanceEvaluator (val balance:BalanceCollector,val fatturato :Double) {

    fun returnOnEquity () :Double {
        return (balance.result()/balance.netWealth()) * 100
    }
    fun returnOnInvestment () :Double {
        return (balance.operativeEarnings()/(balance.netWealth() + balance.longDebts())) * 100
    }
    /*fun returnOnSales () :Double {
        return (balance.operativeEarnings()/fatturato) * 100
    }*/

    //Indici patrimoniali
    fun indipendenceRate () :Double {
        return 100 * balance.netWealth()/balance.totalActive()
    }

    fun primaryStrucMargin () :Double {
        val listOfActives = listOf<Double>(
            balance.plantCosts(), balance.rAndDCosts(),balance.patents(),balance.licences(),balance.goodWill(),
            balance.wipIntagibleAssets(),balance.fieldsAndEstate(),balance.machineriesAndImplants(),balance.equipments(),
            balance.otherGoods(),balance.wipIntagibleAssets(),balance.partecipationsAssets(),balance.creditsAssets(),
            balance.bonds(),balance.propertyStocksAndOthers(),balance.wipTangibleAssets(),balance.otherFixedAssets()
        )
        val listOfPassives = listOf<Double>(
            balance.faPatents(),balance.faPlantCosts(),balance.faReDCosts(),balance.faLicenes(),balance.faGoodWill(),
            balance.faOtherFixedAssets(),balance.faFieldsAndEstate(),balance.faMachineriesAndImplants(),
            balance.faEquipments(),balance.faOtherGoods()
        )
        return balance.netWealth()*100/(listOfActives.sum() - listOfPassives.sum())
    }




}