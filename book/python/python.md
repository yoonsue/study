# 4. 파이썬과 다른 언어와의 비교

python 본연의 이디엄을 사용하면 보다 가독성이 높고 효율적인 코드를 작성할 수 있음

* 파이썬의 중요한 피쳐 몇 가지
  * 잘 알려진 문제를 관습적인 방법으로 해결해보기
* 다른 프로그래밍 언어와 비교
* 쉽게 빠질 수 있는 함정

* 클래스 모델과 객체 지향 프로그래밍
* 동적 다형성
* 데이터 클래스
* 함수형 프로그래밍
* 열거(Enumeration)

타 언어의 개발자
```python
for index in range(len(some_list)):
    print(some_list[index])
```

python 개발자
```python
for item in some_list:
    print(item)
```

## 4.2 클래스 모델과 객체 지향 프로그래밍

| 객체지향언어 타입 | 설명 | 예시 |
| --- | --- | --- |
| 동적 | 컴파일 시 자료형을 정하는 것이 아니라 런타임 시 결정 | python,JavaScript, Ruby 등 |
| 정적 | 컴파일 시 변수의 타입이 결정되는 언어 | Java, C, C++, C# 등 |
> 출처: https://devuna.tistory.com/82

객체 지향 프로그래밍(Object-oriented programming, OOP) : 데이터를 캡슐화(객체 속성)한 객체와 행동(메서드)에 중심을 둠

OOP 언어들은 클래스 조합 또는 상속 같은 수단을 통해 코드 재사용, 확장성(extensibility), 모듈성
 (modularity)를 촉진

python 도 subclassing 지원

Kotlin 과 유사함
| 구분 | python | Kotlin |
| --- | --- | --- |
| 슈퍼클래스(super-class) 메서드의 쉬운 호출 | super() 함수 | Super 키워드 |
| 객체 자기 참조(object self-reference) 표현식 | 첫 번째 인수가 항상 인스턴스 참조. 관습적으로 self 라는 이름을 사용 | this 표현식 |
| 데이터 클래스의 생성 지원 | syntacitc sugar 로 데이터 클래스 지원 | 좌동 |
| property 개념 | property() 데커레이터. discriptor 개념과 함께 객체에 대한 속성 접근을 완전히 커스터마이즈할 수 있음 | 클래스 프로퍼티 setter와 getter 함수 |

> syntatic suger?  
가독성 혹은 쉬운 표현을 위해 고안된 문법    
    - `1 < x < 10`  
    - List Comprehension  
    - Context Manager `with ...:`  
    - Decorators  
https://www.reddit.com/r/Python/comments/c4m1lm/whats_your_favorite_syntactic_sugar_in_python/  
https://www.codeit.kr/community/threads/19376

> python property
프로퍼티 어트리뷰트를 반환
https://docs.python.org/ko/3/library/functions.html#property
```python
# 시그니처
class property(fget=None, fset=None, fdel=None, doc=None)

# 실사용예
class C:
    def __init__(self):
        self._x = None

    # fget함수와 doc 지정
    @property
    def x(self):
        """I'm the 'x' property."""
        return self._x

    @x.setter
    def x(self, value):
        self._x = value

    @x.deleter
    def x(self):
        del self._x
```

> python descriptor란?  
객체가 어트리뷰트 조회, 저장 및 삭제를 사용자 정의 할 수 있도록 합니다.
https://docs.python.org/ko/3/howto/descriptor.html
  
### 4.2.1 슈퍼클래스로의 접근

python 에서 OOP 구현 -> 클래스와 서브클래싱 개념을 기반으로 함

> 서브클래싱?  
이미 존재하는 클래스를 상속하거나 그 동작을 특별하게 설정해 쉽게 재사용하는 방법

서브클래스는 대부분 기본 클래스의 동작에 의존하지만, 추가 메소드를 이용해 동작을 확장하거나 동작의 정의를 overriding함으로써 기존 메서드의 구현을 완전히 새롭게 바꾸기도 함

