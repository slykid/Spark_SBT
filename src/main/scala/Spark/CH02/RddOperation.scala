package Spark.CH02

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

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

  def mapPartitionsWithIndexFunction(sc: SparkContext): Unit = {
    /*
      6. mapPartitionsWithIndex()
      - 인자로 전달받은 함수를 파티션단위로 적용하고, 결과값으로 구성된 새로운 RDD를 생성하는 메소드
      - 해당 파티션의 인덱스 정보도 포함해서 결과를 생성함
    */
    val rdd1 = sc.parallelize(1 to 10, 3)
    val rdd2 = rdd1.mapPartitionsWithIndex((idx, numbers) => {
      numbers.flatMap {
        case number if idx == 1 => Option(number + 1)
        case _                  => None
      }
    })

    println(rdd2.collect.mkString(", "))
  }

  def mapValuesFunction(sc: SparkContext): Unit = {
    /*
      7. mapValues
      - RDD의 모든 요소가 "키(key)-값(value)" 쌍으로 이루어진 경우(=페어 RDD, Pair RDD) 사용 가능
      - 키 부분은 그대로 두고, 값에만 map 함수를 적용한 (키, 값) 형태의 RDD를 반환
    */
    val rdd = sc.parallelize(List("a", "b", "c")).map((_, 1))
    val result = rdd.mapValues(i => i + 1)

    println(result.collect.mkString("\t"))
  }

  def flatMapValuesFunction(sc: SparkContext): Unit = {
    /*
      8. flatMapValues
      - RDD 구성요소가 키-값 쌍인 경우에만 사용할 수 있는 메소드
    */
    val rdd = sc.parallelize(Seq((1, "a,b"), (2, "a,c"), (1, "d,e")))
    val result = rdd.flatMapValues(_.split(","))

    println(result.collect.mkString("\t"))

  }

  def zipFunction(sc: SparkContext): Unit = {
    /*
      9. zip
      - 2개의 서로 다른 RDD를 각 요소의 인덱스에 따라 하나의 (키, 값) 쌍으로 묶어주는 메소드
      - 첫 번째 RDD의 요소 값을 키로, 두 번째 RDD의 요소 값을 값으로 사용함
    */
    val rdd1 = sc.parallelize(List("a", "b", "c"))
    val rdd2 = sc.parallelize(List(1, 2, 3))

    val result = rdd1.zip(rdd2)

    println(result.collect.mkString(", "))

  }

  def zipPartitionsFunction(sc: SparkContext): Unit = {
    /*
      10. zipPartitions
      - 파티션 단위로 zip 연산을 수행할 때 사용하는 메소드
      - 요소들의 집합 단위로 병합을 실행하므로 파티션의 개수만 동일하면 됨
    */
    val rdd1 = sc.parallelize(List("a", "b", "c", "d", "e"))
    val rdd2 = sc.parallelize(List(1, 2), 3)

    val result = rdd1.zipPartitions(rdd2) {
      (it1, it2) =>
        val result = new ListBuffer[String]
        while(it1.hasNext)
        {
          if(it2.hasNext)
          {
            result += (it1.next() + it2.next)
          }
          else
          {
            result += it1.next()
          }
        }
        result.iterator
    }

    println(result.collect.mkString(", "))
  }

  def groupByFunction(sc: SparkContext): Unit = {
    /*
      11. groupBy
      - 일정한 기준에 따라 여러 개의 그룹으로 나누고, 그룹으로 구성된 새로운 RDD를 생성하는 메소드
      - 키와 해당 키에 속한 요소의 시퀀스로 구성되며, 메소드 인자로 전달하는 함수가 각 그룹의 키를 결정하는 역할을 함
    */
    val rdd = sc.parallelize(1 to 10)
    val result = rdd.groupBy{
      case i: Int if (i % 2 == 0) => "even"
      case _                      => "odd"

    }

    result.collect.foreach{
      v => println(s"${v._1}, [${v._2.mkString(",")}]")
    }

  }

  def groupByKeyFunction(sc: SparkContext): Unit = {
    /*
      12. groupByKey
      - RDD의 구성요소가 키와 값의 쌍으로 이뤄진 경우 사용가능한 메소드
      - 수행 작업은 키를 기준으로 같은 키를 가진 요소들로 그룹을 만들고 그룹들로 구성된 새로운 RDD를 생성함
    */
    val rdd = sc.parallelize(List("a", "b", "c", "b", "c")).map((_, 1))
    val result = rdd.groupByKey

    result.collect.foreach {
      v => println(s"${v._1}, [${v._2.mkString(",")}]")
    }

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
    mapPartitionsWithIndexFunction(sc)
    mapValuesFunction(sc)
    flatMapValuesFunction(sc)

    zipFunction(sc)
    zipPartitionsFunction(sc)

    groupByFunction(sc)
    groupByKeyFunction(sc)

    sc.stop()

  }

}
