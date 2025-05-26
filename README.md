## 프로젝트

### 기능 요구사항
 1. 맛집 검색
    1. 다양한 플랫폼에서 조회가능
    2. 검색조건(정확도 순, 리뷰 개수 순)으로 조회 가능
 2. 맛집 등록 (자체 관리)
 3. 맛집 수정 (자체 관리)
 4. 맛집 삭제 (자체 관리)
 5. 맛집 검색 -> 키워드 저장
 6. 키워드 조회 

### 시나리오 흐름도
[시퀀스 다이어그램](https://www.figma.com/design/8cWOhqAqT7sLsNni0iYyff/%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4-%ED%9D%90%EB%A6%84%EB%8F%84?node-id=0-1&p=f&t=ks7LmwyGWWGptwRu-0)

###  ERD
[ERD](https://www.erdcloud.com/d/4wzTtEd3rxwG8xP96)

###  인프라 설계도
[infra](notfound)

## Getting Started

### Prerequisites

#### Running Docker Containers

`local` profile 로 실행하기 위하여 인프라가 설정되어 있는 Docker 컨테이너를 실행해주셔야 합니다.

```bash
docker-compose up -d
```
