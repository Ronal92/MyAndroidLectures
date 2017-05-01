Weekly Summary!!

		람다식은 자바 1.8부터 등장한 축약형 표현식입니다. 최신 개발자들 사이에서 많이 쓰이는데요. 특히 많이 들어보시게 될 RxJava에서 많이 쓰이는 표현법니다. 

 		Custom View는 안드로이드 스튜디오에서 제공하지 않는  뷰들에 대해 직접 자신이 원하는 방식으로 그리는 법입니다.

		팀프로젝트 UI 구성시 많이 사용하게 되니까 기본적인 거는 이 자료를 참고하시면 될거에요 ㅎㅎ

		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만들어 놓았습니다.^^(디자인 패턴 프로젝트는 이클립스로 돼있는데 추후에 올릴게요~~~~)
	   	 


					-------------------- 목차 -----------------
										(1) 람다식
										(2) CustomWidget										(3) CustomView
	----------------------------------------------------

# 1. Lambda

## 람다식이란?

자바 1.8부터 등장한 특징입니다. 함수식에서 불필요한 코드를 줄여 함수 표현식을 축약형으로 나타내고자 등장하였습니다.


## Gradle 설정

![](http://i.imgur.com/JZEGm0H.png)

--> build.gradle(앱 모듈)에 java 8을 사용하기 위한 설정을 추가해줍니다.

## Lambda를 사용하는 이유? 조건?

Lambda를 사용함으로써 코드량을 줄일 수 있습니다. Lambda를 사용하기 위해서는 **콜백 객체가 인자로 넘어가는 곳**이어야 하고 객체에는 **함수가 하나**여야 합니다.

## Lambda 작성법
			
			         1. 콜백객체에서 하나의 함수에 있는 로직블럭만 사용된다.
			         2. 함수명은 생략하고 괄호와 인자(타입생략)만 표시한다.
			         3. 함수명과 로직블럭을 -> 표시로 연결한다.
			         4. 인자가 여러개 일때는 타입을 붙일 수 있다.
			         5. 형태 :  (Parameter) -> { code }


## 1.1 Lambda 1

예시) 버튼 클릭 리스너의 경우

(1) 원형은 아래와 같은 형태입니다.

		 btn.setOnClickListener(
            public void onClick(View view){
               System.out.println(view);
        	}
 		);	

(2) 위 코드를 람다식으로 변형하면,

        람다 1: 함수명 생략
			btn.setOnClickListener(
       		 	(View view) -> {System.out.println(view); }
			);	
    
	    람다 2 : 함수 인자타입 생략
			btn.setOnClickListener(
        		(view) -> {System.out.println(view); }
			);

        람다 3: 함수 괄호 생략
    		btn.setOnClickListener(
    		    view -> { System.out.println(view); }
			);

        람다 4: 한줄일경우 코드 괄호 생략, 세미콜론 생략
			btn.setOnClickListener(        
				view -> System.out.println(view)
			);
        
		람다 5 : 코드측 함수가 받는 인자가 하나일 경우 참조형 메소드로 작성
			btn.setOnClickListener(    
            	System.out::println
			);

## 1.2 Lambda 2

