package jsf;

import jpa.entities.Issue;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.IssueFacade;

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
import jpa.entities.CoreTeam;
import jpa.session.CoreTeamFacade;


@Named("issueController")
@SessionScoped
public class IssueController implements Serializable {

    @EJB
     jpa.session.IssueFacade ejbFacade;
     jpa.session.CoreTeamFacade ejbFacadect;
    private List<Issue> items = null;
    private Issue selected;
    private Issue listi;
    private List<Issue> listaissue;
    private List<CoreTeam> listact;
    private List<Issue> listaissuetabla;
    private List<Issue> listafilter;
    private Issue auto;
    private List<Issue> autolist;
    
    
    
    public List<Issue> autocomplete(String dato){
    
    return autolist=ejbFacade.autoQueryName(dato);
    }

    public Issue getAuto() {
        return auto;
    }

    public void setAuto(Issue auto) {
        this.auto = auto;
    }

    public List<Issue> getAutolist() {
        return autolist;
    }

    public void setAutolist(List<Issue> autolist) {
        this.autolist = autolist;
    }
    
    
    
    
   
    
    public void atrapapm(int idTACtabla){
    
listaissuetabla=ejbFacade.listaissues(idTACtabla);//aqui llamo al metodo de la consulta y el valo que regresa se lo asigno a la lista

System.out.println("Id tac: "+ idTACtabla);

}

    public List<Issue> getListafilter() {
        return listafilter;
    }

    public void setListafilter(List<Issue> listafilter) {
        this.listafilter = listafilter;
    }

    public Issue getListi() {
        return listi;
    }

    public void setListi(Issue listi) {
        this.listi = listi;
    }

    
    
   

    
    
    
    public IssueFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(IssueFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public CoreTeamFacade getEjbFacadect() {
        return ejbFacadect;
    }

    public void setEjbFacadect(CoreTeamFacade ejbFacadect) {
        this.ejbFacadect = ejbFacadect;
    }

    public List<Issue> getListaissuetabla() {
        return listaissuetabla;
    }

    public void setListaissuetabla(List<Issue> listaissuetabla) {
        this.listaissuetabla = listaissuetabla;
    }
    
    
    
    
    public List<CoreTeam> getListact() {
        return listact;
    }

    public void setListact(List<CoreTeam> listact) {
        this.listact = listact;
    }

    public List<Issue> getListaissue() {
      
        return listaissue;
    }

    public void setListaissue(List<Issue> listaTac) {
        this.listaissue = listaTac;
    }

    public IssueController() {
    }

    public Issue getSelected() {
        return selected;
    }

    public void setSelected(Issue selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IssueFacade getFacade() {
        return ejbFacade;
    }

    public Issue prepareCreate() {
        selected = new Issue();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("IssueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("IssueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("IssueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Issue> getItems() {
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

    public Issue getIssue(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Issue> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Issue> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Issue.class)
    public static class IssueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IssueController controller = (IssueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "issueController");
            return controller.getIssue(getKey(value));
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
            if (object instanceof Issue) {
                Issue o = (Issue) object;
                return getStringKey(o.getIdissue());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Issue.class.getName()});
                return null;
            }
        }

    }
public void atrapaTAC(int idTAC){
    
listaissue=ejbFacade.listaissues(idTAC);//aqui llamo al metodo de la consulta y el valo que regresa se lo asigno a la lista
listact=ejbFacadect.listact(idTAC);
System.out.println("Id tac: "+ idTAC);

}
}
