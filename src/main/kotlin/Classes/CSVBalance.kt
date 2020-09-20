package Classes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class CSVBalance (pathToBalance:String) {

    val path = File(pathToBalance)
    val reader = csvReader().readAll(path)

    init {
        val ids = mutableListOf<Double>()
        reader.forEach { el ->
            el.forEach {a ->
                if (a[0] != "ID" && a[0] !in ids) {
                    
                }
            }
        }
    }
}