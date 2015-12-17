package trabalhofinaldesenvolvimentomobile.damwiki.database;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by Louise on 13/12/2015.
 */
public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context contexto)
    {
        super(contexto, "DataBaseWikiApp", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateCategoria());
        db.execSQL(ScriptSQL.getCreateTopico());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE FROM Topico");
        db.execSQL("DELETE FROM Categoria");
    }
}
