package br.com.luizmarcus.exemploanswers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Answers());
        setContentView(R.layout.activity_main);
    }

    public void onKeyMetric(View view) {
        Answers.getInstance().logCustom(new CustomEvent("Gerar Evento")
                .putCustomAttribute("Categoria", "Evento"));
    }

}
