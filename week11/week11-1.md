[ Data : 2017. 03. 20(월) ]


##### To do : RX 자바 설정 5장, 6장 내용 보충



					
					--------------------Today's Topic ------------------
									(1) Rx 자바란?
									(2) RxBasic
									(3) RxBasic2Thread
									(4) RxBasic3Network
									(5) RxBasic4HotObs
									(6) RxBasic5Binding
					----------------------------------------------------

- 프로젝트명 : [RxBasic], [RxBasic2Thread], [RxBasic3Network], [RxBasic4HotObs], [RxBasic5Binding]

- 내용 : Rx 자바에 대한 이해 


# 1 Rx자바란?

일반적인 명령형 프로그래밍이 아니라 반응형 프로그래밍입니다. Rx 자바의 기본 구조는 Observable과 Subscriber 입니다. Observable은 데이터를 발행(emit)하고 Subscriber는 데이터를 연산 처리합니다. 


## 설정


---------------------------------------------------

# 2. RxBasic


![](http://i.imgur.com/8ehJf5n.png)

--> Observable은 데이터 처리를 실행하고 Subscriber에 전달합니다.


##2.1 옵저버(구독자)를 등록해주는 함수 - 기본형

![](http://i.imgur.com/c75LuiT.png)

--> Subscriber는 전달된 데이터를 가지고 연산, 결과를 출력합니다. subscribe 메소드에 Observer를 담아주면, Observable이 실행됩니다. 즉, Observable의 onNext()에 전달한 데이터가 순차적으로 실해됩니다.
							
							*************************************
							*  onNext - 새로운 데이터를 전달한다.  *
							*  onCompleted - 스트림의 종료        *
							*  onError - 에러 신호를 전달한다	  *
							*************************************    

##2.2 옵저버(구독자)를 등록하는 함수 - 진화형

![](http://i.imgur.com/rICga4r.png)

--> Action들은 Subscriber의 각 파트를 정의할 수 있습니다. simpleObservable.subscribe()는 onNext(), onError() 그리고 onComplete()를 대신할 하나, 둘 또는 세 개의 액션 파라미터를 다룰 수 있습니다.

--> Action1, Action0는 각각 함수를 1개만을 전달하는 인터페이스 인스턴스로 사용되는데 2.3에서 람다식으로 변환시키기 위한 중간과정입니다.	

( 출처 : [https://medium.com/@LIP/rxjava-29cfb3ceb4ca#.ud40zg78r](https://medium.com/@LIP/rxjava-29cfb3ceb4ca#.ud40zg78r) )


##2.3 옵저버(구독자)를 등록하는 함수 - 최종진화형 (람다식)

![](http://i.imgur.com/8ZsjRKS.png)


# 3. RxBasic2Thread


![](http://i.imgur.com/uRo4u4e.png)

--> 스케줄러는 일종의 쓰레드와 같습니다. 

##3.1 susbcribeOn 

##### subscribeOn은 Observable의 thread를 지정하여 동작시키도록합니다.

##3.2 observOn

##### observOn은 이후에 따라오는 메소드들에 적용시킵니다. 여기서는 subscribe를 통해 mainThread에서 UI를 바꾸도록 하였습니다.

# 4. RxBasic3Network

--> [RxBasic2Thread] 프로젝트를 응용한 단계입니다. HttpUrlConnection을 사용하여 cnet.co.kr의 소스를 crawling하여 모바일 화면에 보여줍니다.

![](http://i.imgur.com/3e2jHAQ.png)

--> [RxBasic2Thread] 소스 코드와 큰 차이가 없습니다. 다만 데이터를 가져오는 부분에서 Remote 클래스에 있는 getTrlByGet 메소드를 사용하였습니다. 해당 url 사이트로 가서 웹사이트의 소스를 onNext()에 전달하면 subscriber가 화면에 띄워줍니다.

# 5. RxBasic4HotObs

##5.1 Hot / Cold Observable 개념

Observable이 전달한 데이터를 Subscriber가 어느 시점부터 구독하느냐에 따라 차이가 있습니다. Hot Observable은 Observable이 생성되자마자 데이터를 emit합니다. 그래서 Subscriber가 중간에 아무때나 이 데이터를 관찰할 수 있습니다. 하지만 Cold Observable은 반대입니다. Observable은 Subscriber가 구독을 시작할 때까지 emit하지 않고 기다립니다. 따라서 어떤 Subscriber든지 데이터 첫 연산부터 마지막까지 관찰할 수 있습니다.

![]()

# 6. RxBasic5Binding

// 무슨 내용이었는지 기억이 잘...............



# 그 외 공부해야한느 것들

Subject in Android

PublishSubject??

Hot / Cold Observable??