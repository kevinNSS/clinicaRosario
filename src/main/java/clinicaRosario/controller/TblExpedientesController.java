/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.controller;

import clinicaRosario.entity.TblExpedientes;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("tblExpedientesController")
@ViewScoped
public class TblExpedientesController implements Serializable {

    /**
     *
     *
     * AQUI VAN LOS METODOS
     *
     *
     */
    
    
    @FacesConverter(forClass = TblExpedientes.class)
    public static class TblTipoExamenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblTipoExamenesController controller = (TblTipoExamenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblExpedientesController");
            return controller.getTblTipoExamenes(getKey(value));
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
            if (object instanceof TblExpedientes) {
                TblExpedientes o = (TblExpedientes) object;
                return getStringKey(o.getIdExpediente());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblExpedientes.class.getName());
            }
        }

    }
}