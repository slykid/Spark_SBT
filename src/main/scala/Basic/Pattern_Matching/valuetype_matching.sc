for {
  x <-  Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match
  {
    case 1 =>             "int: 1"
    case i: Int =>        "other int: " +i
    case d: Double =>     "other double: " +d
    case "one" =>         "string one"
    case s: String =>     "other string: " +s
    case unexpected =>    "unexpected value: " + unexpected
  }
  println(str)
}

// 부분함수화
for {
  x <-  Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match
  {
    case 1 =>             "int: 1"
    case _: Int =>        "other int: " +x
    case _: Double =>     "other double: " +x
    case "one" =>         "string one"
    case _: String =>     "other string: " +x
    case _ =>    "unexpected value: " + x
  }
  println(str)
}









