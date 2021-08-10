package br.edu.ifsul.dao;

import br.edu.ifsul.modelo04.Formato;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author rvelasco
 */
@Stateful
public class FormatoDAO<TIPO> extends DAOGenerico<Formato> implements Serializable {

    public FormatoDAO() {
        super();
        classePersistente = Formato.class;
    }

}
