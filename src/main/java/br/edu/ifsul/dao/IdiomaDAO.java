/*

 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
