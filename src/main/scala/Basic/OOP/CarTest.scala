package Basic.OOP

import Basic.OOP.Car.{Bus, Car, PaymentModule, Sedan, Suv}

object CarTest {
    def main(args: Array[String]): Unit = {
        val sedan: Car = new Sedan()
        val suv: Car = new Suv()
        val bus: Car = new Bus()

        val cars = List(sedan, suv, bus)

        cars.foreach(it =>  {
          it.engineStart()
          it.engineStop()
        })

        // PaymentModule 의 메소드를 사용할 경우
        bus.asInstanceOf[PaymentModule].collectPayment(1200)

    }

}
