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
import javax.persistence.Lob;
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
@Table(name = "tbl_img")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblImg.findAll", query = "SELECT t FROM TblImg t")
    , @NamedQuery(name = "TblImg.findByIdImagen", query = "SELECT t FROM TblImg t WHERE t.idImagen = :idImagen")})
public class TblImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_IMAGEN")
    private Integer idImagen;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "IMG")
    private byte[] img;

    public TblImg() {
    }

    public TblImg(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public TblImg(Integer idImagen, byte[] img) {
        this.idImagen = idImagen;
        this.img = img;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblImg)) {
            return false;
        }
        TblImg other = (TblImg) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblImg[ idImagen=" + idImagen + " ]";
    }
    
}
