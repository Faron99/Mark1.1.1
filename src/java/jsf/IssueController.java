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

@Named("issueController")
@SessionScoped
public class IssueController implements Serializable {

    @EJB
    private jpa.session.IssueFacade ejbFacade;
    private List<Issue> items = null;
    private Issue selected;

    public IssueController() {
    }

    public Issue getSelected() {
        return selected;
    }

    public void setSelected(Issue selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getIssuePK().setGroupCoreTeamidGroup(selected.getGroupCoreTeam().getIdGroup());
    }

    protected void initializeEmbeddableKey() {
        selected.setIssuePK(new jpa.entities.IssuePK());
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

    public Issue getIssue(jpa.entities.IssuePK id) {
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

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IssueController controller = (IssueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "issueController");
            return controller.getIssue(getKey(value));
        }

        jpa.entities.IssuePK getKey(String value) {
            jpa.entities.IssuePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.IssuePK();
            key.setIdIssue(Integer.parseInt(values[0]));
            key.setGroupCoreTeamidGroup(values[1]);
            return key;
        }

        String getStringKey(jpa.entities.IssuePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdIssue());
            sb.append(SEPARATOR);
            sb.append(value.getGroupCoreTeamidGroup());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Issue) {
                Issue o = (Issue) object;
                return getStringKey(o.getIssuePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Issue.class.getName()});
                return null;
            }
        }

    }

}
