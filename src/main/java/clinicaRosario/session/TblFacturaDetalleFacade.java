/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblFacturaDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevin
 */
@Stateless
public class TblFacturaDetalleFacade extends AbstractFacade<TblFacturaDetalle> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblFacturaDetalleFacade() {
        super(TblFacturaDetalle.class);
    }
    
    public List<TblFacturaDetalle> findAllDetalleByEncabezado(int tblfacturaEncabezado){
        String consulta;
        consulta = "FROM TblFacturaDetalle fe WHERE fe.idFacturaEncabezado.idFactura = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, tblfacturaEncabezado);
        List<TblFacturaDetalle> lista = query.getResultList();
        return lista;
    }
    
}
