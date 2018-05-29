package com.example.jamal.viewsandlayouts;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button exit,check;
    private CheckBox french , arabic, english ;
    private RadioButton major,child,minor;
    private EditText email,firstName,lastName,description,phone;
    private ProgressBar progressBar;
    private RatingBar ratingBar;
    Handler handler = new Handler();
    String text  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        implementListeners();
    }


    //initializing Views
    public void initializeViews(){

        // EditTexts
        email = (EditText) findViewById(R.id.email);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phone = (EditText) findViewById(R.id.phone);
        description = (EditText) findViewById(R.id.selfDescription);

        // Cheboxs
        french = (CheckBox) findViewById(R.id.french);
        english = (CheckBox) findViewById(R.id.english);
        arabic = (CheckBox) findViewById(R.id.arabic);

        // RadioButtons
        major=(RadioButton) findViewById(R.id.major);
        minor=(RadioButton) findViewById(R.id.minor);
        child=(RadioButton) findViewById(R.id.child);

        //  Buttons
        check = (Button) findViewById(R.id.check);
        exit = (Button) findViewById(R.id.exit);

        //  RatingBar
        ratingBar= (RatingBar) findViewById(R.id.RatingBar);

        //  ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    // Listiners
    public void implementListeners(){


        // check button listener
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkItems();
            }
        });

        // exit button listener
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                Thread myThread=  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0 ; i<40;i++){
                            progressBar.setProgress(i);
                            try {
                                Thread.sleep(100);
                            }catch(InterruptedException E){

                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }


                });


                myThread.start();

                try {
                    myThread.join();
                }catch(InterruptedException E){

                }

                finish();





            }
        });


        // RatingBar listener
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                if(rating>=2.5){
                    showShortToast(" good result  ! ");
                }else {
                    showShortToast(" bad result  ! ");
                }
            }
        });
    }



    // checkItems
    public void checkItems(){
        text ="" ;
        if(firstName.getText().toString().equals("")){
                text = text+" please enter your  first name  !!! ";

        }
        if( lastName.getText().toString().equals("")){
            text = text+" please enter your  last name  !!!  ";

        }
        if(phone.getText().toString().equals("")){
            text = text+" please enter your  phone  !!!  ";

        }
        if(email.getText().toString().equals("")){
            text = text+" please enter your  email  !!!  ";

        }
        if(description.getText().toString().equals("")){
            text = text+" please enter your  description  !!!  ";

        }
        if( phone.getText().toString().equals("")
                || email.getText().toString().equals("") || description.getText().toString().equals("")){
            text = text+" please enter your  phone number  !!!  ";

        }


        if(!major.isChecked()  && minor.isChecked() == false && child.isChecked() == false  ){
           text = text+" how old are you !!! ";
        }

        if(french.isChecked() == false && english.isChecked() == false && arabic.isChecked() == false){
            text = text+" which language do you speak !!! ";
        }

        if(text.equals("")){
            showToast("thank you for your interest ");
        }else{
            showToast(text);
        }
    }

    public void showToast(String text){
        Toast.makeText(this , text,Toast.LENGTH_LONG).show();
    }
    public void showShortToast(String text){
        Toast.makeText(getApplicationContext() , text,Toast.LENGTH_SHORT).show();
    }
}
