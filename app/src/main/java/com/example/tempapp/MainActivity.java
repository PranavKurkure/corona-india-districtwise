package com.example.tempapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity  {

    private static final String URL_DATA = "https://api.covid19india.org/v2/state_district_wise.json";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RequestQueue mq;

    String val;

    ArrayList<String> stateList;
    Spinner State;


    List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mq = Volley.newRequestQueue(this);

        State = findViewById(R.id.State);

        stateList = new ArrayList<>();

        stateList.add("Choose State!");
        stateList.add("Andaman and \nNicobar Islands");
        stateList.add("Andhra Pradesh");
        stateList.add("Arunachal Pradesh");
        stateList.add("Assam");
        stateList.add("Bihar");
        stateList.add("Chandigarh");
        stateList.add("Chhttisgarh");
        stateList.add("Dadar Nagar Haveli");
        stateList.add("Delhi");
        stateList.add("Goa");
        stateList.add("Gujarat");
        stateList.add("Harnaya");
        stateList.add("Himachal Pradesh");
        stateList.add("Jammu and Kashmir");
        stateList.add("Jharkhand");
        stateList.add("Karnataka");
        stateList.add("Kerala");
        stateList.add("Ladakh");
        stateList.add("Lakshadweep");
        stateList.add("Madhya Pradesh");
        stateList.add("Maharashtra");
        stateList.add("Manipur");
        stateList.add("Meghalaya");
        stateList.add("Mizoram");
        stateList.add("Nagaland");
        stateList.add("Odisha");
        stateList.add("Puducherry");
        stateList.add("Punjab");
        stateList.add("Rajasthan");
        stateList.add("Sikkim");
        stateList.add("Tamil Nadu");
        stateList.add("Telengana");
        stateList.add("Tripura");
        stateList.add("uttar Pradesh");
        stateList.add("Uttarakhand");
        stateList.add("West Bengal");

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,stateList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        State.setAdapter(stateAdapter);

        State.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Choose State!")){
                    Toast.makeText(parent.getContext(),"Please choose state!",Toast.LENGTH_SHORT).show();
                }else{
                    String state = parent.getItemAtPosition(position).toString();
                    val = state.toLowerCase();
                    clearRecyclerViewData();
                    loadRecyclerViewData(val);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        



    }



    private void loadRecyclerViewData(final String val) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();



        JsonArrayRequest request = new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();


                try {

                    //JSONArray jsonArray = response.getJSONArray("statewise");
                    int str_len = response.length();
                    String lenn = Integer.toString(str_len);
                    Log.d("lenn",lenn);
                    for (int i = 0; i < response.length(); i++)
                    {
                        JSONObject st = response.getJSONObject(i);

                        String statename = st.getString("state");
                        statename = statename.toLowerCase();
                        if(statename.equals(val))
                        {
                            JSONArray jsonArray = st.getJSONArray("districtData");
                            for(int j=0;j<jsonArray.length();j++)
                            {
                                JSONObject dis = jsonArray.getJSONObject(j);
                                ListItem item = new ListItem(val,
                                        dis.getString("district"),
                                        dis.getString("active"),
                                        dis.getString("confirmed"),
                                        dis.getString("deceased"),
                                        dis.getString("recovered"));
                                listItems.add(item);
                                String districtname = dis.getString("district");
                                String active = dis.getString("active");
                                String confirmed = dis.getString("confirmed");
                                String deaths = dis.getString("deceased");
                                String recovered = dis.getString("recovered");
                                districtname = "District : "+ districtname;
                                active = "Active Cases : " + active;
                                confirmed = "Confirmed Cases : " + confirmed;
                                deaths = "Total Deaths : " + deaths;
                                recovered = "Total Recovered : " + recovered;



                            }
                            adapter = new Adapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        }


                    }
                }
                catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error in catch block",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        mq.add(request);
    }

    private void clearRecyclerViewData(){
        listItems.clear();
    }



}