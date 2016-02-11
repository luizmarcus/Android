package br.com.luizmarcus.exemplopalette;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "https://pixabay.com/static/uploads/photo/2013/10/22/03/49/waterfall-199204_960_720.jpg";

    LinearLayout vibrant, vibrantDark,vibrantLight,muted, mutedDark,mutedLight;

    TextView vibrantText, vibrantDarkText, vibrantLightText, mutedText, mutedDarkText, mutedLightText;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //Obter a imagem a partir da URL
        Picasso.with(this).load(IMG_URL).into(img, new Callback() {
            @Override
            public void onSuccess() {
                loadPallete();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void initViews(){

        vibrantDark = (LinearLayout) findViewById(R.id.vibrantDark);
        vibrantLight = (LinearLayout) findViewById(R.id.vibrantLight);
        vibrant = (LinearLayout) findViewById(R.id.vibrant);
        muted = (LinearLayout) findViewById(R.id.muted);
        mutedDark = (LinearLayout) findViewById(R.id.mutedDark);
        mutedLight = (LinearLayout) findViewById(R.id.mutedLight);

        vibrantText = (TextView) findViewById(R.id.vibrantText);
        vibrantDarkText = (TextView) findViewById(R.id.vibrantDarkText);
        vibrantLightText = (TextView) findViewById(R.id.vibrantLightText);
        mutedText = (TextView) findViewById(R.id.mutedText);
        mutedDarkText = (TextView) findViewById(R.id.mutedDarkText);
        mutedLightText = (TextView) findViewById(R.id.mutedLightText);

        img = (ImageView)findViewById(R.id.img);
    }

    private void loadPallete(){

        //obtém uma bitmap a partir da imagem do ImageView
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        /*A pallete serve para obter as cores mais predominantes de uma imagem
        e setar em componentes.
        */
        final Palette.Builder pallete = new Palette.Builder(bitmap);
        pallete.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                Palette.Swatch vibrantSwatchDark = palette.getDarkVibrantSwatch();
                Palette.Swatch vibrantSwatchLight = palette.getLightVibrantSwatch();
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                Palette.Swatch mutedSwatchDark = palette.getDarkMutedSwatch();
                Palette.Swatch mutedSwatchLight = palette.getLightMutedSwatch();

                if (vibrantSwatch!=null) {
                    vibrant.setBackgroundColor(vibrantSwatch.getRgb());
                    vibrantText.setTextColor(vibrantSwatch.getTitleTextColor());
                    vibrantText.setText("Vibrant");
                }

                if (vibrantSwatchDark!=null) {
                    vibrantDark.setBackgroundColor(vibrantSwatchDark.getRgb());
                    vibrantDarkText.setTextColor(vibrantSwatchDark.getTitleTextColor());
                    vibrantDarkText.setText("Vibrant Dark");
                }

                if (vibrantSwatchLight!=null) {
                    vibrantLight.setBackgroundColor(vibrantSwatchLight.getRgb());
                    vibrantLightText.setTextColor(vibrantSwatchLight.getTitleTextColor());
                    vibrantLightText.setText("Vibrant Light");
                }

                if (mutedSwatch!=null) {
                    muted.setBackgroundColor(mutedSwatch.getRgb());
                    mutedText.setTextColor(mutedSwatch.getTitleTextColor());
                    mutedText.setText("Muted");
                }

                if (mutedSwatchLight!=null) {
                    mutedLight.setBackgroundColor(mutedSwatchLight.getRgb());
                    mutedLightText.setTextColor(mutedSwatchLight.getTitleTextColor());
                    mutedLightText.setText("Muted Light");
                }

                if(mutedSwatchDark!=null) {
                    mutedDark.setBackgroundColor(mutedSwatchDark.getRgb());
                    mutedDarkText.setTextColor(mutedSwatchDark.getTitleTextColor());
                    mutedDarkText.setText("Muted Dark");
                }

            }
        });
    }
}
