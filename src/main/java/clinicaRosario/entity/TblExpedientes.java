/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2016
 */
@Entity
@Table(name = "tbl_expedientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblExpedientes.findAll", query = "SELECT t FROM TblExpedientes t")
    , @NamedQuery(name = "TblExpedientes.findByIdExpediente", query = "SELECT t FROM TblExpedientes t WHERE t.idExpediente = :idExpediente")
    , @NamedQuery(name = "TblExpedientes.findByResultado", query = "SELECT t FROM TblExpedientes t WHERE t.resultado = :resultado")
    , @NamedQuery(name = "TblExpedientes.findByFechaIngreso", query = "SELECT t FROM TblExpedientes t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "TblExpedientes.findByReportadoPor", query = "SELECT t FROM TblExpedientes t WHERE t.reportadoPor = :reportadoPor")})
public class TblExpedientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EXPEDIENTE")
    private Integer idExpediente;
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "RESULTADO")
    private String resultado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    @Basic(optional = false)
    @Column(name = "REPORTADO_POR")
    private int reportadoPor;
    @JoinColumn(name = "ID_EXAMEN", referencedColumnName = "ID_EXAMEN")
    @ManyToOne(optional = false)
    private TblExamenes idExamen;
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "ID_PACIENTE")
    @ManyToOne(optional = false)
    private TblPacientes idPaciente;
    @JoinColumn(name = "ID_TBL_HECES", referencedColumnName = "id_tabla_heces")
    @ManyToOne
    private TblHeces idTblHeces;
    @JoinColumn(name = "ID_TBL_HEMOGRAMA", referencedColumnName = "id_tbl_hemograma")
    @ManyToOne
    private TblHemograma idTblHemograma;
    @JoinColumn(name = "ID_TBL_ORINA", referencedColumnName = "id_tbl_orina")
    @ManyToOne
    private TblOrina idTblOrina;

    public TblExpedientes() {
    }

    public TblExpedientes(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public TblExpedientes(Integer idExpediente, String resultado, String fechaIngreso, int reportadoPor) {
        this.idExpediente = idExpediente;
        this.resultado = resultado;
        this.fechaIngreso = fechaIngreso;
        this.reportadoPor = reportadoPor;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(int reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public TblExamenes getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(TblExamenes idExamen) {
        this.idExamen = idExamen;
    }

    public TblPacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(TblPacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    public TblHeces getIdTblHeces() {
        return idTblHeces;
    }

    public void setIdTblHeces(TblHeces idTblHeces) {
        this.idTblHeces = idTblHeces;
    }

    public TblHemograma getIdTblHemograma() {
        return idTblHemograma;
    }

    public void setIdTblHemograma(TblHemograma idTblHemograma) {
        this.idTblHemograma = idTblHemograma;
    }

    public TblOrina getIdTblOrina() {
        return idTblOrina;
    }

    public void setIdTblOrina(TblOrina idTblOrina) {
        this.idTblOrina = idTblOrina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpediente != null ? idExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblExpedientes)) {
            return false;
        }
        TblExpedientes other = (TblExpedientes) object;
        if ((this.idExpediente == null && other.idExpediente != null) || (this.idExpediente != null && !this.idExpediente.equals(other.idExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblExpedientes[ idExpediente=" + idExpediente + " ]";
    }
    
}
