package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
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
@Named("tblEmpleadosController")
@ViewScoped
public class TblEmpleadosController implements Serializable {

    /**
     *
     *
     * AQUI VAN LOS METODOS
     *
     *
     */
    @FacesConverter(forClass = TblEmpleados.class)
    public static class TblExamenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblExamenesController controller = (TblExamenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblEmpleadosController");
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
            if (object instanceof TblEmpleados) {
                TblEmpleados o = (TblEmpleados) object;
                return getStringKey(o.getIdEmpleado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblEmpleados.class.getName());
            }
        }

    }
}
