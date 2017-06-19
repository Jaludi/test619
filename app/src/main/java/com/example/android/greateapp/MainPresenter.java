package com.example.android.greateapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.greateapp.DB.ContactsContract;
import com.example.android.greateapp.restPack.Contact;
import com.example.android.greateapp.restPack.MainHelper;
import com.example.android.greateapp.restPack.RandomAPI;
import com.example.android.greateapp.restPack.Result;
import com.example.android.greateapp.restPack.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android on 6/19/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    RetrofitService service;
    Context context;
    MainHelper helper;
    int count;
    //private static String CONTACT_NAME = "Family";

    public MainPresenter(MainContract.View view, RetrofitService service,Context context) {
        this.view = view;
        this.service = service;
        this.context = context;
        helper = new MainHelper();

        count = 0;
    }

    @Override
    public int retroCall() {
        Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
                if (response.isSuccessful()) {
                    RandomAPI randomAPI = response.body();
                    for (Result result : randomAPI.getResults()) {


                        String fullName = helper.nameFusion(result.getName().getFirst(), result.getName().getLast());

                        String location = result.getLocation().getStreet() + ", " + result.getLocation().getCity()
                                + ", " + result.getLocation().getState() + ", " + result.getLocation().getPostcode();
                        Contact user = new Contact(location, fullName, result.getGender(), result.getEmail(), result.getLogin().toString(), result.getDob(), result.getRegistered(), result.getPhone(), result.getCell(), result.getId().toString(), result.getNat(), result.getPicture().getLarge());
                        addToDataBase(user);
                        count++;
                        view.updateTextview(Integer.toString(count));

                    }


//                    Log.d(TAG, "onResponse: arrayCount = " + contactList.size());
                }
                else {
                    //server application error
                    view.showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {
                view.showNetworkMessage();
            }
        });
    return count++;
    }


    private void addToDataBase(Contact user) {
        ContentValues contactContentValues = getContactContentValues(user);
        Uri contactInsertUri = context.getContentResolver().insert(ContactsContract.contactEntry.CONTENT_URI, contactContentValues);
        long genreRowId = ContentUris.parseId(contactInsertUri);
    }
    private ContentValues getContactContentValues(Contact user) {
        ContentValues value = new ContentValues();
        value.put(ContactsContract.contactEntry.COLUMN_NAME, user.getName());
        value.put(ContactsContract.contactEntry.COLUMN_ADDRESS, user.getLocation());
        value.put(ContactsContract.contactEntry.COLUMN_EMAIL, user.getEmail());
        value.put(ContactsContract.contactEntry.COLUMN_IMG, user.getImageURL());
        return value;
    }
}
