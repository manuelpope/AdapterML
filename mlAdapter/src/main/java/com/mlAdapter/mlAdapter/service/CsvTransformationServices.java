package com.mlAdapter.mlAdapter.service;

import com.mlAdapter.mlAdapter.dao.MongoProjectDaoI;
import com.mlAdapter.mlAdapter.model.Project;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CsvTransformationServices
{
    @Autowired
    private MongoProjectDaoI dao;

    public void projectSave(InputStream inputStream, String name) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        List<List<String>> records = new ArrayList<>();
            try (Scanner scanner = new Scanner(br);) {

                while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        Project project= new Project();
        project.setRecords(records);
        project.setDateInsert(new Date());
        project.setNameProject(name);
        dao.insert(project);


    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}