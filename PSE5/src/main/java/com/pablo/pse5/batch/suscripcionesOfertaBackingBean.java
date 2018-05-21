package com.pablo.pse5.batch;

import com.pablo.pse5.client.InformesClientBean;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.operations.NoSuchJobExecutionException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class suscripcionesOfertaBackingBean {
    

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
