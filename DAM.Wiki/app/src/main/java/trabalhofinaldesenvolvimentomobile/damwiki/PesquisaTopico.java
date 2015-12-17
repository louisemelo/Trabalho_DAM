package trabalhofinaldesenvolvimentomobile.damwiki;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

import trabalhofinaldesenvolvimentomobile.damwiki.database.DataBase;
import trabalhofinaldesenvolvimentomobile.damwiki.dominio.RepositorioCategoria;
import trabalhofinaldesenvolvimentomobile.damwiki.dominio.RepositorioTopico;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Categoria;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Topico;

public class PesquisaTopico extends AppCompatActivity {

    private Spinner listaCategorias;
    private ListView listaTopicos;
    private List<Topico> topicosConsultados;
    private List<Categoria> categoriasConsultadas;
    private ArrayAdapter<String> arrayAdapterCategorias;
    private ArrayAdapter<String> arrayAdapterTopicos;

    private DataBase dataBase;
    private SQLiteDatabase conn;

    private RepositorioCategoria repositorioCategoria;
    private RepositorioTopico repositorioTopico;
    private Topico topico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_topico);

        listaCategorias = (Spinner)findViewById(R.id.spinnerCategoria);
        listaTopicos = (ListView)findViewById(R.id.listViewTopicos);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioCategoria = new RepositorioCategoria(conn);
            repositorioTopico = new RepositorioTopico(conn);

            categoriasConsultadas = repositorioCategoria.buscaCategorias(this);
            arrayAdapterCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            arrayAdapterCategorias.insert("Selecione...", 0);

            for(Categoria categoria : categoriasConsultadas)
            {
                arrayAdapterCategorias.add(categoria.getDescricao());
            }

            listaCategorias.setAdapter(arrayAdapterCategorias);

            // clique no pessoa
            listaTopicos.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                  //  Intent pessoaListView;
                   // pessoaListView = new Intent(PessoaListView.this, PessoaDetail.class);
                   // pessoaListView.putExtra("pessoa_id", topicosConsultados.get(position).getDescricao());
                   // startActivity(pessoaListView);
                    ExibeDescricao(position);


                }
            });

        }
        catch(SQLException ex)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao buscar categorias. Detalhes: " + ex.getMessage());
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
    }

    public void pesquisarTopico(View view) {
        TextView editTextTitulo = (TextView) findViewById(R.id.editTextTituloTopico);
        Spinner spinnerCategoria = (Spinner)findViewById(R.id.spinnerCategoria);


        topicosConsultados = repositorioTopico.pesquisarTopico(spinnerCategoria.getSelectedItemId(), editTextTitulo.getText().toString(), this);

        arrayAdapterTopicos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        if(topicosConsultados.size() == 0)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Nenhum tópico encontrado para o filtro informado!");
            dialog.setNegativeButton("OK",null);
            dialog.show();
        }
        else
        {

            for (Topico topico : topicosConsultados) {
                arrayAdapterTopicos.add(topico.getTitulo());
            }
        }
        listaTopicos.setAdapter(arrayAdapterTopicos);
    }

    public void backMainActivity(View view) {
        finish();
    }

    public void ExibeDescricao(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(topicosConsultados.get(position).getDescricao());
        dialog.setNegativeButton("OK",null);
        dialog.show();
    }
}
