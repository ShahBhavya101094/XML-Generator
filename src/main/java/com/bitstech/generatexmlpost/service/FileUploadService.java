package com.bitstech.generatexmlpost.service;

import com.bitstech.generatexmlpost.model.XMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class FileUploadService {

    private RestTemplate restTemplate;

    @Autowired
    public FileUploadService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public XMLResponse postFile(String url,String filename, byte[] someByteArray) {
        XMLResponse xmlResponse = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(filename)
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(someByteArray, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        try {
            ResponseEntity<XMLResponse> response = restTemplate.exchange(
                   url,
                    HttpMethod.POST,
                    requestEntity,
                    XMLResponse.class
                    );
            xmlResponse = response.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return xmlResponse;
    }
}