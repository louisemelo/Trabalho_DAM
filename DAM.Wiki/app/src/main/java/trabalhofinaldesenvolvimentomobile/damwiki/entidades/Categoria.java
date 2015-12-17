package trabalhofinaldesenvolvimentomobile.damwiki.entidades;

/**
 * Created by Louise on 13/12/2015.
 */
public class Categoria {

    // region [ Propriedades ]

    private long _id;
    private String Descricao;

    // endregion [ Propriedades ]

    // region [ Construtor ]

    public Categoria()
    {

    }

    // endregion [ Construtor ]

    // region [ Métodos Get/Set ]
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    // endregion [ Métodos Get/Set ]
}
