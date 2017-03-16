[ Date : 2017. 03. 13(월) ]
					
					--------------------Today's Topic -------------------------
									(1) FireBaseOne
									(2) FireBaseChat
					-----------------------------------------------------------

 - 프로젝트 명 : [FireBaseOne], [FireBaseChat]

 - 내용 : 파이어베이스 사용법을 익히고[FireBaseOne], 이 서버를 이용하여 채팅창을 구현하였습니다.[FireBaseChat]




#1. FireBaseOne

##출력화면

//그림

--> 파이어베이스에서 제공하는 "Realtime Database"를 사용할 겁니다.

다음과 같은 순서로 진행하겠습니다.
							
							1. Firebase 프로젝트 생성
							2. Realtime Database 
							3. 안드로이드 프로젝트에 firebase 연결하기
							4. 데이터 저장 / 불러오기

##1.1 Firebase 프로젝트 생성

![](http://i.imgur.com/Esuz40W.png)

--> FireBaseOne 프로젝트를 생성합니다. 데이터가 저장될 저장소 역할을 합니다.

##1.2 Realtime Database

![](http://i.imgur.com/E0V5gwM.png)

--> 위 부분이 바로 데이터를 눈으로 확인할 수 있는 곳입니다. <키, 값> 조합으로 된 Map 데이터 구조입니다.

![](http://i.imgur.com/dT8hoVA.png)

--> "Realtime Database"에 있는 데이터 접근 권한을 설정합니다. 여기서는 'true'로 바꿔주어 프로젝트에 참여하는 모든 사람에게 권한을 허용하였습니다.

##1.3 안드로이드 프로젝트에 firebase 연결하기

[Tools] --> [FireBase] --> [Realtime Database -- Save and Retrieve data] --> [Connect your app to Firebase] --> 1.1에서 만든 프로젝트를 선택! --> [Add the Realtime DataBase to your app]

--> 위의 과정을 끝내고 나면, 파이어베이스를 사용하기 위한 모든 환경설정이 자동으로 처리됩니다.(라이브러리 추가 등등...)


##1.4 데이터 저장 / 불러오기

[MainActivity.java]

![](http://i.imgur.com/ubz4mdg.png)
![](http://i.imgur.com/gIEaQaD.png)
![](http://i.imgur.com/ZlgQUgz.png)


(1) 파이어베이스에 데이터를 읽고 쓰기 위해서는 DatabaseReference를 사용해야합니다. 먼저 파이어베이스와 연결합니다.

						FirebaseDatabase database = FirebaseDatabase.getInstance();

(2) CRUD 작업의 기준이 되는 노드를 레퍼런스로 가져옵니다.

						DatabaseReference bbsRef = database.getReference("bbs");

![](http://i.imgur.com/TDg6BLj.png)

(3) addValueEventListener : path에 저장되어있는 데이터를 읽어오거나 혹은, path에 존재하는데이터 변화가 감지되었을 때 사용하는 메소드입니다. 이 리스너를 통해서 콜백되는 함수가 onDataChange()입니다. ( 이 메소드에 담긴 snapshot으로 데이터를 읽어올 수 있습니다.)

						bbsRef.addValueEventListener(postListener); // 레퍼런스(bbsRef) 기준으로 데이터베이스에 쿼리를 날리는데, 자동으로 쿼리가 됩니다. 

(4) 리스트뷰에 목록을 세팅합니다.

      				  listView = (ListView)findViewById(R.id.listView);
				      adapter = new ListAdapter(datas, this);
        			  listView.setAdapter(adapter);

(5) <데이터 읽어오기>ValueEventListener : 경로의 전체 내용에 대한 변경을 읽고 수신 대기합니다. 

       			   datas.clear(); 		// 위에 선언한 저장소인 datas를 초기화합니다.(데이터가 바뀔때마다 같은 내용이 반복되서 datas에 저장되는 것을 방지합니다.)

onDataChange()에서 해줘야 할 일은 bbs 레퍼런스의 스냅샷을 가져와서 레퍼런스의 자식노드를 반복문을 통해 하나씩 꺼냅니다. 
getValue()는 현재 snapshot이 가리키고 있는 데이터를 자바 객체 형태로 반환해줍니다.( 이 자바 객체를 받아줄 Bbs 클래스를 생성해주어야 합니다.) 


 				   for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
     			       String key  = snapshot.getKey();
      		           Bbs bbs = snapshot.getValue(Bbs.class); // 컨버팅되서 Bbs 구조로 바꿉니다.
            		   bbs.key = key;
           			   datas.add(bbs);

        		   }

어뎁터에 리스트가 바뀌었다는 걸 알립니다.
 
       			   adapter.notifyDataSetChanged();


(6 - 1) <데이터 저장하기> bbs 레퍼런스 (테이블)에 키를 생성하고 그 키 값을 가져옵니다.
						
	                String key = bbsRef.push().getKey();

(6 - 2) 입력될 키, 값 세트 (레코드)를 생성합니다.

               		 Map<String, String > postValues = new HashMap<>(); // Map<key, value> 형태로 저장합니다.
            	     postValues.put("title", title);
         		     postValues.put("content", content);


(6 - 3) 생성된 레코드를 데이터베이스에 입력합니다.

                	DatabaseReference keyRef = bbsRef.child(key);
              	    keyRef.setValue(postValues);

![](http://i.imgur.com/3usyJ2q.png)


------------------------------------------------------------------

#2. FireBaseChat

##출력화면

--> FireBase를 사용하여 간단한 채팅방을 구현하였습니다.

![](http://i.imgur.com/NdCJVFP.png)

--> 로그인 화면입니다. 사용자가 입력한 아이디와 비밀번호를 가지고 firebase의 realtime database에 있는 정보들과 비교합니다.

![](http://i.imgur.com/ry5qDdC.png)

--> 사용자가 로그인을 하면, 미리 만들어둔 채팅방의 목록이 화면에 리스트로 나타납니다.

![](http://i.imgur.com/Ui5RExC.png)

--> 사용자가 채팅방을 누르면, 채팅창으로 화면이 바뀝니다. 이 때, 사용자가 누른 채팅방의 키값과 사용자의 아이디, 사용자 이름, 메시지가 realtime database에 저장됩니다.

>> 코드의 핵심적인 부분 위주로 설명드리겠습니다.

##2.1 MainActivity.java

--> 사용자가 로그인을 하는 화면입니다. FireBase에 있는 데이터를 불러와서 값을 비교하는 부분입니다.

[MainActivity.java]

![](http://i.imgur.com/QWgGa1Q.png)
![](http://i.imgur.com/Zb9N0mn.png)

(1) FireBase와 먼저 연결합니다. userRef라는 참조변수는 현재 "fir-one-ed3ed" 아래 "user" 아래 데이터들을 가리키고 있는 상태입니다. 

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("user");

![](http://i.imgur.com/NPF7uRv.png)


(2) FireBase로 child(id) 레퍼런스에 대한 쿼리를 날립니다. userRef.child(id) 검색에 대한 쿼리문 명령어입니다.

		userRef.child(id).addListenerForSingleValueEvent(new ValueEventListener()
					.........................
					.........................
					.........................
		});

예를 들어 만약 사용자가 ID : aaa, 비밀번호 : 555입력하는 경우 위 쿼리문은 키값 "aaa" 아래 내용들을 가져옵니다.

		예) 		aaa
				 ---- name: "홍길동"
				 ---- password: 123

(3) FireBase는 데이터쿼리가 완료되면 스냅샷에 담아서 onDataChange를 호출해줍니다.

		public void onDataChange(DataSnapshot dataSnapshot){
				...................
				...................
		}

(4) (3)에서 가져온 데이터와 사용자가 입력한 아이디, 비밀번호를 비교하는 로직입니다.

	if(dataSnapshot.getChildrenCount() > 0){	// dataSnapshot은 사용자가 요청한 쿼리문 데이터정보를 가지고 있습니다. 만약 데이터가 1개 이상이라면 현재 파이어베이스에 사용자의 아이디가 존재한다는 의미입니다.
	        String fbPw = dataSnapshot.child("password").getValue().toString();
	        String name = dataSnapshot.child("name").getValue().toString();
	        if(fbPw.equals(pw)){				// 현재 dataSnapshot 아래 password 키값에 대응하는 value가 사용자의 비밀번호와 일치하는지 확인합니다.
	            Intent intent = new Intent(MainActivity.this, RoomListActivity.class);       		
	            intent.putExtra("userid",id);
	            intent.putExtra("username",name);  // 사용자 비밀번호가 맞다면, 사용자 아이디와 이름을 intent에 담아 넘깁니다.
	            startActivity(intent);
	        } else {
	            Toast.makeText(MainActivity.this, "Wrong PassWord", Toast.LENGTH_SHORT).show();
	        }
    } else {
        	Toast.makeText(MainActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
    }	

##2.2 RoomListActivity.java + ListAdapter.class

--> 채팅방의 목록을 보여줍니다.

[RoomListActivity.java]

![](http://i.imgur.com/FYOuWpT.png)
![](http://i.imgur.com/w5E7UTJ.png)
![](http://i.imgur.com/4twnixU.png)

(1) FireBase와 먼저 연결합니다. roomRef라는 참조변수는 현재 "fir-one-ed3ed" 아래 "room" 아래 데이터들을 가리키고 있는 상태입니다. 

 		       FirebaseDatabase database = FirebaseDatabase.getInstance();
 		       DatabaseReference roomRef = database.getReference("room");

![](http://i.imgur.com/Y8xaxOi.png)

(2) 리스트뷰와 연결 및 어텝터를 세팅합니다.

     	       listView = (ListView)findViewById(R.id.listView);
		       adapter = new ListAdapter(datas, this);
		       listView.setAdapter(adapter);


(3) 채팅방이 클릭 되었을 때를 처리하는 리스너입니다. 인텐트에 선택된 방의 키값과 방이름, 사용자 아이디와 사용자 이름을 담아 다음 액티비티에 전달합니다.

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			            @Override
            			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				                Room room = datas.get(position);
				                Intent intent = new Intent(RoomListActivity.this, RoomActivity.class);
				                intent.putExtra("key", room.getKey());
				                intent.putExtra("title",room.getTitle());
				                intent.putExtra("userid", userid);
				                intent.putExtra("username", username);
				                startActivity(intent);
            			}
				}

(4) 경로의 전체 내용에 대한 변경(방의 생성 혹은 삭제)을 읽고 수신 대기합니다.

				roomRef.addValueEventListener(roomListener);


(5) addValueEventListener() 리스너를 처리합니다.
	datas는 방정보(Room.class)를 담고있는 List입니다. dataSnapshot이 가리키는 방의 키값과 value 값을 List 타입의 datas에 각각 setKey()와 setTitle로 저장합니다. 그러고나서 어뎁터에 변경사항을 notify하면 미리 세팅된 어뎁터가 현재 datas 정보를 화면에 출력시켜줍니다. 





				    ValueEventListener roomListener = new ValueEventListener() {
					        @Override
					        public void onDataChange(DataSnapshot dataSnapshot) {
					
					            datas.clear();
					            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
					                Room room = new Room();
					                room.setKey(snapshot.getKey());
					                room.setTitle(snapshot.getValue().toString());
					                datas.add(room);
					            }
					            adapter.notifyDataSetChanged();
					        }
							......................
			    	};


(6) ListAdapter.class

--> 채팅방 목록을 보여주기 위해 사용합니다. ( week4-1 참조 : [https://github.com/Ronal92/MyAndroidLectures/blob/master/week4/week4-1.md](https://github.com/Ronal92/MyAndroidLectures/blob/master/week4/week4-1.md) )

[ListAdapter.class]

![](http://i.imgur.com/YlMWWn8.png)
![](http://i.imgur.com/0U9DVc4.png)


##2.3 RoomActivity.java + CustomAdapter.class

--> 사용자가 채팅방을 누르면 채팅장 화면을 보여줍니다.

[RoomActivity.java ]

![](http://i.imgur.com/B9SDMWu.png)
![](http://i.imgur.com/uyaRWyz.png)
![](http://i.imgur.com/RKUtQiC.png)

(1) RoomListActivity.java에서 전달한 방의 키값과 방이름, 사용자 아이디와 사용자 이름을 꺼냅니다.

        Intent intent = getIntent();
        String key = intent.getExtras().getString("key");
        String title = intent.getExtras().getString("title");
        userid = intent.getExtras().getString("userid");
        username = intent.getExtras().getString("username");




(2) FireBase와 먼저 연결합니다. roomRef라는 참조변수는 현재 "chat" 아래 특정 채팅방 아래 데이터들을 가리키고 있는 상태입니다. 

 		       FirebaseDatabase database = FirebaseDatabase.getInstance();
 		       DatabaseReference roomRef = database.getReference("chat").child(key);

![](http://i.imgur.com/87pQ5iL.png)

(3) 경로의 전체 내용에 대한 변경(메시지 생성)을 읽고 수신 대기합니다.

					roomRef.addValueEventListener(eventListener);

(4) addValueEventListener() 리스너를 처리합니다. 현재 dataSnapshot이 가리키고 있는 데이터들을 Message.class 구조에 맞게 변환시킨뒤, msg에 저장합니다. msg는 다시 List 타입의 datas에 저장됩니다. 

			ValueEventListener eventListener = new ValueEventListener() {
			        @Override
			        public void onDataChange(DataSnapshot dataSnapshot) {
			            datas.clear();
			            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
			                String key  = snapshot.getKey();
			                Message msg = snapshot.getValue(Message.class);
			                msg.key = key;
			                datas.add(msg);
			
			            }
			            adapter.notifyDataSetChanged();
			        }
			
			        @Override
			        public void onCancelled(DatabaseError databaseError) {
			
			        }
		    };

(5) 메세지 전송 버튼의 상태를 읽고 수신 대기 합니다.

				btnSend.setOnClickListener(sendListener);

(6) (5) 버튼 리스너 처리를 합니다. 현재 roomRef가 가리키고 있는 데이터 바로 아래 노드에 키값을 주고 Map 데이터 구조로 사용자가 입력한 메세지 정보를 저장합니다. 최종적으로 setValue()를 사용해서 FireBase 서버에 전송합니다.


			    View.OnClickListener sendListener = new View.OnClickListener(){
				        @Override
				        public void onClick(View v) {
				            DatabaseReference msgRef = roomRef.push();
				            String msg = editMessage.getText().toString();
				
				            Map<String, String> msgMap = new HashMap<>();
				            msgMap.put("userid",userid);
				            msgMap.put("username",username);
				            msgMap.put("msg",msg);
				
				            msgRef.setValue(msgMap);
				        }
			    };   

![](http://i.imgur.com/vA8nUfg.png)

(7) CustomAdapter.class

--> 채팅방에 있는 메세지 목록을 보여주기 위해 사용합니다. ( week4-1 참조 : [https://github.com/Ronal92/MyAndroidLectures/blob/master/week4/week4-1.md](https://github.com/Ronal92/MyAndroidLectures/blob/master/week4/week4-1.md) )



[CustomAdapter.class] 

![](http://i.imgur.com/714pFPY.png)
![](http://i.imgur.com/dRZcLbq.png)