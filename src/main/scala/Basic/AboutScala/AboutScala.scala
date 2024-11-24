package Basic.AboutScala

import org.apache.spark.sql.catalyst.expressions.Upper

class Upper {
  def upper(strings: String*): Seq[String] = {
    strings.map((s: String) => s.toUpperCase())
  }

}

object Upper {
  // 보통 메소드의 반환타입은 추론할 수 있으므로 명시적인 변환 타입 표기를 삭제할 수 있다.
  // 추가적으로 메소드 본문에 대해서도 식이 1개만 들어있기 때문에 불필요한 중괄호도 제거하고, 1줄로 메소드를 표기했다.
  def upper2(strings: String*) = strings.map(_.toUpperCase())
}

object AboutScala {

  def main(args: Array[String]): Unit = {

    // 1. val 키워드: 변경 불가능 변수
    val s = "Hello World!"
    println(s)

    // 2. 수치형 값 연산
    println(1 + 2)

    // 3. 멤버쉽 테스트
    println(s.contains("el"))

    // 4. 타입 추론
    val book = "Programming Scala"
    println(book)

    // 5. 타입 정보 추가하기 (:)
    // - 타입 정보를 보여주거나, 선언에 명시적으로 타입 정보를 추가하려는 경우, 대상 이름 뒤에 콜론(:)과 타입 표기를 추가한다.
    val up = new Upper
    println(up.upper("Hello", "World!"))

    // 6. 함수형 프로그래밍
    // - 스칼라에서는 함수형 프로그래밍 사용 시, 싱글턴 디자인 패턴을 기본요소로 받아들였다.
    // - 위의 이유로 클래스처럼 선언했으나, 스칼라 실행환경에서 Upper 인스턴스는 오직 1개만 생성한다.
    println(Upper.upper2("Hello", "World!"))


  }

}
