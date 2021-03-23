package com.mlAdapter.mlAdapter.dao;


import com.mlAdapter.mlAdapter.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProjectDaoI extends MongoRepository<Project, Integer> {


}