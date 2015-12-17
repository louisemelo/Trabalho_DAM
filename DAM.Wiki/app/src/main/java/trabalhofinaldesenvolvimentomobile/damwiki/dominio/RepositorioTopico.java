package trabalhofinaldesenvolvimentomobile.damwiki.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Script;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import trabalhofinaldesenvolvimentomobile.damwiki.database.ScriptSQL;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Categoria;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Topico;

/**
 * Created by Louise on 16/12/2015.
 */
public class RepositorioTopico {

    private SQLiteDatabase conn;


    public RepositorioTopico(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public void inserirTopico(Topico topico)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Titulo", topico.getTitulo());
        contentValues.put("Descricao", topico.getDescricao());
        contentValues.put("id_categoria", topico.get_identificadorCategoria());
        conn.insertOrThrow("Topico", null, contentValues);
    }

    public List<Topico> pesquisarTopico(long id_categoria, String titulo, Context contexto)
    {
        List<Topico> arrayAdapterTopicos = new ArrayList<Topico>();

        Cursor cursor = conn.rawQuery(ScriptSQL.buscarTopicos(),new String[]{ "%" + titulo + "%", ""+id_categoria});
        //conn.execSQL(ScriptSQL.buscarTopicos(titulo));

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst(); // Caso haja registro, posiciona no primeiro
            do
            {
                Topico topico = new Topico();
                topico.set_id(cursor.getLong(0));
                topico.setTitulo(cursor.getString(1));
                topico.setDescricao(cursor.getString(2));

                arrayAdapterTopicos.add((topico));
            }
            while(cursor.moveToNext());
        }

        return arrayAdapterTopicos;

    }
}
