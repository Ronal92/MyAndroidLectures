[ Date : 2017. 03. 23(목) ]
					
					----------------- Today's Topic --------------------

					----------------------------------------------------

- 프로젝트명 : [server_alpha1.js], [Dbcon.js], [server_beta.js]


- 내용 : 


#1. mime 모듈을 사용하여 요청된 파일을 타입별로 처리한다.

필요한 파일 : [server_alpha1.js], 웹페이지로 사용하기 위한 [index.html] , new 폴더 아래의 [a.html] , [b.html]

##화면 출력

먼저 [server_alph1.js]를 node로 서버를 실행시키고 localhost:8080으로 접속하면 아래와 같은 창이 뜹니다.

![](http://i.imgur.com/BficVtm.png)

a.html은 일반 text/html 만을 제공합니다.

![](http://i.imgur.com/CFSROE5.png)

b.html은 image/jpeg를 제공합니다.

![](http://i.imgur.com/rKfKzf0.png)

>> 정리하면 이번 server_alph1.js는 각 html의 타입 체크를 문자열로 체크하는게 아니라 변수 하나에 담아두어 구분할 겁니다.

##1.1 [server_alpha1.js]

 *html 소스는 간단하기 때문에 따로 설명드리지는 않겠습니다.*

![](http://i.imgur.com/c5b8VrV.png)
![](http://i.imgur.com/Zs6kr2b.png)


##1.2 설명(mime 사용법(

(1) 먼저 필요한 모듈을 import 합니다.

					var mime = require('mime');
	
(2) 요청된 파일의 mimeType을 가져옵니다.
 
					var resMime = mime.lookup(res);

(3) if 문으로 mimeType을 체크하여 "text.html" 혹은 그 외의 타입에 대하여 처리를 해줍니다. --> response.writeHead(200, {'Content-Type':resMime})

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


#2