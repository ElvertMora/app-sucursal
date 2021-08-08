package com.it.prueba.sucursal.controllers

import com.it.prueba.sucursal.controllers.dto.BranchDto
import com.it.prueba.sucursal.services.BranchService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(description = "sucursal endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/sucursal")
class BranchController(private val branchService: BranchService) {

    @ApiOperation(value = "metodo que obtienen todas las sucursales")
    @GetMapping("")
    fun getAll() = branchService.getAll()

    @ApiOperation(value = "metodo que obtiene la tienda mas cercana a un punto dado ")
    @GetMapping("/nearby")
    fun findByLocation(
        @RequestParam(name = "latitude", required = true) latitude: Double,
        @RequestParam(name = "longitude", required = true) longitude: Double
    ) = branchService.findNearby(latitude, longitude)

    @ApiOperation(value = "method to get the segment strategy for each one of a list of brands")
    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(@RequestBody request: BranchDto) = branchService.create(request)

    @ApiOperation(value = "metodo para buscar una sucursal por id")
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = branchService.read(id)

    @ApiOperation(value = "metodo para actualizar una sucursal")
    @PutMapping("/{id}")
    fun update(@RequestBody request: BranchDto, @PathVariable id: Long) =
        branchService.update(id, request)

    @ApiOperation(value = "metodo para borrar una sucursal por id")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun delete(@PathVariable id: Long) = branchService.delete(id)

}
