[ Date  : 2017. 01. 31 ]

								------------------------ Today's Topic -------------------------
													(1) ListView
													(2) ListViewHolder
													(3) RecyclerView
													(4) CardView
								----------------------------------------------------------------

#### 프로젝트 : BasicList


# 1. ListView

##1-1 전체 구조
![](http://i.imgur.com/N3msNnT.png)

위 그림에서 Item1, Item2로 나열 할 것처럼 리스트 뷰는 항목들을 수직으로 나열해서 보여주는 위젯이다. 

![](http://i.imgur.com/0EJHs64.png)

전체 코드 설계도는 위와 같습니다.

##1-2 코드

![](http://i.imgur.com/QSJ5iuY.png)


       				  		CustomAdapter adapter = new CustomAdapter(array, this);

빨간줄 위주로 중요한 것만 설명드리자면,

리스트뷰를 사용하기 위해서는 Adapter를 사용해야 합니다.

즉 **"사용자가 정의한 데이터를 ListView에 출력하기 위해 사용하는 객체로, 사용자 데이터와 화면 출력 View로 이루어진 두 개의 부분을 이어주는 객체"** 입니다.

![](http://cfile23.uf.tistory.com/image/2347C350568CB93F27AD93)

(출처 : [http://recipes4dev.tistory.com/42](http://recipes4dev.tistory.com/42))

							listView.setAdapter(adapter);

위에서 선언한 adapter를 listView에 세팅합니다.

							listView.setOnItemClickListener(listener);

리스트 안에 각 Row 마다 클릭 버튼에 대한 리스너를 발생시킵니다. 

![](http://i.imgur.com/D8PfMI6.png)

ListActivity에서 DetailActivity로 창을 전환하면서 number(No)와 요일별 정보(day)가 인텐트에 담겨져 왔다. 여기서는 Bundle을 사용하여 인텐트 안의 값들을 꺼내고 "activity_detail"에 표시하였다.

![](http://i.imgur.com/uNXstux.png)

CustomAdapter는 사용자가 정의한 어뎁터 클래스입니다.

				inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

inflater는 사용자가 정의한 .xml 파일을 메모리에 올려놓기 위해 사용하는 객체입니다. inflater를 생성하기 위해서는 먼저 getSystemService(Context)를 불러와야합니다.

![](http://cfile10.uf.tistory.com/image/11605B354D08F83631D43B)

(출처 : [http://pringlesonion.tistory.com/entry/LayoutInflater%EC%99%80-convertView%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-listView-%EC%B5%9C%EC%A0%81%ED%99%94](http://pringlesonion.tistory.com/entry/LayoutInflater%EC%99%80-convertView%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-listView-%EC%B5%9C%EC%A0%81%ED%99%94) ) 

			        View view = inflater.inflate(R.layout.list_item, null);
			        TextView txtNo = (TextView) view.findViewById(R.id.txtNo);
			        TextView txtDay = (TextView) view.findViewById(R.id.txtDay);

inflater.inflate를 사용하여 사용자가 정의한 아래 list_item.xml을 메모리에 올려놓으면 findViewById로 각 위젯의 아이디를 변수에 할당할 수 있습니다.


> 이때, CustomAdapter가 자체적으로 가지고 있는 findViewById가 아니라 앞에서 생성한 view 안에 정의된 findView를 사용해야 합니다. 또한 getCount(), getItem(), getItemId() 안에 모두 로직을 주어야 getView()로 리스트뷰가 정상적으로 돌아가는 것을 확인할 수 있습니다. 


##1-3 CustormAdapter 말고 일반 Adapter 사용하기

![](http://i.imgur.com/u6FWNhG.png)


##1-4 convertView의 필요성.
ListView에서 1개의 Row를 출력하기 위해서는 getView()를 한 번 호출하게 됩니다. 100, 1000,10000개의 Row를 생성하기 위해서는 getView()를 그만큼 반복 호출해야 하는데 이 과정은 메모리와 시간 면에서 비효율적입니다. **따라서 버려진 Row를 재사용하여 이러한 비효율성을 개선한 것이 convertView입니다.**

						   	     < converView가 갖는 값 >
							 - inflate가 한번도 되지 않았을 때: null값
							 - inflate가 수행된 이후: inflate에 의해 만들어진 View

![](http://i.imgur.com/nELftC8.png)

-----------------------------------------

# 2. ListViewHolder


##2-1 전체 구조


![](http://i.imgur.com/C7OUNei.png)

![](http://i.imgur.com/l4MnZmd.png)

ListViewHolder와 ListView의 가장 큰 차이점은 ViewHolder 입니다.

ViewHolder란, 이름 그대로 뷰(위젯)들을 홀더에 꼽아놓듯이 보관하는 객체를 말합니다. 각각의 Row를 그려낼 때 그 안의 위젯들의 속성을 변경하기 위해 findViewById를 호출하는데 이것의 비용이 큰것을 줄이기 위해 사용합니다.

##2-2 코드 

![](http://i.imgur.com/UQADyfA.png)

					 CustomHolderAdapter adapter = new CustomHolderAdapter(datas, this);

CustomHolderAdapter는 사용자가 inner class로 정의한 Adapter입니다. 이 때, CustomHolderAdapter는 반드시 **BaseAdapter를 상속해야 합니다.**

![](http://i.imgur.com/fUPaVKE.png)

			       public CustomHolderAdapter(ArrayList<User> datas, Context context) {
			            this.datas = datas;
			            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			        }


CustomHolderAdapter 생성자는 인적정보가 담긴 datas와 Context를 인자로 받습니다. 그리고 inflater를 생성하기 위해 인플레이트 서비스를 제공하는 context를 불러옵니다.


> Context란?? Context 는 어플리케이션과 관련된 정보에 접근하고자 하거나 어플리케이션과 연관된 시스템 레벨의 함수를 호출하고자 할 때 사용됩니다. 그런데 안드로이드 시스템에서 어플리케이션 정보를 관리하고 있는 것은 시스템이 아닌, ActivityManagerService 라는 일종의 또 다른 어플리케이션입니다. 이와는 달리 다른 일반적인 플랫폼에서는 프로세스와 애플리케이션이 긴밀하게 연관(맵핑)되어 있기 때문에 별다른 매개체 없이 시스템에 직접 프로세스의 정보에 관한 물어 볼 수 있고, 프로세스와 연관된 시스템 함수를 호출 할 수 있습니다. 그러나 안드로이드에서는 프로세스와 애플리케이션이 서로 독립적으로 운영됩니다. 따라서 안드로이드에서는 어플리케이션과 관련된 정보에 접근하고자 할때는 ActivityManagerService 를 통해야만 하고 이 통로 역할을 Context가 제공해 줍니다. 

> Context 는 어플리케이션이 시작될 때는 물론이요, 어플리케이션 컴포넌트들이 생성될때마다 태어납니다. 또한 각 컴포넌트들이 공유해서 사용하지 않고, 서로 다른 인스턴스르 만들어 사용합니다.

 ( 출처 : [http://arabiannight.tistory.com/entry/272](http://arabiannight.tistory.com/entry/272))

		            if(convertView==null){
		                convertView = inflater.inflate(R.layout.list_holder_item,null);
		                holder = new Holder();
		
		                holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
		                holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
		                holder.txtAge = (TextView) convertView.findViewById(R.id.txtAge);
		
		                convertView.setTag(holder);
		            }else{
		                holder = (Holder)convertView.getTag();
		            }

Holder 클래스는 아래 그림과 같습니다.

				    class Holder{
				        public TextView txtId;
				        public TextView txtName;
				        public TextView txtAge;
				    }

holder 인스턴스를 사용해서 txtId, txtName, txtAge 정보를 멤버변수로 직접 접근합니다. 이것은 멤버변수를 사용하지 않음으로 비용을 줄일 수 있습니다. holder 인스턴스는 TextView 를 계속 생성하는 것이 아니라 안 쓰는 것을 다시 재활용하게 해서 메모리 부담을 줄일 수 있게 해줍니다.


--------------------------------------------------

#3. RecyclerView

이번에 배운 RecyclerView는 ListView와는 달리 LayoutManager와 ViewHolder, Item 뷰에 애니메이션 효과를 낼 수 있는 클래스가 추가 되었습니다. ViewHolder는 리스트뷰 코드에서 생길 수 있는 위젯의 과도한 생성을 RecyclerView에서는 방지하도록 의무화 되었습니다. Layout Manager는 아이템의 배치를 수직/수평/그리드 형식으로 표현합니다.

##3-1 전체 구조

![](http://i.imgur.com/v0083hS.png)

##3-2 코드 
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
# 4.CardView

![](http://i.imgur.com/aQuNsJ4.png)


CardView는 3장의 RecyclerView와 동일한 작업입니다. 리스트에 들어가는 각 Row에 애니메이션 효과를 넣어주고 버튼을 눌렀을 때 화면을 전환하는 기능을 추가하였습니다.

![](http://i.imgur.com/cUIELd2.png)

							holer.cardView.setOnClickListener

한 행의 cardView를 클릭할 때, DetailActivity로 화면이 전환되면서 number와 이름, 나이를 intent에 담아서 넘깁니다.

			        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
			        holder.cardView.setAnimation(animation);

cardView에 애니메이션 효과를 넣어줍니다.


