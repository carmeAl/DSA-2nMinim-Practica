package edu.upc.dsa.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.upc.dsa.proyecto.api.Client;
import edu.upc.dsa.proyecto.api.CookWithMeAPI;
import edu.upc.dsa.proyecto.models.FAQs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapterFaqs adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    CookWithMeAPI gitHub;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "datosLogIn";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //flecha
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // define an adapter
        adapter = new MyAdapterFaqs();
        recyclerView.setAdapter(adapter);
        gitHub= Client.getClient().create(CookWithMeAPI.class);

        doApiCall(null); //hace las llamadas

        // Manage swipe on items, controla los movimientos
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        doApiCall(swipeRefreshLayout);
                    }
                }
        );
    }


    private void doApiCall(SwipeRefreshLayout swipeRefreshLayout) {
        Call<List<FAQs>> call = gitHub.getAllFAQs();

        call.enqueue(new Callback<List<FAQs>>() {
            @Override
            public void onResponse(Call<List<FAQs>> call, Response<List<FAQs>> response) {
                // set the results to the adapter
                adapter.setData(response.body()); //le paso el body al adapter para que la muestre

                if(swipeRefreshLayout!=null) swipeRefreshLayout.setRefreshing(false); //desactiva la animación, no hace falta si no hay swipe
            }
            @Override
            public void onFailure(Call<List<FAQs>> call, Throwable t) { //error en las comunicaciones
                if(swipeRefreshLayout!=null) swipeRefreshLayout.setRefreshing(false);

                String msg = "Error in retrofit: "+t.toString();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent main= new Intent (FaqsActivity.this, AyudaActivity.class);
                startActivity(main);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}