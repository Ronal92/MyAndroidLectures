[ Date : 2017. 02. 05 ]

	Weekly Summary!!
	   	 이번주는 안드로이드에서 앞으로 어플리케이션 제작시 많이 사용하게 될 내용들 위주로 배웠습니다. RecyclerView는 모바일 화면에서 데이터들을 리스트로 보여줄 때 사용하는 형식입니다. 또한 안드로이드 OS 버전별로 권한 설정을 해야 하는 부분과 함께 다른 앱의 저장공간에서 데이터를 가져오는(!) 연습을 하였습니다.

		 그리고 MusicPlayer라는 로컬 앱에서 가장 어렵게 구현할 수 있는 어플리케이션을 만들었습니다. 음악 데이터 가져오기와 쓰레드 사용, 이미지 처리, 서비스 등 많은 개념이 들어가 있어서 반드시 정복해야될 어플 연습입니다.>.<
		 일단 이번주는 음악재생에 맞춰서 수업이 진행되었습니다.

		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만드러놓았습니다.^^

---------------------------------
							
							****************목차******************
								(1) RecyclerView
								(2) CardView
								(3) 접근 권한 설정(Runtime Permission)
								(4) A application에서 B application에 있는 데이터를 사용하는 경우 - 주소 버전
								(5) MusicPlayer 만들기
							*************************************

#1. RecyclerView

이번에 배운 RecyclerView는 ListView와는 달리 LayoutManager와 ViewHolder, Item 뷰에 애니메이션 효과를 낼 수 있는 클래스가 추가 되었습니다. ViewHolder는 리스트뷰 코드에서 생길 수 있는 위젯의 과도한 생성을 RecyclerView에서는 방지하도록 의무화 되었습니다. Layout Manager는 아이템의 배치를 수직/수평/그리드 형식으로 표현합니다.

##1-1 전체 구조

