/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblPromociones;
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
public class TblPromocionesFacade extends AbstractFacade<TblPromociones> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblPromocionesFacade() {
        super(TblPromociones.class);
    }
    
    public List<TblPromociones> allActivePromociones(String estadoActivo){
        String consulta;
        consulta = "FROM TblPromociones p WHERE p.estado.nombreEstado = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, estadoActivo);
        List<TblPromociones> lista = query.getResultList();
        return lista;
    }
    
}
