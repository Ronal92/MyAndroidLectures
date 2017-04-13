[ Date : 2017. 02. 12 ]

	Weekly Summary!!
	   	 이번주는 안드로이드에서 Fragment의 사용과 구글맵, GPS를 직접 구현해 보았습니다. Fragment는 액티비티라는 한 화면을 분할하여 사용할 수 있도록 사용하는 장치로써, 정말 유용합니다.(현재 팀프로젝트를 하면서 정말 많이 사용하고 있어요!)

		 구글맵과 GPS는 나름 신경써서 정리했는데 궁금하신 분들은 한번 따라해보시면 좋을 거 같애요^^
		 우리 강사님이 아주 쉽게 가르쳐 주었습니다. 단 안드로이드 OS 버전별로 차이가 있을수도!

		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만들어 놓았습니다.^^

---------------------------------
							
							****************목차******************
								(1) Fragment
								(2) 프로젝트명 : FragmentControl
								(3) 구글맵 사용하기
								(4) GPS 사용하기
							*************************************


# 1. Fragment
## 1.1 Fragment란??
fragment는 activity의 부분이라고 보면 됩니다. 여러 개의 프래그먼트를 하나의 액티비티에 조합하여 **창이 여러 개인 UI**를 구축할 수 있으며, 하나의 프래그먼트를 여러 액티비티에서 **재사용**할 수 있습니다. 

####### 왜 사용하는가? 태블릿과 같은 Wide Screen에서 Activity라는 큰 틀안에 뷰만을 사용하여 다양한 내용을 넣고 관리하기가 힘들기 때문입니다. fragment는 activity의 영역을 분할, 관리할 수 있도록 해줍니다. Activity처럼 관련된 코드들을 한곳에 묶을 수도 있고, 일반 뷰처럼 애플리케이션 레이아웃에 Fragment를 자유롭게 배치할 수도 있습니다

