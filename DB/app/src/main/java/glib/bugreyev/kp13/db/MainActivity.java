package glib.bugreyev.kp13.db;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CharSequence field = EmployeeDatabaseHelper.COLUMN_ID;
    EmployeeDatabaseHelper dbHelper = new EmployeeDatabaseHelper(this);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPopup();
        Button searchBtn = findViewById(R.id.search_btn);
        Cursor cursor = dbHelper.readAllData();
        cursor.moveToFirst();
        Log.d("test", cursor.getString(
                cursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_NAME)));


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                EditText edit = findViewById(R.id.edit_query);
                String query = edit.getText().toString();

                Cursor queryCursor = null;
                if (field.equals(EmployeeDatabaseHelper.COLUMN_EMPLOYEE_ID)) {
                    queryCursor = dbHelper.readByEmployeeId(query);
                } else if (field.equals(EmployeeDatabaseHelper.COLUMN_NAME)) {
                    queryCursor = dbHelper.readByName(query);
                } else if (field.equals(EmployeeDatabaseHelper.COLUMN_DEPARTMENT_NUMBER)) {
                    queryCursor = dbHelper.readByDepartmentNumber(query);
                } else if (field.equals(EmployeeDatabaseHelper.COLUMN_POSITION_CODE)) {
                    queryCursor = dbHelper.readByPositionCode(query);
                } else if (field.equals(EmployeeDatabaseHelper.COLUMN_SALARY)) {
                    queryCursor = dbHelper.readBySalary(query);
                } else {
                    queryCursor = dbHelper.readAllData();
                }
                String[] res = new String[queryCursor.getCount()];
                int i = 0;
                while (queryCursor.moveToNext()) {
                    res[i] = queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_ID)) + "; ";
                    res[i] += queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_EMPLOYEE_ID)) + "; ";
                    res[i] += queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_NAME)) + "; ";
                    res[i] += queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_DEPARTMENT_NUMBER)) + "; ";
                    res[i] += queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_POSITION_CODE)) + "; ";
                    res[i] += queryCursor.getString(
                            queryCursor.getColumnIndex(EmployeeDatabaseHelper.COLUMN_SALARY)) + "; ";
                    i++;
                }

                ArrayList<String> listData = new ArrayList<>(Arrays.asList(res));
                ListView listView = findViewById(R.id.list_items);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this, android.R.layout.simple_list_item_1, listData);
                listView.setAdapter(adapter);
            }
        });
    }

    private void createPopup() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.popup_text));
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                TextView popupText = findViewById(R.id.popup_text);
                CharSequence itemTitle = menuItem.getTitle();
                String newValue = "";
                if (itemTitle.equals("Department Number")) {
                    newValue = EmployeeDatabaseHelper.COLUMN_DEPARTMENT_NUMBER;
                } else if (itemTitle.equals("Employee id")) {
                    newValue = EmployeeDatabaseHelper.COLUMN_EMPLOYEE_ID;
                } else if (itemTitle.equals("Employee Name")) {
                    newValue = EmployeeDatabaseHelper.COLUMN_NAME;
                } else if (itemTitle.equals("Position Code")) {
                    newValue = EmployeeDatabaseHelper.COLUMN_POSITION_CODE;
                } else if (itemTitle.equals("Salary")) {
                    newValue = EmployeeDatabaseHelper.COLUMN_SALARY;
                }
                popupText.setText(itemTitle);
                field = newValue;
                return true;
            }
        });
        findViewById(R.id.popup_text).setOnClickListener(v -> {
            popup.show();
        });
    }

}