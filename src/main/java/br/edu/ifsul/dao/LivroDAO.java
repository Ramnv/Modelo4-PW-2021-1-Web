/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo04.Livro;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author rvelasco
 */
@Stateful
public class LivroDAO<TIPO> extends DAOGenerico<Livro> implements Serializable {

    public LivroDAO() {
        super();
        classePersistente = Livro.class;
        listaOrdem.add(new Ordem("isbn", "ISBN", "="));
        listaOrdem.add(new Ordem("titulo", "Titulo", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // vai pegar o segundo da lista de ordens
        // criando uma instancia do conversor
        converterOrdem = new ConverterOrdem();
        // associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }

    @Override
    public Livro localizar(Object id) throws Exception {
        Livro objeto = em.find(Livro.class, id);
        // Deve-se inicializar a coleção ou coleçoes do objeto para não
        // dar um erro de lazy inicialization exception
        objeto.getAutores().size();
        return objeto;
    }

    public List<Livro> getListaCompleta() {
        String jpql = "select distinct t from Livro t left join fetch t.autores order by t.id";
        return em.createQuery(jpql).getResultList();
    }

}
