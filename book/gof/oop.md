OOP에 대해 정리하는 문서
- 번역글: https://wikidocs.net/8233
- 원글: [Tony Marston's Blog](https://www.tonymarston.net/php-mysql/what-is-oop.html)

### What OOP is NOT
- 실제 세계 모델링 에 관한 것이 아니다
  - 실세계는 너무 광범위하고 난해하기 때문에 이를 모델링하는 건 전혀 실용적이지 않다. (전혀 무관할 수 있다)
  - OOP는 단순히, 현재 APP의 필요한 부분을 모델링하고, 정의한 추상화가 옳고 그른지 판별해나가는 과정일 뿐이다.
- OOP는 코드 재사용/모듈성/연결가능성에 관한 것이 아니다
  - 객체 지향이 아니더라도 재사용성이 있고 모듈성있는 코드는 얼마든지 짤 수 있다.
  - 이는 OOP에 국한되지 않는다. non-OOP또한 가능하다.
- OOP는 구현 숨기기(캡슐화)를 위해 사용하는 것이 아니다
  - 이는 단순히 캡슐화에 부산물일 뿐이다.
- OOP는 정보 은닉을 위해 사용하는 것이 아니다.
  - getter/setter를 사용해 정보에 접근해야 한다는 견해가 있는 반면, 직접 접근해도 된다는 견해도 존재한다.
    - [Why getter and setter methods are evil] and [Getters/Setters. Evil. Period.]
- OOP는 메세지 전달에 관한 것이 아니다.
  - 실제 메세싱 소프트웨어는 다음과 같이 동작한다
  - 메세지가 처리될때까지 클라이언트는 다른일을 할수있다(비동기를 지원한다).
    - 메세지 대기열 에 enqueue
    - 순서 대기
    - 순서가 될시 처리
    - 처리 결과 return
  - 그러나, OOP의 메소드 활성화는 항상 동기적으로 동작한다.
- OOP는 책임 분리에 대한 것이 아니다
  - 이는 단순히 모듈 구현 방식에 따라 달라진다.
  - 이는 OOP에 국한되지 않는다. non-OOP또한 가능하다.
- OOP는 배우기 쉬운 것은 아니다
- OOP는 행위자와 행위에 관한 것이 아니다.
  - 모호한 설명이다.
- OOP는 late binding에 관한 것이 아니다
  - late binding: (어떤 바이너리를 로드할 지, 어떤 함수를 호출할 지)이 컴파일 시점에(일찍) 내리기 보다는 함수가 호출되기 바로 직전까지 가능한 오래 지연하는 것 
    (TODO: 상세: https://en.wikipedia.org/wiki/Late_binding)
  - 이는 OOP에 국한되지 않는다. non-OOP또한 가능하다.

---

### What is an Object Oriented Language?
클래스는 정의한 클래스에 대한 데이터/속성과 메소드(함수 도는 연산)을 모두 정의한다.
해당 실체에 적용될 수 있는 속성이나 메소드가 해당 클래스 정의 외부에 존재해서는 안된다.
- 1. **class**: blueprint / prototype
- 2. **object**: class' instance
  - 한번에 동일한 클래스의 인스턴스가 두개 이상 존재할 수 있다.
- 3. **encapsulation**: 데이터와 그 데이터에서 수행하는 연산을 동일한 클래스에 배치하는 행위
  - 캡슐/컨테이너: 모든 **속성**과 모든 **메서드**가 똑같은 **클래스**에 존재
  - TODO: [What Encapsulation is not](https://www.tonymarston.net/php-mysql/what-is-oop.html#encapsulation.is.not)
- 4. **inheritance**: 파생 클래스(서브 클래스)를 형성하기 위해 기본 클래스(수퍼 클래스)를 재사용하는 것
  - TODO: [What Inheritance is not](https://wikidocs.net/8258)
- 5. **polymorphism**: 동일한 인터페이스지만, 다른 구현. 한 클래스를 다른 클래스로 대체할 수 잇는 능력
  - TODO: [What Polymorphism is not](https://www.tonymarston.net/php-mysql/what-is-oop.html#polymorphism.is.not)

---

### What OOP is
**객체를 지향하는 프로그래밍**
- 코드 재사용성을 높이고,
- 코드 유지보수비용을 낮추기 위해, 캡슐화, 다형성, 그리고 상속의 잇점을 취하는 것

1. 추상화 - 클래스와 객체의 형태를 제공
2. 상속 - 기존 추상화에서 새로운 추상화를 구축할 수 있는 기능을 제공
3. 런타임 다형성 - 일종의 런타임 바인딩을 제공

** 강력한 응집력, 느슨한 커플링, 그리고 중복 문제를 제거하기 위해 캡슐화, 상속, 그리고 다형성의 개념을 어떻게 구현하는가 **
- 구현이 효과적인 것을 검증하는 것은 이전 패러다임과 비교했을때 재사용 가능성을 높이고 유지관리 가능성을 줄인 코드를 생성할 수 있는 능력이다.

##### Optional Extras
- Visibility (public/protected/private) - 단순히 개발자에게 제한을 가할 뿐이다
- Accessors (Getter) and Mutators (Setters)
  - (다형성의 대표적인 예) 패이지 컨트롤러는 배열의 모든 열 이름을 알 필요가 없으니 각 컨트롤러는 단일 개체에 묶이지 않고,
    임의의 테이블 객체에 재사용할 수 있다
- Overloading - 정적으로 형식화된 언어는 다양한 유형으로 제공될 수 있는 인수를 다루기 위해 오버로드가 필요하지만, 동적으로 형식화되
- Interfaces - 일부 언어는 메소드와 분리된 인터페이스가 필요하다
- Exceptions - 예외처리
- Traits - TODO
- Design Patterns - 
- Object Oriented Design
  - Entity-Relationship Impedance Mismatch : 비정규화된 데이터베이스, 정규화 후 소프트웨어 구조와 일치하지 않는 데이터베이스 구조
    -> 이를 해결하기 위해 ORM(Object Relational Mapper)을 주로 사용함
