package br.com.luizmarcus.exemplopermissiondispatcher

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import permissions.dispatcher.*

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private var button: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button =  findViewById(R.id.button)
        textView = findViewById(R.id.textview)
        textView?.text = "Clique no botão para começar"

        button?.setOnClickListener({
            //este metodo pertence a classe MainActivityPermissionDispatcher e serve como gatilho para o tratamento da permissão
            showCameraWithPermissionCheck()
        });

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //delega o tratamento da permissão ao método gerado na classe MainActivityPermissionDispatcher
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        //Método que solicita a permissão e caso ela seja garantida
        Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
        textView?.text = getString(R.string.permission_granted)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        //Este método server para exibir uma mensagem ao usuário antes do Dialog que irá pedir a permissão.
        AlertDialog.Builder(this)
                .setPositiveButton(getString(R.string.permission_yes)) { _, _ -> request.proceed() }
                .setNegativeButton(getString(R.string.permission_no)) { _, _ -> request.cancel() }
                .setCancelable(false)
                .setMessage(getString(R.string.permission_message))
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
        //Este método será chamado caso o usuário rejeite a permissão
        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        textView?.text = getString(R.string.permission_denied)
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
        //Este método será chamado caso o usuário rejeite a permissão e não queira que o recurso seja solicitado
        Toast.makeText(this, getString(R.string.permission_never_again), Toast.LENGTH_SHORT).show()
        textView?.text = getString(R.string.permission_never_again)
    }

}
