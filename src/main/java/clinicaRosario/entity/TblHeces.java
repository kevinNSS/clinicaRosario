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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2016
 */
@Entity
@Table(name = "tbl_heces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblHeces.findAll", query = "SELECT t FROM TblHeces t")
    , @NamedQuery(name = "TblHeces.findByIdTablaHeces", query = "SELECT t FROM TblHeces t WHERE t.idTablaHeces = :idTablaHeces")
    , @NamedQuery(name = "TblHeces.findByColor", query = "SELECT t FROM TblHeces t WHERE t.color = :color")
    , @NamedQuery(name = "TblHeces.findByConsistencia", query = "SELECT t FROM TblHeces t WHERE t.consistencia = :consistencia")
    , @NamedQuery(name = "TblHeces.findByMucus", query = "SELECT t FROM TblHeces t WHERE t.mucus = :mucus")
    , @NamedQuery(name = "TblHeces.findByHematies", query = "SELECT t FROM TblHeces t WHERE t.hematies = :hematies")
    , @NamedQuery(name = "TblHeces.findByLeucocitos", query = "SELECT t FROM TblHeces t WHERE t.leucocitos = :leucocitos")
    , @NamedQuery(name = "TblHeces.findByMacrofagos", query = "SELECT t FROM TblHeces t WHERE t.macrofagos = :macrofagos")
    , @NamedQuery(name = "TblHeces.findByRestosalimenticios", query = "SELECT t FROM TblHeces t WHERE t.restosalimenticios = :restosalimenticios")
    , @NamedQuery(name = "TblHeces.findByMicroscopicos", query = "SELECT t FROM TblHeces t WHERE t.microscopicos = :microscopicos")
    , @NamedQuery(name = "TblHeces.findByMacroscopicos", query = "SELECT t FROM TblHeces t WHERE t.macroscopicos = :macroscopicos")
    , @NamedQuery(name = "TblHeces.findByEntamoebahistolyca", query = "SELECT t FROM TblHeces t WHERE t.entamoebahistolyca = :entamoebahistolyca")
    , @NamedQuery(name = "TblHeces.findByEntamoebahistolyca2", query = "SELECT t FROM TblHeces t WHERE t.entamoebahistolyca2 = :entamoebahistolyca2")
    , @NamedQuery(name = "TblHeces.findByEntamoebacoli", query = "SELECT t FROM TblHeces t WHERE t.entamoebacoli = :entamoebacoli")
    , @NamedQuery(name = "TblHeces.findByEntamoebacoli2", query = "SELECT t FROM TblHeces t WHERE t.entamoebacoli2 = :entamoebacoli2")
    , @NamedQuery(name = "TblHeces.findByEndolimaxnana", query = "SELECT t FROM TblHeces t WHERE t.endolimaxnana = :endolimaxnana")
    , @NamedQuery(name = "TblHeces.findByEndolimaxnana2", query = "SELECT t FROM TblHeces t WHERE t.endolimaxnana2 = :endolimaxnana2")
    , @NamedQuery(name = "TblHeces.findByGiardiaLamblia", query = "SELECT t FROM TblHeces t WHERE t.giardiaLamblia = :giardiaLamblia")
    , @NamedQuery(name = "TblHeces.findByGiardiaLamblia2", query = "SELECT t FROM TblHeces t WHERE t.giardiaLamblia2 = :giardiaLamblia2")
    , @NamedQuery(name = "TblHeces.findByTrichomonasHominis", query = "SELECT t FROM TblHeces t WHERE t.trichomonasHominis = :trichomonasHominis")
    , @NamedQuery(name = "TblHeces.findByTrichomonasHominis2", query = "SELECT t FROM TblHeces t WHERE t.trichomonasHominis2 = :trichomonasHominis2")
    , @NamedQuery(name = "TblHeces.findByChilomastixmesnilli", query = "SELECT t FROM TblHeces t WHERE t.chilomastixmesnilli = :chilomastixmesnilli")
    , @NamedQuery(name = "TblHeces.findByChilomastixmesnilli2", query = "SELECT t FROM TblHeces t WHERE t.chilomastixmesnilli2 = :chilomastixmesnilli2")
    , @NamedQuery(name = "TblHeces.findByLodamoebabustchlli", query = "SELECT t FROM TblHeces t WHERE t.lodamoebabustchlli = :lodamoebabustchlli")
    , @NamedQuery(name = "TblHeces.findByLodamoebabustchlli2", query = "SELECT t FROM TblHeces t WHERE t.lodamoebabustchlli2 = :lodamoebabustchlli2")
    , @NamedQuery(name = "TblHeces.findByOtrosprotozoarios", query = "SELECT t FROM TblHeces t WHERE t.otrosprotozoarios = :otrosprotozoarios")
    , @NamedQuery(name = "TblHeces.findByAscarislumbricoides", query = "SELECT t FROM TblHeces t WHERE t.ascarislumbricoides = :ascarislumbricoides")
    , @NamedQuery(name = "TblHeces.findByTrichuristrichiura", query = "SELECT t FROM TblHeces t WHERE t.trichuristrichiura = :trichuristrichiura")
    , @NamedQuery(name = "TblHeces.findByUncinarias", query = "SELECT t FROM TblHeces t WHERE t.uncinarias = :uncinarias")
    , @NamedQuery(name = "TblHeces.findByStrongyloidesestercoralis", query = "SELECT t FROM TblHeces t WHERE t.strongyloidesestercoralis = :strongyloidesestercoralis")
    , @NamedQuery(name = "TblHeces.findByEnterobiusvermicularis", query = "SELECT t FROM TblHeces t WHERE t.enterobiusvermicularis = :enterobiusvermicularis")
    , @NamedQuery(name = "TblHeces.findByTaeniasp", query = "SELECT t FROM TblHeces t WHERE t.taeniasp = :taeniasp")
    , @NamedQuery(name = "TblHeces.findByOtrosmetazoarios", query = "SELECT t FROM TblHeces t WHERE t.otrosmetazoarios = :otrosmetazoarios")
    , @NamedQuery(name = "TblHeces.findByObservaciones", query = "SELECT t FROM TblHeces t WHERE t.observaciones = :observaciones")
    , @NamedQuery(name = "TblHeces.findByOtrosprotozoarios2", query = "SELECT t FROM TblHeces t WHERE t.otrosprotozoarios2 = :otrosprotozoarios2")})
