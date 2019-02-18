package com.efficom.Beans;

import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class ValidatorBean {
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public void validateEmail(FacesContext facesContext, UIComponent uiComponent, Object value) {
        String text = (String) value;
        if(text == null || text.length() <= 0 || !VALID_EMAIL_ADDRESS_REGEX .matcher(text).find()) {
        	FacesMessage facesMessage = new FacesMessage("L'addresse n'est pas valide.");
            throw new ValidatorException(facesMessage);
        }
    }
	
	public void validateQuantity(FacesContext facesContext, UIComponent uiComponent, Object value) {
		int quantity = (Integer) value;
		
		if(quantity <= 0) {
        	FacesMessage facesMessage = new FacesMessage("Quantité incorrecte");
            throw new ValidatorException(facesMessage);
        }
	}
}
