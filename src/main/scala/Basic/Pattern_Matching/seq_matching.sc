//val nonEmptySeq   = Seq(1, 2, 3, 4, 5)
//val emptySeq      = Seq.empty[Int]
//val nonEmptyList  = List(1, 2, 3, 4, 5)
//val emptyList     = Nil
//val nonEmptyVec   = Vector(1, 2, 3, 4, 5)
//val emptyVec      = Vector.empty[Int]
//val nonEmptyMap   = Map("one" -> 1, "two" -> 2, "three" -> 3)
//val emptyMap      = Map.empty[String, Int]

//def seqToString[T](seq: Seq[T]): String = seq match {
//    case head +: tail => s"$head +: " + seqToString(tail)
//    case Nil => "Nil"
//}
//
//for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList, nonEmptyVec, emptyVec, nonEmptyMap.toSeq, emptyMap.toSeq))
//{
//    println(seqToString(seq))
//}


// 수정 결과
val nonEmptySeq   = Seq(1, 2, 3, 4, 5)
val emptySeq      = Seq.empty[Int]
val nonEmptyMap   = Map("one" -> 1, "two" -> 2, "three" -> 3)

def seqToString2[T](seq:Seq[T]): String = seq match {
    case head +: tail => s"($head +: ${seqToString2(tail)})"
    case Nil => "(Nil)"
}

for(seq <- Seq(nonEmptySeq, emptySeq, nonEmptyMap.toSeq))
{
    println(seqToString2(seq))
}
