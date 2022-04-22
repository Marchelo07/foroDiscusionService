package com.marcelo.foro.negocio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import com.google.gson.Gson;
import com.marcelo.foro.models.DtoDatos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Service
public class NDatos {

    @Value("classpath:config/app-setting.json")
    private Resource systemSetting;

    public List<DtoDatos> obtenerDatosForo () throws JsonProcessingException {

        List<DtoDatos> resul = new ArrayList<>();
        JSONPObject json1 = null;
        String strJson ="";

        strJson = readJson("datosForo.json");

       ObjectMapper mapper = new ObjectMapper();
       mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);

       DtoDatos[] dtoDatos = mapper.readValue(strJson,DtoDatos[].class);
       for(DtoDatos ot : dtoDatos){
           resul.add(ot);
       }
        return  resul;
    }

     public DtoInfo updateDataForo (List<DtoDatos> model){
        DtoInfo resul = new DtoInfo(false,"");
         Gson json = new Gson();
         //System.out.println(json.toJson(model));
         String strNuevoJson = json.toJson(model);

         try {

             File targetFile = new File(Thread.currentThread().getContextClassLoader().getResource("datosForo.json").getFile());
             //boolean success = targetFile.delete();
            updateJson(targetFile, strNuevoJson);

            resul.setExito(true);
            resul.setMensaje("Actualizado correctamente");
         }catch (Exception ex){

         }
        return resul;
     }

    private String readJson(String nameArchivo) {

        try {

            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(nameArchivo);
            if(inputStream != null){
                StringBuilder sb = new StringBuilder();
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bfReader = new BufferedReader(reader);
                String content = null;

                while((content = bfReader.readLine()) != null){
                    sb.append(content);
                }
                bfReader.close();
                return sb.toString();

            }
        }catch (Exception ex){
            return ex.getMessage();
        }
        return null;
    }

    private void updateJson (File files, String nuevaInfo){

        try {
            File file = files;//resource.getFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(nuevaInfo);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
