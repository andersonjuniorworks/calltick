package com.andersonjunior.calltick.utils;

import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Backup extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    
        String arquivo = "C:\\Calltick\\backups";
        arquivo = arquivo + "\\calltickdb_" + new java.util.Date().getTime() + ".sql";
        String caminho = "C:\\calltick\\utils\\mysqldump.exe";
        try {
          Runtime bck = Runtime.getRuntime();
          bck.exec(caminho + " -v -v -v --host=localhost --user=root --password=printf@javadev --port=3306 --protocol=tcp --force --allow-keywords --compress  --add-drop-table --default-character-set=latin1 --hex-blob  --result-file=" + arquivo + " --databases calltickdb");
        } catch (IOException e) {
          System.out.println(e);
        } 

    }

}
