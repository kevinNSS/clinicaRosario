/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblExpedientes;
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
public class TblExpedientesFacade extends AbstractFacade<TblExpedientes> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblExpedientesFacade() {
        super(TblExpedientes.class);
    }
    
    public List<TblExpedientes> findAllExpedientesHemograma(){
        String consulta;
        consulta = "FROM TblExpedientes e WHERE e.idTblHemograma != null";
        Query query = em.createQuery(consulta);
        List<TblExpedientes> lista = query.getResultList();
        return lista;
    }
}
