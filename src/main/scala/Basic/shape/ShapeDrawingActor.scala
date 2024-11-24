package Basic.shape

import akka.actor.Actor

object Messages {
    object Exit
    object Finished
    case class Response(message: String)
}

class ShapesDrawingActor extends Actor{
    // Actor 를 정의하기 위해서는 반드시 추상 기반 클래스의 서브클래스로 생성해야 함
    // 스칼라에서는 블록 내에서 import 문 사용이 가능하며, 이 때 해당 import문이 적용되는 범위는 블록 내로 설정됨
    import Messages._

    def receive = {
        /*
            반드시 구현해야하는 추상 메소드
            대부분의 액터 시스템에는 액터가 메세지를 처리할 때가지 보관하는 일종의 우편함이 액터별로 존재함
            특정 메세지를 처리하는 동안에는 그 메세지를 처리하는 코드가 다른 스레드에 의해 선점되지 않도록 보장함
            처리방식은 비동기로 동작함 -> 액터의 receive에 정의할 코드 블록은 송신 액터 등의 액터에 다른 메세지를 전송함
        */

        /*
            아래 코드와 같은 형식의 함수를 PartialFunction 이라고 하며, 특별한 함수의 리터럴 구문
            - 인자 타입은 [Any, Unit] 이며, Any 타입을 인자로 받아, Unit 타입으로 변환함
                - Any 타입: 스칼라 타입 계층 중 최상위 클래스

            - 함수에 전달된 메세지의 패턴 매칭을 수행하는 case 절로만 구성됨
                - 패턴 중 하나가 일치할 경우 => 와 다음번 case 절 사이의 식을 평가함
                  (case 키워드가 코드의 모호성을 제거하므로 중간에 {} 로 블록을 설정하지 않아도 됨)
        */
        case s: Shape =>
            s.draw(str => println(s"ShapesDrawingActor: $str"))
            sender ! Response(s"ShapesDrawingActor: $s drawn")
        case Exit =>
            println(s"ShapesDrawingActor: exiting...")
            sender ! Finished
        case unexpected =>
            val response = Response(s"ERROR: Unknown message: $unexpected")
            println(s"ShapesDrawingActor: $response")
            sender ! response
    }
}
