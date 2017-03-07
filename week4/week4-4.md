[ Date : 2017. 02. 03 ]
 
				----------------- Today's Topic ------------
							(1) 실시간 SeekBar 적용을 위한 핸들러
							(2) 버전호환
							(3) Generic
							(4) Abstract와 Interface
							(5) Exception
							(6) Annotation
				--------------------------------------------

안드로이드 프로젝트 명 : [MusicPlayer] 


# 1. 실시간 SeekBar 적용을 위한 핸들러

### 핸들러 개념에 앞서 쓰레드란?

안드로이드는 기본적으로 뷰를 하나의 쓰레드에서 담당하도록 하는 싱글 쓰레드 모델을 원칙으로 합니다. 여기서 싱글 스레드가 바로 메인 쓰레드, 곧 UI쓰레드 입니다. 

UI는 기본적으로 메인 쓰레드에서만 접근 할 수 있기 때문에 다른 서브 쓰레드에서는 원칙적으로 접근할 수가 없습니다. 이것은 동기화가 관련된 문제이기 때문에 다음 시간에.......

### 그렇다면???

서브 쓰레드는 Looper와 Handler를 통해 Main 쓰레드에 접근하여 UI를 동작시킬 수 있습니다.
아래 그림은 Looper와 Handler의 전체 그림입니다.

