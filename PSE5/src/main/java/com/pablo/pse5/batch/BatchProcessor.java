package com.pablo.pse5.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class BatchProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object t) throws Exception {

        String fila;
        SuscripcionesOfertaBatch suscripciones = (SuscripcionesOfertaBatch) t;
        fila = suscripciones.getNumSuscripciones() + "," + suscripciones.getNombreOferta();
        return t;
    }
    


}
