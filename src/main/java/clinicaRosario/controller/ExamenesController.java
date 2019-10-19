package clinicaRosario.controller;

import clinicaRosario.entity.TblEstados;
import clinicaRosario.entity.TblExamenes;
import clinicaRosario.entity.TblTipoExamenes;
import clinicaRosario.session.TblEstadosFacade;
import clinicaRosario.session.TblExamenesFacade;
import clinicaRosario.session.TblTipoExamenesFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
@Named("examenesController")
@ViewScoped
public class ExamenesController implements Serializable {

    @EJB
    private TblExamenesFacade tblExamenesFacade;
    private TblExamenes tblExamenes;
    private Boolean mostrarTablaExamen = true;
    private Boolean mostrarFormExamen = false;
    @EJB
    private TblEstadosFacade tblEstadosFacade;
    private TblEstados tblEstados;
    private String tipoEstado = "examenes";
    @EJB
    private TblTipoExamenesFacade tblTipoExamenesFacade;

    Random numero = new Random();
    private int n1;
    private int n2;

    @PostConstruct
    public void init() {
        tblExamenes = new TblExamenes();
    }

    public TblExamenes getSelected() {
        if (tblExamenes == null) {
            tblExamenes = new TblExamenes();
        }
        return tblExamenes;
    }

    public void nuevoExamen() {
        tblExamenes = new TblExamenes();
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
            tblExamenes.setIdExamen(tblExamenes.getNombreExamen().substring(0, 1).toUpperCase() + tblExamenes.getNombreExamen().substring(tblExamenes.getNombreExamen().length() - 1).toUpperCase() + Integer.toString(n1) + Integer.toString(n2));
            tblExamenesFacade.create(tblExamenes);
            FacesContext.getCurrentInstance().getExternalContext().redirect("examenes.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos ingresados Exitosamente!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Necesita llenar los campos requeridos!"));
        }

    }

    public List<TblExamenes> getAll() {
        return tblExamenesFacade.findAll();
    }

    public List<TblEstados> getEstadosExamenes() {
        return tblEstadosFacade.finAllByTipoEstado(tipoEstado);
    }

    public List<TblTipoExamenes> getAllTipoExamen() {
        return tblTipoExamenesFacade.findAll();
    }
}
