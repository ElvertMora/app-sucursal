package com.it.prueba.sucursal.controllers.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.it.prueba.sucursal.annotations.NoArgsConstructor


@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class BranchDto (
    @JsonProperty("branch_id")
    val id :Long? = null,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    var distance: String = "0.0 metros"
)
