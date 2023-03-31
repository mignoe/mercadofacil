package com.ufcg.psoft.mercadofacil.Controller;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de Controlador de Produtos")
public class ProdutoV1Controller {

    @Autowired
    MockMvc mockMvc;

    Produto produto;

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(10L)
                .codigoBarra("7899137500104")
                .nome("Produto Dez")
                .fabricante("EmpreSa DeZ")
                .preco(450.00)
                .build();

    }

    @Test
    @DisplayName("Quando altero o produto com o nome válido")
    void alteroProdutoComNomeValido() {
        // Arrange
        produto.setNome("Chiclete");
        // Act
//        String produtoModificadoJSONString = mockMvc.perform(put("/produtos/" + 10)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(produto))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn().getResponse().getContentAsString();
//        );

        //Assert
//        Produto produtoModificado = objectMapper.readValue(produtoModificadoJSONString, Produto.class);
//        assertEquals("Chiclete", produtoModificado.getNome());
    }
}
