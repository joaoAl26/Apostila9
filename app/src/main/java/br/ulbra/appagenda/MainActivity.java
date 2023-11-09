package br.ulbra.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edCpf;
    private EditText edTelefone;
    private Button btSalvar;
    private Pessoa dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edTelefone = findViewById(R.id.edTelefone);
        btSalvar = findViewById(R.id.btSalvar);
        dao = new Pessoa();
        //linha de baixo utilizada para atualizar update
        Intent it = getIntent();
        if (it.hasExtra("pessoa")) {
            pessoa = (Pessoa) ((Intent) it).getSerializableExtra("pessoa");
            edNome.setText(pessoa.getNome());
            edCpf.setText(pessoa.getCpf());
            edTelefone.setText(pessoa.getTelefone());

        }
    }

    public void salvar(View view) {
        if (pessoa == null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(edNome.getText().toString());
            pessoa.setCpf(edCpf.getText().toString());
            pessoa.setTelefone(edTelefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida no ID de nº:" + id, Toast.LENGTH_LONG).show();
        } else {
            pessoa.setNome(edNome.getText().toString());
            pessoa.setCpf(edCpf.getText().toString());
            pessoa.setTelefone(edTelefone.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, pessoa.getNome() + ", atualizado com sucesso !!!", Toast.LENGTH_LONG).show();
        }

    }

}
