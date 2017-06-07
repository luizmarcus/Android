package br.com.luizmarcus.exemplokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var result: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val str = findViewById(R.id.text) as EditText
        val button = findViewById(R.id.btn) as Button
        result = findViewById(R.id.result) as TextView

        //Onclick em lambda
        button.setOnClickListener {

            result?.text = str.text

            Toast.makeText(this,str.text,Toast.LENGTH_LONG).show()

            str.text = Editable.Factory.getInstance().newEditable("")

        }

    }

}
