package main

import (
	"flag"
	"fmt"
	"sync"
	"time"
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
			time.Sleep(time.Second)
			fmt.Printf("%d ", x)
		}(i)
	}
	waitGroup.Done()
	fmt.Printf("%#v\n", waitGroup)

	// sync.WaitGroup 변수에 있는 카운터가 0이 될때까지 실행을 멈춤
	waitGroup.Wait()
	fmt.Println("\nExiting...")
}