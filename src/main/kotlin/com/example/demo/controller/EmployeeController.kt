package com.example.demo.controller

import com.example.demo.request.EmployeeRequest
import com.example.demo.response.EmployeeResponse
import com.example.demo.service.EmployeeService
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @PostMapping
    fun createEmployee(@RequestBody request: EmployeeRequest): Mono<EmployeeResponse> {
        return employeeService.createEmployee(request)
            .map { EmployeeResponse.fromEntity(it) }
    }

    @GetMapping
    fun findAllEmployees(): Flux<EmployeeResponse> {
        return employeeService.findAll()
            .map { EmployeeResponse.fromEntity(it) }
    }

    @GetMapping("/{id}")
    fun findEmployeeById(@PathVariable id: ObjectId): Mono<EmployeeResponse> {
        return employeeService.findById(id)
            .map { EmployeeResponse.fromEntity(it) }
    }

    @PutMapping("/{id}")
    fun updateUpdateEmployee(
        @PathVariable id: ObjectId,
        @RequestBody request: EmployeeRequest
    ): Mono<EmployeeResponse> {
        return employeeService.updateEmployee(id, request)
            .map { EmployeeResponse.fromEntity(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: ObjectId): Mono<Void> {
        return employeeService.deleteById(id)
    }

}