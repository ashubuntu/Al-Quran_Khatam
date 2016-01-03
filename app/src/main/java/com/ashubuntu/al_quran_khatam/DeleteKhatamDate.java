package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

public class DeleteKhatamDate extends AppCompatActivity {
    UserLocalStore userLocalStore;
    Spinner nextKhatamDatesDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_khatam_date);
        userLocalStore = new UserLocalStore(DeleteKhatamDate.this);

        nextKhatamDatesDropDown = (Spinner) findViewById(R.id.nextKhatamDatesDropDown);
        downloadKhatamDates();
    }

    private class DeleteDate extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            //Log.d("ChosenDate", params[0]);
            //Log.d("ChosenDate", userLocalStore.readCurrentDate());
            //Log.d("ChosenDate", params[1]);
            ContentValues dataToSend = new ContentValues();
            dataToSend.put("date", params[0]);
            new ServerRequests().sendServerRequest("POST", "delete_khatam_date.php", dataToSend);
            return Integer.valueOf(params[1]);
        }

        @Override
        protected void onPostExecute(Integer chosenDatePosition) {
            super.onPostExecute(chosenDatePosition);
            if(chosenDatePosition == nextKhatamDatesDropDown.getCount() - 1)
                chosenDatePosition --;
            String currentDate = nextKhatamDatesDropDown.getItemAtPosition(chosenDatePosition).toString();
            userLocalStore.storeCurrentDate(currentDate);
            Intent intent = new Intent(DeleteKhatamDate.this, MainActivity.class);
            intent.putExtra("currentDate", userLocalStore.readCurrentDate());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void deleteDate(View view) {
        int chosenDatePosition = nextKhatamDatesDropDown.getSelectedItemPosition();
        String chosenDate = nextKhatamDatesDropDown.getSelectedItem().toString();
        String[] params = {chosenDate, String.valueOf(chosenDatePosition)};
        new DeleteDate().execute(params);

    }

    private void downloadKhatamDates() {
        new DownloadKhatamDates(this).execute();
    }
}
