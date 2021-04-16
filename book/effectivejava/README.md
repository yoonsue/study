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

## volatile
java 변수를 main memory에 저장함
* multi thread 환경에서 thread가 각각 CPU cache에 저장된 값을 읽어오면 변수 값 불일치 문제가 발생

## native
java가 아닌 다른 언어로 구현한 후 자바에서 사용하려고 할 때 이용하는 키워드
* JNI(Java Native Interface): JDK의 일부분으로 코드가 모든 플랫폼 상에서 완벽하게 이식되도록 함

## transient
Serialize 하는 과정에서 제외하고 싶은 경우 선언하는 키워드

## 제네릭 타입
타입 변환이 귀찮아서
```java
public class classA<T> { ... }                  // 제네릭 클래스
public interface interfaceB<T> { ... }          // 제네릭 인터페이스
public <T> ReturnType<T> methodC(T t) { ... }   // 제네릭 메소드
// <?> --> 와일드 카드 타입
// <T extends 상위타입> --> 상위 클래스 제한
// <? super 하위타입> --> 하위 클래스 제한
// Map.Entry<K,V>
```

## Hibernate
[hibernate-start](https://www.slideshare.net/visualkhh/hibernate-start)

[Application - [Hibernate - [JPA - [JDBC API]]]] <-> DB

자바 언어를 위한 ORM Framework.

객체 지향 도메인 모델을 관계형 데이터베이스로 매핑하기 위한 프레임 워크를 제공함

(기존 SQL 중심적 개발시 DTO 객체 변경, mapping이 번거롭기 때문에 나옴) 
* ORM: Object Relational Mapping, 객체와 DB 테이블이 매핑을 이루는 것
* JPA: Java Persistent API, ORM을 사용하기 위한 인터페이스를 모아둔 것

## `implements` VS `extends`
https://velog.io/@hkoo9329/%EC%9E%90%EB%B0%94-extends-implements-%EC%B0%A8%EC%9D%B4
| 구분 | `implements` | `extends` | abstract
| --- | --- | --- | --- |
| 부모에서 | 선언만 | 선언과 정의 모두 | extends와 interface 혼합 |
| 상속 | 인터페이스 | 일반 및 abstract 클래스 | - |
|  | interface 내 모든 메소드 `@Override`(재정의) 필수 | `@Override` 없이 부모의 메소드 가져다 쓸 수 있음 | - |

### 가능한 경우
- 인터페이스간 확장 : `interfaceA extends interfaceB`
- 클래스간 확장(한번에 한 클래스만 확장 가능) : `classA extends classB`, `classC extend classA` 하면 `classC`는 `classB`의 메소드 사용가능
- 인터페이스 구현(모든 메소드): `classA implements interfaceA, interfaceB`
