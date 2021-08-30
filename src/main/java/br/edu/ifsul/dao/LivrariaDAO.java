/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo04.Livraria;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author rvelasco
 */
@Stateful
public class LivrariaDAO<TIPO> extends DAOGenerico<Livraria> implements Serializable {

    public LivrariaDAO() {
        super();
        classePersistente = Livraria.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // site?
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
