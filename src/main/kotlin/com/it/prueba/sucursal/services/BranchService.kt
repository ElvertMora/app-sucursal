package com.it.prueba.sucursal.services

import com.it.prueba.sucursal.controllers.dto.BranchDto
import com.it.prueba.sucursal.distance
import com.it.prueba.sucursal.exceptions.HttpStatusException
import com.it.prueba.sucursal.repositories.BranchRepository
import com.it.prueba.sucursal.repositories.entities.Branch
import com.it.prueba.sucursal.toDto
import com.it.prueba.sucursal.toEntity
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BranchService(
    private val branchRepository: BranchRepository
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun create(branchDto: BranchDto): Long {
        val response = kotlin.runCatching {
            val branch = branchDto.toEntity()
            branchRepository.save(branch)
        }.onFailure { logger.error("Error creating branch", it) }
            .onSuccess { logger.info("Branch created ${it.id}")
            }.getOrThrow()
        return response?.id ?:throw HttpStatusException(HttpStatus.CONFLICT, "no se pudo crear el elemento")
    }

    fun read(id: Long): BranchDto {
        val entity = branchRepository.findById(id)
        if (entity.isPresent) {
            return entity.get().toDto()
        }
        logger.error("Branch not Found $id")
        throw HttpStatusException(HttpStatus.NOT_FOUND, "el elemento con id $id no encontrado")
    }

    fun update(id: Long, branchDto: BranchDto): BranchDto {
        val entity = branchRepository.findById(id)
        if (entity.isPresent) {
            val toSave = entity.get().copy(
                address = branchDto.address,
                latitude = branchDto.latitude,
                longitude = branchDto.longitude
            )
            branchRepository.save(toSave)
            return toSave.toDto()
        }
        logger.error("Branch not exist $id")
        throw HttpStatusException(HttpStatus.BAD_REQUEST, "el elemento con id $id no esxiste")
    }

    fun delete(id: Long) {
        val entity = branchRepository.findById(id)
        if (entity.isPresent) {
            return branchRepository.delete(entity.get())
        }
        logger.error("Branch not exist $id")
        throw HttpStatusException(HttpStatus.BAD_REQUEST, "el elemento con id $id no esxiste")
    }

    fun getAll(): List<BranchDto> = branchRepository.findAll().map(Branch::toDto)

    fun findNearby(latitude: Double, longitude: Double): BranchDto {
        val distanceBranch = branchRepository.findAll()
            .associateBy { branch -> branch.distance(latitude.toDouble(), longitude.toDouble()) }
        val minDistance = distanceBranch.keys.minOrNull()
        val response = distanceBranch[minDistance]?.toDto() ?: throw HttpStatusException(
            HttpStatus.NOT_FOUND,
            "la sucursal mas cercana no fue encontrada"
        )
        return response.apply {
            if (minDistance != null) {
                distance =  "${minDistance*1000} metros"
            }
        }
    }
}
