package glib.bugreyev.kp13.dbclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DEPARTMENT_NUMBER = "department_number";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_NAME = "employee_name";
    public static final String COLUMN_POSITION_CODE = "position_code";
    public static final String COLUMN_SALARY = "salary";

    static final String PROVIDER_NAME = "glib.bugreyev.kp13.db.provider";
    static final String URL = "content://" + PROVIDER_NAME + "/employees";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver().query(CONTENT_URI,
                null, null,
                null, null);
        Button insertBtn = findViewById(R.id.insert_btn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        Button deleteBtn = findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    protected void updateData() {
        ContentValues values = new ContentValues();
        EditText edit1 = findViewById(R.id.edit_query1);
        EditText edit2 = findViewById(R.id.edit_query2);
        EditText edit3 = findViewById(R.id.edit_query3);
        EditText edit4 = findViewById(R.id.edit_query4);
        EditText edit5 = findViewById(R.id.edit_query5);
        EditText edit6 = findViewById(R.id.edit_query6);


        values.put(COLUMN_DEPARTMENT_NUMBER, edit1.getText().toString());
        values.put(COLUMN_EMPLOYEE_ID, edit2.getText().toString());
        values.put(COLUMN_NAME, edit3.getText().toString());
        values.put(COLUMN_POSITION_CODE, edit4.getText().toString());
        values.put(COLUMN_SALARY, edit5.getText().toString());


        getContentResolver().update(CONTENT_URI, values,
                edit6.getText().toString(), null);
    }

    protected void deleteData() {
        EditText edit6 = findViewById(R.id.edit_query6);
        getContentResolver().delete(CONTENT_URI,
                edit6.getText().toString(), null);
    }

    protected void insertData() {
        ContentValues values = new ContentValues();

        EditText edit1 = findViewById(R.id.edit_query1);
        EditText edit2 = findViewById(R.id.edit_query2);
        EditText edit3 = findViewById(R.id.edit_query3);
        EditText edit4 = findViewById(R.id.edit_query4);
        EditText edit5 = findViewById(R.id.edit_query5);

        values.put(COLUMN_DEPARTMENT_NUMBER, edit1.getText().toString());
        values.put(COLUMN_EMPLOYEE_ID, edit2.getText().toString());
        values.put(COLUMN_NAME, edit3.getText().toString());
        values.put(COLUMN_POSITION_CODE, edit4.getText().toString());
        values.put(COLUMN_SALARY, edit5.getText().toString());

        getContentResolver().insert(CONTENT_URI, values);
    }

}