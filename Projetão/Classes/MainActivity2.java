package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.ion.Ion; // Certifique-se de que a lib Ion está no build.gradle

public class MainActivity2 extends AppCompatActivity {

    EditText txtnome, txtsobrenome, txtusuario, txtdata, txtsenha, txtemail;
    Button btncadastrar, btnentrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Iniciando os campos
        txtdata = findViewById(R.id.txtdata);
        txtemail = findViewById(R.id.txtemail);
        txtusuario = findViewById(R.id.txtusuario);
        txtsobrenome = findViewById(R.id.txtsobrenome);
        txtnome = findViewById(R.id.txtnome);
        txtsenha = findViewById(R.id.txtsenha);
        btncadastrar = findViewById(R.id.btncadastrar);
        btnentrar = findViewById(R.id.btnentrar);

        btncadastrar.setOnClickListener(v -> {
            cadastrar();
        });

        btnentrar.setOnClickListener(v -> finish());
    }

    private void cadastrar() {
        // Pegando os valores
        String nome = txtnome.getText().toString().trim();
        String sobrenome = txtsobrenome.getText().toString().trim();
        String usuario = txtusuario.getText().toString().trim();
        String data = txtdata.getText().toString().trim();
        String email = txtemail.getText().toString().trim();
        String senha = txtsenha.getText().toString().trim();

        // Validando
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }


        String url = "https://mayara.fwh.is/inserir.php";
        //String url = "http://192.168.15.6/testemeninas/inserir.php"; //IP minha máquina

        Ion.with(this)
                .load("POST", url)
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .setBodyParameter("nome", nome)
                .setBodyParameter("sobrenome", sobrenome)
                .setBodyParameter("usuario", usuario)
                .setBodyParameter("data_nascimento", data)
                .setBodyParameter("email", email)
                .setBodyParameter("senha", senha)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (e != null) {
                        Log.e("CADASTRO", "Erro: " + e.getMessage());
                        Toast.makeText(this, "Erro na conexão", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        String status = result.get("status").getAsString();

                        if (status.equals("ok")) {
                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                            finish(); // Volta para a tela de login
                        } else if (status.equals("erro_email")) {
                            Toast.makeText(this, "Este e-mail já está cadastrado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Log.e("CADASTRO", "Erro no JSON: " + ex.getMessage());
                    }
                });
    }
}