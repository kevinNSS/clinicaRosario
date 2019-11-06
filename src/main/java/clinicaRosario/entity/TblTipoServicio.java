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
@Table(name = "tbl_tipo_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTipoServicio.findAll", query = "SELECT t FROM TblTipoServicio t")
    , @NamedQuery(name = "TblTipoServicio.findByIdServicio", query = "SELECT t FROM TblTipoServicio t WHERE t.idServicio = :idServicio")
    , @NamedQuery(name = "TblTipoServicio.findByTipoServicio", query = "SELECT t FROM TblTipoServicio t WHERE t.tipoServicio = :tipoServicio")
    , @NamedQuery(name = "TblTipoServicio.findByDescripcion", query = "SELECT t FROM TblTipoServicio t WHERE t.descripcion = :descripcion")})
public class TblTipoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPO_SERVICIO")
    private String tipoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public TblTipoServicio() {
    }

    public TblTipoServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public TblTipoServicio(Integer idServicio, String tipoServicio, String descripcion) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTipoServicio)) {
            return false;
        }
        TblTipoServicio other = (TblTipoServicio) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblTipoServicio[ idServicio=" + idServicio + " ]";
    }
    
}
