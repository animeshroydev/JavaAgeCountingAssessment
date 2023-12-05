package com.animeshpreps.jsonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView responseText, showReverseToken;
    ApiInterface apiInterface;
    String long_dump;

    List<String> key = new ArrayList<>();
    List<String> age = new ArrayList<>();
    List<Integer> finalAge = new ArrayList<>();
    String challengeToken = "ph2eolm4c19";
    String reverseToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = findViewById(R.id.showData);
        showReverseToken = findViewById(R.id.showReverseToken);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<ModelUser> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                ModelUser user1 = response.body();
                long_dump = user1.getData();
                Log.d(TAG, "onResponse: " + long_dump);
                displayData();
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

            }
        });
    }

    private void displayData() {
        try {
            // Extract key Starts here
            String[] keyArr;
            keyArr = long_dump.split("key=", 0);
            for (String a : keyArr) {
                String[] split = a.split(",");
                String firstSubString = split[0];

                // String secondSubString = split[1];
                key.add(firstSubString);
            }
            key.remove(0);
            // System.out.println("key: " + key);
            // Extract key ends here


            // Extract age Starts here
            String[] ageArr;
            ageArr = long_dump.split("age=", 0);
            for (String a : ageArr) {
                String[] split = a.split(",");
                String firstSubString = split[0];

                // String secondSubString = split[1];
                age.add(firstSubString);
            }
            age.remove(0);

            for (int j = 0; j<age.size(); j++) {
                finalAge.add(Integer.valueOf(age.get(j)));
            }

            // System.out.println("age: " + age);
            // Extract age ends here
        } catch (Exception e) {

        }

        int count = 0;
        for (int i = 0; i<age.size(); i++) {
            // System.out.println(finalAge.get(i));
            if (finalAge.get(i) >= 50) {
                count++;
            }
        }

        responseText.setText("Item exists that have age greater than 50 are: " + count);
        reverseToken = challengeToken +":"+ String.valueOf(count);
        StringBuilder builder = new StringBuilder(reverseToken);
        showReverseToken.setText("Modified Token > " + builder.reverse());
    }


}