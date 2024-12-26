# 특강 신청 서비스
 
## 애플리케이션 패키지 설계
```
형식
- 수강신청
    - interface
    - domain
    
src/
├── support/
│   ├── common/ 
│          └── entity/                 # 공통 엔티티
│   ├── exception/                     # 예외 관리
│   ├──    http/                       # 유틸리티
│            └─ CommonResponse.java 
├── user/                   
│   ├── domain/
│   │   ├── User.java.                 # User 엔티티
│   │   ├── UserService.java.          # User 서비스 구현체
│   │   ├── UserRepository.java.       
├── lecture/                   
│   ├── domain/
│   │   ├── Lecture.java.               # Lecture 엔티티
│   │   ├── LectureService.java.        # Lecture 서비스 구현체
│   │   ├── LectureRepository.java.     
│   ├── interfaces/                     
│   │   ├── LecturerController.java
│   │   ├── dto/
│   │   │   ├── LectureRespnse.java
├── reservation/                   
│   ├── domain/
│   │   ├── Reservation.java.            # Reservation 엔티티
│   │   ├── ReservationService.java.     # Reservation 서비스 구현체
│   │   ├── ReservationRepository.java.  
│   ├── interfaces/                     
│   │   ├── ReservationController.java
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   │   ├── ReservationRequest.java
│   │   │   ├── response/
│   │   │   │   ├── ReservationResponse.java
└── Application.java                     # Spring Boot 애플리케이션 진입점
```
클린 + 레이어트 아키텍처로 다음과 같이 구성한 이유는 기존 DIP원칙을 다음 이미지와 같이 지키는 것이 맞지만  응용 영역과 도메인 영역이 인프라스트럭처에 대한 의존을 완전히 갖지 않도록 시도하는 것은 자칫 구현을 더 복잡하고 어렵게 만들 수 있습니다.

![image](https://github.com/user-attachments/assets/4f873fdb-c886-4b9b-88d8-1b8af8df40f6)


이번 아키텍처를 설계 구조만으로도 개발 편의성과 실용성 등을 가져가면서 구조적인 유연함은 어느정도 유지가 가능하기 때문에 다음과 같이 설계했습니다. DIP를 적용하는 주된 이유는 저수준 구현이 변경되더라도 고수준이 영향을 받지 않도록 하기 위함이지만 이번 프로젝트에서는 리포지터리와 도메인 모델의 구현 기술의 변경 가능성이 없기 때문에 DIP 적용이 오히려 더 복잡한 구조가 되어 개발 속도가 늦어질 수 있다고 판단했습니다. 이번 프로젝트에서는 이러한 요소를 고려하여 DIP를 과도하게 적용하지 않고 간단한 구성을 선택했습니다.


## ERD
![image](https://github.com/user-attachments/assets/a2c51ec2-f5ae-41fe-876e-cd7a5b2b1d9b)
    
특강 신청 여부 조회에서 날짜별로 신청 가능한 특강 목록 조회와 해당 특강의 예약 트레픽을 비교할 때 조회를 더 많은 유저가 사용할 것으로 판단되어 비정규화를 적용했습니다.
```
우선순위 조회 >>> 입력
```
1. 자신의 예약을 내역을 조회할 때마다 Join을 하는 것 보다 예약 테이블에 모든 정보를 가지고 있어 단순 Select문을 통해 유저에게 더 빠른 데이터를 전달 할 수 있고 DB의 부화를 줄일 수 있습니다.
2. 만약 특강의 정보가 재활용되는 비즈니스 로직일 경우 유저가 자신의 특강을 조회할 때 자신의 과거 특강 정보가 수정될 수 있는 위험이 있습니다.




