package clinicaRosario.controller;

import clinicaRosario.entity.TblPromociones;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblExamenes;
import clinicaRosario.session.TblPromocionesFacade;

import java.io.Serializable;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("tblPromocionesController")
@SessionScoped
public class TblPromocionesController implements Serializable {

    private TblPromociones current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblPromocionesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private clinicaRosario.session.TblExamenesFacade tblExamenesFacade;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;
    private String tipoEstado = "promocion";
    private Boolean mostrarFormProm = false;
    private Boolean mostrarTablaProm = true;

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

    public List<TblEstados> getEstadosPromociones() {
        return tblEstadosFacade.finAllByTipoEstado(tipoEstado);
    }

    public List<TblExamenes> getAvaibleExamenes() {
        return tblExamenesFacade.findAllExamenesByEstado();
    }
    
    public List<TblPromociones> getAllPromociones(){
        return ejbFacade.findAll();
    }

    public void crearPromocion() {
        try {
            ejbFacade.create(current);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos ingresados Exitosamente!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Necesita llenar los campos requeridos!"));
        }
    }
    
    public void mostrarFormularioPromocion(){
        mostrarFormProm = true;
        mostrarTablaProm = false;
    }
    
    public void mostrarTablaPromocion(){
        mostrarFormProm = false;
        mostrarTablaProm = true;
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
