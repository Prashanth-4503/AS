package com.example.ex6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText Rollno, Name, Marks;
    Button Insert, Delete, Update, View, ViewAll;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rollno = findViewById(R.id.Rollno);
        Name = findViewById(R.id.Name);
        Marks = findViewById(R.id.Marks);
        Insert = findViewById(R.id.Insert);
        Delete = findViewById(R.id.Delete);
        Update = findViewById(R.id.Update);
        View = findViewById(R.id.View);
        ViewAll = findViewById(R.id.ViewAll);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        // Creating database and table
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR, name VARCHAR, marks VARCHAR);");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Insert) {
            insertRecord();
        } else if (view.getId() == R.id.Delete) {
            deleteRecord();
        } else if (view.getId() == R.id.Update) {
            updateRecord();
        } else if (view.getId() == R.id.View) {
            viewRecord();
        } else if (view.getId() == R.id.ViewAll) {
            viewAllRecords();
        } else {
            throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void insertRecord() {
        if (Rollno.getText().toString().trim().isEmpty() ||
                Name.getText().toString().trim().isEmpty() ||
                Marks.getText().toString().trim().isEmpty()) {
            showMessage("Error", "Please enter all values");
            return;
        }

        db.execSQL("INSERT INTO student (rollno, name, marks) VALUES (?, ?, ?);",
                new Object[]{Rollno.getText().toString(), Name.getText().toString(), Marks.getText().toString()});
        showMessage("Success", "Record added");
        clearText();
    }

    private void deleteRecord() {
        if (Rollno.getText().toString().trim().isEmpty()) {
            showMessage("Error", "Please enter Rollno");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = ?", new String[]{Rollno.getText().toString()});
        if (c.moveToFirst()) {
            db.execSQL("DELETE FROM student WHERE rollno = ?", new String[]{Rollno.getText().toString()});
            showMessage("Success", "Record Deleted");
        } else {
            showMessage("Error", "Invalid Rollno");
        }
        c.close();
        clearText();
    }

    private void updateRecord() {
        if (Rollno.getText().toString().trim().isEmpty()) {
            showMessage("Error", "Please enter Rollno");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = ?", new String[]{Rollno.getText().toString()});
        if (c.moveToFirst()) {
            db.execSQL("UPDATE student SET name = ?, marks = ? WHERE rollno = ?;",
                    new Object[]{Name.getText().toString(), Marks.getText().toString(), Rollno.getText().toString()});
            showMessage("Success", "Record Modified");
        } else {
            showMessage("Error", "Invalid Rollno");
        }
        c.close();
        clearText();
    }

    private void viewRecord() {
        if (Rollno.getText().toString().trim().isEmpty()) {
            showMessage("Error", "Please enter Rollno");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno = ?", new String[]{Rollno.getText().toString()});
        if (c.moveToFirst()) {
            Name.setText(c.getString(1));
            Marks.setText(c.getString(2));
        } else {
            showMessage("Error", "Invalid Rollno");
            clearText();
        }
        c.close();
    }

    private void viewAllRecords() {
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("Rollno: ").append(c.getString(0)).append("\n");
            buffer.append("Name: ").append(c.getString(1)).append("\n");
            buffer.append("Marks: ").append(c.getString(2)).append("\n\n");
        }
        showMessage("Student Details", buffer.toString());
        c.close();
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void clearText() {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Rollno.requestFocus();
    }
}
