/**
 * packageName  : Spark.CH02
 * fileName     : RddSetOperation
 * author       : kilhyunkim
 * date         : 25. 2. 9.
 * description
 * ===================================================
 * DATE                 AUTHOR              NOTE
 * ---------------------------------------------------
 * 25. 2. 9.          kilhyunkim         최초작성
 *
 */

package Spark.CH02

import org.apache.spark.{SparkConf, SparkContext}

object RddSetOperation {

  def main(args: Array[String]): Unit = {

    val sc = getSparkContext()

    distinctFunction(sc)
    subtractFunction(sc)
    unionFunction(sc)
    intersectionFunction(sc)
    joinFunction(sc)
    outerJoinFunction(sc)
    subtractByKeyFunction(sc)

    sc.stop()

  }

  def getSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
      .setAppName("RddSetOperationTest")

    new SparkContext(conf)

  }

  def distinctFunction(sc: SparkContext): Unit = {
    /*
      13. distinct
      - RDD의 원소에서 중복을 제외한 요소로만 구성된 새로운 RDD를 생성하는 메소드
    */
    val rdd = sc.parallelize(List(1, 2, 3, 1, 2, 3, 1, 2, 3))
    val result = rdd.distinct()

    println(result.collect.mkString(", "))
  }

  def subtractFunction(sc: SparkContext): Unit = {
    /*
      14. subtract
      - 2개의 RDD가 있을 때, rdd1.subtract(rdd2) 라면, rdd1에 속하고, rdd2에는 속하지 않는 요소로 구성된 새로운 RDD를 생성하는 메소드
      - 집합에서 차집합에 해당
    */
    val rdd1 = sc.parallelize(List("a", "b", "c", "d", "e"))
    val rdd2 = sc.parallelize(List("d", "e"))
    val result = rdd1.subtract(rdd2)

    println(result.collect.mkString(", "))

  }

  def unionFunction(sc: SparkContext): Unit = {
    /*
      15. union
      - 2개의 RDD가 있을 때 rdd1 또는 rdd2에 속하는 요소로 구성된 새로운 RDD를 생성하는 메소드
    */
    val rdd1 = sc.parallelize(List("a", "b", "c"))
    val rdd2 = sc.parallelize(List("d", "e", "f"))

    val result = rdd1.union(rdd2)

    println(result.collect.mkString(", "))

  }

  def intersectionFunction(sc: SparkContext): Unit = {
    /*
      16. intersection
      - 2개의 RDD가 존재할 때 rdd1과 rdd2에 동시에 속하는 요소로 구성된 새로운 RDD를 생성하는 메소드
      - 결과로 생성되는 RDD에는 중복된 원소가 존재하지 않음
    */
    val rdd1 = sc.parallelize(List("a", "a", "b", "c"))
    val rdd2 = sc.parallelize(List("a", "a", "c", "c"))

    val result = rdd1.intersection(rdd2)

    println(result.collect.mkString(", "))

  }

  def joinFunction(sc: SparkContext): Unit = {
    /*
      17. join
      - RDD 구성요소가 키와 값의 쌍으로 구성된 경우에 사용할 수 있는 메소드
      - 서로 같은 키를 가지고 있는 요소를 모아, 그룹을 형성하고, 결과로 구성된 새로운 RDD를 생성하는 메소드
    */
    val rdd1 = sc.parallelize(List("a", "b", "c", "d", "e")).map((_, 1))
    val rdd2 = sc.parallelize(List("b", "c")).map((_, 2))

    val result = rdd1.join(rdd2)

    println(result.collect.mkString("\n"))
  }

  def outerJoinFunction(sc: SparkContext): Unit = {
    /*
      18. LeftOuterJoin, RightOuterJoin
      - RDD의 구성요소가 키와 값의 쌍으로 구성된 경우에 사용할 수 있는 메소드
      - 2개의 RDD가 있을 때, 메소드 이름 그대로 왼쪽 외부 조인, 오른쪽 외부 조인을 수행하고, 결과로 구성된 새로운 RDD를 생성함
    */
    val rdd1 = sc.parallelize(List("a", "b", "c")).map((_, 1))
    val rdd2 = sc.parallelize(List("b", "c")).map((_, 2))

    val result1 = rdd1.leftOuterJoin(rdd2)
    val result2 = rdd1.rightOuterJoin(rdd2)

    println("result1: " + result1.collect.mkString("\t"))
    println("result2: " + result2.collect.mkString("\t"))
  }

  def subtractByKeyFunction(sc: SparkContext): Unit = {
    /*
      19. subtractByKey
      - RDD의 구성요소가 키와 값의 쌍으로 구성된 경우에 사용할 수 있는 메소드
      - rdd1의 요소 중 rdd2에 같은 키가 존재하는 요소를 제외한 나머지로 구성된 새로운 RDD를 생성함
    */
    val rdd1 = sc.parallelize(List("a", "b")).map((_, 1))
    val rdd2 = sc.parallelize(List("b")).map((_, 2))

    val result = rdd1.subtractByKey(rdd2)

    println(result.collect.mkString("\n"))
  }

}
