# Go 마스터링하기 9장 - Go 언어의 동시성 - Go 루틴, 채널, 파이프라인

* 프로세스, 스레드, Go 루틴의 차이점
* Go 스케줄러
* 동시성과 병렬성
* go 루틴 생성 방법
* 채널 생성 방법
* 채널에서 데이터를 읽거나 받는 방법
* 채널에서 데이터를 쓰거나 보내는 방법
* 파이프라인 생성 방법
* Go 루틴이 끝날 때까지 기다리기

## Go 언어의 동시성 지원

- GO 루틴: Go 프로그램에서 독립적으로 실행할 수 있는 최소 단위
- 채널: Go 루틴끼리 통신하기 위한 참조점. Go 루틴 끼리 데이터를 효율적인 방식으로 주고 받을 수 있음

### 왜 go루틴이 좋은가?
-> go루틴이나 채널은 다른 작업이 끝날 떄까지 기다릴 필요 없이 실행이 시작됨  
-> 주고 받는 값을 일일히 변수에 저장할 필요 없기 때문에 변수 사용 횟수를 줄일 수 있고, 결과적으로 메모리 공간이 절약됨  
-> 파이프라인을 사용하게 되면 프로그램 설계를 간결하게 구셩하여 유지보수성이 높아짐

## 프로세스, 스레드, Go 루틴

프로세스 ⊃ 스레드 ⊃ Go 루틴

- 프로그램: 프로세스의 명령어와 사용자 데이터를 초기화하는 데 사용할 명령어와 데이터를 담은 파일
- 프로세스:
  - 명령어와 사용자 데이터, 시스템 영역, 그리고 실행 과정에 수집한 다양한 종류의 리소스로 구성된 독립적인 실행 단위
  - 바이너리 파일을 실행한 것
- 스레드:
  - 프로그램이나 프로세스보다 좀 더 가볍고 작은 실행 단위. 스레드는 프로세스에 의해 실행되며 독립적인 제어 흐름과 스택을 가짐
  - 프로세스의 일부분
- Go 루틴:
  - go 프로그램에서 동시에 실행할 수 있는 최소 단위. 유닉스 프로세스 안에 살고 있는 스레드 안에 존재함
  - 스레드보다 가벼워서 수천 내지 수만개를 구동해도 문제가 없음

### go 루틴 vs thread

> https://www.geeksforgeeks.org/golang-goroutine-vs-thread/

### Go 스케쥴러

Go 런타임 구성 요소. Go 프로그램을 구성하는 Go 루틴을 실행시킬 방법과 순서를 정함  
Go 스케쥴러는 한 프로그램 안에 있는 go 루틴만 다루기 때문에 커널의 스케줄러에 비해 훨씬 간결하고, 효율적이고, 빠르게 작동함

- 유닉스 커널 스케줄러: 프로그램에 있는 스레드를 실행함
- Go 스케쥴러: Go 런타임의 스케줄러. Go 루틴을 실행함

> 더 자세한 내용은 10 장에서..

#### m:n 스케쥴링

Go 스케쥴러가 사용하는 테크닉

- m: 실행되는 Go 루틴의 개수
- n: go 루틴을 멀티플렉싱할 os 의 스레드 개수

> https://tech.ssut.me/goroutine-vs-threads/

### 동시성과 병렬성

- 동시성: 가능하다면 서로 독립적으로 실행할 수 있도록 컴포넌트의 구조를 구성하는 방식
- 병렬성: 특정한 종류의 개체들이 동시에 실행되는 방식

동시성 지원이 되어야 병렬성 구성 가능. os 와 하드웨어에서 지원되어야 함

![동시성과 병렬성](./concurrency_parallelism.png)
> 출처: https://seamless.tistory.com/42

## Go 루틴

go 루틴을 정의하려면 함수 이름이나 익명 함수를 정의하는 문장 앞에 go 키워드를 적으면 됨  
-> go 키워드가 붙은 함수는 즉시 리턴하며, 실제 동작은 백그라운드에서 go 루틴 형태로 실행하며, 원래 수행하던 프로그램 흐름은 계속 이어짐

### go 루틴 생성 방법

```go
// 방법 #1: 일반 함수 이용
go function()

// 방법 #2: 익명 함수 이용 - 실행할 함수의 크기가 작은 경우
go func() {
  ...
}()
```

