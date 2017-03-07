[ Date : 2017. 02. 06 ]

					--------------- Today's Topic ------------
								(1) Fragment
								(2) 프로젝트명 : FragmentControl
								(3) 프로젝트명 : FragmentTap						
					------------------------------------------

# 1. Fragment
##1.1 Fragment란??
fragment는 activity의 부분이라고 보면 됩니다. 여러 개의 프래그먼트를 하나의 액티비티에 조합하여 **창이 여러 개인 UI**를 구축할 수 있으며, 하나의 프래그먼트를 여러 액티비티에서 **재사용**할 수 있습니다. 

#######왜 사용하는가? 태블릿과 같은 Wide Screen에서 Activity라는 큰 틀안에 뷰만을 사용하여 다양한 내용을 넣고 관리하기가 힘들기 때문입니다. fragment는 activity의 영역을 분할, 관리할 수 있도록 해줍니다. Activity처럼 관련된 코드들을 한곳에 묶을 수도 있고, 일반 뷰처럼 애플리케이션 레이아웃에 Fragment를 자유롭게 배치할 수도 있습니다

![](http://wanochoi.com/wp-content/uploads/2016/02/activity_view_fragment.jpg)

( 출처 : [http://wanochoi.com/?p=1990](http://wanochoi.com/?p=1990) )

##1.2 Fragment의 생명 주기

![](https://i.stack.imgur.com/1ztBy.png)

- onAttach() : 프래그먼트가 액티비티 레이아웃에 포함되는 순간 호출됩니다.

- onCreate() :  프래그먼트가 최초로 생성될 때 호출됩니다.

- onCreateView() : 프래그먼트의 UI를 구성하는 뷰(View)를 반환합니다.

- onActivityCreated() :

- onStart() : 프래그먼트가 화면에 표시될 때 호출됩니다.

- onResume() : 프래그먼트가 사용자와 상호작용을 할 수 있게 되었을 때 호출됩니다.

- onPause() : 프래그먼트가 아직 화면에 표시되고 있는 상태이나, 다른 요소에 의해 프래그먼트가 가려져 상호작용을 하지 못하는 상태입니다.


- onStop() : 프래그먼트가 화면에서 보이지 않게 될 때 호출됩니다. Bundle 형태로 저장됩니다.

- onDestroyView() : 프래그먼트가 화면에서 사라진 후, 뷰의 현재 상태가 저장된 후 호출됩니다.

- onDestroy() : 프래그먼트가 더 이상 사용되지 않을 때 호출됩니다. 

- onDetach() : 프래그먼트가 액티비티 레이아웃에서 제거될 때 호출됩니다. 

## 1.3 프래그먼트 생성

프래그먼트를 생성하려면 Fragment의 서브클래스(또는 이의 기존 서브클래스)를 생성해야 합니다. Fragment를 상속 받으면 onCreate(), onResume()과 같은 콜백함수들이 호출됩니다.

## 1.4 프래그먼트와 뷰 연결하기
프래그먼트에는 기본적으로 setConentView()같은 메소드가 없기 때문에 inflater를 사용하여 xml을 객체 생성한후 메모리에 올려야 합니다. 이 inflater를 제공하는 함수가 바로 **onCreateView()** 입니다.



			@Override
			    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                             Bundle savedInstanceState) {
			        // Inflate the layout for this fragment
			        return inflater.inflate(R.layout.example_fragment, container, false);
			    }
			}


##1.5. 프래그먼트 관리
FragmentManager를 사용하여 프래그먼트를 관리할 수 있습니다. FragmentManager가 할 수 있는 일은 여러가지 있는데 그 중에 몇 가지를 소개하자면,

			- 액티비티 내에 존재하는 프래그먼트를 findFragmentById()로 가져오기
			- popBackStack()을 사용하여 프래그먼트를 백 스택에서 꺼냅니다(사용자가 Back 명령을 시뮬레이션).
			- beginTransaction()으로 트랜잭션을 시작할 수 있습니다.

>> 트랜잭션(Transaction)이란?? Transaction은 시스템이 에러가 날 경우 다시 초기화 해야하는 일련의 과정들 혹은 연속되는 일들의 단위입니다.


## 1.5 프래그먼트 트랜잭션

트랜잭션은 액티비티에 커밋한 변경 내용(추가, 제거, 교체 등)의 집합을 말합니다.

트랜잭션의 사용 예는 아래를 보시면 됩니다.
				
				// 프래그먼트 매니저(getFragmentManager())를 사용하여 트랜잭션을 시작합니다.
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				// 프래그먼트의 변경 사항을 적용합니다.
				transaction.replace(R.id.fragment_container, newFragment);
				// 트랜잭션을 프래그먼트 트랜잭션의 백 스택에 추가합니다.
				transaction.addToBackStack(null);
				// commit을 해야 트랜잭션이 수행됩니다.
				transaction.commit();

>> 여기서 잠깐!! 트랜잭션의 저장 VS 프래그먼트의 저장

![](http://i.imgur.com/YBdzAKZ.png)

트랜잭션은 Activity의 BackStack에 저장되지만, 프래그먼트는 하나의 트랜잭션에서 적재되는 구조입니다. 예를 들어 위의 replace 코드를 remove로 바꾸게 된다면 프래그먼트만 제거되고 트랜잭션은 메모리에 그대로 남게됩니다. 만약 트랜잭션 또한 같이 메모리에서 제거하려 한다면 사용자의 백버튼(onBackPressed())를 사용합니다.

## 1.6 액티비티로의 이벤트 콜백 

좀 어려운 개념입니다..... 간단히 설명하면, 프래그먼트 내부에 콜백 인터페이스를 정의하고 해당 호스트가 이 인터페이스를 구현하는 구조입니다. 프래그먼트와 액티비티간에 이벤트를 공유하는 겁니다.

![](http://i.imgur.com/rUSurpO.png)

OnListener()는 Fragment 내부에 선언된 인터페이스입니다. 프래그먼트를 호스팅하는 액티비티가 OnListener 인터페이스를 구현하고 goUri()를 재정의합니다. 프래그먼트 A의 onAttach() 콜백 메서드가 OnListener의 인스턴스를 생성합니다. 이때 onAttach()로 전달되는 Activity를 형변환하여 Activity에 있는 goUri()를 동작시킵니다.

-----------------------------------------------------

#2. 프로젝트명 : FragmentControl

##2.1 동작 흐름

![](http://i.imgur.com/hSTMZyA.png)

activity_main에 있는 FrameLayout에 fragment 레이아웃들이 나타납니다. 여기서는 프래그먼트 동작시 필요한 코드들을 배우고 메모리가 어떻게 동작하는지 배웁니다.

fragment_detail과 fragment_list의 코드는 앞의 1-6의 이벤트 콜백과 같기 때문에 MainActivity 위주로 설명하겠습니다.

##2.2 코드

![](http://i.imgur.com/Kvfk3nG.png)

setList()에서 해주는 일은 fragment를 실행하기 위한 트랜잭션 시작하고 activity_main에 있는 framelayout에 ListFragment 객체를 넣어줍니다. 트랜잭션 전체를 백스택에 저장하고 commit을 호출합니다.

goDetail()은 setList()와 같은 과정이지만 framelayout에 DetailFragment 객체를 넣어줍니다. 이렇게 되면 모바일 화면이 fragment_list.xml에서 fragment_detail.xml로 전환됩니다.


![](http://i.imgur.com/2GvBhpU.png) 

backList()에서는  detail 프래그먼트를 스택에서 제거하여 다시 fragment_detail.xml에서 fragment_list.xml로 전환됩니다. 이 때는 스택에서 DetailFragment의 트랜잭션 전체가 제거되는게 아니라 frame만 없어지기 때문에 스택에는 ListFragment 객체와 DetailFragment 객체가 존재합니다.( 만약 DetailFragment 객체까지 스택에서 제거하려면 super.onBackPressed()를 선언하면 됩니다. ) 

---------------------------------------

# 3. 프로젝트명 : FragmentTap

##3.1 동작 흐름

![](http://i.imgur.com/uZloyVO.png)

## 3.2 코드

![](http://i.imgur.com/Ds4sjA5.png)

이 프로젝트의 핵심 코드입니다. 크게 3부분으로 나뉩니다. 프래그먼트 초기화, 탭 레이아웃 정의, 뷰페이저 생성과 어뎁터 세팅.

(1) 총 4개의 탭이 필요하기 때문에 프래그먼트 4개를 생성합니다.

(2) TabLout으로 레이아웃을 정의하고 탭에 들어갈 제목을 setText()로 입력합니다.

(3) 목록에 해당하는 내용들이 뷰페이저와 같은 방식으로 동작하기 때문에 뷰페이저를 선언합니다.

(4) 페이저 어뎁터를 선언하고 뷰페이저에 세팅합니다.

(5) 페이저가 변경될 때(목록에 해당하는 내용들을 좌우로 스크롤할 때), 탭이 변경될 때(탭을 좌우로 스크롤할 때) 리스너를 생성합니다.


![](http://i.imgur.com/zTFZKCY.png)

PagerAdapter는 MainAcivity에 정의되어 있는 내부클래스입니다. 프래그먼트를 위한 어뎁터를 생성해야하므로 FragmentStatePagerAdapter를 상속합니다. getItem()은 각 탭을 프래그먼트와 연결합니다. getCount()는 탭의 총 개수를 반환합니다.


그 외 프래그먼트.java 코드들은 각각 뷰를 인플레이트 하기만 하면 되므로 설명을 생략합니다.
