package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DownloadKhatamStatus extends AsyncTask<String, Void, Void> {
    Activity activity;
    UserLocalStore userLocalStore;
    private ContentValues dataToSend;
    private List<String> khatamStatus;

    Button  para1, para2, para3, para4, para5,
            para6, para7, para8, para9, para10,
            para11, para12, para13, para14, para15,
            para16, para17, para18, para19, para20,
            para21, para22, para23, para24, para25,
            para26, para27, para28, para29, para30;

    TextView statusMessage;

    JSONObject jsonObject = new JSONObject();

    public DownloadKhatamStatus(Activity activity) {
        this.activity = activity;
        userLocalStore = new UserLocalStore(activity);
        dataToSend = new ContentValues();
        khatamStatus = new ArrayList<>();

        para1 = (Button) activity.findViewById(R.id.paraOneButton);
        para2 = (Button) activity.findViewById(R.id.paraTwoButton);
        para3 = (Button) activity.findViewById(R.id.paraThreeButton);
        para4 = (Button) activity.findViewById(R.id.paraFourButton);
        para5 = (Button) activity.findViewById(R.id.paraFiveButton);
        para6 = (Button) activity.findViewById(R.id.paraSixButton);
        para7 = (Button) activity.findViewById(R.id.paraSevenButton);
        para8 = (Button) activity.findViewById(R.id.paraEightButton);
        para9 = (Button) activity.findViewById(R.id.paraNineButton);
        para10 = (Button) activity.findViewById(R.id.paraTenButton);
        para11 = (Button) activity.findViewById(R.id.paraElevenButton);
        para12 = (Button) activity.findViewById(R.id.paraTwelveButton);
        para13 = (Button) activity.findViewById(R.id.paraThirteenButton);
        para14 = (Button) activity.findViewById(R.id.paraFourteenButton);
        para15 = (Button) activity.findViewById(R.id.paraFifteenButton);
        para16 = (Button) activity.findViewById(R.id.paraSixteenButton);
        para17 = (Button) activity.findViewById(R.id.paraSeventeenButton);
        para18 = (Button) activity.findViewById(R.id.paraEighteenButton);
        para19 = (Button) activity.findViewById(R.id.paraNineteenButton);
        para20 = (Button) activity.findViewById(R.id.paraTwentyButton);
        para21 = (Button) activity.findViewById(R.id.paraTwentyOneButton);
        para22 = (Button) activity.findViewById(R.id.paraTwentyTwoButton);
        para23 = (Button) activity.findViewById(R.id.paraTwentyThreeButton);
        para24 = (Button) activity.findViewById(R.id.paraTwentyFourButton);
        para25 = (Button) activity.findViewById(R.id.paraTwentyFiveButton);
        para26 = (Button) activity.findViewById(R.id.paraTwentySixButton);
        para27 = (Button) activity.findViewById(R.id.paraTwentySevenButton);
        para28 = (Button) activity.findViewById(R.id.paraTwentyEightButton);
        para29 = (Button) activity.findViewById(R.id.paraTwentyNineButton);
        para30 = (Button) activity.findViewById(R.id.paraThirtyButton);

        statusMessage = (TextView) activity.findViewById(R.id.statusMessage);
    }

    @Override
    protected Void doInBackground(String... params) {
        dataToSend.put("date", params[0]);
        jsonObject = new ServerRequests().sendServerRequest("POST", "fetch_khatam_status.php", dataToSend);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Iterator<String> keys;
        String status;
        int parasCompleted = 0;

        try {
            keys = jsonObject.keys();
            while (keys.hasNext()){
                status = jsonObject.getString(keys.next());
                if (status.equals("1"))
                    parasCompleted ++;
                khatamStatus.add(status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userLocalStore.storeKhatamStatus(khatamStatus);
        userLocalStore.storeParasCompleted(parasCompleted);
        loadKhatamStatus();
        statusMessage.setText("Total " + userLocalStore.readParasCompleted() + " para(s) completed.");
        super.onPostExecute(aVoid);
    }

    private void loadKhatamStatus() {
        para1.setBackgroundColor(getColor("status_para_1"));
        para2.setBackgroundColor(getColor("status_para_2"));
        para3.setBackgroundColor(getColor("status_para_3"));
        para4.setBackgroundColor(getColor("status_para_4"));
        para5.setBackgroundColor(getColor("status_para_5"));
        para6.setBackgroundColor(getColor("status_para_6"));
        para7.setBackgroundColor(getColor("status_para_7"));
        para8.setBackgroundColor(getColor("status_para_8"));
        para9.setBackgroundColor(getColor("status_para_9"));
        para10.setBackgroundColor(getColor("status_para_10"));
        para11.setBackgroundColor(getColor("status_para_11"));
        para12.setBackgroundColor(getColor("status_para_12"));
        para13.setBackgroundColor(getColor("status_para_13"));
        para14.setBackgroundColor(getColor("status_para_14"));
        para15.setBackgroundColor(getColor("status_para_15"));
        para16.setBackgroundColor(getColor("status_para_16"));
        para17.setBackgroundColor(getColor("status_para_17"));
        para18.setBackgroundColor(getColor("status_para_18"));
        para19.setBackgroundColor(getColor("status_para_19"));
        para20.setBackgroundColor(getColor("status_para_20"));
        para21.setBackgroundColor(getColor("status_para_21"));
        para22.setBackgroundColor(getColor("status_para_22"));
        para23.setBackgroundColor(getColor("status_para_23"));
        para24.setBackgroundColor(getColor("status_para_24"));
        para25.setBackgroundColor(getColor("status_para_25"));
        para26.setBackgroundColor(getColor("status_para_26"));
        para27.setBackgroundColor(getColor("status_para_27"));
        para28.setBackgroundColor(getColor("status_para_28"));
        para29.setBackgroundColor(getColor("status_para_29"));
        para30.setBackgroundColor(getColor("status_para_30"));
    }

    // utility methods follow
    public int getColor(String para) {
        return userLocalStore.readParaStatus(para).equals("0")?
                ContextCompat.getColor(activity, R.color.parasButtonOFF):
                ContextCompat.getColor(activity, R.color.parasButtonON);

    }
}