super() 함수 : 서브 클래스 내부에서 기존 클래스의 원래 구현에 대한 접근 없이 메서드를 완전히 재작성하지 않게 하여, 코드 재사용성을 높임

```python
class SubClass(OriginClass):
    def __setitem__(self, key: str, value: Any):
        return super().__setitem__(key.lower(), value)
```

### 4.2.2 다중 상속과 메서드 결정 순서

python 의 MRO는 C3 슈퍼클래스 선형화에 기반함

> MRO?  
Method Resolution Order. 다중 상
https://tibetsandfox.tistory.com/26

```python
class Human:
    def say(self):
        print("안녕")

class Mother(Human):
    def say(self):
        print("엄마")

class Father(Human):
    def say(self):
        print("아빠")

class SonA(Mother, Father):
    def say(self):
        print("응애")

class SonB(Mother, Father):
    pass

# C3 선형화 계산 결과 출력
print(SonA.__mro__)
# (<class '__main__.SonA'>, <class '__main__.Mother'>, <class '__main__.Father'>, <class '__main__.Human'>, <class 'object'>)

babyA = SonA()
babyA.say()
# 응애

babyB = SonB()
babyB.say()
# 엄마
```

### 4.2.3 클래스 인스턴스 초기화

__init__() 메서드에서 객체 속성을 선언하고 값을 할당하는 것이 표준

```python
class Point:
    # bad case(code smell) -> init 에 의해 항상 가려짐
    # 클래스 속성이 타입 인스턴스가 아닌 타입 객체에 할당된 경우.
    # x = 0
    # y = 0

    # 클래스 속성 어노테이션
    # https://kimjingo.tistory.com/182
    x: int
    y: int

    def __init__(self, x, y):
        self.x = x
        self.y = y

Point.__annotations__
# {'x': <class 'int'>, 'y': <class 'int'>}
```

> code smell?  
보다 깊은 문제를 야기할 수 있는 코드의 특성. 

### 4.2.4 속성 접근 패턴

다른 정적 타입 객체 지향 언어의 클래스 멤버 표현
- 클래스 외부로부터 객체 속성에 제한적 혹은 무제한적으로 접근하는 방법

| 표현 | d |
| --- | --- |
| public | |
| private | |
| prtected | |

python 에서는?
private/protected와 유사한 기능으로 naming mangling이라는 feature 지원
-> 클래스 바디 안에서 속성을 접두어 __로 정의하면, 해당 속성은 인터프리터가 실시간으로 이름을 변경함
-> 완전히 해당 속성에 대해 접근 하지 못하도록 제한하지 않음
-> 이름 충돌을 암묵적으로 회피하기 위해 만들어짐


```python
class MyClass:
    def __init__(self):
        # 외부에 직접 공개 / 오버라이딩을 막고 싶은 속성
        self.__secret_value = 1

instance = MyClass()
instance.__secret_value
# AttributeError: 'MyClass' object has no attribute '__secret_value'

instance._MyClass__secret_value
# 1

print(dir(instance))
# ['_MyClass__secret_value', '__class__', '__delattr__', '__dict__', '__dir__', '__doc__', '__eq__', '__format__', '__ge__', '__getattribute__', '__gt__', '__hash__', '__init__', '__init_subclass__', '__le__', '__lt__', '__module__', '__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__', '__weakref__']
# https://tibetsandfox.tistory.com/21
```

> `__` : dunder?  
__ 로 시작하는 패턴  
dunder method(`__<>__`) in python : https://www.tutorialsteacher.com/python/magic-methods-in-python

### 4.2.5 디스크립터

다른 클래스의 속성에 접근하는 방법을 정의하는 객체
파이썬에서의 복잡한 속성 접근의 기본
내부적으로 프로퍼티, 메서드, 클래스 메서드, 스태틱 메서드, 슈퍼 메서드를 구현할 때 사용

디스크립터 프로토콜
* `__set__`(self, obj, value)
* `__get__`(self, obj, owner=None)
* `__delete__`(self, obj)

데이터 디스크립터: `__get__`, `__set__` 구현  
비데이터 디스크립터: `__get__`만 구현

