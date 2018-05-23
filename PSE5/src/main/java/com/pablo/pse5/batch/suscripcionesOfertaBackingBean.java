package com.pablo.pse5.batch;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class suscripcionesOfertaBackingBean {
    
    SimpleDateFormat formatoDia = new SimpleDateFormat("dd-MM-yyyy");
    
    
    public void runJob() throws JobSecurityException {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("eod-suscripcionesOfertas", new Properties());
            System.out.println("Started job: with id: " + jobId);
        } catch (JobStartException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
