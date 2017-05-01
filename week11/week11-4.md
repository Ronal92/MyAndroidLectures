[ Date : 2017. 03. 23(목) ]
					
					----------------- Today's Topic --------------------
								(1) 서버 구축해보기
								(2) DB 연동해보기
								(3) 서버랑 DB 연동하기
								(4) 안드로이드 + 서버 + DB
								(5) 페이징
					----------------------------------------------------

- 프로젝트명 : [server_alpha1.js], [Dbcon.js], [server_beta.js]


- 내용 : 



# 1. 서버 구축해보기


필요한 파일 : [server_alpha1.js], 웹페이지로 사용하기 위한 [index.html] , new 폴더 아래의 [a.html] , [b.html]

## 결과

먼저 [server_alph1.js]를 node로 서버를 실행시키고 localhost:8080으로 접속하면 아래와 같은 창이 뜹니다.

![](http://i.imgur.com/BficVtm.png)

a.html은 일반 text/html 만을 제공합니다.

![](http://i.imgur.com/CFSROE5.png)

b.html은 image/jpeg를 제공합니다.

![](http://i.imgur.com/rKfKzf0.png)

>> 정리하면 이번 server_alph1.js는 각 html의 타입 체크를 문자열로 체크하는게 아니라 변수 하나에 담아두어 구분할 겁니다.


## 소스코드 : [server_alpha1.js]

![](http://i.imgur.com/b3rrMkN.png)
![](http://i.imgur.com/YuOQEmV.png)



## 코드 설명


							var http = require('http');
							var url = require('url');
							var fs = require('fs');
							var mime = require('mime');


(1) 먼저 필요한 모듈들을 사용합니다.
>> mime 모듈 추가 . 서비스하려는 파일의 타입을 알기 위해서 사용한다. (npm install mime 설치해야 한다.)



 							http.createServer((request, response)



(2) 서버를 생성하는 부분

							var parsedUrl = url.parse(request.url);
							var res = parsedUrl.pathname;


(3) parsing 된 url 중에 서버URI에 해당하는 pathname 만 따로 저장합니다.


							var resMime = mime.lookup(res);

(4) 파일의 mimeType을 가져옵니다.


						if(resMime == "text.html"){
							fs.readFile(res, 'utf-8', (error, data) => {
							    response.writeHead(200, {'Content-Type':resMime});
							    response.end(data);
							});
					
						} else {
							fs.readFile(res, (error, data) => {
								if(error){
								    response.writeHead(404, {'Content-Type':'text/html'});
							    	response.end("<h1>404 page not found</h1>");
								} else {
								    response.writeHead(200, {'Content-Type':resMime});
							    	response.end(data);
								}
					
							});
						} 


(5) 파일의 타입에 따라 구별하여 해당하는 메시지를 클라이언트에 전달합니다.


						server.listen(8080,()=>{
							console.log("Server is running................");
						});

(6) 서버 포트 8080으로 연결 대기 모드.


--------------------------------------------------------------------------------

# 2. DB 연동해보기 : [dbcon.js] , [dbcon_read.js]

## 2.1 DB 서버 실행하기 



(2) npm install mongodb 몽고DB 모듈 설치

(3) MongoDB가 설치된 폴더에 datas 폴더를 생성해주고mongod.exe 파일이 있는 bin 폴더로 이동해서 아래와 같은 명령어를 실행합니다.
mongod --dbpath 설치폴더\MongoDB\datas

아래와 같은 결과로 현재 몽고DB 설버가 실행되고 있습니다.

![](http://i.imgur.com/XiLNUyF.png)

## 2.2 DB와 node.js 연동하기(Insert) : [dbcon.js]

### 소스 코드

[Dbcon.js]

![](http://i.imgur.com/9fTouJY.png)


### 코드 설명

			Client.connect('mongodb://localhost:27017/bbs', function(error, db)


(1) 연결할 DB 테이블 url을 명시합니다.


        
        var post = {title:'제목', content:'내용', name:'누구'};
        var post2 = {title:'제목2', content:'내용2', name:'누구2'};
        var post3 = {title:'제목3', content:'내용3', name:'누구3'};
        var post4 = {title:'제목4', content:'내용4', name:'누구4'};
        var post5 = {title:'제목6', content:'내용5', name:'누구5'};


(2) 입력할 document 생성


		db.collection('qna').insertMany([post, post2, post3, post4, post5]);

(3) qna 컬렉션의 insert( ) 함수에 입력


		db.close();

(4) 반드시 작업을 마치고 나서는 db 연결을 닫아주어야 합니다.

아래와 같이 현재 Dbcon.js 경로에서 node dbcon.js를 실행시켰을 때, connected 메세지가 출력되면 성공적으로 연결된겁니다. 

![](http://i.imgur.com/IGGPBZT.png)

>> Robomongo 설치 (=mysql 워크벤치 같은 거임) : DB 결과를 확인할 때 사용하는 툴입니다.

## 2.3 DB와 node.js 연동하기(Read) : [dbcon_read.js]

### 소스 코드

![](http://i.imgur.com/OFDc8SI.png)


### 코드 설명

			var cursor = db.collection('qna').find();

(1) find( ) 함수에 아무런 입력값이 없으면 컬렉션의 전체 document 를 읽어온다.


	        cursor.each(function(err,doc){ // document(mysql과 같은 관계DB에서의 record) 가 예약어이기 때문에 변수명을 doc로 변경
	            if(err){
	                console.log(err);
	            }else{
	                if(doc != null){
	                    // 3. document 가 정상적으로 있으면 console 에 출력해준다.
	                    console.log(doc);
	                }    
	            }
	        });

(2) 읽어온 document 를 cursor 에 넣고 반복처리

아래와 같이 현재 dbcon_read.js 경로에서 node dbcon_read.js를 실행시키면, 현재 bbs 테이블에 있는 값들이 출력됩니다. 

![](http://i.imgur.com/VXUCbFo.png)

----------------------------------------------------------------------------

# 3. 서버랑 DB 연동하기 [server_beta.js]

먼저 웹으로 사용자 입력 환경을 만듭니다.(index.html, post.html)

## 출력 결과

![](http://i.imgur.com/sCw31W0.png)

웹에서 사용자가 데이터를 입력하면(쓰기)

![](http://i.imgur.com/ytoqQME.png)

서버를 통해 아래와 같이 DB에 저장되는 것을 볼 수 있습니다.

![](http://i.imgur.com/TxwEyPV.png)

## 코드

기존의 server_alpha.js에서 수정한 코드입니다.

![](http://i.imgur.com/K1CCBxB.png)
![](http://i.imgur.com/PMMLO3i.png)

## 코드 설명

(1) 사용자가 index.html 화면에서 '목록'누르면 if 문으로, '쓰기'를 누르면 else 문으로 들어갑니다.


(2) if(res =="bbs")는 두가지 경우입니다. 
			
			사용자가 '목록'을 눌러서 몽고DB에 있는 데이터를 모두 보여주는 경우, 
			사용자가 "쓰기"에서 내용 입력 후, 확인 버튼으로 서버에 전송하는 경우	

(2-1) 사용자가 '목록'을 누르는 경우 아래 코드가 실행됩니다.

					else if(request.method == "GET") {
			
						var query = querystring.parse(parsedUrl.query);
						var skip = parseInt(query.skip);
						var offset = parseInt(query.offset);
						readAll(response, skip, offset);
			
					}



- query : 쿼리 스트링을 파싱한 거(앞에서 설명함)
- skip : 데이터에서 받아올 첫번째 커서의 위치
- offset : skip을 기준으로 몇번째까지 받아올지를 정함.

 readAll : 위에서 준 조건들(skip, offset) 기준으로 DB에서 데이터를 json 형식으로 response를 해줍니다.
				
				function readAll(response, skip, offset){
					var data='';
					client.connect('mongodb://localhost:27017/bbs', function(error, db){
					    if(error) {
					        console.log(error);
					    } else {
						        // 1. find( ) 함수에 아무런 입력값이 없으면 컬렉션의 전체 document 를 읽어온다.
						        db.collection('qna').find().skip(skip).limit(offset).toArray(function(err,docs){
					        	data = '{"data":'+JSON.stringify(docs)+'}';
					        	console.log(data);
					        	send200(response, data, 'application/json');
					        });
					        
					        db.close();
					    }
					});
				}

(2-2) 사용자가 "쓰기"에서 내용 입력 후, 확인 버튼으로 서버에 전송하는 경우 아래 코드가 실행됩니다.

					if(request.method == "POST"){					
						var postdata = '';
						// 요청에 넘어온 post의 body를 읽어서 postdata에 담는다.
						request.on('data', function (data) {
							postdata = postdata + data;
						});
						// post data를 다 읽고 나면 end 이벤트가 발생해서 아래 로직이 실행된다.
						request.on('end', function () {
							var data = querystring.parse(postdata);
							createData_Web(response, data); // 2번
						});
					}

createData_Web(response, data) : 사용자로부터 받은 데이터를 DB에 저장합니다.

				function createData_Web(response, data){
						console.log("title : " + data.title);
						client.connect('mongodb://localhost:27017/bbs', function(error, db){
															
					    if(error) {
								send500(response);
					    } else {
					        var post = {title:data.title, content:data.content, name:data.name};
					        db.collection('qna').insert(post);
					        db.close();        				
							data = 'SUCCESS';
							send200(response, data, 'text/html');
					    }
					});
				}

# 4. 안드로이드 + 서버 + DB : [MemoWithNodejs]

기존의 memo 앱에서 서버와 연결하는 부분을 더하였습니다.


## 화면 결과

![](http://i.imgur.com/PEIc1s3.png)

![](http://i.imgur.com/7gJR668.png)

앱에서 저장한 내용이 서버를 통해 DB에 저장됩니다.

![](http://i.imgur.com/kAlgI1T.png)

## 코드

### 안드로이드

##### GET

[MainActivity.java]

![](http://i.imgur.com/MD7QkXw.png)
![](http://i.imgur.com/p80irn3.png)

(1) 먼저 Retrofit으로 서버를 통해 DB에 담겨있는 데이터를 모두 가져옵니다.

(2) 데이터의 구조는 List<Qna> 입니다. (객체 배열)

(3) DataStore라는 local 저장소에 저장하면 다른 곳에서 RecyclerView로 화면에 보여줍니다.

##### POST

[WriteActivity.java]

- 사용자가 title, name, content를 입력하고 확인버튼을 누르는 곳. AsyncTask로 백그라운드에서 작업하도록 합니다.

		AsyncTask<String, Void, String> networkTask = new AsyncTask<String, Void, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        String title = params[0];
                        String name = params[1];
                        String content = params[2];

                        Qna qna = new Qna();
                        qna.setTitle(title);
                        qna.setName(name);
                        qna.setContent(content);

                        Gson gson = new Gson();
                        String jsonString = gson.toJson(qna); // object를 json string으로 바꿔준다.

                        String result = Remote.postJson(MainActivity.SITE_URL + "bbs", jsonString);

                        if("SUCCESS".equals(result)){
                            DataStore dataStore = DataStore.getInstance();
                            dataStore.addDatas(qna);
                        }

                        return result;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);
                        Toast.makeText(WriteActivity.this, result, Toast.LENGTH_LONG).show();
                        finish();
                    }

                };

- 이 코드에서 핵심은 Gson을 사용하여 객체를 json String으로 변환해주어야 합니다.
- Remote.postJson()으로 서버에 데이터를 보냅니다. 


[Remote.java]

![](http://i.imgur.com/2d4k7Zc.png)
![](http://i.imgur.com/D1hEUGs.png)

- HttpURLConnection으로 데이터를 post하는 곳입니다.
- 코드는 크게 post 데이터 전송 처리 부분과 전송 후 response를 받는 곳으로 구성됩니다.


### 서버 [server_beta.js]

- 기존의 웹으로 통신한 서버 코드랑 동일합니다. 수정할 부분은 아래입니다.
- 함수명 : createData_Web() --> createData_App()
- 바꾼 부분 : data=JSON.parse(data)을 추가하여 스트링을 json 데이터로 바꿔주어야합니다.

>> 분명 안드로이드에서 json으로 바꿔 보냈는데 서버쪽으로 잘 못받은듯.......... 이해는 안가지만 우선 이렇게 json 스트링으로 처리!!


					function createData_App(response, data){
							data=JSON.parse(data); // 스트링을 json 데이터로 바꿔준다. 
							client.connect('mongodb://localhost:27017/bbs', function(error, db){
																// DB 이름
						    if(error) {
									send500(response);
						    } else {
						        var post = {title:data.title, content:data.content, name:data.name};
						        db.collection('qna').insert(post);
						        db.close();        				
								data = 'SUCCESS';
								send200(response, data, 'text/html');
						    }
						});
					}


# 5.페이징 : [server__beta2.js]

- 3장에서 보았겠지만 페이징이란, 서버를 통해 DB에서 정보를 가져올 때, 전체가 아니라 원하는 부분만을 가져오는 겁니다.

- server_beta.js에서 정보를 조회하는 GET 부분만을 수정한 코드입니다.

					else if(request.method == "GET") {
						// http://192.168.0.190:8080/bbs/2/3 로 호출. 
						// REST API 는 주소체계에 변수의 값이 담겨온다.
						// bbs/숫자/숫자 ( 원래는 temp[1]을 호출하면 outofbounds가 뜬다.)
						var temp = res.split("/");
						var skip = parseInt(temp[1]);
						var offset = parseInt(temp[2]);
			
						readAll(response, skip, offset);
					} 