package Basic.OOP

object BookTest {
    def main(args: Array[String]): Unit = {
        val myBook1 = new Book(title="My Awesome Book1", author="Paul")
        val myBook2 = new Book(title="My Awesome Book2", author="Jason")

        println("My Book1 author: " + myBook1.author)
        println("My Book1 title: " + myBook1.title)

        myBook1.author = "Nancy"
        println("My Book1 author: " + myBook1.author)

        println(s"${myBook2.title} is written by ${myBook2.author}.")

        val myBook3 = new Book(title="My awesome book 3")
        println("My Book3 author: " + myBook3.author)

        // Named Parameter (파이썬의 방식과 동일함)
        val myBook4 = new Book(author="Steve", title="My awesome book 4")
        println(s"${myBook4.title} is written by ${myBook4.author}.")
    }

}
