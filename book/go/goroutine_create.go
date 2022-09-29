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