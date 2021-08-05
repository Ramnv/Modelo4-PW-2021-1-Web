/*
https://drive.google.com/file/d/1vSD1yiWLl6PNgDklVKdICxKQhbtmEDXz/view
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo04.Idioma;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author rvelasco
 */
@Stateful
public class IdiomaDAO<TIPO> extends DAOGenerico<Idioma> implements Serializable {

    public IdiomaDAO() {
        super();
        classePersistente = Idioma.class;
    }

}
