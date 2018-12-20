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

- Build/Run locally as follows. Once done CNTRL-C to exit the running app 

```bash
$ ./package-run.sh
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

```json
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

- When running locally using HTTPie 

```json
papicella@papicella:~/piv-projects/MSA/msa-apifirst$ http :8080/v1/customers
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Thu, 20 Dec 2018 04:20:56 GMT
Transfer-Encoding: chunked

{
    "_embedded": {
        "customers": [
            {
                "_links": {
                    "customers": {
                        "href": "http://localhost:8080/v1/customers"
                    },
                    "self": {
                        "href": "http://localhost:8080/v1/customers/1"
                    }
                },
                "name": "pas",
                "status": "active"
            },
            {
                "_links": {
                    "customers": {
                        "href": "http://localhost:8080/v1/customers"
                    },
                    "self": {
                        "href": "http://localhost:8080/v1/customers/2"
                    }
                },
                "name": "lucia",
                "status": "active"
            },
            {
                "_links": {
                    "customers": {
                        "href": "http://localhost:8080/v1/customers"
                    },
                    "self": {
                        "href": "http://localhost:8080/v1/customers/3"
                    }
                },
                "name": "lucas",
                "status": "inactive"
            },
            {
                "_links": {
                    "customers": {
                        "href": "http://localhost:8080/v1/customers"
                    },
                    "self": {
                        "href": "http://localhost:8080/v1/customers/4"
                    }
                },
                "name": "siena",
                "status": "inactive"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/v1/customers"
        }
    }
}

papicella@papicella:~/piv-projects/MSA/msa-apifirst$ http :8080/v1/customers/1
HTTP/1.1 200
Content-Type: application/hal+json;charset=UTF-8
Date: Thu, 20 Dec 2018 04:20:06 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "customers": {
            "href": "http://localhost:8080/v1/customers"
        },
        "self": {
            "href": "http://localhost:8080/v1/customers/1"
        }
    },
    "name": "pas",
    "status": "active"
}

papicella@papicella:~/piv-projects/MSA/msa-apifirst$ http POST :8080/v1/customers < customer.json
HTTP/1.1 201
Content-Type: application/json;charset=UTF-8
Date: Thu, 20 Dec 2018 04:19:41 GMT
Location: http://localhost:8080/v1/customers/5
Transfer-Encoding: chunked

{
    "_links": {
        "customers": {
            "href": "http://localhost:8080/v1/customers"
        },
        "self": {
            "href": "http://localhost:8080/v1/customers/5"
        }
    },
    "name": "fred",
    "status": "active"
}

papicella@papicella:~/piv-projects/MSA/msa-apifirst$ http DELETE :8080/v1/customers/5
HTTP/1.1 200
Content-Length: 0
Date: Thu, 20 Dec 2018 03:30:38 GMT
```
<hr size=2 />
Pas Apicella [papicella at pivotal.io] is an Advisory Platform Architect at Pivotal APJ 