instance.attribute 와 같은 점 표기법에서 속성을 검색하는법
1. 데이터 디스크립터
1. `__dict__` 룩업
1. 비데이터 디스크립터

* `__getattribute__()` : 모든 속성 룩업 메서드. instance.attribute 와 같은 점 표기법

_120p, 코드 추가_

파이썬은 디스크립터 프로토콜을 이용해 클래스 함수를 인스턴스에게 메서드로 바인드함

디스크립터는 classmethod 와 staticmethod 데커레이터 메커니즘의 기반이 됨. 실제로 해당 함수 객체가 비데이터 객체이기 때문

```python
>>> hasattr(instance, '__set__')
False
```

> monkey patching?  
런타임에 클래스 인스턴스를 동적으로 수정하는 기법. 정의나 소스코드에 손대지 않고도 속성을 추가, 수정, 삭제할 수 있음

_p123, lazy\_property 코드. 잘이해안됨_
* 객체 내에서 클래스 속성으로 객체 인스턴스를 가지는 경우
* 임포트 시점에 초기화되어서 안될 경우

https://stackoverflow.com/questions/24704147/what-is-a-lazy-property

### 4.2.6 프로퍼티

_p127 코드_

내장 디스크립터 타입 제공

생성된 속성은 현재 클래스의 메서드를 이용해 사용될 때 만들어지며, 파생된 클래스에서 오버라이드된 메서드는 사용하지 않음
-> 프로퍼티의 행동 일부만 오버라이딩 하는 것은 권장하지 않음. 파생된 클래스의 모든 프로퍼티메서드를 재작성할 것을 권장.

## 4.3 동적 다형성

다형성(polymorphism): 객체의 인터페이스를 그 타입으로부터 추상화함

정적 타입 언어에서는 일반적으로 다음을 통해 다형성을 달성함
- 서브타이핑(subtyping)
- 암묵적 인터페이스(implicit interface): 모든 타입은 기대하는 인터페이스에서 타입 A의 인터페이스를  이용할 수 있음. 단, 해당 타입이 타입 A와 동알한 메서드를 구현해야함. 인터페이스 선언은 여전히 명시적으로 정의해야하지만 서브클래스/서브타입은 인터페이스를 정의하기 위해 기본 클래스/타입을 명시적으로 속성하지 않아도 됨

**implicit interface in golang**
```golang
package main

import "fmt"

type animal interface {
    breathe()
    walk()
}

type lion struct {
    age int
}

func (l lion) breathe() {
    fmt.Println("Lion breathes")
}

func (l lion) walk() {
    fmt.Println("Lion walk")
}

func main() {
    var a animal
    a = lion{age: 10}
    a.breathe()
    // Lion breathes
    a.walk()
    // Lion walk
}
```

파이썬은 동적 타입 언어 -> 보다 느슨한 다형성 매커니즘 이용. 이를 Duck Typing 이라고 함

> Duck Typing?  
오리처럼 걷고, 오리처럼 운다면, 그것은 틀림없이 오리다.

**Duck Typing in Python**
```python
class Duck:
    def sound(self):
        print("꽥꽥")

class Dog:
    def sound(self):
        print("멍멍")

class Rock:
    pass

# 주어진 콘텍스트에서 사용될 수 있는 모든 객체
def get_sound(animal):
    # 콘텍스트가 기대하는 대로 작동하고 행동해야함
    animal.sound()

def main():
    bird = Duck()
    dog = Dog()
    rock = Rock()
    
    get_sound(bird)
    # 꽥꽥
    get_sound(dog)
    # 멍멍
    get_sound(rock)
    # AttributeError: 'Rock' object has no attribute 'sound'
```

단점: 타입이나 인터페이스를 강제하지 않기 때문에, 코드를 실행한 후에만 코드의 정확성을 검증할 수 있음  
-> 테스팅의 중요성

|  | golang의 implicit interface | python 의 duck typing|
| --- | --- | --- |
| 콘텍스트가 기대하는 대로 작동하고 행동해야 함 | O | O |
| 함수 인수로 기대되는 인터페이스에 선언 | 필요 | 불필요 |

