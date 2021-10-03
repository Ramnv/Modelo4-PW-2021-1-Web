/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo04.Livraria;
import java.io.Serializable;
import java.util.List;
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
        listaOrdem.add(new Ordem("site", "Site", "like"));
        // site?
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }

    @Override
    public Livraria localizar(Object id) throws Exception {
        Livraria objeto = em.find(Livraria.class, id);
        // Deve-se inicializar a coleção ou coleçoes do objeto para não
        // dar um erro de lazy inicialization exception
        objeto.getCatalogos().size();
        return objeto;
    }

    public List<Livraria> getListaCompleta() {
        String jpql = "select distinct t from Livraria t left join fetch t.catalogos order by t.id";
        return em.createQuery(jpql).getResultList();
    }

}
