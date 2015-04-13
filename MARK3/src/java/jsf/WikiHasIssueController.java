package jsf;

import jpa.entities.WikiHasIssue;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.WikiHasIssueFacade;

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

@Named("wikiHasIssueController")
@SessionScoped
public class WikiHasIssueController implements Serializable {

    @EJB
    private jpa.session.WikiHasIssueFacade ejbFacade;
    private List<WikiHasIssue> items = null;
    private WikiHasIssue selected;

    public WikiHasIssueController() {
    }

    public WikiHasIssue getSelected() {
        return selected;
    }

    public void setSelected(WikiHasIssue selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getWikiHasIssuePK().setIssueIdissue(selected.getTac().getIdtac());
        selected.getWikiHasIssuePK().setWikiidWiki(selected.getWiki().getIdWiki());
    }

    protected void initializeEmbeddableKey() {
        selected.setWikiHasIssuePK(new jpa.entities.WikiHasIssuePK());
    }

    private WikiHasIssueFacade getFacade() {
        return ejbFacade;
    }

    public WikiHasIssue prepareCreate() {
        selected = new WikiHasIssue();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiHasIssueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiHasIssueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiHasIssueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<WikiHasIssue> getItems() {
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

    public WikiHasIssue getWikiHasIssue(jpa.entities.WikiHasIssuePK id) {
        return getFacade().find(id);
    }

    public List<WikiHasIssue> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<WikiHasIssue> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = WikiHasIssue.class)
    public static class WikiHasIssueControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WikiHasIssueController controller = (WikiHasIssueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "wikiHasIssueController");
            return controller.getWikiHasIssue(getKey(value));
        }

        jpa.entities.WikiHasIssuePK getKey(String value) {
            jpa.entities.WikiHasIssuePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.WikiHasIssuePK();
            key.setWikiidWiki(Integer.parseInt(values[0]));
            key.setIssueIdissue(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.WikiHasIssuePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getWikiidWiki());
            sb.append(SEPARATOR);
            sb.append(value.getIssueIdissue());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof WikiHasIssue) {
                WikiHasIssue o = (WikiHasIssue) object;
                return getStringKey(o.getWikiHasIssuePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), WikiHasIssue.class.getName()});
                return null;
            }
        }

    }

}
