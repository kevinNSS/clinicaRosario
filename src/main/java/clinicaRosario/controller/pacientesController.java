package clinicaRosario.controller;

import clinicaRosario.entity.TblPacientes;
import clinicaRosario.session.TblPacientesFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
//importamos los paquetes para trabajar con mensajes
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevin
 */
@Getter
@Setter
@Named
@ViewScoped
public class pacientesController implements Serializable {

    private TblPacientes tblPacientes;
    @EJB
    private TblPacientesFacade tblPacientesFacade;
    private String ResultadoNumero;
    private Boolean mostrarFormIngreso = false;
    private Boolean mostrarTablaPaciente = true;
     //formato para la fecha actual y indicamos el formato
    DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    //fecha actual en date a utilizar en IF con formato
    private Date dateHoy;
    //metodo set para convertir dateHoy a tipo Date(dateNow)
    public void setConversionFechaHoy(Date dateNow){
        try {
           String fechaAhora = formatoFecha.format(dateNow);
           this.dateHoy = formatoFecha.parse(fechaAhora);
        } catch (ParseException e) {
            
        }
    }
    //fecha del usuario convertida a date con formato para IF
    private Date dateUser;
    //metodo set para convertir fechaUsuario a Date
    public void setConversionFecha(String fechaUsuario) throws ParseException{
        this.dateUser = formatoFecha.parse(fechaUsuario);
    }
    
    Random numero = new Random();
    private int n1;
    private int n2;
    private int n3;
    private int n4;
    
    @PostConstruct
    public void init() {
        tblPacientes = new TblPacientes();
    }
    
    public void nuevoPaciente(){
        tblPacientes = new TblPacientes();
        mostrarFormIngreso = true;
        mostrarTablaPaciente = false;
    }
    
    public void mostrarTabla(){
        mostrarFormIngreso = false;
        mostrarTablaPaciente = true;
    }

    public void ingresoPacientes() throws IOException {
        String fechaUsuario = tblPacientes.getFechaNacimiento();
        //capturar fecha de hoy
        Date fechaHoy = Calendar.getInstance().getTime();
        try {
            setConversionFecha(fechaUsuario);
            setConversionFechaHoy(fechaHoy);
            if (dateUser.after(dateHoy) || dateUser.equals(dateHoy)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifica!", "La fecha ingresada no es valida."));
            }else{
                n1 = numero.nextInt(10);
                n2 = numero.nextInt(10);
                n3 = numero.nextInt(10);
                n4 = numero.nextInt(10);
                tblPacientes.setIdPaciente(tblPacientes.getPrimerNombrePaciente().substring(0, 1).toUpperCase() + tblPacientes.getPrimerApellidoPaciente().substring(0, 1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4));
                tblPacientesFacade.create(tblPacientes);
                tblPacientes = new TblPacientes();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos ingresados Exitosamente!"));
            }
        } catch (ParseException e) {
          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifica!", "Error al interpretar "));
        }
    }
    
    public List<TblPacientes> getAll(){
        return tblPacientesFacade.findAll();
    }

}
