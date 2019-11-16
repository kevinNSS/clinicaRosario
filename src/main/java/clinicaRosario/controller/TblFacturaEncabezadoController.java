package clinicaRosario.controller;

import clinicaRosario.entity.TblFacturaEncabezado;
import clinicaRosario.controller.util.JsfUtil;
import clinicaRosario.controller.util.PaginationHelper;
import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblFacturaDetalle;
import clinicaRosario.session.TblFacturaEncabezadoFacade;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



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
    private double totalPagar = 0.0;
    private List<TblFacturaDetalle> facturaDetalleList = new ArrayList<>();
    private TblFacturaDetalle agregarDetalleFactura;
    DecimalFormat df = new DecimalFormat("###.##");
    private List<TblFacturaEncabezado> facturaEncabezadosList = null;
    private int totalPacientesDia;
    private double totalReporteFinancieroDiario;
    private LocalDate fechaHoy = LocalDate.now();

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

    public TblFacturaDetalle getAgregarDetalleFactura() {
        if (agregarDetalleFactura == null) {
            agregarDetalleFactura = new TblFacturaDetalle();
        }
        return agregarDetalleFactura;
    }

    public void setAgregarDetalleFactura(TblFacturaDetalle agregarDetalleFactura) {
        this.agregarDetalleFactura = agregarDetalleFactura;
    }

    public List<TblFacturaDetalle> getFacturaDetalleList() {
        return facturaDetalleList;
    }

    public void setFacturaDetalleList(List<TblFacturaDetalle> facturaDetalleList) {
        this.facturaDetalleList = facturaDetalleList;
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

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public void setTblFacturaDetalle(TblFacturaDetalle tblFacturaDetalle) {
        this.tblFacturaDetalle = tblFacturaDetalle;
    }

    public List<TblFacturaEncabezado> getFacturaEncabezadosList() {
        return facturaEncabezadosList;
    }

    public void setFacturaEncabezadosList(List<TblFacturaEncabezado> facturaEncabezadosList) {
        this.facturaEncabezadosList = facturaEncabezadosList;
    }

    public int getTotalPacientesDia() {
        return totalPacientesDia;
    }

    public void setTotalPacientesDia(int totalPacientesDia) {
        this.totalPacientesDia = totalPacientesDia;
    }

    public double getTotalReporteFinancieroDiario() {
        return totalReporteFinancieroDiario;
    }

    public void setTotalReporteFinancieroDiario(double totalReporteFinancieroDiario) {
        this.totalReporteFinancieroDiario = totalReporteFinancieroDiario;
    }

    public TblFacturaEncabezado getSelected() {
        if (current == null) {
            current = new TblFacturaEncabezado();
            selectedItemIndex = -1;
        }
        return current;
    }

    public LocalDate getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(LocalDate fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    private TblFacturaEncabezadoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null){
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
        facturaDetalleList = new ArrayList<>();
        totalPagar = 0.0;
    }
    
    public List<TblFacturaEncabezado> getReporteFinancieroDiario() {
        facturaEncabezadosList = ejbFacade.findAllFacturaDiario(fechaHoy.toString());
        setTotalReporteFinancieroDiario(0.0);
        if (facturaEncabezadosList.size() > 0) {
            for (int i = 0; i < facturaEncabezadosList.size(); i++) {
                totalReporteFinancieroDiario = totalReporteFinancieroDiario + facturaEncabezadosList.get(i).getTotal();
            }
        }
        return ejbFacade.findAllFacturaDiario(fechaHoy.toString());
    }
    
    public List<TblFacturaEncabezado> getReportePacientesDiario(){
        facturaEncabezadosList = ejbFacade.findAllFacturaDiario(fechaHoy.toString());
        setTotalPacientesDia(0);
        if(facturaEncabezadosList.size() > 0){
            for(int i = 0; i < facturaEncabezadosList.size(); i++){
                totalPacientesDia = totalPacientesDia + 1;
            }
        }
        return ejbFacade.findAllFacturaDiario(fechaHoy.toString());
    }

    public void addDetalleFactura() {
        if (agregarDetalleFactura.getIdExamen() != null && agregarDetalleFactura.getIdPromocion() != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "¡Se Debe Agregar Una Opción A La Vez!"));
        } else {
            if (agregarDetalleFactura.getIdExamen() != null || agregarDetalleFactura.getIdPromocion() != null) {
                facturaDetalleList.add(agregarDetalleFactura);
                if (agregarDetalleFactura.getIdExamen() != null) {
                    totalPagar = totalPagar + agregarDetalleFactura.getIdExamen().getPrecioExamen();
                } else if (agregarDetalleFactura.getIdPromocion() != null) {
                    totalPagar = totalPagar + agregarDetalleFactura.getIdPromocion().getTotalPagar();
                }
                agregarDetalleFactura = null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "¡Se Debe Ingresar Al Menos Un Examen ó Promoción!"));
            }
        }
    }

    public void create() throws JRException, IOException {
        String fechaUsuario = current.getFechaFacturacion();
        //capturar fecha de hoy
        Date fechaHoy = Calendar.getInstance().getTime();
        //try para validacion de fecha
        try{
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifica!", "La fecha ingresada no es valida."));
            }else{
                ejbFacade.create(current);
                for (int i = 0; i < facturaDetalleList.size(); i++) {
                    facturaDetalleList.get(i).setIdFacturaEncabezado(current);
                    tblFacturaDetalleFacade.create(facturaDetalleList.get(i));
                }
                current.setSubTotal(totalPagar - (totalPagar * 0.13));
                current.setIva(totalPagar * 0.13);
                current.setTotal(totalPagar);
                ejbFacade.edit(current);
                current = new TblFacturaEncabezado();
                facturaDetalleList = new ArrayList<>();
                totalPagar = 0.0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos Ingresados Exitosamente!"));
            }//fin ELSE
        }catch (ParseException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "Error:" + e));
        }//final try validacion de fecha
    }//fin de metodo create
    
    public void exportarPDF() throws JRException, IOException{
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("idPaciente", current.getIdPaciente());
        parametros.put("fechaFacturacion", current.getFechaFacturacion());
        parametros.put("total", current.getTotal());
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporteFacturacion.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getAllDetalleByFacEncabezado()));
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=facturacion.pdf");
        ServletOutputStream stream = response.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public List<TblFacturaDetalle> getAllDetalleByFacEncabezado(){
        return tblFacturaDetalleFacade.findAllDetalleByEncabezado(current.getIdFactura());
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
