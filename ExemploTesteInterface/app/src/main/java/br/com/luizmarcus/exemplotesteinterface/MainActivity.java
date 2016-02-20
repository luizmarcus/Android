package br.com.luizmarcus.exemplotesteinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);

    }

    public void login(View view){

        if (user.getText().toString().equals("Testeapp") && pass.getText().toString().equals("Password")){
            startActivity(new Intent(this,SecondActivity.class));
        }else {
            Toast.makeText(this,getString(R.string.error),Toast.LENGTH_LONG).show();
        }


    }
}
