# MSA - API First demo

This demo is a basic demo showing how to build aan API which is documented using SWAGGER

## Push to Pivotal Cloud Foundry

- Edit manifest.yml as shown below to matach what you would like from ma deployment perspective

```yaml
applications:
  - name: customer-api
    memory: 1G
    instances: 1
    random-route: true
    path: ./target/msa-apifirst-0.0.1-SNAPSHOT.jar
```

- Push as follows

```bash
$ cf push
```

- Once deployed access SWAGGER UI and other endpoints as shown below

```
SWAGGER: http://{ROUTE}/swagger-ui.html</li>
API DOCS: http://{ROUTE}/v2/api-docs</li>
ACTUATOR ENDPOINTS: http://{ROUTE}/actuator
```

- Actuator Health endpoint

```
papicella@papicella:~$ http http://customer-api-funny-reedbuck.cfapps.io/actuator/health
HTTP/1.1 200 OK
Connection: keep-alive
Content-Length: 183
Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8
Date: Thu, 20 Dec 2018 02:52:55 GMT
X-Vcap-Request-Id: 44be7076-5a06-40d4-603b-24f18af802d1

{
    "details": {
        "db": {
            "details": {
                "database": "H2",
                "hello": 1
            },
            "status": "UP"
        },
        "diskSpace": {
            "details": {
                "free": 896278528,
                "threshold": 10485760,
                "total": 1073741824
            },
            "status": "UP"
        }
    },
    "status": "UP"
}

```
- Some images showing this

![alt tag](https://i.ibb.co/FwBcN6c/msa-apifirst-1.png)

![alt tag](https://i.ibb.co/ykvPBzk/msa-apifirst-2.png)

![alt tag](https://i.ibb.co/KNwZcSH/msa-apifirst-3.pngg)

<hr size=2 />
Pas Apicella [papicella at pivotal.io] is an Advisory Platform Architect at Pivotal APJ 