![](http://i.imgur.com/nxYDHut.png)

- Looper : 메인 쓰레드 내부에 존재함. Message queue에서 받은 message나 runnable 객체를 꺼내어 Handler에게 전달한다. 메인 스레드는 Looper가 기본적으로 생성돼 있지만, 새로 생성한 스레드는 기본적으로 Looper를 가지고 있지 않고, 단지 run 메서드만 실행한 후 종료하기 때문에 메시지를 받을 수 없습니다. 

- Message Queue : 스레드가 다른 스레드나 혹은 자기 자신으로부터 전달받은 Message를 기본적으로 선입선출 형식으로 보관하는 queue이다.

- Message : 스레드 간 통신할 내용을 담는 객체

- Handler : Looper로부터 받은 Message를 실행, 처리하거나 다른 스레드로부터 메시지를 받아서 Message Queue에 넣는 역할을 하는 스레드 간의 통신 장치이다.

쓰레드를 생성하는 방법은 크게 2가지 입니다. 새 스레드는 Thread() 생성자로 만들어서 내부적으로 run()을 구현하던지, Thread(Runnable runnable) 생성자로 만들어서 Runnable 인터페이스를 구현한 객체를 생성하여 전달하던지 둘 중 하나의 방법으로 생성하게 됩니다. 

( 출처 : [https://realm.io/kr/news/android-thread-looper-handler/](https://realm.io/kr/news/android-thread-looper-handler/) )

### 이제 코드로 돌아가서 !
 
우리가 하고자 하는 것은 SeekBar를 음악 플레이와 상관없이 계속 동작시켜야 됩니다. 따라서 Main 쓰레드가 아닌 서브 쓰레드가 필요하고 이 코드가 아래 그림입니다.

![](http://i.imgur.com/frQW4JJ.png)

(1) 먼저 새로운 Thread 인스턴스를 생성합니다.

(2) run()은 Thread를 동작시키는 함수라서 이 안에 연산을 정의합니다.

(3) runOnThread() : 메인 쓰레드에게 UI 메세지를 던집니다.

(4) try catch() : player가 도중에 종료되는 경우 즉 null이 될 때, 예외 처리를 해줍니다.

--------------------------------------------
# 2. 버전호환

모바일 폰에서의 화면과 태블릿에서의 화면 사이즈가 다르기 때문에 레이아웃으로 보는 화면이 달라질 수 있습니다. 이런 차이를 극복하기 위해 해주는 설정이 '버전호환'입니다.

버전호환은 간단합니다. xml에서 직접 사이즈를 크기를 주지 않고 리소스 파일에서 제공해주면 됩니다.

![](http://i.imgur.com/TYbNSRw.png)

Values/dimens 파일과 values-w720dp/dimens로 가서 필요한 사이즈 값을 주고 name을 지정합니다.

![](http://i.imgur.com/tIZ7jaP.png)

dimes에 지정한 name을 실제 TextView 사이즈 값으로 넣어주면 버전 호환이 끝납니다^^


---------------------------------------------
# 3. Generic

ArrayList가 다룰 객체를 미리 명시해줌으로써 형변환을 하지 않고 사용하는 것입니다. 예를 들어 아래그림에서 보듯이 제네릭 타입이 명시되지 않으면, 값을 꺼내올 때 각각에 맞는 형변환을 해주어야 했습니다.

						ArrayList<> str = new ArrayList<>();
						str.add = "abcde";
						String ret_str = (String)str.get(0);

타입을 미리 ArrayList에 generic으로 정하면, 타입체크와 형변환이 생략되어 코드가 간결해집니다.

						ArrayList<String> str = new ArrayList<>();
						str.add = "abcde";
						String ret_str = str.get(0);

### 와일드 카드란?

 제네릭에서는 단 하나의 타입을 지정하지만 와일드 카드는 하나 이상의 타입을 지정 하는것 을 가능 하게 해 줍니다

![](http://i.imgur.com/hyr3LHS.png)


위 그림에서 Product 자손들을 ArrayList 타입의 매개변수로 받고 싶은 경우에는 ArrayList<? extends Product> list 와 같이 사용하면 됩니다.

( 출처 :  [http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-ArrayListT-%EC%A0%9C%EB%84%A4%EB%A6%AD%EC%8A%A4Generics%EB%9E%80](http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-ArrayListT-%EC%A0%9C%EB%84%A4%EB%A6%AD%EC%8A%A4Generics%EB%9E%80))


### Generic 패러미터 정의 방법

![](http://i.imgur.com/hZzL7Hy.png)


--------------------------------------------

# 4. Abstract와 Interface

### 공통점 

선언만 있고 구현 내용이 없는 클래스로서, 이 클래스나 인터페이스를 상속받은 자손 클래스에서는 반드시 선언되어 있는 메소들을 정의해야 합니다.


### 차이점

Abstract 클래스는 abstract 메소드가 하나라도 존재하는 클래스입니다. 구현체가 있는 경우도 있고 보통 자식클래스에서 필수적으로 구현해야할 메소드가 있을 때 abstract라고 선언합니다.

인터페이스는 구현체가 없는 경우도 있습니다. implements의 다중 구현이 가능합니다.



---------------------------------------
# 5. Exception


### Exception와 Error의 차이

-Exception : 자바에서 알 수 있는 오류

-에러 : 자바에서 알 수 없는 오류

### Exception try-catch-finally

예외가 발생한 지점에서 직접 처리합니다.

![](http://i.imgur.com/ySeU3GL.png)


( 출처 : [http://hyeonstorage.tistory.com/203](http://hyeonstorage.tistory.com/203))



-------------------------------------------
#6. Annotation

> 비즈니스 로직에는 영향을 주지는 않지만 해당 타겟의 연결 방법이나 소스코드의 구조를 변경할 수 있습니다. 쉽게 말해서 "이 속성을 어떤 용도로 사용할까, 이 클래스에게 어떤 역할을 줄까?"를 결정해서 붙여준다고 볼 수 있습니다. 어노테이션은 소스코드에 메타데이터를 삽입하는 것이기 때문에 잘 이용하면 구독성 뿐 아니라 체계적인 소스코드를 구성하는데 도움을 줍니다.

(출처 : http://www.nextree.co.kr/p5864/)


![](http://i.imgur.com/7LmCnVK.png)

annotation이 붙은 클래스에서 실제 객체에 annotation 값을 넣고 출력한 것을 결과로 확인할 수 있습니다. 아직은 어떤 쓰임새로 사용할지 잘 모르겠지만...............
코드량이 길어질수록 가독성에서는 분명 좋을 거 같습니다. 아니면 중요한 키 값 정보를 저장해두기에도 !!
