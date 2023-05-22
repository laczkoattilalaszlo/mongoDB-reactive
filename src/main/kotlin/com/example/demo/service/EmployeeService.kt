package com.example.demo.service

import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import com.example.demo.request.EmployeeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    fun createEmployee(request: EmployeeRequest): Mono<Employee> {
        return employeeRepository.save(
            Employee(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email
            )
        )
    }

    fun findAll(): Flux<Employee> =
        employeeRepository.findAll()

    fun findById(id: ObjectId): Mono<Employee> =
        employeeRepository.findById(id)
            .switchIfEmpty {
                Mono.error(
                    Exception("Employee with the given id ($id) not found")
                )
            }

    fun updateEmployee(id: ObjectId, request: EmployeeRequest): Mono<Employee> {
        val employeeToUpdate = findById(id)

        return employeeToUpdate.flatMap {
            employeeRepository.save(
                it.apply {
                    firstName = request.firstName
                    lastName = request.lastName
                    email = request.email
                }
            )
        }
    }

    fun deleteById(id: ObjectId): Mono<Void> {
       return employeeRepository.findById(id)
           .flatMap { employeeRepository.delete(it) }
    }

}