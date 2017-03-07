[ Date : 2017. 01. 23 ]


                -------------------- Today's Topic ---------------
                                  (1) 안드로이드 구조
                                  (2) HelloWorld 프로젝트
                                  (3) Gradle
                                  (4) Lint
                                  (5) Git에 코드 업로드하기
                                  (6) Travis
                --------------------------------------------------

#[안드로이드 구조]

- Dalvik(JIT) : 기존 JavaVM과 마찬가지로 소스코드가 실행될 때 한번 컴파일을 하기 때문에 실행시 성능 저하의 우려가 있어왔다.

- ART(AOT+JIT) : 설치시 최초 한번만 컴파일하는 방식인 AOT로 설계 되었으나 효율성을 위해 AOT와 JIT를 함께 사용하는 형태로 발전하였다.

-----------------------------------------

# [HelloWorld 프로젝트]

오늘은 간단히 HelloWorld "TextView"와 Button "Button" 을 생성해 보았다.

![](http://i.imgur.com/8RhH2Tt.png)

--> 왼쪽은 .xml에 작성된 component들이 레이아웃에 나타나는 화면이다. 오른쪽은 .xml을 문석작성이 아닌 GUI로 접근할 수 있는 화면이다.


 (SettingsActivity)                                              
![](http://i.imgur.com/wu490ff.png)

--> 위 화면은 .xml에 표시된 TextView와 Button을 자바 코드와 연동하는 방법이다.
코드로는 "Type" = ("Type") findViewById(R.id.이름) 과 같은 코드로 작성하면 된다.
중요한 것은 버튼을 눌렀을 때와 같은 이벤트 발생의 경우에는 setOnClickListener를 통해 자기자신(this) 객체를 OnClick으로 보내주어 다음 동작을 작성하면 된다.

-----------------------------------------

# [Gradle]

##빌드와 컴파일의 차이는?

컴파일은 소스코드 파일을 실행파일, 라이브러리 등의 **object 파일**로 바꾸는 작업이다.

빌드는 소스코드 파일들을 컴퓨터에서 실행할 수 있는 소프트웨어로 바꾸는 과정들이다. 컴파일은 빌드의 부분으로 볼 수 있다.([http://giyatto.tistory.com/100](http://giyatto.tistory.com/100))

안드로이드 빌드는 내부적으로 컴파일과 링크를 다 같이 실행하고 최종 apk파일로 생성해주는 작업이다.

## 그렇다면 Gradle이란?
C 빌드 툴인 make를 확장한 것으로 현재 안드로이드 빌드에서 가장 많이 사용되는 툴이다.

## Gradle의 내용을 보자!(안드로이드)



![](http://i.imgur.com/OdGeIHo.png)

--> gradle 파일은 위 디렉토리를 따라가면 나온다.


 (build.gradle-module) 첫번째 화면

![](http://i.imgur.com/hP6XGs8.png)


  (build.gradle-module) 두번째 화면

![](http://i.imgur.com/oG5Qzit.png)

  (build.gradle-module) 세번째 화면

![](http://i.imgur.com/h4OP6jW.png)

마지막 세번째 화면에서 각 store별로 빌드 버전을 자동 생성하게 하려면 터미널에서 "gradlew build"를 명령어로 주면 아래 그림과 같이 google, onestore, samsung 별로 apk 파일이 생성되는 것을 확인할 수 있다.

![](http://i.imgur.com/fzuzm0Y.png)

-----------------------------------------

# [Lint]

######Lint는 코드상의 문법과 같은 문제를 파악해서 교정하는 것을 도와준다.(한글에서 철자 검사와 비슷하다...)

## Lint 설정을 수동으로 변경하는 법

![](http://i.imgur.com/qzMyqLo.png)

--> 실제 팀으로 프로젝트를 하게되는 경우, Lint 오류 메시지 때문에 컴파일 실행이 안되는 경우가 많은데 이럴 때 lint 오류를 무시하게 하는 코드를 작성하는 것이 좋다. (From 강사님 말씀......)

-----------------------------------------


# [Git에 코드 업로드하기]

![](http://i.imgur.com/QkBWK79.png)


![](http://i.imgur.com/FZVgkk0.png)

-----------------------------------------


# [Travis]

![](http://i.imgur.com/JlBG44l.png)


![](http://i.imgur.com/l6jf0yC.png)