public class TblHeces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabla_heces")
    private Integer idTablaHeces;
    @Size(max = 25)
    @Column(name = "Color")
    private String color;
    @Size(max = 25)
    @Column(name = "Consistencia")
    private String consistencia;
    @Size(max = 25)
    @Column(name = "Mucus")
    private String mucus;
    @Size(max = 25)
    @Column(name = "Hematies")
    private String hematies;
    @Size(max = 25)
    @Column(name = "Leucocitos")
    private String leucocitos;
    @Size(max = 25)
    @Column(name = "Macrofagos")
    private String macrofagos;
    @Size(max = 25)
    @Column(name = "Restos_alimenticios")
    private String restosalimenticios;
    @Size(max = 25)
    @Column(name = "Microscopicos")
    private String microscopicos;
    @Size(max = 25)
    @Column(name = "Macroscopicos")
    private String macroscopicos;
    @Size(max = 25)
    @Column(name = "Entamoeba_histolyca")
    private String entamoebahistolyca;
    @Size(max = 25)
    @Column(name = "Entamoeba_histolyca2")
    private String entamoebahistolyca2;
    @Size(max = 25)
    @Column(name = "Entamoeba_coli")
    private String entamoebacoli;
    @Size(max = 25)
    @Column(name = "Entamoeba_coli2")
    private String entamoebacoli2;
    @Size(max = 25)
    @Column(name = "Endolimax_nana")
    private String endolimaxnana;
    @Size(max = 25)
    @Column(name = "Endolimax_nana2")
    private String endolimaxnana2;
    @Size(max = 25)
    @Column(name = "Giardia_Lamblia")
    private String giardiaLamblia;
    @Size(max = 25)
    @Column(name = "Giardia_Lamblia2")
    private String giardiaLamblia2;
    @Size(max = 25)
    @Column(name = "Trichomonas_Hominis")
    private String trichomonasHominis;
    @Size(max = 25)
    @Column(name = "Trichomonas_Hominis2")
    private String trichomonasHominis2;
    @Size(max = 25)
    @Column(name = "Chilomastix_mesnilli")
    private String chilomastixmesnilli;
    @Size(max = 25)
    @Column(name = "Chilomastix_mesnilli2")
    private String chilomastixmesnilli2;
    @Size(max = 25)
    @Column(name = "Lodamoeba_bustchlli")
    private String lodamoebabustchlli;
    @Size(max = 25)
    @Column(name = "Lodamoeba_bustchlli2")
    private String lodamoebabustchlli2;
    @Size(max = 25)
    @Column(name = "Otros_protozoarios")
    private String otrosprotozoarios;
    @Size(max = 25)
    @Column(name = "Ascaris_lumbricoides")
    private String ascarislumbricoides;
    @Size(max = 25)
    @Column(name = "Trichuris_trichiura")
    private String trichuristrichiura;
    @Size(max = 25)
    @Column(name = "Uncinarias")
    private String uncinarias;
    @Size(max = 25)
    @Column(name = "Strongyloides_estercoralis")
    private String strongyloidesestercoralis;
    @Size(max = 25)
    @Column(name = "Enterobius_vermicularis")
    private String enterobiusvermicularis;
    @Size(max = 25)
    @Column(name = "Taenia_sp")
    private String taeniasp;
    @Size(max = 25)
    @Column(name = "Otros_metazoarios")
    private String otrosmetazoarios;
    @Size(max = 40)
    @Column(name = "Observaciones")
    private String observaciones;
    @Size(max = 25)
    @Column(name = "Otros_protozoarios2")
    private String otrosprotozoarios2;
    @OneToMany(mappedBy = "idTblHeces")
    private Collection<TblExpedientes> tblExpedientesCollection;

    public TblHeces() {
    }

    public TblHeces(Integer idTablaHeces) {
        this.idTablaHeces = idTablaHeces;
    }

    public Integer getIdTablaHeces() {
        return idTablaHeces;
    }

    public void setIdTablaHeces(Integer idTablaHeces) {
        this.idTablaHeces = idTablaHeces;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConsistencia() {
        return consistencia;
    }

    public void setConsistencia(String consistencia) {
        this.consistencia = consistencia;
    }

    public String getMucus() {
        return mucus;
    }

    public void setMucus(String mucus) {
        this.mucus = mucus;
    }

    public String getHematies() {
        return hematies;
    }

    public void setHematies(String hematies) {
        this.hematies = hematies;
    }

    public String getLeucocitos() {
        return leucocitos;
    }

    public void setLeucocitos(String leucocitos) {
        this.leucocitos = leucocitos;
    }

    public String getMacrofagos() {
        return macrofagos;
    }

    public void setMacrofagos(String macrofagos) {
        this.macrofagos = macrofagos;
    }

    public String getRestosalimenticios() {
        return restosalimenticios;
    }

    public void setRestosalimenticios(String restosalimenticios) {
        this.restosalimenticios = restosalimenticios;
    }

    public String getMicroscopicos() {
        return microscopicos;
    }

    public void setMicroscopicos(String microscopicos) {
        this.microscopicos = microscopicos;
    }

    public String getMacroscopicos() {
        return macroscopicos;
    }

    public void setMacroscopicos(String macroscopicos) {
        this.macroscopicos = macroscopicos;
    }

    public String getEntamoebahistolyca() {
        return entamoebahistolyca;
    }

    public void setEntamoebahistolyca(String entamoebahistolyca) {
        this.entamoebahistolyca = entamoebahistolyca;
    }

    public String getEntamoebahistolyca2() {
        return entamoebahistolyca2;
    }

    public void setEntamoebahistolyca2(String entamoebahistolyca2) {
        this.entamoebahistolyca2 = entamoebahistolyca2;
    }

    public String getEntamoebacoli() {
        return entamoebacoli;
    }

    public void setEntamoebacoli(String entamoebacoli) {
        this.entamoebacoli = entamoebacoli;
    }

    public String getEntamoebacoli2() {
        return entamoebacoli2;
    }

    public void setEntamoebacoli2(String entamoebacoli2) {
        this.entamoebacoli2 = entamoebacoli2;
    }

    public String getEndolimaxnana() {
        return endolimaxnana;
    }

    public void setEndolimaxnana(String endolimaxnana) {
        this.endolimaxnana = endolimaxnana;
    }

    public String getEndolimaxnana2() {
        return endolimaxnana2;
    }

    public void setEndolimaxnana2(String endolimaxnana2) {
        this.endolimaxnana2 = endolimaxnana2;
    }

    public String getGiardiaLamblia() {
        return giardiaLamblia;
    }

    public void setGiardiaLamblia(String giardiaLamblia) {
        this.giardiaLamblia = giardiaLamblia;
    }

    public String getGiardiaLamblia2() {
        return giardiaLamblia2;
    }

    public void setGiardiaLamblia2(String giardiaLamblia2) {
        this.giardiaLamblia2 = giardiaLamblia2;
    }

    public String getTrichomonasHominis() {
        return trichomonasHominis;
    }

    public void setTrichomonasHominis(String trichomonasHominis) {
        this.trichomonasHominis = trichomonasHominis;
    }

    public String getTrichomonasHominis2() {
        return trichomonasHominis2;
    }

    public void setTrichomonasHominis2(String trichomonasHominis2) {
        this.trichomonasHominis2 = trichomonasHominis2;
    }

    public String getChilomastixmesnilli() {
        return chilomastixmesnilli;
    }

    public void setChilomastixmesnilli(String chilomastixmesnilli) {
        this.chilomastixmesnilli = chilomastixmesnilli;
    }

    public String getChilomastixmesnilli2() {
        return chilomastixmesnilli2;
    }

    public void setChilomastixmesnilli2(String chilomastixmesnilli2) {
        this.chilomastixmesnilli2 = chilomastixmesnilli2;
    }

    public String getLodamoebabustchlli() {
        return lodamoebabustchlli;
    }

    public void setLodamoebabustchlli(String lodamoebabustchlli) {
        this.lodamoebabustchlli = lodamoebabustchlli;
    }

    public String getLodamoebabustchlli2() {
        return lodamoebabustchlli2;
    }

    public void setLodamoebabustchlli2(String lodamoebabustchlli2) {
        this.lodamoebabustchlli2 = lodamoebabustchlli2;
    }

    public String getOtrosprotozoarios() {
        return otrosprotozoarios;
    }

    public void setOtrosprotozoarios(String otrosprotozoarios) {
        this.otrosprotozoarios = otrosprotozoarios;
    }

    public String getAscarislumbricoides() {
        return ascarislumbricoides;
    }

    public void setAscarislumbricoides(String ascarislumbricoides) {
        this.ascarislumbricoides = ascarislumbricoides;
    }

    public String getTrichuristrichiura() {
        return trichuristrichiura;
    }

    public void setTrichuristrichiura(String trichuristrichiura) {
        this.trichuristrichiura = trichuristrichiura;
    }

    public String getUncinarias() {
        return uncinarias;
    }

    public void setUncinarias(String uncinarias) {
        this.uncinarias = uncinarias;
    }

    public String getStrongyloidesestercoralis() {
        return strongyloidesestercoralis;
    }

    public void setStrongyloidesestercoralis(String strongyloidesestercoralis) {
        this.strongyloidesestercoralis = strongyloidesestercoralis;
    }

    public String getEnterobiusvermicularis() {
        return enterobiusvermicularis;
    }

    public void setEnterobiusvermicularis(String enterobiusvermicularis) {
        this.enterobiusvermicularis = enterobiusvermicularis;
    }

    public String getTaeniasp() {
        return taeniasp;
    }

    public void setTaeniasp(String taeniasp) {
        this.taeniasp = taeniasp;
    }

    public String getOtrosmetazoarios() {
        return otrosmetazoarios;
    }

    public void setOtrosmetazoarios(String otrosmetazoarios) {
        this.otrosmetazoarios = otrosmetazoarios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOtrosprotozoarios2() {
        return otrosprotozoarios2;
    }

    public void setOtrosprotozoarios2(String otrosprotozoarios2) {
        this.otrosprotozoarios2 = otrosprotozoarios2;
    }

    @XmlTransient
    public Collection<TblExpedientes> getTblExpedientesCollection() {
        return tblExpedientesCollection;
    }

    public void setTblExpedientesCollection(Collection<TblExpedientes> tblExpedientesCollection) {
        this.tblExpedientesCollection = tblExpedientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablaHeces != null ? idTablaHeces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHeces)) {
            return false;
        }
        TblHeces other = (TblHeces) object;
        if ((this.idTablaHeces == null && other.idTablaHeces != null) || (this.idTablaHeces != null && !this.idTablaHeces.equals(other.idTablaHeces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaRosario.entity.TblHeces[ idTablaHeces=" + idTablaHeces + " ]";
    }
    
}
