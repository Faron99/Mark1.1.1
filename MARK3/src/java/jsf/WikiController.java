package jsf;

import jpa.entities.Wiki;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.WikiFacade;

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
import jpa.entities.WikiHasIssue;
import jpa.session.WikiHasIssueFacade;

@Named("wikiController")
@SessionScoped
public class WikiController implements Serializable {

    @EJB
    jpa.session.WikiFacade ejbFacade;
    @EJB
    jpa.session.WikiHasIssueFacade wikifaca;
    private List<Wiki> items = null;
    private Wiki selected;
    List<Wiki> listaWiki;
    String datowiki;
    Wiki bus;
    private Wiki autowiki;
    private List<Wiki> autolistwiki;
    private List<WikiHasIssue> getT;
    

    
    public void getTAC(int a){
    
        getT= wikifaca.lista(a);
        System.out.println("producto seleccionado: "+a);
    
    
    }
    
    
     public void buscarWiki(Wiki w){
    
    bus=w;
        System.out.println("w seleccionado: " +w);
    
    }
     
     public List<Wiki>autocomplete2(String dato){
      System.out.println("sigo funcionando autoComplete METODO");
      
            autolistwiki = ejbFacade.autoQueryName2(dato);
       
        return autolistwiki;
         
     }


     
    public WikiFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(WikiFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public WikiHasIssueFacade getWikifaca() {
        return wikifaca;
    }

    public void setWikifaca(WikiHasIssueFacade wikifaca) {
        this.wikifaca = wikifaca;
    }

    public List<Wiki> getAutolistwiki() {
        return autolistwiki;
    }

    /*   public List<Wiki>autocomplete(String dato){
    System.out.println("sigo funcionando autoComplete METODO");
    if (dato.matches("^[a-zA-Z0]*$")) {
    System.out.println("busqueda por nombre");
    autolistwiki = ejbFacade.autoQueryName(dato);
    } else {
    System.out.println("busqueda por numero de parte");
    dato = ejbFacade.autoQueryPartNumber(dato);
    }
    // System.out.println(patron);
    return autolistwiki;
    }*/
    public void setAutolistwiki(List<Wiki> autolistwiki) {
        this.autolistwiki = autolistwiki;
    }

    public List<WikiHasIssue> getGetT() {
        return getT;
    }

    public void setGetT(List<WikiHasIssue> getT) {
        this.getT = getT;
    }

     
     
    public List<Wiki> getAutolistrwiki() {
        return autolistwiki;
    }

    public void setAutolistrwiki(List<Wiki> autolistrwiki) {
        this.autolistwiki = autolistrwiki;
    }
     
     
     
     
    public Wiki getBus() {
        return bus;
    }

    public void setBus(Wiki bus) {
        this.bus = bus;
    }

    public Wiki getAutowiki() {
        return autowiki;
    }

    public void setAutowiki(Wiki autowiki) {
        this.autowiki = autowiki;
    }
    

    public String getDatowiki() {
        return datowiki;
    }

    public void setDatowiki(String datowiki) {
        this.datowiki = datowiki;
    }
   

    
    

    public List<Wiki> getListaWiki() {
        return listaWiki;
    }

    public void setListaWiki(List<Wiki> listaWiki) {
        this.listaWiki = listaWiki;
    }
    
    
    
    public WikiController() {
    }

    public Wiki getSelected() {
        return selected;
    }

    public void setSelected(Wiki selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WikiFacade getFacade() {
        return ejbFacade;
    }

    public Wiki prepareCreate() {
        selected = new Wiki();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("WikiDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Wiki> getItems() {
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

    public Wiki getWiki(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Wiki> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Wiki> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Wiki.class)
    public static class WikiControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WikiController controller = (WikiController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "wikiController");
            return controller.getWiki(getKey(value));
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
            if (object instanceof Wiki) {
                Wiki o = (Wiki) object;
                return getStringKey(o.getIdWiki());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Wiki.class.getName()});
                return null;
            }
        }

    }

    
   
    
     public void buscarWiki2(int idWiki){
    
    datowiki=ejbFacade.Descripion(idWiki);
        System.out.println("Id seleccionado: " +idWiki);
    
    }
    
   
}
