# PATTERNS STUDY
아래 참고도서를 통해 고찰한 내용을 정리하는 레포지토리임.
### 참고도서
- [GoF의 디자인 패턴](http://www.yes24.com/Product/Goods/17525598)
- [Holub Design Patterns](https://www.holub.com/goodies/holub_design_patterns.pdf)

### 기호표
| 기호 | 표기 |
|:---:|:---:|
|<i>italic</i>|추후 다시 확인할것|
|<b>bold</b>| 강조|
|<u>underline</u>| 불확실, 추측 |

# Patterns

## 1. Abstract Factory Pattern
- [abstract_factory](http://www.github.com/yoonsue/)
* 제품을 생성하기 위한 인터페이스
* 일반화된 클래스(부모 클래스)가 더 상세한 클래스(서브클래스)를 묶고, 이 일반화된 클래스를 통해 클라이언트는 접근함. 
* 세부 구현은 상세한 클래스(서브 클래스)에서 이루어짐.
* 사용 예
  * 다양한 시스템에서 사용될때(시스템 독립적)
  * 구현 내용이 아닌 인터페이스를 노출시킬때

* 유의
  * <i>Builder: 실체를 만들 때 build 되는 내용에 대해 전혀 모르는 것
  * Factory Method: 서브클래스가 override하는 abstract method </i>

## 2. Builder
- [Builder](http://www.github.com/yoonsue/)
* 복잡한 객체를 한단계씩 실행시키며 생성함. 마지막 단계에서 객체를 반환함.
* 각각 독립된 여러 단계를 거치며, 특정 단계의 선택폭(프로토콜, representation 방법)이 넓을 때 사용하면 좋을 듯함.
* 사용 예
  * RTF(서식있는 텍스트 파일) 리더
  
## 3. Factory Method
- [Factory Method](http://www.github.com/yoonsue/)
* 
 
* 유의
  * Factory Method: 하나의 메소드가 여러 종류의 객체 생성 (미리 선지정하는 느낌)
  * Abstract Factory Method: 내부에 (보통 팩토리 메소드를 사용해서) 여러 객체를 생성