package com.jinwoo.android.asynctask;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    boolean flag = false;

    TextView txtResult;
    Button btnStart, btnStop;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        txtResult = (TextView)findViewById(R.id.textResult);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    Toast.makeText(MainActivity.this, "실행중입니다.",Toast.LENGTH_SHORT).show();
                }else {
                    String filename = "Dokkabi.mp4";
                    new TestAsync().execute(filename);
                }
            }
        });

        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delFile("Dokkabi.mp4");
            }
        });
    }

    public void delFile(String filename){
        String fullPath = getFullPath(filename);
        File file = new File(fullPath);
        if(file.exists()){
            file.delete();
        }
    }

    public class TestAsync extends AsyncTask<String, Integer, Boolean>{
        // AsyncTask Generic 이 가리키는 것
        // 1. doInBackground 의 파라미터 (파일명 등을 execute에서 보낸다)
        // 2. onProgressUpdate 의 파라미터 ( 다운로드 표시 값을 넘긴다.)

        // AsyncTask의 background 프로세스 전에 호출되는 함수
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            flag = true;
            progressBar.setProgress(0);
            //파일 사이즈를 입력
            AssetManager manager = getAssets();
            try {
                // 파일 사이즈를 가져오기 위해 파일 스트림 생성
                InputStream is = manager.open("Dokkabi.mp4");
                int fileSize = is.available(); // stream에 연결된 파일사이즈를 리턴해준다.
                // 프로그래스방의 최대값에 파일사이즈 입력
                is.close();
                progressBar.setMax(fileSize);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        // sub thread에서 실행되는 함수
        @Override
        protected Boolean doInBackground(String... params) {
            String filename = params[0];
            assetToDisk(filename);
            return true;
        }

        // doInBackground 가 종료된 후에 호출되는 함수
        //                의 return 값을 받는다
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean == true){
                txtResult.setText("완료되었습니다");
            }
        }

        int totalSize = 0;
        // main thread에서 실행되는 함수
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int size = values[0];
            // 넘어온 파일길이를 totalsize에 계속 더해준다.
            totalSize = totalSize + size;
            txtResult.setText(totalSize + " Byte");
            progressBar.setProgress(totalSize);
        }

        // assets에 있는 파일을 쓰기가능한 internal Storage로 복사한다.
        // - InternalStorage의 경로구조
        //   /data/data/패키지명
        public void assetToDisk(String filename){        // 경로 + 파일이름

            // 스트림 선언
            // try - catch 문안에 선언을 하게 되면 exception 발생시 close 함수를 호출할 방법이 없기 때문에 따로 빼 놓았다.
            InputStream is = null;
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            try {
                // 1. assets에 있는 파일을 filename으로 읽어온다.
                AssetManager manager = getAssets();
                // 2. 파일 스트림 생성
                is = manager.open(filename);
                // 3. 버퍼 스트림으로 래핑 ( 한번에 여러개의 데이터를 가져오기 위한 래핑)
                bis = new BufferedInputStream(is);

                // 쓰기 위한 준비 작업
                // 4. 저장할 위치에 파일이 없으면 생성
                String targetFile = getFullPath(filename);
                File file = new File(targetFile);
                if (!file.exists()) {
                    file.createNewFile();
                }
                // 5. 쓰기 스트림을 생성
                fos = new FileOutputStream(file);
                // 6. 버퍼 스트림으로 동시에 여러 개의 데이터를 쓰기위한 래핑 (래퍼 클래스???)
                bos = new BufferedOutputStream(fos);

                // 읽어올 데이터의 길이를 담아둘 변수
                int read = -1; // 모두 읽어오면 -1이 저장된다.
                // 한번에 읽을 버퍼의 크기를 지정
                byte buffer[] = new byte[1024];
                // 읽어올 데이터가 없을때까지 반복문을 돌면서 읽고 쓴다.
                    while ((read = bis.read(buffer, 0, 1024)) != -1) {
                        // 0부터 읽어온 데이터의 길이(read)만큼만 쓴다. 왜? 데이터의 마지막 부분에 가서는 반드시 1024바이트까지 있으란 보장이 없기 때문이다.
                        bos.write(buffer, 0, read);
                        // AsyncTask의 onProgressUpdate
                        publishProgress(read);
                    }
                // 남아있는 데이터를 다 흘려보낸다.
                bos.flush();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try {
                    // 역순으로 리소스를 해제해준다.
                    if(bos != null) bos.close();
                    if(fos != null) fos.close();
                    if(bis != null) bis.close();
                    if(is != null) is.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        // Internal Storage 내의 파일의 전체 경로를 가져오는 함수

    }

    private String getFullPath(String filename){
        // /data/data/패키지명/files + / + 파일명
        return getFilesDir().getAbsolutePath() + File.separator + filename; // File.separator는 시스템마다 다른 구분자(/)를 알아서 가져다 준다.
    }

}
