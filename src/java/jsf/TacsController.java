package jsf;

import jpa.entities.Tacs;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.TacsFacade;

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

@Named("tacsController")
@SessionScoped
public class TacsController implements Serializable {

    @EJB
    private jpa.session.TacsFacade ejbFacade;
    private List<Tacs> items = null;
    private Tacs selected;

    public TacsController() {
    }

    public Tacs getSelected() {
        return selected;
    }

    public void setSelected(Tacs selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getTacsPK().setGroupCoreTeamidGroup(selected.getGroupCoreTeam().getIdGroup());
    }

    protected void initializeEmbeddableKey() {
        selected.setTacsPK(new jpa.entities.TacsPK());
    }

    private TacsFacade getFacade() {
        return ejbFacade;
    }

    public Tacs prepareCreate() {
        selected = new Tacs();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("TacsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tacs> getItems() {
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

    public Tacs getTacs(jpa.entities.TacsPK id) {
        return getFacade().find(id);
    }

    public List<Tacs> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tacs> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tacs.class)
    public static class TacsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TacsController controller = (TacsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tacsController");
            return controller.getTacs(getKey(value));
        }

        jpa.entities.TacsPK getKey(String value) {
            jpa.entities.TacsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.TacsPK();
            key.setIdTACS(Integer.parseInt(values[0]));
            key.setName(values[1]);
            key.setGroupCoreTeamidGroup(values[2]);
            return key;
        }

        String getStringKey(jpa.entities.TacsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdTACS());
            sb.append(SEPARATOR);
            sb.append(value.getName());
            sb.append(SEPARATOR);
            sb.append(value.getGroupCoreTeamidGroup());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Tacs) {
                Tacs o = (Tacs) object;
                return getStringKey(o.getTacsPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tacs.class.getName()});
                return null;
            }
        }

    }

}
