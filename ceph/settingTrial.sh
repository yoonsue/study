#!/usr/bin/env bash

#storage 1 port 9001
function ssh() {
	ssh $1@$2 -p $3
}


hostname=cpsnas.hanyang.ac.kr
user=park

# storage1
port=8001
ssh $user@$hostname -p $port
exit

# storage2
port=8002
ssh $user@$hostname -p $port
exit

# storage3
port=8003
ssh $user@$hostname -p $port
exit
