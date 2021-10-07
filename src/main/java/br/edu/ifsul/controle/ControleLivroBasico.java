package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LivroBasicoDAO;
import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.modelo04.LivroBasico;
import br.edu.ifsul.modelo04.Autor;
import br.edu.ifsul.modelo04.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
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
public class ControleLivroBasico implements Serializable {

    @EJB
    private LivroBasicoDAO<LivroBasico> dao;
    private LivroBasico objeto;
    private Boolean novo;
    @EJB
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private int abaAtiva;

    public ControleLivroBasico() {

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

    public String listar() {
        return "/privado/livrobasico/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
        novo = true;
        abaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);
            novo = false;
            abaAtiva = 0;
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (novo) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }

    public LivroBasicoDAO<LivroBasico> getDao() {
        return dao;
    }

    public void setDao(LivroBasicoDAO<LivroBasico> dao) {
        this.dao = dao;
    }

    public LivroBasico getObjeto() {
        return objeto;
    }

    public void setObjeto(LivroBasico objeto) {
        this.objeto = objeto;
    }

    public Boolean getNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
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
