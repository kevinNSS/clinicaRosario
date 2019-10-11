/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "tbl_cargos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCargos.findAll", query = "SELECT t FROM TblCargos t")
    , @NamedQuery(name = "TblCargos.findByIdCargo", query = "SELECT t FROM TblCargos t WHERE t.idCargo = :idCargo")
    , @NamedQuery(name = "TblCargos.findByNombreCargo", query = "SELECT t FROM TblCargos t WHERE t.nombreCargo = :nombreCargo")
    , @NamedQuery(name = "TblCargos.findByDescripcion", query = "SELECT t FROM TblCargos t WHERE t.descripcion = :descripcion")})
public class TblCargos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARGO")
    private Integer idCargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBRE_CARGO")
    private String nombreCargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idCargo")
    private Collection<TblEmpleados> tblEmpleadosCollection;

    public TblCargos() {
    }

    public TblCargos(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public TblCargos(Integer idCargo, String nombreCargo, String descripcion) {
        this.idCargo = idCargo;
        this.nombreCargo = nombreCargo;
        this.descripcion = descripcion;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idCargo != null ? idCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCargos)) {
            return false;
        }
        TblCargos other = (TblCargos) object;
        if ((this.idCargo == null && other.idCargo != null) || (this.idCargo != null && !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblCargos[ idCargo=" + idCargo + " ]";
    }
    
}
