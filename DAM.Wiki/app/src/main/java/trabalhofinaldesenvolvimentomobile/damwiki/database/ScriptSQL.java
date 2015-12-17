package trabalhofinaldesenvolvimentomobile.damwiki.database;

/**
 * Created by Louise on 13/12/2015.
 */
public class ScriptSQL {

    // region - SCRIPTS CATEGORIA

    public static String getCreateCategoria()
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS Categoria( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("Descricao VARCHAR(20) NOT NULL); ");
        sqlBuilder.append("DROP TABLE Topico; ");
        return sqlBuilder.toString();
    }

    public static String getInsertCategoria()
    {
        return "INSERT INTO Categoria('Teste');";
    }

    public static String getCategorias()
    {
        return "SELECT * FROM Categoria;";
    }

    // endregion - SCRIPTS CATEGORIA

    // region - SCRIPTS TOPICO

    public static String getCreateTopico()
    {


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS Topico( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("Titulo VARCHAR NOT NULL, ");
        sqlBuilder.append("Descricao VARCHAR NOT NULL, ");
        sqlBuilder.append("id_categoria INTEGER NOT NULL, ");
        sqlBuilder.append("FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria)); ");

        return sqlBuilder.toString();
    }

    public static String buscarTopicos()
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM Topico WHERE Titulo LIKE ? AND id_categoria = ?");
        return sqlBuilder.toString();
    }

    // endregion - SCRIPTS CATEGORIA


}