```go
package main

import (
  "fmt"
  "time"
)

func function() {
  for i := 0; i < 10; i++ {
    fmt.Print(i)
  }
  fmt.Println()
}

func main() {
  // go 키워드로 goroutine 실행
  go function()

  go func() {
    for i := 10; i < 20; i++ {
      fmt.Print(i, " ")
    }
  }()
  time.Sleep(1 * time.Second)
}
```

아래와 같이 실행 결과가 다르게 나올 수 있음
```sh
suejoe@Sueui-Macmini git % go run goroutine_simple.go
0123456789
10 11 12 13 14 15 16 17 18 19
suejoe@Sueui-Macmini git % go run goroutine_simple.go
10 11 12 13 14 15 16 17 18 19 0123456789
```

### Go 루틴 여러 개 생성하기

```go
package main

import (
	"flag"
	"fmt"
	"time"
)

func function() {
	for i := 0; i < 10; i++ {
		fmt.Print(i)
	}
	fmt.Println()
}

func main() {
	n := flag.Int("n", 10, "Number of goroutines")
	flag.Parse()

	count := *n
	fmt.Printf("Golang to create %d goroutines\n", count)

	for i := 0; i < count; i++ {
		go func(x int) {
			fmt.Printf("%d ", x)
		}(i)
	}
	// go routine 들이 모두 작업을 마칠때까지 기다리기 위함
	// 실제로는 이렇게 처리하면 안됨
	time.Sleep(time.Second)
	fmt.Println("\nExiting...")
}
```

실행 결과  
-> `time.Sleep()` 을 적절히 지정하지 않으면 go루틴의 결과를 보지 못할 수 있음  
-> 코드가 지저분해지고 실행 결과를 예측할 수 없기 때문에 알 수 없는 버그가 발생할 가능성 높음

```sh
suejoe@Sueui-Macmini git % go run goroutine_create.go -n 100
Golang to create 100 goroutines
0 1 2 6 4 5 11 7 8 9 10 17 12 13 14 15 16 27 18 19 20 21 22 23 24 25 26 40 28 29 30 31 32 33 34 35 36 37 38 39 60 41 42 43 44 45 46 47 48 49 50 51 3 79 61 62 63 64 65 66 99 68 67 52 53 54 70 58 71 69 55 72 75 59 78 77 76 73 56 81 89 80 74 85 86 87 82 88 84 94 90 91 92 93 96 97 83 95 98 57 
Exiting...
```

## Go 루틴이 마칠 때까지 기다리기

`sync` 패키지의 `WaitGroup`을 이용해 go 루틴이 모두 끝날 때까지 main 함수가 리턴하지 않게 만듦  
-> `WaitGroup`을 생성하여 `Add` 매서드로 대기해야할 go 루틴의 개수를 추가.  
go 루틴이 종료될 때 `Done` 메서드로 고루틴이 종료되었음을 알림.  
`Wait` 메서드를 호출하면 대기중인 모든 go루틴이 종료될 때까지 대기

```go
package main

import (
	"flag"
	"fmt"
	"sync"
)

func main() {
	n := flag.Int("n", 20, "Number of goroutines")
	flag.Parse()

	count := *n
	fmt.Printf("Golang to create %d goroutines\n", count)

	var waitGroup sync.WaitGroup
	// https://pkg.go.dev/sync#WaitGroup
	// type WaitGroup struct {
	// 	noCopy noCopy
	// 	state1 [12]byte
	// 	sema uint32
	// }

	fmt.Printf("%#v\n", waitGroup)
	for i := 0; i < count; i++ {
		// sync.WaitGroup 변수 카운터 증가
		// go 문이 나오기 전에 호출해야함
		// -> 그렇지 않으면, race condition 발생
		waitGroup.Add(1)
		go func(x int) {
			defer waitGroup.Done()
			fmt.Printf("%d ", x)
		}(i)
	}
	fmt.Printf("%#v\n", waitGroup)

	// sync.WaitGroup 변수에 있는 카운터가 0이 될때까지 실행을 멈춤
	waitGroup.Wait()
	fmt.Println("\nExiting...")
}
```

