[ Date : 2017. 02. 14(수) ]

			-------------------------Today's Topic -------------------------
								(1) DB 및 테이블 생성
								(2) xml과 기능 구현
								(3) interfaces
			----------------------------------------------------------------


간단한 메모어플리케이션 만들어보기

 프로젝트명 : [MemoBasic]

 내용 : 안드로이드를 DB와 연동하여 간단한 메모 어플리케이션을 만듭니다.(쓰기 기능)

![](http://i.imgur.com/dfmLLOk.png)

- 왼쪽 상단이 시작화면입니다.(메모의 리스트를 화면에 보여줍니다.)

- (+)을 누르면 오른 쪽 상단 화면으로 전환됩니다.(메모장)

- 내용을 입력하고 (SAVE) 버튼을 누르면 다시 원래 화면으로 돌아와서 입력한 내용을 보여줍니다.

프로젝트 순서는 DB와 연동할 객체 클래스 생성(DBHelper.java) -> 테이블로써 사용할 클래스 생성(Memo.java) -> xml과 기능 구현 -> 액티비티와 프래그먼트간 통신할 인터페이스 생성(interfaces) 입니다.

-------------------------------------------

# 1. DB 및 테이블 생성


##1.1 DBHelper.java

DBHelper.java에서 구현한 메소드는 아래입니다. week6-2의 DBHelper 클래스와 같습니다.

- DBHelper()

- onCreate()

- onUpgrade()

- getMemoDao() 

[DBhelper.java - 1]

![](http://i.imgur.com/3hCGPnM.png)

[DBhelper.java - 2]

![](http://i.imgur.com/XIn1p0e.png)

##1.2 Memo.java

[Memo.java]

![](http://i.imgur.com/V2ffPh3.png)

-위 코드는 아래 테이블을 만드는 겁니다. 이 프로젝트에서는 주로 id와 memo를 사용하고 date는 사용하지 않습니다. -

![](http://i.imgur.com/IRFnDWC.png)

-------------------------------------------------

# 2. xml과 기능 구현

## 2.1 화면 전체 UI 동작



![](http://i.imgur.com/DjbGx7Z.png)


## 2.2 MainActivity.java



### 역할 : MainActivity에 프래그먼트들을 연결시키고 DB table에서 데이터들을 가져옵니다.

[MainActivity - 1]

![](http://i.imgur.com/ku7cYle.png)

(1) onCreate() : ListFragment와 DetailFragment의 객체를 불러옵니다. 프래그먼트 매니저를 생성합니다. 그 외 초기화 작업(데이터 가져와서 세팅)을 진행합니다.

(2) loadData() : DB의 memo table에서 정보를 가져와서 datas에 저장합니다.

(3) setList() : ListFragment를 자신의 FrameLayout에 add 시킵니다.(화면에 메모 목록이 뜨게 합니다.)

[MainActivity - 2]

![](http://i.imgur.com/Nil2iLV.png)

(4)여기는 인터페이스와 관련된 부분으로 **3. interfaces** 에서 설명 드리겠습니다.


##2.2 ListFragment.java

ListFragment.java에서 해주는 일은 다음과 같습니다.

- 사용자가 저장해둔 메모들을 리스트로 보여줍니다.(리사이클러뷰, 어뎁터)
- 사용자가 +버튼을 누르면 메모를 작성하는 화면(DetailFragment)으로 전환됩니다.

[ListFragment - 1]

![](http://i.imgur.com/HB1gNOK.png)

(1) newInstance() : ListFragment의 인스턴스를 직접 생성해서 반환합니다. 이 때, Bundle에 필요한 데이터를 저장합니다.(여기서는 목록을 화면상에 보여줄 column의 개수입니다.)

> 왜 프래그먼트는 객체 생성시 new Fragment()가 아닌 newInstance()로 할까???
>
>> 안드로이드에서는 메모리가 부족시, 액티비티와 프래그먼트를 필요하다면 소멸시켰다가 나중에 재생성합니다. 프래그먼트의 경우에는 재생성될때에 호출되는 메소드가 아래와 같습니다.

>>Fragment#instantiate(Context context, String fname, Bundle args)

>>위 메소드를 보면 Bundle이 다시 넘어옵니다. 즉, newInstance() 생성시 저장해둔 Bundle 값이 다시 넘어오기 때문에 굳이 액티비티가 파기될때 onSaveInstanceState()를 하지 않아도 됩니다.

>> ( 출처 : [https://www.google.co.kr/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=fragment+newinstance&*](https://www.google.co.kr/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=fragment+newinstance&*) )

(2) onAttach() : 프래그먼트가 액티비티에 붙을 때 호출됩니다. 이 때 프래그먼트는 액티비티의 context를 저장합니다.

(3) onCreate() : onAttach() 끝나고 호출되는 메소드입니다. 이때 bundle에 저장해둔 값을 꺼냅니다.

[ListFragment - 2]

![](http://i.imgur.com/2LrbXRu.png)

(4) onCreateView() : fragment의 xml을 메모리에 inflate 시키고 리사이클러뷰를 위젯과 연결한 다음, 어뎁터를 리사이클러뷰에 세팅하면 됩니다.

[ListFragment - 3]

![](http://i.imgur.com/hwiDsH3.png)

(5) setData() : MainActivity에서 필요한 데이터를 로드하였습니다. 이 데이터를 ListFragment와 어뎁터에서 사용하려고 전달하기 위한 메소드입니다.

(6) refreshAdapter() : 저장한 메모 목록을 새롭게 표시하기 위해 사용합니다.

(7) onClick() : 사용자가 +버튼을 누르면 detailFragment 화면으로 전환됩니다. 인터페이스와 관련된 부분으로 **3. interfaces** 에서 설명 드리겠습니다.


##2.3 ListAdapter.java

[ListAdapter]

![](http://i.imgur.com/AtBFLrb.png)
![](http://i.imgur.com/NxiHYKI.png)

--> ListFragment 화면에 메모 목록을 리사이클러뷰로 보여주기 사용되는 코드입니다.

##2.4 DetailFragment.java

사용자가 메모장에 필요한 내용을 입력하고 저장 혹은 취소 버튼을 클릭합니다.

[DetailFragment]

![](http://i.imgur.com/Hthip3V.png)

onClick() 부분이 가장 핵심적이고 다른 코드들은 앞에서 배운 내용들이기에 직접 보셔도 충분히 이해할 수 있습니다. ^^

(1) btnSave() : 사용자가 입력한 내용(editMemo.getText().toString())과 현재 날짜를 Memo 인스턴스에 저장합니다. 이 인스턴스(memo)를 saveToList()를 통하여 전달합니다.
인터페이스와 관련된 부분으로 **3. interfaces** 에서 설명 드리겠습니다.

(2) btnCancel : 인터페이스와 관련된 부분으로 **3. interfaces** 에서 설명 드리겠습니다.

--------------------------------------------------

# 3. interfaces

##3.1 ListInterface.java

![](http://i.imgur.com/s2tfOW3.png)

##3.2 DetailInterface.java

![](http://i.imgur.com/VEaSyWu.png)

![](http://i.imgur.com/NdEcxij.png)