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
 * @author 2016
 */
@Entity
@Table(name = "tbl_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProveedores.findAll", query = "SELECT t FROM TblProveedores t")
    , @NamedQuery(name = "TblProveedores.findByIdProveedor", query = "SELECT t FROM TblProveedores t WHERE t.idProveedor = :idProveedor")
    , @NamedQuery(name = "TblProveedores.findByNombreProveedor", query = "SELECT t FROM TblProveedores t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TblProveedores.findByDireccionProveedor", query = "SELECT t FROM TblProveedores t WHERE t.direccionProveedor = :direccionProveedor")
    , @NamedQuery(name = "TblProveedores.findByCorreoProveedor", query = "SELECT t FROM TblProveedores t WHERE t.correoProveedor = :correoProveedor")
    , @NamedQuery(name = "TblProveedores.findByTel1Proveedor", query = "SELECT t FROM TblProveedores t WHERE t.tel1Proveedor = :tel1Proveedor")
    , @NamedQuery(name = "TblProveedores.findByTel2Proveedor", query = "SELECT t FROM TblProveedores t WHERE t.tel2Proveedor = :tel2Proveedor")})
public class TblProveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DIRECCION_PROVEEDOR")
    private String direccionProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CORREO_PROVEEDOR")
    private String correoProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TEL1_PROVEEDOR")
    private String tel1Proveedor;
    @Size(max = 8)
    @Column(name = "TEL2_PROVEEDOR")
    private String tel2Proveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<TblIngresoInventario> tblIngresoInventarioCollection;

    public TblProveedores() {
    }

    public TblProveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public TblProveedores(Integer idProveedor, String nombreProveedor, String direccionProveedor, String correoProveedor, String tel1Proveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccionProveedor = direccionProveedor;
        this.correoProveedor = correoProveedor;
        this.tel1Proveedor = tel1Proveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getTel1Proveedor() {
        return tel1Proveedor;
    }

    public void setTel1Proveedor(String tel1Proveedor) {
        this.tel1Proveedor = tel1Proveedor;
    }

    public String getTel2Proveedor() {
        return tel2Proveedor;
    }

    public void setTel2Proveedor(String tel2Proveedor) {
        this.tel2Proveedor = tel2Proveedor;
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
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProveedores)) {
            return false;
        }
        TblProveedores other = (TblProveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblProveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
