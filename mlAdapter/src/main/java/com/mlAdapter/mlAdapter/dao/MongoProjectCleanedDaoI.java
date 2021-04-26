package com.mlAdapter.mlAdapter.dao;


import com.mlAdapter.mlAdapter.model.Project;
import com.mlAdapter.mlAdapter.model.ProjectCleaned;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProjectCleanedDaoI extends MongoRepository<ProjectCleaned, Integer> {


}