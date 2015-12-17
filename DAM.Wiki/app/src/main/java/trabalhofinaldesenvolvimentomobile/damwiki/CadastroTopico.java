package trabalhofinaldesenvolvimentomobile.damwiki;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import trabalhofinaldesenvolvimentomobile.damwiki.dominio.RepositorioCategoria;

import trabalhofinaldesenvolvimentomobile.damwiki.database.DataBase;
import trabalhofinaldesenvolvimentomobile.damwiki.dominio.RepositorioTopico;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Categoria;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Topico;

public class CadastroTopico extends AppCompatActivity {

    private Spinner listaCategorias;
    private ArrayAdapter<String> arrayAdapterCategorias;
    private List<Categoria> categoriasList;

    private DataBase dataBase;
    private SQLiteDatabase conn;

    private RepositorioCategoria repositorioCategoria;
    private RepositorioTopico repositorioTopico;
    private Topico topico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_topico);

        listaCategorias = (Spinner)findViewById(R.id.spinnerCategoria);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioCategoria = new RepositorioCategoria(conn);
            repositorioTopico = new RepositorioTopico(conn);

            categoriasList = repositorioCategoria.buscaCategorias(this);
            arrayAdapterCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

            arrayAdapterCategorias.insert("Selecione...", 0);

            for(Categoria categoria : categoriasList)
            {
                arrayAdapterCategorias.add(categoria.getDescricao());
            }

            listaCategorias.setAdapter(arrayAdapterCategorias);
        }
        catch(SQLException ex)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao buscar categorias. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }

    public void cadastrarTopico(View view) {
        try
        {
            TextView editTextTitulo = (TextView) findViewById(R.id.edtTitulo);
            TextView editTextDescricao = (TextView) findViewById(R.id.edtDescricao);
            Spinner spinnerCategoria = (Spinner)findViewById(R.id.spinnerCategoria);

            long teste = spinnerCategoria.getSelectedItemId();


            topico = new Topico();
            topico.setTitulo(editTextTitulo.getText().toString());
            topico.setDescricao(editTextDescricao.getText().toString());
            topico.set_identificadorCategoria(spinnerCategoria.getSelectedItemId());
            repositorioTopico.inserirTopico(topico);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Tópico cadastrado com sucesso!");
            dialog.setNegativeButton("OK", null);
            dialog.show();

            editTextTitulo.setText("");
            editTextDescricao.setText("");
            spinnerCategoria.setSelection(0);

        }
        catch (Exception ex)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao cadastrar tópico. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }

    public void backMainActivity(View view) {
       // Intent mainActivity = new Intent(this, MainActivity.class);
       // startActivity(mainActivity);
        finish();
    }
}

