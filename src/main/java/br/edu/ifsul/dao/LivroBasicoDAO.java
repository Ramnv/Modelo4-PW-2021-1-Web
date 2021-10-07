/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo04.Livro;
import br.edu.ifsul.modelo04.LivroBasico;
import br.edu.ifsul.modelo04.LivroBasico;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author rvelasco
 */
@Stateful
public class LivroBasicoDAO<TIPO> extends DAOGenerico<LivroBasico> implements Serializable {

    public LivroBasicoDAO() {
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
    public LivroBasico localizar(Object id) throws Exception {
        LivroBasico obj = em.find(LivroBasico.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getAutores().size();
        return obj;
    }

    public boolean verificaUnicidadeISBN(String ISBN) throws Exception {
        String jpql = "from LivroBasico where ISBN = :pISBN";
        Query query = em.createQuery(jpql);
        query.setParameter("pISBN", ISBN);
        if (query.getResultList().size() > 0) {
            return false;
        } else {
            return true;
        }
    }

}
