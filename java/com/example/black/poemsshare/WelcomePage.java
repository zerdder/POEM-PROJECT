package com.example.black.poemsshare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WelcomePage extends AppCompatActivity {
    private Button btuin;
    private Button btuup;
    private EditText etzh;
    private EditText etmm;
    private PoemSQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        etzh = (EditText) findViewById(R.id.zhText);
        etmm = (EditText) findViewById(R.id.mmText);
        btuin = (Button) findViewById(R.id.buttonSignin);
        btuup = (Button) findViewById(R.id.buttonSignup);
        dbHelper = new PoemSQLiteHelper(this,"Poem.db",null,1);

        dbHelper.getWritableDatabase();

        btuin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                SQLiteDatabase mydb = dbHelper.getWritableDatabase();
                Cursor cursor = mydb.rawQuery("select * from USER WHERE Account = ? AND Password = ? "
                        ,new String[] {etzh.getText().toString(),etmm.getText().toString()});
                if(cursor.getCount() != 0){
                    Intent intent = new Intent(WelcomePage.this,TodayActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(WelcomePage.this,"账户名或密码错！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btuup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                etzh.setText(" ");
                etmm.setText(" ");
                Intent intent = new Intent(WelcomePage.this,SignActivity.class);
                startActivity(intent);
            }
        });
    }

}
