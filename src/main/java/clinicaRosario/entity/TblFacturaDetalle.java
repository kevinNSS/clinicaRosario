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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tbl_factura_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFacturaDetalle.findAll", query = "SELECT t FROM TblFacturaDetalle t")
    , @NamedQuery(name = "TblFacturaDetalle.findByIdFacturaDetalle", query = "SELECT t FROM TblFacturaDetalle t WHERE t.idFacturaDetalle = :idFacturaDetalle")
    , @NamedQuery(name = "TblFacturaDetalle.findByCantidad", query = "SELECT t FROM TblFacturaDetalle t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "TblFacturaDetalle.findBySubTotal", query = "SELECT t FROM TblFacturaDetalle t WHERE t.subTotal = :subTotal")
    , @NamedQuery(name = "TblFacturaDetalle.findByDescuento", query = "SELECT t FROM TblFacturaDetalle t WHERE t.descuento = :descuento")
    , @NamedQuery(name = "TblFacturaDetalle.findByTotal", query = "SELECT t FROM TblFacturaDetalle t WHERE t.total = :total")})
public class TblFacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FACTURA_DETALLE")
    private Integer idFacturaDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUB_TOTAL")
    private double subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO")
    private double descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @JoinColumn(name = "ID_FACTURA_ENCABEZADO", referencedColumnName = "ID_FACTURA")
    @ManyToOne(optional = false)
    private TblFacturaEncabezado idFacturaEncabezado;
    @JoinColumn(name = "ID_EXAMEN", referencedColumnName = "ID_EXAMEN")
    @ManyToOne(optional = false)
    private TblExamenes idExamen;
    @JoinColumn(name = "ESTADO_FACTURA_DETALLE", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private TblEstados estadoFacturaDetalle;

    public TblFacturaDetalle() {
    }

    public TblFacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public TblFacturaDetalle(Integer idFacturaDetalle, int cantidad, double subTotal, double descuento, double total) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.total = total;
    }

    public Integer getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TblFacturaEncabezado getIdFacturaEncabezado() {
        return idFacturaEncabezado;
    }

    public void setIdFacturaEncabezado(TblFacturaEncabezado idFacturaEncabezado) {
        this.idFacturaEncabezado = idFacturaEncabezado;
    }

    public TblExamenes getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(TblExamenes idExamen) {
        this.idExamen = idExamen;
    }

    public TblEstados getEstadoFacturaDetalle() {
        return estadoFacturaDetalle;
    }

    public void setEstadoFacturaDetalle(TblEstados estadoFacturaDetalle) {
        this.estadoFacturaDetalle = estadoFacturaDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacturaDetalle != null ? idFacturaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblFacturaDetalle)) {
            return false;
        }
        TblFacturaDetalle other = (TblFacturaDetalle) object;
        if ((this.idFacturaDetalle == null && other.idFacturaDetalle != null) || (this.idFacturaDetalle != null && !this.idFacturaDetalle.equals(other.idFacturaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblFacturaDetalle[ idFacturaDetalle=" + idFacturaDetalle + " ]";
    }
    
}
