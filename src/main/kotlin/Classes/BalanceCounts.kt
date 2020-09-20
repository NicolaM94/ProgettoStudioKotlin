package Classes

class Asset (val id:Double, name:String, type:String, value:Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }

}

class Debt (val id:Double,name:String, type: String, value: Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }

}

class Cost (val id:Double, name:String, type: String, value: Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

class Revenue (val id:Double, name: String, type: String, value: Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

