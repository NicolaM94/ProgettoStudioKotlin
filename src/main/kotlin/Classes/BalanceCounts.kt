package Classes



open class Active (val id:Double, name:String, type:String, value:Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }


}

open class Passive (val id:Double, name:String, type: String, value: Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }

}

open class Cost (val id:Double, name:String, type: String, value: Double)  {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

open class Revenue (val id:Double, name: String, type: String, value: Double) {

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

