# pii-log-filter

## To Run App:
```
./mvnw spring-boot:run
```

## Send Request to Running App:
```
curl -X POST \
  http://localhost:8080/collector/event \
  -H 'Content-Type: application/json' \
  -d '{
	"timestamp": 1231231235, 
	"level": "info",
	"logMessage": "phone number 5879206527"
}'
```

You can view in the app logs what is redacted

## To Run Tests:
```
./mvnw test
```