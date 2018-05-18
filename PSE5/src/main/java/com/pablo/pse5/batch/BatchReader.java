package com.pablo.pse5.batch;

import java.io.Serializable;
import java.util.Iterator;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Dependent
public class BatchReader extends AbstractItemReader {

    Iterator<SuscripcionesOfertaBatch> list;
    @PersistenceContext
    EntityManager em; 

    @Override
    public void open(Serializable checkpoint) throws Exception {
        list = em.createNamedQuery("Suscribir.CountSuscripcionesPorEmpresa", SuscripcionesOfertaBatch.class)
                .getResultList()
                .iterator();
    }

    @Override
    public Object readItem() throws Exception {
        SuscripcionesOfertaBatch s= null;
        if (list.hasNext()) {
            s = list.next();
        }
        return s;
    }

}
