[ Date : 2017. 03. 22(수) ]
					
					----------------- Today's Topic --------------------
								(1) 클라이언트의 get(주소형태) 요청에 대한 처리
								(2) 클라이언트의 post(주소형태) 요청에 대한 처리
								(3) 모듈을 사용하는 법
								(4) 이벤트 기반 비동기 방식
								(5) 파일 읽기
					----------------------------------------------------

- 프로젝트명 : [server_get.js], [server_post.js], [module_test.js] , [custom_module.js], [custom_event.js], [server_event.js], [server_file.js]


- 내용 : node js 기초 문법(get, post, 모듈 사용하기, 이벤트 처리, 파일 입출력)

- 참고 사이트
	
	https://javafa.gitbooks.io/nodejs_server_basic/content
	https://opentutorials.org/module/938/7373

#1. 클라이언트의 get(주소형태) 요청에 대한 처리



##1.1 코드

[server_get.js]

![](http://i.imgur.com/Wpd9bdI.png)
![](http://i.imgur.com/NZeB4Pi.png)

##1.2 설명

(1) 서버로 사용하기 위해서는 먼저 http 모듈을 import 해야 합니다.

					var http = require('http');

(2) 요청한 url을 객체로 만들기 위해 url 모듈사용

					var url = require('url');

(3) 요청한 url 중에 Query String 을 객체로 만들기 위해 querystring 모듈 사용

					var querystring = require('querystring'); 

(4) 브라우저에서 요청한 주소를 parsing 하여 객체화 후 출력

					var parsedUrl = url.parse(request.url);

(5) parsedUri.pathname과 parsedUri.query의 차이

![](http://i.imgur.com/VqCdgUC.png)


(6) 객체화된 url 중에 Query String 부분만 따로 객체화 후 출력. 디렉토리 뒤에 변수와 값으로 분리합니다.

					var parsedQuery = querystring.parse(parsedUrl.query,'&','='); 

				    console.log("num=" + parsedQuery.num);
    				console.log("num2=" + parsedQuery.num2);

(7) 서버는 클라이언트에게 정상 처리 됬음을 알려줍니다.

				    response.writeHead(200, {'Content-Type':'text/html'});
				    response.end('Hello node.js!!');

(8) 8080 포트로 서버를 열어놓습니다.

					server.listen(8080, function(){
					    console.log('Server is running...');
					});
-----------------------------------------------------

#2. 클라이언트의 post(주소형태) 요청에 대한 처리



>> GET이랑 다른점은?? POST는 주소만 요청하고 변수와 값을 주소가 아닌 요청 BODY에 담아서 서버측에 요청합니다.
	
##2.1 코드

[server_post.js]

![](http://i.imgur.com/2Fw1p9A.png)

##2.2 설명

(1) request객체에 on( ) 함수로 'data' 이벤트를 연결(클라이언트가 'data' 이벤트 발생시키면 서버가 리스너로서 동작합니다. )

			  request.on('data', function (data) {
			    	postdata = postdata + data;  // data 이벤트가 발생할 때마다 callback을 통해 postdata 변수에 값을 저장
  			  });

(2) request객체에 on( ) 함수로 'end' 이벤트를 연결(클라이언트가 'end' 이벤트 발생시키면 서버가 리스너로서 동작합니다. )

			  request.on('end', function () {
			    var parsedQuery = querystring.parse(postdata); // end 이벤트가 발생하면(end는 한버만 발생한다) 미리 저장해둔 postdata 를 querystring 으로 객체화			    
			    response.writeHead(200, {'Content-Type':'text/html'}); // 성공 HEADER 와 데이터를 담아서 클라이언트에 응답처리
			    response.end('var1의 값 = ' + parsedQuery.num);
			  });

(3) 구글 확장프로그램 중 Postman을 사용하여 특정 url 주소로 post 방식으로 서버에 요청할 수 있습니다.

![](http://i.imgur.com/n5KUYVE.png)


---------------------------------------------

#3. 모듈을 사용하는 법



	
##3.1 코드(1)

--> custom_module 모듈을 생성하여 mmodule_test.js에서 이 모듈을 사용하겠습니다.

[custom_module.js]

![](http://i.imgur.com/AGCdvRH.png)

(1) exports 객체를 사용해서 sum이라는 변수를 만들고, sum 변수에 function 을 사용해서 하나의 파라미터를 가진 함수식을 대입.

				exports.sum = function(max) {
					return (max+1)*max/2; // 입력된 값을 최대값으로 1부터 최대값까지 더해서 반환하는 로직
				}

>> exports는 자바에서 public과 같은 접근 권한자입니다. exports로 선언하지 않은 식별자는 private이기 때문에 외부에서 접근할 수 없습니다.

(2) private 식별자인 var1은 getValue() 메소드로 값을 불러올 수 있습니다.


					var var1 = 'NEW VALUE 100';

					exports.getValue = function(){
						return var1;
					}

##3.2 코드(2)

[module_test.js]

![](http://i.imgur.com/CkhCgOn.png)

(1) custom_module을 import합니다.

			var custom = require('./custom_module');

(2) custom_module의 포인터인 custom을 사용하여 모듈에 선언되어있는 메소들이나 변수에 접근합니다.


----------------------------------------------------


#4. 이벤트 기반 비동기 방식


##4.1 코드(1)

[custom_event.js]

![](http://i.imgur.com/YUCvhTS.png)


(1) 이벤트가 정의되 있는 events 모듈 생성. 이전 버전의 process.EventEmitter() 는 deprecated!

				var EventEmitter = require('events');




(2) 생성된 이벤트 모듈을 사용하기 위해 custom_object로 초기화


				var custom_object = new EventEmitter();

>> 현재 events 모듈의 코드가 EvenEmittier에게 전달된 상태입니다. on(), emit()은 exports로 선언돼있지 않기 때문에 직접 접근할 수 없습니다. 대신 events 모듈안에 있는 EvenEmitter(최상위 root)가 exports로 선언되어 있으므로 객체화 시킨 다음에 on(), emit()을 사용합니다.


(3) events 모듈에 선언되어 있는 on( ) 함수를 재정의 하여 'call' 이벤트를 처리

				custom_object.on('call', ()=> {
				    console.log('called events!');
				});


(4) call 이벤트를 강제로 발생

				custom_object.emit('call');


##4.2 코드(2)

[server_event.js]

![](http://i.imgur.com/hFBHkup.png)


(1) 필요한 모듈을 import합니다. events 모듈을 객체화 시킵니다. 
							
							var http = require('http')
							var EventEmitter = require('events');
							var custom_object = new EventEmitter();


(2) 이벤트 리스너 등록

							custom_object.on('call', ()=> {
							    console.log('called events!');
							});

(3) url 주소로 http://localhost:8080/call 을 요청하면 이벤트를 발생시킵니다.


							if(request.url == "/call"){
								custom_object.emit("call");
							}
							response.end("");

----------------------------------------

#5. 파일 읽기



##5.1 코드


![](http://i.imgur.com/wSTo6lQ.png)


##5.2

(1) 파일 시스템 모듈을 사용합니다.

				var fs = require('fs');

(2) filename의 파일을 [options]의 방식으로 읽은 후 callback으로 전달된 함수를 호출합니다. (비동기적)

		fs.readFile(filename, [options], callback)


		예시)fs.readFile('index.html', 'utf-8', (error, data) => {
		   		 response.writeHead(200, {'Content-Type':'text/html'});
		 	  	 response.end(data);
			});
