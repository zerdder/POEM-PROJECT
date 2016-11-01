
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLPermission;

/**
 * A login screen that offers login via email/password.
 */
public class SignActivity extends AppCompatActivity {

    private EditText etzh;
    private EditText etmm;
    private EditText etnc;
    PoemSQLiteHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        etzh = (EditText) findViewById(R.id.zhanghao);
        etmm = (EditText) findViewById(R.id.mima);
        etnc = (EditText) findViewById(R.id.Name);
        dbHelper = new PoemSQLiteHelper(this,"Poem.db",null,1);

        Button butzc = (Button) findViewById(R.id.email_sign_in_button);
        butzc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etzh.getText().toString().length() == 0 ||
                    etmm.getText().toString().length() == 0 ||
                    etnc.getText().toString().length() == 0 ||
                    etzh.getText().toString().length() > 10 ||
                    etmm.getText().toString().length() > 20 ||
                    etnc.getText().toString().length() > 16 ){
                    Toast.makeText(SignActivity.this, "注册信息长度不符！", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase mydb = dbHelper.getWritableDatabase();
                    Cursor cursor = mydb.rawQuery("select * from USER WHERE Account = ? "
                            ,new String[] {etzh.getText().toString()});

                    if(cursor.getCount() != 0){
                        Toast.makeText(SignActivity.this,"账户名已存在！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ContentValues values = new ContentValues();
                        values.put("Account",etzh.getText().toString());
                        values.put("Password",etmm.getText().toString());
                        values.put("Name",etnc.getText().toString());
                        mydb.insert("USER",null,values);
                        values.clear();
                    }

                }
            }
        });
    }
}

