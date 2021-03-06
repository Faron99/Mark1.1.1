package jsf;

import jpa.entities.Pm;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.PmFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jpa.entities.Tac;

 @Named("pmController")
@SessionScoped
public class PmController implements Serializable {

    @EJB
    jpa.session.PmFacade ejbFacade;
    jpa.session.TacFacade ejbFacadetac;
    private List<Pm> items = null;
    private Pm selected;
    private Pm lpm;
    private List<Tac> listatac;

    
    public void atrapatacid(int tac){
    
        listatac= ejbFacadetac.nametacpm(tac);
        
    
    
    }
    
    
    
    
    
    
    
    public List<Tac> getListatac() {
        return listatac;
    }

    public void setListatac(List<Tac> listatac) {
        this.listatac = listatac;
    }

    public Pm getLpm() {
        return lpm;
    }

    public void setLpm(Pm lpm) {
        this.lpm = lpm;
    }
    
    
    
    private List<Pm> listapm;

    public List<Pm> getListapm() {
        return listapm;
    }

    public void setListapm(List<Pm> listapm) {
        this.listapm = listapm;
    }
    
    
    public void recibepm(Pm pm){
    lpm=pm;
        System.out.println("pm: "+ pm);
    
    }
    
   /* public void recibepm(int pm){
    listapm=ejbFacade.listapm(pm);
        System.out.println("pm: "+ pm);
    
    }*/
    

    public PmController() {
    }

    public Pm getSelected() {
        return selected;
    }

    public void setSelected(Pm selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PmFacade getFacade() {
        return ejbFacade;
    }

    public Pm prepareCreate() {
        selected = new Pm();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("PmCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("PmUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("PmDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pm> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Pm getPm(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pm> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pm> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pm.class)
    public static class PmControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PmController controller = (PmController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pmController");
            return controller.getPm(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pm) {
                Pm o = (Pm) object;
                return getStringKey(o.getIdpm());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pm.class.getName()});
                return null;
            }
        }

    }

}
