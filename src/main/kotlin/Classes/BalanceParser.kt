package Classes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.lang.reflect.GenericArrayType
import java.lang.reflect.GenericDeclaration

class BalanceParser (pathToBalance:String) {

    private val path = File(pathToBalance)
    private val reader = XSSFWorkbook(path)

    val assetsMap: MutableMap<String, Asset> = mutableMapOf()
    val debtsMap: MutableMap<String,Debt> = mutableMapOf()
    val costsMap: MutableMap<String,Cost> = mutableMapOf()
    val revenuesMap: MutableMap<String,Revenue> = mutableMapOf()

    init {
        var sheet = reader.getSheet("Stato Patrimoniale")
        //Dumping assets and debts
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                assetsMap.set(
                    testCell, Asset(
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
                debtsMap.set(
                    testCell, Debt(
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

        assetsMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        debtsMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        costsMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
        revenuesMap.forEach { el ->
            println("${el.key} : ${el.value.name},${el.value.type}, ${el.value.value}")
        }
    }
}