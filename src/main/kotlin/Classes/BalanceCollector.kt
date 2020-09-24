package Classes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Paths

class BalanceCollector (pathToBalance:String) {
    
    val mapOfCounts: MutableMap<Int,String> = mutableMapOf()

    /**Assigning indexes to counts IDs*/
    init {
        val workingPath = Paths.get("").toAbsolutePath().toString()
        val file = File (workingPath+"/pdc.csv")
        val reader = csvReader().readAll(file)
        reader.forEach { sublist ->
            mapOfCounts.set(sublist[0].toInt(),sublist[1])
        }
    }

    private val path = File(pathToBalance)
    private val reader = XSSFWorkbook(path)

    val activeMap: MutableMap<Int, Active> = mutableMapOf()
    val passiveMap: MutableMap<Int,Passive> = mutableMapOf()
    val costsMap: MutableMap<Int,Cost> = mutableMapOf()
    val revenuesMap: MutableMap<Int,Revenue> = mutableMapOf()

    /**Collecting values from the balance*/
    init {
        var sheet = reader.getSheet("Stato Patrimoniale")
        //Dumping assets and debts
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                activeMap.set(
                    mapOfCounts.getValue(testCell.take(5).toInt()).toInt(), Active(
                        testCell.toDouble(),
                        row.getCell(1).toString(),
                        "asset",
                        row.getCell(2).toString().toDouble()
                    )
                )
            }
        }
        for (row in sheet) {
            var testCell = row.getCell(3)?.toString() ?: "null"
            if (testCell.startsWith("2")) {
                passiveMap.set(
                    mapOfCounts.getValue(testCell.take(5).toInt()).toInt(), Passive(
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "debt",
                        row.getCell(5).toString().toDouble()
                    )
                )
            }

        }

        sheet = reader.getSheet("CONTO ECONOMICO")
        //Dumping costs and revenues
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?:"null"
            if (testCell.startsWith("3")) {
                costsMap.set(
                    mapOfCounts.getValue(testCell.take(5).toInt()).toInt(), Cost (
                        testCell.toDouble(),
                        row.getCell(1).toString(),
                        "cost",
                        row.getCell(2).toString().toDouble()
                    )
                )
            }
        }
        for (row in sheet) {
            var testCell = row.getCell(3)?.toString() ?:"null"
            if (testCell.startsWith("4")) {
                revenuesMap.set(
                    mapOfCounts.getValue(testCell.take(5).toInt()).toInt(), Revenue (
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "revenue",
                        row.getCell(5).toString().toDouble()
                    )
                )
            }
        }

        /*activeMap.forEach { el ->
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
        }*/
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
            if (el.key in 1..6) {
                result += el.value.value
            }
        }
        return result
    }
    fun plantCosts () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (el.key in 7..10) {
                result += el.value.value
            }
        }
        return result
    }
    fun rAndDCosts () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (el.key in 11..13) {
                result += el.value.value
            }
        }
        return result
    }
    fun patents () :Double {
        var result = 0.00
        activeMap.forEach { el ->
            if (el in 14..20) {
                result += el.value.value
            }
        }
        return result
    }
    fun licences () :Double {
        var result = 0.00
        activeMap.forEach{el ->
            if (el.key in 21..28) {
                result += el.value.value
            }
        }
        return result
    }
}