> race condition in Go?  
두개 이상의 go routine이 공유데이터를 가지며, 동시에 공유데이터에 접근할 때 발생  
https://medium.com/trendyol-tech/race-conditions-in-golang-511314c0b85

### WaitGroup Read 카운트가 Done 카운트가 다를 경우

#### WaitGroup Read 카운트 > Done 카운트

go routine의 종료(`sync.Done()`)를 끝없이 기다리며 데드락 발생

> deadlock?

```sh
suejoe@Sueui-Macmini git % go run goroutine_syncGo.go -n 10  
Golang to create 10 goroutines
sync.WaitGroup{noCopy:sync.noCopy{}, state1:0x0, state2:0x0}
sync.WaitGroup{noCopy:sync.noCopy{}, state1:0xb00000000, state2:0x0}
9 5 6 7 8 2 1 3 0 4 fatal error: all goroutines are asleep - deadlock!
```

#### WaitGroup Read 카운트 < Done 카운트

해당 에러는 명확히 드러나지 않을 떄가 있어, 주의해야함

```sh
suejoe@Sueui-Macmini git % go run goroutine_syncGo.go -n 5
Golang to create 5 goroutines
sync.WaitGroup{noCopy:sync.noCopy{}, state1:0x0, state2:0x0}
sync.WaitGroup{noCopy:sync.noCopy{}, state1:0x400000000, state2:0x0}
0 4 2 1 3 
Exiting...
panic: sync: negative WaitGroup counter
```

## 채널

Go 루틴끼리 데이터를 주고 받기 위해 사용하는 통신 매커니즘  

### 채널의 규칙

1. 채널의 element type: 각 채널마다 특정한 데이터 타입으로만 데이터를 교환할 수 있음
1. 채널을 함수의 매개변수로 사용할 때 채널의 방향을 명시해야함

| 설명 | 표현 |
| --- | --- | 
| 채널 선언 | `c := make(chan int)` |
| 채널에서 읽기 | `<-c` |
| 채널에 쓰기 | `c <- x` |

#### 채널에 데이터 쓰기

> c 라는 채널에 x 라는 값을 쓴다  
`c <- x`

```go
func writeToChannel(c chan int, x int) {
	fmt.Println("1", x)
	c <- x
	close(c)
	// 출력 안됨
	fmt.Println("2", x)
}

func main() {
	c := make(chan int)

	go writeToChannel(c, 10)
	time.Sleep(1 * time.Second)
}

// RESULT
// 1 10
```

위 코드에서 c 라는 채널에서 쓴 값을 아무도 읽지 않기 때문에 `writeChannel()` 함수는 `c <- x` 문장에서 막힘

#### 채널에서 데이터 읽기

위 코드의 main 함수 에 채널 읽기 추가

```go
func main() {
	c := make(chan int)

	go writeToChannel(c, 10)
	time.Sleep(1 * time.Second)

	fmt.Println("Read: ", <-c)
	time.Sleep(1 * time.Second)

	// 채널이 열려있는지 확인
	_, ok := <-c
	if ok {
		fmt.Println("Channel is open!")
	} else {
		fmt.Println("Channel is closed!")
	}
}

// RESULT
// 1 10
// Read:  10
// 2 10
// Channel is closed!
```

### 함수 매개변수로 지정한 채널

양방향 채널: 읽기/쓰기 모두 가능
단방향 채널: 읽기/쓰기 중 하나만 가능

```go
// pings 쓰기 전용 채널
func ping(pings chan<- string, msg string) {
    pings <- msg
}

// pings 읽기 전용 채널, pongs 쓰기 전용 채널
func pong(pings <-chan string, pongs chan<- string) {
    msg := <-pings
    pongs <- msg
}

func main() {
    pings := make(chan string, 1)
    pongs := make(chan string, 1)
    ping(pings, "passed message")
    pong(pings, pongs)
    fmt.Println(<-pongs)
}

// RESULT
// passed message
```
> 출처: https://gobyexample.com/channel-directions

## 파이프라인

파이프라인: Go루틴과 채널을 연결한 기법. 한쪽 go 루틴의 출력을 다른 go 루틴의 입력으로 연결할 수 있음  
-> 데이터의 흐름을 일정하게 구현할 수 있음

위의 ping-pong 예제