### 4.3.1 연산자 오버로딩

#### 던더 메서드(언어 프로토콜)

`__`(double underscore) 로 시작하는 메서드

python 에서 사용할 수 있는 모든 연산자는 자체 프로토콜을 가지고 있으며, 해당 프로토콜의 던더 메서드를 구현해서 오버로딩함

크게 5 그룹으로 나눌 수 있음
* arithmetic operator(산술연산자)
* in-place assignment operator(제자리 할당 연산자)
* 비교 연산자
* 식별 연산자
* 비트와이즈 연산자

| 프로토콜명 | 메서드 | 설명 |
| --- | --- | --- |
| callable | `__call__()` | ()을 이용해 객체 호출 가능<br>`instance()` |
| descriptor | `__set__(), __get__(), __del__()` | 클래스 속성 접근 패턴 조작 |
| container | `__contains__()` | 키워드를 이용해 객체가 특정한 값을 포함하고 있는지 테스트 가능<br>`value in instance` |
| iterable | `__iter__()` | for 키워드를 이용해 객체 반복 가능<br>`for value in instance` |
| sequence | `__getitem__(), __len__()` | 대괄호 구문을 이용해 객체를 인덱스로 참조할 수 있고, 내장 함수를 이용해 객체 길이를 확인할 수 있음<br>`item = instance[index]`<br>`length = len(instance)` |

> 더 많은 dunder method는 아래에서..  
https://docs.python.org/ko/3/reference/datamodel.html#the-standard-type-hierarchy

#### 파이썬과 C++

클래스와 클래스 상속으로 연산자를 오버로딩함

| | C++ | python |
| --- | --- | --- |
| 주요 매커니즘 | 완전한 서브타이핑 | 좌동 | 
| | 애드훅 다형성(ad hoc polymorphism) | |
| | 함수 오버로딩을 이용해 동일한 함수를 입력 인수에 따라 여럿으로 구현할 수 있음 |
| | 자유 함수를 통해 연산자 오버로딩 | 한 피연산자의 던더 메서드로 오버로딩 |

_p138, 코드 보면 왼쪽 피연산자에 해당해서 TypeError 발생_

#### 연산자 오버로딩의 위험성과 복잡성
피연산자에 대해 대안적인 오버로딩 연산자 구현을 제공 -> 기존 연산자의 명확한 의미가 상실되었음을 의미

### 4.3.2 함수 및 메서드 오버로딩

python은 연산자 오버로딩은 제공하지만, 실제로 함수와 메서드 오버로딩은 지원하지 않음  
-> 한 모듈 안에서 같은 이름을 사용하는 여러 함수를 정의하면, 뒤쪽의 정의가 앞쪽의 모든 정의를 감춘다

일반적인 함수 오버로딩 방법
- 함수의 항수(매개변수의 수): 두 함수의 매개변수 수가 다르다면, 이 함수들은 같은 이름을 공유할 수 있음
- 매개변수 타입: 두 함수의 시그니처가 다른 타입의 매개변수를 요구한다면, 이 함수들은 같은 이름을 공유할 수 있음

python 에서의 함수 오버로딩
- 메서드/서브클래싱을 이용: 함수가 매개변수 타입을 구분하도록 하는 대신 해당 타입의 매서드로 정의함으로써 특정한 타입과 연결
- 인수와 키워드 인수 언패킹 이용: *args와 **kwargs 패턴을 통해 여러 인수를 함수의 시그니처에 사용할 수 있는 기능 제공 (가변 함수)
- 타입 체킹 이용: isinstance() 함수를 이용해 입력 인수를 특정 타입 및 베이스 클래스와 비교한 뒤 처리함

#### 싱글-디스패치 함수

지원해야할 타입의 수가 많아, `if isinstance(...)` 가 과도하게 많아질 경우 사용

