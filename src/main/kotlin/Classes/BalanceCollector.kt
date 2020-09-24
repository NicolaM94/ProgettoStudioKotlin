package Classes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Paths

class BalanceCollector (pathToBalance:String) {

    val baseSetOfCounts = mutableMapOf<String,String>()

    init {
        val workingPath = Paths.get("").toAbsolutePath().toString()
        val file = File (workingPath+"/pdc.csv")
        val reader = csvReader().readAll(file)
        reader.forEach { el -> println(el) }
    }




    private val path = File(pathToBalance)
    private val reader = XSSFWorkbook(path)

    val activeMap: MutableMap<String, Active> = mutableMapOf()
    val passiveMap: MutableMap<String,Passive> = mutableMapOf()
    val costsMap: MutableMap<String,Cost> = mutableMapOf()
    val revenuesMap: MutableMap<String,Revenue> = mutableMapOf()

    init {
        var sheet = reader.getSheet("Stato Patrimoniale")
        //Dumping assets and debts
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                activeMap.set(
                    testCell, Active(
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
                    testCell, Passive(
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
                    testCell, Cost (
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
                    testCell, Revenue (
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "revenue",
                        row.getCell(5).toString().toDouble()
                    )
                )
            }
        }

        /*activeMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        passiveMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        costsMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        revenuesMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
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



}