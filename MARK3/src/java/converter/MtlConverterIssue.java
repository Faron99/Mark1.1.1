/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import jsf.IssueController;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import jpa.entities.Issue;


/**
 *
 * @author NORE
 */
@FacesConverter("mtlissue")
public class MtlConverterIssue implements Converter {

    @Inject
    IssueController nm;
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        }
        try {
            String noParte = value;
            List<Issue> tags = nm.getAutolist();
            for (Issue t : tags) {
                if (t.getDescription().equals(noParte)) {
                    return t;
                }
            }
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Error", null));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            Issue mt = (Issue) value;
            return mt.getDescription();
        }
    }

}
