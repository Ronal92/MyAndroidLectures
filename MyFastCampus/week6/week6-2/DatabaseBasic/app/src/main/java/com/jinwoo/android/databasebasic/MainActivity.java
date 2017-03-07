package com.jinwoo.android.databasebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.jinwoo.android.databasebasic.domain.Bbs;
import com.jinwoo.android.databasebasic.domain.Memo;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCreate, btnRead, btnDelete, btnUpdate;
    TextView textList;
    EditText editNo, editMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();
        setListener();
    }



    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {

                case R.id.btnCreate:
                    create();
                    break;
                case R.id.btnRead:
                    read();
                    break;
                case R.id.btnDelete:
                    delete();
                    break;
                case R.id.btnUpdate:
                    update();
                    break;
            }
        }  catch(Exception e){
            e.printStackTrace();
        }
    }

    private void create() throws SQLException{
        // 1. DB 연결
        DBHelper dbHelper = new DBHelper(this);
        // 2. Table 연결
        Dao<Memo, Integer> memoDao = dbHelper.getDao(Memo.class);
        // 3. 입력값 화면에서 가져와서 변수에 담고
        String memo = editMemo.getText().toString();
        // 4. 변수에 담긴 입력값으로 domain 클래스 생성자에 대입한 후 DB에 입력
        memoDao.create(new Memo(memo));
        // 5. DB 연결 해제
        dbHelper.close();
        // 6. create 후에 화면에서 글자를 지워준다.
        editMemo.setText("");
    }

    private void read() throws SQLException{
        // 1. DB 연결
        DBHelper dbHelper = new DBHelper(this);
        // 2. Table 연결
        Dao<Memo, Integer> memoDao = dbHelper.getDao(Memo.class);
        List<Memo> list = memoDao.queryForAll();
        String temp = "";
        // 데이터를 한줄씩 읽어서 임시 변수인 temp에 저장한다.
        for(Memo memo : list){
            temp = temp + "no:" + memo.getId() + ", " + memo.getContent() + "\n";
        }
        //화면에 temp 변수의 내용을 뿌려준다.
        textList.setText(temp);
        dbHelper.close();
    }


    private void update() throws SQLException{
        int no = Integer.parseInt(editNo.getText().toString());
        String temp = editMemo.getText().toString();

        DBHelper dbHelper = new DBHelper(this);
        Dao<Memo, Integer> memoDao = dbHelper.getDao(Memo.class);
        // 1. 변경할 레코드를 가져온다.
        Memo memo = memoDao.queryForId(no);
        // 2. 변경한 값을 입력한다.
        memo.setContent(temp);
        // 3. 테이블에 반영한다.
        memoDao.update(memo);
        dbHelper.close();
    }


    private void delete() throws SQLException{
        int no = Integer.parseInt(editNo.getText().toString());
        DBHelper dbHelper = new DBHelper(this);
        Dao<Memo, Integer> memoDao = dbHelper.getDao(Memo.class);
        memoDao.deleteById(no);
        dbHelper.close();
    }


    private void setWidget(){
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        textList = (TextView)findViewById(R.id.textList);

        editNo = (EditText)findViewById(R.id.editNo);
        editMemo = (EditText)findViewById(R.id.editMemo);

    }

    private void setListener() {
        btnCreate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }














    private void insert() throws SQLException {

        // 1. 데이터베이스를 연결합니다. OpenHelperManager에 DBHelper.class를 던져주면 context측이 DBHelper 인스턴스를 생성해준다.
        //    dbHelper는 DB와 연결할 커넥션 객체
        //    OpenHelperManager.getHelper는 싱글통 객체를 생성하기 때문에 close를 하면 안된다.
        DBHelper dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);

        // 2. Dao는 데이터베이스의 특정 bbs 테이블을 조작하기 위한 객체를 생성합니다. (getDao를 직접 호출한다.)
        Dao<Bbs, Long> bbsDao = dbHelper.getBbsDao();

        // 3. 입력값 생성.
        String title = "제목";
        String content = "내용입니다";
        Date currDateTime = new Date(System.currentTimeMillis());

        // 4. 위의 입력값으로 Bbs 객체 생성
        Bbs bbs = new Bbs(title, content, currDateTime);

        // ----------- 생성 (Create) ----------------------
        // 5. 생성된 Bbs rorcpfmf dao를 통해 insert
        bbsDao.create(bbs);

        // 위의 3, 4, 5 번을 한줄로 표현
        bbsDao.create( new Bbs( "제목2", "내용2", new Date(System.currentTimeMillis())));
        bbsDao.create( new Bbs( "제목3", "내용3", new Date(System.currentTimeMillis())));



        List<Bbs> bbsList = bbsDao.queryForAll();
        for(Bbs item : bbsList){
            Log.i("Bbs Item", "id=" + item.getId() + ", title=" + item.getTitle() + ", content=" + item.getContent() + ", Date=" + item.getCurrentDate() );
        }

        // ----------- 읽기 (Read) -----------------------
        // 01. 조건 ID
        Bbs bbs2 = bbsDao.queryForId(2L);
        Log.i("TestBbs one", "content = " + bbs2.getContent());


        // 02. 조건 컬럼명 값
        List<Bbs> bbsList2 = bbsDao.queryForEq("title", "제목3");
        for(Bbs item : bbsList2){
            Log.i("Bbs Item", "queryForEq :::::::: id=" + item.getId() + ", title=" + item.getTitle());
        }

        // 03. 조건 컬럼 raw query
        String query = "SELECT * FROM bbs where title like'%제%'";
        GenericRawResults<Bbs> rawResults = bbsDao.queryRaw(query, bbsDao.getRawRowMapper());
        List<Bbs> bbsList3 = rawResults.getResults();

        for(Bbs item : bbsList3){
            Log.i("Bbs Item", "queryForEq :::::::: id=" + item.getId() + ", title=" + item.getTitle());
        }

        // ---------------- 삭제(Delete) ---------------
        // 아이디로 삭제
        bbsDao.deleteById(5L);
        //
        bbsDao.delete(bbs2);

        // --------------- 수정(Update) ---------------
        Bbs bbsTemp = bbsDao.queryForId(7L);
        bbsTemp.setTitle("7번 수정됨");
        bbsDao.update(bbsTemp);

        List<Bbs> bbsList4 = bbsDao.queryForAll();
        for(Bbs item : bbsList4){
            Log.i("Bbs Item", "id=" + item.getId() + ", title=" + item.getTitle() + ", content=" + item.getContent() + ", Date=" + item.getCurrentDate() );
        }

    }



}
