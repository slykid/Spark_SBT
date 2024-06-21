package Basic.Expressions

object FunctionTest extends App {

  // 1. General Function
  def add(a: Int, b: Int): Int = {
      a + b
  }
  println(add(1, 2))

  // 2. Concise Version
  def add2(a: Int, b: Int): Int = a + b  // 중괄호의 생략도 가능함
  println(add2(1, 2))

  // 3. Function Type (Assign anonymous function to val)
  /*
   * Key diff between = and =>
     * = : 메소드의 정의를 대입함 (실제 실행되는 코드)
     * => : 함수의 타입을 대입함
   */
  val substract = (a: Int, b: Int) => a - b
  val result = substract(10, 3)
  println(result)

  // 4. Higher order Function
  def operateOnNumbers(a: Int, b:Int, operation: (Int, Int) => Int): Int = {
    operation(a, b)
  }
  val outputSum = operateOnNumbers(2, 3, add)
  println(outputSum)

  // 5. Anonymous Function (Function Literals)
  val result1 = operateOnNumbers(3, 4, (x, y) => x * y)
  println(result1)

  // 6. Nested Function
  def factorial(n: Int): Int = {
    def iter(x: Int, y: Int): Int = {
      if (x <= 0) y
      else iter(x - 1, x * y)  // 재귀
    }
    iter(n, 1)
  }
  println(factorial(3))

  // 7. Default Parameter & String Interpolation
  /*
   * 기본 값을 설정하고 싶은 경우에는 파라미터에 값을 할당해주면, 해당 값이 파라미터의 기본 값으로 설정됨
   * 문자열의 경우, 원하는 변수의 값을 포함시켜서 문자열을 구성하고 싶은 경우라면, s"{$~~~}" 형식으로 사용 가능 함
   */
  def greet(name: String, greeting: String = "Hello"): String = s"{$greeting}, $name!"
  println(greet(name="Alice", greeting="Hi"))
  println(greet(name="Bob"))


  // 8. varargs (Variable number of Arguments)
  /*
   * 자료형 다음에 "*" 을 붙여주면, Serial 형태로 해당 자료형 값을 여러 개 선언할 수 있다.
   */
  def totalSum(numbers: Int*): Int = numbers.sum
  println(totalSum(1, 2, 3, 4))

  def concatenate(strings: String*): String = {
    strings.mkString
  }
  println(concatenate("Hello", "concat", "World", "!"))

  def findMax(numbers: Int*): Int = {
    numbers.max
  }
  println(findMax(1, 2, 3, 10))

  def printAll(strings: String*): Unit = {
    for(str <- strings) {
      println(str)
    }
  }
  printAll("Hello", "concat", "World", "!")

  def greet2(greeting:String, names: String*): Unit = {
    names.foreach(name => println(s"$greeting $name"))
  }
  greet2("Hello", "Alice", "Bob", "slykid")

  // 9. Partial Function: PartialFunction[INPUT_TYPE, OUTPUT_TYPE]
  val handleStrings: PartialFunction[String, String] = {
    case "Scala" => "A great Language"
    case "Java" => "A classic Language"
  }
  println(handleStrings("Scala"))

  // 단, 위에 정의되지 않은 값의 경우에는 아래와 같은 에러를 출력함
  // println(handleStrings("Python"))
  // -> Exception in thread "main" scala.MatchError: Python (of class java.lang.String)

  // 때문에 아래와 같이 예외 처리를 하는 조건문을 넣어줘야 함
  if(handleStrings.isDefinedAt("Ruby")) {
    println(handleStrings("Ruby"))
  } else {
    println("No Information about Ruby")
  }


}
