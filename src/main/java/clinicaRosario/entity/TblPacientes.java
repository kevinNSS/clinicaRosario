/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPacientes.findAll", query = "SELECT t FROM TblPacientes t")
    , @NamedQuery(name = "TblPacientes.findByIdPaciente", query = "SELECT t FROM TblPacientes t WHERE t.idPaciente = :idPaciente")
    , @NamedQuery(name = "TblPacientes.findByPrimerNombrePaciente", query = "SELECT t FROM TblPacientes t WHERE t.primerNombrePaciente = :primerNombrePaciente")
    , @NamedQuery(name = "TblPacientes.findBySegundoNombrePaciente", query = "SELECT t FROM TblPacientes t WHERE t.segundoNombrePaciente = :segundoNombrePaciente")
    , @NamedQuery(name = "TblPacientes.findByPrimerApellidoPaciente", query = "SELECT t FROM TblPacientes t WHERE t.primerApellidoPaciente = :primerApellidoPaciente")
    , @NamedQuery(name = "TblPacientes.findBySegundoApellidoPaciente", query = "SELECT t FROM TblPacientes t WHERE t.segundoApellidoPaciente = :segundoApellidoPaciente")
    , @NamedQuery(name = "TblPacientes.findByDireccionPaciente", query = "SELECT t FROM TblPacientes t WHERE t.direccionPaciente = :direccionPaciente")
    , @NamedQuery(name = "TblPacientes.findByTelPaciente", query = "SELECT t FROM TblPacientes t WHERE t.telPaciente = :telPaciente")
    , @NamedQuery(name = "TblPacientes.findByCorreoPaciente", query = "SELECT t FROM TblPacientes t WHERE t.correoPaciente = :correoPaciente")
    , @NamedQuery(name = "TblPacientes.findByTelResponsable", query = "SELECT t FROM TblPacientes t WHERE t.telResponsable = :telResponsable")
    , @NamedQuery(name = "TblPacientes.findBySexo", query = "SELECT t FROM TblPacientes t WHERE t.sexo = :sexo")
    , @NamedQuery(name = "TblPacientes.findByFechaNacimiento", query = "SELECT t FROM TblPacientes t WHERE t.fechaNacimiento = :fechaNacimiento")})
public class TblPacientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID_PACIENTE")
    private String idPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_NOMBRE_PACIENTE")
    private String primerNombrePaciente;
    @Size(max = 20)
    @Column(name = "SEGUNDO_NOMBRE_PACIENTE")
    private String segundoNombrePaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_APELLIDO_PACIENTE")
    private String primerApellidoPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEGUNDO_APELLIDO_PACIENTE")
    private String segundoApellidoPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DIRECCION_PACIENTE")
    private String direccionPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TEL_PACIENTE")
    private String telPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "CORREO_PACIENTE")
    private String correoPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TEL_RESPONSABLE")
    private String telResponsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SEXO")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<TblExpedientes> tblExpedientesCollection;

    public TblPacientes() {
    }

    public TblPacientes(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public TblPacientes(String idPaciente, String primerNombrePaciente, String primerApellidoPaciente, String segundoApellidoPaciente, String direccionPaciente, String telPaciente, String correoPaciente, String telResponsable, String sexo, String fechaNacimiento) {
        this.idPaciente = idPaciente;
        this.primerNombrePaciente = primerNombrePaciente;
        this.primerApellidoPaciente = primerApellidoPaciente;
        this.segundoApellidoPaciente = segundoApellidoPaciente;
        this.direccionPaciente = direccionPaciente;
        this.telPaciente = telPaciente;
        this.correoPaciente = correoPaciente;
        this.telResponsable = telResponsable;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getPrimerNombrePaciente() {
        return primerNombrePaciente;
    }

    public void setPrimerNombrePaciente(String primerNombrePaciente) {
        this.primerNombrePaciente = primerNombrePaciente;
    }

    public String getSegundoNombrePaciente() {
        return segundoNombrePaciente;
    }

    public void setSegundoNombrePaciente(String segundoNombrePaciente) {
        this.segundoNombrePaciente = segundoNombrePaciente;
    }

    public String getPrimerApellidoPaciente() {
        return primerApellidoPaciente;
    }

    public void setPrimerApellidoPaciente(String primerApellidoPaciente) {
        this.primerApellidoPaciente = primerApellidoPaciente;
    }

    public String getSegundoApellidoPaciente() {
        return segundoApellidoPaciente;
    }

    public void setSegundoApellidoPaciente(String segundoApellidoPaciente) {
        this.segundoApellidoPaciente = segundoApellidoPaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getTelPaciente() {
        return telPaciente;
    }

    public void setTelPaciente(String telPaciente) {
        this.telPaciente = telPaciente;
    }

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
    }

    public String getTelResponsable() {
        return telResponsable;
    }

    public void setTelResponsable(String telResponsable) {
        this.telResponsable = telResponsable;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @XmlTransient
    public Collection<TblFacturaEncabezado> getTblFacturaEncabezadoCollection() {
        return tblFacturaEncabezadoCollection;
    }

    public void setTblFacturaEncabezadoCollection(Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection) {
        this.tblFacturaEncabezadoCollection = tblFacturaEncabezadoCollection;
    }

    @XmlTransient
    public Collection<TblExpedientes> getTblExpedientesCollection() {
        return tblExpedientesCollection;
    }

    public void setTblExpedientesCollection(Collection<TblExpedientes> tblExpedientesCollection) {
        this.tblExpedientesCollection = tblExpedientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPacientes)) {
            return false;
        }
        TblPacientes other = (TblPacientes) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblPacientes[ idPaciente=" + idPaciente + " ]";
    }
    
}
