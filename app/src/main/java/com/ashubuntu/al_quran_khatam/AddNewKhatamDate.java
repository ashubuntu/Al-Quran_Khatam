package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class AddNewKhatamDate extends FragmentActivity {
    UserLocalStore userLocalStore;
    static EditText chosenDateET;
    StringBuilder helper;
    Button  para1, para2, para3, para4, para5,
            para6, para7, para8, para9, para10,
            para11, para12, para13, para14, para15,
            para16, para17, para18, para19, para20,
            para21, para22, para23, para24, para25,
            para26, para27, para28, para29, para30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_khatam_date);

        userLocalStore = new UserLocalStore(AddNewKhatamDate.this);

        chosenDateET = (EditText) findViewById(R.id.chosenDateET);
        para1 = (Button) findViewById(R.id.paraOneButton);
        para2 = (Button) findViewById(R.id.paraTwoButton);
        para3 = (Button) findViewById(R.id.paraThreeButton);
        para4 = (Button) findViewById(R.id.paraFourButton);
        para5 = (Button) findViewById(R.id.paraFiveButton);
        para6 = (Button) findViewById(R.id.paraSixButton);
        para7 = (Button) findViewById(R.id.paraSevenButton);
        para8 = (Button) findViewById(R.id.paraEightButton);
        para9 = (Button) findViewById(R.id.paraNineButton);
        para10 = (Button) findViewById(R.id.paraTenButton);
        para11 = (Button) findViewById(R.id.paraElevenButton);
        para12 = (Button) findViewById(R.id.paraTwelveButton);
        para13 = (Button) findViewById(R.id.paraThirteenButton);
        para14 = (Button) findViewById(R.id.paraFourteenButton);
        para15 = (Button) findViewById(R.id.paraFifteenButton);
        para16 = (Button) findViewById(R.id.paraSixteenButton);
        para17 = (Button) findViewById(R.id.paraSeventeenButton);
        para18 = (Button) findViewById(R.id.paraEighteenButton);
        para19 = (Button) findViewById(R.id.paraNineteenButton);
        para20 = (Button) findViewById(R.id.paraTwentyButton);
        para21 = (Button) findViewById(R.id.paraTwentyOneButton);
        para22 = (Button) findViewById(R.id.paraTwentyTwoButton);
        para23 = (Button) findViewById(R.id.paraTwentyThreeButton);
        para24 = (Button) findViewById(R.id.paraTwentyFourButton);
        para25 = (Button) findViewById(R.id.paraTwentyFiveButton);
        para26 = (Button) findViewById(R.id.paraTwentySixButton);
        para27 = (Button) findViewById(R.id.paraTwentySevenButton);
        para28 = (Button) findViewById(R.id.paraTwentyEightButton);
        para29 = (Button) findViewById(R.id.paraTwentyNineButton);
        para30 = (Button) findViewById(R.id.paraThirtyButton);

        initKhatamStatus();
    }

    private void initKhatamStatus() {
        helper = new StringBuilder();
        for (int i = 0; i<30; i++)
            helper.append("0 ");
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static void setChosenDate(int year, int month, int day) {
        month ++;
        String formattedDay = day <= 9?"0" + day : "" + day;
        String formattedMonth = month <= 9?"0" + month : "" + month ;
        chosenDateET.setText(year + "-" + formattedMonth + "-" + formattedDay);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            setChosenDate(year, month, day);
        }
    }

    public void setNewKhatamDateStatus(int index, char status, Button paraBtn) {
        if(status == '0') {
            status = '1';
            helper.setCharAt(index, status);
            paraBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.parasButtonON));
        } else {
            status = '0';
            helper.setCharAt(index, status);
            paraBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.parasButtonOFF));
        }
    }

    public void flipStatus(View view) {
        switch (view.getId()) {
            case R.id.paraOneButton:
                setNewKhatamDateStatus(0, helper.charAt(0), para1);
                break;
            case R.id.paraTwoButton:
                setNewKhatamDateStatus(2, helper.charAt(2), para2);
                break;
            case R.id.paraThreeButton:
                setNewKhatamDateStatus(4, helper.charAt(4), para3);
                break;
            case R.id.paraFourButton:
                setNewKhatamDateStatus(6, helper.charAt(6), para4);
                break;
            case R.id.paraFiveButton:
                setNewKhatamDateStatus(8, helper.charAt(8), para5);
                break;
            case R.id.paraSixButton:
                setNewKhatamDateStatus(10, helper.charAt(10), para6);
                break;
            case R.id.paraSevenButton:
                setNewKhatamDateStatus(12, helper.charAt(12), para7);
                break;
            case R.id.paraEightButton:
                setNewKhatamDateStatus(14, helper.charAt(14), para8);
                break;
            case R.id.paraNineButton:
                setNewKhatamDateStatus(16, helper.charAt(16), para9);
                break;
            case R.id.paraTenButton:
                setNewKhatamDateStatus(18, helper.charAt(18), para10);
                break;
            case R.id.paraElevenButton:
                setNewKhatamDateStatus(20, helper.charAt(20), para11);
                break;
            case R.id.paraTwelveButton:
                setNewKhatamDateStatus(22, helper.charAt(22), para12);
                break;
            case R.id.paraThirteenButton:
                setNewKhatamDateStatus(24, helper.charAt(24), para13);
                break;
            case R.id.paraFourteenButton:
                setNewKhatamDateStatus(26, helper.charAt(26), para14);
                break;
            case R.id.paraFifteenButton:
                setNewKhatamDateStatus(28, helper.charAt(28), para15);
                break;
            case R.id.paraSixteenButton:
                setNewKhatamDateStatus(30, helper.charAt(30), para16);
                break;
            case R.id.paraSeventeenButton:
                setNewKhatamDateStatus(32, helper.charAt(32), para17);
                break;
            case R.id.paraEighteenButton:
                setNewKhatamDateStatus(34, helper.charAt(34), para18);
                break;
            case R.id.paraNineteenButton:
                setNewKhatamDateStatus(36, helper.charAt(36), para19);
                break;
            case R.id.paraTwentyButton:
                setNewKhatamDateStatus(38, helper.charAt(38), para20);
                break;
            case R.id.paraTwentyOneButton:
                setNewKhatamDateStatus(40, helper.charAt(40), para21);
                break;
            case R.id.paraTwentyTwoButton:
                setNewKhatamDateStatus(42, helper.charAt(42), para22);
                break;
            case R.id.paraTwentyThreeButton:
                setNewKhatamDateStatus(44, helper.charAt(44), para23);
                break;
            case R.id.paraTwentyFourButton:
                setNewKhatamDateStatus(46, helper.charAt(46), para24);
                break;
            case R.id.paraTwentyFiveButton:
                setNewKhatamDateStatus(48, helper.charAt(48), para25);
                break;
            case R.id.paraTwentySixButton:
                setNewKhatamDateStatus(50, helper.charAt(50), para26);
                break;
            case R.id.paraTwentySevenButton:
                setNewKhatamDateStatus(52, helper.charAt(52), para27);
                break;
            case R.id.paraTwentyEightButton:
                setNewKhatamDateStatus(54, helper.charAt(54), para28);
                break;
            case R.id.paraTwentyNineButton:
                setNewKhatamDateStatus(56, helper.charAt(56), para29);
                break;
            case R.id.paraThirtyButton:
                setNewKhatamDateStatus(58, helper.charAt(58), para30);
                break;
        }
    }

    String getChosenDate() {
        return chosenDateET.getText().toString();
    }

    public class CreateNewDate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ContentValues dataToSend = new ContentValues();
            dataToSend.put("date", getChosenDate());
            dataToSend.put("khatamStatus", helper.toString());
            new ServerRequests().sendServerRequest("POST", "insert_new_khatam_date.php", dataToSend);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userLocalStore.storeCurrentDate(getChosenDate());
            //Log.d("currentdate", userLocalStore.readCurrentDate());
            Intent intent = new Intent(AddNewKhatamDate.this, MainActivity.class);
            intent.putExtra("currentDate", userLocalStore.readCurrentDate());
            setResult(Activity.RESULT_OK, intent);
            //startActivity(intent);
            finish();
        }
    }

    public void createNewDate(View view) {
        new CreateNewDate().execute();
    }
}
