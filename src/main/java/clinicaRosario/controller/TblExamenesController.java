package clinicaRosario.controller;

import clinicaRosario.entity.TblExamenes;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblTipoExamenes;
import clinicaRosario.session.TblExamenesFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("tblExamenesController")
@ViewScoped
public class TblExamenesController implements Serializable {

    private TblExamenes current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblExamenesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    Random numero = new Random();
    private int n1;
    private int n2;
    private Boolean mostrarTablaExamen = true;
    private Boolean mostrarFormExamen = false;
    private String tipoEstado = "examenes";

    @EJB
    private clinicaRosario.session.TblTipoExamenesFacade tblTipoExamenesFacade;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;

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

    public void nuevoExamen() {
        current = new TblExamenes();
        mostrarFormExamen = true;
        mostrarTablaExamen = false;
        tblEstadosFacade.finAllByTipoEstado(tipoEstado);
        tblTipoExamenesFacade.findAll();
    }

    public void mostrarTablaDeExamen() {
        mostrarFormExamen = false;
        mostrarTablaExamen = true;
    }

    public void ingresarExamen() throws IOException {
        try {
            n1 = numero.nextInt(10);
            n2 = numero.nextInt(10);
            current.setIdExamen(current.getNombreExamen().substring(0, 1).toUpperCase() + current.getNombreExamen().substring(current.getNombreExamen().length() - 1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2));
            ejbFacade.create(current);
            current = new TblExamenes();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos ingresados Exitosamente!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Necesita llenar los campos requeridos!"));
        }

    }

    public List<TblEstados> getEstadosExamenes() {
        return tblEstadosFacade.finAllByTipoEstado(tipoEstado);
    }

    public List<TblTipoExamenes> getAllTipoExamen() {
        return tblTipoExamenesFacade.findAll();
    }

    public List<TblExamenes> getAll() {
        return ejbFacade.findAll();
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
