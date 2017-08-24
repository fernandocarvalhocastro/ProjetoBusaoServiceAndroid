package onibus.fiap.com.br.onibus;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OnibusActivity extends AppCompatActivity implements Button.OnClickListener{

    Button iniciar;
    Button parar;

    private EditText edtDistancia;
    private EditText edtLinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onibus);
        iniciar = (Button)findViewById(R.id.btnIniciar);
        iniciar.setOnClickListener(this);
        parar = (Button)findViewById(R.id.btnParar);
        parar.setOnClickListener(this);

        edtDistancia = (EditText)findViewById(R.id.edtDistancia);
        edtLinha = (EditText)findViewById(R.id.edtLinha);

    }

    public void iniciar(View v){
        Intent start = new Intent(this,OnibusService.class);
        start.putExtra("distancia",edtDistancia.getText().toString());
        start.putExtra("linha",edtLinha.getText().toString());
        startService(start);
        finish();
    }

    public void parar(View v){
        Intent stop = new Intent(this,OnibusService.class);
        stopService(stop);
    }

    @Override
    public void onClick(View v) {
        if(v==iniciar){
            iniciar(v);
        }else if(v==parar){
            parar(v);
        }
    }
}
