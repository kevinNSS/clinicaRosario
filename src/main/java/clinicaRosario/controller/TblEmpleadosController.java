package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.session.TblEmpleadosFacade;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
//importamos los paquetes para trabajar con las fechas
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.ParseException;

@Named("tblEmpleadosController")
@SessionScoped
public class TblEmpleadosController implements Serializable {

    private TblEmpleados current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblEmpleadosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;
    private String tipoExamen = "empleados";
    private Boolean mostrarTblEmpleados = true;
    private Boolean mostrarFormEmpleados = false;

    Random numero = new Random();
    private int n1;
    private int n2;
    private int n3;
    private int n4;

    //formato para la fecha actual e indicamos e formato
    DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    //fecha actual en date a utiliza en IF con formato
    private Date dateHoy;
    //metodo set para convertir datehoy a tipo Date(dateNow)
    public void setConversionFechaHoy(Date dateNow){
        try {
            String fechaAhora = formatoFecha.format(dateNow);
            this.dateHoy = formatoFecha.parse(fechaAhora);
        } catch (ParseException e) {

        }
    }//fin metodo set

    //fecha del usuario convertida a date con formato para IF
    private Date dateUser;
    //metodo set para convertir fechaUsuario a Date
    public void setConversionFecha(String fechaUsuario) throws ParseException{
        this.dateUser = formatoFecha.parse(fechaUsuario);
    }//fin metodo set

    //fecha de nacimiento convertida a date con formato para IF
    private Date dateNacimiento;
    //metodo set para convertir fechaNacio a Date
    public void setConversionFechaNacer(String fechaNacio) throws ParseException{
        this.dateNacimiento = formatoFecha.parse(fechaNacio);
    }//fin metodo set

    public Boolean getMostrarTblEmpleados() {
        return mostrarTblEmpleados;
    }

    public void setMostrarTblEmpleados(Boolean mostrarTblEmpleados) {
        this.mostrarTblEmpleados = mostrarTblEmpleados;
    }

    public Boolean getMostrarFormEmpleados() {
        return mostrarFormEmpleados;
    }

    public void setMostrarFormEmpleados(Boolean mostrarFormEmpleados) {
        this.mostrarFormEmpleados = mostrarFormEmpleados;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public TblEmpleadosController() {
    }

    public TblEmpleados getSelected() {
        if (current == null) {
            current = new TblEmpleados();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblEmpleadosFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TblEmpleados) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblEmpleados();
        selectedItemIndex = -1;
        return "Create";
    }

    public List<TblEstados> getEstadoEmpleado() {
        return tblEstadosFacade.finAllByTipoEstado(tipoExamen);
    }
    
    public List<TblEmpleados> getAllEmpleados(){
        return ejbFacade.findAll();
    }

    public void create() {
        String fechaUsuario = current.getFechaContratacion();
        String fechaNacio = current.getFechaNacimiento();
        //Obtener fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();
        try {
            setConversionFechaNacer(fechaNacio);
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy) || dateNacimiento.after(dateHoy) || dateNacimiento.equals(dateHoy)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verificar", "Fecha ingresada no es valida"));
            }else{
                n1 = numero.nextInt(10);
                n2 = numero.nextInt(10);
                n3 = numero.nextInt(10);
                n4 = numero.nextInt(10);
                current.setIdEmpleado(current.getPrimerNombreEmpleado().substring(0, 1).toUpperCase() + current.getPrimerApellidoEmpleado().substring(0, 1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4));
                ejbFacade.create(current);
                current = new TblEmpleados();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Existosamente!"));
            }
        }catch (ParseException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error: " + e));
        }
    }
    
    public void editar(){
        ejbFacade.edit(current);
        current = new TblEmpleados();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Modificados Existosamente!"));
    }
    
    public void leerEmpleado(TblEmpleados leer){
        current = leer;
    }

    public String prepareEdit() {
        current = (TblEmpleados) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public void mostrarTablaEmpleados(){
        mostrarFormEmpleados = false;
        mostrarTblEmpleados = true;
    }
    
    public void mostrarFormularioEmpleados(){
        mostrarFormEmpleados = true;
        mostrarTblEmpleados = false;
        current = new TblEmpleados();
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblEmpleadosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TblEmpleados) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblEmpleadosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public TblEmpleados getTblEmpleados(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblEmpleados.class)
    public static class TblEmpleadosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblEmpleadosController controller = (TblEmpleadosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblEmpleadosController");
            return controller.getTblEmpleados(getKey(value));
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
