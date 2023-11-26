package com.projetosenac.appcafeteria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView textTelaCadastro, textTelaRecuperaConta;
    private EditText edit_email, edit_senha;
    private ProgressBar progressBar;
    private AppCompatButton bt_entrar;
    private String[] msgs = {"Preencha todos os campos!", "Login realizado com sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        this.chamaTelaCadastro();
        this.iniciarComponentes();
        this.chamarTelaRecuperarConta();
        this.login(); // autenticar user

    }// fim onCreate

    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioCorrente = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioCorrente !=null) {
            chamarTelaPrincipal();

        }// fim if

    }// fim  onStart

    private void login() {
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) { // está vazio?
                    Snackbar objSnackbar = Snackbar.make(view, msgs[0], Snackbar.LENGTH_SHORT);
                    objSnackbar.setBackgroundTint(Color.WHITE);
                    objSnackbar.setTextColor(Color.BLACK);
                    objSnackbar.show();

                }// fim if
                else { // ambos email e senha foram preenchidos
                    autenticarUsuario(view, email, senha);

                }// fim else

            }// Fim onClick

        });// fim setOnClickListener

    }// fim login

    public void autenticarUsuario(View view, String email, String senha) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    chamarTelaPrincipal();

                                } // fim run

                            }, 3000);

                        }// fim if

                        else { // deu ruim na auth
                            String erro = "";

                            try {
                                throw task.getException();

                            }// fim try

                            catch (Exception e) {
                                erro = "Erro ao logar no app!";
                                Snackbar objSnackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                                objSnackbar.setBackgroundTint(Color.WHITE);
                                objSnackbar.setTextColor(Color.BLACK);
                                objSnackbar.show();

                            }// fim catch

                        }// fim else

                    }// fim onComplete

                }// fim OnCompleteListener

        ); //addOnCompleteListener


    }// fim autenticarUsuario

    public void chamarTelaPrincipal() {
        Intent intent = new Intent(FormLogin.this, TelaPrincipal.class);
        startActivity(intent);
        finish();

    }// fim chamarTelaPrincipal

    public void chamarTelaRecuperarConta() {
        textTelaRecuperaConta = findViewById(R.id.TextViewIdRecuperarLogin);
        textTelaRecuperaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, RecuperarConta.class);
                startActivity(intent);
                finish();

            }// fim onClick

        });

    }// fim chamarTelaRecuperarConta


    private void chamaTelaCadastro() {
        textTelaCadastro = findViewById(R.id.TextViewIdCadastroLogin);
        textTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
                finish();

            }

        });

    } // fim chamaTelaCadastro

    private void iniciarComponentes() {
        edit_email = findViewById(R.id.EditTextIdEmailLogin);
        edit_senha = findViewById(R.id.EditTextIdSenhaLogin);
        bt_entrar = findViewById(R.id.ButtonIdLogin);
        progressBar = findViewById(R.id.ProgressIdBarLogin);
        progressBar.setVisibility(View.INVISIBLE);

    }// Fim inciarComponentes


}// fim class