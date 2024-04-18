package Basic.OOP

class Book(var title: String, var author: String)
// 클래스에서 사용할 필드 선언 시, var, val 키워드에 따라 getter 와 setter를 암묵적으로 생성함
// - var: getter, setter 모두 생성
// - val: getter 만 생성
{
    // 기본 생성자 생성방법
    def this(title: String) = {
        this(title, "anonymous")
    }
}