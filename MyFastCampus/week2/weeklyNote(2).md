[Date : 2017. 01. 21]

#Weekly Summary!!

-------------------------------
#1. 자바의 컴파일 과정
![](http://i.imgur.com/o8Cuk4p.png)

자바는 사용자가 프로그래밍한 코드를 먼저 javac를 통해 컴파일 한다. 컴파일 된 코드는 .class 파일(바이트코드)로 생성된다. JVM은 바이트코드를 위한 Interpreter인데 JVM이 설치된 시스템이라면 바이트 코드를 실행시킬 수 있다. 

#클래프 파일을 컴파일 하는 2가지 방식 (<-wee2-1)
- JIT(Just In Time) : 클래스 파일이 호출되는 순간 기계어로 컴파일 됨. 따라서 최초 실행시 약간의 속도 저하가 있다.


- AOT(Ahead of Time) : 클래스 파일 설치시 최초 한번 기계어로 컴파일된다.

---------------------------------------
#코드의 메모리 상태 in JVM (<-wee2-1)
- Method Area : Class나 interface를 저장하는 논리적인 메모리 공간이다.
- Heap : 실제 Object와 Array 객체가 저장되는 곳이다.  
- Java Stack: Thread별로 수행중인 메소드의 정보를 저장하는 곳
	 
★실제 자바 코딩을 하면서 각각의 코드가 어디 메모리에 올라가는 지를 생각하면서 코딩하면
실수를 줄일수 있다.

--------------------------------------------

#2. Java Syntax

## 반복문 (<-wee2-2)
1. For-loop
 
 - 형식 : For(초기값 ; 조건문; 증감)

 - 사용 예 : 1부터 10까지의 합을 더한다.

				*****************************
				*	For(i=1; i<=10; i++){   *
				*		sum+=i;             *
				*	}                       *
                *****************************
				

2. While-loop

 - 형식 : while(조건문)

 - 사용 예 : 1부터 10까지의 합을 더한다.

				*****************************
				*	i=1   					*
				*	while(i<11){            *
				*		sum+=i;             *
				*		i++	                *
				*	}                       *
                *****************************

3. do-While

 - 형식 : do{}while{조건문}; !!세미콜론 꼭

 - 사용 예 : 1부터 10까지의 합을 더한다.

				*****************************
				*	i=1   					*
				*	do{       	            *
				*		sum+=i;             *
				*		i++	                *
				*	}while(i<11);           *
                *****************************

## 조건문 (<-wee2-2)

1. if문

 - 특정 범위의 조건을 명시할 때 사용한다.
 - 사용 예 : 짝수와 홀수 비교
 	
				*****************************
				*	if((num%2)==0)          *
				*		return 0; // 짝수   *			
				*	else				    *
				*		return 1; // 홀수   *
                *****************************


2. switch문
 - if와는 달리 특정값으로 비교한다.
 - 사용 예 : 값 비교
 
				*****************************
				*	switch(num){			*
				*		case 1 : 실행문;	  *
				*				 break;     *
				*		case 2 : 실행문;	  *
				*				 break;		*
				*		case 3 : 실행문;	  *
				*				 break;	    *
				*		default: 실행문;	  *
				*				 break;		*
				*  }						*				 
				*****************************


## 배열 이해하기 (<-wee2-3)

#### 1차원 배열
 
######(1) 선언 

![](http://i.imgur.com/lPDZ1D3.png)


> 위 두 가지 방식중에서 ②이 코드 캡쳐링하기가 편하다.(코드 캡처링이란? 수많은 코드줄 중에서 원하는 변수명이나 메소드를 검색으로 찾는 행위)

######(2) 출력

![](http://i.imgur.com/SiZMQ9A.png)

#### 2차원 배열

######(1) 선언
![](http://i.imgur.com/veyRmkS.jpg)
                

######(2) 출력
![](http://i.imgur.com/PJRAeP4.png)

#### 3차원 배열



######(1) 선언
![](http://i.imgur.com/9T1QsnM.jpg)


######(2) 출력
![](http://i.imgur.com/dtrPJrD.jpg)



## Constructor (<-wee2-4)
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


##접근제어자 (<-wee2-4)

(1) Private : 클래스 내부에서만 조작하도록 할 때 사용한다.

(2) Default : 같은 패키지 내에서만 접근이 가능하다.

(3) Protected : 같은 패키지와 다른 패키지의 자식 관계에서만 접근 가능하다. 

(4) Public : 모든 패키지와 클래스에서 접근 가능하다.



   //  접근 제어자의 범위는 아래 표를 참고하세요~~~!  //
![](http://i.imgur.com/ms47IxZ.png)

##Overloading vs Overriding 

![](http://i.imgur.com/THa4hql.png)

##파일 입출력 (<-wee2-5)

여기서는 사용자 입장에서 가장 쓰기 쉬운 NIO 코드를 소개한다. IO에 대한 추가 코드는 (week2-5)를 참고하면 된다.


##FileUtil클래스의 readNio()          
readNio는 NIO에서 파일로부터 입력을 받을 때 사용하도록 정의한 함수이다.

NIO에서 파일로부터 read를 할 때에는 Path를 통해 먼저 불러올 경로를 지정한다.

Files 클래스에 있는 함수(readAllBytes, readAllLines 등)를 사용하여 파일로부터 데이터값을 한번에 읽어들인다.

![](http://i.imgur.com/o7Sc0Xo.png)


##FileUtil클래스의 writeNio()
writeNio()는 NIO에서 파일에 출력할 때 사용하도록 정의한 함수이다.

NIO에서 파일에 write를 할 때에는 Path에 먼저 저장할 경로를 지정한다.

Files.write()를 통해 해당 경로에 사용자의 문자열이 저장된 파일을 쓰도록 한다. 이때, Files.write() 안에는 *content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND* 등과 같은 옵션이 있기 때문에 필요한 옵션을 사용하면 된다. 

![](http://i.imgur.com/jZYG3iO.png)


##String 합치기 (<-wee2-5)
--강사님 권고 사항--

                          ************************************************
                          *     StringBuffer sbf = new StringBuffer();   *
                          *     sbf.append(“aaa”);                       *
                          *     sbf.append(“bbb”);                       *
                          ************************************************  

                          ************************************************
                          *     StringBuilder abd = new StringBuilder(); *
                          *     sbf.append(“aaa”).append(“bbb”);         *
                          *     sbf.append(“bbb”);                       *
                          ************************************************   

                          ************************************************
                          *     String a = “aaa” + “bbb”                 * 
                          ************************************************    

가급적 StringBuffer 또는 StringBuilder를 사용할 것.!!!!

##Java Doc 작성법 (<-wee2-1)


	/** 설명 : Java 문서를 생성하기 위한 주석
	 * 
	 * @author Hunter
	 * @version 1.0
	 * 
	 */

####javadoc 문서 생성법

[Project] 우클릭 -> [export] -> [java] -> [javadoc] -> 
javadoc command : C:\Program Files\Java\jdk1.8.0_101\bin\javadoc.exe ( JDK 설치 파일의 하위 bin 디렉토리에서 javadoc.exe)




#3.Java 코드 진행상황

 자바코딩이 시작된 첫날은 자바 문법에 익숙해지기 위해 게시판 기능을 제공하는 코드들을 작성하였다. 둘째날부터는 프로그램의 종료와 상관없이 데이터를 저장하기 위해 파일 입출력 기능을 코드에 추가하였다. 현재는 사용자의 입력을 추가하는 부분만 작성된 상태이고, 삭제와 읽기 기능을 추가해야 한다........


### 첫날 : MainBbs와 Bbs, Controller (<-wee2-4)

![](http://i.imgur.com/eNg84UR.png)


### 둘째날 : MainBbs와 Bbs, Controller (<-wee2-5)

![](http://i.imgur.com/RUsKch0.png) 