package Spark.WordCount

import org.apache.log4j.{Logger, Level}
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]) {

    val logger = Logger.getLogger("WordCount")
    logger.setLevel(Level.INFO)

    logger.info("1. Spark Conf 설정")
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Spark/WordCount")

    val sc = new SparkContext(conf)

    var exit_code = 1
    try
    {
      logger.info("2. README 파일 로드")
      var input = sc.textFile("input/README.md")

      logger.info("3. WordCount 수행")
      var count = input.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)

      count.saveAsTextFile("output/output.txt")
      logger.info("OK")

      exit_code = 0
      
    } catch {
      case ex: Exception =>
        logger.error("ex:" + ex)
    } finally {
      System.exit(exit_code)
    }
  }
}
