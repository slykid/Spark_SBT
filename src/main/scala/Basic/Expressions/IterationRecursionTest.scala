package Basic.Expressions

object IterationRecursionTest extends App {

    // 1. Iteration (순회)
    /*
     * for, while 구문과 같이 특정 구문에 대해 반복적인 작업을 수행함
     */
    def factorialIterative(n: Int): BigInt = {
        var result=BigInt(1)

        for(i <- 2 to n) {
            result *= i
        }

        result
    }

    // 2. Recursion (재귀)
    /*
     * 하나의 함수를 해당 함수 내에서 자신을 호출하는 방법
     */
    def factorialStackRecursive(n: Int): BigInt = {
        if(n <= 1) 1
        else n * factorialStackRecursive(n - 1)
    }

    // 3. Tail Recursion
    // - 기존의 재귀는 함수 안에 자신을 호출하는 구조를 갖고 있으며, 해당 호출 횟수가 많을수록 Stack 메모리를 많이 사용하게되고,
    //   Base Case에 도달하더라도, n * factorialStackRecursive(n - 1) 에 의해 한 번 더 연산을 수행해야한다.
    // - 위의 문제를 개선하기 위해, 함수에 Accumulator 작업횟수도 같이 넣어준다.
    //   이로 인해 최종적으로는 Base Case 에 대한 값만 반환하게 되고, 매 연산마다 데이터 값을 전달한다.
    def factorialTailRecursive(n: Int, accumulator: Int = 1): Int = {
        if (n <= 1) accumulator
        else factorialTailRecursive(n - 1, n * accumulator)
    }


}
