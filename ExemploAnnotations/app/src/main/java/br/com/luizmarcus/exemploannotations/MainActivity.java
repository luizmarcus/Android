package br.com.luizmarcus.exemploannotations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.InputStream;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "https://pixabay.com/static/uploads/photo/2013/10/22/03/49/waterfall-199204_960_720.jpg";

    @ViewById
    protected ImageView imagem;

    @ViewById(R.id.viewText)
    protected TextView texto;

    @ViewById
    protected Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Chamado após carregar as views
     */
    @AfterViews
    void loadViews(){
        texto.setText("Views inicializadas");
    }

    /*
    * Chamado quando o botão é clicado
    * */
    @Click(R.id.botao)
    void baixarImagem(){
        texto.setText("Baixando imagem");
        executarDownload();
    }

    /*
    * Cria uma thread em background para executar o método
    * */
    @Background
    void executarDownload(){
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(IMG_URL).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        atualizaViews(img);
    }

    /**
     * Executa o método na thread principal
     */
    @UiThread
    void atualizaViews(Bitmap bitmap){
        imagem.setImageBitmap(bitmap);
        texto.setText("Carregou!");
    }
}
