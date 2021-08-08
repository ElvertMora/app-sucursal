package com.it.prueba.sucursal.controllers

import com.it.prueba.sucursal.controllers.dto.BranchDto
import com.it.prueba.sucursal.services.BranchService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@ExtendWith(SpringExtension::class)
@TestPropertySource(locations = ["classpath:application-test.properties"])
@WebMvcTest(BranchController::class, BranchService::class)
class BranchControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var branchService: BranchService

    @Test
    fun getAll() {
        BDDMockito.given(branchService.getAll())
            .willReturn(emptyList())

        val request = MockMvcRequestBuilders.get("/sucursal")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun findByLocation() {
        BDDMockito.given(branchService.findNearby(anyDouble(),anyDouble()))
            .willReturn(BranchDto(address = "any", latitude = 4.5,longitude = -74.8))

        val request = MockMvcRequestBuilders.get("/sucursal/nearby?latitude=4.5&longitude=-47.6")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun create() {
        BDDMockito.given(branchService.create(anyOrNull()))
            .willReturn(1L)
        val request = MockMvcRequestBuilders.post("/sucursal")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"address\": \"string\",\"latitude\": 0,\"longitude\": 0}")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun findById() {
        BDDMockito.given(branchService.read(anyLong()))
            .willReturn(BranchDto(address = "any", latitude = 4.5,longitude = -74.8))

        val request = MockMvcRequestBuilders.get("/sucursal/1")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun update() {
        BDDMockito.given(branchService.update(anyLong(),anyOrNull()))
            .willReturn(BranchDto(address = "any", latitude = 4.5,longitude = -74.8))
        val request = MockMvcRequestBuilders.put("/sucursal/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"address\": \"string\",\"latitude\": 0,\"longitude\": 0}")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun delete() {
        val request = MockMvcRequestBuilders.delete("/sucursal/1")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    fun <T> anyOrNull(): T = any<T>()
}
