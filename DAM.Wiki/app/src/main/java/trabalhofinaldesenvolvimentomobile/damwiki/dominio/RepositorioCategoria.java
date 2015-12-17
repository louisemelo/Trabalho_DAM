package trabalhofinaldesenvolvimentomobile.damwiki.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.List;

import trabalhofinaldesenvolvimentomobile.damwiki.R;
import trabalhofinaldesenvolvimentomobile.damwiki.entidades.Categoria;

/**
 * Created by Louise on 13/12/2015.
 */
public class RepositorioCategoria {

    private SQLiteDatabase conn;


    public RepositorioCategoria(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public void inserirCategoria(Categoria categoria)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Descricao",categoria.getDescricao());
        conn.insertOrThrow("Categoria", null, contentValues);
    }

    public List<Categoria> buscaCategorias(Context contexto)
    {
        //ArrayAdapter<String> arrayAdapterCategorias = new ArrayAdapter<String>(contexto, android.R.layout.simple_list_item_1);
        List<Categoria> listaCategorias = new ArrayList<Categoria>();

        Cursor cursor = conn.query(("Categoria"), null, null, null, null, null, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst(); // Caso haja registro, posiciona no primeiro
            do
            {
                //Categoria categoria = cursor.getString(1);
                Categoria categoria = new Categoria();
                categoria.set_id(cursor.getLong(0));
                categoria.setDescricao(cursor.getString(1));

                listaCategorias.add((categoria));
            }
            while(cursor.moveToNext());
        }

        return listaCategorias;
    }
}
