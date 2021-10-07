package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroBasicoDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo04.Autor;
import br.edu.ifsul.modelo04.Catalogo;
import br.edu.ifsul.modelo04.Formato;
import br.edu.ifsul.modelo04.Idioma;
import br.edu.ifsul.modelo04.Livro;
import br.edu.ifsul.modelo04.LivroBasico;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rvelasco
 */
@Named(value = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable {

    @EJB
    private LivroDAO<Livro> dao;
    private Livro objeto;
    @EJB
    private LivroBasicoDAO<LivroBasico> daoLivroBasico;
    @EJB
    private IdiomaDAO<Idioma> daoIdioma;
    @EJB
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private Boolean novoAutor;
    @EJB
    private FormatoDAO<Formato> daoFormato;

    public ControleLivro() {

    }

    public void imprimeLivros() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAutor", parametros, dao.getListaCompleta());
    }

    public void imprimeAutor(Object id) {
        try {
            objeto = dao.localizar(id);
            List<Livro> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioAutor", parametros, lista);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
        }
    }

    public void novoAutor() {
        novoAutor = true;
        autor = new Autor();
    }

    public void alterarAutor(int index) {
        autor = objeto.getAutores().get(index);
        novoAutor = false;
    }

    public void salvarAutor() {
        if (novoAutor) {
            objeto.adicionarAutor(autor);
        }
        Util.mensagemInformacao("Autor adicionado ou atualizado com sucesso");
    }

    public void removerAutor(int index) {
        objeto.removerAutor(index);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }

    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
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
            if (objeto.getISBN() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
    }

    public LivroBasicoDAO<LivroBasico> getDaoLivroBasico() {
        return daoLivroBasico;
    }

    public void setDaoLivroBasico(LivroBasicoDAO<LivroBasico> daoLivroBasico) {
        this.daoLivroBasico = daoLivroBasico;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setNovoAutor(Boolean novoAutor) {
        this.novoAutor = novoAutor;
    }

}
