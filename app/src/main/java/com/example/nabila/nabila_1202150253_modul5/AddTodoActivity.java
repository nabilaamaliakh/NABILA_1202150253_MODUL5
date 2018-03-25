package com.example.nabila.nabila_1202150253_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodoActivity extends AppCompatActivity {
    private EditText todo, des, prio;
    private DatabaseTodoList db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);

        todo = (EditText) findViewById(R.id.et_name);
        des = (EditText) findViewById(R.id.et_descript);
        prio = (EditText) findViewById(R.id.et_priority);
        db = new DatabaseTodoList(this);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AddTodoActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void AddTodo (View view){
        if (db.inputdata(new ListData(todo.getText().toString(), des.getText().toString(), prio.getText().toString()))){

            Toast.makeText(this, "To Do List sudah ditambahkan!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddTodoActivity.this, MainActivity.class));
            this.finish();

        }else {
            Toast.makeText(this, "List tidak bisa kosong", Toast.LENGTH_SHORT).show();
            todo.setText(null);
            des.setText(null);
            prio.setText(null);
        }
    }
}