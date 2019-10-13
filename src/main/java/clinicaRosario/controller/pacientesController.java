package clinicaRosario.controller;

import clinicaRosario.entity.TblPacientes;
import clinicaRosario.session.TblPacientesFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

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

    public void ingresoPacientes() {
        n1 = numero.nextInt(10);
        n2 = numero.nextInt(10);
        n3 = numero.nextInt(10);
        n4 = numero.nextInt(10);
        tblPacientes.setIdPaciente(tblPacientes.getPrimerNombrePaciente().substring(0, 1).toUpperCase() + tblPacientes.getPrimerApellidoPaciente().substring(0, 1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4));
        tblPacientesFacade.create(tblPacientes);
    }
    
    public List<TblPacientes> getAll(){
        return tblPacientesFacade.findAll();
    }

}