```python
from functools import singledispatch
from datetime import datetime

@singledispatch
def report(value):
    return f"raw: {value}"

@report.register
def _(value: datetime):
    return f"dt: {value.isoformat()}"

@report.register
def _(value: complex):
    return f"complex: {value.real}{value.imag:+}j"

print(report('test'))
# raw: test
print(report(datetime.now()))
# dt: 2022-09-20T01:29:18.235630
print(report(100-30j))
# complex: 100.0-30.0j

print(report.registry)
# {<class 'object'>: <function report at 0x10339b2e0>, <class 'datetime.datetime'>: <function _ at 0x103471630>, <class 'complex'>: <function _ at 0x1034715a0>}
```

> `_`  
: 명시적으로 사용되지 않아야하는 객체의 이름에 관습적으로 사용

-> singledispatch 데코레이터가 단일 인자를 받기 때문에, class 내부에서 사용하려면 아래와 같이 singledispatchmethod 를 사용하면 됨

```python
from functools import singledispatchmethod

class Example:
    @singledispatchmethod
    def method(self, argument):
        pass

    @method.register
    def _(self, argument: float):
        pass
```

## 4.4 데이터 클래스

클래스 코드를 짧게 줄이기 위해 사용.  
속성 애너테이션을 기반으로 `__init__(), __repr__(), __eq__()` 메서드를 자동으로 생성

커스터마이즈된 **복잡한 초기화 상태를 요구한다면 `__init__()` 메서드를 이용해 기본적인 초기화 방법을 사용**하는 것이 바람직함

> 그 외 dataclass 의 다양한 옵션(유용한 피처 제공)
> https://docs.python.org/ko/3/library/dataclasses.html#dataclasses.dataclass

```python
from dataclasses import dataclass, field

@dataclass
class Vector:
    x: int
    y: int

    def __add__(self, other):
        pass

    def __sub__(self, other):
        pass

# 클래스 인스턴스를 불변하게 만듦(클래스 속성 수정 불가)
@dataclass(frozen=true)
class FrozenVector:
    x: int
    y: int

@dataclass
class DataClassWithDefaults:
    immutable: str = field(default="this is static default value")
    mutable: list = field(default_factory=list)
```

> field 에 대해 더 자세히 알고 싶다면?
> https://docs.python.org/ko/3/library/dataclasses.html#dataclasses.field

## 4.5 함수형 프로그래밍

함수형 프로그래밍 기본 용어
| 용어 | 설명 | 예시 |
| --- | --- | --- |
| side effect | 함수를 호출한 결과로 발생되는 함수 바깥에서 관찰할 수 있는 모든 변경 | 글로별 변수의 수정, 함수 범위 바깥에서 이루어지는 객체의 속성 변경, 외부 서비스로의 데이터 저장 |
| 참조 투명성 | 함수 또는 표현식이 참조적으로 투명하면, 이를 그 출력값에 해당하는 값으로 바꾸어도 프로그램의 동작을 변경하지 않음 | datetime.now() -> 생성자 메서드는 부작용이 없지만, 호출된 시점에 따라 다른 값을 반환하기 때문에 참조적으로 불투명 | 
| pure function | 순수 함수는 부작용을 갖지 않으며 같은 입력에 대해 항상 같은 값을 반환하는 함수 | 모든 수학적 함수는 순수함수. 외부 세계에 대해 자신의 실행 결과를 남기는 함수는 순수 함수가 아님 |
| 1급 함수 | 어떤 언어의 함수가 다른 값 또는 엔티티로서 취급되면 이 언어는 1급 함수를 가지고 있다고 부름. 1급 함수들은 다른 함수에 인수로 전달될 수 있고, 함수 반환값으로 반환되며 변수에 할당될 수 있음. | 파이썬의 함수들 |

python 은 순수한 함수형 프로그래밍 언어가 아니지만, 순수한 함수형 언어에서만 사용할 수 있었던 많은 피처들을 제공함
* 람다 함수와 1급 함수
* map(), filter(), reduce() 함수
* 부분 객체 및 부분 함수
* 제너레이터 및 제너레이터 표현식

### 4.5.1 람다 함수

