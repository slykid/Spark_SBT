package Basic.Expressions

/*
Expression vs. Instruction
- Expression: 값을 반환, 데이터 타입 존재 ex. 1+2, if(a > b) a else b, {val x = 10; x+20}
- Instruction: 값 반환 x (but, 실행은 됨), 데이터 타입 없음 ex. println("Hello World!") -> 콘솔에서 실행되는 Unit 이므로
 */

object ExpressionsTest extends App {
  // 1. Basic Expression
  var sum = 10 + 5
  sum += 10
  println(sum)

  // 2. Conditional Expression
  val a1 = 4
  val a2 = 5
  val max = if (a1 > a2) a1 else a2
  println(max)

  // Negation
  println(!(1 == 1))

  // 3. Block Expression
  val blockValue = {
    val a = 10
    val b = 5
    a + b
  }
  println(blockValue)

  // 4. Functional Expression
  val multiply = (x: Int, y: Int) => x * y
  println(multiply(3, 4))

  // 5. Case Classes and Pattern Matching
  case class Point(x: Int, y: Int)
  val point = Point(1, 2)
  val description = point match {
    case Point(0, 0) => "origin"
    case Point(x, 0) => s"X axis at $x"
    case Point(0, y) => s"Y axis at $y"
    case _ => "Somewhere on the plane"
  }
  println(description)

  // 6. Collection Operations
  val number = List(1, 2, 3, 4, 5)
  val doubled = number.map(_ * 2)
  println(doubled)

  println(println(123))
}
