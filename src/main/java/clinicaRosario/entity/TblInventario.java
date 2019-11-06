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
 * @author 2016
 */
@Entity
@Table(name = "tbl_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventario.findAll", query = "SELECT t FROM TblInventario t")
    , @NamedQuery(name = "TblInventario.findByIdInventario", query = "SELECT t FROM TblInventario t WHERE t.idInventario = :idInventario")
    , @NamedQuery(name = "TblInventario.findByNombreProducto", query = "SELECT t FROM TblInventario t WHERE t.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "TblInventario.findByStockProducto", query = "SELECT t FROM TblInventario t WHERE t.stockProducto = :stockProducto")
    , @NamedQuery(name = "TblInventario.findByUnidadMedida", query = "SELECT t FROM TblInventario t WHERE t.unidadMedida = :unidadMedida")})
public class TblInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID_INVENTARIO")
    private String idInventario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_PRODUCTO")
    private int stockProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "UNIDAD_MEDIDA")
    private String unidadMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInventario")
    private Collection<TblIngresoInventario> tblIngresoInventarioCollection;

    public TblInventario() {
    }

    public TblInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public TblInventario(String idInventario, String nombreProducto, int stockProducto, String unidadMedida) {
        this.idInventario = idInventario;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
        this.unidadMedida = unidadMedida;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @XmlTransient
    public Collection<TblIngresoInventario> getTblIngresoInventarioCollection() {
        return tblIngresoInventarioCollection;
    }

    public void setTblIngresoInventarioCollection(Collection<TblIngresoInventario> tblIngresoInventarioCollection) {
        this.tblIngresoInventarioCollection = tblIngresoInventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblInventario)) {
            return false;
        }
        TblInventario other = (TblInventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblInventario[ idInventario=" + idInventario + " ]";
    }
    
}
