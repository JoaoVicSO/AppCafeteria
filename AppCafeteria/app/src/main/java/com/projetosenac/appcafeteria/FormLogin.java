package com.projetosenac.appcafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FormLogin extends AppCompatActivity {

    private TextView textTelaCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        ProgressBar progressBar = findViewById(R.id.ProgressIdBarLogin);
        progressBar.setVisibility(View.INVISIBLE);
        this.chamaTelaCadastro();

    }

    private void chamaTelaCadastro() {
        textTelaCadastro = findViewById(R.id.TextViewIdCadastroLogin);
        textTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });
    }
}