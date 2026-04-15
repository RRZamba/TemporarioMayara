package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity
{

    EditText txtEmail, txtSenha;
    Button btnEntrar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent it = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(it);
            }
        });
    }


    //Método para fazer login na nuvem
    public void login() {

        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();

        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://mayara.fwh.is/listar.php";
        //String url = "http://192.168.15.6/testemeninas/listar.php"; //IP minha máquina

        /*
         //   RETORNO DO LOG DE ERRO
        Ion.with(this)
                .load("POST", url)
                .setBodyParameter("email", email)
                .setBodyParameter("senha", senha)
                .asString() // Mude para String temporariamente
                .setCallback((e, result) -> {
                    // Agora o 'result' é o texto puro que o PHP mandou
                    Log.d("RESPOSTA_SERVIDOR", result);
                    // Verifique o Logcat para ver exatamente o que o PHP escreveu
                });

*/


        Ion.with(this)
                .load("POST", url)
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .setBodyParameter("email", email)
                .setBodyParameter("senha", senha)
                .asJsonObject()
                .setCallback((e, result) -> {

                    if (e != null) {
                        e.printStackTrace();
                        Log.e("LOGIN", "Erro detalhado: ", e);
                        Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }



                    try {
                        String status = result.get("status").getAsString();

                        if (status.equals("ok")) {

                            String login = result.get("login").getAsString();

                            Toast.makeText(this, "Bem-vindo " + login, Toast.LENGTH_SHORT).show();

                            // Ir para próxima tela
                            Intent i = new Intent(this, MainActivity2.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception ex) {
                        Log.e("LOGIN", "Erro ao processar JSON");
                    }
                });
    }


    }