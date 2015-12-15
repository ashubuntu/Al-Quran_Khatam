package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DownloadKhatamDates extends AsyncTask<Void, Void, Void> {
    Activity activity;
    UserLocalStore userLocalStore;

    Spinner nextKhatamDatesDropDown;

    protected List<String> khatamDates = new ArrayList<>();
    JSONObject jsonObject = new JSONObject();
    Iterator<String> keys;

    public DownloadKhatamDates(Activity activity) {
        this.activity = activity;
        userLocalStore = new UserLocalStore(activity);
        nextKhatamDatesDropDown = (Spinner) activity.findViewById(R.id.nextKhatamDatesDropDown);
    }

    @Override
    protected Void doInBackground(Void... params) {
        jsonObject = new ServerRequests().sendServerRequest("GET", "fetch_khatam_dates.php", null);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        long currentDatePosition = 0, i = 0;
        String date;
        try {
            keys = jsonObject.keys();
            while (keys.hasNext()){
                date = jsonObject.getString(keys.next());
                khatamDates.add(date);
                if(date.equals(userLocalStore.readCurrentDate()))
                    currentDatePosition = i;
                i++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity.getBaseContext(),
                android.R.layout.simple_spinner_dropdown_item, khatamDates);

        //Log.d("currentdate", userLocalStore.readCurrentDate());
        //Log.d("currentdate", String.valueOf(currentDatePosition));
        nextKhatamDatesDropDown.setAdapter(adapter);
        userLocalStore.storeCurrentDatePosition(currentDatePosition);
        nextKhatamDatesDropDown.setSelection(userLocalStore.readCurrentDatePosition());

        userLocalStore.storeCurrentDate(nextKhatamDatesDropDown.getSelectedItem().toString());
        //userLocalStore.storeCurrentDatePosition(nextKhatamDatesDropDown.getSelectedItemPosition());
        new DownloadKhatamStatus(activity).execute(userLocalStore.readCurrentDate());

        userLocalStore.storeDateCount(adapter.getCount());

        nextKhatamDatesDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userLocalStore.storeCurrentDate(nextKhatamDatesDropDown.getSelectedItem().toString());
                userLocalStore.storeCurrentDatePosition(nextKhatamDatesDropDown.getSelectedItemPosition());
                new DownloadKhatamStatus(activity).execute(userLocalStore.readCurrentDate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userLocalStore.storeKhatamDates(khatamDates);
        super.onPostExecute(aVoid);
    }
}
