package br.com.luizmarcus.exemplodialogflow;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;

import java.util.Map;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener {

    private TextView textView;
    private Button button;

    private AIService aiService;

    private static final int RECORD_AUDIO_PERMISSION = 1;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        //Setando as configurações
        final AIConfiguration config = new AIConfiguration("seu_token",
                AIConfiguration.SupportedLanguages.PortugueseBrazil,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);
        aiService.setListener(this);

    }

    public void ouvir(View view){

        //Obtendo a permissão para leitura do áudio
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO_PERMISSION);
        } else {
            aiService.startListening();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RECORD_AUDIO_PERMISSION) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                aiService.startListening();

            } else {
                textView.setText("Permissão não obtida");
            }
        }

    }

    @Override
    public void onResult(AIResponse result) {
        Result r = result.getResult();

        // Obtendo os parâmetros compreendidos pelo Dialogflow
        String parameterString = "";
        if (r.getParameters() != null && !r.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : r.getParameters().entrySet()) {
                parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
            }
        }

        // Exibindo os parâmetros e a resposta para o áudio enviado
        textView.setText("Query:" + r.getResolvedQuery() +
                "\nAction: " + r.getAction() +
                "\nParameters: " + parameterString+
                "\nResponse: " + r.getFulfillment().getDisplayText());
    }

    @Override
    public void onError(AIError error) {
        textView.setText(error.toString());
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {
        Log.d(TAG,"Lendo audio!");
    }

    @Override
    public void onListeningCanceled() {
        Log.d(TAG,"Leitura de audio cancelada!");
    }

    @Override
    public void onListeningFinished() {
        Log.d(TAG,"Lendo audio finalizada!");
    }


}
