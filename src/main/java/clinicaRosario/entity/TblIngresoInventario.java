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
@Table(name = "tbl_ingreso_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIngresoInventario.findAll", query = "SELECT t FROM TblIngresoInventario t")
    , @NamedQuery(name = "TblIngresoInventario.findByIdIngresoInventario", query = "SELECT t FROM TblIngresoInventario t WHERE t.idIngresoInventario = :idIngresoInventario")
    , @NamedQuery(name = "TblIngresoInventario.findByCantidad", query = "SELECT t FROM TblIngresoInventario t WHERE t.cantidad = :cantidad")
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
    @Size(min = 1, max = 10)
    @Column(name = "FECHA_INGRESO")
    private String fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FECHA_CADUCIDAD")
    private String fechaCaducidad;
    @JoinColumn(name = "ID_INVENTARIO", referencedColumnName = "ID_INVENTARIO")
    @ManyToOne(optional = false)
    private TblInventario idInventario;
    @JoinColumn(name = "PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private TblProveedores proveedor;

    public TblIngresoInventario() {
    }

    public TblIngresoInventario(Integer idIngresoInventario) {
        this.idIngresoInventario = idIngresoInventario;
    }

    public TblIngresoInventario(Integer idIngresoInventario, int cantidad, String fechaIngreso, String fechaCaducidad) {
        this.idIngresoInventario = idIngresoInventario;
        this.cantidad = cantidad;
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

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public TblInventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(TblInventario idInventario) {
        this.idInventario = idInventario;
    }

    public TblProveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(TblProveedores proveedor) {
        this.proveedor = proveedor;
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