![](http://wanochoi.com/wp-content/uploads/2016/02/activity_view_fragment.jpg)

( 출처 : [http://wanochoi.com/?p=1990](http://wanochoi.com/?p=1990) )

## 1.2 Fragment의 생명 주기

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


## 1.5. 프래그먼트 관리
FragmentManager를 사용하여 프래그먼트를 관리할 수 있습니다. FragmentManager가 할 수 있는 일은 여러가지 있는데 그 중에 몇 가지를 소개하자면,

			- 액티비티 내에 존재하는 프래그먼트를 findFragmentById()로 가져오기
			- popBackStack()을 사용하여 프래그먼트를 백 스택에서 꺼냅니다(사용자가 Back 명령을 시뮬레이션).
			- beginTransaction()으로 트랜잭션을 시작할 수 있습니다.

>> 트랜잭션(Transaction)이란?? Transaction은 시스템이 에러가 날 경우 다시 초기화 해야하는 일련의 과정들 혹은 연속되는 일들의 단위입니다.


## 1.6 프래그먼트 트랜잭션

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

## 1.7 액티비티로의 이벤트 콜백 

좀 어려운 개념입니다..... 간단히 설명하면, 프래그먼트 내부에 콜백 인터페이스를 정의하고 해당 호스트가 이 인터페이스를 구현하는 구조입니다. 프래그먼트와 액티비티간에 이벤트를 공유하는 겁니다.

![](http://i.imgur.com/rUSurpO.png)

OnListener()는 Fragment 내부에 선언된 인터페이스입니다. 프래그먼트를 호스팅하는 액티비티가 OnListener 인터페이스를 구현하고 goUri()를 재정의합니다. 프래그먼트 A의 onAttach() 콜백 메서드가 OnListener의 인스턴스를 생성합니다. 이때 onAttach()로 전달되는 Activity를 형변환하여 Activity에 있는 goUri()를 동작시킵니다.



# 2. 프로젝트명 : FragmentControl

## 2.1 동작 흐름

![](http://i.imgur.com/hSTMZyA.png)

activity_main에 있는 FrameLayout에 fragment 레이아웃들이 나타납니다. 여기서는 프래그먼트 동작시 필요한 코드들을 배우고 메모리가 어떻게 동작하는지 배웁니다.

fragment_detail과 fragment_list의 코드는 앞의 1-6의 이벤트 콜백과 같기 때문에 MainActivity 위주로 설명하겠습니다.

## 2.2 코드

![](http://i.imgur.com/Kvfk3nG.png)

setList()에서 해주는 일은 fragment를 실행하기 위한 트랜잭션 시작하고 activity_main에 있는 framelayout에 ListFragment 객체를 넣어줍니다. 트랜잭션 전체를 백스택에 저장하고 commit을 호출합니다.

goDetail()은 setList()와 같은 과정이지만 framelayout에 DetailFragment 객체를 넣어줍니다. 이렇게 되면 모바일 화면이 fragment_list.xml에서 fragment_detail.xml로 전환됩니다.


![](http://i.imgur.com/2GvBhpU.png) 

backList()에서는  detail 프래그먼트를 스택에서 제거하여 다시 fragment_detail.xml에서 fragment_list.xml로 전환됩니다. 이 때는 스택에서 DetailFragment의 트랜잭션 전체가 제거되는게 아니라 frame만 없어지기 때문에 스택에는 ListFragment 객체와 DetailFragment 객체가 존재합니다.( 만약 DetailFragment 객체까지 스택에서 제거하려면 super.onBackPressed()를 선언하면 됩니다. ) 


# 3. 구글맵 사용하기

[구글맵 사용하기]는 자신의 위치를 GPS를 사용하여 실시간으로 구글맵에 표시합니다.


![](http://i.imgur.com/2MBbhlH.png)

## 3.1 구글맵과 GPS를 같이 사용하기 위해서는 먼저 manefest에 권한 설정을 해주어야 합니다.

![](http://i.imgur.com/hHiObjW.png)

**android.permission.INTERNET** - API가 Google Maps 서버에서 지도 타일을 다운로드할 때 사용합니다.

**android.permission.ACCESS_FINE_LOCATION** - 애플리케이션이 device의 위치를 실시간으로 접근하도록 허락합니다. (정확한 위치 정보)

**android.permission.ACCESS_COARSE_LOCATION** - 애플리케이션이 device의 위치를 실시간으로 접근하도록 허락합니다. (근사 위치 정보)


## 3.2. 구글 맵 프로젝트 생성과 구글 API 키 가져오기

Google Maps Android API를 사용하려면 Google Developers Console에 앱을 등록하고 추가할 수 있는 API 키가 필요합니다.

### 3.2.1
먼저 "Google Maps Activity"를 생성합니다.

![](http://i.imgur.com/PyyQbwG.png)

### 3.2.2

 OnMapReadyCallback 인터페이스를 FourFragment에서 구현합니다.

			public class FourFragment extends Fragment implements OnMapReadyCallback {
			   ...............................
			}



### 3.2.3

![](http://i.imgur.com/8QYtBPl.png)

(1) app/res/values/google_maps_api.xml로 이동합니다.

(2) 표시된 링크로 가면 Google Developers Console로 이동합니다. Console 화면에 나온대로 따라하면 구글 키 생성!

(3) 구글 key를 string 태그 안에 넣으면 됩니다. 

## 3.3 Mapview 추가 

MyUtility 프로젝트에서 구글맵이 표시될 화면은 '현재위치' 탭 아래 화면입니다. 이 화면에 해당하는 "fragment_four.xml"에 mapview 프래그먼트를 추가하고 ID를 'mapView'로 합니다.

## 3.4 Mapview 프래그먼트를 액티비티에 추가.
우리가 사용하는 액티비티에는 뷰페이저가 등록이 되어있습니다. 뷰페이저에 어뎁터를 setting시킴으로 화면에 표시가 되는데 이 때, 우리가 사용할 어뎁터로 fragmentStatePageAdapter를 상속하는 어뎁터를 사용할 겁니다. 

결론적으로 Mapview를 화면에 보여주기 위해 어디에 등록해야 하냐? 

----> Mapview를 프래그먼트에 등록시킨 후, 프래그먼트를 어뎁터 클래스에서 객체로 생성하여 반환해주면 됩니다.(MainActivity.java) 여기서는 프래그먼트의 onCreateView(프래그먼트가 화면에 보여질 때)에 아래 과정을 넣어주면 됩니다.(FourFragment.java)

![](http://i.imgur.com/lg3fo1W.png)

## 3.5 OnMapReadyCallback 인터페이스 메소드 정의하
	

![](http://i.imgur.com/CrIWioO.png)


3.2.2에서 OnMapReadyCallback 인터페이스를 implements하면 onMapReady 콜백 메소드를 정의해주어야합니다. 여기서는 GoogleMap 객체(map)를 사용하여 가령 지도에 시드니 도시의 마커를 표시(.addMarker)하고 화면을 시드니가 있는 쪽으로 옮깁니다.(.moveCamer) 이때 zoom level은 15 정도만 줬습니다. 


> 여기까지가 구글맵을 띄우는 과정이었습니다!!! 휴.........
> 4장부터는 GPS를 사용하여 내 위치를 실시간으로 마크해주는 기능을 넣겠습니다

------------------------------------------------

# 4. GPS 사용하기

				          	<GPS를 사용하는 법>
					 * 1. manefest에 FINE, COARSE 권한추가
					 * 2. runtime permission 코드에 추가
					 * 3. GPS Location Manager 정의
					 * 4. GPS가 켜져있는지 확인. 꺼져있다면 GPS화면으로 이동
					 * 5. Listener 실행
					 * 6. Listener 해제




![](http://i.imgur.com/yPDGFTB.png)

전체적인 코드 진행은 위와 같습니다.

## 4.1 manefest에 FINE, COARSE 권한추가

1번은 1.1에서 설정했으므로 생략합니다^^

## 4.2 runtime permission 코드에 추가

![](http://i.imgur.com/LMy7B0R.png)

GPS를 사용하기 위해서는 사용자에게 위치 탐색 권한을 요청해야 합니다. 
따라서 "Manifest.permission.ACCESS_FINE_LOCATION"와 "Manifest.permission.ACCESS_COARSE_LOCATION"를 반드시 권한체크 문과 권한체크 후 콜백문에 넣어주어야 합니다!!



## 4.3 GPS Location Manager 정의

Location Manager는 실시간으로 device의 위치 정보를 제공해주는 역할입니다. Location Manager를 불러올 때는 **Context.getSystemService(Context.LOCATION_SERVICE)** 를 사용하면 됩니다.

		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

## 4.4 GPS가 켜져있는지 확인. 꺼져있다면 GPS화면으로 이동

[MainActivity.java]
![](http://i.imgur.com/QnyAjEc.png)

GPS가 켜져있는지 여부를 팝업으로 사용자에게 알리는 코드입니다.

(1) 직접 만든 gpsCheck()로 GPS가 켜져있는지를 확인합니다.

(2) 안켜져 있다면, AlertDialog.Builder 객체를 선언합니다.

>> AlertDialog.Builder은 사용자 인터페이스에서 필요한 대부분의 대화 상자를 구현하게 합니다.

(3) alertDialog.setTitle() : 팝업창 제목을 만듭니다.

(4) alertDialog.setMessage() : 팝업창 메시지를 만듭니다. 

(5) alertDialog.setPositiveButton : Yes 버튼을 눌렀을 때. intent에 Settings.ACTION_LOCATION_SOURCE_SETTINGS을 담아 시스템에 전달합니다. GPS 설정 화면으로 넘어갑니다. 

(6)  alertDialog.setNegativeButton : No 버튼을 눌렀을 때. 이 때는 dialog.cancel()로 대화상자를 그냥 닫습니다.

(7) alertDialog.show() : 팝업창을 화면에 띄웁니다.


[MainActivity.java]
![](http://i.imgur.com/4Opte66.png)

위 코드는 롤리팝 이하 버전일 경우 gps가 켜져있는지를 시스템 내부에서 확인하는 과정입니다.

### GPS 설정 화면

![](http://i.imgur.com/pwzyY26.png)


## 4.5 Listener 실행

GPS를 사용하기 위해서는 위치좌표 값을 불러와야 합니다. 위치 값을 매번 얻어올 때, 필요한 것이 리스너(LocationListener) 등록입니다. 이 리스너는 모바일 화면에서 일정 거리 기준으로 움직임이 발생할 때마다 호출됩니다. 

### 4.5.1 리스너 등록

[FourFragment.java]
![](http://i.imgur.com/b8j5DUG.png)

(1) 먼저 device의 OS 버전이 마시멜로 이상인지를 체크하고 ACCESS_COARSE_LOCATION와 ACCESS_FINE_LOCATION 퍼미션이 권한 체크 되 있는지 확인합니다.

(2) requestLocationUpdates() -- GPS_PROVIDER.
2.3에서 선언한 manager를 통하여 GPS 제공자의 정보가 바뀔때마다 콜백하도록 합니다.

(3) requestLocationUpdates() -- NETWORK_PROVIDER.
2.3에서 선언한 manager를 통하여 네트워크 제공자의 정보가 바뀔때마다 콜백하도록 합니다.

>> 위치 제공자는 GPS_PROVIDER, NETWORK_PROVIDER 2가지 종류가 있습니다. 실내에서는 GPS_PROVIDER를 호출해도 응답이 없습니다. 따라서 타이머를 설정하여 GPS_PROVIDER를 호출 한 뒤 일정 시간이 지나도 응답이 없을 경우 NETWORK_PROVIDER를 호출 하거나, 또는 둘 다 한꺼번에 호출하여 들어오는 값을 사용하는 방식이 일반적입니다. 저희 코드에서는 둘다를 호출하도록 하였습니다.

( 출처 : [http://biig.tistory.com/74](http://biig.tistory.com/74) )

### 4.5.2 리스너 구현

[FourFragment.java]
![](http://i.imgur.com/qtsxC7L.png)

여기서는 GPS로 디바이스의 위치값이 변경될 때마다 이벤트가 발생하였을 때를 처리하기 위한 코드입니다. 위치값은 Location 형태로 오기 때문에 현재 위도, 경도, 고도 등의 값들은 Location 안에 정의되어 있는 함수들을 사용하면 됩니다. 

이렇게 자신의 위치값을 가져온 후에는, map.addmarker()로 구글맵에 마크표시를 하고난 뒤 map.moveCamera로 화면을 내 위치로 이동시키면 끝~!!



## 4.6 Listener 해제

![](http://i.imgur.com/Q23YMPW.png)

더이상 위치값을 호출하지 않아도 되는 경우에는 removeUpdates()로 자원해제를 반드시 해 줍니다. 여기서는 프래그먼트가 유저와의 상호작용이 끝나는 단계인 onPause()에서 정의하였습니다.