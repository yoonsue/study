package main

import (
	"flag"
	"fmt"
	"strings"
)

type NamesFlag struct {
	Names []string
}

func (s *NamesFlag) GetNames() []string {
	return s.Names
}

func (s *NamesFlag) String() string {
	return fmt.Sprint(s.Names)
}

// flag.Var 에서 사용하는 class 인스턴스를 처리할 함수
func (s *NamesFlag) Set(v string) error {
	if len(s.Names) > 0 {
		return fmt.Errorf("Cannot use names flag more than once!")
	}
	names := strings.Split(v, ",")
	for _, item := range names {
		s.Names = append(s.Names, item)
	}
	return nil
}

func main() {
	var manyNames NamesFlag
	minusK := flag.Bool("k", true, "k")
	minus0 := flag.Int("0", 1, "0")

	flag.Var(&manyNames, "names", "Comma-seperated list")
	flag.Parse()

	fmt.Println("-k: ", *minusK)
	fmt.Println("-0: ", *minus0)

	for i, item := range manyNames.GetNames() {
		fmt.Println(i, item)
	}

	fmt.Println("Remaining command-line arguments: ")
	for index, val := range flag.Args() {
		fmt.Println(index, ":", val)
	}
}
