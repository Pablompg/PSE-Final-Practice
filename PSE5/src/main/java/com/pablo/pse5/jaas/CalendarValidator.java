
package com.pablo.pse5.jaas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("calendarValidator")
public class CalendarValidator implements Validator{
    
    private LocalDateTime currentDate = LocalDateTime.now();
    private LocalDateTime fechaIntroducidaFormateada;
    private Date fechaIntroducida;
    private long years;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null){
            return;
        }
        fechaIntroducida = (Date) value;
        fechaIntroducidaFormateada = LocalDateTime.ofInstant(fechaIntroducida.toInstant(), ZoneId.systemDefault());
        LocalDateTime temp = LocalDateTime.from(fechaIntroducidaFormateada);
        years = temp.until(currentDate, ChronoUnit.YEARS);
        if(years>65){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación","No se admiten usuarios mayores de 65 años"));
        }
        else if(years<16){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación","No se admiten usuarios menores de 18 años"));
        }
    }
}
