/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class ReportesController implements Serializable {

    private Boolean mostrarReporteHeces = false;
    private Boolean mostrarReporteHemograma = false;
    private Boolean mostrarReporteOrina = false;
    private Boolean mostrarReporteFinanciero = false;
    private Boolean mostrarReportePacientes = false;

    public void mostrarReporteExamHeces() {
        mostrarReporteHeces = true;
        mostrarReporteHemograma = false;
        mostrarReporteOrina = false;
        mostrarReporteFinanciero = false;
        mostrarReportePacientes = false;
    }
    
    public void mostrarReporteExamHemogramas(){
        mostrarReporteHeces = false;
        mostrarReporteHemograma = true;
        mostrarReporteOrina = false;
        mostrarReporteFinanciero = false;
        mostrarReportePacientes = false;
    }
    
    public void mostrarReporteExamOrina(){
        mostrarReporteHeces = false;
        mostrarReporteHemograma = false;
        mostrarReporteOrina = true;
        mostrarReporteFinanciero = false;
        mostrarReportePacientes = false;
    }
    
    public void mostrarReporteFinancieroFacturas(){
        mostrarReporteHeces = false;
        mostrarReporteHemograma = false;
        mostrarReporteOrina = false;
        mostrarReporteFinanciero = true;
        mostrarReportePacientes = false;
    }
    
    public void mostrarReporteExamenesPacientes(){
        mostrarReporteHeces = false;
        mostrarReporteHemograma = false;
        mostrarReporteOrina = false;
        mostrarReporteFinanciero = false;
        mostrarReportePacientes = true;
    }
}
