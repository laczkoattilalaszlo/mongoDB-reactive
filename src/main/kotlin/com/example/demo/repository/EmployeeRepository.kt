package com.example.demo.repository

import com.example.demo.model.Employee
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface EmployeeRepository : ReactiveMongoRepository<Employee, ObjectId> {
}