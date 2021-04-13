## 1. 개론
> 좋은 코드를 보면 감탄이 나온다.  
명백하게 좋은 해답은 없다. 그렇지만 오답은 있다. [참고글](https://brunch.co.kr/@lkj28/92)
>> 나도 좋은 코드를 짜고 싶다  

Gang of Four (GoF)  
Robert C. Martin  
Martin Fowler  
Joshua Kerievsky  

### 1.0. OO concepts
- object : 객체란, 행동, 상태, unique한 식별성을 가진 entity
  - object는 class에 의해 생성되며(the process of instantiation), class에 의해 생성된 instance라고도 불린다.
- class : 비슷한 속성과 행위를 가진 object들의 집합체
  - interface: interface란, object에서 사용될수 있는 모든 사인(signature)이라고 볼수 있다.
    ```java
    // Icon Interface
    int getIconHeight()
    int getIconWidth()
    void paintIcon(Component c, Graphics g, int x, int y)
    ``` 
    - signature: 함수에 대한 명세(헤더). type of is return value, the operation's name, types of its parameters
    - type: 인터페이스 정의(이름) a name denoting only a particular interface
- inheritance : Superclass(Base Class) ⊃ Subclass(Derived Class)
  > General concept >> Specific concept  
  "is-a" relationship  
  >> - e.g. A Mammal is an Animal
  - ##### 상속의 이점
    - share interface(메소드 헤더)
      - interface inheritance: 메소드 헤더를 상속. 수퍼 클래스의 인터페이스를 수퍼타입, 서브 클래스의 인터페이스를 서브 타입이라 부른다.
    - share implementation(메소드 구현)
      - class inheritance(implementation inheritance): 메소드 구현을 상속 e.g. List ⊃ Stack, stack의 client들은 List의 인터페이스를 볼수 없다.
    - share both
  - #### 상속의 기본원리
    - LSP(Liskob Substitution Principle): is-a 의 역은 (대부분) 성립하지 않는다.
      - 성립하는 경우가, 이상한 경우라 생각한다. (아무것도 추가하지 않을거면 왜 만들었나..)
    - Favor object composition over class inheritance: Whitebox(내부 구현을 드러내냐) VS Blackbox(내부 구현을 드러내지 않느냐)
      - class inheritance에 관한 원칙
      - whitebox reuse
        - 장점: 직관적임. 재사용될때 수정하기 쉬움.
        - 단점: 런타임시에 내부 구현 변동불가. FRC(Fragile Base Class) 문제(수퍼 클래스를 마구잡이로 바꿔버리는..) 발생
      - black box reuse
        - 장점: 좀더 Flexible하게 구현할 수 있음. 계층 구조로 관리 가능.
        - 단점: 런타임 복잡도가 올라감. 내부 관계로 인해 이해하기는 조금더 힘듦
    - Program to an interface, not an implementation!: 'abstract' 에 대해 프로그래밍해라
      - interface 계층과 implementatin 계층을 구분해라
      - implementation variation과 type(interface) variation을 각각 만들고, composition aggregation 관계를 만들어라.
  - #### 상속시 고려해야할 사항
    - inheritance of function interface only: 메소드 헤더만 제공할지
    - inheritance of function default implementation: 기본 구현까지 제공할지
- polymorphism: 상속을 통해 클래스에 추가 기능들이 붙어갈 수 있는 것

#### OO의 advanced 원리
1. SRP(The Single Responsibility Principle): 하나의 메소드는 한가지 일만 잘하면 된다. --> 위반하면, rigidity 와 fragility가 깨짐
2. OCP(The Open Closed Principle): `abstraction`과 `polymorphism`
  > Open for extension, but closed for modification - Bertrand Meyer, 1988
  - 추가해야할 모듈의 행위가 있을 경우, 기존 코드를 수정하기보다 새로운 코드를 추가해라
4. LSP(The Liskov Substitution Principle): is-a 관계의 역은 성립하지 않는다.
5. DIP(The Dependency Inversion Principle): 모듈이 직접 concrete detail에 dependancy를 가지면 안되며, 상위 계층의 모듈과 하위계층의 모듈 모두 abstraction을 향해야 한다.
6. ISP(The Interface Segregation Principle): 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다. 큰 덩어리의 인터페이스들을 구체적이고 작은 단위들로 분리시킴으로써 클라이언트들이 꼭 필요한 메서드들만 이용할 수 있게 한다. [위키](https://ko.wikipedia.org/wiki/%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4_%EB%B6%84%EB%A6%AC_%EC%9B%90%EC%B9%99)

### 1.1. 나쁜 코드란 무엇인가
> 스파게티 코드: 뒤죽박죽이 되어서 팀원 누구도 코드를 제대로 이해할수 없게되는.. 결국 새로운 소프트웨어를 만드는...  
  (실패하는 회사의 소프트웨어 라이프 사이클)

#### 1.1.0. 나쁜 코드의 특성 (= 썩어가는 코드의 악취)
**"not" {reusable, flexible, extensible, and maintainable}**
- rigidity (엄격성)
  - 의존적인 모듈이 많아서 소프트웨어 변경이 쉽지 않은거
- fragile (파손이 쉬운)
  - 수정한 부분과 무관하게 다른데서 문제생긴다
- not reusable (재사용성 없는)
  - 객체 의존성이 점점 커져서 가져다 못쓰는거
- high viscostiy (끈끈한)
  - 요구사항 문서나 로직 구조가 변경될때, 변경사항을 받아들이는 과정에서 원래 코드가 잘 유지되지 않는거
- Needless Complexity (불필요한 복잡도)
- Needless Repetition (불필요한 반복 코드)
- Opacity (ignorance, negligence) (불투명성) `TODO: 아직 불명확함.`

#### 1.1.1 나쁜 코드 예제
```c
void copy(void)
{
  int ch;
  while ( ( ch = ReadKeyboard() ) != EOF )
    WritePrinter(ch);
}
```

Tape을 통해 읽는 기능을 추가해보자.
```c
bool GtapeReader = false; // remember to clear
void copy(void)
{
  int ch;
  while ( ( ch = GtapeReader ? ReadTape() : ReadKeyboard() ) != EOF )
    WritePrinter(ch);
}
```

좋은 설계
```c
#include <stdio.h>

void Copy( File* in, File* out ) {
  int ch;
  while ( ( ch = fgetc(in) ) != EOF ) {
    fputc( ch, out );
  }
}
```

#### 1.1.2 리팩토링이란?
> 나쁜 코드를 > 좋은 코드로 바꾸자

> 좋은 코드는 결국 이타적인 코드이다.  
이타적이라는 말에는 나중에 볼 나자신도 포함된다.

소프트웨어를 보다 잘 이해할 수 있도록 가독성을 향상시키고, 유지보수의 용이성과 재사용성을 높이기 위해서 행하는 코드 개선 활동

##### 기본적인 좋은 규칙들
- 1. 죽은 코드를 삭제한다
- 2. 함수나 변수명을 잘 짓는다
- 3. 함수나 메소드는 해당 기능만 수행하도록 한다.
- 4. 주석이 없어도, 코드만으로 이해할 수 있는 코드면 좋다
- 5. 코드 중복을 최대한 없애야한다
- 6. 모듈화와 계층화를 잘해야한다. (리아키텍처링)
- 7. 들여쓰기를 잘하자


### 1.2. 디자인 패턴이란?
소프트웨어 설계 방법론, 특정 상황에서 구조적인 문제를 해결하는 방식을 설명함 [위키](https://ko.wikipedia.org/wiki/%EB%94%94%EC%9E%90%EC%9D%B8_%ED%8C%A8%ED%84%B4#%EC%BB%B4%ED%93%A8%ED%84%B0_%EA%B3%BC%ED%95%99%EC%97%90%EC%84%9C%EC%9D%98_%EB%94%94%EC%9E%90%EC%9D%B8_%ED%8C%A8%ED%84%B4)

### 1.3. 왜 써야하는가?
좋은 코드를 만들기 위해

### 1.4. 언제, 어떻게 활용되는가?
특정 상황에서 구조적인 문제를 해결할 수 있다.

[위키](https://ko.wikipedia.org/wiki/%EB%94%94%EC%9E%90%EC%9D%B8_%ED%8C%A8%ED%84%B4_(%EC%B1%85))의 정리를 따왔다.
#### 생성 패턴(Creational Patterns)
- 추상 팩토리 패턴: 동일한 주제의 다른 팩토리를 묶어 준다.
- 빌더 패턴: 생성(construction)과 표기(representation)를 분리해 복잡한 객체를 생성한다
- 팩토리 메서드 패턴: 생성할 객체의 클래스를 국한하지 않고 객체를 생성한다.
- 프로토타입 패턴: 기존 객체를 복제함으로써 객체를 생성한다.
- 싱글턴 패턴: 한 클래스에 한 객체만 존재하도록 제한한다.

#### 구조 패턴(Structural Patterns)
- 어댑터 패턴: 인터페이스가 호환되지 않는 클래스들을 함께 이용할 수 있도록, 타 클래스의 인터페이스를 기존 인터페이스에 덧씌운다.
- 브리지 패턴: 추상화와 구현을 분리해 둘을 각각 따로 발전시킬 수 있다.
- 합성 패턴: 0개, 1개 혹은 그 이상의 객체를 묶어 하나의 객체로 이용할 수 있다.
- 데코레이터 패턴: 기존 객체의 매서드에 새로운 행동을 추가하거나 오버라이드 할 수 있다.
- 파사드 패턴: 많은 분량의 코드에 접근할 수 있는 단순한 인터페이스를 제공한다.
- 플라이웨이트 패턴: 다수의 유사한 객체를 생성·조작하는 비용을 절감할 수 있다.
- 프록시 패턴: 접근 조절, 비용 절감, 복잡도 감소를 위해 접근이 힘든 객체에 대한 대역을 제공한다.

#### 행동 패턴(Behavioral Patterns)
- 책임연쇄 패턴(Chain of responsibility): 책임들이 연결되어 있어 내가 책임을 못 질 것 같으면 다음 책임자에게 자동으로 넘어가는 구조
- 커맨드 패턴(Command Pattern: 위의 명령어를 각각 구현하는 것보다는 위 그림처럼 하나의 추상 클래스에 메서드를 하나 만들고 각 명령이 들어오면 그에 맞는 서브 클래스가 선택되어 실행하는 것
- 해석자 패턴 (Interpreter Pattern): 문법 규칙을 클래스화한 구조를 갖는SQL 언어나 통신 프로토콜 같은 것을 개발할 때 사용
- 반복자 패턴 (Iterator Pattern): 반복이 필요한 자료구조를 모두 동일한 인터페이스를 통해 접근할 수 있도록 메서드를 이용해 자료구조를 활용할 수 있도록 해준다.
- 옵저버 패턴: 어떤 클래스에 변화가 일어났을 때, 이를 감지하여 다른 클래스에 통보해주는 것
- 전략 패턴 (Strategy Pattern): 알고리즘 군을 정의하고 각각 하나의 클래스로 캡슐화한 다음, 필요할 때 서로 교환해서 사용할 수 있게 해준다.
- 템플릿 메서드 패턴 (Template method pattern): §상위 클래스에서는 추상적으로 표현하고 그 구체적인 내용은 하위 클래스에서 결정되는 디자인 패턴
- 방문자 패턴 (visitor Pattern): 각 클래스의 데이터 구조로부터 처리 기능을 분리하여 별도의 visitor 클래스로 만들어놓고 해당 클래스의 메서드가 각 클래스를 돌아다니며 특정 작업을 수행하도록 하는 것
- 중재자 패턴 (Mediator Pattern): 클래스간의 복잡한 상호작용을 캡슐화하여 한 클래스에 위임해서 처리 하는 디자인 패턴
- 상태 패턴 (State Pattern): 동일한 동작을 객체의 상태에 따라 다르게 처리해야 할 때 사용하는 디자인 패턴
- 기념품 패턴 (Memento Pattern): Ctrl + z 와 같은 undo 기능 개발할 때 유용한 디자인패턴. 클래스 설계 관점에서 객체의 정보를 저장
