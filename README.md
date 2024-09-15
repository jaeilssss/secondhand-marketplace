# 우리동네 중고 거래 시스템
-------------

## Project Tech

* **Tech** : Spring Boot 3.2.5, Spring data JPA, Spring Security, QueryDSL, WebSocket
* **Server** : Amazon EC2
* **Database** : MySQL, Redis
* **Language** : Java
* **ETC** : Lombok, Jenkins, Docker

## Project Architecture

![secondhandArchitecture2](https://github.com/user-attachments/assets/29f55f68-abff-498b-84d3-6a7370fbd099)

## ERD Diagram


![second-db-dia](https://github.com/user-attachments/assets/5a62baaf-54e7-4768-94ad-8792315413b2)

## API 명세서

|URL|Method|Description|Authentication|
|----------|-----|-----|-----|
|/api/member/login|POST|로그인 API|토큰 불필요|
|/api/member|POST|회원가입 API|토큰 불필요|
|/api/member/{memberId}|GET|회원 정보 조회 API|토큰 필요|
