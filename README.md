# retrofitExample

retrofit2 + gson + asyncTask(deprecated)



## Overview

배송 조회 Open API 를 이용한 Retrofit2 사용 예제.



## Rest API Document

* 참고링크 : https://tracker.delivery/guide/

* baseURL : https://apis.tracker.delivery

  

## GetCarriers API

>택배사 목록 조회 API

```
@GET("/carriers")
https://apis.tracker.delivery/carriers
```

##### [Response Body]

| Key  | Description     | Type   |
| ---- | --------------- | ------ |
| id   | 택배사 아이디   | string |
| name | 택배사 이름     | string |
| tel  | 택배사 전화번호 | string |

```json
[   
   {
      "id": "kr.cjlogistics",
      "name": "CJ대한통운",
      "tel": "+8215881255"
   },
   {
      "id": "kr.cupost",
      "name": "CU 편의점택배",
      "tel": "+8215771287"
   },   
   {
      "id": "kr.epost",
      "name": "우체국 택배",
      "tel": "+8215881300"
   }
   ...
]
```



## GetCarriersTracks API 

> 배송조회 API

```
@GET("/carriers/{carrier_id}/tracks/{track_id}")
@Path("carrier_id") String [택배사 아이디]
@Path("track_id") long [송장번호]
```

```
https://apis.tracker.delivery/carriers/kr.epost/tracks/1111111111111
```

##### [Response Body]

| Key                             | Description      | Type          |
| ------------------------------- | ---------------- | ------------- |
| from.name                       | 택배사 이름      | string        |
| from.time                       | 날짜시각         | string        |
| to.name                         | 택배사 이름      | string        |
| to.time                         | 날짜시각         | string        |
| state.id                        | 배송 현황        | string        |
| state.text                      | 배송 현황 text   | string        |
| list.progresses                 | 배송 과정 리스트 | array[Object] |
| list.progresses[].time          | 날짜시각         | string        |
| list.progresses[].location.name | 위치정보         | string        |
| list.progresses[].status.id     | 배송 현황        | string        |
| list.progresses[].status.text   | 배송 현황 text   | string        |
| list.progresses[].description   | 배송 현황 설명   | string        |
| carrier.id                      | 택배사 아이디    | string        |
| carrier.name                    | 택배사 이름      | string        |
| carrier.tel                     | 택배사 전화번호  | string        |

```json
{
   "from": {
      "name": "한*지역난방공사양산지사",
      "time": "2020-07-28T00:00:00+09:00"
   },
   "to": {
      "name": "유* 노르웨이 아침 관리사무소",
      "time": "\n\t\t\t\t\t\t\t2020-09-07\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\tT00:00:00+09:00"
   },
   "state": {
      "id": "delivered",
      "text": "배송완료"
   },
   "progresses": [
      {
         "time": "2020-05-12T16:02:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료 교부  ( 배달 )"
      },
      {
         "time": "2020-05-13T16:02:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-05-13T16:04:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "미배달  * 미배달 사유 : 수취인부재  ( 보관중 )"
      },
      {
         "time": "2020-06-22T18:14:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-01T06:47:00+09:00",
         "location": {
            "name": "여의도우체국"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-01T11:21:00+09:00",
         "location": {
            "name": "여의도우체국"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료  ( 배달 )"
      },
      {
         "time": "2020-07-15T12:12:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료  ( 배달 )"
      },
      {
         "time": "2020-07-15T14:52:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-17T11:18:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-17T12:12:00+09:00",
         "location": {
            "name": "우정사업정보센터"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료  ( 배달 )"
      },
      {
         "time": "2020-07-18T09:10:00+09:00",
         "location": {
            "name": "해운대우체국"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-18T09:10:00+09:00",
         "location": {
            "name": "해운대우체국"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료 교부  ( 배달 )"
      },
      {
         "time": "2020-07-28T14:20:00+09:00",
         "location": {
            "name": "양산석산우체국"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "접수"
      },
      {
         "time": "2020-07-29T21:12:00+09:00",
         "location": {
            "name": "부산우편집중국"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "발송"
      },
      {
         "time": "2020-07-29T22:56:00+09:00",
         "location": {
            "name": "울산우편집중국"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "도착"
      },
      {
         "time": "2020-07-30T10:53:00+09:00",
         "location": {
            "name": "울산우편집중국"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "발송"
      },
      {
         "time": "2020-07-30T12:21:00+09:00",
         "location": {
            "name": "양산황산우체국"
         },
         "status": {
            "id": "in_transit",
            "text": "이동중"
         },
         "description": "도착"
      },
      {
         "time": "2020-07-31T08:32:00+09:00",
         "location": {
            "name": "양산황산우체국"
         },
         "status": {
            "id": "out_for_delivery",
            "text": "배송출발"
         },
         "description": "배달준비"
      },
      {
         "time": "2020-07-31T10:55:00+09:00",
         "location": {
            "name": "양산황산우체국"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료  ( 배달 )"
      },
      {
         "time": "2020-09-07T09:00:00+09:00",
         "location": {
            "name": "서울관악우체국"
         },
         "status": {
            "id": "delivered",
            "text": "배송완료"
         },
         "description": "배달완료  ( 배달 )  (수령인:정*님 - 본인)"
      }
   ],
   "carrier": {
      "id": "kr.epost",
      "name": "우체국 택배",
      "tel": "+8215881300"
   }
}
```

