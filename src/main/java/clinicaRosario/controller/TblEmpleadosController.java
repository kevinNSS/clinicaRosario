package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.session.TblEmpleadosFacade;
import clinicaRosario.session.TblEstadosFacade;
import java.io.Serializable;
import java.util.List;
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
@Named("tblEmpleadosController")
@ViewScoped
public class TblEmpleadosController implements Serializable {

    private TblEmpleados tblEmpleados;
    @EJB
    private TblEmpleadosFacade tblEmpleadosFacade;
    @EJB 
    private TblEstadosFacade tblEstadosFacade;
    private Boolean mostrarTblEmpleados = true;
    private Boolean mostrarFormEmpleados = false;
    private String estadoEmpleado = "empleados";
    
    public void init(){
        if (tblEmpleados == null) {
            tblEmpleados = new TblEmpleados();
        }
    }
    
    public void mostrarTablaEmpleado(){
        mostrarTblEmpleados = true;
        mostrarFormEmpleados = false;
    }
    
    public void mostrarFormularioEmpleado(){
        mostrarTblEmpleados = false;
        mostrarFormEmpleados = true;
       
    }
    
    public void nuevoEmpleado(){
        tblEmpleados = new TblEmpleados();
    }
    
    public void leerEmpleado(TblEmpleados empleadoSeleccionado){
        tblEmpleados = empleadoSeleccionado;
    }
    
    public List<TblEstados> getEstadosEmpleados(){
        return tblEstadosFacade.finAllByTipoEstado(estadoEmpleado);
    }
    
    public void crearEmpleado(){
        try {
            tblEmpleadosFacade.create(tblEmpleados); 
            tblEmpleados = new TblEmpleados();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","¡Datos Ingresados Exitosamente!"));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","¡Llene los campos requeridos!"));
            
        }
    }
    
    
    
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
