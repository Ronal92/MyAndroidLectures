[Date : 2017. 01. 16]


#Installing Sublime Text2 
링크 : [http://www.sublimetext.com/2](http://www.sublimetext.com/2)

Sublime Text2라는 에디터를 설치하여 PowerShell에서 javac HelloWorld.java로 클래스파일(바이트코드) 생성 후, java HelloWorld로 실행!!

Sublime Text 에디터 사용하기 편함. 윈도에서 리눅스명령어 사용기는 처음 봄…

!!!아주 중요한 거 : 들여쓰기 시, tab or space 둘 중 하나만 사용(파이썬에서 혼합 사용하면 컴파일이 안된다)



----------

#클래프 파일을 컴파일 하는 2가지 방식
- JIT(Just In Time) : 클래스 파일이 호출되는 순간 기계어로 컴파일 됨. 따라서 최초 실행시 약간의 속도 저하가 있다.


- AOT(Ahead of Time) : 클래스 파일 설치시 최초 한번 기계어로 컴파일된다.

----------

#코드의 메모리 상태 in JVM
- Method Area : Class나 interface를 저장하는 논리적인 메모리 공간이다.

    Type Information, Constant Pool, Field Information, Method Information, Class Variables, Reference to class (ClassLoader), Reference to class (Class)와 같이 7개로 구성된ek.
- Heap : 실제 Object와 Array 객체가 저장되는 곳이다.  
- Java Stack: Thread별로 수행중인 메소드의 정보를 저장하는 곳(**Object에 대한 reference** 정보와 local variable 등이 담겨있다.)

	 

> static code는 method area에 있기 때문에 그냥 부르면된다.
	   하지만 non-static은 heap에 올라가기 때문에 객체를 생성해야 한다.
	  **new ClassName() 이런 방식으로 heap에 올리고 stack에 있는 참조 변수를 통하여  non-static 접근이 가능하다**
	 

--------------------
#javadoc 사용법
	/** 설명 : Java 문서를 생성하기 위한 주석
	 * 
	 * @author Hunter
	 * @version 1.0
	 * 
	 */

####javadoc 문서 생성법

[Project] 우클릭 -> [export] -> [java] -> [javadoc] -> 
javadoc command : C:\Program Files\Java\jdk1.8.0_101\bin\javadoc.exe ( JDK 설치 파일의 하위 bin 디렉토리에서 javadoc.exe)

#### 문서 확인
[workspace] -> [해당 project 폴더] -> [doc] -> [index.html]
![](http://i.imgur.com/kY9Z6mn.png) 

----------
#BigDecimal 용도
왜 사용하는가? 빅데이터 등과 같은 곳에서 매우 큰 수를 사용하기 때문에 필요한 연산 과정을 객체 차원에서 집합, 제공해준다. 빠른 연산이 가능하다.
(사용 예시)
![](http://i.imgur.com/NoQlKdo.png)
----------
#toString이란? 
메서드는 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드












