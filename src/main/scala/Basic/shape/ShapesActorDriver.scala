package Basic.shape

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

// 파일 내 사용하는 메세지
private object Start

object ShapeDrawingDriver {
    def main(args: Array[String]): Unit = {
        val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
        val drawer = system.actorOf(
          Props(new ShapesDrawingActor), "drawingActor"
        )

        val driver = system.actorOf(
          Props(new ShapesDrawingDriver(drawer)), "drawingService"
        )

        driver ! Start
    }
}

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {
    import Messages._

    def receive = {
        case Start =>
            drawerActor ! Circle(Point(0.0, 0.0), 1.0)                                  // 모양 생성
            drawerActor ! Rectangle(Point(0.0, 0.0), 2, 5)                              // 모양 생성
            drawerActor ! 3.14159                                                       // 오류 처리
            drawerActor ! Triangle(Point(0.0, 0.0), Point(2.0, 0.0), Point(1.0, 2.0))   // 모양 생성
            drawerActor ! Exit                                                          // 종료 (context를 통해 접근해 엑터 시스템을 종료)

        case Finished =>
            println(s"ShapeDrawingDriver: cleaning up...")
            context.system.stop(drawerActor)

        case response: Response =>
            println("ShapeDrawingDriver: Response = " + response)

        case unexpected =>
            println("ShapesDrawingDriver: ERROR: Received an unexpected message = " + unexpected)
    }


}
