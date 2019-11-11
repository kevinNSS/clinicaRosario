package clinicaRosario.controller;

import clinicaRosario.entity.TblHeces;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblExpedientes;
import clinicaRosario.session.TblHecesFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
//importamos los paquetes para trabajar con las fechas
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.ParseException;

@Named("tblHecesController")
@ViewScoped
public class TblHecesController implements Serializable {

    private TblHeces current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblHecesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Boolean mostrarTblHeces = true;
    private Boolean mostrarFormHeces = false;
    @EJB
    private clinicaRosario.session.TblExpedientesFacade tblExpedientesFacade;
    private TblExpedientes tblExpedientes;

    public TblHecesController() {
    }

    public Boolean getMostrarTblHeces() {
        return mostrarTblHeces;
    }

    public void setMostrarTblHeces(Boolean mostrarTblHeces) {
        this.mostrarTblHeces = mostrarTblHeces;
    }

    public Boolean getMostrarFormHeces() {
        return mostrarFormHeces;
    }

    public void setMostrarFormHeces(Boolean mostrarFormHeces) {
        this.mostrarFormHeces = mostrarFormHeces;
    }

    public TblExpedientes getTblExpedientes() {
        if (tblExpedientes == null) {
            tblExpedientes = new TblExpedientes();
        }
        return tblExpedientes;
    }

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

    public void setTblExpedientes(TblExpedientes tblExpedientes) {
        this.tblExpedientes = tblExpedientes;
    }

    public TblHeces getSelected() {
        if (current == null) {
            current = new TblHeces();
            selectedItemIndex = -1;
        }
        return current;
    }


    private TblHecesFacade getFacade() {
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

    public void mostrarFormularioExamHeces() {
        mostrarFormHeces = true;
        mostrarTblHeces = false;
        tblExpedientes = new TblExpedientes();
        current = new TblHeces();
    }

    public void mostrarTablaExamHeces() {
        mostrarFormHeces = false;
        mostrarTblHeces = true;
    }

    public List<TblExpedientes> getAllExamHeces(){
        return tblExpedientesFacade.findAllExpedientesHeces();
    }
    
    public void leerExamHeces(TblExpedientes leer){
        tblExpedientes = leer;
        current = tblExpedientes.getIdTblHeces();
    }

    public void create() {
       String fechaUsuario = tblExpedientes.getFechaIngreso();
       //obtener la fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();

        //try para validacion de fecha
        try{
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifica!", "La fecha ingresada no es valida."));
            }else{
                ejbFacade.create(current);
                tblExpedientes.setIdTblHeces(current);
                tblExpedientesFacade.create(tblExpedientes);
                current = new TblHeces();
                tblExpedientes = new TblExpedientes();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
            }
        }catch (ParseException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifica!", "Error: " + e));
        }//fin del try
    }//fin del metodo create
    
    public void editar(){
        ejbFacade.edit(current);
        current = new TblHeces();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblHecesDeleted"));
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public TblHeces getTblHeces(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblHeces.class)
    public static class TblHecesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblHecesController controller = (TblHecesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblHecesController");
            return controller.getTblHeces(getKey(value));
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
            if (object instanceof TblHeces) {
                TblHeces o = (TblHeces) object;
                return getStringKey(o.getIdTablaHeces());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblHeces.class.getName());
            }
        }

    }

}
