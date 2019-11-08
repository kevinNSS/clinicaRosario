package clinicaRosario.controller;

import clinicaRosario.entity.TblFacturaEncabezado;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblFacturaDetalle;
import clinicaRosario.session.TblFacturaEncabezadoFacade;

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

@Named("tblFacturaEncabezadoController")
@SessionScoped
public class TblFacturaEncabezadoController implements Serializable {

    private TblFacturaEncabezado current;
    private DataModel items = null;
    @EJB
    private clinicaRosario.session.TblFacturaEncabezadoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private clinicaRosario.session.TblFacturaDetalleFacade tblFacturaDetalleFacade;
    private TblFacturaDetalle tblFacturaDetalle;
    private Boolean mostrarTblFactura = true;
    private Boolean mostrarFormFactura = false;
    @EJB
    private clinicaRosario.session.TblEstadosFacade tblEstadosFacade;
    private String tipoExamen = "factura";
    private TblFacturaDetalle f1;
    private TblFacturaDetalle f2;
    private TblFacturaDetalle f3;
    private TblFacturaDetalle f4;
    private TblFacturaDetalle f5;
    private double totalPagar = 0.0;

    public Boolean getMostrarTblFactura() {
        return mostrarTblFactura;
    }

    public void setMostrarTblFactura(Boolean mostrarTblFactura) {
        this.mostrarTblFactura = mostrarTblFactura;
    }

    public Boolean getMostrarFormFactura() {
        return mostrarFormFactura;
    }

    public void setMostrarFormFactura(Boolean mostrarFormFactura) {
        this.mostrarFormFactura = mostrarFormFactura;
    }

    public TblFacturaDetalle getF1() {
        if (f1 == null) {
            f1 = new TblFacturaDetalle();
        }
        return f1;
    }

    public void setF1(TblFacturaDetalle f1) {
        this.f1 = f1;
    }

    public TblFacturaDetalle getF2() {
        if (f2 == null) {
            f2 = new TblFacturaDetalle();
        }
        return f2;
    }

    public void setF2(TblFacturaDetalle f2) {
        this.f2 = f2;
    }

    public TblFacturaDetalle getF3() {
        if (f3 == null) {
            f3 = new TblFacturaDetalle();
        }
        return f3;
    }

    public void setF3(TblFacturaDetalle f3) {
        this.f3 = f3;
    }

    public TblFacturaDetalle getF4() {
        if (f4 == null) {
            f4 = new TblFacturaDetalle();
        }
        return f4;
    }

    public void setF4(TblFacturaDetalle f4) {
        this.f4 = f4;
    }

    public TblFacturaDetalle getF5() {
        if (f5 == null) {
            f5 = new TblFacturaDetalle();
        }
        return f5;
    }

    public void setF5(TblFacturaDetalle f5) {
        this.f5 = f5;
    }

    public TblFacturaEncabezadoController() {
    }

    public TblFacturaDetalle getTblFacturaDetalle() {
        return tblFacturaDetalle;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public void setTblFacturaDetalle(TblFacturaDetalle tblFacturaDetalle) {
        this.tblFacturaDetalle = tblFacturaDetalle;
    }

    public TblFacturaEncabezado getSelected() {
        if (current == null) {
            current = new TblFacturaEncabezado();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TblFacturaEncabezadoFacade getFacade() {
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
        current = (TblFacturaEncabezado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TblFacturaEncabezado();
        selectedItemIndex = -1;
        return "Create";
    }

    public void mostrarTablaFactura() {
        mostrarFormFactura = false;
        mostrarTblFactura = true;
    }

    public void mostrarFormularioFactura() {
        mostrarFormFactura = true;
        mostrarTblFactura = false;
        current = new TblFacturaEncabezado();
    }

    public void create() {
        current.setDescuentoTotal(0.0);
        current.setSubTotal(0.0);
        current.setIva(0.0);
        current.setTotal(0.0);
        ejbFacade.create(current);
        f1.setIdFacturaEncabezado(current);
        if (f1.getIdExamen() != null) {
            totalPagar = totalPagar + f1.getIdExamen().getPrecioExamen();
        } else if (f1.getIdPromocion() != null) {
            totalPagar = totalPagar + f1.getIdPromocion().getTotalPagar();
        }
        tblFacturaDetalleFacade.create(f1);
        if (f2.getIdExamen() != null || f2.getIdPromocion() != null) {
            f2.setIdFacturaEncabezado(current);
            if (f2.getIdExamen() != null) {
                totalPagar = totalPagar + f2.getIdExamen().getPrecioExamen();
            } else if (f2.getIdPromocion() != null) {
                totalPagar = totalPagar + f2.getIdPromocion().getTotalPagar();
            }
            tblFacturaDetalleFacade.create(f2);
        }
        if (f3.getIdExamen() != null || f3.getIdPromocion() != null) {
            f3.setIdFacturaEncabezado(current);
            if (f3.getIdExamen() != null) {
                totalPagar = totalPagar + f3.getIdExamen().getPrecioExamen();
            } else if (f3.getIdPromocion() != null) {
                totalPagar = totalPagar + f3.getIdPromocion().getTotalPagar();
            }
            tblFacturaDetalleFacade.create(f3);
        }
        /*if (f4.getIdExamen() != null || f4.getIdPromocion() != null) {
            f4.setIdFacturaEncabezado(current);
            if (f4.getIdExamen() != null) {
                totalPagar = totalPagar + f4.getIdExamen().getPrecioExamen();
            } else if (f4.getIdPromocion() != null) {
                totalPagar = totalPagar + f4.getIdPromocion().getTotalPagar();
            }
            tblFacturaDetalleFacade.create(f4);
        }
        if (f5.getIdExamen() != null || f5.getIdPromocion() != null) {
            f5.setIdFacturaEncabezado(current);
            if (f5.getIdExamen() != null) {
                totalPagar = totalPagar + f5.getIdExamen().getPrecioExamen();
            } else if (f5.getIdPromocion() != null) {
                totalPagar = totalPagar + f5.getIdPromocion().getTotalPagar();
            }
            tblFacturaDetalleFacade.create(f5);
        }*/
        current.setSubTotal(totalPagar);
        current.setIva(totalPagar * 0.13);
        current.setTotal(totalPagar + (totalPagar * 0.13));
        ejbFacade.edit(current);
        current = new TblFacturaEncabezado();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
    }

    public List<TblFacturaEncabezado> getAllFacturas() {
        return ejbFacade.findAll();
    }

    public List<TblEstados> getAllEstadosFacturacion() {
        return tblEstadosFacade.finAllByTipoEstado(tipoExamen);
    }

    public String prepareEdit() {
        current = (TblFacturaEncabezado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblFacturaEncabezadoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TblFacturaEncabezado) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TblFacturaEncabezadoDeleted"));
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

    public TblFacturaEncabezado getTblFacturaEncabezado(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TblFacturaEncabezado.class)
    public static class TblFacturaEncabezadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TblFacturaEncabezadoController controller = (TblFacturaEncabezadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tblFacturaEncabezadoController");
            return controller.getTblFacturaEncabezado(getKey(value));
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
            if (object instanceof TblFacturaEncabezado) {
                TblFacturaEncabezado o = (TblFacturaEncabezado) object;
                return getStringKey(o.getIdFactura());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TblFacturaEncabezado.class.getName());
            }
        }

    }

}
