package br.com.luizmarcus.exemploandroidrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRate.with(this)
                .setInstallDays(0) //Conta os dias de instalação do aplicativo no smartphone do usuário. Padrão é 10. 0 significa "mesmo dia da instalação"
                .setLaunchTimes(0) //Conta o número de vezes que o usuário abriu o app. Padrão é 10
                .setRemindInterval(0) // Intervalo de dias entre uma exibião e outra, caso o usuário clique em "avaliar mais tarde"
                .setShowLaterButton(true) // Exibir o botão "Avaliar mais tarde"
                .setDebug(false) // Mostrar debug
                .setOnClickButtonListener(new OnClickButtonListener() {
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                        switch (which){
                            case -1:
                                Toast.makeText(MainActivity.this,"Botão avaliar",Toast.LENGTH_LONG).show();
                                break;
                            case -2:
                                Toast.makeText(MainActivity.this,"Botão cancelar",Toast.LENGTH_LONG).show();
                                break;
                            case -3:
                                Toast.makeText(MainActivity.this,"Botão avaliar depois",Toast.LENGTH_LONG).show();
                                break;

                        }
                    }
                })
                /*
                * Os métodos abaixo são usados para customizar os textos dos botões, a mensagem e o título da caixa de diálogo
                * */
                //.setTitle(R.string.new_rate_dialog_title)
                //.setMessage(R.string.new_rate_dialog_message)
                //.setTextLater(R.string.new_rate_dialog_later)
                //.setTextNever(R.string.new_rate_dialog_never)
                //.setTextRateNow(R.string.new_rate_dialog_ok)
                .monitor();

        // Mostra a caixa de dialogo, caso as condições acima sejam atendidas
        AppRate.showRateDialogIfMeetsConditions(this);

    }
}
