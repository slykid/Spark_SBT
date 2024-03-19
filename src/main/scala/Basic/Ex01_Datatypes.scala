package Basic

object Ex01_Datatypes {

  def main(args: Array[String]): Unit = {

    // 1. 변수 선언
    var name = "slykid"  // var : variable 의 약어, 변수 / 설정한 변수 값을 보고 추론하여 데이터 타입을 정함
    println(name)

    name = "hyun"
    println(name)

    // 2. 상수 선언
    val name_const = "slykid"
    println(name_const)

//    try {
//      name_const = "hyun"
//    } catch {
//      case ex: Exception =>
//        println("ex: " + ex)
//    }

    // 3. 데이터 타입 설정
    var name_set: String = "slykid"
    println(name_set)

//    try {
//      name_set = 13
//    } catch {
//      case ex: Exception =>
//        println("ex: " + ex)
//    }

  }

}
