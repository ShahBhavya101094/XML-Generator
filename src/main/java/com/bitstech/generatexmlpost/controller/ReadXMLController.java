package com.bitstech.generatexmlpost.controller;

import com.bitstech.generatexmlpost.model.XMLResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ReadXMLController {

    @PostMapping("/xml/send")
    public XMLResponse callGeneratedXML(@RequestParam("file") MultipartFile file) {

        System.out.println("File name : " + file.getOriginalFilename());
        System.out.println("File Type : " + file.getName());
        System.out.println("File Content Type : " + file.getContentType());
        System.out.println("Read dATA : "+file.getSize());
        XMLResponse xmlResponse = null;
        try {

           // File file1 = new File();


            JAXBContext jaxbContext = JAXBContext.newInstance(XMLResponse.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            xmlResponse = (XMLResponse) jaxbUnmarshaller.unmarshal(file.getInputStream());

         //   System.out.println(xmlResponse);

        } catch (JAXBException | IOException e) {

            e.printStackTrace();

        }
        return xmlResponse;



    }
}