![](http://i.imgur.com/fAUCRx7.png)


![](http://i.imgur.com/E39tluu.png)



## 1.3 Lambda 3 

![](http://i.imgur.com/3IIrxed.png)


--> 람다가 정말 사용되는 이유는?? 대용량의 데이터를 처리하기에 적합합니다.

위 코드는 버튼 btnLoop와 btnStream이 있을 때, 각각의 버튼을 눌렀을 경우 objectArray[]의 내용을 처리하는 방식이 각각 다릅니다.

(1) btnLoop 버튼을 눌렀을 경우, for-loop에서 String 타입의 str은 objectArray를 다 읽고나서 "System.ou.println"을 처리합니다. ( 이런 방식은 대용량의 데이터를 처리하는 경우 많은 시간이 걸리게 됩니다. )

(2) btnStream은 Stream 타입의 stream이 objectArray를 읽으면서 "System.out.println"을 바로 처리합니다. 

>> 람다식의 장점은 기존의 iteration 대신에 forEach를 사용한다는 것과 Stream에서 람다식이 파라미터 값으로 전달될 수 있다는 점입니다. 더 자세한 내용은 다음 링크를 보시면 도움 될겁니다.!!!! [http://m.blog.naver.com/2feelus/220706579677](http://m.blog.naver.com/2feelus/220706579677)



# 2. CustomWidget


## 2.1 텍스트뷰 커스터마이징


### 화면 출력

![](http://i.imgur.com/gtInKBI.png )

--> "Hello world!" 위젯 위에 텍스트뷰가 있습니다. 안드로이드에 있는 텍스트뷰를 사용하지 않고 직접 클래스로 정의하여 화면에 띄워보겠습니다.




[Today.java]

![](http://i.imgur.com/nL5wvl4.png)

(1) 먼저 사용자가 원하는 텍스트뷰를 화면에 띄우기 위해서는 클래스로 상속을 받아야 됩니다.
이때 생성자로 넘어오는 값 중, AttributeSet은 위젯의 속성값들입니다.

(2) "editText.setText("hello world");" 를 떠올리면 됩니다. setText() 메소드를 사용자가 직접 정의합니다. 여기서는 현재 날짜가 출력되도록 하였습니다.



[activity_main.xml]

![](http://i.imgur.com/CIFTc69.png)

--> TextView를 상속받은 클래스를 메모리에 올리고 나면 .xml에서 직접 가져다 사용할 수 있습니다. 여기서는 패키지명 + 클래스 이름 형태로 가져왔습니다.

## 2.2 속성 커스터마이징

### 화면 출력

![](http://i.imgur.com/aT4ehFm.png)

--> 이번에는 속성을 직접 사용자가 정의하여 현재 날짜를 출력할 때 '/'가 포함되도록 하였습니다.

### New Resource File 생성

![](http://i.imgur.com/Jbn8Gzq.png)

--> attrs 레이아웃 파일을 생성합니다.


![](http://i.imgur.com/ygWwbHO.png)

--> attribute로 사용할 속성을 정의합니다. delimeter 속성을 만듭니다.

### 코드

[Today.java]

![](http://i.imgur.com/fv6qF17.png)
![](http://i.imgur.com/1EqFXUA.png)

(1) 생성자에서는 .xml의 속성들과 attrs의 속성들을 매칭시켜서 현재 속성에 입력되어 있는 값을 가져오는 작업을 합니다.

(2) setDate()에서는 생성자에서 꺼내온 입력값( 여기서는 '/')을 실제 출력할 데이터에 적용합니다. 즉 날짜 포맷을 정의합니다.


[activity_main.xml]

![](http://i.imgur.com/djG2mGG.png)

--> 사용자가 정의한 custom 속성을 사용하기 위해서는 먼저 xmlns:custom을 선언합니다.

				xmlns:custom="http://schemas.android.com/apk/res-auto"

--> 사용자가 정의한 custom 속성을 다음과 같은 형태로 정의합니다.

				custom : 사용자 속성 = 입력값
				custom:delimeter = "/"

--------------------------------------------

# 3. CustomView

--> 1. CustomWidgdet에서 다루었던 내용들을 바탕으로 모바일 화면에 여러 그림들을 표시해보면서 pushpush 게임을 진행하기 위한 맵을 구현하였습니다.

## 3.1 화면출력

![](http://i.imgur.com/gs5twcJ.png)

## 3.2 맵 구성

화면 상단은 FrameLayout입니다. 이 FrameLayout 안에 가로, 세로 10칸씩의 맵을 만들겠습니다. 이 맵은 도형을 표시하기 위한 위치 x, y 좌표를 제공하게 됩니다.

![](http://i.imgur.com/RyEGWt1.png)

먼저 getDisplayMetrices()를 사용하여 자신의 모바일 화면 사이즈를 불러온 뒤 GROUND_SIZE( 여기서는 상수값 10으로 설정하였음. ) 나눕니다. 맵의 1칸의 단위는 unit에 저장됩니다.


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        unit = metrics.widthPixels / GROUND_SIZE; // 화면의 가로사이즈

맵으로 사용할 2차원 배열입니다. 초기값으로 0을 넣었습니다. 숫자 1은 검은색 사각형이 표시됩니다. 이 2차원 배열은 x, y좌표를 위한 역할입니다. 전역변수로 선언하였습니다.

    int mapOne[][] = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},

    };

[MainActivity.java]

![](http://i.imgur.com/pA5UoN5.png)

(1) 화면에 그림을 그릴 객체로 Paint instance를 사용합니다.

(2) CustomView 생성자 안에서는 Paint instance에 색깔을 setColor()로 지정합니다.

[MainActivity.java]

![](http://i.imgur.com/IF1aW7P.png)

(3) onDraw()는 canvas 객체를 참조하여 화면에 그림을 그리게 해줍니다.
		
		- 메소드 (동그라미를 그릴 때)
		canvas.drawcicle( x 좌표, y 좌표, 반지름, Paint instance)
		
		- 메소드 (정사각형을 그릴 때)
		canvas.drawRectangle( 좌상단 x좌표, 좌상단 y좌표, 우하단 x좌표, 우하단 y 좌표, Paint instance)



![](http://i.imgur.com/zN2lcy9.png)

위 그림은  분홍색 동그라미를 화면에 그리는 과정을 소개합니다.

![](http://i.imgur.com/8iHoyyd.png)

(4) 2차원 배열 변수 map을 가지고 검은색 사각형을 그립니다. 먼저 onCreate()에서는 Stage 인스턴스를 생성한 다음, init()에서 Stage 안에 map을 가져옵니다. onDraw()에서는 map을 좌표계로 사용하여 검은색 상자를 필요한 곳에 그리면 됩니다.

>> Stage 클래스는 게임 유저에게 보여줄 맵을 array list로 저장하기 위한 클래스입니다. 


(5) 위에서 만든 커스텀뷰를 실제로 모바일 화면에 보여주기 위해서는 CustomView 인스턴스를 생성한 다음 "ground.addView()"에 인스턴스를 담으면 뷰와 연결됩니다.

      				    view = new CustomView(this);
    				    ground.addView(view);


