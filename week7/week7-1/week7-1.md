[ Date : 2017. 02. 20(월) ]

					-------------------Today's Topic --------------
								(1) 이클립스에서 MySQL에 접속하기.
								(2) Java Syntax ( Timeline / 날짜, 시간 얻기 / String join )
								(3) 람다식
					-----------------------------------------------

 프로젝트 명 : DataBase(이클립스), Lambda(안드로이드)

 내용 : 이클립스에서 MySQL에 접속하여 C.R.U.D를 사용하기,
        람다식 이해하기.

# 1. 이클립스에서 MySQL에 접속하기.

##1.1 MySQL 드라이버 : Data base driver for java platform and development

이클립스에서 MySQL에 접속하기 위해서는 MySQL 드라이버를 설치해야 합니다. 

![](http://i.imgur.com/mWU5EBe.png)

--> MySQL 드라이버로 여기서는 Connector/J를 사용하겠습니다.

![](http://i.imgur.com/iMljGKH.png)

-->일단 알아보기 쉽게 사용하려는 프로젝트 하위에 mysql-connector-java-5.1.40-bin.jar를 보관합니다.

![](http://i.imgur.com/EJGn42d.png)

--> java Build Path의 library 탭에 mysql-connector-java-5.1.40-bin.jar를 추가합니다.

![](http://i.imgur.com/jfLMCJs.png)

--> DataBase 프로젝트 하위 목록중 Referenced Libraries에 라이브러리가 추가된 것을 확인할 수 있습니다. 

##1.2 C.R.U.D operation

###1.2.1 MySQL에 연결하기 

![](http://i.imgur.com/nH69ye1.png)


(1) MySQL에 접속할 root 계정과, 비밀번호, DB 이름을 변수로 선언합니다.

(2) DB에 연결할 주소를 url에 담고 Class.forName()으로 서버쪽 라이브러리를 동적으로 로드합니다.

(3) Connection 인스턴스에 DriverManger를 통해 MySQL에 접속할 객체를 반환합니다. 

###1.2.1 Create

			쿼리문 : INSERT INTO 테이블명(필드1, 필드2) VALUES ('값', '값' );

>> create를 하기 전 auto-closeable을 이해해야 합니다.
> auto-closeable이란? 예외 발생 여부와 상관없이 사용했던 리소스 객체의 close()메소드를 자동적으로 호출하여 안전하게 객체를 닫아줍니다.
> ( 출처 : [http://palpit.tistory.com/913](http://palpit.tistory.com/913) )


##### auto-closeable이 없는 자바 1.6 이하 버전

![](http://i.imgur.com/as71TiB.png)

(1) Connection 인스턴스에 connect()를 사용하여 DB 연결할 객체를 반환합니다.

(2) sql에 MySQL에 insert할 쿼리문 작성

(3) DB에 사용할 실행 단위 Statement 인스턴스 생성.

(4) Statement 인스턴스가 sql문을 데이터베이스로 전송합니다.

(5) 사용한 DB 연결을 닫아줍니다.

##### auto-closeable이 있는 자바 1.7 이상 버전(try - with - resources)

![](http://i.imgur.com/ELJvxwX.png)

--> 코드는 1.6이하 버전과 동일합니다. 다만 close() 부분처리가 다릅니다. try 괄호 안에 리소스 객체 선언을 넣어주었습니다. 


##### 대용량처리 데이터를 한번에 입력하는 경우 (PreparedStatement를 사용할 것)

![](http://i.imgur.com/Qn4hIVV.png)

>> 일반 Statement와 PreparedStatement의 차이는 동일한 쿼리문을 반복할 때 드러납니다.
Statement는 쿼리를 수행할 때마다 쿼리 문장 분석, 컴파일, 실행을 계속 반복해주어야 하지만,
PreparedStatement는 위의 과정을 한번만 수행하면 이미 메모리에 저장되어 있기 때문에 쿼리문에 직접적으로 바뀌는 데이터 입력값들만 다르게 처리하면 됩니다.

--> 예를 들어 Statement는 특정 데이터 값을 추가할 경우 다음의 코드들을 계속 반복해 주어야 합니다.

						String sql = "insert into bbs1(title, content, nDate) values('"+ title + "','"+content +"',now());";
						Statement stmt = conn.createStatement();
						stmt.execute(sql);	

--> 하지만,  PreparedStatement에서는 아래 sql문에 바뀔 부분만 '?' 로 표시하고 setString에서 추가하고 싶은 데이터 값들만을 넣어주면 됩니다.

		String sql = "insert into bbs1(title, content, nDate) values(?,?,now());";	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,title);
		pstmt.setString(2,content);
		pstmt.execute();	

###1.2.2 Read 

			쿼리문 : SELECT * FROM 테이블명 WHERE 필드='조건' AND 필드2='조건2'

####readAll() : 테이블에 있는 모든 데이터를 읽어온다.

![](http://i.imgur.com/R880Qsz.png)

(1) 쿼리를 작성합니다.

(2) Statement 객체는 Statement 인터페이스를 구현한 Connection 클래스의 createStatement( ) 메소드를 호출함으로써 얻어집니다.

(3) 쿼리한 테이블의 결과값을 ResultSet 참조변수에 담으면 커서로 동작하게 됩니다.

(4) next()는 다음 위치를 가리키고 데이터가 있으면 true, 없으면 false를 반환합니다.

( 출처 : [http://all-record.tistory.com/79](http://all-record.tistory.com/79) )


####read() : 테이블에 있는 데이터 중, 특정 조건에 해당하는 것만 불러온다.

![](http://i.imgur.com/MCvOO4l.png)

--> readAll()과 비교하였을 때, 달라지는 부분은 Statement 대신에 PreparedStatement를 사용하였습니다.
					
					setInt(위치, 값)은 쿼리문에 위치한 ?에 값을 넘기는 역할 입니다. 
					- 위치는 SQL 문의 첫번째 ? 특수문자가 오는 위치를 가리킵니다.
					- 값은 위치에 대응하는 값으로써 전달값입니다.( 넘겨주는 인자값의 타입은 set___에 따라 달라집니다.)
					
###1.2.3 Update

			쿼리문 : UPDATE 테이블명 SET 필드='값', 필드2='값', 필드3='값' WHERE 필드 LIKE '조건'

![](http://i.imgur.com/v83MdfY.png)

(1) 쿼리를 작성합니다.

(2) Connection 객체의 prepareStatement() 메소드를 사용해서 PreparedStatement 객체를 생성합니다.

(3) set__ 로 쿼리문의 해당 필드에 대응되는 값들을 입력합니다.

(4) executeUpdate()는 MySQL에 쿼리문을 전송합니다.



###1.2.4 Delete

			쿼리문 : DELETE FROM 테이블명 WHERE 조건

![](http://i.imgur.com/rlYPQci.png)

--> Update 동작과 같고 쿼리문만 다르게 하면 됩니다.



--------------------------------------------------


# 2. Java Syntax ( Timeline / 날짜, 시간 얻기 / String join ) - java8 추가!!!!!!


## 2.1 TimeLine

### currentTimeMillis()를 이용하여 소요된 시간 계산하기.

				start = System.currentTimeMillis();
				double r = 0;
				for(Long i = 0L; i< 1000000000L ; i++){
					r = r+i;
				}
				end = System.currentTimeMillis();
				period = end - start;
				System.out.println("소요시간 : " + period);


### Instant 인스턴스로 소요된 시간 계산하기.

				Instant start = Instant.now();
				double r = 0;
				for(Long i = 0L; i< 1000000000L ; i++){
					r = r+i;
				}
				Instant end = Instant.now();
				Duration period = Duration.between(start, end);
				Long millis = period.toMillis();
				System.out.printf("소요시간:%d", millis);

>>Instant 인스턴스로 사용하였을 때가 오차가 적습니다.

## 2.2 날짜 불러오기 

### 날짜 불러오기

![](http://i.imgur.com/xrqHLfv.png)

[출력화면]

![](http://i.imgur.com/7CUky4b.png)

--> LocalDate에 정의된 메소드를 사용하여 현재 날짜와 사용자가 원하는 날짜 값들을 불러올 수 있습니다.


### 사용자가 원하는 날짜 불러오기

![](http://i.imgur.com/OgofEoK.png)

[출력화면]

![](http://i.imgur.com/mlI7GsJ.png)

## String join

--- 여기는 아직..... ------


---------------------------------------------------



# 3. Lambda

## 람다식이란?

자바 1.8부터 등장한 특징입니다. 함수식에서 불필요한 코드를 줄여 함수 표현식을 축약형으로 나타내고자 등장하였습니다.


## Gradle 설정

![](http://i.imgur.com/JZEGm0H.png)

--> build.gradle(앱 모듈)에 java 8을 사용하기 위한 설정을 추가해줍니다.

## Lambda를 사용하는 이유? 조건?

Lambda를 사용함으로써 코드량을 줄일 수 있습니다. Lambda를 사용하기 위해서는 **콜백 객체가 인자로 넘어가는 곳**이어야 하고 객체에는 **함수가 하나**여야 합니다.

## Lambda 작성법
			
			         1. 콜백객체에서 하나의 함수에 있는 로직블럭만 사용된다.
			         2. 함수명은 생략하고 괄호와 인자(타입생략)만 표시한다.
			         3. 함수명과 로직블럭을 -> 표시로 연결한다.
			         4. 인자가 여러개 일때는 타입을 붙일 수 있다.
			         5. 형태 :  (Parameter) -> { code }


##3.1 Lambda 1

예시) 버튼 클릭 리스너의 경우

(1) 원형은 아래와 같은 형태입니다.

		 btn.setOnClickListener(
            public void onClick(View view){
               System.out.println(view);
        	}
 		);	

(2) 위 코드를 람다식으로 변형하면,

        람다 1: 함수명 생략
			btn.setOnClickListener(
       		 	(View view) -> {System.out.println(view); }
			);	
    
	    람다 2 : 함수 인자타입 생략
			btn.setOnClickListener(
        		(view) -> {System.out.println(view); }
			);

        람다 3: 함수 괄호 생략
    		btn.setOnClickListener(
    		    view -> { System.out.println(view); }
			);

        람다 4: 한줄일경우 코드 괄호 생략, 세미콜론 생략
			btn.setOnClickListener(        
				view -> System.out.println(view)
			);
        
		람다 5 : 코드측 함수가 받는 인자가 하나일 경우 참조형 메소드로 작성
			btn.setOnClickListener(    
            	System.out::println
			);

##3.2 Lambda 2

![](http://i.imgur.com/fAUCRx7.png)


![](http://i.imgur.com/E39tluu.png)



##3.3 Lambda 3 

![](http://i.imgur.com/3IIrxed.png)


--> 람다가 정말 사용되는 이유는?? 대용량의 데이터를 처리하기에 적합합니다.

위 코드는 버튼 btnLoop와 btnStream이 있을 때, 각각의 버튼을 눌렀을 경우 objectArray[]의 내용을 처리하는 방식이 각각 다릅니다.

(1) btnLoop 버튼을 눌렀을 경우, for-loop에서 String 타입의 str은 objectArray를 다 읽고나서 "System.ou.println"을 처리합니다. ( 이런 방식은 대용량의 데이터를 처리하는 경우 많은 시간이 걸리게 됩니다. )

(2) btnStream은 Stream 타입의 stream이 objectArray를 읽으면서 "System.out.println"을 바로 처리합니다. 

>> 람다식의 장점은 기존의 iteration 대신에 forEach를 사용한다는 것과 Stream에서 람다식이 파라미터 값으로 전달될 수 있다는 점입니다. 더 자세한 내용은 다음 링크를 보시면 도움 될겁니다.!!!! [http://m.blog.naver.com/2feelus/220706579677](http://m.blog.naver.com/2feelus/220706579677)






