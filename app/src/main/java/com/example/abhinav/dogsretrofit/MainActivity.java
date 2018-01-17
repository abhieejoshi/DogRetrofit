package com.example.abhinav.dogsretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private  ApiInterface apiInterface;
    Spinner catagorySpinner;
    ImageView dogImage;
    TextView textViewLoading;
    ProgressDialog progressDialog;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        catagorySpinner = findViewById(R.id.catagory_spinner);
        dogImage = findViewById(R.id.imageViewDog);
        textViewLoading = findViewById(R.id.textView3);
        progressDialog = new ProgressDialog(MainActivity.this);




        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(dogImage);
        Glide.with(MainActivity.this)
                .load(R.drawable.loading)
                .into(imageViewTarget);


        Call<Dogs> call = apiInterface.getDogs();
        call.enqueue(new Callback<Dogs>() {
            @Override
            public void onResponse(Call<Dogs> call, Response<Dogs> response) {

                catagorySpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,response.body().getMessage()));
                textViewLoading.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                Log.i("onfailure","fail");
                dogImage.setImageResource(R.drawable.dog);


            }
        });

        catagorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                progressDialog.setMessage("Loading, Please Wait!");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                selectedItem = catagorySpinner.getSelectedItem().toString();
                RandomImageLoad();
                textViewLoading.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RandomImageLoad();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void RandomImageLoad (){
        progressDialog.show();
        apiInterface.getBreedImage(selectedItem).enqueue(new Callback<RandomDogImage>() {
            @Override
            public void onResponse(Call<RandomDogImage> call, Response<RandomDogImage> response) {
                Glide.with(MainActivity.this)
                        .load(response.body().getMessage())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                progressDialog.dismiss();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                progressDialog.dismiss();
                                textViewLoading.setText("Get Next Image");
                                textViewLoading.setVisibility(View.VISIBLE);
                                return false;
                            }
                        })
                        .into(dogImage);

                //Toast.makeText(MainActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RandomDogImage> call, Throwable t) {

            }
        });
    }
}
