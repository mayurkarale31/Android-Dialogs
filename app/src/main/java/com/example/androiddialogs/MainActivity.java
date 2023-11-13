package com.example.androiddialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnAlertDialog;
    private Button btnDatePickerDialog;
    private Button btnTimePickerDialog;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bitcode");
                builder.setIcon(R.mipmap.alertmessage);
                builder.setMessage("Do you need a job?");

                DialogInterface.OnClickListener listener = new AlertDialogButtonListener();
                builder.setPositiveButton("Yes", listener);
                builder.setNegativeButton("No", listener);
                builder.setNeutralButton("I don't Know", listener);

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mt("Dialog is cancelled");
                    }
                });

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mt("Dialog Dismiss");
                    }
                });

                //Alert Dialog Button Listener
                /*builder.setPositiveButton("Yes", new AlertDialogPositiveButtonListener());
                  builder.setNegativeButton("I'm Rich", new AlertDialogNegativeButtonListener());
                  builder.setNeutralButton("I don't know",new AlertDialogNeutralButtonListener());*/

                builder.setCancelable(true);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnDatePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new myOnDateSetListener(), 2022, 8, 13);
                datePickerDialog.show();
            }
        });

        btnTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new myOnTimeSetListener(), 9,23, false);
                timePickerDialog.show();
            }
        });
        btnLogin.setOnClickListener(new btnLoginClickListener());
    }
    private class AlertDialogButtonListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    mt("Yes");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mt("No");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("Don't Know");
                    break;
            }
        }
    }
    private class AlertDialogPositiveButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            mt("You said yes");
        }
    }

    private class AlertDialogNegativeButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            mt("You said no!");
        }
    }

    private class AlertDialogNeutralButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            mt("You are confused...");
        }
    }

    private class myOnDateSetListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btnDatePickerDialog.setText(dayOfMonth + " - " + (month+1) + " - " + year);
        }
    }

    private class myOnTimeSetListener implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            btnTimePickerDialog.setText(hours + ":" + minutes);
        }
    }

    private class btnLoginClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.login_dialog);

            EditText edtUsername = dialog.findViewById(R.id.edtUsername);
            EditText edtPassword = dialog.findViewById(R.id.edtPassword);
            Button btnLogin = dialog.findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtUsername.getText().toString().equals("bitcode") && edtPassword.getText().toString().equals("bitcode")){
                        mt("Login Successfully..");
                        dialog.dismiss();
                    }
                    else {
                        mt("Login Failed ..!");
                    }
                }
            });
            dialog.show();
        }
    }
    private void mt(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    private void initviews(){
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnLogin = findViewById(R.id.btnLogin);
    }
}