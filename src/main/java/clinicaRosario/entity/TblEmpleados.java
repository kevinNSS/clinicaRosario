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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEmpleados.findAll", query = "SELECT t FROM TblEmpleados t")
    , @NamedQuery(name = "TblEmpleados.findByIdEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "TblEmpleados.findByPrimerNombreEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.primerNombreEmpleado = :primerNombreEmpleado")
    , @NamedQuery(name = "TblEmpleados.findBySegundoNombreEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.segundoNombreEmpleado = :segundoNombreEmpleado")
    , @NamedQuery(name = "TblEmpleados.findByPrimerApellidoEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.primerApellidoEmpleado = :primerApellidoEmpleado")
    , @NamedQuery(name = "TblEmpleados.findBySegundoApellidoEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.segundoApellidoEmpleado = :segundoApellidoEmpleado")
    , @NamedQuery(name = "TblEmpleados.findByDireccionEmpleado", query = "SELECT t FROM TblEmpleados t WHERE t.direccionEmpleado = :direccionEmpleado")
    , @NamedQuery(name = "TblEmpleados.findByTelEmpleado1", query = "SELECT t FROM TblEmpleados t WHERE t.telEmpleado1 = :telEmpleado1")
    , @NamedQuery(name = "TblEmpleados.findByTelEmpleado2", query = "SELECT t FROM TblEmpleados t WHERE t.telEmpleado2 = :telEmpleado2")
    , @NamedQuery(name = "TblEmpleados.findByFechaNacimiento", query = "SELECT t FROM TblEmpleados t WHERE t.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "TblEmpleados.findByFechaContratacion", query = "SELECT t FROM TblEmpleados t WHERE t.fechaContratacion = :fechaContratacion")
    , @NamedQuery(name = "TblEmpleados.findBySueldo", query = "SELECT t FROM TblEmpleados t WHERE t.sueldo = :sueldo")})
public class TblEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID_EMPLEADO")
    private String idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_NOMBRE_EMPLEADO")
    private String primerNombreEmpleado;
    @Size(max = 20)
    @Column(name = "SEGUNDO_NOMBRE_EMPLEADO")
    private String segundoNombreEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMER_APELLIDO_EMPLEADO")
    private String primerApellidoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SEGUNDO_APELLIDO_EMPLEADO")
    private String segundoApellidoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DIRECCION_EMPLEADO")
    private String direccionEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TEL_EMPLEADO_1")
    private String telEmpleado1;
    @Size(max = 8)
    @Column(name = "TEL_EMPLEADO_2")
    private String telEmpleado2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTRATACION")
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUELDO")
    private double sueldo;
    @OneToMany(mappedBy = "usuarioEmpleado")
    private Collection<TblUsuarios> tblUsuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encargadoFacturacion")
    private Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection;
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO")
    @ManyToOne
    private TblCargos idCargo;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private TblEstados idEstado;

    public TblEmpleados() {
    }

    public TblEmpleados(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TblEmpleados(String idEmpleado, String primerNombreEmpleado, String primerApellidoEmpleado, String segundoApellidoEmpleado, String direccionEmpleado, String telEmpleado1, Date fechaNacimiento, Date fechaContratacion, double sueldo) {
        this.idEmpleado = idEmpleado;
        this.primerNombreEmpleado = primerNombreEmpleado;
        this.primerApellidoEmpleado = primerApellidoEmpleado;
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.telEmpleado1 = telEmpleado1;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
        this.sueldo = sueldo;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getPrimerNombreEmpleado() {
        return primerNombreEmpleado;
    }

    public void setPrimerNombreEmpleado(String primerNombreEmpleado) {
        this.primerNombreEmpleado = primerNombreEmpleado;
    }

    public String getSegundoNombreEmpleado() {
        return segundoNombreEmpleado;
    }

    public void setSegundoNombreEmpleado(String segundoNombreEmpleado) {
        this.segundoNombreEmpleado = segundoNombreEmpleado;
    }

    public String getPrimerApellidoEmpleado() {
        return primerApellidoEmpleado;
    }

    public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
        this.primerApellidoEmpleado = primerApellidoEmpleado;
    }

    public String getSegundoApellidoEmpleado() {
        return segundoApellidoEmpleado;
    }

    public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getTelEmpleado1() {
        return telEmpleado1;
    }

    public void setTelEmpleado1(String telEmpleado1) {
        this.telEmpleado1 = telEmpleado1;
    }

    public String getTelEmpleado2() {
        return telEmpleado2;
    }

    public void setTelEmpleado2(String telEmpleado2) {
        this.telEmpleado2 = telEmpleado2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @XmlTransient
    public Collection<TblUsuarios> getTblUsuariosCollection() {
        return tblUsuariosCollection;
    }

    public void setTblUsuariosCollection(Collection<TblUsuarios> tblUsuariosCollection) {
        this.tblUsuariosCollection = tblUsuariosCollection;
    }

    @XmlTransient
    public Collection<TblFacturaEncabezado> getTblFacturaEncabezadoCollection() {
        return tblFacturaEncabezadoCollection;
    }

    public void setTblFacturaEncabezadoCollection(Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection) {
        this.tblFacturaEncabezadoCollection = tblFacturaEncabezadoCollection;
    }

    public TblCargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(TblCargos idCargo) {
        this.idCargo = idCargo;
    }

    public TblEstados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(TblEstados idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEmpleados)) {
            return false;
        }
        TblEmpleados other = (TblEmpleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblEmpleados[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
