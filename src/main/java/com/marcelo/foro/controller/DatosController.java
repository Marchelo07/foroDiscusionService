package com.marcelo.foro.controller;

import com.marcelo.foro.models.DtoDatos;
import com.marcelo.foro.negocio.DtoInfo;
import com.marcelo.foro.negocio.DtoPrueba;
import com.marcelo.foro.negocio.NDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datosForo")
public class DatosController {


    @Autowired
    NDatos nDatos;

    @GetMapping("/obtenerDatos")
    public ResponseEntity<List<DtoDatos>> obtenerDatosForo (){
        try{

            return new ResponseEntity(nDatos.obtenerDatosForo(), HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateDataForo")
    public ResponseEntity<DtoInfo> updateDataForo(@RequestBody List<DtoDatos> model){
        try{
            return new ResponseEntity(nDatos.updateDataForo(model), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("/prueba")
    public List<String> prueba(@RequestBody DtoPrueba model){
        return List.of("01","02","03");
    }*/

}
