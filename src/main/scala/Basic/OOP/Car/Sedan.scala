package Basic.OOP.Car

class Sedan extends Car {
    override def engineStart(): Unit = println("Engine Start in Sedan")
    override def engineStop(): Unit = println("Engine Stop in Sedan")
}
