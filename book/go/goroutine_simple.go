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