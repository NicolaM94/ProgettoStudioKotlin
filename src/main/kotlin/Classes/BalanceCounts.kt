package Classes


/** Abstract collector to use as generic in the GUI classes and others.
 * @param id Balance count number
 * @param name Balance count name
 * @param type Balance count type (Asset,Passivity,Cost or Revenue)
 * @param value Balance count value
 * @property nameChanger manage count name
 * @property typeChanger manage count type
 * @property valueChanger manage count value */
abstract class BalanceCount (open val id :Double, open var name :String,open var type: String, open var value: Double) {

    fun nameChanger (newName:String) { name=newName }

    fun typeChanger (newType:String) { type=newType }

    fun valueChanger (newValue:Double) { value=newValue }
}


open class Active (id:Double, name:String, type:String, value:Double) :BalanceCount(id,name,type,value) {

    override val id:Double = id

    override var name:String = name

    override var type:String = type

    override var value:Double = value


}

open class Passive (id:Double, name:String, type: String, value: Double) :BalanceCount(id,name,type,value) {

    override val id:Double = id

    override var name:String = name

    override var type:String = type

    override var value:Double = value
}

open class Cost (id:Double, name:String, type: String, value: Double) :BalanceCount(id,name,type,value) {

    override val id:Double = id

    override var name:String = name

    override var type:String = type

    override var value:Double = value
}

open class Revenue (id:Double, name: String, type: String, value: Double) :BalanceCount(id,name,type,value){

    override val id:Double = id

    override var name:String = name

    override var type:String = type

    override var value:Double = value


}

