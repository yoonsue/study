## 추상 클래스 인터페이스
* 추상 메소드: abstract로 표현된 서브클레스에서 구현해야하는 메소드
  
* 추상클래스: 추상 메소드가 있거나 없거나 
  * 상속시 (extends classA) - 다중 상속 불가
  * 내부에 추상 메소드가 있을경우 반드시 abstract로 표기해야함
  * 생성자를 가질수 있음
  * 추상 클래스를 상속받아서 기능을 이용하고 확장 시킴
* 인터페이스: 모든 메소드가 추상 메소드인 경우 
  * 상속시 
    * 인터페이스간 (extends classA, classB)
    * 구현클래스가 상속받을시 (implements class1, class2)
  * 인터페이스는 빈 메소드(껍데기)만 있음. 서브킅래스에서의 함수 구현을 강제시킴
  * static 변수만 가질수 있음
  
## static
keyword: 공유, 인스턴스 없이 접근가능
* static 변수
  * 메모리에 한번 할당되어 프로그램이 종료될 때 해제되는 변수
  * 여러 객체가 해당 메모리를 공유
* static 메소드
  * 객체의 생성(인스턴스) 없이 호출이 가능. 객체에서는 호출이 불가능
  * 대표예: java.util.Math
* static 클래스
  * inner class에 outer class의 인스턴스 생성 없이 사용하기 위해 붙임

## this
this 키워드: 현재 클래스 인스턴스
this() 메소드: 클래스에 정의된 생성자를 부를때

## super
super 키워드: 부모 클래스의 값을 가져온다
```java
public class Parent {
    int num=0;
}

public class Child extends Parent {
    int num=10;
    System.out.Println(super.num); // 10
}
```
super 메소드: 부모 클래스의 생성자를 호출
```java
super(); // 매개변수가 있는 부모 생성자의 경우는 적어줘야함
```