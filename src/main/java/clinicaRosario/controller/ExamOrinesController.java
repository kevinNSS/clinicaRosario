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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class ExamOrinesController implements Serializable{
    
    @EJB
    private TblOrinaFacade tblOrinaFacade;
    private TblOrina tblOrina;
    @EJB
    private TblPacientesFacade tblPacientesFacade;
    private Boolean mostrarFormOrina = false;
    private Boolean mostrarTblOrina = true;
    
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
        tblOrinaFacade.create(tblOrina);
        tblOrina = new TblOrina();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Información Ingresada Exitosamente!"));
    }
    
    public List<TblOrina> getAllExamenesOrina(){
        return tblOrinaFacade.findAll();
    }
    
    public List<TblPacientes> getAllPacientes(){
        return tblPacientesFacade.findAll();
    }
}
