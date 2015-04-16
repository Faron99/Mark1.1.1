package jsf;

import jpa.entities.CoreTeam;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.CoreTeamFacade;

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
import jpa.entities.Issue;
import jpa.session.IssueFacade;

@Named("coreTeamController")
@SessionScoped
public class CoreTeamController implements Serializable {

   @EJB
   jpa.session.CoreTeamFacade ejbFacade;
    @EJB jpa.session.IssueFacade ejbFacadeissue;
    
    private List<CoreTeam> items = null;
    private CoreTeam selected;

    private List<CoreTeam> listact;
    private List<Issue> listaissue;
    

  
      
      
    

    public CoreTeamFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CoreTeamFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public IssueFacade getEjbFacadeissue() {
        return ejbFacadeissue;
    }

    public void setEjbFacadeissue(IssueFacade ejbFacadeissue) {
        this.ejbFacadeissue = ejbFacadeissue;
    }

    public List<Issue> getListaissue() {
        return listaissue;
    }

    public void setListaissue(List<Issue> listaissue) {
        this.listaissue = listaissue;
    }

    
    
    
    public List<CoreTeam> getListact() {
        return listact;
    }

    public void setListact(List<CoreTeam> listact) {
        this.listact = listact;
    }
    
    
    public CoreTeamController() {
    }

    public CoreTeam getSelected() {
        return selected;
    }

    public void setSelected(CoreTeam selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CoreTeamFacade getFacade() {
        return ejbFacade;
    }

    public CoreTeam prepareCreate() {
        selected = new CoreTeam();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("CoreTeamCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("CoreTeamUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("CoreTeamDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CoreTeam> getItems() {
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

    public CoreTeam getCoreTeam(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CoreTeam> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CoreTeam> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CoreTeam.class)
    public static class CoreTeamControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CoreTeamController controller = (CoreTeamController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "coreTeamController");
            return controller.getCoreTeam(getKey(value));
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
            if (object instanceof CoreTeam) {
                CoreTeam o = (CoreTeam) object;
                return getStringKey(o.getIdcoreTeam());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CoreTeam.class.getName()});
                return null;
            }
        }

    }
public void atrapa (int ct){
listaCt(ct);
addct(ct);
}

public void listaCt(int ct){
listact=ejbFacade.listact(ct);
System.out.println("CT: "+ct);
}

public void addct(int ct){
listaissue=ejbFacadeissue.listaissues(ct);
System.out.println("issue: "+ct);
}



}
