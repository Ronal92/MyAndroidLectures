[ Date : 2017. 01. 19]
						
					-----------Today's Topic---------------
						      (1) 자바 Syntax
							  (2) 게시판 만들기
							  (3) 코드(게시판) 정리 하기	
                    ---------------------------------------

#자바 Syntax

##SRP(Single Responsibility Principal)
   객체지향 언어에서 개발자는 클래스간의 관계를 설계할 때, 클래스와 다른 클래스간 의존도(응집도)는 높이고 클래스 안의 구조는 결합도를 낮추도록 설계해야한다. 이는 다른 클래스와의 의존도를 낮추어야 코드의 복잡도를 줄이고 가독성을 높일 수 있다. 즉, 하나의 클래스는 하나의 역할만 맡도록 한다. 

##Constructor
 Constructor는 클래스의 생성자이다. 객체 생성 시, 객체 안에 있는 멤버 변수들을 초기화할 때 사용한다.생성자는 클래스가 new 표현식에 의해 인스턴스화되어 객체를 생성할 때 객체의 레퍼런스를 생성하기 전에 객체의 초기화를 위해 사용되는 코드의 블록이다. 
(출처 : [http://javacan.tistory.com/entry/37](http://javacan.tistory.com/entry/37))

                          **************************
                          *  사용 예)               *
                          *  public 클래스 이름(){  *                       
                          *      // logic          *             
                          *  }                     *  
                          **************************

* this 는 클래스 내에서 객체가 자기자신의 멤버를 호출할 때, 사용한다.
* super 는 자식 클래스 내에서 부모 객체를 참조할 때 사용한다.

##toString이란?
자바에서 기본적으로 제공하는 메소드인데 보통 객체를 설명해주는 문자열을 리턴한다.
객체의 toString을 **overload**하여 다른 형식으로 출력할 수 있다.(즉 int, float 등)

여기서! OverLoad와 Override의 차이를 보자면,

      OverLoads는 하나의 class에 동일한 이름을 가진 멤버 함수를 만들어 사용하는 것이다. Return 타입이나 접근제한자는 같아도 되지만, 파라미터의 개수나 타입이 달라야 한다

      Override는 자식 클래스에서 부모 클래스에 있는 함수를 덮어(?) 사용하는 경우이다. ovveride를 할 경우 모두(Return 타입, 파라미터 개수, 타입 등)같아야 되지만 접근제한자는 달라도 된다. 단 자식의 접근제한자 범위가 부모보다 커야된다.


##접근제어자

(1) Private : 클래스 내부에서만 조작하도록 할 때 사용한다.

(2) Default : 같은 패키지 내에서만 접근이 가능하다.

(3) Protected : 같은 패키지와 다른 패키지의 자식 관계에서만 접근 가능하다. 

(4) Public : 모든 패키지와 클래스에서 접근 가능하다.



   //  접근 제어자의 범위는 아래 표를 참고하세요~~~!  //
![](http://i.imgur.com/ms47IxZ.png)

   // 접근 제어자의 사용 예(default)  //
Child 패키지의 Child 클래스 내용을 보면 name 변수가 default로 설정 되어 있다. 이 변수를 
다른 패키지(com.jinwoo.bbs)의 다른 클래스(MainBbs)에서 접근하려는 경우 에러가 나오는 것을 확인할 수 있다. 이럴 때는 name 변수의 접근제어자를 public으로 바꾸어 주어야 한다.
![](http://i.imgur.com/5CNDjGj.png)


## Static과 Non-Static의 차이
#####Static은 (객체가) 초기화 하지 않아도 프로그램이 시작 됨과 동시에 class가 메모리(Method Area)에 로드가 되는 것이다. Static으로 선언된 멤버는 객체를 선언하지 않아도 클래스 이름만으로 접근이 가능하다.

#####Non-Static은 new 클래스 이름()과 같이 객체를 선언, 초기화 해야만 메모리(heap)에 올라갈 수 있다. 따라서 Non-Static으로 선언된 멤버는 반드시 객체를 먼저 선언해야 접근할 수 있다.


그래도 모르겠다면??? 아래 그림을 봅시다.
![](http://i.imgur.com/TLSyDXT.png)

(print 함수에 접근하기 위해서는 main 함수에서 **MainBbs main = new MainBbs();** 를 선언해야 main을 통해 접근할 수 있다. 만약 객체를 생성하지 않고 **클래스**를 통해 바로 접근하려면 아래 그림과 같이 print를 static print로 바꿔주면 된다.


![](http://i.imgur.com/g53MzMW.png)

## Get과 Set의 이해
다른 클래스의 멤버가 private이면 직접 접근이 어려우기 때문에 private 멤버가 있는 클래스에서 get과 set 함수를 통해 해당 멤버에 접근할 수 있다.

get은 멤버 변수의 값을 불러온다.

set은 멤버 변수에 특정 값을 저장한다.

   (아래와 같이 변수 bbs_no 가 private으로 선언되어 있으면 getBbs_no()와 setBbs_no() 함수를 통해 접근할 수 있다.)

![](http://i.imgur.com/3tCDcQL.png)


	(main 함수에서는 getBbs_no()와 setBbs_no()를 사용해서 123 값을 bbs_no에 저장하고 불러올 수 있다.)

![](http://i.imgur.com/rFcnh0z.png)

-------------------------------------------

#게시판 만들기

### 여기서 사용할 클래스는 MainBbs와 Bbs, Controller 3가지 이다. 먼저 아래 그림을 보자.

![](http://i.imgur.com/eNg84UR.png)

> 클래스를 하나씩 설명하면,

##1. MainBbs

###### 여기서 Bbs를 객체 생성하고 내용을 입력한 다음, BbsController를 통해 databse에 저장한다.

###### MainBbs가 가지고 있는 기능은 사용자로부터 책(bbs)의 이름과 저자명, 내용을 입력받아 저장하고 출력해준다. 주요 기능은(create, read, list, exit)이 있다.

![](http://i.imgur.com/CVBNvBS.png)


##2. Bbs
###### Bbs의 bbs_no, title, content, author, datetime 멤버 변수와 각 변수의 get과 set을 가지고 있다.


![](http://i.imgur.com/rBC2xbN.png)

##3. BbsController
###### 컨트롤러 기능, 데이버베이스 기능을 한다. 
###### Method : C(Create) R(Read) U(update) D(delete)

> 메소드를 하나씩 설명하자면,

####(1) BbsController() : BbsController() 클래스 안에 있는 database를 초기화시킨다.

![](http://i.imgur.com/kTwL6hJ.png)

####(2) void create(Bbs bbs) : count는 bbs_no를 갱신하기 위한 변수이다.
######Util 클래스에 있는 날짜를 받아 설정한다.
######사용자로부터 받은 bbs를 Arraylist인 database에 추가한다.

 ![](http://i.imgur.com/puq9uRS.png)


####(3) Bbs read(int bbsno) : 사용자로부터 bbsno(책의 번호)에 해당 하는 bbs 객체를 반환한다.

![](http://i.imgur.com/4z1jpXh.png)

####(4) ◎ ArrayList<Bbs> readAll() : 저장되어 있는 모든 bbs 객체를 반환한다.

![](http://i.imgur.com/b7lsYF8.png)

####(5) ◎ void delete(int bbsno) : bbsno(책의 번호)에 해당 하는 bbs 객체를 arraylist에서 지운다.

![](http://i.imgur.com/vEYtijo.png)

####(6) ◎ void update((Bbs bbs) : 아무것도 없음.........(아직 정의 안됨)

![](http://i.imgur.com/hWoB7Jo.png)


##4. Util 
그림에는 안 나와 있지만 날씨 정보와 위의 read() 함수에서 에러를 처리할 함수들을 정의하였다.

![](http://i.imgur.com/iSzDWu7.png)

--------------------------------------------------------------------------

#코드(게시판) 정리하기

강사님이 가르쳐주신 방법을 간단히 소개하면,
![](http://i.imgur.com/xFKfCed.png)

![](http://i.imgur.com/QUmC84Z.png)