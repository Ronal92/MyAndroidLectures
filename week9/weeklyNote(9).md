Weekly Summary!!

		핸드폰을 사용하다보면 화면 상단에 알림이 뜨는 걸 볼 수 있습니다. 안드로이드의 Notification이 바로 그러한 기능을 제공합니다.

		Okhttp와 Retrofit은 서버와의 통신 방식을 나타내는데,
		OkHttp는 동기방식으로, Retrofit은 비동기 방식으로 동작합니다. 즉, OkHttp는 서버와의 데이터 통신이 끝나야 다른 작업을 진행할 수 있고, Retrofit은 데이터 통신 중에도 다른 작업을 할 수 있습니다.!!(참 쉽죠~~~)


		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만들어 놓았습니다.^^
	   	 


			-------------------- 목차 -----------------
							(1) Notification
							(2) RemoteOkHttp
							(3) RemoteRetrofit
			------------------------------------------


# 1. Notification - [MediaPlayerService]

## 화면 출력

서비스를 구현하는 어플리케이션에서 많이 사용되는 기능입니다. 카카오톡에서 메시지가 왔을 때, 핸드폰 상단에 알림창이 뜨는 거처럼 화면 상단에 알림이 뜨게 합니다.  


![](http://i.imgur.com/nHS4Rfr.png)

버튼을 클릭하면 서비스가 실행되면서 화면 상단에 알림바가 생성됩니다.

![](http://i.imgur.com/IRVF50h.png)

이 프로젝트에서는 음악을 직접 실행하지는 않지만, 음악 실행과 관련된 알림들과 버튼 모양 등을 실제 사용에 가깝게 구현하였습니다. 

핵심 코드가 구현되어 있는 "MediaPlayerService" 위주로 설명 드리겠습니다.

![](http://i.imgur.com/EeREhDb.png)


## 1.1 onStartCommand()

[MediaPlayerService]

![](http://i.imgur.com/pR4MzoP.png)

--> MainActivity에서 처음 서비스를 실행시켰을 때 호출되는 함수입니다.

(1) mManager는 MediaSession(미디어 재생 기능을 지원하기 위한 클래스)의 참조변수입니다.

(2) initMediaSessions() : 미디어 관련 API들을 세팅합니다. 
 
(3) handleIntent() : //

## 1.2 initMediaSessions()

[MediaPlayerService]

![](http://i.imgur.com/cI9jzTW.png)
![](http://i.imgur.com/T6KK84X.png)

(1) mMediaPlayer는 음악을 재생시키기 위한 control을 제공합니다. 객체 선언한 이후 음악이 내장된 path를 설정합니다. 준비 메서드인 prepare()를 호출합니다.
					
					mMediaPlayer = new MediaPlayer();
					mMediaPlayer.setDataSource(musicUri.getPath());
				    mMediaPlayer.prepare();

(2) MediaSession에서 세션을 만들고, 그 세션으로부터 토큰을 받아와 미디어 컨트롤(mController)에 설정합니다.


        mSession = new MediaSession(getApplicationContext(), "Media Player Session");
        mController =new MediaController(getApplicationContext(), mSession.getSessionToken());

(3) 세션은 음악의 각각의 상태(pause, play, stop 등)를 정의하기 위한 콜백함수를 부릅니다. 각 상태마다 알림 상태바가 달라야 하기 때문에 Notification이 새롭게 호출되어야 합니다. 
				
						mSession.setCallback()

(4) onStop() : 알림을 관리하는 NotificationManager를 얻어온 뒤, cancel() 메소드를 호출하여 Notification을 해제하면 상태바가 사라집니다. 여기서는 서비스 또한 같이 종료되게 하였습니다.


## 1.3 buildNotification()

[MediaPlayerService]

![](http://i.imgur.com/pqjkTyb.png)
![](http://i.imgur.com/1p8dcUh.png)

--> 알림을 설정하고 띄우는 역할입니다.

(1) 노티바 모양을 결정합니다. // but, 이번 프로젝트에서는 사용하지 않았습니다.........

			Notification.MediaStyle style = new Notification.MediaStyle();

(2) 노티바 전체를 클릭했을 때, 다음에 실행되는 동작을 intent에 담아둡니다. 여기서는 음악을 멈추게 하기 위하여 ACTION_STOP을 설정하였습니다.

				Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
    			intent.setAction( ACTION_STOP );

(3) PendingIntent는 인텐트의 발생을 지연시킵니다. 즉 노티바 전체를 클릭하여 음악을 멈추게 하는 동작을 지연시킬 목적으로 사용합니다. PendingIntent를 생성하는데 필요한 인자로서 노티바나 위젯을 사용하기 위해서는 시스템 자원이 필요합니다. 

    PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
															(Context context, int requestCode, Intent intent, int flags)


(4) 노티바를 생성하는 곳입니다. 알림(Notification)을 만들어내는 Builder 객체를 생성하고 필요한 속성들을 설정합니다. 

   			 Notification.Builder builder = new Notification.Builder( this )
   			         .setSmallIcon(R.mipmap.ic_launcher) 					//상태표시줄에 보이는 아이콘 모양	
    		        .setContentTitle( "Media Title" )                       // 노래제목
    		        .setContentText( "Media Artist" )                       // 가수
      		        .setDeleteIntent( pendingIntent )                       // 슬라이드로 노티바를 지운다.
       		        .setStyle(style);										// 노티바 모양


(5) 노티바에 있는 각 버튼의 아이콘과 리스너를 설정합니다. 인자값으로 Action 타입을 주어야 합니다. 여기서는 generateAction() 사용자 정의 함수를 만들어서 Action 타입의 변수가 리턴됩니다.

				builder.addAction(Action action)

(6) OS 별로 액션의 개수가 정해져 있으므로 Action의 중요도에 따라 꼭 보여져야 되는 Action 을 앞쪽에 배치합니다. 번호는 순서대로 0번부터.....입니다. 이 프로젝트는 롤리팝 이하에서도 실행되어야 하기 때문에 1,2,3 번만 보여주도록 하였습니다. 그 이상 버전에서는 5개 모두 나옵니다ㅣ.

					style.setShowActionsInCompactView(1,2,3,0,4);

(7) NotificationManager를 선언하고 노티바를 화면에 보여줍니다. notify() 안에 있는 인자는 노티바의 아이디와 Notifiaction(builder 객체를 build()하면 생성됩니다.) 입니다. **즉 NotificationManager가 리스트에 등록되어있는 Notification을 화면에 띄워주는 작업니다.** 

				NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        
        		notificationManager.notify( 1, builder.build() );

## 1.4 generateAction()


[MediaPlayerService]

![](http://i.imgur.com/xFkZNEr.png)

--> Activity 에서 클릭 버튼을 생성합니다.

(1) intent 객체의 액션은 호출된 함수에서 넘어온 패러미터 값으로 설정합니다.( ACTION_PLAY, ACTION_PAUSE, ACTION_REWIND, ACTION_STOP 등) 

					Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
					intent.setAction( intentAction );



(2) 1.3에서도 설명하였지만 PendingIntent는 실행 대상이 되는 인텐트를 지연시키는 역할입니다. 노티바에 있는 버튼들을 눌렀을 때, 인텐트 실행을 지연시킵니다.

					 PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);

(3) OS 버전별 Action Builder를 처리합니다. builder.addAction()에게 action 값을 반환하기 위해 build() 메소드를 호출해서 action 값을 생성, 반환합니다. 

    
  				  if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
 			      		 Icon iconTemp = Icon.createWithResource(getBaseContext(),icon);
  				         return new Notification.Action.Builder(iconTemp, title, pendingIntent).build();
 				  } else {
  			      	     return new Notification.Action.Builder(icon, title, pendingIntent).build();
				  }

## 1.5 handleIntent()


[MediaPlayerService]

![](http://i.imgur.com/4jf6CKw.png)

--> Intent Action 에 넘어온 명령어를 분기시키는 함수입니다. 각 action에 대한 처리들을 controller가 받아서 세션으로 넘기면 1.2 initMediaSessions()의 setCallback이 받아서 Notification 바를 생성하고 버튼 생성 등의 역할을 수행합니다.

-----------------------------------------------

# 2. RemoteOkHttp


--> 이번 시간에는 OkHttp 라이브러리를 사용해서 웹과 통신해 보겠습니다.

>> HttpUrlConnection이 있는데 왜 OkHttp를 사용할까????

>> 저처럼 궁금해하는 사람들을 위해 아래 그림으로 보여드리겠습니다. 둘다 www.daum.net의 소스 데이터를 가져오는 작업입니다. 하지만! HttpUrlConnection과는 달리 OkHttp 라이브러리를 사용하면 소스코드를 훨씬 적게, 단순하게 구현할 수 있습니다. 

![](http://i.imgur.com/BzQjUro.png)

# 2.1 OkHttp 라이브러리

--> 먼저 OkHttp 라이브러리를 안드로이드 app gradle에 추가합니다. 

			compile 'com.squareup.okhttp3:okhttp:3.6.0'

# 2.2 코드 사용법

(1) OkHttp 인스턴스를 생성합니다.

						OkHttpClient client = new OkHttpClient();

(2) request 개체를 생성합니다.

						Request request = new Request.Builder().url(url).build();

(3) client  인스턴스에 request를 담아 보냅니다. 즉 서버쪽으로 요청합니다.

						Response response = client.newCall(request).execute();

(4) Response 참조변수 통해 JSON 문자열을 가져올 수 있습니다.

						return response.body().string();

>> 정리 :
>     OkHttp 라이브러리는 HttpUrlConnection 을 쉽게 사용할 수 있게 해줍니다.
    하지만 Thread 처리가 되어 있지 않기 때문에
    Thread 를 사용하는 다른 Api 와 함께 사용해야만 합니다.


-----------------------------------------------------

# 3. RemoteRetrofit


## 출력 화면 

![](http://i.imgur.com/5tQ9UgW.png)

## 3.1 라이브러리 추가

안드로이드 App module에 retrofit 라이브러리를 추가합니다.

					compile 'com.squareup.retrofit2:retrofit:2.2.0'

그 다음으로 gson 라이브러리를 추가합니다.(gson은 json 파일을 자바 객체로 변환시키기 위해 사용합니다.)

					
   				   compile 'com.squareup.retrofit2:converter-gson:2.2.0'

## 3.2 json to class(pojo 사이트)


![](http://i.imgur.com/bkwlfG7.png) 

--> week9-1과는 달리 json에 대응되는 객체를 생성해야 합니다. pojo 사이트는 데이터 구조가 계층 구조에 맞게 설계되도록 객체를 만들어줍니다.


![](http://i.imgur.com/h2uWOZS.png)

--> pojo 사이트에서 만들어진 4개의 클래문을 안드로이드에 클래스로 복사/붙이기 합니다.

## 3.3 인터페이스 선언하기

<SeoulOpenService.interface>

![](http://i.imgur.com/goCZZz3.png)

--> retrofit은 사용자가 접속할 서버 주소(HTTP API)를 자바 인터페이스로 바꿔줍니다.

			@GET : 접속할 서버 URL 주소

			Call <'주고받을 객체'> '함수명' (@Query ('변수 이름') '자료형' '변수 이름')  ==> 여기서는 @Query에 Path를 넣어서 url에 들어갈 주소값을 동적으로 할당하였습니다.

## 3.4 GSON 라이브러리 사용

json 형식으로 된 api의 샘플 코드를 객체 형식으로 사용하기 위해서 GSON 라이브러리를 사용합니다. 

##### MapsActivity는 API의 샘플코드를 Retrofit 형식의 객체로 받아온 다음 객체에 들어있는 데이터들을 구글맵에 마커로 표시해줍니다.

<MapsActivity.java>

![](http://i.imgur.com/rTN7OHc.png)




(1) Retrofit을 초기화합니다. 

서버에서 json으로 주는 응답을 간단하게 변환할 수 있도록 Retrofit에서 Converter를 제공합니다. 

Converter를 사용하기 위해서 build.gradle 파일에 다음을 추가하고

		compile 'com.squareup.retrofit2:converter-gson:(insert latest version)'

retrofit 초기화 코드에 "addConverterFactory"를 사용하면 interface로 정의된 

SeoulOpenService를 사용할 수 있습니다.

(2) SeoulOpenService 참조변수를 Retrofit으로 생성합니다.


(3) 이후 service을 통해서 Retrofit을 사용하게 됩니다. 여기서 Data는 json 으로 받은 객체를 담을 형식을 정의한 클래스입니다. 



( 출처 : [http://thdev.tech/androiddev/2016/11/13/Android-Retrofit-Intro.html](http://thdev.tech/androiddev/2016/11/13/Android-Retrofit-Intro.html) )




![](http://i.imgur.com/dHZwUql.png)

 Retrofit에서 GSON을 SeoulOpenService 변환한 결과를 받아옵니다.

이후 과정은 이전에 사용한 구글맵 프로젝트와 같습니다.