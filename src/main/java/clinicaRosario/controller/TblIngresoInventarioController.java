package clinicaRosario.controller;

import clinicaRosario.entity.TblIngresoInventario;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblInventario;
import clinicaRosario.entity.TblProveedores;
import clinicaRosario.session.TblIngresoInventarioFacade;

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

@Named("tblIngresoInventarioController")
@SessionScoped
public class TblIngresoInventarioController implements Serializable {

    private TblIngresoInventario current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblIngresoInventarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Boolean mostrarFormInventarioDetalle = false;
    private Boolean mostrarTblInventarioDetalle = true;
    @EJB
    private clinicaRosario.session.TblInventarioFacade tblInventarioFacade;
    private TblInventario tblInventario;

    public Boolean getMostrarFormInventarioDetalle() {
        return mostrarFormInventarioDetalle;
    }

    public void setMostrarFormInventarioDetalle(Boolean mostrarFormInventarioDetalle) {
        this.mostrarFormInventarioDetalle = mostrarFormInventarioDetalle;
    }

    public Boolean getMostrarTblInventarioDetalle() {
        return mostrarTblInventarioDetalle;
    }

    public void setMostrarTblInventarioDetalle(Boolean mostrarTblInventarioDetalle) {
        this.mostrarTblInventarioDetalle = mostrarTblInventarioDetalle;
    }

    public TblIngresoInventarioController() {
    }

    public TblIngresoInventario getSelected() {
        if (current == null) {
            current = new TblIngresoInventario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblIngresoInventarioFacade getFacade() {
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
        current = (TblIngresoInventario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblIngresoInventario();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public void mostrarFormularioDetalle(){
        mostrarFormInventarioDetalle = true;
        mostrarTblInventarioDetalle = false;
        current = new TblIngresoInventario();
    }
    
    public void mostrarTablaDetalle(){
        mostrarTblInventarioDetalle = true;
        mostrarFormInventarioDetalle = false;
    }

    public void create() {
     ejbFacade.create(current);
     tblInventario = tblInventarioFacade.inventarioSeleccionado(current.getIdInventario());
     tblInventario.setStockProducto(tblInventario.getStockProducto() + current.getCantidad());
     tblInventarioFacade.edit(tblInventario);
     current = new TblIngresoInventario();
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
    }
    
    public List<TblIngresoInventario> getAllDetalleInventario(){
        return ejbFacade.findAll();
    }
    
    public List<TblInventario> getAllInventario(){
        return tblInventarioFacade.findAll();
    }

    public String prepareEdit() {
        current = (TblIngresoInventario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblIngresoInventarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TblIngresoInventario) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblIngresoInventarioDeleted"));
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

    public TblIngresoInventario getTblIngresoInventario(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblIngresoInventario.class)
    public static class TblIngresoInventarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblIngresoInventarioController controller = (TblIngresoInventarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblIngresoInventarioController");
            return controller.getTblIngresoInventario(getKey(value));
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
