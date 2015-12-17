package trabalhofinaldesenvolvimentomobile.damwiki;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.database.*;

import trabalhofinaldesenvolvimentomobile.damwiki.database.DataBase;

public class MainActivity extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase conn; // representa conexão com banco

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getReadableDatabase(); // Cria e abre o Banco de Dados
        }
        catch(SQLException ex){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao criar conexão. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }

    public void startCadastroCategoriaActivity(View view) {
        Intent cadastroCategoriaActivity = new Intent(this, CadastroCategoria.class);
        startActivity(cadastroCategoriaActivity);
    }

    public void startCadastroTopicoActivity(View view) {
        Intent cadastroTopicoActivity = new Intent(this, CadastroTopico.class);
        startActivity(cadastroTopicoActivity);
    }

    public void startSobreActivity(View view) {
        Intent sobreActivity = new Intent(this, Sobre.class);
        startActivity(sobreActivity);
    }

    public void startPesquisaTopicoActivity(View view) {
        Intent pesquisaTopicoActivity = new Intent(this, PesquisaTopico.class);
        startActivity(pesquisaTopicoActivity);
    }

    public void fecharApp(View view) {
        finish();
        System.exit(0);
    }


}
