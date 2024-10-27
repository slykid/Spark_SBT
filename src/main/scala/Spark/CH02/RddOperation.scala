package Spark.CH02

import org.apache.spark.{SparkConf, SparkContext}

object RddOperation {

  def collectFunction(sc: SparkContext): Unit = {
    /*
      1. collect()
      - RDD 내 모든 요소를 배열로 반환
      - 전체 데이터를 담을 수 있을만큼 메모리가 확보된 상태에서 수행해야함
    */
    var rdd = sc.parallelize(1 to 10)
    var result = rdd.collect
    println(result.mkString(", "))
  }

  def countFunction(sc: SparkContext): Unit = {
    /*
      2. count()
      - RDD 내의 요소 개수를 반환
    */
    var rdd = sc.parallelize(1 to 11)
    println(rdd.count)

  }

  def mapFunction(sc: SparkContext): Unit = {
    /*
      3. map()
      - 입력받은 값(요소)을 적용된 연산의 결과로 반환
      - 전달되는 함수의 입력과 출력의 데이터 타입이 모두 같을 필요는 없음
    */
    var rdd = sc.parallelize(1 to 5)
    var result = rdd.map(_ + 1)
    println(result.collect.mkString(", "))
  }

  def flatMapFunction(sc: SparkContext): Unit = {
    /*
      4. flatMap()
      - map() 과 유사하나, 반환 타입이 시퀀스, 리스트처럼 이터레이션이 가능한 타입으로 반환
      - 하나의 입력값(요소)에 대응되는 반환값이 여러 개인 경우에 사용가능
    */
    val fruits = List("apple,orange", "grape,apple,mango", "blueberry,tomato,orange")
    val rdd1 = sc.parallelize(fruits)

    println("Map() Result")
    val rddMapResult = rdd1.map(_.split(","))
    println(rddMapResult.collect().map(_.mkString("{", ", ", "}")).mkString("{", ", ", "}"))

    println()
    println("flatMap() Result")
    val rddFlatMapResult = rdd1.flatMap(_.split(","))
    println(rddFlatMapResult.collect.mkString(", "))
  }

  def mapPartitionsFunction(sc: SparkContext): Unit = {
    /*
      5. mapPartitions()
      - map() 함수를 파티션 단위로 처리, 새로운 RDD를 결과로 반환
      - 파티션 단위의 중간산출물, 데이터베이스 연결 등의 고비용 자원을 파티션 단위로 사용하는 경우에 활용
    */
    val rdd1 = sc.parallelize(1 to 10, 3)
    val rdd2 = rdd1.mapPartitions(number => {
      println("DB연결!")
      number.map {
        number => number + 1
      }
    })

    println(rdd2.collect.mkString(", "))
  }



  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setMaster("local[*]")
      .setAppName("RddOperationTest")

    val sc = new SparkContext(conf)

    collectFunction(sc)
    countFunction(sc)
    mapFunction(sc)
    flatMapFunction(sc)
    mapPartitionsFunction(sc)

    sc.stop()

  }

}
