package Basic.OOP.Car

class Suv extends Car{
    override def engineStart(): Unit = println("Engine Start in SUV")
    override def engineStop(): Unit = println("Engine Stop in SUV")
}
