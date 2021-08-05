/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

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
    }

}
