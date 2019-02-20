## some tips


#### shell

```sbtshell

curl -XPOST "http://localhost:10001/login" -H "Content-Type:application/json" -d '{"username":"lisi", "password": "123"}' | jq .

curl -XGET "http://localhost:10001/v1/persons" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaXNpIiwicGFzc3dvcmQiOiIxMjMiLCJyb2xlIjoiYWRtaW4ifQ.1gtCatIZ0JfiveS96TFoPFeo4lRBCcqWWbEzgIw95l_mqfuSq4OxJ8M9yBzblnssIMINXO6cWgWCurMgJNF5Dw" -H "Content-Type:application/json"
```


#### start

```sbtshell
nohup java -Xms128m -Xmx128m -XX:+UseG1GC -XX:MaxGCPauseMillis=200  -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:ErrorFile=/home/test/dev/jwt_demo/fetal.log -jar jwt_demo.jar &
```

#### stop

```sbtshell
ps ax | grep jwt_demo | grep -v grep | awk '{print $1}' | xargs kill
```
