package clinicaRosario.controller;

import clinicaRosario.entity.TblIngresoInventario;
import clinicaRosario.session.TblIngresoInventarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
@Named("tblIngresoInventarioController")
@ViewScoped
public class TblIngresoInventarioController implements Serializable {

    private TblIngresoInventario tblIngresoInventario;
    @EJB
    private TblIngresoInventarioFacade tblIngresoInventarioFacade;
    private Boolean mostrarTblIngresoInventario = true;
    private Boolean mostrarFormIngresoInventario = false;

    public void init() {
        tblIngresoInventario = new TblIngresoInventario();
    }

    public void mostrarTablaIngresoInventari() {
        mostrarTblIngresoInventario = true;
        mostrarFormIngresoInventario = false;
    }

    public void mostrarFormIngresoInventario() {
        mostrarTblIngresoInventario = false;
        mostrarFormIngresoInventario = true;
    }

    public void leerDetalleInventario(TblIngresoInventario infoDetalleInventario) {
        tblIngresoInventario = infoDetalleInventario;
    }
    
    public void nuevoDetalleInventario(){
        tblIngresoInventario = new TblIngresoInventario();
    }
    
    public void crearDetalleInventario(){
        tblIngresoInventarioFacade.create(tblIngresoInventario);
        tblIngresoInventario = new TblIngresoInventario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
    }

    @FacesConverter(forClass = TblIngresoInventario.class)
    public static class TblTipoExamenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblTipoExamenesController controller = (TblTipoExamenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblIngresoInventarioController");
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
            if (object instanceof TblIngresoInventario) {
                TblIngresoInventario o = (TblIngresoInventario) object;
                return getStringKey(o.getIdIngresoInventario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblIngresoInventario.class.getName());
            }
        }
    }
}
