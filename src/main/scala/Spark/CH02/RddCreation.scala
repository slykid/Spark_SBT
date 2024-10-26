package Spark.CH02

import org.apache.spark.{SparkConf, SparkContext}

object RddCreation {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
      .setAppName("RddOperation")

    val sc = new SparkContext(conf)

    // 1. RDD 생성1: 드라이버 프로그램의 컬렉션 객체 이용
    // - 시퀀스 타입을 사용
    var rdd1 = sc.parallelize(List("a", "b", "c", "d", "e"), 2)  // 두번째 매개변수로 생성하는 RDD의 파티션 수를 지정할 수 있음
    // 만약 "전체 요소 수 < 파티션 수" 라면, 생성된 파티션 중 일부는 요소가 하나도 없는 빈 파티션이 될 수 있다.

    println(rdd1.collect().mkString(", "))


    // 2. RDD 생성2: 파일, 데이터베이스 등의 외부 데이터를 읽어, 새로운 RDD를 생성
    // - 각 줄 하나가 RDD가 되며, 시작위치 정보는 무시하고 줄의 내용만을 사용해 RDD를 생성함
    val rdd2 = sc.textFile("/Users/kilhyunkim/program/spark-3.5.0/README.md")
    println(rdd2.collect().mkString(", "))

  }

}