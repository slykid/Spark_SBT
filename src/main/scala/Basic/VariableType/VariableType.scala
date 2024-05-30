package Basic.VariableType

object VariableType extends App {
  // 1. Constant VS. Mutable
  val constantValue: Int = 31
  println(constantValue)

  var mutableValue: Int = 10
  mutableValue = 20
  println(mutableValue)


  // 2. Type Inference
  val number = 32
  println(number.isInstanceOf[Int])

  var name = "slykid"
  println(name.isInstanceOf[String])


  // 3. Built-In DataType
  val byteVal: Byte = 10
  println(byteVal)

  val shortVal: Short = 100
  println(shortVal)

  val intVal: Int = 1000
  println(intVal)

  val longVal: Long = 10000L
  println(longVal)

  val floatVal: Float = 10.5f
  println(floatVal)

  val doubleVal: Double = 10.5
  println(doubleVal)

  val charVal: Char = 'A'
  println(charVal)

  val booleanVal: Boolean = true
  println(booleanVal)

}