/**
 * packageName  : Spark.CH02
 * fileName     : RddFilterSortOperation
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

import org.apache.spark.{SparkConf, SparkContext}

object RddFilterSortOperation {

  def main(args: Array[String]): Unit = {
    val sc = getSparkContext()

    filterFunction(sc)
    sortByKeyFunction(sc)
    keysValuesFunction(sc)
    sampleFunction(sc)

    sc.stop()
  }

  def getSparkContext(): SparkContext = {
    val conf = new SparkConf()
      .setAppName("RddFilterSortOperationTest")
      .setMaster("local[*]")

    new SparkContext(conf)
  }

  def filterFunction(sc: SparkContext): Unit = {
    /*
     * filter()
     * - RDD 요소 중 원하는 요소만 남기는 동작을 하는 메소드
     * - 참과 거짓으로 가려내는 함수를 RDD의 각 요소에 적용해, 결과가 참인 것만 남기게 된다.
    */
    val rdd = sc.parallelize(1 to 5)
    val result = rdd.filter(_ > 2)

    println(result.collect.mkString(", "))
  }

  def sortByKeyFunction(sc: SparkContext): Unit = {
    /*
     * sortByKey()
     * - 키 값을 기준으로 요소를 정렬하는 연산
     * - 키 값을 기준으로 정렬하기 때문에, 모든 요소가 키와 값 형태로 구성돼 있어야 한다.
     * - 정렬 완료 후, 파티션 내부의 요소는 정렬 순서상 인접한 요소로 재구성됨
    */
    val rdd = sc.parallelize(List("q", "z", "a"))
    val result = rdd.map((_, 1)).sortByKey()

    println(result.collect.mkString(", "))
  }

  def keysValuesFunction(sc: SparkContext): Unit = {
    /*
     * keys(), values()
     * - RDD의 구성요소가 키와 값의 쌍으로 구성된 경우에 사용
     *   - keys(): 키에 해당하는 요소로 구성된 RDD를 생성
     *   - values(): 값에 해당하는 요소로 구성된 RDD를 생성
    */
    val rdd = sc.parallelize(List(("k1", "v1"), ("k2", "v2"), ("k3", "v3")))

    println(rdd.keys.collect.mkString(", "))
    println(rdd.values.collect.mkString(", "))
  }

  def sampleFunction(sc: SparkContext): Unit = {
    /*
     * sample()
     * - 샘플을 추출해 새로운 RDD를 생성할 수 있음
     * - 인자
     *   - withReplacement: 복원 추출을 수행할 지 여부를 정하는 것
     *   - fraction: 복원 추출 여부에 따라 의미가 다름 / 복원추출: 샘플 내 각 요소가 나타는 횟수의 기대값, 비복원추출: 각요소가 샘플에 포함될 확률
     *   - seed: 난수 생성
    */
    val rdd = sc.parallelize(1 to 100)

    val result1 = rdd.sample(false, 0.5)
    val result2 = rdd.sample(true, 1.5)

    println(result1.collect.mkString(", "))
    println(result2.collect.mkString(", "))
  }
}
