# Spring MSA demo project 

Key-word : Service Discovery in a Microservices Architecture

## 🥞 Project Objective

The project utilizes the Eureka Server and Eureka Client components to manage and retrieve service information efficiently, enabling seamless access to essential details.

Two primary implementation approaches for the Service Discovery pattern are employed:

1. **Client-side Service Discovery using Ribbon**
    - Create an Eureka Server and Service Client.
    - Implement the Client-side Service Discovery pattern using Ribbon, allowing services to be invoked in a balanced, round-robin manner.
2. **Server-side Service Discovery using Spring Cloud Gateway**
    - Create an Eureka Server and Service Client.
    - Position the Spring Cloud API Gateway at the service's front-end.
    - Implement the Server-side Service Discovery pattern, leveraging Spring Cloud Gateway to facilitate the process.


## 1️⃣ Client Side Service Discovery
### **Objective** 
Implement the **ClientSide Service Discovery pattern** using Ribbon to invoke services in a round-robin manner.

### **Directory Structure**
```
├── eureka-server
├── service-a : Service A inst 001
├── service-b : Service B inst 001
├── service-b-1 : Service B inst 002
```

### **Scenario**
 1. Implement an Eureka Server (Service Registry) using Netflix Eureka.
 2. Register two services (Service A and Service B) along with three instances in the Eureka Client.
 3. From Service A, invoke two instances of Service B using a round-robin approach.

### **Outcome**
![1](https://github.com/serinryu/spring-msa/assets/74564995/b27b8e06-1924-4bc6-be9c-27540259f3b1)
    - Service-A(Eureka Client) will retrieve information about Service-B(Eureka Client) from the Eureka Server. Service-A will then invoke 2 instances of Service-B(inst001, inst002) in a round-robin manner. This is achieved by utilizing Ribbon, a built-in load balancer within Eureka.
    - In essence, **Service A will act as a load balancer itself, showcasing the ClientSide Service Discovery pattern.**


### **Test**
- External Client => Service A => Service B
![2](https://github.com/serinryu/spring-msa/assets/74564995/acbbd605-9f31-45f0-80f6-ac909fd9a0d7)
![3](https://github.com/serinryu/spring-msa/assets/74564995/8506c0ff-c63f-4943-9658-6a73e76df87d)


## 2️⃣ Server Side Service Discovery
    
### **Objective** 
Implement the ServerSide Service Discovery pattern using a Spring Cloud API Gateway at the front-end of services.

### **Directory Structure**
```
├── eureka-server
├── gateway
├── service-a : Service A inst 001
├── service-b : Service B inst 001
├── service-b-1 : Service B inst 002
```

### **Scenario**
 1. Create an Eureka Server (Service Registry) using Netflix Eureka. 
 2. Implement a Spring Cloud Gateway and register it with the Eureka Client.
 3. Register the existing two services and three instances(Service A, Service B inst 001, Service B inst002) with the Eureka Client.

 - Note: Open-source options for API Gateway include Netflix Zuul, Spring Cloud Gateway, and ServiceComb EdgeService. Starting from Spring Boot 2.4.X, Zuul and Hystrix are no longer recommended, and the Spring Cloud community suggests using Spring Cloud Gateway. Thus, we intend to utilize Spring Cloud Gateway instead of Netflix Zuul to configure the API Gateway.)

 - A crucial point to note is that the existing peer-to-peer communication, where Service A directly calls Service-B (Service A → Service B), needs to be modified. The source code should be adjusted to have Service A call the Gateway, which in turn communicates with Service B **(Service A → Gateway → Service B)**.

### **Outcome**

![4](https://github.com/serinryu/spring-msa/assets/74564995/d0e53ae5-c102-4e34-8246-65930061e11e)

 - When Service A calls Service B, it passes through the Gateway. This applies not only to external clients invoking services but also within the same service layer. 
 - Regardless of whether it's an external client or an internal service invocation, you only need to know the information about the **Gateway** as it handles the routing.

> - External Client => Gateway => Service A => Gateway => Service B
> - External Client => Gateway => Service A 
> - External Client => Gateway => Service B


### **Test**

- External Client => Gateway => Service B
![5](https://github.com/serinryu/spring-msa/assets/74564995/0252715c-ab3a-43c5-acd6-86ac386aa587)
![6](https://github.com/serinryu/spring-msa/assets/74564995/9f5bcfe0-f5b5-4b0e-8c8b-c3d6638bebdb)

- External Client => Gateway => Service A => Gateway => Service B
![Screenshot 2023-08-08 at 1 50 16 PM](https://github.com/serinryu/spring-msa/assets/74564995/eb92ee7b-94f5-4b11-b000-8164f3102012)



### **Potential Areas for Improvement**

Even though all client requests are managed by the Gateway, the Gateway introduces a single point of failure and potential traffic concentration, requiring careful considerations. 

In the future, we can enhance the Gateway by incorporating more sophisticated features such as authentication, authorization, logging, and more through Filters within the API Gateway.
