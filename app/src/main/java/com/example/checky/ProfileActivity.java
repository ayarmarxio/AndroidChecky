package com.example.checky;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String teacherName;
    private String courseName;
    private Float average;

    private Integer q1;
    private Integer q2;
    private Integer q3;
    private Integer q4;
    private Integer q5;
    private Integer q6;

    HashMap<Integer, Integer> seekbarMap;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private Spinner spinnerTeacher;
    private Spinner spinnerCourse;

    private TextView taskDescriptionTV;

    private TextView questionOneTV;
    private TextView questionTwoTV;
    private TextView questionThreeTV;
    private TextView questionFourTV;
    private TextView questionFiveTV;
    private TextView questionSixTV;

    private SeekBar questionOneSeek;
    private SeekBar questionTwoSeek;
    private SeekBar questionThreeSeek;
    private SeekBar questionFourSeek;
    private SeekBar questionFiveSeek;
    private SeekBar questionSixSeek;

    private Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.helloTextView);
        textViewUserEmail.setText("Welcome " + user.getEmail()+"!");

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        questionOneSeek = (SeekBar)findViewById(R.id.questionOneSeek);
        questionTwoSeek = (SeekBar)findViewById(R.id.questionTwoSeek);
        questionThreeSeek = (SeekBar)findViewById(R.id.questionThreeSeek);
        questionFourSeek = (SeekBar)findViewById(R.id.questionFourSeek);
        questionFiveSeek = (SeekBar)findViewById(R.id.questionFiveSeek);
        questionSixSeek = (SeekBar)findViewById(R.id.questionSixSeek);

        seekbarMap = new HashMap<>();

        buttonLogout.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        spinnerTeacher = (Spinner)findViewById(R.id.spinnerTeacher);
        spinnerCourse = (Spinner)findViewById(R.id.spinnerCourse);

        spinnerTeacher.setOnItemSelectedListener(this);
        spinnerCourse.setOnItemSelectedListener(this);

        questionOneSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
               seekbarMap.put(1, progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        questionTwoSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                q2 = seekbarMap.put(2, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        questionThreeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                q3 = seekbarMap.put(3, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        questionFourSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                q4 = seekbarMap.put(4, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        questionFiveSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                q5 = seekbarMap.put(5, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        questionSixSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                seekbarMap.put(6, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (buttonLogout == v){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (submitBtn == v) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference newRegistryRef = db.collection("registry").document();
            Float average = GetAverage();
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Registry registry = new Registry();
            registry.setTeacherName(this.teacherName);
            registry.setCourseName(this.courseName);
            registry.setAverage(average);
            registry.setUserId(userId);

            newRegistryRef.set(registry).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        sendEmail();
                        Toast.makeText(getApplicationContext(), "Information Saved", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Information Failed to Save", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerTeacher){
            this.teacherName = spinnerTeacher.getSelectedItem().toString().trim();
        }

        else if (parent.getId() == R.id.spinnerCourse) {
            this.courseName = spinnerCourse.getSelectedItem().toString().trim();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Float GetAverage(){
        float sum = 0.0f;
              for (float f : seekbarMap.values()){
                  sum += f;
              }

      average = sum /  seekbarMap.size();

      return average;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("teacher", teacherName);
        outState.putString("course", courseName);

        outState.putInt("q1", q1);
        outState.putInt("q2", q2);
        outState.putInt("q3", q3);
        outState.putInt("q4", q4);
        outState.putInt("q5", q5);
        outState.putInt("q6", q6);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.teacherName = savedInstanceState.getString("teacher");
        this.courseName = savedInstanceState.getString("course");
        this.q1 = savedInstanceState.getInt("q1");
        this.q2 = savedInstanceState.getInt("q2");
        this.q3 = savedInstanceState.getInt("q3");
        this.q4 = savedInstanceState.getInt("q4");
        this.q5 = savedInstanceState.getInt("q5");
        this.q6 = savedInstanceState.getInt("q6");

        seekbarMap.put(1, q1);
        seekbarMap.put(2, q2);
        seekbarMap.put(3, q3);
        seekbarMap.put(4, q4);
        seekbarMap.put(5, q5);
        seekbarMap.put(6, q6);
    }

    private void sendEmail() {
        Log.i("Send email", "");
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        String[] TO = {userEmail};
        String[] CC = {"ayarmarxio@hotmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Rating done by: " + userEmail);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "You have been rated in Checky App");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ProfileActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
