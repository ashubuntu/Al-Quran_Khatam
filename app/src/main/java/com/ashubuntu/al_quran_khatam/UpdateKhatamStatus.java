package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.widget.Spinner;

import org.json.JSONObject;

public class UpdateKhatamStatus extends AsyncTask<String, Void, Void> {
    Activity activity;
    UserLocalStore userLocalStore;
    Spinner nextKhatamDatesDropDown;

    public UpdateKhatamStatus(Activity activity) {
        this.activity = activity;
        userLocalStore = new UserLocalStore(activity);
        nextKhatamDatesDropDown = (Spinner) activity.findViewById(R.id.nextKhatamDatesDropDown);
    }

    public String getSelectedDate() {
        return nextKhatamDatesDropDown.getSelectedItem().toString();
    }

    @Override
    protected Void doInBackground(String... params) {
        ContentValues dataToSend = new ContentValues();
        dataToSend.put("date", getSelectedDate());
        dataToSend.put("khatamStatus", params[0]);
        new ServerRequests().sendServerRequest("POST", "update_khatam_status.php", dataToSend);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
