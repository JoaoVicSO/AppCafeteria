package com.projetosenac.appcafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FormCadastro extends AppCompatActivity {

    private ImageButton ImageButtonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        this.voltarLogin();
    }

    public void voltarLogin() {
        this.ImageButtonVoltar = findViewById(R.id.ButtonIdVoltarCadastro);
        ImageButtonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormCadastro.this, FormLogin.class);
                startActivity(intent);
            }
        });

    }
}