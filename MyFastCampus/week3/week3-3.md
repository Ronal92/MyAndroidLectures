[ Date : 2017. 01. 25]

 					  --------------------Today's Topic----------------------
									(1) Constrained Layout
									(2) 리스너 생성방법
									(3) 단위 변환기 만들어보기(실습)
                      -------------------------------------------------------

# 1. Constrained Layout

Constrained Layout은 화면에 나타나는 뷰에서 각 아이템들이 서로간에 위치를 잡는 것을 정의한다.
즉 어떤 아이템이 위치를 잡을 때 다른 아이템과의 거리나 다른 조건들도 고려해서 배치된다는 말이다.
여기서 말하는 Constraint는 다음 3가지다.

(출처 : [https://segunfamisa.com/posts/constraint-layout-in-android](https://segunfamisa.com/posts/constraint-layout-in-android) )	

                                 ***********************************
                                 *    - An anchor point            * 
                                 *    - An edge of the layout      *
                                 *    - An invisible guide line    * 
                                 ***********************************    

![](http://i.imgur.com/OHekOTq.png)

Constrained Layout을 사용함에 있어서 먼저 Constrained layout library를 앱 dependencies에 추가해야 한다.

![](http://i.imgur.com/olsiuQ3.png)



![](http://i.imgur.com/bHoDs4D.png)

Constrained Layout을 사용하면서 느낀 점은 전체적으로 파워포인트의 객체 묶음과 같다. 한 객체를 움직이면 다른 객체도 함께 따라서움직인다. 뭔가 디자인하기에는 편할 거 같다......


# 2. 리스너 생성방법

.xml에서 화면 구성을 하고나서 각 아이템들을 유저가 눌렀을 때, 반응하도록 하는 것이 리스너이다. 리스너는 .java에서 작성한다.
이 때, 리스너를 프로그래밍하는 방법을 소개한다.

상황) 토글 버튼을 생성하여 리스너를 만드는 상황을 가정한다.

##(1) 익명 클래스를 이용한다.

![](http://i.imgur.com/pxvVuCO.png)



##(2) 이벤트 리스너를 implement하는 클래스를 생성한다.

![](http://i.imgur.com/MF9u3ji.png)

##(3) (2)번과 비슷한 방법이지만 리스너를 넘길 때 자기자신(this)를 넘기는 방법

다른 아이템(버튼, 텍스트뷰 등)에서도 많이 사용되는 방법이다.

![](http://i.imgur.com/HR2E4qc.png)

# 3. Spinner와 SeekBar의 생성방법

## (1) Spinner

### Spinner 그림 


![](http://3.bp.blogspot.com/-d1pt2GTOZ-I/UjxbCVF-rlI/AAAAAAABGPc/Ed43IyMjGSo/s1600/android-spinner-style-01.PNG)

(출처 : [http://www.broculos.net/2013/09/how-to-change-spinner-text-size-color.html#.WIinclWLTIU](http://www.broculos.net/2013/09/how-to-change-spinner-text-size-color.html#.WIinclWLTIU))


### Spinner 코드


![](http://i.imgur.com/wt1Osty.png)

## (2) SeekBar

### SeekBar 그림
![](http://www.android-examples.com/wp-content/uploads/2016/03/setOnSeekBarChangeListener-250x175.png)

(출처 : [http://www.android-examples.com/category/android-basics/](http://www.android-examples.com/category/android-basics/))

### SeekBar 코드
![](http://i.imgur.com/6mtpIfi.png)