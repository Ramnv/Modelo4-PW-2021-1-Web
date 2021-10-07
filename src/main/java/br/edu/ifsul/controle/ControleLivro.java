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
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
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
    private Boolean novo;
    @EJB
    private LivroBasicoDAO<LivroBasico> daoLivroBasico;
    @EJB
    private IdiomaDAO<Idioma> daoIdioma;

    @EJB
    private FormatoDAO<Formato> daoFormato;

    @EJB
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private int abaAtiva;

    public ControleLivro() {

    }

    public void removerAutor(Autor obj) {
        objeto.getAutores().remove(obj);
        Util.mensagemInformacao("Autor removida com sucesso!");
    }

    public void adicionarAutor() {
        if (!objeto.getAutores().contains(autor)) {
            if (autor != null) {
                objeto.getAutores().add(autor);
                Util.mensagemInformacao("Autor adicionada com sucesso!");
            } else {
                Util.mensagemErro("Selecione a autor");
            }
        } else {
            Util.mensagemErro("Livro já possui esta autor");
        }
    }

    public void verificarUnicidadeISBN() {
        if (novo) {
            try {
                if (!dao.verificaUnicidadeISBN(objeto.getISBN())) {
                    Util.mensagemErro("Nome de livro '" + objeto.getISBN() + "' "
                            + " já existe no banco de dados!");
                    objeto.setISBN(null);
                    // capturar o componente que chamou o método
                    UIComponent comp
                            = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
                    if (comp != null) {
                        // deixar em vermelho após o update
                        UIInput input = (UIInput) comp;
                        input.setValid(false);
                    }
                }
            } catch (Exception e) {
                Util.mensagemErro("Erro do sistema:" + Util.getMensagemErro(e));
            }
        }
    }

    public void imprimeLivros() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAutor", parametros, dao.getListaCompleta());
    }

    public void imprimeLivro(Object id) {
        try {
            objeto = dao.localizar(id);
            List<Livro> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioLivro", parametros, lista);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
        }
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

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}
