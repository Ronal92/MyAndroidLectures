package com.jinwoo.android.lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btnLambda);


        // 람다를 사용하는 이유
        // 1. 코드량을 줄일 수 있다.

        // 람다 사용조건
        // 1. 콜백 객체가 인자로 넘어가는 곳
        // 2. 콜백 객체에 함수가 하나여야 한다.

        // 람다 작성법
        // 1. 콜백객체에서 하나의 함수에 있는 로직블럭만 사용된다.
        // 2. 함수명은 생략하고 괄호와 인자(타입생략)만 표시한다.
        // 3. 함수명과 로직블럭을 -> 표시로 연결한다.
        // 4. 인자가 여러개 일때는 타입을 붙일 수 있다.
        // (Parameter) -> { code }

        btn.setOnClickListener(

        //원형 :
        //    public void onClick(View view){
        //       System.out.println(view);
        //    }

        //람다 1: 함수명 생략
        //(View view) -> {System.out.println(view); }

        //람다 2 : 함수 인자타입 생략
        //(view) -> {System.out.println(view); }

        //람다 3: 함수 괄호 생략
        //view -> { System.out.println(view); }

        //람다 4: 한줄일경우 코드 괄호 생략, 세미콜론 생략
        //view -> System.out.println(view)

        //람다 5 : 코드측 함수가 받는 인자가 하나일 경우 참조형 메소드로 작성
            System.out::println

        );

        Button btnLoop = (Button) findViewById(R.id.btnLoop);
        Button btnStream = (Button) findViewById(R.id.btnStream);

        String objectArray[] = {"A", "B", "C", "DX", "E", "F", "G", "H", "I", "J", "K"};

        btnLoop.setOnClickListener(
                (v) -> {  // str이 objectArray를 읽고나서 다음 로직을 수행한다.
                    for (String str: objectArray){
                        if(str.length() == 1){
                            System.out.println("I am " + str);
                        }
                    }
                }
        );

        btnStream.setOnClickListener(
                (v) -> { // str이 objectArray를 읽으면서 로직을 수행한다.
                    Stream<String> stream = Arrays.stream(objectArray);
                    stream.filter(a->a.length() == 1).forEach(a->System.out.println(a));
                }
        );

    }

    public void runLambdaFunction(){
        ClickListener arg = calc(); // calc가 호출되면 calc 함수에 정의된 람다식이 넘어온다.
        // arg : num -> num * num
        int result = arg.squareParameter(50);
        System.out.print("result: " + result);
    }

    public ClickListener calc(){
        return num -> num * num;
        //return num -> { return num * num; };
    }

    interface ClickListener {
        public abstract int squareParameter(int num); // 함수는 하나만 사용한다.
    }

    /* 하나의 함수 instance로 작성
       함수를 개체화 한다.
    함수이름 생략 / (파라미터타입 생략) -> {
        실행블럭
    }
     */
}
