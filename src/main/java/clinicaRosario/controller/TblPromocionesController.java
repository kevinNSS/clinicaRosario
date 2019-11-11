package clinicaRosario.controller;

import clinicaRosario.entity.TblPromociones;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.session.TblPromocionesFacade;

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

@Named("tblPromocionesController")
@SessionScoped
public class TblPromocionesController implements Serializable {

    private TblPromociones current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblPromocionesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Boolean mostrarFormPromocion = false;
    private Boolean mostrarTblPromocion = true;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;
    private String tipoExamen = "promocion";

    public Boolean getMostrarFormPromocion() {
        return mostrarFormPromocion;
    }

    public void setMostrarFormPromocion(Boolean mostrarFormPromocion) {
        this.mostrarFormPromocion = mostrarFormPromocion;
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

    //fecha de nacimiento convertida a date con formato para IF
    private Date dateFinalizacion;
    //metodo set para convertir fechaNacio a Date
    public void setConversionFechaFinalizacion(String fechaFinaliza) throws ParseException{
        this.dateFinalizacion = formatoFecha.parse(fechaFinaliza);
    }//fin metodo set

    public Boolean getMostrarTblPromocion() {
        return mostrarTblPromocion;
    }

    public void setMostrarTblPromocion(Boolean mostrarTblPromocion) {
        this.mostrarTblPromocion = mostrarTblPromocion;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public TblPromocionesController() {
    }

    public TblPromociones getSelected() {
        if (current == null) {
            current = new TblPromociones();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblPromocionesFacade getFacade() {
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
        current = (TblPromociones) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblPromociones();
        selectedItemIndex = -1;
        return "Create";
    }

    public void mostrarTablaPromocion() {
        mostrarFormPromocion = false;
        mostrarTblPromocion = true;
    }

    public void mostrarFormularioPromocion() {
        mostrarFormPromocion = true;
        mostrarTblPromocion = false;
        current = new TblPromociones();
    }

    public void create() {
        String fechaUsuario = current.getFechaInicio();
        String fechaFinal = current.getFechaFinalizacion();
        //capturar fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();
       try {
           setConversionFechaHoy(fechaHoy);
           setConversionFecha(fechaUsuario);
           setConversionFechaFinalizacion(fechaFinal);
           if (dateUser.before(dateHoy) || dateFinalizacion.before(dateHoy)){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verificar", "Fechas ingresadas no validas"));
           }else{
               ejbFacade.create(current);
               current = new TblPromociones();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
           }
       }catch (ParseException e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error: " + e));
       }//fin try
    }//fin del metodo create
    
    public List<TblPromociones> getAllPromociones(){
        return ejbFacade.findAll();
    }
    
    public List<TblEstados> getAllEstadosPromociones(){
        return tblEstadosFacade.finAllByTipoEstado(tipoExamen);
    }
    
    public void leerPromocion(TblPromociones leerProm){
        current = leerProm;
    }

    public String prepareEdit() {
        current = (TblPromociones) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() {
        ejbFacade.edit(current);
    }

    public String destroy() {
        current = (TblPromociones) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblPromocionesDeleted"));
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

    public TblPromociones getTblPromociones(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblPromociones.class)
    public static class TblPromocionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblPromocionesController controller = (TblPromocionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblPromocionesController");
            return controller.getTblPromociones(getKey(value));
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
            if (object instanceof TblPromociones) {
                TblPromociones o = (TblPromociones) object;
                return getStringKey(o.getIdPromocion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblPromociones.class.getName());
            }
        }

    }

}
