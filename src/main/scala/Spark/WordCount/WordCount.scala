package Spark.WordCount

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Spark/WordCount")

    val sc = new SparkContext(conf)

    var exit_code = 1
    try {
      var input = sc.textFile("input/README.md")
      var count = input.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)

      count.saveAsTextFile("output/output.txt")
      println("OK")

      exit_code = 0
      
    } catch {
      case ex: Exception =>
        println("ex:" + ex)
    } finally {
      System.exit(exit_code)
    }
  }
}
