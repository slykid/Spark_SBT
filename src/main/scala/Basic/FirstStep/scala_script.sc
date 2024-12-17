// 1. scala 에서는 모든 것이 객체이다. (결과마저 객체)
// - Int타입은 scala.Int 타입을 의미함(자바 패키지와 동일) -> 자바의 모든 원시타입은 스칼라 패키지에 대응하는 클래스 존재함
1 + 2
res0 * 3
println("Hello World!")

// 2. 변수 정의하기
// - val: Java의 final 과 유사함, 초기화 이후 변경이 불가함
// - var: Java의 일반 변수와 유사함, 초기화 이후에도 변경이 가능함
val msg = "Hello, World!"
// msg = "Hello, Scala!"
// Error: reassignment to val

var greeting = "Hello, World!"
greeting = "Hello, Scala!"

// 3. 인터프리터에서 여러 줄의 문자열을 입력하는 경우
// val multiLine =
//   | "This is the next line." [Enter x 2]

// 4. 함수 정의하기
def max(x: Int, y: Int): Int = {
  if (x > y) x
  else y
}

max(3, 5)