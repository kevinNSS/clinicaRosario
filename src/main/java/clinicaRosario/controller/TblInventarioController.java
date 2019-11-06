package clinicaRosario.controller;

import clinicaRosario.entity.TblInventario;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.session.TblInventarioFacade;

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

@Named("tblInventarioController")
@SessionScoped
public class TblInventarioController implements Serializable {

    private TblInventario current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblInventarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    Random numero = new Random();
    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private Boolean mostrarFormInventario = false;
    private Boolean mostrarTblInventario = true;

    public Boolean getMostrarFormInventario() {
        return mostrarFormInventario;
    }

    public void setMostrarFormInventario(Boolean mostrarFormInventario) {
        this.mostrarFormInventario = mostrarFormInventario;
    }

    public Boolean getMostrarTblInventario() {
        return mostrarTblInventario;
    }

    public void setMostrarTblInventario(Boolean mostrarTblInventario) {
        this.mostrarTblInventario = mostrarTblInventario;
    }

    public TblInventarioController() {
    }

    public TblInventario getSelected() {
        if (current == null) {
            current = new TblInventario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblInventarioFacade getFacade() {
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
        current = (TblInventario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblInventario();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public void mostrarFormularioInventario(){
        mostrarFormInventario = true;
        mostrarTblInventario = false;
        current = new TblInventario();
    }
    
    public void mostrarTablaInventario(){
        mostrarFormInventario = false;
        mostrarTblInventario = true;
    }

    public void create() {
        n1 = numero.nextInt(10);
        n2 = numero.nextInt(10);
        n3 = numero.nextInt(10);
        n4 = numero.nextInt(10);
        current.setIdInventario(current.getNombreProducto().substring(0, 1).toUpperCase() + current.getNombreProducto().substring(current.getNombreProducto().length() -1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4));
        current.setStockProducto(0);
        ejbFacade.create(current);
        current = new TblInventario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
    }
    
    public List<TblInventario> getAllInventario(){
       return ejbFacade.findAll();
    }

    public String prepareEdit() {
        current = (TblInventario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblInventarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TblInventario) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblInventarioDeleted"));
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

    public TblInventario getTblInventario(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblInventario.class)
    public static class TblInventarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblInventarioController controller = (TblInventarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblInventarioController");
            return controller.getTblInventario(getKey(value));
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
            if (object instanceof TblInventario) {
                TblInventario o = (TblInventario) object;
                return getStringKey(o.getIdInventario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblInventario.class.getName());
            }
        }

    }

}
