var http = require('http');
var url = require('url');
var fs = require('fs');
var client = require('mongodb').MongoClient;
var querystring = require('querystring');
// mime 모듈 추가 . 서비스하려는 파일의 타입을 알기 위해서 사용한다.
var mime = require('mime');



// 1. 서버생성
var server = http.createServer((request, response) => {
	var parsedUrl = url.parse(request.url);
	var res = parsedUrl.pathname;
	// 제일 앞에 / 를 제거하면 fs.readfile에서 실제 경로상의 파일을 접근할 수 있다.
	res = res.substring(1);

	// root 처리
	if(res == ""){
		res = "index.html";
	}

	// post (게시판 글쓰기 분석
	if(res.indexOf("bbs") == 0 ){
		// 쓰기 
		if(request.method == "POST"){
			// 요청에 넘어온 post의 body를 읽어서 postdata에 담는다.
			var postdata = '';
			request.on('data', function (data) {
				postdata = postdata + data;
			});
			// post data를 다 읽고 나면 end 이벤트가 발생해서 아래 로직이 실행된다.
			request.on('end', function () {
				var data = querystring.parse(postdata);
				// 어플로 확인하려면 1번, 웹으로 보려면 2번 
				createData(response, postdata); // 1번
				//createData(response, postdata); // 2번
			});
		// 읽기	
		} else if(request.method == "GET") {
			console.log('this is GET');
			// http://192.168.0.190:8080/bbs/2/3 로 호출. 
			// REST API 는 주소체계에 변수의 값이 담겨온다.
			// bbs/숫자/숫자 ( 원래는 temp[1]을 호출하면 outofbounds가 뜬다.)
			var temp = res.split("/");
			var skip = parseInt(temp[1]);
			var offset = parseInt(temp[2]);

			readAll(response, skip, offset);
		// method 지원안함 
		} else {
			send404(response);
		}
	} else {

		var resMime = mime.lookup(res); // 파일의 mimeType을 가져온다. 
		console.log("mime=" + resMime);

		// 요청된 파일의 mime type이 text/html 일 경우만 처리
		if(resMime == "text.html"){
			// 파일을 읽어서 전송한다.
			fs.readFile(res, 'utf-8', (error, data) => {
			    send200(response, data, resMime);
			});

		// 그 이외의 mime type은 모두 여기서 처리	
		} else {
			fs.readFile(res, (error, data) => {
				if(error){
					send404(response);
				} else {
					send200(response, data, resMime);
				}

			});			
		} 
	}

});

server.listen(8080,()=>{
	console.log("Server is running................");
});

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

function createData(response, data){
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

function send200(response, data, mimeType){
	response.writeHead(200, {'Content-Type':mimeType});
	response.end(data);

}

function send404(response){
    response.writeHead(404, {'Content-Type':'text/html'});
	response.end("<h1>404 page not found</h1>");
}

function send500(response){
	response.writeHead(500, {'Content-Type':'text/html'});
	response.end('500 server internal error');
}