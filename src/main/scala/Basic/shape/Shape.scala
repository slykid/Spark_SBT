package Basic.shape

case class Point(x: Double = 0.0, y: Double = 0.0)

abstract class Shape() {
  // 각 Shape은 자신을 문자열로 바꾼 결과를 함수에 전달한다.
  def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")  // Unit 타입: 실제 타입이지만, 자바의 void 처럼 동작함
}

case class Circle(center: Point, radius: Double) extends Shape
case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape
case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape
