package edu.upc.dsa.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import edu.upc.dsa.proyecto.api.Client;
import edu.upc.dsa.proyecto.api.CookWithMeAPI;
import edu.upc.dsa.proyecto.models.Denuncia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenunciaActivity extends AppCompatActivity {

    EditText editTextNombreDen;
    EditText editTextComentarioDen;

    CookWithMeAPI gitHub;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent main= new Intent (DenunciaActivity.this, MainActivity.class);
                startActivity(main);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        gitHub= Client.getClient().create(CookWithMeAPI.class);

        editTextNombreDen = (EditText)findViewById(R.id.editTextNombre);
        editTextComentarioDen = (EditText)findViewById(R.id.editTextComentario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void buttonEnviar(View v){
        String fecha= new Date().toString();
        String nombre = editTextNombreDen.getText().toString();
        String comentario = editTextComentarioDen.getText().toString();

        gitHub.postDenuncia(new Denuncia(fecha,nombre,comentario)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("RECIPE",""+response.code());

                if(response.code() == 201){
                    Toast.makeText(getApplicationContext(),"Denuncia enviada", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                String msg = "Error in retrofit: " + t.toString();
                Log.d("RECIPE",msg);
                Toast.makeText(getApplicationContext(),"msg", Toast.LENGTH_SHORT).show();


            }
        });
        Log.d("RECIPE", "end login");

    }
}