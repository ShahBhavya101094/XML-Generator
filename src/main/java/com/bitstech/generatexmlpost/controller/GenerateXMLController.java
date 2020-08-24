package com.bitstech.generatexmlpost.controller;

import com.bitstech.generatexmlpost.FileStorageProperties;
import com.bitstech.generatexmlpost.model.AccountDetails;
import com.bitstech.generatexmlpost.model.XMLResponse;
import com.bitstech.generatexmlpost.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GenerateXMLController {

    private final Path fileStorageLocation;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    public GenerateXMLController(FileStorageProperties fileStorageProperties) {

        String cat = fileStorageProperties.getUploadDir();
        if (null != cat && cat.length() > 0) {
            int endIndex = cat.lastIndexOf(File.separator);
            if (endIndex > 0) {
                cat = cat.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
            }
        }
        cat = cat + File.separator + "generateXML";


        this.fileStorageLocation = Paths.get(cat)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            System.out.println("Could not create the directory where the uploaded files will be stored.");
        }
    }

    @GetMapping("/xml/calling")
    public XMLResponse callMe() {

        XMLResponse xmlResponse1 = null;
        XMLResponse xmlResponse = new XMLResponse();
        xmlResponse.setDate("24-08-2020");
        xmlResponse.setHashKey("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        xmlResponse.setIpAddress("10.16.0.111");
        xmlResponse.setTotAmount(100000);
        xmlResponse.setTotStudent(254);
        Path targetLocation = this.fileStorageLocation.resolve("24-08-2020.xml");

        List<AccountDetails> accountDetails = new ArrayList<>();
        accountDetails.add(new AccountDetails(1, "GMCA", "123456789", "Maninagar", "FSC00012", "SBI Bank", 14, 10000));
        accountDetails.add(new AccountDetails(1, "LD", "123456789", "Maninagar", "FSC00012", "SBI Bank", 15, 10000));
        accountDetails.add(new AccountDetails(1, "Naran Lala", "123456789", "Maninagar", "FSC00012", "SBI Bank", 25, 10000));
        xmlResponse.setAccountDetails(accountDetails);
        try {

            System.out.println(targetLocation.toUri());
            File file = new File(targetLocation.toUri());

            JAXBContext jaxbContext = JAXBContext.newInstance(XMLResponse.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(xmlResponse, file);

            jaxbMarshaller.marshal(xmlResponse, System.out);
            System.out.println("Data : " + Files.readAllBytes(targetLocation).length);
            return fileUploadService.postFile("http://localhost:8081/xml/send", "24-08-2020.xml", Files.readAllBytes(targetLocation));


        } catch (JAXBException e) {

            e.printStackTrace();
            return xmlResponse1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xmlResponse1;
    }


}
