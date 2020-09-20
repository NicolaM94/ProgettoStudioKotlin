package Classes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class BalanceParser (pathToBalance:String) {

    private val path = File(pathToBalance)
    private val reader = XSSFWorkbook(path)

    val countsMap: MutableMap<String, Asset> = mutableMapOf()

    init {
        val sheet = reader.getSheet("Stato Patrimoniale")
        for (row in sheet) {
            var testCell = row.getCell(0)?.toString() ?: "null"
            if (testCell.startsWith("1")) {
                countsMap.set(
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
                countsMap.set(
                    testCell, Asset(
                        testCell.toDouble(),
                        row.getCell(4).toString(),
                        "debt",
                        row.getCell(5).toString().toDouble()
                    )
                )
            }

        }
        countsMap.forEach{el ->
            println("$el")
        }
    }
}