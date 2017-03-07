[ Date : 2017. 01. 24 ]


                          ---------------------Today's Topic-------------------------
                                            (1) 안드로이드 Layout
                                            (2) GridLayout
                                            (3) 버튼 눌러서 창 넘기기
                                            (4) 계산기 만들어보기 (라이브러리X)
                          -----------------------------------------------------------

# 1. 안드로이드 Layout

- Match parent은 화면 상에 나타나는 전체(가로,세로) 뷰를 나타낸다.
- Wrap content는 텍스트, 버튼 등이 차지하고 있는 영역만을 나타낸다.
- Padding 안의 공백(레이아웃은 그대로, 텍스트가 움직인다.)
- Margin 밖의 공백(레이아웃 전체가 움직인다.)

![](http://i.imgur.com/GBu0GOL.png)



----------------------------------
# 2. LinearLayout vs GridLayout

##LinearLayout  
   - stack 처럼 쌓는다. (horizontal, vertical) / 각 구성요소들을 비율로 나타낼 때 사용한다.
   
(세로버전)

![](http://i.imgur.com/SnYCqS4.png)


(가로버전)

![](http://i.imgur.com/GIpAAts.png)

##GridLayout 
- 구성요소(텍스트, 버튼 등)들을 2차원 배열의 테이블 형식으로 나타낼 때 사용한다.


![](http://i.imgur.com/ypSh3N8.png)




----------------------------------
# 3. 버튼 눌러서 창 넘기기


#####새 창으로 넘어가기 위해서는 아래와 같은 절차를 따른다
######(1) 클래스에 인터페이스로 View.OnClickListner를 implement한다.
######(2) 사용할 위젯(Button)의 변수를 선언한다.
######(3) 선언된 변수에 xml의 위젯 id를 불러와서 할당한다.
######(4) 변수를 리스너를 통해 onClick에 넘긴다.
######(5) 넘어온 객체(view)의 id와 위젯 id가 맞는지 확인한다.
######(6) Intent 객체에 다음 창으로 넘길 class를 인자로 받는다. 



(MainActivity)

![](http://i.imgur.com/vBFczkc.png)


----------------------------------

# 4. 계산기 만들어보기 (라이브러리X)

다음과 같은 계산기 기능을 구현해 볼 것이다.

![](http://i.imgur.com/KxBbnup.png)

버튼의 이벤트 발생하는 코드는 3장이랑 비슷한 내용이므로 아래는 계산기 수식을 구현하는 내용 위주로 소개하겠다.

![](http://i.imgur.com/bhNDjAA.png)

![](http://i.imgur.com/D7fGAMB.png)

![](http://i.imgur.com/SDFg9NF.png)

![](http://i.imgur.com/t7TL76N.png)

