package trabalhofinaldesenvolvimentomobile.damwiki;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import trabalhofinaldesenvolvimentomobile.damwiki.dominio.RepositorioCategoria;
import trabalhofinaldesenvolvimentomobile.damwiki.database.DataBase;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Categoria;

public class CadastroCategoria extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase conn;

    private RepositorioCategoria repositorioCategoria;
    private Categoria categoria;

    private EditText edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        edtDescricao  = (EditText)findViewById(R.id.edtDescricaoCategoria);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioCategoria = new RepositorioCategoria(conn);
        }
        catch(SQLException ex)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao iniciar conexão. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }

    public void backMainActivity(View view) {
        finish();
    }

    public void cadastrarCategoria(View view) {

        try
        {
            categoria = new Categoria();
            categoria.setDescricao(edtDescricao.getText().toString());
            repositorioCategoria.inserirCategoria(categoria);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Categoria cadastrada com sucesso!");
            dialog.setNegativeButton("OK", null);
            dialog.show();
            TextView editTextDescCategoria = (TextView) findViewById(R.id.edtDescricaoCategoria);
            editTextDescCategoria.setText("");
        }
        catch (Exception ex)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao cadastrar categoria. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }
}
