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

    /**Assets MicroAreas*/
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
    fun wipAssets () :Double {
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

}