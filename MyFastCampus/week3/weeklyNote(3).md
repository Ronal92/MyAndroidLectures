[Date : 2017. 01. 29]

	Weekly Summary!!
		 이번주는 안드로이드를 처음 배우기 시작하여 안드로이드의 구조와 레이아웃, 이벤트 리스너 등을 기본적으로 배웠습니다. 실습한 내용과 코드는은 저의 [https://github.com/Ronal92/MyFastCampus.git](https://github.com/Ronal92/MyFastCampus.git)을 참고하시면 되고 중요한 거 위주로 요약해 놓았습니다.^^

---------------------------------
#1. 안드로이드 구조 <-week3-1

- Dalvik(JIT) : 기존 JavaVM과 마찬가지로 소스코드가 실행될 때 한번 컴파일을 하기 때문에 실행시 성능 저하의 우려가 있어왔습니다.

- ART(AOT+JIT) : 설치시 최초 한번만 컴파일하는 방식인 AOT로 설계 되었으나 효율성을 위해 AOT와 JIT를 함께 사용하는 형태로 발전하였습니다.

------------------------------------------

#2. Gradle <-week3-1

##빌드와 컴파일의 차이는?

컴파일은 소스코드 파일을 실행파일, 라이브러리 등의 **object 파일**로 바꾸는 작업입니다.

빌드는 소스코드 파일들을 컴퓨터에서 실행할 수 있는 소프트웨어로 바꾸는 과정들이다. 컴파일은 빌드의 부분으로 볼 수 있습니다.([http://giyatto.tistory.com/100](http://giyatto.tistory.com/100))

안드로이드 빌드는 내부적으로 컴파일과 링크를 다 같이 실행하고 최종 apk파일로 생성해주는 작업입니다.

## 그렇다면 Gradle이란?
C 빌드 툴인 make를 확장한 것으로 현재 안드로이드 빌드에서 가장 많이 사용되는 툴입니다.

## Gradle의 내용을 보자!(안드로이드)



![](http://i.imgur.com/OdGeIHo.png)

--> gradle 파일은 위 디렉토리를 따라가면 나옵니다.


 (build.gradle-module) 첫번째 화면

![](http://i.imgur.com/hP6XGs8.png)


  (build.gradle-module) 두번째 화면

![](http://i.imgur.com/oG5Qzit.png)

  (build.gradle-module) 세번째 화면

![](http://i.imgur.com/h4OP6jW.png)

마지막 세번째 화면에서 각 store별로 빌드 버전을 자동 생성하게 하려면 터미널에서 "gradlew build"를 명령어로 주면 아래 그림과 같이 google, onestore, samsung 별로 apk 파일이 생성되는 것을 확인할 수 있습니다.

![](http://i.imgur.com/fzuzm0Y.png)

------------------------------------------------


# 3. 안드로이드 Layout <-week3-2

- Match parent은 화면 상에 나타나는 전체(가로,세로) 뷰를 나타낸다.
- Wrap content는 텍스트, 버튼 등이 차지하고 있는 영역만을 나타낸다.
- Padding 안의 공백(레이아웃은 그대로, 텍스트가 움직인다.)
- Margin 밖의 공백(레이아웃 전체가 움직인다.)

![](http://i.imgur.com/GBu0GOL.png)

-----------------------------------------------

# 4. Constrained Layout <-week3-3

Constrained Layout은 화면에 나타나는 뷰에서 각 아이템들이 서로간에 위치를 잡는 것을 정의합니다.
즉 어떤 아이템이 위치를 잡을 때 다른 아이템과의 거리나 다른 조건들도 고려해서 배치된다는 말입니다.
여기서 말하는 Constraint는 다음 3가지입니다.

(출처 : [https://segunfamisa.com/posts/constraint-layout-in-android](https://segunfamisa.com/posts/constraint-layout-in-android) )	

                                 ***********************************
                                 *    - An anchor point            * 
                                 *    - An edge of the layout      *
                                 *    - An invisible guide line    * 
                                 ***********************************    

![](http://i.imgur.com/OHekOTq.png)

Constrained Layout을 사용함에 있어서 먼저 Constrained layout library를 앱 dependencies에 추가해야 합니다.

![](http://i.imgur.com/olsiuQ3.png)



![](http://i.imgur.com/bHoDs4D.png)

----------------------------------------------

# 5. 버튼 눌러서 창 넘기기 <-week3-3



#####새 창으로 넘어가기 위해서는 아래와 같은 절차를 따른다
######(1) 클래스에 인터페이스로 View.OnClickListner를 implement한다.
######(2) 사용할 위젯(Button)의 변수를 선언한다.
######(3) 선언된 변수에 xml의 위젯 id를 불러와서 할당한다.
######(4) 변수를 리스너를 통해 onClick에 넘긴다.
######(5) 넘어온 객체(view)의 id와 위젯 id가 맞는지 확인한다.
######(6) Intent 객체에 다음 창으로 넘길 class를 인자로 받는다. 

> 버튼 말고도 텍스트뷰와 Spinner, SeekBar가 있고 버튼 리스너 생성에도 여러 방법이 존재합니다. week3-3을 참고하시면 됩니다:)

-----------------------------------------

#6. Activity의 생명주기 <-week3-4

![](http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585)


안드로이드 어플리케이션 개발 할 때, 가장 기본이 되는 단위가 Activity입니다 Activity는 stack구조*(스택 구조를 모르신다면 제 git의 1-3을 방문하시길......)*로 쌓이게 되는데 가장 최근에 활성화된 된 화면이 stack의 최상단 부분입니다

#### 액티비티에는 4가지 상태가 있습니다.

           (가) Active : 현재 활성화되고 있는 창이다.이 액티비티가 활성화되면 기존의 활성된 액티비티는 Pause가 된다.
           (나) Pause : 투명한 액티비티나 활성화된 액티비티가 기존의 액티비티를 일부 가리는 상태이다. 액티비티가 완전히 가려지게 되면 가려진 액티비티는 Stop 상태가 된다.
           (다) Stop : 화면에 나타나지 않는 액티비티이다. 액티비티가 화면 밖으로 나가거나 닫히고 나면 그 액티비티는 Inactive가 된다.
           (라) Inactive : activity stack에서 제거된 상태이다. 다시 화면에 나타나기 위해서는 재시작되어야 한다.

(출처 :[http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585](http://cfile6.uf.tistory.com/image/135CA30D4B6FBAEC7BD585) )

----------------------------------------

#7. Logger Class  <-week3-4에 안 담긴 내용......
Logger 클래스는 자신의 코드를 디버그하기 위해 정의한 클래스입니다. Log.v, Log.d 등을 선언해 놓았습니다. 가장 중요한 거는 개발자의 코드가 릴리즈 버전인지 개발 버전인지를 자동으로 구분으로 로그 기록을 남깁니다

## 선언

![](http://i.imgur.com/pFY6CUT.png)

## 사용


![](http://i.imgur.com/ycTrhgz.png)

> 그 밖에 [다른 액티비티에 값 넘기기], [호출한 앱으로부터 값 돌려받기], [묵시적 클래스 전달], [커스텀 브라우저(WebView)] 가 코드와 함께 자세히 설명 되어있습니다. [https://github.com/Ronal92/MyFastCampus.git](https://github.com/Ronal92/MyFastCampus.git)
링크에서 3주차 week3-4를 보시면 됩니다.