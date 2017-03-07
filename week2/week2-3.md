[ Date : 2017. 01. 18]

							---	오늘의 학습내용 ---
							(1) 배열 이해하기
							(2) Collection FrameWork
							(3) String 관련 함수 사용
							(4) 달팽이코드

#1. 배열 이해하기

###1차원 배열
 
###### 선언 

	 ①	int array[] = new int[6];
				
	 ②	int[] array2 = new int[6];


> 위 두 가지 방식중에서 ②이 코드 캡쳐링하기가 편하다.(코드 캡처링이란? 수많은 코드줄 중에서 원하는 변수명이나 메소드를 검색으로 찾는 행위)
> 
> 만약 ①번을 사용해서 코드 캡처링을 하려면 int a_array[]로 바꾸는 게 좋다. (From 강사님..)


###### 출력
	사용 예) 
            *****************************************************************************
			* int index = 0;                                                            *
			* For(index = 0; index<10 ; index++){                                       * 
			*     System.out.println(arr[index]); // array[0] 부터 array[9]까지 출력     *
			* }                                                                         *
            *****************************************************************************



###2차원 배열

###### 선언
![](http://i.imgur.com/veyRmkS.jpg)
                

###### 출력
![](http://i.imgur.com/PJRAeP4.png)

###3차원 배열



###### 선언
![](http://i.imgur.com/9T1QsnM.jpg)


###### 출력
![](http://i.imgur.com/dtrPJrD.jpg)

이클립스에서 출력하면 아래 그림과 같이 나오는 걸 확인할 수 있다.


![](http://i.imgur.com/ybYbiIM.jpg)


----------


#2. Collection Framework
 Collection Framework란 배열이 가지고 있는 불편함, 한계를 쉽게 벗어나게 도와주고 인스턴스의 저장 및 참고를 목적으로 하는 프레임 워크이다.

무슨 말인지 알겠으니 이제 프레임워크로써 사용하는 ArrayList를 살펴보면!!!

그 선언은 아래와 같이한다. 

                           ArrayList nameList = new ArrayList();
								
							여기서 new ArrayList()는 ArrayList라는 내장된 클래스를 인스턴스화 시키는 거고
						   nameList를 인스턴스화된 new ArrayList()를 참조하는 것이다.



## ArrayList의 사용
ArrayList nameList = new ArrayList();

nameList.add(myName);

System.out.println(i +"th persion : " + nameList.get(i));

=====> 위 코드는 nameList라는 변수를 참조해서 ArrayList에 자신의 이름을 추가(.add())하고 출력(.get())하는 코드이다.

		.add() : ArrayList에서 제공되는 함수로서 객체 참조하고 있는 리스트에 데이터를 추가한다.
		.get() : ArrayList에서 제공되는 함수로서 객체 참조하고 있는 리스트이 특정 인덱스에 있는 값을 불러온다.

>  추가로 강사님이 알려주신 팁은 ArrayList를 for-loop를 통해 출력할 때는 ArrayList.size()를 따로 변수로 두어 For-loop의 조건문에 사용한다.
>  
		***************************************************
        *   int i = 0;                                    *
        *   int nameListSize = nameList.size();           *
        *                                                 *
        *   for(i = 0; i < nameListSize; i++){            *
        *       System.out.println(nameList.get(i));      * 
        *    }                                            *
        ***************************************************



## ArrayList의 장점

장점을 보기 전에 먼저 알아야 하는 개념이 **boxing과 unboxing**이다. 박싱은 int나 float 값을 객체에 넣어주는 일이고, 언박싱은 객체에 넣은 값을 다시 int, float type에 할당하는 일이다.(출처 : [http://ammff.tistory.com/20](http://ammff.tistory.com/20) ) 

ArrayList의 장점은 Collection의 타입(String , Integer 등)이 달라도 같은 ArrayList에 담을 수 있다는 점이다. 즉 원하는 int나 float 등의 값을 먼저 Integer 혹은 Float 객체에 넣어주고(이것이 언박싱이다) 다시 ArrayList에 넣어주면 된다. 아래 코드를 보자!
![](http://i.imgur.com/lSH3F1d.jpg)


#3. String 관련 함수 사용

아래 코드에 있는 변수를 가정하고 이해하면 된다.!!

		
				               ***********************************
				               *     String a = "128766";        *
				               *     String b = "123456";        *
				               *     String c = "158799";        *
				               ***********************************     


##문자열 비교
**System.out.println(a.compareTo(b));**

**System.out.println(a.compareTo(c));**

**System.out.println(a.equals(b));**

**System.out.println(a.equals(c));**

[출력화면]

![](http://i.imgur.com/cABDhaM.png)

##문자열의 인덱스
**System.out.println(a.charAt(2));**

[출력화면]

![](http://i.imgur.com/y8L5Jve.png)

##문자열 합치기
**System.out.println(a+b);**

[출력화면]

![](http://i.imgur.com/T8arv7c.png)

##문자열이 무엇으로 시작하는지 파악
**System.out.println(a.startsWith("23"));**

**System.out.println(a.endsWith("31"));**

[출력화면]

![](http://i.imgur.com/oPZrfkc.png)


##문자열에서 찾고자 하는 데이터의 위치 알아내기
**System.out.println(a.indexOf("8"));**

[출력화면]

![](http://i.imgur.com/YdZzf0J.png)


##문자열의 길이
**System.out.println(a.length());**

[출력화면]

![](http://i.imgur.com/Assottr.png)


##문자열 변경
**System.out.println(a.replace("1","X"));**

[출력화면]

![](http://i.imgur.com/weCgDgY.png)


##문자열 자르기
**System.out.println(a.substring(3)); // 3번째부터 문자를 데리고 온다.**


**System.out.println(a.substring(3,4)); // 3번과 4번 사이의 문자를 데리고 온다.**



[출력화면]

![](http://i.imgur.com/k7WC2i7.png)

##문자열 분리하기
**String value = "abcd";**

**String values[] = value.split(""); //  문자열을 분리할 기준문자"/".**

**for(String item : values){**

**System.out.print(item + " ");**

**}**


[출력화면]

![](http://i.imgur.com/KPfR32g.png)


##숫자 --> 문자 변환
**String ccc = 888 + "";**


[출력화면]

![](http://i.imgur.com/UpTPJ1x.png)


##문자 --> 숫자 변환
**int ddd = Integer.parseInt(ccc);**
**long eee = Long.parseLong(ccc);**

[출력화면]

![](http://i.imgur.com/COVe0Ot.png)


##int --> char 변환 // char 범위보다 큰 값이 입력되면 절삭됨.
**ddd=888;**
**char fff = Integer.toString(ddd).charAt(0);**


[출력화면]

![](http://i.imgur.com/nEuQTEY.png)


##하나의 숫자를 char로 변형하다. (문자열보다 효율적일 때)
**int argNum = 8; // 입력하는 숫자**
**int argDigix = 10; // 진법**
**char ch_argNum = Character.forDigit(argNum, argDigix);**




##문자열을 한글자씩 char로 분해
**String target = "8888";**
**char arrs[] = target.toCharArray();**


[출력화면]

![](http://i.imgur.com/Z173KM3.png)
##배열 정렬
**int[] arrs1 = {13, 34, 22, 4, 29};**

**Arrays.sort(arrs1); // Arrays는 "import java.util.Arrays"를 import하여 사용한다.**

**System.out.println();**


**System.out.print("After : ");**

**for(index = 0; index < arrs1.length; index++){**

**System.out.print(arrs1[index] + " ");**

**}**


[출력화면]


![](http://i.imgur.com/lhJWeeM.png)

#5. 문자열 거꾸로 출력하기
아래 알고리즘은 간단하다. 먼저 사용자로부터 문자열을 입력받은 다음 loop를 통해 문자열(array)의 끝에서부터 0까지 값을 다른 문자열(result)에 0부터 마지막까지 저장하면 된다.

![](http://i.imgur.com/McA0Ib1.png)

#5. Annagram
Anagram은 입력받은 단어 a, b를 비교하여 가지고 있는 문자가 서로 같은지를 확인하는 말이다.

![](http://i.imgur.com/GQgoTMe.png)

