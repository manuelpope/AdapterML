package com.mlAdapter.mlAdapter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * The type Project.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
public class ProjectCleaned {
    @Id
    private String id;
    private String nameProject;
    private List<List<String>> records;
    private Date dateInsert;
    private String colNameTarget;

}
