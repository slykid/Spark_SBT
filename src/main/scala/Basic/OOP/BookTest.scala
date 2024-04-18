package Basic.OOP

object BookTest {
    def main(args: Array[String]): Unit = {
        val myBook1 = new Book(title="My Awesome Book1", author="Paul")
        val myBook2 = new Book(title="My Awesome Book2", author="Jason")

        println(myBook1.author)
        println(myBook1.title)

        println()

        println(myBook2.author)
        println(myBook2.title)
    }

}
