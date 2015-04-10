/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {
     
    private Map<String,Map<String,String>> data = new HashMap<>();
    private String country; 
    private String city;  
    private Map<String,String> countries;
    private Map<String,String> cities;
     
    @PostConstruct
    public void init() {
        countries  = new HashMap<>();
        countries.put("Audio", "Audio");
        countries.put("BackBeat", "BackBeat");
        countries.put("Blackwire", "Blackwire");
        countries.put("Calisto", "Calisto");
        countries.put("Encore", "Encore");
        countries.put("Gamecom", "Gamecom");
        countries.put("Savi", "Savi");
        countries.put("Voyager", "Voyager");


         
        Map<String,String> map = new HashMap<>();
        map.put("310", "310");
        map.put("355", "995");
        map.put("995", "995");
        data.put("Audio", map);
         
        
        map = new HashMap<>();
        map.put("fit", "fit");
        map.put("fit plus", "fit plus");
        map.put("go", "go");
        map.put("go2", "go2");
        data.put("BackBeat", map);
         
        map = new HashMap<>();
        map.put("c300", "c300");
        map.put("c500", "c500");
        map.put("c700", "c700");
        map.put("420", "420");
        map.put("435", "435");
        map.put("720", "720");
        data.put("Blackwire", map);
        
        map = new HashMap<>();
        map.put("620", "620");
        map.put("800", "800");
        map.put("P620", "P620");
        data.put("Calisto", map);
               
        map = new HashMap<>();
        map.put("pro", "pro");     
        data.put("Encore", map);
         
        map = new HashMap<>();
        map.put("307", "307");
        map.put("308", "308");
        map.put("318", "318");
        map.put("378", "378");
        map.put("Commander", "Commander");
        map.put("Gamma", "Gamma");
        data.put("Gamecom", map);
        
        map = new HashMap<>();
        map.put("445", "310");
        map.put("720", "995");  
        data.put("Savi", map);
         
        
        map = new HashMap<>();
        map.put("edge", "edge");
        map.put("legend", "legend");
        map.put("pro", "pro");     
        data.put("Voyager", map);
    }
 
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public Map<String, String> getCountries() {
        return countries;
    }
 
    public Map<String, String> getCities() {
        return cities;
    }
 
    public void onCountryChange() {
        if(country !=null && !country.equals(""))
            cities = data.get(country);
        else
            cities = new HashMap<>();
    }
     
    public void displayLocation() {
        FacesMessage msg;
        if(city != null && country != null)
            msg = new FacesMessage("Selected", city + " of " + country);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
}