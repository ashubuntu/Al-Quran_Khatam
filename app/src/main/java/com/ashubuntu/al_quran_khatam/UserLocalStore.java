package com.ashubuntu.al_quran_khatam;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.List;


public class UserLocalStore {
    public static final String SP_NAME = "Khatams";
    public static final String KHATAM_DATE = "khatam_date_";
    public static final String PARA_STATUS = "status_para_";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Activity activity) {
        userLocalDatabase = activity.getSharedPreferences(SP_NAME, 0);
    }

    public void storeKhatamDates(List<String> khatamDates) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        Iterator<String> khatamDatesIterator = khatamDates.iterator();
        int i = 1;
        while (khatamDatesIterator.hasNext()) {
            editor.putString(KHATAM_DATE + i, khatamDatesIterator.next());
            i++ ;
        }
        editor.apply();
    }

    public void storeKhatamStatus(List<String> khatamStatus) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        Iterator<String> khatamStatusIterator = khatamStatus.iterator();
        int i = 1;
        while (khatamStatusIterator.hasNext()) {
            editor.putString(PARA_STATUS + i, khatamStatusIterator.next());
            i++ ;
        }
        editor.apply();
    }

    public void updateKhatamStatus() {
        StringBuilder khatamStatusBuilder = new StringBuilder();
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        int i = 0;
        while (++ i < 31) {
            khatamStatusBuilder.append(userLocalDatabase.getString(PARA_STATUS + i, "0"));
            khatamStatusBuilder.append(" ");
        }
        editor.putString("khatamStatus", String.valueOf(khatamStatusBuilder));
        editor.apply();
    }

    public String readKhatamStatus() {
        return userLocalDatabase.getString("khatamStatus", "");
    }

    public String readParaStatus(String para) {
        return userLocalDatabase.getString(para,"0");
    }

    public void setParaStatus(String para, String status) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putString(para, status);
        editor.apply();
    }

    public void clearUserData() {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.clear();
        editor.apply();
    }

    public String readKhatamDate(String date) {
        return userLocalDatabase.getString(date,"");
    }

    public void storeCurrentDate(String currentDate) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putString("currentDate", currentDate);
        editor.apply();
    }

    public String readCurrentDate(){
        return userLocalDatabase.getString("currentDate","");
    }

    public void storeCurrentDatePosition(long currentDatePosition) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putString("currentDatePosition", String.valueOf(currentDatePosition));
        editor.apply();
    }

    public int readCurrentDatePosition() {
        return Integer.parseInt(userLocalDatabase.getString("currentDatePosition", "0"));
    }

    public void storeDateCount(int dateCount) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putString("dateCount", String.valueOf(dateCount));
        editor.apply();
    }

    public int readDateCount() {
        return Integer.parseInt(userLocalDatabase.getString("dateCount", "0"));
    }

    public void storeParasCompleted(int parasCompleted) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putInt("parasCompleted", parasCompleted);
        editor.apply();
    }

    public int readParasCompleted(){
        return userLocalDatabase.getInt("parasCompleted", 0);
    }
}