![](http://i.imgur.com/v0083hS.png)

##1-2 코드 
![](http://i.imgur.com/p7rSpkg.png)

![](http://i.imgur.com/6uhTZm9.png)

Recycler 뷰를 생성한 액티비티에서 해줘야 할 것은 다음 네가지 입니다.
									
									(1) RecyclerView 뷰 가져오기
									(2) 어뎁터 생성하기
									(3) Recycler 뷰에 Adapter 세팅하기
									(4) Recycler 뷰 매니저 등룍하기

![](http://i.imgur.com/Pumwdqb.png)

RecyclerCustomAdapter는 데이터와 뷰를 중간에서 연결해주는 객체입니다. 

						public RecyclerCustomAdapter(ArrayList<User> datas, int itemLayout)

먼저 자신의 생성자를 선언하여 인스턴스를 생성하기 전에 데이터 (datas)와 RecyclerView에 들어갈 하나의 Row (itemLayout)을 받습니다.
						public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) 

여기서는 뷰를 생성한 다음, inflate로 하나의 Row를 메모리에 올리는 작업을 합니다. 메모리에 올라간 Row는 holder에 담겨져 있게 되고 사용자가 스크롤을 내릴 때마다 없어지는 row들을 재사용할 수 있습니다.

						public void onBindViewHolder(CustomViewHolder holder, int position) 

리스트뷰에서의 getView를 대체하는 함수입니다. 여기서는 생성자에서 받은 데이터 값들을 실제로 뷰에 setting, 값을 넣는 과정입니다.
하나의 Row가 화면상에 그려지게 됩니다.

						public int getItemCount() 

총 데이터의 개수를 반환합니다.
> 이거 제대로 값 주는거 잊어먹어서 실제 실습에서 꽤 많이 해맸음.............. 보시는 분들도 주의하세요.........


![](http://i.imgur.com/vXaIyjg.png)

 CustomViewHolder는 Recycler 뷰에서 사용하는 뷰횰더를 정의한 inner class 입니다. 이 뷰홀더를 사용하는 Adapter는 generic으로선언된 부모 객체를 상속받아 구현해야 한다. onCreateViewHoler에서 CustomViewHolder를 생성하는 순간 내부의 위젯을 변수와 맵핑시킵니다.

-------------------------------------------------
# 2.CardView

![](http://i.imgur.com/aQuNsJ4.png)


CardView는 3장의 RecyclerView와 동일한 작업입니다. 리스트에 들어가는 각 Row에 애니메이션 효과를 넣어주고 버튼을 눌렀을 때 화면을 전환하는 기능을 추가하였습니다.

![](http://i.imgur.com/cUIELd2.png)

							holer.cardView.setOnClickListener

한 행의 cardView를 클릭할 때, DetailActivity로 화면이 전환되면서 number와 이름, 나이를 intent에 담아서 넘깁니다.

			        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
			        holder.cardView.setAnimation(animation);

cardView에 애니메이션 효과를 넣어줍니다.

# 3. 접근 권한 설정(Runtime Permission)

## Runtime Permissions이란? 

사용자가 접근 권한이 필요한 기능( 예를 들자면, 전화 바로 걸기)을 수행할때, 사용자로 하여금 해당 권한을 앱에 허락 할 것인지 묻고, 개발자가 아닌 사용자가 자신의 디바이스의 접근 권한을 결정하는 방식입니다. 코드에서는 런타임시에 checkSelfPermission()로 자신의 권한 승인 여부를 확인하고 requestPermission()로 사용자에게 권한 승인을 요청하게 됩니다.



##코드 

![](http://i.imgur.com/sM3uhrN.png)

권한 설정을 다루는 코드는 간단합니다. 만약 전화번호부나 음악 리스트와 데이터를 사용하는 것을 사용자에게 권한 요청하는 경우에는, 위의 노란색 상자에 있는 코드들에 필요한 내용을 추가해 주면 됩니다.

예를 들어, 기본 어플리케이션에 있는 전화번호부 목록을 사용하고 싶다면, 전화번호 목록을 읽어들이는 옵션을 추가하면 됩니다.

	checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED 

	Manifest.permission.READ_CONTACTS

	grantResults[2] == PackageManager.PERMISSION_GRANTED

여기서 grantResults[]는 사용자에게 요청한 권한 목록을 배열로 담겨있습니다. 해당 옵션이 위치한 인덱스에서 런타임 권한을 확인하면 됩니다.


----------------------------------------------------------

# 4. A application에서 B application에 있는 데이터를 사용하는 경우 - 주소 버전

##2.1 전체 구조

![](http://i.imgur.com/NhoR9a3.png)

(1) MainActivity에서 런타임권한을 먼저 체크하고 loadData()를 호출합니다.
(2) loadData()에서는 DataLoader 인스턴스를 생성하고 DataLoader 멤버변수로 load()를 사용하여 주소록에 있는 정보들을 담아옵니다. 이때 Contact 클래스가 필요한 정보를 담기 위한 ArrayList로 사용됩니다.
(3) loadData()에서 RecyclerView를 생성합니다. RecyclerAdapter를 생성하면서 (2)에서 받아온 데이터를 넘깁니다.어뎁터에서는 카드뷰를 메모리에 올려놓고 받은 데이터를 카드뷰의 위젯에 세팅시키빈다.
(4) (3)에서 생성한 RecyclerView에 RecyclerAdapter를 세팅합니다.
(5) 전체적인 뷰를 LinearLayoutManger로 화면에 출력시킵니다.

##2.2 코드

###2.2.1)

![](http://i.imgur.com/gjORHt4.png)


##### 1번

![](http://cfile5.uf.tistory.com/image/1178C5014AFDAE6A083D75)


다른 애플리케이션에 있는 데이터에 접근하기 위해서는 ContentProvider를 사용해야 합니다. ContentResolver는 컨텐트 프로바이더의 주소를 통해 해당 컨텐트 프로바이더에 접근하여 컨텐트 프로바이더의 데이터에 접근할 수 있도록 해주는 역할을 합니다. 따라서 위 1번 코드는 주소록에 접근하기 위해 시스템 자원에 접근할 수 있는 context를 사용하여Content Resolver 를 불러옵니다.

( 출처 : [http://androidhuman.com/279](http://androidhuman.com/279))



##### 2번

주소록에서 가져올 데이터 컬럼명을 정의합니다. ContactsContract.CommonDataKinds는 전화번호부 데이터를 가지고 있는 일종의 데이터 테이블입니다. 여기서 kind는 그 데이터의 종류를 의미합니다. 예를 들어 CONTACT_ID는 테이블의 한 Row가 전화번호부 아이디를 나타냅니다.


##### 3번

resolver로 전화번호부 URI에 가서 data를 query 합니다. 이 때 사용하는 메소드에 들어갈 내용은 아래입니다. 메소드의 반환값은 Cursor object입니다.

							<Query method의 arguments>
							- uri(URI) : access할 object의 URI, null이 되면 안됨.
							- projection(String[]) : 접근하고자 하는 Column 또는 attribute들
							- selection(String) : return할 record들을 결정할 수 있다.
							- selectionArgs(String[]) : selection 에 대한 binding parameter들
							- sortOrder(String) : 결과의 sort 순서를 결정할 수 있다.
	( 출처 : [http://callmansoft.tistory.com/entry/Content-Provider](http://callmansoft.tistory.com/entry/Content-Provider) )

##### 4번
 Cursor란? 안드로이드에서는 DB에서 가져온 데이터를 쉽게 처리하기 위해서 Cursor 라는 인터페이스를 제공해 줍니다. Cursor는 기본적으로 DB에서 값을 가져와서 마치 실제 Table의 한 행(Row), 한 행(Row) 을 참조하는 것 처럼 사용 할 수 있게 해 줍니다. 

							<Cursor에서 사용된 메소드>
							
							- cursor.moveToNext() : Cursor를 다음 행(Row)으로 이동 시킨다.
							- cursor.getColumnINdex() : DB 테이블의 해당 필드(컬럼) 이름을 얻어 옵니다.
							- cursor.getInt() : DB 테이블에서 해당 데이터를 Integer로 가져옵니다.
							- cursor.getString() : DB 테이블에서 해당 데이터를 String로 가져옵니다.

이렇게 가져온 데이터를 ArrayList에 Contact 타입으로 저장합니다.


###2.2.2)

![](http://i.imgur.com/v7eppNb.png)

가져온 전화번호부 목록에서 다이얼 버튼을 눌렀을 때, 전화를 걸 수 있는 기능을 추가합니다. 여기서는 [week3-4]의 묵시적 인텐트 전달을 참고하면 됩니다. 

Intent 객체에 전화를 거는 컴포넌트를 발생시킬 옵션과 Url을 담아 생성시키면 됩니다.

# 5. MusicPlayer 만들기

## 5.1 음악 재생을 위한 초기 세팅

![](http://i.imgur.com/0eQKc59.png)

####(1)

  뷰페이저로 이동할 경우, 현재 플레이 상태와 이미지를 STOP으로 하고 플레이어에 세팅된 값을 해제합니다.

####(2)

플레이어에 음원을 세팅하기 위해 음원이 있는 url 주소를 입력합니다. setLooping()은 반복여부를 묻는 함수입니다. 음원의 전체 시간을 seekBar와 textView에 표시합니다.

####(3)

		setOnCompletionListener()

setOnCompletionListener는 음원 플레이가 완료되는 경우 호출되는 리스너입니다. 자연스럽게 Next() 다음곡으로 넘깁니다.


----------------------------------------------


## 5.2 음악 재생을 위한 현재 음원 위치 가져오기.

현재 음원의 위치를 가져오기 위해서는 사용자가 음원을 클릭하였을 때와 뷰페이저를 넘겼을 때를 처리해야합니다.

사용자가 음원을 클릭하였을 때는, 액티비티가 전환이 되는 순간입니다. 인텐트로 값을 받아오면됩니다.

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");  

뷰페이저를 넘겼을 경우, 페이지 체인지 리스너(OnPageChangeListener)가 호출됩니다. 이곳에서 onPageSelected(int position)의 position 값을 액티비티의 전역변수에 저장합니다.

