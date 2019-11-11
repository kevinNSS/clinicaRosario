package clinicaRosario.controller;

import clinicaRosario.entity.TblOrina;
import clinicaRosario.entity.TblPacientes;
import clinicaRosario.session.TblOrinaFacade;
import clinicaRosario.session.TblPacientesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
//importamos los paquetes para trabajar con las fechas
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.ParseException;

@Getter
@Setter
@Named("examOrinesController")
@ViewScoped
public class ExamOrinesController implements Serializable{
    
    @EJB
    private TblOrinaFacade tblOrinaFacade;
    private TblOrina tblOrina;
    @EJB
    private TblPacientesFacade tblPacientesFacade;
    private Boolean mostrarFormOrina = false;
    private Boolean mostrarTblOrina = true;

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
    
    @PostConstruct
    public void init(){
        tblOrina = new TblOrina();
    }
    
    public void mostrarFormularioOrina(){
        mostrarFormOrina = true;
        mostrarTblOrina = false;
        tblOrina = new TblOrina();
    }
    
    public void mostrarTablaOrina(){
        mostrarFormOrina = false;
        mostrarTblOrina = true;
    }
    
    public void nuevoFormulario(){
        tblOrina = new TblOrina();
    }
    
    public void registrarFormulario(){
        String fechaUsuario = tblOrina.getFechaRegistro();
        //obtener la fecha actual
        Date fechaHoy = Calendar.getInstance().getTime();

        try {
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Fecha ingresada no es valida!"));
            }else{
                tblOrinaFacade.create(tblOrina);
                tblOrina = new TblOrina();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Información Ingresada Exitosamente!"));
            }//Final IF ELSE
        }catch (ParseException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error: " + e));
        }//Final try
    }//Final metodo registrarFormulario
    
    public List<TblOrina> getAllExamenesOrina(){
        return tblOrinaFacade.findAll();
    }
    
    public List<TblPacientes> getAllPacientes(){
        return tblPacientesFacade.findAll();
    }
    
    public TblOrina getExamOrinesController(java.lang.Integer id){
        return tblOrinaFacade.find(id);
    }
    
    @FacesConverter (forClass = TblOrina.class)
    public static class ExamOrinesControllerConverter implements Converter{
        
        @Override
        public Object getAsObject (FacesContext facesContext, UIComponent component, String value){
            if(value == null || value.length() == 0){
                return null;
            }
            ExamOrinesController controller = (ExamOrinesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "examOrinesController");
            return controller.getExamOrinesController(getKey(value));
        }
        
        java.lang.Integer getKey(String value){
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }
        
        String getStringKey(java.lang.Integer value){
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object){
            if(object == null){
                return null;
            }
            if(object instanceof TblOrina){
                TblOrina o = (TblOrina) object;
                return getStringKey(o.getIdTblOrina());
            }else{
                throw new IllegalArgumentException("object" + object + "is of type" + object.getClass().getName() +"; expected type: " + TblOrina.class.getName());
            }
        }
    }
}
