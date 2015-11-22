# spring-security-distributed-system

This is a simple springBoot project consists of two RESTful web-services (client1 and client2) and spring security configuration (security-config) which allows to authenticate by a JSON object. By using session token (stored in REDIS database) instead of sessionId in cookie, it is possible to share authentication among two web-services. Therefore, it is pretty easy to authenticate by client1 and then invoke client2 endpoint.


__REDIS__

Redis database is required. If you use Windows, you will find appropriate installer here: https://github.com/MSOpenTech/redis/releases Installers for other systems are available here: http://redis.io/download


__Build and Run__

Build:
`mvn clean install`

Run:
You can run main method from your IDE or execute the following command in your terminal: 

`mvn spring-boot:run -Dserver.port=8091` for client1

`mvn spring-boot:run -Dserver.port=8092` for client2


Authentication:

`curl -X POST http://localhost:8092/authenticate -H "Content-Type: application/json" -d "{\"user\" : \"admin\", \"password\" : \"s3cr3t\"}" -v`

Expected output:

```
< HTTP/1.1 200 OK
* Server Apache-Coyote/1.1 is not blacklisted
< Server: Apache-Coyote/1.1
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< x-auth-token: 0e401c0f-d365-4ceb-813b-e3e18cccee8f
< Content-Length: 54
< Date: Sun, 22 Nov 2015 12:32:03 GMT
```

Once we have a session token, we can try invoke :

`curl http://localhost:8091/time -H "x-auth-token: 0e401c0f-d365-4ceb-813b-e3e18cccee8f"` for endpoint of client1

`curl http://localhost:8092/user -H "x-auth-token: 0e401c0f-d365-4ceb-813b-e3e18cccee8f"` for endpoint of client2


