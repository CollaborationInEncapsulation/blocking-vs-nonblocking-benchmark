# Reactive vs NonReactive 

This code is a part of presentataion Refactor to Reactive With Spring 5 and Project Reactor.

## Project

* [blocking-tomcat-demo]() - blocking web application that is based on Tomcat server
* [non-blocking-tomcat-demo]() - nonblocking web application that is based on Tomcat server + Servlet Api 3.1
* [non-blocking-netty-demo]() - nonblocking web application that is based on Netty server
* [gatling-tests]() - Gatling based test suite 

## Tool-sets

* Spring Framework 5
* Spring WebMVC for blocking 
* Spring WebFlux for non-blocking
* Tomcat Server for blocking and non-blocking
* Netty Server for nonblocing

## Hardware

Amazon EC2 ***t2.medium***

## Throughtput-sets

### Blocking Tomcat

#### Execution Options

```bash
java -Xmx2g 
     -Xms1g
     -Dserver.tomcat.accept-count=20000 
     -jar blocking-demo-0.0.1-SNAPSHOT.jar
```

#### Results

| Throughtput | Avarage Latency (milliseconds) |
| ----------- | ------------------------------ |
| 100 | 1200 |
| 1000 | 4600 |
| 10000 | 44565 |


### Blocking Tomcat

#### Execution Options

```bash
java -Xmx2g 
     -Xms1g
     -Dserver.tomcat.max-threads=10000 
     -Dserver.tomcat.max-connections=10000 
     -Dserver.tomcat.accept-count=20000 
     -jar blocking-demo-0.0.1-SNAPSHOT.jar
```

#### Results

| Throughtput | Avarage Latency (milliseconds) |
| ----------- | ------------------------------ |
| 100 | 1271 |
| 1000 | 1429 |
| 10000 | OutOfMemoryError / Killed |

### NonBlocking Tomcat (based on Servlet API 3.1)

#### Execution Options

```bash
java -Xmx2g 
     -Xms1g
     -Dserver.tomcat.accept-count=20000 
     -jar non-blocking-demo-tomcat-0.0.1-SNAPSHOT.jar
```

#### Results

| Throughtput | Avarage Latency (milliseconds) |
| ----------- | ------------------------------ |
| 100 | 1203 |
| 1000 | 1407 |
| 10000 | 9661 |


### NonBlocking Netty

#### Execution Options

```bash
java -Xmx2g 
     -Xms1g
     -jar non-blocking-demo-netty-0.0.1-SNAPSHOT.jar
```

#### Results

| Throughtput | Avarage Latency (milliseconds) |
| ----------- | ------------------------------ |
| 1000 | 1370 |
| 10000 | 2699 |
| 20000 | 6310 |



