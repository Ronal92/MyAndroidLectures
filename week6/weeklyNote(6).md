
	Weekly Summary!!
		안드로이드 스튜디오에는 여러 데이터 저장소가 있습니다. 이 중에서 오늘 다룰 주제는
		"DB 연동" 입니다. 앱 특성상 많은 유저의 정보를 담기 때문에 이 정보를 저장하고 보관할 저장소가 필요합니다. DataBase의 특징을 이해하고 안드로이드 스튜디오와 연동해보겠습니다.

		실습한 내용은 저의 [https://github.com/Ronal92/MyAndroidLectures]를, 전체 코드는 [https://github.com/Ronal92] 아래 프로젝트별로 Repository를 만들어 놓았습니다.^^(디자인 패턴 프로젝트는 이클립스로 돼있는데 추후에 올릴게요~~~~)
	   	 


					-------------------- 목차 -----------------
										(1) DB <-> 안드로이드 연동
										(2) DBHelper 클래스 생성
										(3) 데이터 베이스의 테이블이 될 클래스 생성
										(4) CREATE
										(5) READ
										(6) UPDATE
										(7) DELETE
					----------------------------------------------------

 프로젝트명 : [DataBaseBasic]

 내용 : OrmLiteSqlite를 이용하여 안드로이와 DB를 연동.
		C(create)R(read)U(update)D(delete) 구현해보기.

![](http://i.imgur.com/sYSfCj0.png)

--> CRUD를 버튼으로 생성하여 각 버튼마다 사용자의 입력값을 테이블에 저장, 수정 등을 할겁니다.

# 1. DB <-> 안드로이드 연동하기

DBMS로 사용할 툴은 ormlite 입니다. ormlite는 소스코드에서 직접 쿼리를 생성하지 않고 클객체 안에 getter/ setter 로 쿼리를 자동 생성해 줍니다.

ormlite를 사용하기 위해서는 먼저 Gradle에 dependencies를 설정해 주어야 합니다.

				    compile 'com.j256.ormlite:ormlite-core:5.0'
				    compile 'com.j256.ormlite:ormlite-android:5.0'

---------------------------------------------------

# 2. DBHelper 클래스 생성.

DBHelper 클래스는 OrmLiteSqliteOpenHelper를 상속합니다. ORMLite 메소드를 이용하여 데이터 테이블을 생성하거나 수정합니다.

![](http://i.imgur.com/K1oQCur.png)

## 코드 [DbHelper.class]

![](http://i.imgur.com/xcfv1mN.png)

(1) DBHelper에서는 테이블이 담길 파일명으로 "database.db"와 버전 "1"을 생성합니다.

(2) onCreate()에서 Memo.class에 있는 내용으로 테이블을 만듭니다.

![](http://i.imgur.com/EhUxsga.png)

(3) onUpgrade()에서는 테이블의 변경사항이 있을 경우 호출되기 때문에, 기존의 테이블을 삭제하고 새롭게 생성합니다.

(4) 데이터 테이블에 접근하여 CRUD를 할 수 있는 객체를 반환합니다.(Dao)

## 정리

![](http://i.imgur.com/vVmCMns.png)


------------------------------------------------------

# 3. 데이터 베이스의 테이블이 될 클래스 생성(Memo.class)

ORMLite에서 지원하는 애노테이션을 사용하여 테이블을 생성할 수 있습니다.

## 코드 [Memo.class]

![](http://i.imgur.com/QIqs46U.png)

#### (1) @DatabaseTable(tableName = "memo")

클래스 상단에 추가하여 생성되는 객체가 테이블임을 알려줍니다.

#### (2) @DatabaseField

테이블의 컬럼임을 명시합니다. (generatedId는 테이블에 실제 value 값을 주지 않더라도 자동으로 번호가 생성되게 합니다.)

---------------------------------------------------------

# 4. CREATE

## 테이블 생성

![](http://i.imgur.com/hDNtvLL.png)

## 코드 

[MainActivity.class]
![](http://i.imgur.com/ooSxDcY.png)




------------------------------------------------------

# 5. READ

## 테이블 읽어오기

![](http://i.imgur.com/ybzLsi7.png)

## 코드 

[MainActivity.class]
![](http://i.imgur.com/FQSHypN.png)




-----------------------------------------------------

# 6. UPDATE

## 테이블 수정하기

![](http://i.imgur.com/IIGQE01.png)

## 코드 

[MainActivity.class]
![](http://i.imgur.com/4Wqjqup.png)


------------------------------------------------------

# 7. DELETE

## 테이블 안에 특정 내용 지우기

![](http://i.imgur.com/4MjczZJ.png)

## 코드 

[MainActivity.class]
![](http://i.imgur.com/Vw88V4K.png)