익명 함수라고도 불리며, 어떤 식별자에도 바인드 될 필요가 없음

```py
import math

def circle_area(radius):
    return math.pi * radius ** 2

circle_area_lambda = lambda radius: math.pi * radius ** 2

print(circle_area(22))
print(circle_area.__class__)
print(circle_area.__name__)
# 1520.53084433746
# <class 'function'>
# circle_area

print(circle_area_lambda(22))
print(circle_area_lambda.__class__)
print(circle_area_lambda.__name__)
# 1520.53084433746
# <class 'function'>
# <lambda>
```

주로 사용되는 예
```py
from dataclasses import dataclass

@dataclass
class Person:
    age: int
    name: str

people = []
personA = Person(age=1, name='A')
personB = Person(age=3, name='B')
personC = Person(age=2, name='C')
people.append(personA)
people.append(personB)
people.append(personC)

print(people)
# [Person(age=1, name='A'), Person(age=3, name='B'), Person(age=2, name='C')]

print(sorted(people, key=lambda person: person.age))
# [Person(age=1, name='A'), Person(age=2, name='C'), Person(age=3, name='B')]
```

### 4.5.2 map(), filter(), reduce() 함수

#### map()

func 함수 인수를 모든 이터러블 아이템에 적용

```py
# 시그니처
# map(func, iterable, ...)

list(map(lambda x: x**2, range(10)))
# [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
```

#### filter()

입력된 요소를 하나씨 차례로 평가

```py
# 시그니처
# filter(func, iterable)

evens = filter(lambda number: number % 2 == 0, range(10))
print(list(evens))
# [0, 2, 4, 6, 8]
```

#### reduce()

이터러블을 하나의 값으로 줄임

```py
# 시그니처
# reduce(func, iterable)
# reduce(func, [a, b, c, d])
# === func(func(func(a,b), c), d)

print(reduce(lambda a, b: a + b, [2, 2, 2]))
# 6
```

### 4.5.3 부분 객체와 부분 함수

부분 함수: 가능한 모든 입력값의 범위(도메인)를 그 결과로 매핑되도록 강제하지 않는 방법으로 수학적인 함수를 일반화한 것

python에서는 부분 객체를 이용해서 주어진 함수의 일부 인수를 고정값으로 설정해 가능한 입력 범위를 슬라이싱함

```py
# 시그니처
# partial(func, *args, **keywords)

from functools import partial

powers_of_2 = partial(pow, 2)
```

### 4.5.4 제너레이터

요소의 수열을 반환하는 함수를 간단하고 효율적으로 만드는 우아한 방법을 제공

```py
def fibonacci():
    a, b = 0, 1
    while True:
        # 호출될 떄마다 b 반환
        yield b
        a, b = b, a + b

for item in fibonacci():
    print(item)
    if item > 10:
        break
```

### 4.5.5 제너레이터 표현식

딕셔너리, 집합, 리스트 리터럴에서 사용되는 컴프리헨션과 유사함

```py
(item for item in iterable_expression)
```

### 4.5.6 데커레이터

일반적으로 호출 가능한(`__call__` 메서드가 구현된 모든 객체) 표현식으로, 호출 시 하나의 인수를 받아 호출 가능한 다른 객체(`__call__` 메서드를 구현한 보다 복잡한 클래스의 인스턴스)를 반환함

주요 유스케이스: 추가적인 행동과 함께 기존 함수 구현을 개선

```py
@some_decorator
def decorated_function():
    pass

# same as bellow
decorated_function = some_decorater(decorated_function)
```

```py
# Flask framework
@app.route('/secret_page')
@login_required
def secret_page():
    pass
```

> 8장에서 더 자세히..

## 4.6 열거형(Enumerated type)

유한한 개수의 이름을 가진 값으로 구성됨
한정된 값들의 집합을 변수 또는 함수 인수로 인코딩할 때 유용. 혹은 bit 플래그, 마스크 연산에 의한 계산

```py
from enum import Enum

class Weekday(Enum):
    MON = 0
    TUE = 1
    WED = 2
    THU = 3
```
