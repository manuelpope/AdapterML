package com.mlAdapter.mlAdapter.controller;

import com.mlAdapter.mlAdapter.service.CsvTransformationServices;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The type Controller csv.
 */
@RestController
@Profile("Csv-file")
@Slf4j
public class ControllerCsv {

    @Autowired
    private CsvTransformationServices csvTransformationServices;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * Index string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping("/")
    public String index() throws IOException {
        // csvTransformationServices.projectSave();
        return "Greetings from Spring Boot! CSv";
    }

    /**
     * Create new simple data project.
     *
     * @param file the file
     * @param name the name
     * @throws IOException the io exception
     */
    @PostMapping("/addCsv")
    public void createNewSimpleDataProject(@RequestParam("file") MultipartFile file, @RequestParam("name") String
            name, @RequestParam("target") String target) throws IOException {


        Optional.ofNullable(file).filter(s -> !s.isEmpty()).map(r -> {
            try {
                csvTransformationServices.projectSave(r.getInputStream(), name, target);
                return ResponseEntity.status(HttpStatus.OK).build();


            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

        });


    }

    @PostMapping("/addCsvCleaned")
    public void createNewCleanedDataProject(@RequestParam("file") MultipartFile file, @RequestParam("name") String
            name, @RequestParam("target") String target) throws IOException {


        Optional.ofNullable(file).filter(s -> !s.isEmpty()).map(r -> {
            try {
                csvTransformationServices.projectSaveCleaned(r.getInputStream(), name, target);
                kafkaTemplate.send(new ProducerRecord<String, String>("AQAdapter", name)).get(10, TimeUnit.SECONDS);
                kafkaTemplate.send(new ProducerRecord<String, String>("AQAdapter", "eod")).get(10, TimeUnit.SECONDS);

                return ResponseEntity.status(HttpStatus.OK).build();


            } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

        });


    }


}