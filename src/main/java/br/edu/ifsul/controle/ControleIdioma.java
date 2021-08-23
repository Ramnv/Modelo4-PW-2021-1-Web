package br.edu.ifsul.controle;

import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.modelo04.Idioma;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rvelasco
 */
@Named(value = "controleIdioma")
@ViewScoped
public class ControleIdioma implements Serializable {

    @EJB
    private IdiomaDAO<Idioma> dao;
    private Idioma objeto;

    public ControleIdioma() {

    }

    public String listar() {
        return "/privado/idioma/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Idioma();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public IdiomaDAO<Idioma> getDao() {
        return dao;
    }

    public void setDao(IdiomaDAO<Idioma> dao) {
        this.dao = dao;
    }

    public Idioma getObjeto() {
        return objeto;
    }

    public void setObjeto(Idioma objeto) {
        this.objeto = objeto;
    }

}
