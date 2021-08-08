package com.it.prueba.sucursal.repositories.entities


import com.it.prueba.sucursal.annotations.NoArgsConstructor
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@NoArgsConstructor
@Table(name = "branch")
data class Branch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "address") val address: String,
    @Column(name = "latitude") val latitude: Double,
    @Column(name = "longitude") val longitude: Double
)


