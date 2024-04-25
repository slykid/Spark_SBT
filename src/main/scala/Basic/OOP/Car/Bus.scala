package Basic.OOP.Car

class Bus extends Car with PaymentModule {
    // Car
    def engineStart(): Unit = println("Engine Start in Bus")
    def engineStop(): Unit = println("Engine Stop in Bus")

    // PaymentModule
    def collectPayment(amount: Int): Boolean ={
        // 결제 기능 코드 작성
        println(s"Paid $amount won")
        return true
    }
}
