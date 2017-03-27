[ Date : 2017. 03. 27(월) ]


// To do : 강의 설명 추가.....

#RESTFul API 추가

[server_beta2.js]

RESTFUL API

코드1.
	// post (게시판 글쓰기 분석
	if(res.indexOf("bbs") == 0 ){


코드2.  RESTFUL API는 주소체계를 설정하는 것.]

	// REST API 는 주소체계에 변수의 값이 담겨온다.
	// bbs/숫자/숫자
	var temp = res.split("/");
	var skip = parseInt(temp[1]);
	var offset = parseInt(temp[1]);









#Express 사용해보기

[server.js]


링크 : http://blog.movenext.co.kr/87
http://blog.naver.com/hyoun1202/220670652183



1. Npm 명령어로 익스프레스 먼저 설ㅊ.

Npm install –g express-generator

Npm install


2. Express 폴더 가서 server.js 생성


3.Restful api, post


#Express + Realm

[server_realm.js]

링크 : http://jinfactory.tistory.com/177

