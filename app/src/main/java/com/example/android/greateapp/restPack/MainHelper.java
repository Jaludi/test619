package com.example.android.greateapp.restPack;

/**
 * Created by Android on 6/13/2017.
 */

public class MainHelper {
    public MainHelper() {
    }
    public  String nameFusion(String first, String last){
        StringBuilder builder = new StringBuilder();

        builder.append(first);
        builder.append(" ");
        builder.append(last);
        return builder.toString();

    }
}
