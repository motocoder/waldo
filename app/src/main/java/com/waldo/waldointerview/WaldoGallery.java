package com.waldo.waldointerview;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.waldo.waldointerview.data.Album;
import com.waldo.waldointerview.network.WaldoNetworkAPI;
import com.waldo.waldointerview.network.WaldoNetworkException;

public class WaldoGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //show cache type
        Picasso.get().setIndicatorsEnabled(true);
        
        setContentView(R.layout.activity_waldo_gallery);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        new LoadDataTask(this, recyclerView).execute();

    }

    private static class LoadDataTask extends AsyncTask<Void, Integer, Album> {

        private final Activity activity;
        private final RecyclerView recyclerView;

        private LoadDataTask(
            final Activity activity,
            final RecyclerView recyclerView
        ) {

            this.activity = activity;
            this.recyclerView = recyclerView;

        }

        @Override
        protected Album doInBackground(Void... voids) {

            final WaldoNetworkAPI api = new WaldoNetworkAPI();

            try {
                final Album album = api.getImageData(
                        "__dev.waldo.auth__=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50X2lkIjoiZjdkMWI5ZWYtYWE0Yi00YjY3LWFhZjQtYzYzZmE1MDYzOWM0Iiwicm9sZXMiOlsiY29uc3VtZXIiXSwiaXNzIjoid2FsZG86Y29yZSIsImdyYW50cyI6WyJhbGJ1bXM6dmlldzpwdWJsaWMiLCJhbGJ1bXM6ZWRpdDpvd25lZCIsImFsYnVtczpjcmVhdGU6cHJpdmF0ZSIsImFsYnVtczp2aWV3OmpvaW5lZCIsImFsYnVtczpkZWxldGU6b3duZWQiLCJhbGJ1bXM6Y3JlYXRlOnB1YmxpYyIsImFsYnVtczpjcmVhdGU6b3duZWQiLCJhbGJ1bXM6dmlldzpvd25lZCJdLCJleHAiOjE1MzkwNDQ1MzksImlhdCI6MTUzNjQ1MjUzOX0.xTQhv0QCkk4FqAdnmIFeezISw2TyKEleRKTJ1bNOtos"
                );

                return album;

            } catch (WaldoNetworkException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Album album) {
            super.onPostExecute(album);

            if(album == null) {
                Toast.makeText(activity, "Couldn't get data",  Toast.LENGTH_LONG).show();
            }
            else {

                final GalleryAdapter adapter = new GalleryAdapter(activity.getApplicationContext(), album.getRecords());
                recyclerView.setAdapter(adapter);

            }

        }
    }
}
