package com.it.prueba.sucursal.repositories

import com.it.prueba.sucursal.repositories.entities.Branch
import org.springframework.data.jpa.repository.JpaRepository

interface BranchRepository : JpaRepository<Branch,Long>
