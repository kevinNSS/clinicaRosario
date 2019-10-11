/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEstados.findAll", query = "SELECT t FROM TblEstados t")
    , @NamedQuery(name = "TblEstados.findByIdEstado", query = "SELECT t FROM TblEstados t WHERE t.idEstado = :idEstado")
    , @NamedQuery(name = "TblEstados.findByNombreEstado", query = "SELECT t FROM TblEstados t WHERE t.nombreEstado = :nombreEstado")
    , @NamedQuery(name = "TblEstados.findByDescripcionEstado", query = "SELECT t FROM TblEstados t WHERE t.descripcionEstado = :descripcionEstado")
    , @NamedQuery(name = "TblEstados.findByTipoEstado", query = "SELECT t FROM TblEstados t WHERE t.tipoEstado = :tipoEstado")})
public class TblEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_ESTADO")
    private String nombreEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION_ESTADO")
    private String descripcionEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "TIPO_ESTADO")
    private String tipoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoFacturaDetalle")
    private Collection<TblFacturaDetalle> tblFacturaDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoExamen")
    private Collection<TblExamenes> tblExamenesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection;
    @OneToMany(mappedBy = "idEstado")
    private Collection<TblEmpleados> tblEmpleadosCollection;

    public TblEstados() {
    }

    public TblEstados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public TblEstados(Integer idEstado, String nombreEstado, String descripcionEstado, String tipoEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.descripcionEstado = descripcionEstado;
        this.tipoEstado = tipoEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @XmlTransient
    public Collection<TblFacturaDetalle> getTblFacturaDetalleCollection() {
        return tblFacturaDetalleCollection;
    }

    public void setTblFacturaDetalleCollection(Collection<TblFacturaDetalle> tblFacturaDetalleCollection) {
        this.tblFacturaDetalleCollection = tblFacturaDetalleCollection;
    }

    @XmlTransient
    public Collection<TblExamenes> getTblExamenesCollection() {
        return tblExamenesCollection;
    }

    public void setTblExamenesCollection(Collection<TblExamenes> tblExamenesCollection) {
        this.tblExamenesCollection = tblExamenesCollection;
    }

    @XmlTransient
    public Collection<TblFacturaEncabezado> getTblFacturaEncabezadoCollection() {
        return tblFacturaEncabezadoCollection;
    }

    public void setTblFacturaEncabezadoCollection(Collection<TblFacturaEncabezado> tblFacturaEncabezadoCollection) {
        this.tblFacturaEncabezadoCollection = tblFacturaEncabezadoCollection;
    }

    @XmlTransient
    public Collection<TblEmpleados> getTblEmpleadosCollection() {
        return tblEmpleadosCollection;
    }

    public void setTblEmpleadosCollection(Collection<TblEmpleados> tblEmpleadosCollection) {
        this.tblEmpleadosCollection = tblEmpleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEstados)) {
            return false;
        }
        TblEstados other = (TblEstados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblEstados[ idEstado=" + idEstado + " ]";
    }
    
}
