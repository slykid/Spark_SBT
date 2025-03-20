/**
 * packageName  : Spark.CH02
 * fileName     : RddActionOperation
 * author       : kilhyunkim
 * date         : 25. 3. 6.
 * description
 * ===================================================
 * DATE                 AUTHOR              NOTE
 * ---------------------------------------------------
 * 25. 3. 6.          kilhyunkim         최초작성
 *
 */

package Spark.CH02

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object RddActionOperation {

  def main(args: Array[String]): Unit = {
    val sc = getSparkContext()

    firstFunction(sc)
    takeFunction(sc)
    takeSampleFunction(sc)
    countByValueFunction(sc)
    reduceFunction(sc)
    foldFunction(sc)
    aggregateFunction(sc)
    sumFunction(sc)
    foreachFunction(sc)
    toDebugStringFunction(sc)
    cacheAndPersistFunction(sc)

    sc.stop()
  }

  def getSparkContext(): SparkContext = {
    val conf = new SparkConf()
      .setAppName("rddActionOperationTest")
      .setMaster("local[*]")

    new SparkContext(conf)
  }

  def firstFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10)
    val result = rdd.first()

    println(result)
  }

  def takeFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10, 5)
    val result = rdd.take(5)

    println(result.mkString(", "))
  }

  def takeSampleFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 100)
    val result = rdd.takeSample(false, 20)

    println(result.length)
  }

  def countByValueFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(List(1, 1, 2, 3, 3))
    val result = rdd.countByValue()

    println(result)
  }

  def reduceFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10, 3)
    val result = rdd.reduce(_ + _)

    println(result)
  }

  def foldFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10, 3)
    val result = rdd.fold(0)(_ + _)

    println(result)
  }

  def aggregateFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(List(100, 80, 75, 90, 95), 3)
    val zeroValue = Record(0, 0)
    val seqOp = (r: Record, v: Int) => r.add(v)
    val combOp = (r1: Record, r2: Record) => r1 add r2
    val result = rdd.aggregate(zeroValue)(seqOp, combOp)

    println(result.amount / result.number)

    val resultFunctional = rdd.aggregate(Record(0, 0))(_ add _, _ add _)
    println(resultFunctional.amount / resultFunctional.number)
  }

  def sumFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10)
    val result = rdd.sum()

    println(result)
  }

  def foreachFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 10, 3)

    rdd.foreach{
      v => println(s"Value side Effect: ${v}")
    }

    rdd.foreachPartition( values => {
      println("Partition Side Effect:")
      for(v <- values) println(s"Value Side Effect: ${v}")
    })
  }

  def toDebugStringFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 100, 10).map(_ * 2).persist.map(_ + 1).coalesce(2)

    println(rdd.toDebugString)
  }

  def cacheAndPersistFunction(sc: SparkContext): Unit = {
    val rdd = sc.parallelize(1 to 100, 10)
    rdd.cache()

    rdd.persist(StorageLevel.MEMORY_ONLY)
  }
}
