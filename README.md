## some tips


#### shell

```sbtshell

curl -XPOST "http://localhost:10001/login" -H "Content-Type:application/json" -d '{"username":"lisi", "password": "123"}' | jq .

curl -XGET "http://localhost:10001/v1/persons" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaXNpIiwicGFzc3dvcmQiOiIxMjMiLCJyb2xlIjoiYWRtaW4ifQ.1gtCatIZ0JfiveS96TFoPFeo4lRBCcqWWbEzgIw95l_mqfuSq4OxJ8M9yBzblnssIMINXO6cWgWCurMgJNF5Dw" -H "Content-Type:application/json"
```
