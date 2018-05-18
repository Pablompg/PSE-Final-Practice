
package com.pablo.pse5.batch;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class BatchWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List list) throws Exception {
        for (String g: (List<String>)list){
            System.out.println(g);
        }
    }
    
}
