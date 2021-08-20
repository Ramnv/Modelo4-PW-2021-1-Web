package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
