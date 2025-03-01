/**
 * packageName  : Spark.CH02
 * fileName     : RddAggregateOperation
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

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Logger, Level}

object RddAggregateOperation {

  val logger = Logger.getLogger("SparkTest")
  logger.setLevel(Level.INFO)

  def main(args: Array[String]): Unit = {

    val sc = getSparkContext()

    reduceByKeyFunction(sc)
    foldByKeyFunction(sc)
    combineByKeyFunction(sc)
    aggregateByKeyFunction(sc)

    sc.stop()
  }

  def getSparkContext(): SparkContext = {
    val conf = new SparkConf()
      .setAppName("RddAggregateOperationTest")
      .setMaster("local[*]")

    new SparkContext(conf)
  }

  def reduceByKeyFunction(sc: SparkContext): Unit = {
    /*
     20. reduceByKey
     - RDD 구성요소가 키, 값의 쌍으로 구성된 경우에 사용하는 메소드
     - 같은 키를 가진 값들을 하나로 병합해 키-값 쌍으로 구성된 새로운 RDD를 생성함
     - 병합을 수행하기 위해 2개의 값을 하나로 합치는 함수를 인자로 사용, 해당 함수는 결합법칙과 교환법칙이 성립해야함
    */
    val rdd = sc.parallelize(List("a", "b", "b")).map((_, 1))
    val result = rdd.reduceByKey(_ + _)

    logger.info(result.collect.mkString(", "))
  }

  def foldByKeyFunction(sc: SparkContext): Unit = {
    /*
      21. foldByKey
      - RDD 구성요소가 키, 값의 쌍으로 구성된 경우에 사용하는 메소드
      - 같은 키를 가진 값들을 하나로 병합해 키-값 쌍으로 구성된 새로운 RDD를 생성함
      - reduceByKey() 와 달리 병합 연산의 초기값을 메소드 인자로 전달해, 병합 시 사용할 수 있다는 점에서 차이가 있음
      - 사용되는 함수는 각 단위 병합 단계에서 결과에 영향이 없는 초기값을 사용하기 때문에 결합법칙만 만족하면 사용할 수 있음
    */
    val rdd = sc.parallelize(List("a", "b", "b")).map((_, 1))
    val result = rdd.foldByKey(0)(_ + _)

    logger.info(result.collect.mkString(", "))
  }

  def combineByKeyFunction(sc: SparkContext): Unit = {
    /*
      22. combineByKey
      - RDD의 구성요소가 키, 값의 쌍으로 구성된 경우에 사용하는 메소드
      - 같은 키를 가진 값들을 하나로 병합하는 기능을 수행하지만, 병합을 수행하는 과정에서 값의 타입이 바뀔 수 있다는 점에서
        reduceByKey 와 foldByKey 랑은 차이가 있음
      - 병합에 사용하는 함수와 최종결과를 만들어진 RDD가 모두 원래의 RDD와 같이 키의 타입은 K이고, 값 타입은 V를 유지하지만,
        combineByKey의 경우에는 값 타입이 C로 변경됨
    */
    val data = Seq(("Math", 100L), ("Eng", 80L), ("Math", 50L), ("Eng", 60L), ("Eng", 90L))
    val rdd = sc.parallelize(data)
    val createCombiner = (v: Long) => Record(v)
    val mergeValue = (c: Record, v: Long) => c.add(v)
    val mergeConbiners = (c1: Record, c2: Record) => c1.add(c2)
    val result = rdd.combineByKey(createCombiner, mergeValue, mergeConbiners)

    logger.info(result.collect.mkString(", "))
  }

  def aggregateByKeyFunction(sc: SparkContext): Unit = {
    /*
      23. aggregateByKey
      - RDD의 구성요소가 키, 값의 쌍으로 구성된 경우에 사용하는 메소드
      - 병합을 힛작하는 초기값 생성 부분을 제외하면 combineByKey()와 동일한 동작을 수행함
      - 차이점은 zeroValue라는 "값"을 사용한다는 점이 차이 (combineByKey() 중 createCombiner() 함수로 특정 값 zero를 돌려주는 함수를 사용한 것
    */
    val data = Seq(("Math", 100L), ("Eng", 80L), ("Math", 50L), ("Eng", 60L), ("Eng", 90L))
    val rdd = sc.parallelize(data)

    val zero = Record(0, 0)
    val mergeValue = (c: Record, v: Long) => c.add(v)
    val mergeConmbiners = (c1: Record, c2: Record) => c1.add(c2)

    val result = rdd.aggregateByKey(zero)(mergeValue, mergeConmbiners)

    logger.info(result.collect.mkString(",\t"))
  }

}

case class Record(var amount: Long, var number: Long = 1) {
  def map(v: Long) = Record(v)
  def add(amount: Long): Record = {
    add(map(amount))
  }
  def add(other: Record): Record = {
    this.number += other.number
    this.amount += other.amount
    this
  }
  override def toString: String = s"avg: ${amount / number}"
}