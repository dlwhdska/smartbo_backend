# smartbo_backend

<div align="center">
  <img width="134" alt="logo" src="https://github.com/user-attachments/assets/defd5b39-f3cf-45ef-90d4-cdacb57fc192" />
</div>

## 프로젝트 소개

효율적인 자원 관리를 위해 기본 기능에 더해 최적화된 자원 관리 기능을 강화한 서비스입니다.

---

## Tech Stacks

### IDE
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=Eclipse&logoColor=white)
![VS Code](https://img.shields.io/badge/VS%20Code-007ACC?style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white)
![MySQL Workbench](https://img.shields.io/badge/MySQL%20Workbench-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### DataBase
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### FrontEnd
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=black)
![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white)
![vue-router](https://img.shields.io/badge/vue--router-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white)
![Axios](https://img.shields.io/badge/Axios-5A29E7?style=for-the-badge&logo=Axios&logoColor=white)

### BackEnd
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-1B1A1D?style=for-the-badge&logo=Mybatis&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white)

---

## ERD

<div align="center">
  <img width="583" alt="erd" src="https://github.com/user-attachments/assets/75f342fc-fc11-4774-88e0-64478949722c" />
</div>

---

## 디렉토리 구조

<details>
  <summary>BackEnd</summary>
  <div>
  
  ```bash
     main
      ├── java
      │   └── com
      │       └── bo
      │           ├── SmartboBackendApplication.java
      │           ├── address
      │           │   ├── controller
      │           │   │   └── AddressController.java
      │           │   ├── dto
      │           │   │   └── AddressMemberDTO.java
      │           │   └── service
      │           │       ├── AddressService.java
      │           │       └── AddressServiceImpl.java
      │           ├── advice
      │           │   ├── MyControllerAdvice.java
      │           │   └── ValidTestControllerAdvice.java
      │           ├── attendance
      │           │   ├── controller
      │           │   │   └── AttendanceController.java
      │           │   ├── dao
      │           │   │   └── AttendanceRepository.java
      │           │   ├── dto
      │           │   │   └── AttendanceDTO.java
      │           │   ├── entity
      │           │   │   └── AttendanceEntity.java
      │           │   └── service
      │           │       ├── AttendanceMapper.java
      │           │       ├── AttendanceService.java
      │           │       ├── AttendanceServiceImpl.java
      │           │       ├── AttendanceStatus.java
      │           │       └── AttendanceTime.java
      │           ├── car
      │           │   ├── control
      │           │   │   └── CarController.java
      │           │   ├── dto
      │           │   │   ├── CarDTO.java
      │           │   │   └── CarRentDTO.java
      │           │   ├── entity
      │           │   │   ├── CarEntity.java
      │           │   │   └── CarRentEntity.java
      │           │   ├── repository
      │           │   │   ├── CarRentRepository.java
      │           │   │   └── CarRepository.java
      │           │   └── service
      │           │       ├── CarMapper.java
      │           │       ├── CarRentMapper.java
      │           │       ├── CarScheduler.java
      │           │       ├── CarService.java
      │           │       └── CarServiceImpl.java
      │           ├── config
      │           │   ├── AttendanceConfig.java
      │           │   ├── MyApplicationContext.java
      │           │   ├── MyMVCContext.java
      │           │   └── SchedulerConfig.java
      │           ├── department
      │           │   ├── dto
      │           │   │   └── DepartmentDTO.java
      │           │   ├── entity
      │           │   │   └── DepartmentEntity.java
      │           │   └── repository
      │           │       └── DepartmentRepository.java
      │           ├── exception
      │           │   ├── AddException.java
      │           │   ├── DuplicateException.java
      │           │   ├── FindException.java
      │           │   ├── ModifyException.java
      │           │   ├── RemoveException.java
      │           │   └── UnavailableException.java
      │           ├── login
      │           │   ├── controller
      │           │   │   └── LoginController.java
      │           │   ├── dto
      │           │   │   └── LoginRequestDTO.java
      │           │   └── service
      │           │       ├── LoginService.java
      │           │       └── LoginServiceImpl.java
      │           ├── meetingroom
      │           │   ├── controller
      │           │   │   └── MeetingroomController.java
      │           │   ├── dto
      │           │   │   ├── MeetingReservationDTO.java
      │           │   │   ├── MeetingRoomDTO.java
      │           │   │   └── ParticipantsDTO.java
      │           │   ├── entity
      │           │   │   ├── MeetingReservationEntity.java
      │           │   │   ├── MeetingroomDetailEntity.java
      │           │   │   ├── ParticipantEmbedded.java
      │           │   │   └── ParticipantsEntity.java
      │           │   ├── repository
      │           │   │   ├── MeetingReservationRepository.java
      │           │   │   ├── MeetingRoomRepository.java
      │           │   │   └── ParticipantsRepository.java
      │           │   └── service
      │           │       ├── MeetingroomMapper.java
      │           │       ├── MeetingroomService.java
      │           │       ├── MeetingroomServiceImpl.java
      │           │       ├── ReservationScheduler.java
      │           │       └── ReservationValidator.java
      │           ├── member
      │           │   ├── dto
      │           │   │   └── MemberDTO.java
      │           │   ├── entity
      │           │   │   └── MemberEntity.java
      │           │   └── repository
      │           │       └── MemberRepository.java
      │           ├── notice
      │           │   ├── control
      │           │   │   └── NoticeController.java
      │           │   ├── dto
      │           │   │   └── NoticeDTO.java
      │           │   ├── entity
      │           │   │   └── NoticeEntity.java
      │           │   ├── repository
      │           │   │   └── NoticeRepository.java
      │           │   └── service
      │           │       ├── NoticeMapper.java
      │           │       ├── NoticeService.java
      │           │       └── NoticeServiceImpl.java
      │           ├── schedule
      │           │   ├── control
      │           │   │   └── ScheduleController.java
      │           │   ├── dto
      │           │   │   └── ScheduleDTO.java
      │           │   ├── entity
      │           │   │   └── ScheduleEntity.java
      │           │   ├── repository
      │           │   │   └── ScheduleRepository.java
      │           │   └── service
      │           │       ├── ScheduleMapper.java
      │           │       ├── ScheduleService.java
      │           │       └── ScheduleServiceImpl.java
      │           └── stuff
      │               ├── controller
      │               │   ├── StuffController.java
      │               │   └── StuffReqController.java
      │               ├── dto
      │               │   ├── StuffDTO.java
      │               │   └── StuffReqDTO.java
      │               ├── entity
      │               │   ├── StuffEntity.java
      │               │   └── StuffReqEntity.java
      │               ├── repository
      │               │   ├── StuffRepository.java
      │               │   └── StuffReqRepository.java
      │               ├── service
      │               │   ├── StuffReqService.java
      │               │   ├── StuffReqServiceImpl.java
      │               │   ├── StuffService.java
      │               │   └── StuffServiceImpl.java
      │               └── util
      │                   ├── StuffMapper.java
      │                   └── StuffReqMapper.java
      └── resources
          ├── application.properties
          ├── db.properties
          ├── log4j.xml
          └── log4jdbc.log4j2.properties

  ```

  </div>
</details>

<details>
  <summary>FrontEnd</summary>
  <div>

  ```bash
    src
   ├── App.vue
   ├── components
   │   ├── Footer.vue
   │   ├── Header.vue
   │   ├── Main.vue
   │   ├── PageGroup.vue
   │   ├── Section.vue
   │   └── Sidebar.vue
   ├── main.js
   ├── pages
   │   ├── Login.vue
   │   ├── address
   │   │   └── AddressList.vue
   │   ├── attendance
   │   │   ├── AttendanceItem.vue
   │   │   └── AttendanceList.vue
   │   ├── car
   │   │   ├── CarAllRentList.vue
   │   │   ├── CarAllRentListItem.vue
   │   │   ├── CarList.vue
   │   │   ├── CarListItem.vue
   │   │   ├── CarManage.vue
   │   │   ├── CarManageList.vue
   │   │   ├── CarManageListItem.vue
   │   │   ├── CarMyRentList.vue
   │   │   ├── CarMyRentListItem.vue
   │   │   ├── CarNoReturnList.vue
   │   │   ├── CarNoReturnListItem.vue
   │   │   ├── CarRentList.vue
   │   │   ├── CarRentListItem.vue
   │   │   ├── CarWaitingList.vue
   │   │   └── CarWaitingListItem.vue
   │   ├── contacts
   │   │   └── Contacts.vue
   │   ├── dashboard
   │   │   ├── AttendanceInfo.vue
   │   │   ├── CarStatus.vue
   │   │   ├── Dashboard.vue
   │   │   ├── NoticeItemDash.vue
   │   │   └── StuffStatus.vue
   │   ├── meetingroom
   │   │   ├── FullCalendarTest.vue
   │   │   ├── MeetingRoomItem.vue
   │   │   ├── MeetingRoomList.vue
   │   │   ├── MeetingRoomResDelete.vue
   │   │   ├── MeetingRoomResInfo.vue
   │   │   ├── MeetingRoomResItem.vue
   │   │   ├── MeetingRoomResList.vue
   │   │   ├── MeetingRoomResModify.vue
   │   │   ├── MeetingRoomTimeline.vue
   │   │   └── ReservationModal.vue
   │   ├── notice
   │   │   ├── NoticeInfo.vue
   │   │   ├── NoticeItem.vue
   │   │   └── NoticeList.vue
   │   ├── schedule
   │   │   ├── Calendar.vue
   │   │   ├── ScheduleInfo.vue
   │   │   ├── Test.vue
   │   │   └── TodayTodoItem.vue
   │   └── stuff
   │       ├── StuffManage.vue
   │       ├── StuffManageItem.vue
   │       ├── StuffManageItemModal.vue
   │       ├── StuffRequest.vue
   │       ├── StuffRequestList.vue
   │       ├── StuffRequestListItem.vue
   │       ├── StuffRequestListItemModal.vue
   │       └── StuffRequestSend.vue
   └── router
       └── index.js
    
  ```
    
  </div>
</details>

---

## 주요 기능

- 메인 페이지
  <br>
  ㄴ vue-router를 활용한 SPA 방식의 사이드 메뉴바 구현 <br>
  ㄴ 대시보드 화면 구성 및 각 기능 연결

- 근태관리
  <br>
  ㄴ 출근, 퇴근, 서비스 이용자에 따라 설정 가능한 출퇴근 시간 및 지각, 결근 시간 설정 <br>
  ㄴ 근태 현황 조회 및 월별 조회

- 공지사항
  <br>
  ㄴ 공지 목록 전체 조회, 상세보기

- 주소록
  <br>
  ㄴ 주소록 조회, 검색 기능

- 일정 관리
  <br>
  ㄴ FullCalendar API 이용한 일정 조회/추가/수정/삭제

- 차량 예약/관리
  <br>
  ㄴ 차량 예약 신청/취소, 신청 내역 조회 <br>
  ㄴ 신청 승인/반려/반납처리 기능

- 비품 요청/관리
  <br>
  ㄴ 비품 요청과 승인/반려 기록 추적을 위한 로직 구현 <br>
  ㄴ 요청 데이터 조건에 따른 메서드 호출 로직 구현

- 회의실 예약
  <br>
  ㄴ 예약 등록, 자원 예약 효율화를 위한 중복 예약 방지 로직 구현, 예약 Timeline 프론트 구현 <br>
  ㄴ 회의 참여자 추가/수정, 예약 취소 기능

- 로그인
  <br>
  ㄴ 세션 로그인, 로그아웃

---

## UI

<div align="center">
  <img width="1047" alt="smartbo UI" src="https://github.com/user-attachments/assets/74703c8f-7e6a-4a48-baaf-2c811c084c38" />
</div>

---
