var http = require('http');
var express = require('express');
var client = require('mongodb').MongoClient;
var bodyParser = require('body-parser');

var app = express();

// 일반적인 queryString 형태를 처리하는 parser 모듈 사용.
app.use(bodyParser.urlencoded({ extended: false}));

// json을 처리하는 파서 모듈 사용.(json 스트링을 json 객체로 변환해준다. == Gson)
app.use(bodyParser.json());


// get으로 전체 조회
app.get("/bbs",(request,response)=>{
	var skip = parseInt(undefined);
	var offset = parseInt(undefined);
	readAll(response, skip, offset);
});

// 익스프레스의 핵심 RESTFul application
app.get("/bbs/:skip/:offset",(request,response)=>{
	var skip = parseInt(request.params.skip);
	var offset = parseInt(request.params.offset);
	readAll(response, skip, offset);
});

//post
app.post("/bbs",(request,response) =>{
	console.log('body : ' + request.body);
	var postdata = request.body;
	createData(response, postdata);

});

// 서버 실행 
http.createServer(app).listen(8080, ()=>{
	console.log('Server is running......');
});

function createData(response, data){
		//data=JSON.parse(data); // 스트링을 json 데이터로 바꿔준다. 
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

// 명령어 
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