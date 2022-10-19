package main

import (
	"fmt"
	"math"
	"net"
	"net/rpc"
	"os"
	"sharedRPC"
)

type MyInterface struct{}

func Power(x, y float64) float64 {
	return math.Pow(x, y)
}

func (t *MyInterface) Multiply(arguments *sharedRPC.MyFloats, reply *float64) error {
	*reply = arguments.A1 * arguments.A2
	return nil
}

func (t *MyInterface) Power(arguments *sharedRPC.MyFloats, reply *float64) error {
	*reply = Power(arguments.A1, arguments.A2)
	return nil
}

func main() {
	PORT := ":1234"
	arguments := os.Args
	if len(arguments) != 1 {
		PORT = ":" + arguments[1]
	}

	myInterface := new(MyInterface)
	// RPC 서버에서만 등록
	rpc.Register(myInterface)
	t, err := net.ResolveTCPAddr("tcp4", PORT)
	if err != nil {
		fmt.Println(err)
		return
	}

	l, err := net.ListenTCP("tcp4", t)
	if err != nil {
		fmt.Println(err)
		return
	}

	for {
		c, err := l.Accept()
		if err != nil {
			continue
		}
		// RPC 클라이언트와 통신하는데 사용할 IP 주소와 포트번호 리턴
		fmt.Printf("%s\n", c.RemoteAddr())
		// RPC 클라이언트에게 서비스 제공
		rpc.ServeConn(c)
	}
}
