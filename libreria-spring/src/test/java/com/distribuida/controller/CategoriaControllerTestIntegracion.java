package com.distribuida.controller;

import com.distribuida.model.Categoria;
import com.distribuida.service.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

//import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws Exception {
        Categoria categoria = new Categoria(1,"Amor","Romance");

        Mockito.when(categoriaService.findAll()).thenReturn(List.of(categoria));

        mockMvc.perform(get("/categoria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria").value("Amor"));
    }

    @Test
    public void testSave() throws Exception {
        Categoria categoria = new Categoria(1,"Amor","Romance");

        Mockito.when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria))
        )       .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoria").value("Amor"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/categoria/1")).andExpect(status().isNoContent());
    }



}
