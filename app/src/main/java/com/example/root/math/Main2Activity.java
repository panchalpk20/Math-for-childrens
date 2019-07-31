package com.example.root.math;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button add,sub,mul,div;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        // getSupportActionBar().hide(); //hide the title bar
        //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //      WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        dialog = new Dialog(Main2Activity.this);
        dialog.setContentView(R.layout.exit_dialog);

        //Positive Button *********************
        dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        // NEGATIVE BUTTON ***********************
        dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    //    dialog.show();





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        add = findViewById(R.id.plus);
        sub = findViewById(R.id.minus);
        mul = findViewById(R.id.mult);
        div = findViewById(R.id.div);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nxtScreen(" + ");
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nxtScreen(" - ");
            }
        });


        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nxtScreen(" X ");
            }
        });



        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nxtScreen(" / ");
            }
        });


    }

    void nxtScreen(String sign){

        startActivity(new Intent(this, MainActivity.class).putExtra("sign",sign ));
    }


    @Override
    public void onBackPressed(){

        Log.e("","back is pressssed");
        dialog.show();


    }

}
