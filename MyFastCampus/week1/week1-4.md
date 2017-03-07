[Date : 2017. 01. 12]

#<데이터 베이스>
 - 보조기억장치에 들어있는 내용들을 다룬다. (자료구조 : 주기억 장치에 있는 내용들을 다룬다)
 - 저장(INSERT),검색(SELECT),수정(UPDATE),삭제(DELETE)작업을 효율적으로 수행 할 수 있도록 
고안된 정보의 집합체



###관계형 DB란?
관계형 데이터베이스는 우리가 흔히 표현하는 행(Column), 열(Record)로 구성된Table간의 관계를 나타낼때 사용한다. 우리는 이렇게 표현된 데이터를  SQL(Structured Query Language)을 사용하여 데이터 관리 및 접근을 한다.



###DBMS란? 
DataBase에 접근할 수 있는 기능을 제공하는 소프트웨어(ex: MySQL, SQLite, MariaDB 등)

###SQL이란?
DBMS를 통해 데이터를 관리하기 위한 구조화된 질의문을 작성하기 위한 언어


![](http://i.imgur.com/6AaxJn9.jpg)

----------


#<네트워크>

###1. Types of Network
######- LAN(Local Area Network)
######- MAN(Metropolitan Area Network)
######- WAN(Wide Area Network)


###2. Network architencture
	문서들이 있는 정보의 저장소이다.
	분산과 연결 : 정보를 제공하는 주체가 많은 경우, 저장소를 다른 사람과 공유하며 서로 관리를 하게 된다.

		□ URL [Protocol]://[Host]]:[Port]/[Path]
	
###3. Protocol
	
####① HTTP(Hyper Text Transfer Protocol) : 인터넷 상에서 데이터를 주고받기 위한 서버/클라이언트 모델을 따른다. 	웹서버는 보통 표준 포트인 80번을 사용한다.
				● GET : 정보를 요청하기 위해서 사용한다.(SELECT)
				
				● POST : 정보를 밀어넣기 위해서 사용한다.(INSERT)
				
				● PUT : 정보를 업데이트하기 위해 사용한다.(UPDATE)
				
				● DELETE : 정보를 삭제하기 위해서 사용한다.(DELETE)
				
				● HEAD : (HTTP)헤더 정보만 요청한다. 해당 자원이 존재하는지 혹은 서버에 문제가 없는지 확인하기 위해서 사용한다.
				
				● OPTIONS : 웹서버가 지원하는 메소드의 종류를 요청한다.
				
				● TRACE : 클라이언트의 요청을 그대로 반환한다.




####② TCP/IP
			TCP(Transmission Control Protocol) : 서버와 클라이언트 간의 통신과정에서 데이터를 신뢰성 있게 보낸다.
			IP : 각 node들 사이의 IP 주소가 정해져 있어 TCP에서 온 패킷을 라우터를 통해 해당 목적지에 전송한다.

####➂ FTP(File Transfer Protocol) 
			TCP/IP 위에서 서버와 클라이언트 사이의 파일 전송을 하기 위한 프로토콜이다.
		
####➃ TELNET(TErminaL NETwork) 
			 원격 로그인을 위한 프로토콜
####➄ SSH(Secure Shell) 
			 네트워크 상의 다른 컴퓨터에 로그인하거나 원격 시스테메서 명려을 실행하고 다른 시스템므올 파일을 복사할 수 있도록 해주는 응용 프로그램 또는 프로토콜.
####⓺ SSL(Secure Socket Layer) 
			 웹서버와 브라우저 사이의 보안을 위한 프로토콜

###4. DNS(Domain Name System) 
호스트의 도메인 이름과 호스트의 네트워크 주소를 서로간에 변환한다. 
(예 : www.example.com -> 192.67.8.1 / 192.67.8.1 -> www.example.com)

![](http://i.imgur.com/MRintlL.png)
		DNS 서버 : 웹 서버 주소에 해당하는 IP주소 테이블을 저장하고 있다
				만약 유저가 www.microsoft.com을 입력한다면
			1. local dns server로 이동
			2. root dns server로 이동
			3. .com DNS 서버로 이동
			4. Microsofot.com DNS 서버로 이동
			5. local dns server로 해당 주소를 보내면 
			6. 유저한테 서버 정보를 보여준다.

###5. MAC 
IP와 같이 컴퓨터 고유의 주소이다. 하지만 IP는 논리적인 주소이고 MAC은 물리적인 주소이다.
IP주소의 경우 Private IP address로서 중복될 여지가 있기 때문에 MAC이 특정 주소를 가리키게 된다. 

----------


#암호화

##대칭키 암호화
암호화와 복호화에 같은 암호키를 쓰는 알고리즘	
![](http://cfile234.uf.daum.net/image/1823301449EC7C873C6EC5)

## 공개키 암호화
공개키로 암호화된 데이터를 비밀키를 사용하여 복호화할 수 있는 암호화 알고리즘
![](http://cfile212.uf.daum.net/image/185D6C1249EC7CA0EC1CB9)
##암호화 해시함수  
임의의 데이터를 고정된 길이의 데이터로 매핑하여 원래의 입력값과의 관계를 찾기 어렵게 만든 것. 
![](http://files.idg.co.kr/itworld/image/2015/06/hash_function02(1).png)