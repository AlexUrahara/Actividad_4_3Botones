package com.example.actividad4_3botones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText text1;
    private Button btn1;

    private EditText text2;
    private Button btn2;
    private EditText text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById(R.id.texto1);
        btn1 = (Button) findViewById(R.id.boton1);


        btn2 = (Button)findViewById(R.id.boton2);
        text2 = (EditText) findViewById(R.id.texto2);
        text5 = (EditText)findViewById(R.id.texto5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text11 = text1.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text11));
                startActivity(intent);
            }
        });
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    {
                            Manifest.permission.SEND_SMS},1000);
        }else {
        };
    }
    public void enviar1(View view){
        String  numero = text5.getText().toString();
        String  mensaje = text2.getText().toString();
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero, null, mensaje, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado Correctamente", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Mensaje no Enviado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void enviarsms(View view){
        String numero=text5.getText().toString();
        String mensaje=text2.getText().toString();
        Uri uri = Uri.parse("smsto: " + numero);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", mensaje);
        startActivity(it);
    }
}