package com.example.android.greateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.greateapp.restPack.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {
    Button addBT;
    MainPresenter presenter;
    TextView countTV;

    private static final String RETROFIT_URL = "https://randomuser.me/";
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        presenter = new MainPresenter(this,service,this);
        addBT = (Button)findViewById(R.id.addBTN);
        addBT.setOnClickListener(this);
        countTV = (TextView)findViewById(R.id.countTV);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBTN:
                presenter.retroCall();
                break;
            default:
                return;
        }
    }



    @Override
    public void showErrorMessage() {
        Log.d(TAG, "showErrorMessage: errror!!");
    }

    @Override
    public void showNetworkMessage() {
        Log.d(TAG, "showNetworkMessage:  network failed!!");
    }

    @Override
    public void updateTextview(String s) {
        countTV.setText(s);
    }
}
