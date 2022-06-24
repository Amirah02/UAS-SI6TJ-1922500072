package com.example.uassitj61922500072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private LecturerJasonPlaceHolderAPI jasonPlaceHolderAPI;
    private FloatingActionButton tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah = findViewById(R.id.input);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Dosen.class);
                startActivity(i);
            }
        });
        textViewResult = findViewById(R.id.text_dosen_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.70/UAS1922500072/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jasonPlaceHolderAPI = retrofit.create(LecturerJasonPlaceHolderAPI.class);
        getPosts();
    }
    private void getPosts(){
        Map<String, String> parameters = new HashMap<>();
        Call<List<tampil>> call = jasonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<tampil>>() {
            @Override
            public void onResponse(Call<List<tampil>> call, Response<List<tampil>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code:  " +response.code());
                    return;
                }
                List<tampil> posts = response.body();
                for (tampil post : posts){
                    String content = "";
                    content += "nidn :    " + post.getNidn() + "\n";
                    content += "nama dosen :   " + post.getNama_dosen() + "\n";
                    content += "jabatan :   " + post.getJabatan() + "\n";
                    content += "golpang :   " + post.getGol_pang() + "\n";
                    content += "bidang keahlian :   " + post.getKeahlian() + "\n";
                    content += "prodi :   " + post.getProgram_studi() + "\n \n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<tampil>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
}