package com.it.prueba.sucursal.services


import com.it.prueba.sucursal.controllers.dto.BranchDto
import com.it.prueba.sucursal.exceptions.HttpStatusException
import org.junit.Before
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = ["classpath:application-test.properties"])
class BranchServiceTest {

    @Autowired
    private lateinit var branchService: BranchService
    //4.6109547,-74.0724903
    private val colpatria =
        BranchDto(address = "Torre Colpatria", latitude = 4.6109547, longitude = -74.0724903)
    //4.5981206,-74.0782375
    private val plazaDeBolivar =
        BranchDto(address = "Plaza de Bolivar", latitude = 4.5981206, longitude = -74.0782375)

    private var idColpatria: Long = 0L
    private var idPlazaDeBolivar: Long = 0L

    @BeforeEach
    fun setup() {
        idColpatria = branchService.create(colpatria)
        idPlazaDeBolivar = branchService.create(plazaDeBolivar)
    }

    @AfterEach
    fun tearDown() {
        branchService.delete(idColpatria)
        branchService.delete(idPlazaDeBolivar)
    }

    @Test
    fun `success on create and on delete`() {
        val parque = BranchDto(address = "parque", latitude = 4.6034058, longitude = -74.0715523)
        val id = branchService.create(parque)
        val response = branchService.read(id)
        Assertions.assertEquals("parque", response.address)
        branchService.delete(id)
        assertThrows<HttpStatusException> {
            branchService.read(id)
        }
    }

    @Test
    fun `on read not found`() {
        assertThrows<HttpStatusException> {
            branchService.read(0L)
        }
    }

    @Test
    fun `on read  found element "Plaza de Bolivar"`() {
        val response = branchService.read(idPlazaDeBolivar)
        Assertions.assertEquals("Plaza de Bolivar", response.address)
    }

    @Test
    fun `success on update`() {
        branchService.update(
            idColpatria,
            BranchDto(
                address = "Torre Colpatria carrea 7",
                latitude = 4.6109547,
                longitude = -74.072485
            )
        )
        val response = branchService.read(idColpatria)
        Assertions.assertEquals("Torre Colpatria carrea 7", response.address)
    }

    @Test
    fun `error on delete`() {
        assertThrows<HttpStatusException> {
            branchService.delete(0L)
        }
    }

    @Test
    fun getAll() {
        val response = branchService.getAll()
        Assertions.assertEquals(2, response.size)
    }

    @Test
    fun `findNearby  shoild return "Plaza de Bolivar"`() {
        //coordenadas museo del oro
        //4.6016886,-74.0740793
        val response = branchService.findNearby(4.6016886,-74.0740793)
        Assertions.assertEquals("Plaza de Bolivar", response.address)
    }
}
