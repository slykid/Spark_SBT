package Basic.Function

object Function extends App {
  // 1. 기본형
  def add(a: Int, b: Int): Int = {
    a + b
  }
  println(add(1, 2))


  // 2. Consise Version
  def add2(a: Int, b: Int): Int = a + b
  println(add(2, 3))

  // 3. Function Types (Anonymous Function)
  // - = 과 => 의 차이
  val substract = (a: Int, b:Int) => a - b
  val result = substract(10, 3)
  println(result)

  // 4. Higher Order function



}
