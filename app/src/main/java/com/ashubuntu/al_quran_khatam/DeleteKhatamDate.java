package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DeleteKhatamDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_khatam_date);
    }

    public void deleteDate(View view) {
        Intent intent = new Intent(DeleteKhatamDate.this, MainActivity.class);
        setResult(Activity.RESULT_OK, intent);
        //startActivity(intent);
        finish();
    }
}
