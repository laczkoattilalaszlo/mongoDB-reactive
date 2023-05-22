package com.example.demo.response

import com.example.demo.model.Employee

class EmployeeResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {

    companion object {
        fun fromEntity(employee: Employee): EmployeeResponse =
            EmployeeResponse(
                id = employee.id!!.toHexString(),
                firstName = employee.firstName,
                lastName = employee.lastName,
                email = employee.email
            )
    }

}