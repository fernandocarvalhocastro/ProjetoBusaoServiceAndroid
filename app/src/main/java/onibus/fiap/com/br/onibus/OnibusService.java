package onibus.fiap.com.br.onibus;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import java.util.Timer;

public class OnibusService extends Service {
    private Timer timer;
    private AvisoOnibusTask task;

    public OnibusService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("SERVIÇO","SERVIÇO FOI INICIADO - ON CREATE");
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SERVICO","PROCESSANDO - ON START COMMAND");
        String distancia = intent.getExtras().getString("distancia");
        String linha = intent.getExtras().getString("linha");
        task = new AvisoOnibusTask(distancia,linha);
        timer.schedule(task,0, 5*1000);
        return START_STICKY; //Caso o android precise interromper seu serviço, ele obriga o sistema re-abrir o serviço do inicio.
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Log.i("SERVICO", "SERVIÇO FOI DESTRUIDO - ON DESTROY");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
