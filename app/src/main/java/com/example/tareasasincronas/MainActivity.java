package com.example.tareasasincronas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView c1,c2;
    Button b,c;
int maximo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relacionarVistas();
    }
    public void relacionarVistas(){
        c1=(TextView) findViewById(R.id.contador1);
        c2=(TextView) findViewById(R.id.contador2);
        b=(Button) findViewById(R.id.iniciar);
    }
    public void iniciarBoton(View v){
//TareaAsicronaContadores objeto=new TareaAsicronaContadores();
//objeto.execute();

        new TareaAsicronaContadores().execute();
        ProgressDialog progreso = new ProgressDialog(this);
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setIcon(R.drawable.ic_launcher_background);
        progreso.setTitle("Barras de Progreso");
        progreso.show();
    }

    class TareaAsicronaContadores extends AsyncTask {

        @Override
        protected void onPreExecute() {
            maximo=1000;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            for(int i=0;i<maximo;i++)
                publishProgress(i);
            for (int j=0;j<maximo;j++)
                publishProgress(j);
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            c1.setText(String.valueOf(values[0]));
            c2.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(getApplicationContext(), "Acabo el conteo", Toast.LENGTH_SHORT).show();
            maximo=0;
        }
    }
}