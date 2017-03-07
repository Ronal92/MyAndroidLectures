[ Date : 2017. 02. 24 ]

					----------------- Today's Topic ---------------------
								(1) TheadRainDrop
								(2) Service
					-----------------------------------------------------

 - 프로젝트 명 : [ThreadRaindrop] , [ServiceBasic]
 - 내용 : Thread를 이용하여 화면상으로 비가 계속 떨어지는 장면을 구현합니다.


#1. ThreadRaindrop

##1.1 출력 화면

![](http://i.imgur.com/272asy2.png)

버튼은 3가지입니다. (START, PAUSE, STOP)

- START : 총 3가지의 thread가 동작합니다.(화면을 다시 그려주는 thead , 물방울 하나를 계속 만들어내는 thread, 물방울 하나당 y축 좌표만을 바꾸는 thread)

- PAUSE : 화면을 정지시킵니다. ( 이 때, 모든 thread의 while loop가 정지됩니다.)

- STOP : 화면을 리셋시키면서 Thread를 메모리에서 제거합니다.

##1.2 코드

이 프로젝트에서는 MainActivity.java 하나의 파일로 구성하였습니다.

###1.2.1 onCreate()

![](http://i.imgur.com/s8pOFG6.png)

(1) 모바일 폰의 화면 사이즈 값을 가로(deviceWidth)와 세로(deviceHeight)에 각각 받습니다.

(2) 위젯과 아이디를 서로 연결하고 버튼 리스너를 생성합니다.

(3) stage는 비를 표현(물방울)하는 객체를 화면에 그려줍니다. 

(4) layout은 버튼 상단에 위치하여 비가 그려지는 frameout입니다. 여기에 stage를 담아주기(그려주기)위해서는 아래 명령을 해주어야 합니다.

			     		layout.addView(stage) 

###1.2.2 stage 클래스

![](http://i.imgur.com/jH4vNFD.png)
![](http://i.imgur.com/0jkQXN2.png)

(1) Stage constructor :  물방울 객체(raindrops)를 담을 ArrayList를 선언하고
각 방울의 색깔(rainColor)는 파랑색으로 설정합니다.

>> 왜 CopyOnWriteArrayList()를 사용하는가?
>
>> onDraw()에서 ArrayList에 담은 물방울을 모두 그려주기 위해서 동적 loop를 사용하였습니다. 하지만 이때 ArrayList의 사이즈가 달라질 위험이 있기 때문에 해당 데이터를 안전하게 읽고 처리하기 위해 CopyOnWriteArrayList()를 사용합니다.

>> CopyOnWriteArrayList()는 안의 내용을 읽을 때, 데이터를 복사해 놓고 읽습니다.


(2) onDraw() : raindrops에 담긴 물방울을 화면 좌표상에 그립니다.

(3) addRaindrop() : raindrops에 새로 생성한 물방울을 추가하는 메소드.

(4) removeRaindrop() : 물방울이 화면의 사이즈를 벗어났거나 유저가 상황을 종료시킬 때 호출되는 메소드. 물방울을 raindrops에서 제거하고 해당하는 물방울 쓰레드를 종료시킵니다.

(5) removeAll() : (4)번이 하나의 물방울만을 제거하지만, 이 메소드가 호출되면 모든 물방울이 제거되면서 모든 물방울 쓰레드를 종료시킵니다.

###1.2.3 RedawThread // 쓰레드

![](http://i.imgur.com/APUObHu.png)

--> thread 생성시, Stage 인스턴스를 받습니다. 이 인스턴스는 0.05초 간격으로 화면(view)을 계속 새롭게 그리는데 사용합니다.

				stage.postInvalidate();



###1.2.4 MakeRain // 쓰레드

![](http://i.imgur.com/5yyQu0k.png)

--> thread 생성시, Stage 인스턴스를 받습니다. 0.05초 간격으로 새로운 물방울을 만듭니다.
(Raindrop 쓰레드를 호출합니다.)


###1.2.5 Raindrop // 쓰레드


![](http://i.imgur.com/7pcyCUd.png)
![](http://i.imgur.com/eGaKh0n.png)

--> 물방울 하나에 대한 정보를 가집니다. 

(1) 물방울이 떨어질 x, y좌표와 반지름, 떨어지는 속도를 생성자에서 결정합니다. 자기 자신을 stage의 ArrayList에 저장합니다.

(2) y 좌표에 떨어지는 속도를 매번 갱신합니다.

(3) y 좌표가 화면 사이즈를 벗어나면 stage의 ArrayList에서 제거합니다.

###1.2.6 onClick()

![](http://i.imgur.com/9u8RjOd.png)
![](http://i.imgur.com/UMR1I63.png)

(1) START 버튼 : RedrawThread와 MakeRain 쓰레드 를 동작시킵니다.

(2) PAUSE 버튼 : flag 값을 바꾸어 각 쓰레드가 동작하지 못하도록 막습니다.

(3) STOP 버튼 : flag 값을 바꾸고 각 쓰레드를 메모리에서 제거한뒤 화면을 초기화 시킵니다.

###1.2.7 onDestroy()

![](http://i.imgur.com/c04Pxis.png)

--> 사용자가 실행되고 있는 애플리케이션에서 나갈 경우, onDestroy()에서는 1.2.6 onClick()의 STOP 버튼과 같은 역할을 합니다. 



--------------------------------------------------------------

#2. Service

##2.1 개념

Service는 안드로이드의 4대 컴포넌트(Activity, BroadCast Receiver, Content Provider, Service) 중에 하나입니다. Activity와 달리 사용자가 직접 볼수 있는 환경이 아니라 background에서 작업합니다. ( 사용 예 : MP3 플레이어가 종료되고 나서도 계속 노래를 재생시킬 때 )

##2.2 Service의 생명 주기

![](http://www.w3ii.com/android/services.jpg)

--> 서비스 사용은 크게 2가지로 나뉩니다. 시작타입(왼쪽) / 연결타입(오른쪽)

--> 시작타입 서비스 : 서비스가 시작되고 나서 스스로가 stopSelf()를 호출하거나 다른 컴포넌트에서 stopService()를 호출하지 해야 종료됩니다.

--> 연결타입 서비스 : 서비스와 연결되어있는 모든 컴포넌트가 연결을 해제해야 서비스가 종료됩니다.

( 출처 : [http://ccdev.tistory.com/25](http://ccdev.tistory.com/25) )

##2.3 코드 ( 시작 타입 서비스)

###2.3.1 출력 화면

![](http://i.imgur.com/EEAGbZF.png)

###2.3.2 MyService 클래스 생성(시작타입)

![](http://i.imgur.com/QKRMVyt.png)

(1) Service를 먼저 상속합니다.

(2) onCreate() : 서비스 호출시 제일 먼저 생성됩니다.

(3) onStartCommand() : onCreate() 이후에 자동으로 호출됩니다. 3가지의 리턴 타입을 가집니다. 

- START_STICKY : 서비스가 강제 종료된 이후에도 재시작할 수 있습니다. onStartCommand()의 Intent 인자로 넘어온 값은 재시작할 때, null로 초기화 됩니다.

- START_NOT_STICKY : 서비스가 강제 종료된 이후에 재시작할 수 없습니다. 

- START_REDELIVER_INTENT : 서비스가 강제 종료된 이후에도 재시작할 수 있습니다. intent 값을 그대로 유지 시켜 줍니다.(위 코드에서 return super.onStartCommand(intent, flags, startId)는 START_STICKY 와 동일게 동작 합니다.)

(4) onDestroy() : 서비스가 종료될 때 호출됩니다.


###2.3.3 MainActivity 

![](http://i.imgur.com/I0IUNcE.png)

(1) START SERVICE 버튼 동작 : intent에 service 클래스를 담고 전송합니다. 

(2) STOP SERVICE 버튼 동작 : 서비스를 중단합니다.


## 2.4 코드( 연결 타입 서비스 )

연결타입 서비스는 서비스와 다른 컴포넌트 간에 유기적인 통신을 하려고 사용합니다.

bind service(연결 타입 서비스)를 사용하기 위해서는 다음의 과정이 필요합니다.

				- 바인드 되는 서비스는 Service 클래스를 상속받습니다.
				- 서비스가 바인드 되기 위해서는 onBind() 콜백 메소드를 구현합니다. ( 클라이언트들이 서비스와 상호작용할 수 있는 인터페이스를 정의한 IBinder 객체를 리턴합니다. )
				- 클라이언트는 bindService()를 호출하여 서비스에 바인드합니다.
				- 위 메소드를 호출할 때, 인자값으로 ServiceConnection의 구현객체를 전달하는데, 이것은 서비스와의 연결상태를 모니터링하는 역할을 합니다. 
				- 모든 클라이언트가 서비스에서 언바인드(unbind)되면, 시스템은 (startService()로 시작된 서비스가 아닌 경우에 한하여) 서비스를 종료합니다. 
				
				( 출처 : [http://android-kr.tistory.com/283](http://android-kr.tistory.com/283) )

###2.4.1 출력 화면

![](http://i.imgur.com/EHdggwj.png)

![](http://i.imgur.com/sTerW97.png)

![](http://i.imgur.com/cU3UwaC.png)

###2.4.2 MyService 클래스 생성(연결타입)

![](http://i.imgur.com/vrYpSPg.png)

(1) 서비스가 다른 컴포넌트에 자신의 데이터를 전달하려면, bind를 사용해야 합니다.
(먼저 넘겨줄 IBinder 인스턴스를 생성합니다.)

(2) getService() : 사용자가 임의로 만든 메소드입니다. 연결된 서비스의 인스턴스를 받으려고 만들었습니다.

(3) onBind() : 액티비티에서 bindService()를 실행하면 호출됩니다. 리턴한 IBinder 객체는 서비스와 클라이언트 사이의 인터페이스를 정의합니다. (즉 MyBinder 안에 선언된 getService()만 보입니다.)


###2.4.3 MainActivity 

![](http://i.imgur.com/rdKvQBL.png)

(1) 서비스 객체로 사용할 인스턴스(bService)와 현재 컴포넌트와 서비스 간 연결 상태를 나타내는(isService)를 클래스의 멤버변수로 선언합니다.

(2) 서비스와의 연결상태를 모니터링하는 인스턴스(conn)를 생성합니다.

(3) onServiceConnected()는 서비스와 연결되었을 때 호출되는 함수로서, 클라이언트(MainActiviy)에서 onBind()를 호출하면 binder가 리턴되어 옵니다.

(4) binder는 MyBinder 타입입니다. 따라서 MyService.MyBinder 참조변수가 받도록 합니다.

(5) mb 참조변수는 getService()를 사용하여 MyService 객체를를 미리 선언한 bService가 가리키도록 합니다. 연결 상태를 true로 설정합니다.(isServiec = true)

![](http://i.imgur.com/PNjHuyT.png)

(1) btnBind 버튼 : bindService()를 호출하여 현재 컴포넌트와 서비스를 연결합니다. 
				
				 첫번째 인자 : 바인드할 서비스를 가리키는 인텐트
				 두번째 인자 : ServiceConnection의 구현 객체
				 세번째 인자 : 바인딩 옵션 값 ( "BIND_AUTO_CREATE"는 서비스가 생성되어 있지 않을때 자동으로 생성하도록 합니다.)
(2) btnUnbind 버튼 : 현재 연결되어 있다면 연결된 서비스와의 관계를 해제하고 isService를 false로 바꿔줍니다.

(3) btnCallService 버튼 : 현재 서비스와 연결상태가 아니라면 서비스중이 아님을 사용자에게 알려줍니다. 
현재 서비스와 연결상태라면, MyService 객체를 가리키고 있는 bService를 사용하여 getRandomNumber() 메소드를 사용할 수 있습니다. 여기서는 난수값을 화면상에 출력하도록 하였습니다.

