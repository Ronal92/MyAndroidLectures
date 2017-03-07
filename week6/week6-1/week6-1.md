[ Date : 2017. 02. 13(월) ]

					------------------- Today's Topic -------------------
									(1) 타이틀바 없애기
									(2) MyUtility에 사진, 갤러리 기능 추가
									(3) MySQL을 이용한 데이터베이스 맛보기
					-----------------------------------------------------

프로젝트명 : MyUtility

내용 : 5번째 탭에 사진 기능과 갤러리 기능을 추가하였습니다.


#1. 타이틀바 없애기

![](http://i.imgur.com/KF1vy1u.png)

--> 위 그림을 보면 지금까지 만든 어플리케이션과의 차이점을 발견하실 수 있습니다.


타이틀바와 상태바가 없습니다!!! 타이틀바와 상태바를 없애기 위해서는
[app]->[src]->[main]->[res]->[values]->[styles.xml]로 들어가셔서 아래 코드를 입력하면 됩니다.

		        *******타이블바 없애기 ( 내 앱의 속성 )********
		        <item name = "windowNoTitle">true</item>
		        ******* 스테이터스바 없애기  *****************
		        <item name = "android:windowFullscreen">true</item>

[values/styles.xml]
![](http://i.imgur.com/4e9Sdcy.png)




#2. MyUtility에 사진, 갤러리 기능 추가

![](http://i.imgur.com/lIe5jkU.png)

--> 이전에 만들었던 MyUtility 프로젝트에서 갤러리 탭을 하나 더 추가하였습니다.

버튼을 눌렀을 때 사진 촬영 모드로 들어가고 갤러리 기능이 포함되어 있습니다.

사진 촬영 모드는 "week5-5의 2장 사진 앱 만들기" 코드와 같습니다.( 링크 : [https://github.com/Ronal92/MyFastCampus/blob/master/5%EC%A3%BC%EC%B0%A8/week5-5.md](https://github.com/Ronal92/MyFastCampus/blob/master/5%EC%A3%BC%EC%B0%A8/week5-5.md) )

**이번 장에서는 "갤러리 기능 추가" 코드 위주로 설명하겠습니다.**


##2.1 xml 코드

![](http://i.imgur.com/s1nNn7B.png)

--> 여기서 fragment_item.xml에는 이미지뷰가, fragment_item_list.xml에는 리사이클러뷰와 버튼이 추가됐습니다.

##2.2 class 코드

###2.2.1 MainActivity.java

--> MainActivity에서는 갤러리 탭 추가와 인스턴스 생성, 프래그먼트뷰와 탭 연결 코드가 추가되었습니다.

(1) MainActivity에서 **"FiveFragment five"**를 멤버변수로 추가합니다 onCreate()에서는  **"five = FiveFragment.newInstance(3);"** 를 선언합니다. (FiveFragment는 생성자가 직접 자신의 인스턴스를 생성하여 반환해주기 때문에 new FiveFragment()를 할 필요가 없습니다. 또한 인자값은 사진이 나열될 column의 개수를 나타냅니다.)

				FiveFragment five = FiveFragment.newInstance(3);

(2)  프래그먼트뷰와 탭 연결하기
				
				final int TAB_COUNT = 5; // 전역 변수
				tabLayout.addTab(tabLayout.newTab().setText("갤러리"));
	

(3) 탭버튼 눌렀을 때 화면 전환하기

[PagerAdapter - MainActivity.java]
![](http://i.imgur.com/NkO1SAT.png)


###2.2.2 FiveFragment.java

![](http://i.imgur.com/xlg03KD.png)

(1) newInstance()

![](http://i.imgur.com/KZVa2Yq.png)

--> Bundle 안에 MainActivity에서 넘어온 fragment column의 값을 담습니다. Fivefragment 객체인 fragment에 이 값을 담으면(setArguments()) onCreate에서 자동적으로 getArguments() 호출됩니다.

(MainActivity에서 Five fragment 생성시 column의 값을 지정하기 위한 코드입니다.)


(2) onCreate()

![](http://i.imgur.com/iqxZfXv.png)

--> Bundle 안에 있는 column의 값을 확인하여 mColumn(FiveFragment의 전역변수)에 값을 넣습니다. 이 변수는 나중에 이미지를 레이아웃에 표시할 때 사용됩니다.

(3) onCreateView()

![](http://i.imgur.com/h2djmz9.png)

이 코드에서 중요한 점은
					
					첫번째, 버튼 위젯 설정.
					
					두번째, loadData()로 갤러리에 있는 이미지를 불러옵니다.
					
					세번째, view에 fragment_item_list.xml을 인플레이트 시키고 recyclerView에 리사이클러뷰 위젯인 list를 연결합니다. 
					
					네번째, 어뎁터 생성 및 recyclerView에 세팅 시킵니다. 

					

(4) loadData()

![](http://i.imgur.com/t57mjST.png)
					
					첫번째, 갤러리 어플리케이션에 있는 사진을 가져오기 위해서는 resolver를 생성해야합니다.
					
					두번째, 갤러리 어플리케이션 uri를 지정해주어야 합니다.(target)
					
					세번째, 갤러리에서 가져올 이미지 데이터들 중에서 column 값을 지정해서 필요한 정보만 가져옵니다.
					
					네번째, cursor는 가져온 이미지 데이터를 행단위로 불러냅니다. 여기서 불러낸 데이터를 String<List> 타입의 datas에 저장합니다.

					다섯번째, cursor를 닫습니다.(close())

(5) View.OnClickListener()

![](http://i.imgur.com/N5BRNaQ.png)

위 내용은 "week5-5의 2장 사진 앱 만들기" 코드와 같습니다.


(6) onActivityResult()

![](http://i.imgur.com/IGCasf0.png)

--> 사진 촬영 후 이미지 처리 하기위한 코드입니다. 

첫번째, 사진 촬영 액티비티에서 넘어온 경우(requestCode), 사진을 저장한 경우(resultCode)를 체크합니다.

두번째, 다시한번 datas에 갤러리에 저장된 이미지를 가져옵니다.(loadData())

세번째, 어뎁터에 변경된 테이터를 반영합니다.

네번째, notifyDataSetChanged()를 호출하여 시스템에 adapter 안에 데이터가 변경되었음 알려줍니다.

(7) onResume()

![](http://i.imgur.com/LwYHVUU.png)

--> 모바일 화면이 갤러리탭인 동안에는 계속 어뎁터의 변경사항을 체크합니다. 

###2.2.3 MyItemRecyclerViewAdapter.java

![](http://i.imgur.com/NOdJaTB.png)


(1) MyItemRecyclerViewAdapter()

![](http://i.imgur.com/wdnjuwT.png)

--> FiveFragment()에서 어뎁터 생성시 호출됩니다. FiveFragment()의 context를 받아 저장하고 이미지가 저장되어있는 datas도 받아서 저장합니다.

(2) onCreateViewHolder()

![](http://i.imgur.com/23ot6yi.png)

--> 여기서는 리사이클러뷰에 저장될 이미지 아이템을 인플레이트 시킵니다. 뷰홀더에 담아서 반환해야 뷰홀더가 위젯에 이미지를 세팅시킬 때 시스템 리소스 부담을 줄일 수 있습니다.

(3) onBindViewHolder()

![](http://i.imgur.com/29FacGc.png)

--> 뷰홀더에 있는 위젯과 datas에 는 이미지를 연결하여 화면에 보여주도록 합니다. 이 때, Glide를 사용하면 이미지를 비트맵이 아닌 Uri로 접근하여 많은 용량의 사진도 보여줄 수 있습니다.

(4) getItemCount()

![](http://i.imgur.com/WsGxGJC.png)

--> datas에 담긴 이미지들의 사이즈를 반환해주어야 합니다.

(5) ViewHolder class

![](http://i.imgur.com/AdNznau.png)

--> onCreateViewHolder()에서 인플레이트한 이미지 아이템들의 위젯(R.id.id)을 사용할 변수(ImageView)에 할당합니다.


#3. MySQL을 이용한 데이터베이스 맛보기

- 목표 : 실제 안드로이드에 들어가기 전 DB에서 사용하는 CRUD들을 간접 경험합니다. 

- 설치 : 구글에 MySQL 설치법 검색하면 워낙 많은 자료가 있기에 따로 설명드리지는 않겠습니다. 설치 시간은 1시간 예상하면 됩니다. ( 개인적으로 설치하는데 4시간 걸렸던............) 


## 3.1 테이블 생성하기(Create)

#### 사용법 : create table 테이블명 (컬럼명1 속성, 컬럼명2 속성);
#### 예시1 
				 create table bbs (
				    bbsno int            -- 숫자는 int, float
				    , title varchar(255) -- 숫자값 바이트의 문자열 입력시 사용
				    , content text       -- 대용량의 데이터 입력시 사용
				   );

#### 예시2


![](http://i.imgur.com/XMCphtx.png)

테이블 생성!

![](http://i.imgur.com/ZL7iJjY.png)

## 3.2 데이터 입력하기(Insert)

이제 값을 넣어보겠습니다.

#### 사용법 :  insert into 테이블명(컬럼명1, 컬럼명2) value(숫자값,'문자값');

#### 예시1 
				
				INSERT INTO bbs (bbsno, title, content) VALUES(2, '타이틀', '내용입니다');
			
#### 예시2


![](http://i.imgur.com/NnbbyjV.png)

값!!!

![](http://i.imgur.com/ioXcvbL.png)

## 3.3 데이터 읽기(Read)

#### 사용법 : select 불러올컬럼명1, 컬럼명2 from 테이블명 where 컬럼명 = 값

#### 예시1 : 

				SELECT * FROM bbs;

#### 예시2 

![](http://i.imgur.com/T4QWzTR.png)

## 3.4 데이터 수정(Update)

#### 사용법 : update 테이블명 set 변경할컬럼명1 = 값, 컬럼명2 = 값 where 컬럼명 = 값

#### 예시1 :

				update bbs set title=”LeeSunShin” where bbsno = 2;

#### 예시2 

![](http://i.imgur.com/XQAEY1H.png)

## 3.5 데이터 삭제(Delete)

####사용법 : delete from 테이블명 where 컬럼명 = 값;

#### 예시1 : 

					delete from bbs where bbsno = 1;

#### 예시2 

![](http://i.imgur.com/hyoSlMy.png)




## 3.6 id 자동증가 테이블 생성하기

####  사용법 : create table 테이블명 (컬럼명1 속성 autoincrement primary key, 컬럼명2 속성);

#### 예시

				create table bbs2 (
				    bbsno int primary key auto_increment not null -- 자동증가
				    , title varchar(255) -- 숫자값 바이트의 문자열 입력시 사용
				    , content text       -- 대용량의 데이터 입력시 사용
				   , ndate datetime
				   );
   

>> 자동증가 테이블에는 insert시에 값을 지정하지 않습니다.
INSERT INTO bbs2 (title, content) VALUES('타이틀', '내용입니다');
commit;

>> 날짜 값 넣기
insert into bbs1 (title, content, ndate) values("babo", "pooh", now());





