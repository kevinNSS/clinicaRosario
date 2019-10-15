package clinicaRosario.controller;

import clinicaRosario.entity.TblEmpleados;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class VerificacionController implements Serializable {

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            TblEmpleados us = (TblEmpleados) context.getExternalContext().getSessionMap().get("tblEmpleados");
            
            if(us == null){
                context.getExternalContext().redirect("login.xhtml");
            }
        } catch (Exception e) {

        }
    }
}
