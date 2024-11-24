package Basic.AboutScala

import Basic.shape._

object ActorModelTest {

    def main(args: Array[String]): Unit = {

        // Point 선언
        val p00 = new Point()           // 인자 설정 안하는 경우: 기본 설정 값으로 초기화
        val p20 = new Point(2.0, 0.0)   // 인자 값은 입력한 순서대로 할당됨
        val p20b = new Point(2.0)       // 인자 수 보다 입력한 값의 수가 적은 경우, 입력한 값까지만 할당되고, 나머지는 기본 값으로 할당됨
        val p02 = new Point(y=2.0)      // 만약 특정 인자와 값을 같이 할당한 경우 해당 인자만 입력한 값으로 할당되고, 나머지는 기본 값으로 할당됨

        /*
        스칼라의 "== 연산자"
        - Java 의 "== 연산자" 는 논리적 비교를 위해 equals 메소드를 명시적으로 호출해야한다.
        - 스칼라에서는 "== 연산자" 를 사용 시, equals 메소드를 호출하기 때문에 별도로 명시하여 비교할 필요는 없다.
        */
        println(p00 == p20)     // false
        println(p20 == p20b)    // true

        // Case Class 의 특징: 모든 케이스 클래스에 대해 각 클래스와 이름이 동일한 싱글톤 객체인 동반 객체를 자동으로 생성한다.
        // 동반 객체에는 메소드를 추가할 수 있으며, 추후에 다룰 예정
        // apply() 메소드와 같이 자동으로 추가되는 메소드들도 존재함
        val p1 = Point.apply(1.0, 2.0)  // 객체에 apply() 메소드가 없으면, 컴파일에서 에러 발생함 -> 추가 필요!
        val p2 = Point(1.0, 2.0)

        // 일반적인 함수형 프로그래밍에서는 부수 효과 없이 작업 결과를 반환 값으로 돌려주는 순수 함수를 선호함
        // - 추론, 재활용이 쉽다는 장점이 있음
        // - 최소한의 I/O는 필요함

    }
}