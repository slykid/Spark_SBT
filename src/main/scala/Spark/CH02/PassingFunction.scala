package Spark.CH02

import org.apache.spark.{SparkContext, SparkConf}

class PassingFunction {

  // 예제 2-1
  val count = 1

  def add1(i: Int): Int = {
    count + i
  }

  def runMap(sc: SparkContext): Unit = {
    val rdd1 = sc.parallelize(1 to 10)
    val rdd2 = rdd1.map(add1)  // Caused by: java.io.NotSerializableException 발생!

    println(count)
  }


  // 예제 2-2
  def add(i: Int): Int = {
    i + 1
  }

  def runMap2(sc: SparkContext) {
    val rdd1 = sc.parallelize(1 to 10)
    val rdd2 = rdd1.map(Operations.add)
    print(rdd2.collect().toList)
  }

  // 예제 2-3
  var increment = 1

  def runMap3(sc: SparkContext): Unit = {
    val rdd1 = sc.parallelize(1 to 10)
    val rdd2 = rdd1.map(_ + increment)

    println(rdd2.collect().toList)
  }

  def runMap4(sc: SparkContext): Unit = {
    val localIncrement = increment

    val rdd1 = sc.parallelize(1 to 10)
    val rdd2 = rdd1.map(_ + localIncrement)

    println(rdd2.collect().toList)
  }

}

// 예제 2-2
object Operations {
  def add(i: Int): Int = {
    i + 1
  }
}

object PassingFunctionRunner {
  def main(args: Array[String]): Unit = {
    val sc = getSparkContext
    val sample = new PassingFunction

    // 실행 메소드 (주석제거 후 실행)
    // sample.runMap(sc)
    // sample.runMap2(sc)
    // sample.runMap3(sc)
    sample.runMap4(sc)

    sc.stop

  }

  def getSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
        .setAppName("PassingFunction")

    new SparkContext(conf)
  }
}