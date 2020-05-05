package com.example.projectmovie.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectmovie.R;
import com.example.projectmovie.database.AppDatabase;
import com.example.projectmovie.database.FavoritModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailMovie extends AppCompatActivity {

    ImageView ivThumb, ivBackdrop;
    TextView tvRating, tvCount, tvJudul, tvDesc;
    FrameLayout favorit;
    String thumb,backdrop,title,desc;
    int id;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivThumb = findViewById(R.id.detail_iv_thumb);
        ivBackdrop = findViewById(R.id.detail_iv_backdrop);
        tvRating = findViewById(R.id.detail_tv_rate);
        tvCount = findViewById(R.id.detail_tv_vote);
        tvJudul = findViewById(R.id.detail_tv_judul);
        tvDesc = findViewById(R.id.detail_tv_desc);
        favorit = findViewById(R.id.detail_favorit);

        simpan();
        getIncomingIntent();
}

    private void getIncomingIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int getId = bundle.getInt("id");
            String getBackdrop = bundle.getString("backdrop");
            String getThumb = bundle.getString("thumb");
            double getRating = bundle.getDouble("rating");
            int getVote = bundle.getInt("count");
            String getTitle = bundle.getString("judul");
            String getDetail = bundle.getString("desc");

            try {
                URL urlBack = new URL(getBackdrop);
                URL urlThumb = new URL(getThumb);
                new DownloadImageTask().execute(urlBack, urlThumb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            id = getId;
            thumb = getThumb;
            backdrop = getBackdrop;
            title = getTitle;
            desc = getDetail;

            tvRating.setText(String.valueOf(getRating));
            tvCount.setText(String.valueOf(getVote));
            tvJudul.setText(getTitle);
            tvDesc.setText(getDetail);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadImageTask extends AsyncTask<URL, Integer, Bitmap[]> {
        protected Bitmap[] doInBackground(URL... urls) {
            Bitmap[] bmp = new Bitmap[2];
            try {
                //backdrop
                bmp[0] = BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());
                //thumb
                bmp[1] = BitmapFactory.decodeStream(urls[1].openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap[] result) {
            ivBackdrop.setImageBitmap(result[0]);
            ivThumb.setImageBitmap(result[1]);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, com.example.projectmovie.view.activity.MainActivity.class);
                startActivity(intent);
                finish();
                DetailMovie.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        DetailMovie.this.finish();
    }

    //tombol favorit
    private void simpan(){
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    FavoritModel favoritModel = new FavoritModel();
                    favoritModel.setId(id);
                    favoritModel.setBackdrop(backdrop);
                    favoritModel.setThum(thumb);
                    favoritModel.setTitle(title);
                    favoritModel.setDesc(desc);

                    appDatabase.favoritDAO().insertFavorit(favoritModel);

                    Log.d("DetailMovie","Sukses menambahkan Favorit");
                    Toast.makeText(getApplicationContext(), "Sukses menambah Favorit", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("DetailMovie","Gagal menambahkan Favorit, err : "+ex.getMessage());
                    Toast.makeText(getApplicationContext(), "Gagal menambah Favorit", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
