## IoC Contianer
 = Bean 팩토리(오브젝트의 생성과 오브젝트 사이의 런타임 관계를 설정하는 DI관점)
<br/>  = 애플리케이션 컨텍스트(DI를 위한 빈 팩토리에 개발에 필요한 여러가지 컨테이너 기능을 추가한 것)
* DI: 의존성 주읩
* IoC: 제어의 역전 - 컨테이너로 제어권 넘김
   
 
### 역할
1. BeanDefinition으로 만들어진 메타정보를 담은 오브젝트를 사용해 Bean을 생성하고 관리

* 아래와 같이 개발자가 직접 Application 인터페이스 구현 가능
```java
//GenericApplicationContext - JUnit 테스트에서 사용
    StaticApplicationContext ac = new StaticApplicationContext();
    ac.registerBeanDefinition("printer", StringPrinter.class);

    BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
    helloDef.getPropertyValues().addPropertyValue("name", "Spring");
    helloDef.getPropertyValues()
            .addPropertyValue("printer", new RuntimeBeanReference("printer");
    
    ac.registerBeanDefinition("hello", helloDef));

    Hello hello = ac.getBean("hello", Hello.class);
    hello.print();

    assertThat(ac.getBean("printer").toString(), is("Hello Spring");
```
* 대개 아래와 같이 설정파일을 만듬
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001.XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--Bean ID, class, scope, property, 생성자 파라미터, 지연된 로딩여부,
         우선 빈 여부, 자동 와이어링 여부, 부모 빈 정보, 빈팩토리 이름 등-->    
    <bean id="hello" class="springbook,learningtest.spring.ioc.bean.Hello">
        <property name="name" value="Spring" />
        <property name="printer" ref="printer" />
    </bean>
</beans>
```
1. 메타정보 읽음
2. 빈 오브젝트 생성
3. DI 작업(프로퍼티나 생성자를 통해 의존 오브젝트 주입)
4. DI로 연결된 오브젝트들이 모여 하나의 App 구성 및 동작

### IoC Container 계층구조
* 자기 자신 -> 직계부모 순으로 타고 올라감
* 일부 빈 구성을 바꾸고 싶은 경우
* 여러 애플리케이션 컨텍스트가 공유하는 설정을 만들기 위해 사용
