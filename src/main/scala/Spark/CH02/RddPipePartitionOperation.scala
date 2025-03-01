/**
 * packageName  : Spark
 * fileName     : RddPipePartitionOperation
 * author       : kilhyunkim
 * date         : 25. 2. 14.
 * description
 * ===================================================
 * DATE                 AUTHOR              NOTE
 * ---------------------------------------------------
 * 25. 2. 14.          kilhyunkim         최초작성
 *
 */

package Spark.CH02

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}

object RddPipePartitionOperation {

  val logger = Logger.getLogger("SparkTest")
  logger.setLevel(Level.INFO)

  def main(args: Array[String]): Unit = {
    val sc = getSparkContext()

    pipeFunction(sc)
    coalesceRepartitionFunction(sc)
    repartitionAndSortWithinPartitionFunction(sc)
    partitionByFunction(sc)

    sc.stop()

  }

  def getSparkContext(): SparkContext = {
    val sc = new SparkConf()
      .setAppName("RddPipePartitionOperationTest")
      .setMaster("local[*]")

    new SparkContext(sc)
  }

  def pipeFunction(sc: SparkContext): Unit = {
    /*
      24. pipe
      - 데이터 처리 과정에서 외부 프로세스를 활용할 수 있음
    */
    val rdd = sc.parallelize(List("1,2,3", "4,5,6", "7,8,9"))
    val result = rdd.pipe("cut -f 1,3 -d ,")

    logger.info(result.collect.mkString(", "))
  }

  def coalesceRepartitionFunction(sc: SparkContext): Unit = {
    /*
      25. coalesce & repartition
      - 현재 RDD의 파티션 개수를 조정하는 메소드
      - 둘 다 최초 설정된 파티션 개수가 적합하지 않은 경우 조정하기 위해 사용되며, 정수를 인자를 받아서 파티션 수를 조정함
      - 차이점은 repartition은 파티션을 늘리거나 줄이는 것이 가능하나, coalesce는 줄이는 것만 가능함
    */
    val rdd1 = sc.parallelize(1 to 1000000, 10)
    val rdd2 = rdd1.coalesce(5)
    val rdd3 = rdd2.repartition(10)

    println(s"partition size: ${rdd1.getNumPartitions}")
    println(s"partition size: ${rdd2.getNumPartitions}")
    println(s"partition size: ${rdd3.getNumPartitions}")
  }

  def repartitionAndSortWithinPartitionFunction(sc: SparkContext): Unit = {
    /*
     *
    */
    val r = scala.util.Random
    val data = for (i <- 1 to 10) yield (r.nextInt(100), "-")

    val rdd1 = sc.parallelize(data)
    val rdd2 = rdd1.repartitionAndSortWithinPartitions(new HashPartitioner(3))

    println(rdd2.count)

    rdd2.foreachPartition(it => {
      println("===================")
      it.foreach(v => println(v))
    })
  }

  def partitionByFunction(sc: SparkContext): Unit = {
    val rdd1 = sc.parallelize(List("Apple", "Orange", "mouse", "monitor"), 5).map { a => (a, a.length)}
    val rdd2 = rdd1.partitionBy(new HashPartitioner(3))

    println(s"rdd1: ${rdd1.getNumPartitions}, rdd2: ${rdd2.getNumPartitions}")
  }
}
