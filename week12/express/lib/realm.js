var Realm = require('realm'); 


// 객체 생성( realm 데이터 베이스를 사용하기 위한 설정 객체 )
// Realm 테이블 설정값
let UserSchema = { 
	name : 'User', 
	properties : { 
		name : 'string',
		 email : 'string', 
		 tel : 'string', 
		 date : 'date'
	  } 
}; 

// 위에서 정의한 Realm 테이블의 설정값을 사용해서 테이블을 생성한다. 
var UserRealm = new Realm({ 
	path : 'user.realm', 
	schema : [UserSchema] 
});


module.exports = { 
	UserRealm : UserRealm 
};

