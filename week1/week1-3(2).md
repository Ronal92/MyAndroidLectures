[Date : 2017. 01 .11]

# 자료구조
	제한된 메모리를 효과적으로 관리하기 위해 데이터를 저장하는 형태
### 배열  

	- 데이터가 테이블 형식으로 표현되며, 일정한 규칙에 따라 나열된다.        
	- 인덱스 값 기준으로 어디든 바로 참조가 가능하다.
	- 삽입과 삭제시 오버헤드가 일어나고 저장 공간의 제약이 있다.
![](http://i.imgur.com/YETkIbi.png)	
### 리스트 
	- 각 데이터들을 포인터로 연결한다.
    - 배열이 가지고 있는 인덱스 개념이 없는 대신, 삽입과 삭제가 편리하다.
	- 리스트의 종류에는 연결형, 양방향, 순환형

![](http://i.imgur.com/3BmouPg.png)

### 스택 
	- 데이터를 연결형 리스트로 저장할 때, 가장 마지막에 추가된 데이터가 가장 처음 체거되는 방식이다.(LIFO)
 ![](http://postfiles15.naver.net/20160224_190/ziddlaos_1456289280177dGSVI_PNG/%BD%BA%C5%C3%B1%D7%B8%B2.PNG?type=w773)
> 출처 :   [http://blog.naver.com/PostView.nhn?blogId=ziddlaos&logNo=220636722017&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView](http://blog.naver.com/PostView.nhn?blogId=ziddlaos&logNo=220636722017&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView)
### 큐
	- 가장 처음에 추가된 원소가 가장 먼저 제거(FIFO)된다. 원소의 추가는 뒤에서 제거는 앞에서 한다.
![](http://i.imgur.com/XZM6G4v.png)


> 출처 : [http://blog.naver.com/PostView.nhn?blogId=ziddlaos&logNo=220637038328&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView](http://blog.naver.com/PostView.nhn?blogId=ziddlaos&logNo=220637038328&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView)
 // 그림
### 트리 
	- 데이터들이 계층적 구조를 가지고 있는 비선형 구조이다.
	- 트리의 주요 용어
			노드 : 트리의 구성요소
			루트 : 시작하는 노드 (맨 위!!)
			서브트리 : 특정 노드를 시작노드로 하는 트리
			단말 노드 : 자식이 없는 노드
![](http://www.ktword.co.kr/img_data/1332_1.JPG)

### 그래프 
- 그래프는 연결된 데이터들간의 관계를 표현한다. 트리와의 차이는 그래프는 한 노드에서 출발하여 사이클이 가능하다는 점이다 




# 알고리즘

### Selection sort란?
######주어진 리스트 중에 최소값을 찾아 그 값을 맨 앞에 위치한다. 이 과정을 반복하여 정렬을 한다.

### Bubble sort이란?
###### 두 인접한 원소를 검사하여 정렬하는 방법이다. 원소의 이동이 거품이 수면으로 올라오는 뜻한 모양에서 유래하였다.
![](http://i.imgur.com/nhzWQ24.png)
> 출처 : http://i.imgur.com/nhzWQ24.png
### Insertion sort란?
###### 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여, 자신의 위치를 찾아 삽입한다.(from 위키)
![](http://i.imgur.com/OkyBK3e.png)
### Merging sort이란?
###### 전체 원소를 하나의 단위로 분할한 후 분할한 원소를 다시 병합하는 방법이다.
(분할))
![](http://i.imgur.com/Psq8viZ.png)
(병합)
![](http://i.imgur.com/V33iYhh.png)
### Quick sort이란?
###### 전체 원소 중에서 피벗을 하나 고른 후, 피벗 앞에는 피벗 값보다 작은 원소를, 피벗 뒤에는 피벗 값보다 큰 원소를 둔다. 피벗을 기준으로 분할된 리스트 중에서 피벗을 다시 고르면서 위의 과정을 반복하는 방법이다.
![](http://i.imgur.com/svs6KYI.png)
