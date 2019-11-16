package clinicaRosario.controller;

import clinicaRosario.entity.TblHemograma;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblExpedientes;
import clinicaRosario.session.TblHemogramaFacade;

import java.io.Serializable;
import java.util.List;
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

@Named("tblHemogramaController")
@SessionScoped
public class TblHemogramaController implements Serializable {

    private TblHemograma current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblHemogramaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private clinicaRosario.session.TblExpedientesFacade tblExpedientesFacade;
    private TblExpedientes tblExpedientes;
    private Boolean mostrarFormHemograma = false;
    private Boolean mostrarTblHemograma = true;

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

    public TblHemogramaController() {
    }

    public Boolean getMostrarFormHemograma() {
        return mostrarFormHemograma;
    }

    public void setMostrarFormHemograma(Boolean mostrarFormHemograma) {
        this.mostrarFormHemograma = mostrarFormHemograma;
    }

    public Boolean getMostrarTblHemograma() {
        return mostrarTblHemograma;
    }

    public void setMostrarTblHemograma(Boolean mostrarTblHemograma) {
        this.mostrarTblHemograma = mostrarTblHemograma;
    }

    public TblHemograma getSelected() {
        if (current == null) {
            current = new TblHemograma();
            selectedItemIndex = -1;
        }
        return current;
    }

    public TblExpedientes getTblExpedientes() {
        if (tblExpedientes == null) {
            tblExpedientes = new TblExpedientes();
        }
        return tblExpedientes;
    }

    public void setTblExpedientes(TblExpedientes tblExpedientes) {
        this.tblExpedientes = tblExpedientes;
    }

    private TblHemogramaFacade getFacade() {
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
        current = (TblHemograma) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblHemograma();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public void mostrarFormularioHemograma(){
        mostrarFormHemograma = true;
        mostrarTblHemograma = false;
        tblExpedientes = new TblExpedientes();
        current = new TblHemograma();
    }
    
    public void mostrarTablaHemograma(){
        mostrarFormHemograma = false;
        mostrarTblHemograma = true;
    }
    
    public List<TblExpedientes> getAllExpedientesHemogramas(){
        return tblExpedientesFacade.findAllExpedientesHemograma();
    }

    public void create() {
       String fechaUsuario = tblExpedientes.getFechaIngreso();
       //obtener la fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();

        try{
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", "¡La fecha ingresada no es valida!"));
            }else{
                ejbFacade.create(current);
                tblExpedientes.setIdTblHemograma(current);
                tblExpedientesFacade.create(tblExpedientes);
                current = new TblHemograma();
                tblExpedientes = new TblExpedientes();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", "¡Datos Ingresados Exitosamente!"));
            }
        }catch (ParseException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", "Error: " + e));
        }//fin del try
    }//fin del metodo create
    
    public void leer(TblHemograma leer){
       current = leer;
    }
    
    public void editar(){
        ejbFacade.edit(current);
        current = new TblHemograma();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", "¡Datos Modificados Exitosamente!"));
    }

    public String prepareEdit() {
        current = (TblHemograma) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblHemogramaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TblHemograma) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblHemogramaDeleted"));
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

    public TblHemograma getTblHemograma(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblHemograma.class)
    public static class TblHemogramaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblHemogramaController controller = (TblHemogramaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblHemogramaController");
            return controller.getTblHemograma(getKey(value));
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
            if (object instanceof TblHemograma) {
                TblHemograma o = (TblHemograma) object;
                return getStringKey(o.getIdTblHemograma());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblHemograma.class.getName());
            }
        }

    }

}
