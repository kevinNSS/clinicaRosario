/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblFacturaDetalle;
import clinicaRosario.entity.TblFacturaEncabezado;
import clinicaRosario.entity.TblPacientes;
import clinicaRosario.session.TblEmpleadosFacade;
import clinicaRosario.session.TblEstadosFacade;
import clinicaRosario.session.TblFacturaDetalleFacade;
import clinicaRosario.session.TblFacturaEncabezadoFacade;
import clinicaRosario.session.TblPacientesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
@Named("facturacionController")
@ViewScoped
public class FacturacionController implements Serializable {

    private TblFacturaEncabezado tblFacturaEncabezado;
    private TblFacturaDetalle tblFacturaDetalle;
    @EJB
    private TblFacturaEncabezadoFacade ejbFacade;
    @EJB
    private TblEmpleadosFacade tblEmpleadosFacade;
    @EJB
    private TblEstadosFacade tblEstadosFacade;
    @EJB
    private TblFacturaDetalleFacade tblFacturaDetalleFacade;
    @EJB
    private TblPacientesFacade tblPacientesFacade;
    private Boolean mostrarFormularioFacturacion = false;
    private Boolean mostrarTablaFacturacion = true;
    private String estadoEmpleado = "factura";
    
    @PostConstruct
    public void init(){
        tblFacturaEncabezado = new TblFacturaEncabezado();
        tblFacturaDetalle = new TblFacturaDetalle();
    }

    public void mostrarFormFacturacion() {
        mostrarFormularioFacturacion = true;
        mostrarTablaFacturacion = false;
        tblFacturaEncabezado = new TblFacturaEncabezado();
        tblFacturaDetalle = new TblFacturaDetalle();
    }

    public void mostrarTblFacturacion() {
        mostrarFormularioFacturacion = false;
        mostrarTablaFacturacion = true;
    }

    public void nuevaFacturacion() {
        tblFacturaEncabezado = new TblFacturaEncabezado();
        tblFacturaDetalle = new TblFacturaDetalle();
    }

    public List<TblPacientes> getAllPacientes() {
        return tblPacientesFacade.findAll();
    }

    public List<TblEmpleados> getAllEmpleados() {
        return tblEmpleadosFacade.findAll();
    }

    public List<TblEstados> getAllEstadosFactura() {
        return tblEstadosFacade.finAllByTipoEstado(estadoEmpleado);
    }

    public void crearFacturacion() {
        ejbFacade.create(tblFacturaEncabezado);
    }

    @FacesConverter(forClass = TblFacturaEncabezado.class)
    public static class TblFacturaEncabezadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblFacturaEncabezadoController controller = (TblFacturaEncabezadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturacionController");
            return controller.getTblFacturaEncabezado(getKey(value));
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
            if (object instanceof TblFacturaEncabezado) {
                TblFacturaEncabezado o = (TblFacturaEncabezado) object;
                return getStringKey(o.getIdFactura());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblFacturaEncabezado.class.getName());
            }
        }

    }

    @FacesConverter(forClass = TblFacturaDetalle.class)
    public static class TblFacturaDetalleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblFacturaDetalleController controller = (TblFacturaDetalleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturacionController");
            return controller.getTblFacturaDetalle(getKey(value));
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
            if (object instanceof TblFacturaDetalle) {
                TblFacturaDetalle o = (TblFacturaDetalle) object;
                return getStringKey(o.getIdFacturaDetalle());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblFacturaDetalle.class.getName());
            }
        }

    }
}
