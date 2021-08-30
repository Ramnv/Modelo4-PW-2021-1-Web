package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.modelo04.Catalogo;
import br.edu.ifsul.modelo04.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rvelasco
 */
@Named(value = "controleCatalogo")
@ViewScoped
public class ControleCatalogo implements Serializable {

    @EJB
    private CatalogoDAO<Catalogo> dao;
    private Catalogo objeto;

    private Livro livro;

    public ControleCatalogo() {

    }

    public String listar() {
        return "/privado/catalogo/listar?faces-redirect=true";
    }

    public void novoLivro() {
        livro = new Livro();
    }

    public void alterarLivro(int index) {
        livro = objeto.getLivros().get(index);
    }

    public void salvarLivro() {
        objeto.adicionarLivro(livro);
        Util.mensagemInformacao("Livro adicionado ou atualizado com sucesso");
    }

    public void removerLivro(int index) {
        objeto.removerLivro(index);
        Util.mensagemInformacao("Livro removido com sucesso!");
    }

    public void novo() {
        objeto = new Catalogo();
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

    public CatalogoDAO<Catalogo> getDao() {
        return dao;
    }

    public void setDao(CatalogoDAO<Catalogo> dao) {
        this.dao = dao;
    }

    public Catalogo getObjeto() {
        return objeto;
    }

    public void setObjeto(Catalogo objeto) {
        this.objeto = objeto;
    }

}
