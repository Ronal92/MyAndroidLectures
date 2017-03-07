[ Date : 2017. 01. 26 ]

		------------------------- Today's Topic ---------------------------
							(1) App Architecture & Activity Life Cycle
							(2) 액티비티의 생명주기 실습
							(3) 다른 액티비티에 값 넘기기
							(4) 호출한 앱으로부터 값 돌려받기
							(5) 묵시적 클래스 전달
							(6) 커스텀 브라우저(WebView)
		-------------------------------------------------------------------

# 1. App Architecture & Activity Life Cycle

##1.1 App Architecture란?

애플리케이션 아키텍처는 애플리케션을 설계하고 구축하는 작업을 표준화 한 것이다. 표준화된 작업은 애플리케이션 개발주기를 단축시키고 다른 앱의 코드와도 공유하는 것이 가능하다. 아키텍처 패턴 중에 가장 많이 사용되는 것이 MVC 패턴이다. 

MVC는 모델, 뷰, 컨트롤러를 의미한다.

- 모델 : 애플리케이션에 사용되는 데이터의 일반적인 포맷
- 뷰 : 사용자에게 데이터를 나타낸다.
- 컨트롤러 : 애플리케이션의 이벤트에 관한 처리를 모델과 뷰 사이에서 명령을 내리는 역할이다.

MVC 아키텍처의 장점은 각 클래스마다 역할이 분명히 정의되어있기 때문에 앱의 테스트와 유지가 쉽고 코드를 재사용할 수 있다.

(출처 : [https://medium.com/@manyoung/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C-%EC%9B%B9%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90-%EB%B9%84%EA%B5%90%EB%B6%84%EC%84%9D-mvc%EC%99%80-mvvm-e446a0f46d8c#.sm5vtjrzg](https://medium.com/@manyoung/%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C-%EC%9B%B9%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90-%EB%B9%84%EA%B5%90%EB%B6%84%EC%84%9D-mvc%EC%99%80-mvvm-e446a0f46d8c#.sm5vtjrzg) )

![](http://cfs4.tistory.com/upload_control/download.blog?fhandle=YmxvZzQzNTY1QGZzNC50aXN0b3J5LmNvbTovYXR0YWNoLzAvMTIwMDAwMDAwMDAxLnBuZw%3D%3D)

(출처 : [http://jokergt.tistory.com/114](http://jokergt.tistory.com/114))

##1.2 Activity에도 생명 주기가 있다!

![](http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585)


안드로이드 어플리케이션 개발 할 때, 가장 기본이 되는 단위가 Activity이다. Activity는 stack구조*(스택 구조를 모르신다면 제 git의 1-3을 방문하시길......)*로 쌓이게 되는데 가장 최근에 활성화된 된 화면이 stack의 최상단 부분이다. 

#### 액티비티에는 4가지 상태가 있다.

                   (가) Active : 현재 활성화되고 있는 창이다.이 액티비티가 활성화되면 기존의 활성된 액티비티는 Pause가 된다.
                   (나) Pause : 투명한 액티비티나 활성화된 액티비티가 기존의 액티비티를 일부 가리는 상태이다. 액티비티가 완전히 가려지게 되면 가려진 액티비티는 Stop 상태가 된다.
                   (다) Stop : 화면에 나타나지 않는 액티비티이다. 액티비티가 화면 밖으로 나가거나 닫히고 나면 그 액티비티는 Inactive가 된다.
                   (라) Inactive : activity stack에서 제거된 상태이다. 다시 화면에 나타나기 위해서는 재시작되어야 한다.

(출처 :[http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585](http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585) )

---------------------------------------------

#2. 액티비티의 생명주기 실습

이번 실습에서 할 내용은 1장에서 배운 액티비티의 생명주기를 Log.d를 이용해서 직접 모니터로 확인할 것이다. 
Common과 TRANSPARENT 버튼을 누르면 둘다 다른 액티비티로 넘어가게 되지만 TRANSPARENT는 반투명 액티비티이다.

![](http://i.imgur.com/fROpCEz.png)

(MainActivity.java -- [ActivityControl project])

![](http://i.imgur.com/5Dv4vpC.png)

![](http://i.imgur.com/WlrBUGN.png)

![](http://i.imgur.com/TlA185S.png)

![](http://i.imgur.com/cMYjhi0.png)

--------------------------------------------------


#3. 다른 액티비티에 값 넘기기

![](http://i.imgur.com/yVZ3qyf.png)
![](http://i.imgur.com/3M6qF3n.png)
![](http://i.imgur.com/1zwrdmG.png)

------------------------------------------

#4. 호출한 앱으로부터 값 돌려받기

![](http://i.imgur.com/yjnXq8x.png)
![](http://i.imgur.com/OQ5add4.png)
![](http://i.imgur.com/n9hcIK4.png)


-----------------------------------------------------


#5. 묵시적 인텐트 전달

##잠깐!! 코드를 보기 전에 묵시적 인텐트와 명시적 인텐트를 구분하면
###### 명시적 인텐트는 호출되는 대상이 명확하게 지정된 경우를 말한다. 보통 자신의 하위 엑티비티를 호출하기위해 많이 사용된다. 엑티비티를 사용하기 위해서는 메니페스트에 반드시 등록을 해아하며 등록하지 않았다면 오류가 발생한다. (암시적 인텐트에서는 Action, Data, Type, Category, Component, Extras, Flags가 있다.)


###### 묵시적 인텐트는 호출 대상이 명확하게 정의되어있지 않은 인텐트를 말한다. 주로 다른 어플리케이션의 컴포넌트를 호출 할 때 사용한다. 아래 그림을 보고 이해하자.

(출처 : [http://whitegom.tistory.com/22](http://whitegom.tistory.com/22) )

![](http://i.imgur.com/3XycSKK.png)


![](http://i.imgur.com/7UCRGMF.png)


#6. 커스텀 브라우저(WebView)

######이전 기능에서는 사용자가 URL 주소를 입력하면 새로운 창으로 전환되면서 사용자가 뒤로가기 버튼을 눌렀을 때, 이전 앱으로 돌아갔었다. 커스텀 브라우저는 웹브라우저가 새로운 화면으로 전환되지 않고 화면 가운데에서 실행되는 것을 말한다.

![](http://i.imgur.com/S8UBXNR.png)

![](http://i.imgur.com/jdwy2eG.png)

![](http://i.imgur.com/OxM6yaF.png)















