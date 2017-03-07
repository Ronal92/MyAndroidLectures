[ Date : 2017. 02. 02 ]
 
				----------------- Today's Topic ------------
							(1) 음악 데이터 가져오기
							(2) 음악 이미지(앨범 자킷) 추가
							(3) 글라이드 라이브러리
							(4) 뷰페이저
							(5) 음악 재생을 위한 초기 세팅
							(6) 음악 재생을 위한 현재 음원 위치 가져오기.
				-------------------------------------------

안드로이드 프로젝트 명 : [MusicPlayer] 

구현 내용 : 음악 리스트를 보여주고 재생, 다음곡 재생, 이전곡 재생 기능 완성. 리사이클러 어뎁터와 뷰페이저를 사용함. SeekBar에 적용된 멀티쓰레딩
 
# 전체 구조

![](http://i.imgur.com/PytJudJ.png)

![](http://i.imgur.com/2kBf3RY.png)

![](http://i.imgur.com/YgBF88d.png)

-----------------------------------------------

#1. 음악 데이터 가져오기 

![](http://i.imgur.com/GDW1x2E.png)

####(1)
 week4-1장의 전화번호부를 불러온 프로그램과 같습니다. 여기서는 핸드폰 음악 어플리케이션에 있는 음악 리스트를 불러옵니다. 먼저 음악 리스트에 접근하기 위해 resolver를 생성한 후, 리스트가 있는 주소를 uri에 담습니다. proj[]는 음악 리스트를 가져온 후에 분류할 column 이름입니다. 

####(2)
 resolver로 가져온 데이터는 다시 cursor 단위로 가져오게 됩니다. 커서는 가져온 데이터 테이블에서 한 행씩 접근하는데 아래 그림대로 보면, proj[]에 있는 이름대로 필요한 데이터들을 옮깁니다.

![](http://i.imgur.com/eF6sCIH.png)

이렇게 한 행씩 가져온 데이터는 ArrayList<Music> 타입의 data에 추가됩니다.

####(3)
 cursor를 통한 접근은 메모리 낭비를 막기 위해 마지막에 close를 해주어야 합니다.

-----------------------------------------------------

#2. 음악 이미지(앨범 자킷) 추가

![](http://i.imgur.com/l7miane.png)

음악 이미지의 경우는 String이 아닌 **Uri 타입**으로 가져와야 합니다. 따라서 음악 이미지가 있는 주소인 *content://media/external/audio/albumart/album_id* 주소를 가져와야 합니다. 위에 사각형 안에 있는 코드들의 내용입니다. 

		getAlubmImageSimple()에서 구현된 코드는  앨범 Uri를 강제로 생성하게 하는 가장 간단한 방법이지만, 실제 앨범데이터만 있어서 이미지를 불러오지 못하는 경우가 있습니다. 이 외에 Bitmap으로 데이터를 가져와서 스트림으로 가져오는 방법이 있지만, 100개 이상의 음악리스트의 경우에는 메모리 문제로 어플리케이션이 갑자기 종료됩니다.

------------------------------------------------------

#3. 글라이드 라이브러리

![](http://i.imgur.com/E39w0rO.png)

MusicAdapter.java는 DataLoader에서 저장된 데이터를 가지고 모바일 화면의 위젯들에 실제 데이터를 표시하게 합니다. 이 때 URL을 사용해서 이미지를 로드하는 역할을 Glide(이미지 로딩 라이브러리) 입니다. 글라이드가 필요한 이유는 아래와 같습니다. 

> 
> 이미지 로딩을 구현할 때는 HTTP 통신을 안정되게 구현하고, 비트맵으로 디코딩하면서 메모리가 넘치거나 새지 않도록 주의해야 한다. 네트워크 호출과 디코딩은 단순히 백그라운드 스레드에서 동작하는 것만으로는 충분하지 않고 더 적극적으로 병렬성을 활용해야 한다. 화면 회전, 전환, 스크롤 때 반복적인 요청이 가지 않도록 이미지를 캐시하고, 불필요해진 요청은 빠른 시점에 취소해서 더 나은 UI 반응을 제공하면서 자원을 절약해야 한다. 이 과제들을 모두 해결하려다 보면 처리 흐름은 복잡해지고, 비슷한 코드가 반복되기 쉽다. 출처: http://gun0912.tistory.com/17 [박상권의 삽질블로그]



				compile 'com.github.bumptech.glide:glide:3.7.0'

글라이드를 사용하려면 먼저 Gradle에 위의 내용을 추가해야합니다.


-load() : 해당 url로 가서 이미지를 불러옵니다.

-placeholder() : 이미지를 로딩하는동안 처음에 보여줄 placeholder이미지를 지정할 수 있습니다.

-into() : 이미지가 들어가 target 위젯을 정합니다.

---------------------------------------------------

# 4. 뷰페이저

##뷰페이저란? 
이미지를 좌우로 넘길 때 사용합니다. AdapterView의 일종인데 뷰를 페이지처럼 사용하게 합니다.

사용방식은 RecyclerView와 같습니다.

 
        datas = DataLoader.get(this);  // 0. 데이터 가져오기

        
        viewPager = (ViewPager)findViewById(R.id.viewPager); // 1. 뷰페이저 가져오기

       
        adapter = new PlayerAdapter(datas, this);  // 2. 뷰페이저용 아답터 생성

       
        viewPager.setAdapter(adapter);  // 3. 어뎁터를 뷰페이저에 등록
       
        viewPager.addOnPageChangeListener(viewPageListener);  // 4. 뷰페이저 리스너 연결

		viewPager.setCurrentItem(position);  // 5. 특정 페이지 호출

> Adapter란,
대량의 Data(여기서는 Image들)들을
보여주기 위해 View를 여러개 만들어서 ViewGroup에 추가해줘야 하는데
이 작업을 Data의 순서에 맞게 적절한 View로 만들어 주는 클래스 객체를 말합니다.

### 레이아웃 파일 생성.

![](http://i.imgur.com/7ntWs5E.png)

### 페이저 adapter 소스.

![](http://i.imgur.com/0QZ8cQe.png)

![](http://i.imgur.com/9Mj3u4C.png)



####(1)


	public PlayerAdapter(ArrayList<Music> datas, Context context)

-->어뎁터 생성시 받은 data와 context를 객체의 멤버변수에 저장해두고 inflater를 생성합니다.

####(2)

 

    public Object instantiateItem(ViewGroup container, int position)

--> listView의 getView와 같은 역할입니다. 실제 음악 데이터를 가져와서 화면상의 위젯에 set을 합니다. 마지막으로 *container.addView(view);* 생성한 뷰를 컨테이너에 담아줍니다.


####(3)

	public void destroyItem(ViewGroup container, int position, Object object)

-->화면에서 사라진 뷰를 메모리에서 제거하기 위한 함수입니다.


####(4)
	
	public boolean isViewFromObject(View view, Object object)

-->instantiateItem 에서 리턴된 Object가 View가 맞는지를 확인하는 함수입니다.


------------------------------------------------------

# 5. 음악 재생을 위한 초기 세팅

![](http://i.imgur.com/0eQKc59.png)

####(1)

  뷰페이저로 이동할 경우, 현재 플레이 상태와 이미지를 STOP으로 하고 플레이어에 세팅된 값을 해제합니다.

####(2)

플레이어에 음원을 세팅하기 위해 음원이 있는 url 주소를 입력합니다. setLooping()은 반복여부를 묻는 함수입니다. 음원의 전체 시간을 seekBar와 textView에 표시합니다.

####(3)

		setOnCompletionListener()

setOnCompletionListener는 음원 플레이가 완료되는 경우 호출되는 리스너입니다. 자연스럽게 Next() 다음곡으로 넘깁니다.


----------------------------------------------


# 6. 음악 재생을 위한 현재 음원 위치 가져오기.

현재 음원의 위치를 가져오기 위해서는 사용자가 음원을 클릭하였을 때와 뷰페이저를 넘겼을 때를 처리해야합니다.

사용자가 음원을 클릭하였을 때는, 액티비티가 전환이 되는 순간입니다. 인텐트로 값을 받아오면됩니다.

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");  

뷰페이저를 넘겼을 경우, 페이지 체인지 리스너(OnPageChangeListener)가 호출됩니다. 이곳에서 onPageSelected(int position)의 position 값을 액티비티의 전역변수에 저장합니다.





#7. 핸들러

는 앞의 내용이 너무 많은 관계로 week4-4로 넘기겠습니다........... 그 외에 플레이 기능 또한 여기에 포함된 관계로 다음장에.........





