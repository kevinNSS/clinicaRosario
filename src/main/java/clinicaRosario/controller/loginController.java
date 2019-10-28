package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
import clinicaRosario.session.TblEmpleadosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@SessionScoped
public class loginController implements Serializable {

    @EJB
    private TblEmpleadosFacade tblEmpleadosFacade;
    private TblEmpleados tblEmpleados;

    private String usuario;
    private String contra;
    private String tipoUsuario;
    private String nombreUsuario;
    private String primerN;
    private String primerA;

    @PostConstruct
    public void init() {
        tblEmpleados = new TblEmpleados();
    }

    public TblEmpleados getSelected() {
        if (tblEmpleados == null) {
            tblEmpleados = new TblEmpleados();
        }
        return tblEmpleados;
    }

    public String iniciarSesion() {
        TblEmpleados us;
        String redireccion = null;
        tblEmpleados.setIdEmpleado(usuario);
        tblEmpleados.setContrasena(contra);
        try {
            us = tblEmpleadosFacade.iniciarSesion(tblEmpleados);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tblEmpleados", us);
                tipoUsuario = us.getIdCargo().getNombreCargo();
                primerN = us.getPrimerNombreEmpleado().toUpperCase();
                primerA = us.getPrimerApellidoEmpleado().toUpperCase();
                nombreUsuario = primerN + " " + primerA;
                redireccion = "index?faces-redirect=true";            
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
        return redireccion;
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
