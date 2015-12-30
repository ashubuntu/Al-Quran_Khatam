package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    UserLocalStore userLocalStore;

    Spinner nextKhatamDatesDropDown;

    Button  para1, para2, para3, para4, para5,
            para6, para7, para8, para9, para10,
            para11, para12, para13, para14, para15,
            para16, para17, para18, para19, para20,
            para21, para22, para23, para24, para25,
            para26, para27, para28, para29, para30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(this);

        nextKhatamDatesDropDown = (Spinner) findViewById(R.id.nextKhatamDatesDropDown);

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

        downloadKhatamDates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.createNewDateButton:
                Intent intent = new Intent(this, AddNewKhatamDate.class);
                startActivityForResult(intent, 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String currentDate=data.getStringExtra("currentDate");
                userLocalStore.storeCurrentDate(currentDate);
                recreate();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                recreate();
                //if there's no result
            }
        }
    }

    private void downloadKhatamDates() {
        new DownloadKhatamDates(this).execute();
    }

    public void setStatus(Button paraBtn, String paraStatusKey) {
        String status = userLocalStore.readParaStatus(paraStatusKey).equals("0")? "1" : "0" ;
        userLocalStore.setParaStatus(paraStatusKey, status);
        paraBtn.setBackgroundColor(getColor(paraStatusKey));
    }

    public void flipStatus(View view) {
        switch (view.getId()) {
            case R.id.paraOneButton:
                setStatus(para1, "status_para_1");
                break;
            case R.id.paraTwoButton:
                setStatus(para2, "status_para_2");
                break;
            case R.id.paraThreeButton:
                setStatus(para3, "status_para_3");
                break;
            case R.id.paraFourButton:
                setStatus(para4, "status_para_4");
                break;
            case R.id.paraFiveButton:
                setStatus(para5, "status_para_5");
                break;
            case R.id.paraSixButton:
                setStatus(para6, "status_para_6");
                break;
            case R.id.paraSevenButton:
                setStatus(para7, "status_para_7");
                break;
            case R.id.paraEightButton:
                setStatus(para8, "status_para_8");
                break;
            case R.id.paraNineButton:
                setStatus(para9, "status_para_9");
                break;
            case R.id.paraTenButton:
                setStatus(para10, "status_para_10");
                break;
            case R.id.paraElevenButton:
                setStatus(para11, "status_para_11");
                break;
            case R.id.paraTwelveButton:
                setStatus(para12, "status_para_12");
                break;
            case R.id.paraThirteenButton:
                setStatus(para13, "status_para_13");
                break;
            case R.id.paraFourteenButton:
                setStatus(para14, "status_para_14");
                break;
            case R.id.paraFifteenButton:
                setStatus(para15, "status_para_15");
                break;
            case R.id.paraSixteenButton:
                setStatus(para16, "status_para_16");
                break;
            case R.id.paraSeventeenButton:
                setStatus(para17, "status_para_17");
                break;
            case R.id.paraEighteenButton:
                setStatus(para18, "status_para_18");
                break;
            case R.id.paraNineteenButton:
                setStatus(para19, "status_para_19");
                break;
            case R.id.paraTwentyButton:
                setStatus(para20, "status_para_20");
                break;
            case R.id.paraTwentyOneButton:
                setStatus(para21, "status_para_21");
                break;
            case R.id.paraTwentyTwoButton:
                setStatus(para22, "status_para_22");
                break;
            case R.id.paraTwentyThreeButton:
                setStatus(para23, "status_para_23");
                break;
            case R.id.paraTwentyFourButton:
                setStatus(para24, "status_para_24");
                break;
            case R.id.paraTwentyFiveButton:
                setStatus(para25, "status_para_25");
                break;
            case R.id.paraTwentySixButton:
                setStatus(para26, "status_para_26");
                break;
            case R.id.paraTwentySevenButton:
                setStatus(para27, "status_para_27");
                break;
            case R.id.paraTwentyEightButton:
                setStatus(para28, "status_para_28");
                break;
            case R.id.paraTwentyNineButton:
                setStatus(para29, "status_para_29");
                break;
            case R.id.paraThirtyButton:
                setStatus(para30, "status_para_30");
                break;
        }
    }

    public void createNewKhatamDate(View view) {
        Intent intent = new Intent(this, AddNewKhatamDate.class);
        startActivityForResult(intent, 1);
    }

    public void deleteKhatamDate(View view) {

    }

    public void endApplication(View view) {
        userLocalStore.clearUserData();
        finish();
    }

    public void updateKhatamStatus(View view) {
        userLocalStore.updateKhatamStatus();
        new UpdateKhatamStatus(this).execute(userLocalStore.readKhatamStatus());
        recreate();
    }

    public void getKhatamStatus(View view) {
        int currentDatePosition;
        switch (view.getId()) {
            case R.id.previousDateButton:
                currentDatePosition = userLocalStore.readCurrentDatePosition();
                if(currentDatePosition == 0)
                    Toast.makeText(this, "No previous date", Toast.LENGTH_SHORT).show();
                else {
                    currentDatePosition -- ;
                    userLocalStore.storeCurrentDate(nextKhatamDatesDropDown.getAdapter().
                            getItem(currentDatePosition).toString());
                    userLocalStore.storeCurrentDatePosition(currentDatePosition);
                    nextKhatamDatesDropDown.setSelection(userLocalStore.readCurrentDatePosition());
                    new DownloadKhatamStatus(this).execute(userLocalStore.readCurrentDate());
                }
                break;
            case R.id.nextDateButton:
                currentDatePosition = userLocalStore.readCurrentDatePosition();
                if(currentDatePosition == nextKhatamDatesDropDown.getAdapter().getCount() - 1)
                    Toast.makeText(this, "No next date", Toast.LENGTH_SHORT).show();
                else {
                    currentDatePosition ++ ;
                    userLocalStore.storeCurrentDate(nextKhatamDatesDropDown.getAdapter().
                            getItem(currentDatePosition).toString());
                    userLocalStore.storeCurrentDatePosition(currentDatePosition);
                    nextKhatamDatesDropDown.setSelection(userLocalStore.readCurrentDatePosition());
                    new DownloadKhatamStatus(this).execute(userLocalStore.readCurrentDate());
                }
                break;
        }
    }

    // utility methods follow
    public int getColor(String para) {
        return userLocalStore.readParaStatus(para).equals("0")?
                ContextCompat.getColor(this, R.color.parasButtonOFF):
                ContextCompat.getColor(this, R.color.parasButtonON);
    }
}
