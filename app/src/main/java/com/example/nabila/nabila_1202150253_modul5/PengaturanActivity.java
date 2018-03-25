package com.example.nabila.nabila_1202150253_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PengaturanActivity extends AppCompatActivity {
    private TextView color;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        alert = new AlertDialog.Builder(this);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Preference", 0);
        shared = sharedPreferences.edit();
        colorid = sharedPreferences.getInt("Colourground", R.color.white);
        color = findViewById(R.id.tv_color);
        color.setText(getShapeColor(colorid));
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PengaturanActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed(); //menjalankan method onbackpressed
        }
        return true;
    }

    public String getShapeColor(int i) {
        if (i == R.color.red) {
            return "Red";
        } else if (i == R.color.green) {
            return "Green";
        } else if (i == R.color.blue) {
            return "Blue";
        }else {
            return "White";
        }
    }

    public int getColorid(int i) {
        if (i == R.color.red) {
            return R.id.bt_red;
        } else if (i == R.color.green) {
            return R.id.bt_green;
        } else if (i == R.color.blue) {
            return R.id.bt_blue;
        }else{
            return R.id.bt_white;
        }
    }

    public void Color (View view) {

        alert.setTitle("Shape Color"); //set title alert dialog menjadi Shape Color
        View view1 = getLayoutInflater().inflate(R.layout.activity_color, null); //membuat view baru
        alert.setView(view1); //menampilkan view yang telah dibuat

        //akses radiogroup apda layout
        final RadioGroup radG = view1.findViewById(R.id.radio_color);
        radG.check(getColorid(colorid));


        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//ketika klik Ok pada alert dialog
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.bt_red:
                        colorid = R.color.red;
                        break;
                    case R.id.bt_green:
                        colorid = R.color.green;
                        break;
                    case R.id.bt_blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.bt_white:
                        colorid = R.color.white;
                        break;
                }

                color.setText(getShapeColor(colorid)); //set shape color menjadi color id yang dipilih
                shared.putInt("Colourground", colorid);
                shared.commit();
            }
        });

        //ketika cancel diklik pada alertdialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.create().show();  //buat dan tampilkan alert dialog
    }
}
