package trabalhofinaldesenvolvimentomobile.damwiki.entidades;

/**
 * Created by Louise on 13/12/2015.
 */
public class Topico {

    // region  [ Propriedades ]

    private long _id;
    private String Titulo;
    private String Descricao;
    private long _identificadorCategoria;

    // endregion  [ Propriedades ]

    // region [ Construtor ]

    public Topico()
    {

    }

    // endregion

    // region [ Métodos Get/Set ]

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public long get_identificadorCategoria() {
        return _identificadorCategoria;
    }

    public void set_identificadorCategoria(long _identificadorCategoria) {
        this._identificadorCategoria = _identificadorCategoria;
    }

    // endregion [ Métodos Get/Set ]
}
