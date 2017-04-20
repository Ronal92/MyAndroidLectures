[ Date : 2017. 02. 19 ]

	Weekly Summary!!
		"자바 디자인 패턴"!! 이번 위클리 서머리 주제입니다. 
		안드로이드는 자바를 기반으로 프로그래밍하기 때문에 자바에서 사용되는 디자인 패턴이
 		상당히 도움이 될 수 있다. (저도 수업을 들으면서 Observer 패턴의 실제 사용법을 이해한게 큰 도움이 되었습니다.)

		디자인 패턴이란 실무 프로그래머들이 인정한 효율적인 코딩 방법이자 구조로서, 코딩이 명확하고 단순하여 재사용이 용이합니다.

		컴퓨터 공학이셨던 분들은 잠깐 복기한다는 기분으로! 처음인 사람들은 가볍게 이해하고 넘어가시면 됩니다.^^ 

		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만들어 놓았습니다.^^(디자인 패턴 프로젝트는 이클립스로 돼있는데 추후에 올릴게요~~~~)
	   	 
---------------------------------

							****************목차******************
								(1) Singleton
								(2) Proxy
								(3) Decorator
								(4) Template Method
								(5) Factory
								(6) Strategy
								(7) Strategy CallBack
								(8) Observer 패턴
							*************************************


# 1. Singleton

Singleton 패턴은 지정된 클래스의 인스턴스를 *강제로* 단 1개만 생성하게 하는 작업입니다. 이 하나의 인스턴스를 통해서 인스턴스 내부의 정보를 Singleton 클래스를 호출한 곳에서 공유할 수 있습니다.

Singleton 클래스를 만드는 방법은 아래와 같습니다.

						첫째, private static 멤버 변수로 자기 자신의 클래스의 인스턴스를 가집니다. 

						둘째, constructor를 private으로 지정하여, 외부에서 절대로 인스턴스를 생성하지 못하게 합니다.(또한 상속하지 못하게 합니다.)

						셋째, getInstance() 메쏘드를 통해 객체를 static하게 가져올 수 있습니다. (이 때, 객체가 null인지 아닌지를 체크하여 하나의 객체만을 반환하도록 합니다.) 





## 코드.

