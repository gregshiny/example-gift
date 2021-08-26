# 패스트캠퍼스 강의 - 선물하기 프로젝트
해당 repo 는 [패스트캠퍼스 RED - 비즈니스 성공을 위한 Java/Spring 기반 서비스 개발과 MSA 구축](https://fastcampus.co.kr/dev_red_lhc) 에 대한 예제 프로젝트 입니다

<br>

### 강의 순서에 따른  branch 순서

#### 4. 선물하기 프로젝트 개발 

| branch | 강의 구간 | 특이 사항 |
|---|:---:|:---:|
| base/implements | 추후 반영 | |
| api/retrofit | 추후 반영 | 해당 branch 에서는 aws AMI 계정 발급 후 application.yml 에 반영해야 함 |
| message/aws-sqs | 추후 반영 | 해당 branch 에서는 aws AMI 계정 발급 후 application.yml 에 반영해야 함 |

#### 주의사항 
해당 프로젝트의 정상적인 실행을 위해서는 
1. [주문 예제 프로젝트](https://github.com/gregshiny/example-order) 의 order/expand-gift-with-sqs 브랜치를 로컬에서 사전에 실행시켜야 합니다
1. aws AMI 계정 발급 후 application.yml 에 반영해야 합니다 

<br>

### 강의에서의 API 호출 순서 
1. gift 서버에서 **선물하기 등록 API** 호출
1. gift 서버에서 **선물하기 결제중 상태로 변경 API** 호출
1. order 서버에서 **선물하기 주문 결제 처리 API** 호출 (이 때, aws-sqs 에 메시지가 발행됨)
1. gift 서버의 SqsListener 가 메시지를 읽고 해당 주문을 ORDER_COMPLETE 으로 변경
1. gift 서버에서 **선물하기 수락 상태로 변경 API** 호출

<br>

### 문의 방법 
강의 또는 코드 구현에 관한 질문은 greg.shiny82@gmail.com 으로 문의 바랍니다. <br>
수강생에 한하여 답변 드릴 수 있도록 하겠습니다 <br>
현업 업무가 있기 때문에 주말에 몰아서 답변 가능합니다 

