package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo04.Catalogo;
import br.edu.ifsul.modelo04.Formato;
import br.edu.ifsul.modelo04.Idioma;
import br.edu.ifsul.modelo04.Livraria;
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
    @EJB
    private IdiomaDAO<Idioma> daoIdioma;
    @EJB
    private FormatoDAO<Formato> daoFormato;
    @EJB
    private LivroDAO<Livro> daoLivro;
    @EJB
    private LivrariaDAO<Livraria> daoLivraria;

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

    public LivroDAO<Livro> getDaoLivro() {
        return daoLivro;
    }

    public void setDaoLivro(LivroDAO<Livro> daoLivro) {
        this.daoLivro = daoLivro;
    }

    public LivrariaDAO<Livraria> getDaoLivraria() {
        return daoLivraria;
    }

    public void setDaoLivraria(LivrariaDAO<Livraria> daoLivraria) {
        this.daoLivraria = daoLivraria;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
    }

}
