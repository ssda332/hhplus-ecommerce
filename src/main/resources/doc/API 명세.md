# e-commerce API 명세

## **Balance API**

잔액 충전 및 잔액 조회 기능을 제공합니다.

### 잔액 충전

사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.

**엔드포인트**: POST /balance/charge

**Request**

**Request Syntax**

```json
{ 
	"userId": 1, 
	"amount": 100 
}
```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Request Body**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| userId | Long | 필수 | 사용자 ID |
| amount | Long | 필수 | 충전 금액 |

**Response**

**Response Syntax**

```json
{
  "userId": 1,
  "balance": 200
}
```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| userId | Long | 사용자 ID |
| balance | Long | 현재 잔액 |

### 잔액 조회

사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

**엔드포인트**: GET /balance

**Request**

**Request Syntax**

```json
{
	"userId": 1
}
```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Request Elements**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| userId | Long | 필수 | 사용자 ID |

**Response**

**Response Syntax**

```json
{
  "userId": 1,
  "balance": 200
}
```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| userId | Long | 사용자 ID |
| balance | Long | 현재 잔액 |

---

## **Order API**

주문 생성 및 결제 기능을 제공합니다.

### 주문 생성

새로운 주문을 생성합니다.

**엔드포인트**: POST /order

**Request**

**Request Syntax**

```json
{ 
	"userId": 1, 
	"productId": 1, 
	"productOptionId": 1, 
	"productCount": 1 
}
```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Request Body**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| userId | Long | 필수 | 사용자 ID |
| productId | Long | 필수 | 상품 ID |
| productOptionId | Long | 필수 | 상품 옵션 ID |
| productCount | Long | 필수 | 주문 수량 |

**Response**

**Response Syntax**

```json
{
  "orderId": 1,
  "userId": 1,
  "productId": 1,
  "productName": "Sample Product",
  "productOptionId": 1,
  "productOptionName": "Sample Option",
  "productCount": 1,
  "productPrice": 2000,
  "createDate": "2023-07-03T12:00:00"
}
```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| orderId | Long | 주문 ID |
| userId | Long | 사용자 ID |
| productId | Long | 상품 ID |
| productName | String | 상품 이름 |
| productOptionId | Long | 상품 옵션 ID |
| productOptionName | Strin | 상품 옵션 이름 |
| productCount | Long | 주문 수량 |
| productPrice | Long | 상품 가격 |
| createDate | LocalDateTime | 주문 생성 일자 |

### 결제

주문에 대한 결제를 처리합니다.

**엔드포인트**: POST /order/payment

**Request**

**Request Syntax**

```json
{ 
	"userId": 1, 
	"orderId": 1, 
	"amount": 2000 
}
```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Request Body**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| userId | Long | 필수 | 사용자 ID |
| orderId | Long | 필수 | 주문 ID |
| amount | Long | 필수 | 결제 금액 |

**Response**

**Response Syntax**

```json
{
  "paymentId": 1,
  "userId": 1,
  "orderId": 1,
  "amount": 2000,
  "status": "SUCCESS",
  "paymentDate": "2023-07-03T12:00:00"
}
```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| paymentId | Long | 결제 ID |
| userId | Long | 사용자 ID |
| orderId | Long | 주문 ID |
| amount | Long | 결제 금액 |
| status | String | 결제 상태 |
| paymentDate | LocalDateTime | 결제 일자 |

---

## **Product API**

상품 목록 조회 및 상품 상세 정보 조회 기능을 제공합니다.

### 상위 상품 조회

최근 3일간 가장 많이 팔린 상위 5개 상품을 조회합니다.

**엔드포인트**: GET /product/top

**Request**

**Request Syntax**

```

```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Response**

**Response Syntax**

```json
[
  {
    "id": 1,
    "name": "Product1",
    "price": 1000,
    "description": "Option1",
    "stock": 100,
    "createDate": "2023-06-30T12:00:00",
    "soldCount": 50
  },
  ...
]
```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 상품 ID |
| name | String | 상품 이름 |
| price | Long | 상품 가격 |
| description | String | 상품 설명 |
| stock | Long | 재고량 |
| createDate | LocalDateTime | 상품 생성 일자 |
| soldCount | Long | 판매 수량 |

### 상품 상세 정보 조회

상품 ID를 기반으로 상품의 상세 정보를 조회합니다.

**엔드포인트**: GET /product/{id}

**Request**

**Request Syntax**

```json
{
	"id": 1
}
```

**Request Header**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

**Request Elements**

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| id | Long | 필수 | 상품 ID |

**Response**

**Response Syntax**

```json
json코드 복사
{
  "id": 1,
  "name": "사과",
  "price": 10000,
  "description": "고급",
  "stock": 10,
  "createDate": "2023-07-03T12:00:00",
  "soldCount": 10
}

```

**Response Elements**

| 필드 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 상품 ID |
| name | String | 상품 이름 |
| price | Long | 상품 가격 |
| description | String | 상품 설명 |
| stock | Long | 재고량 |
| createDate | LocalDateTime | 상품 생성 일자 |
| soldCount | Long | 판매 수량 |