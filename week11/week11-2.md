[ Date : 2017. 03. 21(화) ]

					----------------- Today's Topic --------------------
								(1) Properties와 SharedPreference란??
								(2) Properties
								(3) SharedPreference
					----------------------------------------------------

- 프로젝트명 : [SharedPreference]

- 내용 : Properties와 SharedPreference의 차이를 이해하고 각각의 사용법을 구현하였습니다.

- 참고 사이트

		http://j07051.tistory.com/326
	

   
# 1. Properties와 SharedPreference란??

앱을 실행하고 나서 설정파일을 변경하면 그 변경된 내용을 계속 저장해야 될 필요가 있습니다. 즉 자주 변경되지 않는 설정파일이 존재하는데, 이 내용들을 따로 DB에 저장하지 않고 프로퍼티(properties)나 혹은 SharedPreference로 만들고 필요할 때만 읽어서 사용할 수 있습니다.


---------------------------------------------------

# 2. Properties

## 출력 결과

![](http://i.imgur.com/TawBgxi.png)


![](http://i.imgur.com/t2ASjk4.png)


--> 앱실행시, 처음에 도움말 창이 뜹니다. 사용자가 CLOSE버튼을 누르면 현재 상태를 프로퍼티에 저장하여 그 다음 앱실행시에는 도움말 창이 뜨지 않고 두번째 화면이 바로 나타납니다.  

## 2.1 코드

###2.1.1 PropertyUtil 클래스 생성

--> 프로퍼티를 사용하기 위한 메소드들을 특정 클래스에 모아두어 정리하였습니다. 여러 액티비티에서 프로퍼티 클래스를 사용할 때, 하나의 경로만 사용하도록 싱글턴 객체를 이용하였습니다.

[PropertyUtil.class]

![](http://i.imgur.com/uxaHEjW.png)

(1) 싱글턴을 만들기 위해 생성자 접근 권한을 private로 하고 getInstance(()로 싱글턴 객체를 전달합니다. 

[PropertyUtil.class]

![](http://i.imgur.com/cRIDFyd.png)

--> 프로퍼티에 저장된 내용을 읽어오는 메소드입니다.

(2) 프로퍼티 객체를 생성합니다. 

					Properties prop = new Properties();

(3) 프로퍼티 파일 스트림에 담고 파일을 로딩합니다.

	        FileInputStream fis = new FileInputStream(internalStoragePath + "/" + propertyFile);
            prop.load(fis);

(4) key 값에 해당하는 value를 찾어 반환합니다.
			
			value = prop.getProperty(key);

[PropertyUtil.class]

![](http://i.imgur.com/l7PHphk.png)

--> 특정 키, 값으로 프로퍼티에 저장합니다.

(5) 프로퍼티 객체를 생성하고 저장하려는 키와 값을 만든 객체에 추가합니다.

			        Properties prop = new Properties();
        			prop.put(key, value);
	
(6) 앱의 내부저장소에 파일 형태로 저장합니다. store() 메소드는 매개변수로 OutputStream과 String을 받는데 여기서 string은 프로퍼티파일의 첫번째줄에 코멘트로 저장되어집니다.
		
            FileOutputStream fos = new FileOutputStream(internalStoragePath + "/" + propertyFile);
            prop.store(fos, "comment"); // key = value

### 2.1.2 MainActivity에서 동작

[MainActivity.class]

![](http://i.imgur.com/vXOH2VE.png)


(1) 프로퍼티 객체를 불러옵니다.

(2) 앱을 실행시켰을 때, 프로퍼티에 "false" 값이 있는지 확인합니다. "false" 값이 존재하면 도움말 창을 닫습니다. 

(3) CLOSE 버튼을 누르면 도움말 창을 닫고 키와 값을 프로퍼티에 저장합니다.

-------------------------------------------------

# 3. SharedPreference

## 출력 결과

![](http://i.imgur.com/5elh9Lw.png)


![](http://i.imgur.com/Yz09XGq.png)


--> 처음 앱 실행시, 이메일과 Switch를 체크하고 SAVE 버튼을 누르면 그 다음 앱실행시에도 사용자가 입력한 내용이 두번째 화면과 같이 나타납니다.

##3.1 코드

###3.1.1 loadSetting

--> 프로퍼티에서 저장된 내용을 가져와서 위젯에 세팅합니다. 

[MainActivity.class]

![](http://i.imgur.com/CnME7f0.png)


(1) SharedPreferences 인스턴스를 생성합니다.

(2) 데이터의 타입에 따라 getString(키, 없을 경우 반환할 값), getBoolean(키, 없을 경우 반환할 값)으로 데이터를 가져옵니다.



###3.1.2 saveSetting

[MainActivity.class]

![](http://i.imgur.com/aDoSiRL.png)

(1) 우선 SharedPreferences의 인스턴스를 생성합니다. 인스턴스를 생성하는 방법으로  getPreferences()와 getSharedPreferences(),  PerferenceManager.getDefaultSharedPreferences() 가 있습니다.

			- getPreferences(int mode) : 하나의 액티비티에서만 사용하는 SharedPreferences를 생성합니다. 생성되는 SharedPreferences 파일은 해당 액티비티의 이름으로 생성됩니다.
			
			- getSharedPreferences(String name, int mode) : 특정 이름을 가지는 SharedPreferences를 생성합니다.
			
			- PerferenceManager.getDefaultSharedPreferences(Context context) : 환경 설정 액티비티에서 설정한 값이 저장된 SharedPreferences의 데이터에 접근할 때 사용합니다.



(2) SharedPreferences.Editor : 데이터 기록.

			Editor 인스턴스를 받은 후에는 저장하고 싶은 데이터의 형태에 따라 putInt(KEY, VALUE), putFloat(KEY, VALUE), putString(KEY, VALUE) 등의 메서드를 사용하여 데이터를 저장합니다. 데이터 기록을 마친 후에는 commit() 메서드를 호출해야만 변경 사항이 저장됩니다.
