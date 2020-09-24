package Classes


open class Active (id:Double, name:String, type:String, value:Double) {

    val id:Double = id

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }


}

open class Passive (id:Double, name:String, type: String, value: Double) {

    val id:Double = id

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }

}

open class Cost (id:Double, name:String, type: String, value: Double)  {

    val id:Double = id

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

open class Revenue (id:Double, name: String, type: String, value: Double) {

    val id:Double = id

    var name:String = name

    var type:String = type

    var value:Double = value

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}

