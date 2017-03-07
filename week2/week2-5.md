[ Date : 2017. 01. 20 ]
						
						 --------------------Today's Topic------------------------ 
                           (1) 자바 syntax
                                 string 연산
                                 try with
                                 mkdir()과 mkdirs()의 차이
                           (2) Java IO Stream Package
                           (3) IO vs NIO
                           (4) 파일 입출력(코드)
                           (5) 게시판의 데이터베이스를 파일 입출력으로 바꿔주기.(코드)


                        ----------------------------------------------------------
# 자바 syntax
--------------------------------------------------

##1. String 합치기
--강사님 권고 사항--

                          ************************************************
                          *     StringBuffer sbf = new StringBuffer();   *
                          *     sbf.append(“aaa”);                       *
                          *     sbf.append(“bbb”);                       *
                          ************************************************  

                          ************************************************
                          *     StringBuilder abd = new StringBuilder(); *
                          *     sbf.append(“aaa”).append(“bbb”);         *
                          *     sbf.append(“bbb”);                       *
                          ************************************************   

                          ************************************************
                          *     String a = “aaa” + “bbb”                 * 
                          ************************************************    

가급적 StringBuffer 또는 StringBuilder를 사용할 것.!!!!

##2. try-with를 이용한 자원 해제
![](http://i.imgur.com/oC63UjE.png)

##3. mkdir()과 mkdirs()의 차이

File.mkdir() : 만들고자하는 디렉토리의 상위 디렉토리가 존재하지 않는다면, 디렉토리를 만들수 없다.

File.mkdirs() : 만들고자하는 디렉토리의 상위 디렉토리가 존재하지 않아도, 입력한 디렉토리 전체를 생성한다.


----------

# Java IO Stream Package
----------


![](http://i.imgur.com/DUA9ork.png)

여기서 용어의 차이를 설명하자면,
InputStream, OutputStream은 그림, 동영상 파일을 전송할 때 사용하는 것이고
Reader, Writer는 문자 파일을 전송할 때 사용한다.


# IO vs NIO

자바의 IO 는 Blocking 방식으로 통신하였다. Blocking 방식이란, 어떤 작업을 하기 위해서는 먼저 접근한 요청이 끝날 때까지 기다리는 것이다. 이는 서버와 통신하는 사용자가 많아질 경우 쓰레드가 많아지는 과부하 현상이 발생하게 된다.  각각의 쓰레드들은 고유의 stack memory를 사용하고 CPU를 점유하기 때문에 전체 성능이 저하된다. 하지만 NIO 에서는 Non-Blocking 방식으로 설정되어 있다. 위의 Blocking 방식을 피할뿐만 아니라 채널을 통해 버퍼에 접근하여 HDD에 잦은 접근을 예방할 수 있다.
(출처 :  [http://rockdrumy.tistory.com/1096](http://rockdrumy.tistory.com/1096))



----------


# 파일 입출력(코드) -- FileUtil
---------------------------------------------


##FileUtil클래스의 readNio()          
readNio는 NIO에서 파일로부터 입력을 받을 때 사용하도록 정의한 함수이다.

NIO에서 파일로부터 read를 할 때에는 Path를 통해 먼저 불러올 경로를 지정한다.

Files 클래스에 있는 함수(readAllBytes, readAllLines 등)를 사용하여 파일로부터 데이터값을 한번에 읽어들인다.

![](http://i.imgur.com/o7Sc0Xo.png)


##FileUtil클래스의 writeNio()
writeNio()는 NIO에서 파일에 출력할 때 사용하도록 정의한 함수이다.

NIO에서 파일에 write를 할 때에는 Path에 먼저 저장할 경로를 지정한다.

Files.write()를 통해 해당 경로에 사용자의 문자열이 저장된 파일을 쓰도록 한다. 이때, Files.write() 안에는 *content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND* 등과 같은 옵션이 있기 때문에 필요한 옵션을 사용하면 된다. 

![](http://i.imgur.com/jZYG3iO.png)


##FileUtil클래스의 readStream()
readStream()은 IO에서 스트림을 이용해 파일로부터 입력을 받을 때 사용하도록 정의한 함수이다.

FileInputStream을 이용하여 InputStream 객체를 생성한다. 생성된 객체는 InputStreamReader()에 들어가서 Reader 스트림 형태로 바뀌게 된다. BufferedReader는 이 Reader 스트림을 버퍼 기능이 추가된 입력 스트림으로 바꿔준다. 버퍼가 하는 일은 데이터를 한번에 저장했다가 readLine()과 같이 한줄씩 읽어온다.

![](http://i.imgur.com/kkCn7qW.png)


##FileUtil클래스의 writeStream()
writeStream()은 IO에서 스트림을 이용해 파일에 출력할 때 사용하도록 정의한 함수이다.

FileOutputStream 객체를 사용하여 스트림을 생성하고 바이트 단위로 문자를 전송한다.

![](http://i.imgur.com/YHMvGaU.png)


##FileUtil클래스의 readFile()
readFile()은 IO에서 버퍼를 이용해 파일로부터 입력을 받을 때 사용하도록 정의한 함수이다.

BufferedReader를 통해 생성된 객체는 파일의 내용을 버퍼에 저장한 다음 화면에 출력된다. 이 때, readLine()은 한 줄 단위의 스트링을 반환한다.

![](http://i.imgur.com/eIao0jS.png)

##FileUtil클래스의 writeFile()
writeFile()은 IO에서 버퍼를 이용해 파일에 출력할 때 사용하도록 정의한 함수이다.

BufferedWriter를 통해 생성된 객체는 사용자가 입력하는 데이터를 버퍼에 보관한 다음 파일에 작성된다.

![](http://i.imgur.com/aSUlxDn.png)

----------

# 게시판의 데이터베이스를 파일 입출력으로 바꿔주기.(코드)

--------------------------------------------
지금까지 만든 프로젝트는 게시판 기능을 제공한다. 이 코드를 수정하여 데이터 베이스 기능을 ArrayList가 아닌 파일로 데이터베이스를 만들고자 한다.


![](http://i.imgur.com/RUsKch0.png)


- MainBbs : 사용자의 입력을 받는 곳이다. 사용자로부터 책의 이름, 저자명, 내용을 받아서 Bbs 객체에 저장한다.

- Bbs : 책의 번호, 제목, 내용, 저자, 저장 시간을 설정하고 값을 받는다.

- BbsController : MainBbs의 기능(추가, 삭제, 저장 등)을 수행하는 곳이다.

- FileUtil : Bbs의 값들을 파일에 저장한다.

- Util : 현재 시간을 받는 기능(getDatetime)과 사용자로부터 입력 값이 숫자인지를 구분하는 기능(Util)이 있다.

==> 아래 코드들은 이전 프로젝트에서 dataindex.txt와 database.txt를 사용하여 파일문에 직접 입출력을 위해 추가된 코드들이다.

![](http://i.imgur.com/lC6xVyt.png)



![](http://i.imgur.com/fYWzaiw.png)

![](http://i.imgur.com/9blHrKm.png)
 

