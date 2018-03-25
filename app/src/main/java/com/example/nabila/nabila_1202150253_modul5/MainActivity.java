package com.example.nabila.nabila_1202150253_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseTodoList db;
    private RecyclerView recyclerView;
    private AdapterList adapter;
    private ArrayList<ListData> list_data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        list_data = new ArrayList<>();
        db = new DatabaseTodoList(this);
        db.readdata(list_data);

        SharedPreferences shared = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = shared.getInt("Colourground", R.color.white);

        adapter = new AdapterList(this, list_data, color);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        deleteData();
    }

    private void deleteData() {
        ItemTouchHelper.SimpleCallback touch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ListData current = adapter.getData(position);

                if (direction == ItemTouchHelper.LEFT){
                    if (db.remove(current.getTodo())){
                        adapter.deleteData(position);
                        Snackbar.make(findViewById(R.id.relative), "List sudah dihapus",2000).show();
                    }
            }
        }
    };

        ItemTouchHelper touchHelper = new ItemTouchHelper(touch);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, PengaturanActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void AddList (View view){
        Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
        startActivity(intent);
    }

}
