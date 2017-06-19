package com.example.android.greateapp;

/**
 * Created by Android on 6/19/2017.
 */

public interface MainContract {
    interface Presenter {
        int retroCall();

    }
    interface View{
        void showErrorMessage();
        void showNetworkMessage();
        void updateTextview(String s);
    }
}
