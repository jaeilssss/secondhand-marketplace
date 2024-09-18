# 우리동네 중고 거래 시스템

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

## API Table

|URL|Method|Description|Authentication|other|
|----------|-----|-----|-----|-----|
|/api/member/login|POST|로그인 API|토큰 불필요|
|/api/member|POST|회원가입 API|토큰 불필요| |
|/api/member/{memberId}|GET|회원 정보 조회 API|토큰 필요|
|/api/member/{memberId}|PUT|회원 정보 수정 API|토큰 필요||
|/api/member/{memberId}|DELETE|회원 탈퇴 API|토큰 필요||
|/api/member/renew/refresh|POST|토큰 재 발행 API|토큰 필요||
|/api/secondhand/post|POST|중고 거래 개시글 작성 API|토큰 필요||
|/api/secondhand?lat=1&lon=2&page=1|GET|우리 동네 중고 개시글 조회 API|토큰 필요|lat, lon, page 파라미터 필요|
|/api/secondhand?secondhandId=1|PUT|중고 개시글 수정 API|토큰 필요|secondhandId 파라미터 필요|
|/api/secondhand/mysecondhand|GET|나의 중고 개시글 조회 API|토큰 필요|page, memberId 파라미터 필요|
|/api/secondhand/image|POST|이미지 업로드API|토큰 필요|
|/api/secondhand/weather|GET|날씨 조회 API|토큰 필요|lat, lon 파라미터 필요|
|/api/pay|POST|결제 API|토큰 필요|
|/api/pay/refund|POST|환불 API|토큰 필요|
|/api/chatroom|POST|채팅방 조회 API|토큰 필요|
|/api/category|POST|카테고리 등록 API|토큰 필요|
|/api/category|GET|전체 카테고리 리스트 조회 API|토큰 필요|
