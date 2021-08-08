package com.it.prueba.sucursal

import com.it.prueba.sucursal.controllers.dto.BranchDto
import com.it.prueba.sucursal.repositories.entities.Branch
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

fun BranchDto.toEntity():Branch = Branch(
    id = this.id,
    address = this.address,
    latitude = this.latitude,
    longitude = this.longitude
)

fun Branch.toDto():BranchDto = BranchDto(
    id = this.id,
    address = this.address,
    latitude = this.latitude,
    longitude = this.longitude
)

fun Branch.distance(latitude: Double, longitude: Double): Double =
    if (this.latitude == latitude && this.longitude == longitude) {
        0.0
    } else {
        val theta = this.longitude - longitude
        var dist = sin(Math.toRadians(this.latitude)) * sin(Math.toRadians(latitude)) + Math.cos(
            Math.toRadians(this.latitude)
        ) * cos(
            Math.toRadians(latitude)
        ) * cos(Math.toRadians(theta))
        dist = acos(dist)
        dist = Math.toDegrees(dist)
        dist *= 60 * 1.1515
        dist * 1.609344
    }
