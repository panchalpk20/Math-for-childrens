package com.example.root.math;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView n1,score;
    TextView n2,sign,stat, txt_ans;
    Button ans1,ans2, ans3;
    int ActAns;
    Animation animation;
    TransitionDrawable td;
    LinearLayout ll;
    Dialog alert;
    int x,y,z;
    int sc;

    int dif = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        // getSupportActionBar().hide(); //hide the title bar
        //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //      WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign = findViewById(R.id.Sign);

        ll = findViewById(R.id.buttonLayout);
        stat = findViewById(R.id.stat);
        txt_ans = findViewById(R.id.ansrr);
        stat.setTextColor(Color.WHITE);
        stat.setVisibility(View.INVISIBLE);

        sign.setTextColor(Color.WHITE);


      //  stat.setVisibility(View.GONE);

        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2); //text viewss
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        score = findViewById(R.id.score);
        ans3 = findViewById(R.id.ans3);//buttons
        animation = AnimationUtils.loadAnimation(this, R.anim.zoom);

        n1.setTextColor(Color.WHITE);
        n2.setTextColor(Color.WHITE);

        sc = sc + getIntent().getIntExtra("score",0 );

        score.setText("Score: "+sc);

        Log.e("","sign from prev"+getIntent().getStringExtra("sign") );
        sign.setText(getIntent().getStringExtra("sign"));
        genNums();





         Log.e(" "+ActAns,""+ActAns );

        ans1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
              checkAns(ans1,ans2,ans3);

            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkAns(ans2,ans1,ans3);
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               checkAns(ans3,ans1,ans2);
            }
        });



    }







    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkAns(Button b, Button fb1, final Button fb2){


    //    td.startTransition(1500);
        int a = Integer.parseInt(b.getText().toString());

        if(a == ActAns) {
 //           Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

            //   animate(true, fb1,fb2);
            Log.e("","correct" );


            fb1.startAnimation(new AnimationUtils().loadAnimation(MainActivity.this, R.anim.out));
            fb2.startAnimation(new AnimationUtils().loadAnimation(MainActivity.this, R.anim.out));
        //    b.startAnimation(new AnimationUtils().loadAnimation(MainActivity.this, R.anim.out));


            fb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.trans_w));
            fb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.trans_w));
            td = (TransitionDrawable) fb1.getBackground();
            td.startTransition(1500);
            td = (TransitionDrawable) fb2.getBackground();
            td.startTransition(1500);

            txt_ans.setTextColor(Color.WHITE);

            txt_ans.setText(" = " + a);
            txt_ans.startAnimation(new AnimationUtils().loadAnimation(getApplicationContext(), R.anim.fade_in));

            stat.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_text));
            stat.setText("Correct!");

            animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
            animation.setStartTime(1500);
            animation.setDuration(1900);
            stat.startAnimation(animation);

            final Intent i = new Intent(this,MainActivity.class );
            i.putExtra("sign",sign.getText().toString() );
            i.putExtra("score", sc+2 );

            new CountDownTimer(2000,100) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    startActivity(i);
                }
            }.start();


            sc += 2;


        }
        else {
            stat.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.wrong_text ));

            Log.e("","incorrect" );
            stat.setText("Wrong!");

            animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
            animation.setStartTime(1500);
            stat.startAnimation(animation);

            b.startAnimation(new AnimationUtils().loadAnimation(MainActivity.this, R.anim.wrong));
             b.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.trans_w ));
             td = (TransitionDrawable) b.getBackground();
             td.startTransition(1500);
             td.reverseTransition(1500);
             sc = sc - 1;
             score.setText("score: "+sc);
        }

        Log.e("score"," score is "+sc  );


        Log.e("","After ifeelse");

    }

    public void genNums(){

        String sg = sign.getText().toString();
        Log.e("","sign from gen num " + sg);


        int a;
        a = new Random().nextInt(10);
        int b;
        b = new Random().nextInt(10);

        if(sg.equals(" / ")){

            while(b == 0){
                b = new Random().nextInt(10);
            }
            a = b*a;

            ActAns = a/b;


        }

        if(sg.equals(" - ")){

            ActAns = a-b;

        }

        if(sg.equals(" X ")){
            ActAns = a * b;
        }

        if(sg.equals(" + ")){
            ActAns = a + b;

        }
        x = ActAns;
        y = x + 2 - new Random().nextInt(2) + 1;
        z = x - b + new Random().nextInt(3) + 1;


        Log.e("","sign   "+sign.getText().toString());

        n1.setText(Integer.toString(a));
        n2.setText(Integer.toString(b));


        int[] ar = {x,y,z};
        Log.e("","  "+ar );
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = new Random().nextInt(i + 1);
            // Simple swap
            int aa = ar[index];
            ar[index] = ar[i];
            ar[i] = aa;
        }
        Log.e("","  "+ar );

        ans1.setText(Integer.toString(ar[0]));
        ans2.setText(Integer.toString(ar[1]));
        ans3.setText(Integer.toString(ar[2]));


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,Main2Activity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }






}
