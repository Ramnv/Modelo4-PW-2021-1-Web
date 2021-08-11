package br.edu.ifsul.converters;

import br.edu.ifsul.modelo04.Formato;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterFormato")
@RequestScoped
public class ConverterFormato implements Serializable, Converter {

    @PersistenceContext(unitName = "Modelo04-PW-2021-Web")
    protected EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        return em.find(Formato.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null) {
            return null;
        }
        Formato obj = (Formato) t;
        return obj.getId().toString();
    }

}
