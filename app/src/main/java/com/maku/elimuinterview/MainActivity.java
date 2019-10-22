package com.maku.elimuinterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.maku.elimuinterview.Network.ApiClient;
import com.maku.elimuinterview.adapters.RepoListInfoAdapter;
import com.maku.elimuinterview.interfaces.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    //widgets
    @BindView(R.id.moviewRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.profileImage) ImageView mImageView;
    @BindView(R.id.profileName) TextView mTextViewName;

    //vars
    private LinearLayoutManager mLinearLayoutManager;
    private RepoListInfoAdapter mProfileInfoAdapter;
    ArrayList<com.maku.githubapitestapp.models.MyRepoList> mMyRepoLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //butterknife
        ButterKnife.bind(this);

        mMyRepoLists = new ArrayList<>();

        //methods
        initRecyclerview();
        getProfileInfo();
        getRepoList();
    }

    /*initialise recyclerview*/
    private void initRecyclerview() {
        Log.d(TAG, "initRecyclerview: ");
        mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    /*get repository list*/
    private void getRepoList() {

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> call = mApiInterface.getRepoList();

        call.enqueue(new Callback<ArrayList<com.maku.githubapitestapp.models.MyRepoList>>() {
            @Override
            public void onResponse(Call<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> call, Response<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> response) {

                if (response.isSuccessful())  {
                    Log.d(TAG, "onResponse: " + response.toString());
                    processList(response);
                }else{
                    Log.d(TAG, "onResponse: there is no response");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }

    /*add list to recyclerview*/
    private void processList(Response<ArrayList<com.maku.githubapitestapp.models.MyRepoList>> response) {

        mMyRepoLists =  response.body();

        Log.d(TAG, "process: " );

        mProfileInfoAdapter = new RepoListInfoAdapter(this, mMyRepoLists);
        mRecyclerView.setAdapter(mProfileInfoAdapter);

    }


    /*get profile info*/
    private void getProfileInfo() {

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<com.maku.githubapitestapp.models.Profile> call = mApiInterface.getProfileInfo();

        call.enqueue(new Callback<com.maku.githubapitestapp.models.Profile>() {
            @Override
            public void onResponse(Call<com.maku.githubapitestapp.models.Profile> call, Response<com.maku.githubapitestapp.models.Profile> response) {

                if (response.isSuccessful())  {
                    Log.d(TAG, "onResponse: " + response.toString());
                    process(response);
                }else{
                    Log.d(TAG, "onResponse: there is no response");
                }
            }

            @Override
            public void onFailure(Call<com.maku.githubapitestapp.models.Profile> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });

    }

    /*add profile info to wigets*/
    private void process(Response<com.maku.githubapitestapp.models.Profile> response) {

        String name = (String) response.body().getName();
        String img = (String) response.body().getAvatarUrl();

        /*attach profile to widgets*/
        mTextViewName.setText(name);
        Picasso.get().load(img).into(mImageView);

    }

}
