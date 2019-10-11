/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_ingreso_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIngresoInventario.findAll", query = "SELECT t FROM TblIngresoInventario t")
    , @NamedQuery(name = "TblIngresoInventario.findByIdIngresoInventario", query = "SELECT t FROM TblIngresoInventario t WHERE t.idIngresoInventario = :idIngresoInventario")
    , @NamedQuery(name = "TblIngresoInventario.findByCantidad", query = "SELECT t FROM TblIngresoInventario t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "TblIngresoInventario.findByProveedor", query = "SELECT t FROM TblIngresoInventario t WHERE t.proveedor = :proveedor")
    , @NamedQuery(name = "TblIngresoInventario.findByFechaIngreso", query = "SELECT t FROM TblIngresoInventario t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "TblIngresoInventario.findByFechaCaducidad", query = "SELECT t FROM TblIngresoInventario t WHERE t.fechaCaducidad = :fechaCaducidad")})
public class TblIngresoInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INGRESO_INVENTARIO")
    private Integer idIngresoInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVEEDOR")
    private int proveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @JoinColumn(name = "ID_INVENTARIO", referencedColumnName = "ID_INVENTARIO")
    @ManyToOne(optional = false)
    private TblInventario idInventario;

    public TblIngresoInventario() {
    }

    public TblIngresoInventario(Integer idIngresoInventario) {
        this.idIngresoInventario = idIngresoInventario;
    }

    public TblIngresoInventario(Integer idIngresoInventario, int cantidad, int proveedor, Date fechaIngreso, Date fechaCaducidad) {
        this.idIngresoInventario = idIngresoInventario;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getIdIngresoInventario() {
        return idIngresoInventario;
    }

    public void setIdIngresoInventario(Integer idIngresoInventario) {
        this.idIngresoInventario = idIngresoInventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public TblInventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(TblInventario idInventario) {
        this.idInventario = idInventario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngresoInventario != null ? idIngresoInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIngresoInventario)) {
            return false;
        }
        TblIngresoInventario other = (TblIngresoInventario) object;
        if ((this.idIngresoInventario == null && other.idIngresoInventario != null) || (this.idIngresoInventario != null && !this.idIngresoInventario.equals(other.idIngresoInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblIngresoInventario[ idIngresoInventario=" + idIngresoInventario + " ]";
    }
    
}
