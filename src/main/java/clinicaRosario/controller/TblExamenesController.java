package clinicaRosario.controller;

import clinicaRosario.entity.TblExamenes;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.session.TblExamenesFacade;

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
import sun.security.util.Length;

@Named("tblExamenesController")
@SessionScoped
public class TblExamenesController implements Serializable {

    private TblExamenes current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblExamenesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Boolean mostrarTblExamen = true;
    private Boolean mostrarFormExamen = false;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;
    private String tipoExamen = "examenes";
    Random numero = new Random();
    private int n1;
    private int n2;

    public Boolean getMostrarTblExamen() {
        return mostrarTblExamen;
    }

    public void setMostrarTblExamen(Boolean mostrarTblExamen) {
        this.mostrarTblExamen = mostrarTblExamen;
    }

    public Boolean getMostrarFormExamen() {
        return mostrarFormExamen;
    }

    public void setMostrarFormExamen(Boolean mostrarFormExamen) {
        this.mostrarFormExamen = mostrarFormExamen;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public TblExamenesController() {
    }

    public TblExamenes getSelected() {
        if (current == null) {
            current = new TblExamenes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblExamenesFacade getFacade() {
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
    
    public void mostrarTablaDeExamen(){
        mostrarFormExamen = false;
        mostrarTblExamen = true;
    }
    
    public void mostrarFormularioExamen(){
        mostrarFormExamen = true;
        mostrarTblExamen = false;
        current = new TblExamenes();
    }
    
    public List<TblExamenes> getAll(){
        return ejbFacade.findAll();
    }
    
    public List<TblEstados> getAllEstadosExamenes(){
        return tblEstadosFacade.finAllByTipoEstado(tipoExamen);
    }
    
    public void leerExamen(TblExamenes leerExamen){
        current = leerExamen;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TblExamenes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblExamenes();
        selectedItemIndex = -1;
        return "Create";
    }

    public void create() {
        n1 = numero.nextInt(10);
        n2 = numero.nextInt(10);
        current.setIdExamen(current.getNombreExamen().substring(0, 1).toUpperCase() + current.getNombreExamen().substring(current.getNombreExamen().length() -1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2));
        ejbFacade.create(current);
        current = new TblExamenes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
    }
    
    public void editar(){
        ejbFacade.edit(current);
        current = new TblExamenes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Editados Exitosamente!"));
    }

    public String prepareEdit() {
        current = (TblExamenes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String destroy() {
        current = (TblExamenes) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblExamenesDeleted"));
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

    public TblExamenes getTblExamenes(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblExamenes.class)
    public static class TblExamenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblExamenesController controller = (TblExamenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblExamenesController");
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
            if (object instanceof TblExamenes) {
                TblExamenes o = (TblExamenes) object;
                return getStringKey(o.getIdExamen());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblExamenes.class.getName());
            }
        }

    }

}
