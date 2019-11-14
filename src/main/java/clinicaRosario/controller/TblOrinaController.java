package clinicaRosario.controller;

import clinicaRosario.entity.TblOrina;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblExpedientes;
import clinicaRosario.session.TblOrinaFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

@Named("tblOrinaController")
@SessionScoped
public class TblOrinaController implements Serializable {

    private TblOrina current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblOrinaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Boolean mostrarFormOrina = false;
    private Boolean mostrarTblOrina = true;
    private TblExpedientes tblExpedientes;
    @EJB
    private clinicaRosario.session.TblExpedientesFacade tblExpedientesFacade;

    public TblOrinaController() {
    }

    //formato para la fecha actual e indicamos e formato
    DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    //fecha actual en date a utiliza en IF con formato
    private Date dateHoy;

    //metodo set para convertir datehoy a tipo Date(dateNow)
    public void setConversionFechaHoy(Date dateNow) {
        try {
            String fechaAhora = formatoFecha.format(dateNow);
            this.dateHoy = formatoFecha.parse(fechaAhora);
        } catch (ParseException e) {

        }
    }//fin metodo set

    //fecha del usuario convertida a date con formato para IF
    private Date dateUser;

    //metodo set para convertir fechaUsuario a Date
    public void setConversionFecha(String fechaUsuario) throws ParseException {
        this.dateUser = formatoFecha.parse(fechaUsuario);
    }//fin metodo set

    public Boolean getMostrarFormOrina() {
        return mostrarFormOrina;
    }

    public void setMostrarFormOrina(Boolean mostrarFormOrina) {
        this.mostrarFormOrina = mostrarFormOrina;
    }

    public Boolean getMostrarTblOrina() {
        return mostrarTblOrina;
    }

    public void setMostrarTblOrina(Boolean mostrarTblOrina) {
        this.mostrarTblOrina = mostrarTblOrina;
    }

    public TblExpedientes getTblExpedientes() {
        return tblExpedientes;
    }

    public void setTblExpedientes(TblExpedientes tblExpedientes) {
        this.tblExpedientes = tblExpedientes;
    }

    public TblOrina getSelected() {
        if (current == null) {
            current = new TblOrina();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblOrinaFacade getFacade() {
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

    public void mostrarFormularioOrina() {
        mostrarFormOrina = true;
        mostrarTblOrina = false;
        current = new TblOrina();
        tblExpedientes = new TblExpedientes();
    }

    public void mostrarTablaOrina() {
        mostrarFormOrina = false;
        mostrarTblOrina = true;
    }

    public void nuevoFormulario() {
        current = new TblOrina();
        tblExpedientes = new TblExpedientes();
    }

    public void registrarFormulario() {
        //obtener la fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();

        try {
            setConversionFecha(tblExpedientes.getFechaIngreso());
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Fecha ingresada no es valida!"));
            } else {
                ejbFacade.create(current);
                tblExpedientes.setIdTblOrina(current);
                tblExpedientesFacade.create(tblExpedientes);
                current = new TblOrina();
                tblExpedientes = new TblExpedientes();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Información Ingresada Exitosamente!"));
            }//Final IF ELSE
        } catch (ParseException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error: " + e));
        }//Final try
    }//Final metodo registrarFormulario

    public List<TblOrina> getAllExamenesOrina() {
        return ejbFacade.findAll();
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

    public TblOrina getTblOrina(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblOrina.class)
    public static class TblOrinaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblOrinaController controller = (TblOrinaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblOrinaController");
            return controller.getTblOrina(getKey(value));
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
            if (object instanceof TblOrina) {
                TblOrina o = (TblOrina) object;
                return getStringKey(o.getIdTblOrina());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblOrina.class.getName());
            }
        }

    }

}
