package com.lucasgiavaroti.spring_cardapio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("api/uploads")
public class UploadController {


    @PostMapping("/imagem")
    public ResponseEntity<String> uploadImagem(@RequestParam("file") MultipartFile file) {
        try{

            final String uploadDir = "C:/imagensCardapio/uploads/";
            File diretorio = new File(uploadDir);

            // Cria o caminho se não existe
            if(!diretorio.exists()){
                diretorio.mkdirs();
            }

            String fileName = file.getOriginalFilename();

            String filePath = uploadDir + UUID.randomUUID()+"_"+fileName;

            File destino = new File(filePath);

            // Salva no caminho
            file.transferTo(destino);

            // Retorna o caminho relativo
            return ResponseEntity.ok(filePath);

        }catch (Exception e){
            e.printStackTrace(); // Exibe o erro no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem.");
        }
    }

}