![](http://i.imgur.com/NsEdlRA.png)


![](http://i.imgur.com/poH2D0G.png)



![](http://i.imgur.com/Q2ZUT0z.png)

----------------------------------------------------
# 2. Proxy

Proxy의 뜻은 사전적 의미로 '대리인'입니다. 즉 어떤 객체가 대리인(proxy)를 통해서 일을 처리하는 겁니다. proxy가 객체로 데이터를 전달할 때에는 자신이 받은 원본 데이터를 수정하지 않고 부가적인 기능만을 해줍니다.

![](http://i.imgur.com/VjVWpJY.png)

##코드

![](http://i.imgur.com/eSAsTDH.png)

![](http://i.imgur.com/sf3Hi3t.png)

---------------------------------------------------
# 3. Decorator

Decorator 패턴은 (프록시와 유사하지만) 원본데이터를 수정해서 보낸다는 차이가 있습니다.

![](http://i.imgur.com/BJaKdfB.png)


## 코드

![](http://i.imgur.com/ebd3Iyc.png)


----------------------------------------------------
# 4. Template Method

상위 클래스에서 처리의 흐름을 제어하며, 하위클래스에서 처리의 내용(상위클래스에서 abstract method)을 구체화합니다. 여러 클래스에 공통되는 사항은 상위 추상 클래스에서 구현하고, 각각의 상세부분은 하위 클래스에서 구현합니다. 정리하면, **상위 클래스에서 처리의 뼈대를 결정하고, 하위 클래스에서 그 구체적인 내용을 결정하는 디자인 패턴을 템플릿 메서드 패턴**이라고 한다.


![](http://i.imgur.com/no55k6k.png)




(1) TemplateMethod.java 에서는 jump() 메소드를 하위 클래스에서 정의하도록 추상 클래스로 만듭니다.

(2) Frog.java와 Rabbit.java는 TemplateMethod.java를 상속받고 jump() 를 정의합니다.

(3) MainDesignPattern에서는 Frog.java와 Rabbit.java의 내용을 모르더라도 다형성을 이용하면 됩니다. 즉 TemplateMethod 타입의 참조변수로 Frog.java와 Rabbit.java의 객체를 선언하여 필요한 연산(play() 메소드)을 진행하면 됩니다.  



-----------------------------------------------------
# 5. Factory

**인스턴스를 생성하는 공장을 템플릿 메서드 패턴으로 구성한 것이 Factory method pattern입니다.**

## 전체 코드 흐름

![](http://i.imgur.com/MOeBB09.png)

(1 )클라이언트 입장(MainDesignPattern)에서는 FactoryMethod 타입의 참조변수를 사용하여 특정 제품을 만드는 공장(TVFactory 등)의 클래스 내용을 알 필요가 없습니다. 


(2) FactoryMethod 타입의 참조변수로 make() 메소드를 호출하면 자동적으로 TVFacorty 인스턴스 안에 있는 make()가 호출되어 TV 인스턴스를 반환합니다.

(3) FactoryMethod 타입의 참조변수로 pack() 메소드를 호출하면 TVFacorty 인스턴스 안에 있는 pack()가 호출됩니다. 위의 (2) 과정이랑 다르게 여기서는 product 즉 TV를 Packed 클래스에 등록하고(setProduct()) 포장된 TV 인스턴스(packed)를 반환합니다.

## 정리

![](http://i.imgur.com/2GzcOAI.png)

Factory Method의 장점은 클라이언트가 여러 종류의 인스턴스를 직접 구분하여 생성할 필요 없습니다. 공통적으로 설계되어 있는 FactoryMethod 타입의 참조변수를 사용하면 필요한 객체만 인스턴스화해서 받으면 됩니다. 

----------------------------------------------
# 6. Strategy

Strategy 패턴은 클래스 설계시, 비슷한 기능을 하는 클래스들을 위한 작업입니다. 먼저 비슷한 기능(여기서는 방패, 검, 총)을 인터페이스로 선언한 뒤, 하위 클래스들에서 각각의 정의에 맞게 구현합니다. 클라이언트는 하위 클래스들의 내용을 구체적으로 알 필요 없이 인터페이스만을 참조하여 해당 기능 즉, 메소드를 사용하기만 하면 됩니다. Strategy 패턴의 장점은 기능을 추가할 때, 기존 코드의 변경 없이 클래스를 상속, 추가하기만 하면 됩니다.


## 코드 전체 흐름

![](http://i.imgur.com/CI1b98V.png)


(1) MainDesignPattern에서는 총, 검, 방패의 공통 기능인 Strategy를 참조변수(strategy)로 선언한 다음, 필요한 기능만을 객체로 인스턴스화( new StrategyShield() )시키면 됩니다. 

(2) Solider 클래스는 Strategy 인터페이스에 정의되어 있는 runStrategy()를 사용할 거지만, 각 기능별로 별도로 만들필요 없이 오직 하나의 runStrategy()만을 사용할 겁니다.

(3) Solier 참조변수인 context로 객체 안에 있는 useStrategy()를 호출할 때, 여러가지 runStrategy() 중에서 자동적으로 StrategyShield의 runStrategy()를 부릅니다.


>> Why?? Strategy 참조변수를 선언할 때, StrategyShield 객체를 참조하라고 명령을 주었기 때문에 당연히 Strategy 하위 클래스 중에서 StrategyShield의 메소드를 작동시킬 겁니다.



-------------------------------------------------
# 7. Strategy CallBack

실행되는 것을 목적으로 다른 오브젝트의 메소드에 전달되는 오브젝트를 말한다.
특정 로직을 담은 메소드를 실행 시키기 위해 사용하는데, 자바에선 메소드 자체를 파라미터로 전달할 방법은 없기 때문에 메소드가 담긴 오브젝트를 전달해야 한다. ( 출처 : [http://elaia.tistory.com/70](http://elaia.tistory.com/70))

## 코드 전체 흐름

![](http://i.imgur.com/ewC5Jdw.png)

--> 6장의 Strategy 패턴과 비교하면 이해가 더 잘 될 겁니다. Strategy 패턴에서 쓴 코드와의 차이는 **StrategyShield, StrategyGun, StrategySword를 클래스로 설계하여 만들지 않고 직접 익명 클래스**로 만들었습니다. 그리고 **useStrategy()에서 이 익명 클래스를 직접 Solider의 useStrategy()로 넘겼습니다.**

--> 그렇다면!!! useStrategy() 안에 있는 runStrategy()는 바로~~~~ StrategyGun의 runStrategy()를 사용할 겁니다. 위 코드에서는 StrategyGun이 직접적으로 보이지는 않지만 메소드 내용은 같습니다. 중요한 건 MainDesignPattern에서 Strategy 객체 전체를 Soldier에 넘겼고 Solder의 메소드 연산 중에서 Strategy 객체 안에 정의되어 있는 runStrategy()를 콜백한 겁니다.

![](http://i.imgur.com/dijTgQL.png)


>> 참고로, 콜백은 쓰레드 프로그래밍에서 주로 사용됩니다. 만약 A, B가 서로 다른 쓰레드인 경우에는 B의 콜백이 A의 다른 연산에 영향을 주지 않기 위해 콜백이 완전히 끝났다는 신호를 A에게 알려줍니다.

-------------------------------------------------

# 8. Observer 패턴

Observer 패턴은 문자 그대로 "관찰자"를 나타냅니다. 

**********************************************************************
상태를 가지고 있는 **주체 객체**와 상태의 변경을 알아야 하는 **관찰 객체**가 존재한다.
서로의 정보를 넘기고 받는 과정에서 정보의 단위 / 객체들의 규모 / 각 객체들의 관계 등이 복잡 혹은 커질수록 코드의 복잡성이 증가됩니다. 이 복잡성에 가이드라인을 제시해 주는 것이 바로 Observer 패턴입니다.
************************************************************************

즉 간단히 말해서, Observer는 계속 서버를 관찰하여 서버에 변경사항이 있는지를 체크하고 변경사항이 발생하면 각 Observer들에게 내용을 업데이트 합니다.

## 8.1 코드 전체 흐름


간단히 채팅 서버를 구현하였습니다. "KakaoTalkServer"를 통해서 메시지가 전송이 되면 각 "Student" 옵저버에게 메시지가 전달 받습니다. 


![](http://i.imgur.com/nQ3VL5a.png)

(1) New Student() 인스턴스를 생성하면서 Student 생성자는 자기 자신과 서버가 서로 상호 참조를 하게 합니다.

(2) 서버에 메시지를 보냅니다.- sendMessage()

(3) 서버에서 옵저버(Student)에게로  변경사항이 있음을 알려줍니다.(콜백) – update()

(4) 옵저버 (Student) 는 변경사항을 화면에 보여줍니다.(showMessage)


## 8.2 코드

[MainDesignPattern.java]
![](http://i.imgur.com/oRrHxCH.png)

[KakaoTalkServer.java]
![](http://i.imgur.com/bJsSIzC.png)

[Student.java]
![](http://i.imgur.com/1fTg1om.png)