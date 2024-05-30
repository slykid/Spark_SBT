def checkY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case y => "found y!!!"
//      case `y` => "found y!!!"
      case i: Int => "int: " + i
    }
    println(str)
  }
}

checkY(100)


// 여러 데이터 타입 한 번에 처리하기
for{
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case _: Int | _: Double => "a number: " + x
    case "one"              => "string one"
    case _: String          => "other String: " + x
    case _                  => "unexpected value:" + x
  }
  println(str)
}
