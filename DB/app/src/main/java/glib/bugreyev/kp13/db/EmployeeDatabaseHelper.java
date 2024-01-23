package glib.bugreyev.kp13.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    //final String CONTENT_URI = "content://glib.bugreyev.kp13.db.provider/employees";

    private Context context;
    public static final String DATABASE_NAME = "EmployeeDatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "employees";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DEPARTMENT_NUMBER = "department_number";
    public static final String COLUMN_EMPLOYEE_ID = "employee_id";
    public static final String COLUMN_NAME = "employee_name";
    public static final String COLUMN_POSITION_CODE = "position_code";
    public static final String COLUMN_SALARY = "salary";

    public EmployeeDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DEPARTMENT_NUMBER + " INTEGER, " +
                COLUMN_EMPLOYEE_ID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_POSITION_CODE + " TEXT, " +
                COLUMN_SALARY + " REAL);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Додаткові методи читання та запису даних можуть бути додані, якщо потрібно.

    // Метод для додавання нового працівника
    void addEmployee(int departmentNumber, int employeeId, String employeeName,
                     String positionCode, double salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DEPARTMENT_NUMBER, departmentNumber);
        cv.put(COLUMN_EMPLOYEE_ID, employeeId);
        cv.put(COLUMN_NAME, employeeName);
        cv.put(COLUMN_POSITION_CODE, positionCode);
        cv.put(COLUMN_SALARY, salary);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Метод для отримання всіх даних з таблиці
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readByName(String name) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME + "='" + name + "'";
        Log.d("testdb", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readByEmployeeId(String employee_id) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_EMPLOYEE_ID + "=" + employee_id;
        Log.d("testdb", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readByDepartmentNumber(String department_number) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_DEPARTMENT_NUMBER + "=" + department_number;
        Log.d("testdb", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readByPositionCode(String position_code) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_POSITION_CODE + "='" + position_code + "'";
        Log.d("testdb", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readBySalary(String salary) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_SALARY + "=" + salary;
        Log.d("testdb", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String employee_id, String name, String department_number,
                    String position_code, double salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMPLOYEE_ID, employee_id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DEPARTMENT_NUMBER, department_number);
        cv.put(COLUMN_POSITION_CODE, position_code);
        cv.put(COLUMN_SALARY, salary);

        long result = db.update(TABLE_NAME, cv, "_id=?",
                new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?",
                new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}