package com.example.sem4_s2

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val CAMERA_REQUEST_CODE = 0
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamera = findViewById<Button>(R.id.btCamera)
        btCamera.setOnClickListener{
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA )
            != PackageManager.PERMISSION_GRANTED){
            //pido permiso
            requestCameraPermision()
        }
        else {
            //tiene permiso
            Toast.makeText(this, "Ya tiene permiso!", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermision (){
        if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.CAMERA)){
            Toast.makeText(this, "Ya rechazó el permiso antes. Habilítelo MANUALMENTE", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this,"Nunca se pidió el permiso. Acepte o rechace el permiso", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permiso otorgado
                    Toast.makeText(this, "Se otorgo el permiso", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Permiso NEGADO!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}