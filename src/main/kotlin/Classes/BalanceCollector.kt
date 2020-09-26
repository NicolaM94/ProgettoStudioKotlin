package Classes

import kotlin.math.floor
import java.io.File
import org.apache.poi.xssf.usermodel.XSSFWorkbook


class BalanceCollector (pathToBalance:String) {

    private val path = File(pathToBalance)
    private val reader = XSSFWorkbook(path)

    val activeMap: MutableMap<Double, Active> = mutableMapOf()
    val passiveMap: MutableMap<Double,Passive> = mutableMapOf()
    val costsMap: MutableMap<Double,Cost> = mutableMapOf()
    val revenuesMap: MutableMap<Double,Revenue> = mutableMapOf()

    /**Collecting values from the balance*/
    init {
        var sheet = reader.getSheet("Stato Patrimoniale")
        //Dumping assets and debts
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                activeMap.set(
                    testCell.toDouble(), Active(
                        testCell.toDouble(),
                        row.getCell(1).toString(),
                        "asset",
                        row.getCell(2).toString().toDouble()
                    )
                )
            } else if (testCell.startsWith("2")) {
                passiveMap.set(
                    testCell.toDouble(), Passive(
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "debt",
                        row.getCell(5).toString().toDouble() * -1
                    )
                )
            }
        }

        for (row in sheet) {
            var testCell = row.getCell(3)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                activeMap.set(
                    testCell.toDouble(), Active(
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "debt",
                        row.getCell(5).toString().toDouble() * -1
                    )
                )
            } else if (testCell.startsWith("2")) {
                passiveMap.set(
                    testCell.toDouble(), Passive(
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "debt",
                        row.getCell(5).toString().toDouble()
                    )
                )
            }
        }

        //Dumping costs and revenues

        sheet = reader.getSheet("CONTO ECONOMICO")
        //Dumping costs and revenues
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?:"null"
            if (testCell.startsWith("3")) {
                costsMap.set(
                    testCell.toDouble(), Cost (
                        testCell.toDouble(),
                        row.getCell(1).toString(),
                        "cost",
                        row.getCell(2).toString().toDouble()
                    )
                )
            } else if (testCell.startsWith("4")) {
                revenuesMap.set(
                    testCell.toDouble(), Revenue (
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "revenue",
                        row.getCell(5).toString().toDouble() * -1
                    )
                )
            }
        }

        for (row in sheet) {
            var testCell = row.getCell(3)?.toString() ?:"null"
            if (testCell.startsWith("4")) {
                revenuesMap.set(
                    testCell.toDouble(), Revenue (
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "revenue",
                        row.getCell(5).toString().toDouble() * -1
                    )
                )
            } else if (testCell.startsWith("3")) {
                costsMap.set(
                    testCell.toDouble(), Cost (
                        testCell.toDouble(),
                        row.getCell(1).toString(),
                        "cost",
                        row.getCell(2).toString().toDouble()
                    )
                )
            }
        }

        activeMap.forEach { el ->
            println("${el.key} : ${el.value.id},${el.value.name},${el.value.type}, ${el.value.value}")
        }
        passiveMap.forEach { el ->
            println("${el.key} : ${el.value.id},${el.value.name},${el.value.type}, ${el.value.value}")
        }
        costsMap.forEach { el ->
            println("${el.key} : ${el.value.id},${el.value.name},${el.value.type}, ${el.value.value}")
        }
        revenuesMap.forEach { el ->
            println("${el.key} : ${el.value.id},${el.value.name},${el.value.type}, ${el.value.value}")
        }
    }

    /**Totals methods return the total of the category */
    fun totalActive () :Double {
        var result:Double = 0.00
        activeMap.forEach { key, value ->
            result += value.value
        }
        return result
    }
    fun totalPassive () :Double {
        var result:Double = 0.00
        passiveMap.forEach { key, value ->
            result += value.value
        }
        return result
    }
    fun totalCosts () :Double {
        var result:Double = 0.00
        costsMap.forEach { s, cost ->
            result += cost.value
        }
        return result
    }
    fun totalRevenues () :Double {
        var result:Double = 0.00
        revenuesMap.forEach {s,revenue ->
            result += revenue.value
        }
        return result
    }

    /**Active MicroAreas*/
    fun creditsVsAssociates () :Double {
        var result: Double = 0.00
        activeMap.forEach {el ->
            if (floor((el.key)).toInt() in 10011..10040 ) {
                result += el.value.value
            }
        }
        return result
    }
    fun plantCosts () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 11010..11040) {
                result += el.value.value
            }
        }
        return result
    }
    fun rAndDCosts () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 11110..11130) {
                result += el.value.value
            }
        }
        return result
    }
    fun patents () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 11210..11242) {
                result += el.value.value
            }
        }
        return result
    }
    fun licences () :Double {
        var result = 0.00
        activeMap.forEach{el ->
            if (floor(el.key).toInt() in 11310..11350) {
                result += el.value.value
            }
        }
        return result
    }
    fun goodWill () :Double {
        return activeMap[11410.1]?.value ?: 0.00
    }
    fun wipIntagibleAssets () :Double {
        var result: Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 11510..11520) {
                result += el.value.value
            }
        }
        return result
    }
    fun otherFixedAssets () :Double {
        var result:Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 11610..11670) result += el.value.value
        }
        return result
    }
    fun fieldsAndEstate () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 12010..12030) result += el.value.value
        }
        return result
    }
    fun machineriesAndImplants () :Double {
        var result:Double = 0.00
        activeMap.forEach {el ->
            if (floor(el.key).toInt() in 12111..12122) result+=el.value.value
        }
        return result
    }
    fun equipments () :Double {
        var result:Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 12210..12230) result += el.value.value
        }
        return result
    }
    fun otherGoods () :Double {
        var result:Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 12310..12360) result += el.value.value
        }
        return result
    }
    fun wipTangibleAssets () :Double {
        var result:Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 12410..12420) result+=el.value.value
        }
        return result
    }
    fun partecipationsAssets () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 13010..13060) result+= el.value.value
        }
        return result
    }
    fun creditsAssets () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 13110..13170) result+=el.value.value
        }
        return result
    }
    fun bonds () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 13210..13280) result+=el.value.value
        }
        return result
    }
    fun propertyStocksAndOthers () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 13310..13570) result+=el.value.value
        }
        return result
    }
    fun inventories () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 14011..14060) result += el.value.value
        }
        return result
    }
    fun credits () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 14500..15150) result+= el.value.value
        }
        return result
    }
    fun taxCredits () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 16010.. 16520) result += el.value.value
        }
        return result
    }
    fun otherCredits () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 17010..17499) result += el.value.value
        }
        return result
    }
    fun bankAccounts () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 18010..18098) result += el.value.value
        }
        return result
    }
    fun cashAndChecks () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 18110..19010) result += el.value.value
        }
        return result
    }
    fun accrualsAndDeferrals () :Double {
        var result :Double = 0.00
        activeMap.forEach { el ->
            if (floor(el.key).toInt() in 19110..192220) result += el.value.value
        }
        return result
    }

    /**Passive MicroAreas*/

    fun netWealth () :Double {
        var result :Double = 0.00
        passiveMap.forEach { el ->
            var tester = floor(el.key).toInt()
            if (tester in 20010..20790 || tester in 20810..20840 || tester in 20910..20950) {
                result += el.value.value
            } else if (tester in 20760..20795 || tester in 20860..20890) {
                result -= el.value.value
            }
        }
        return result
    }




}