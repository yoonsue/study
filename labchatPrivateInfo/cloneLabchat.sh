#! /usr/bin/env bash

killall labchat
mv labchat labchatold
git clone "https://github.com/yoonsue/labchat"
cp labchatold/labchat.conf.yaml labchat/labchat.conf.yaml
cp labchatold/phone.txt	labchat/phone.txt
cp labchatold/birthday.txt labchat/birthday.txt
rm -rf labchatold

cd labchat
go build
./labchat &

