package jsf;

import jpa.entities.Tac;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.TacFacade;

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

@Named("tacController")
@SessionScoped
public class TacController implements Serializable {

    @EJB
    jpa.session.TacFacade ejbFacade;
    //jpa.session.TacFacade mtl;
    private List<Tac> items = null;
    private Tac selected;
    private Tac tacselected;
    private List<Tac> listafiltac;

    private List<Tac> listatac;
    private String auto;


    
      public void gettac (int idtac){
listatac=ejbFacade.listaTAC(idtac);//aqui llamo al metodo de la consulta y el valo que regresa se lo asigno a la lista
tacselected=ejbFacade.tacselec(idtac);
System.out.println("Id tac: "+ idtac);

} 

    public List<Tac> getListafiltac() {
        return listafiltac;
    }

    public void setListafiltac(List<Tac> listafiltac) {
        this.listafiltac = listafiltac;
    }
    
    
    public List<Tac> autocomplete(String patron){
    
    return ejbFacade.newquery(patron);
    }
   /* public List<Tac> autocomplete(String patron){
    System.out.println("sigo funcionando autoComplete METODO");
        if (patron.matches("^[a-zA-Z0]*$")) {
            System.out.println("busqueda por nombre");
            listafiltac = ejbFacade.autoQueryName(patron);
        } else {
            System.out.println("busqueda por numero de parte");
            
        }
    
   
    return listafiltac;
    }*/
      
    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }
    
    
    
    public List<Tac> getListatac() {
        return listatac;
    }

    public void setListatac(List<Tac> listatac) {
        this.listatac = listatac;
    }

    public Tac getTacselected() {
        return tacselected;
    }

    public void setTacselected(Tac tacselected) {
        this.tacselected = tacselected;
    }
    
    
    
    
    public TacController() {
    }

    public Tac getSelected() {
        return selected;
    }

    public void setSelected(Tac selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TacFacade getFacade() {
        return ejbFacade;
    }

    public Tac prepareCreate() {
        selected = new Tac();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("TacUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("TacDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tac> getItems() {
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

    public Tac getTac(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tac> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tac> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tac.class)
    public static class TacControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TacController controller = (TacController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tacController");
            return controller.getTac(getKey(value));
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
            if (object instanceof Tac) {
                Tac o = (Tac) object;
                return getStringKey(o.getIdtac());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tac.class.getName()});
                return null;
            }
        }

    }

   public void getidtac (int idtac){
listatac=ejbFacade.listaTAC(idtac);//aqui llamo al metodo de la consulta y el valo que regresa se lo asigno a la lista
tacselected=ejbFacade.tacselec(idtac);
System.out.println("Id tac: "+ idtac);

} 
  
}