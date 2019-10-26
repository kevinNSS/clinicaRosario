package clinicaRosario.controller;

import clinicaRosario.entity.TblInventario;
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
@Named("tblInventarioController")
@ViewScoped
public class TblInventarioController implements Serializable {
    
    /**
     * 
     * 
     * AQUI VAN LOS METODOS
     * 
     * 
     */

    @FacesConverter(forClass = TblInventario.class)
    public static class TblExamenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblExamenesController controller = (TblExamenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblInventarioController");
            return controller.getTblExamenes(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TblInventario) {
                TblInventario o = (TblInventario) object;
                return getStringKey(o.getIdInventario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblInventario.class.getName());
            }
        }
    }
}
