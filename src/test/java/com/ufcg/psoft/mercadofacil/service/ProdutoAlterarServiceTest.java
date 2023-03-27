package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para  a alteração do Produto")
public class ProdutoAlterarServiceTest {

    @Autowired
    ProdutoAlterarService driver;


    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;

    @BeforeEach
    void setUp() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500104")
                        .nome("Produto Dez")
                        .fabricante("EmpreSa DeZ")
                        .preco(450.00)
                        .build()
                );

        produto = produtoRepository.find(10L);
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500104")
                        .nome("Nome Produto Alterado")
                        .fabricante("Nome Fabricante Alterado")
                        .preco(500.00)
                        .build()
                );
    }

    @Test
    @DisplayName("Quando altero o nome do produto com um nome válido")
    void alterarNomeDoProduto() {
        /* AAA */
        //Arrange
        produto.setNome("Nome do Produto Alterado");
        //Act
        Produto resultado = driver.alterar(produto);
        //Assert
        assertEquals("Nome do Produto Alterado", resultado.getNome());
    }

    @Test
    @DisplayName("Quando o preço é menor ou igual a zero")
    void precoMenorIgualAZero() {
        /* AAA */
        //Arrange
        produto.setPreco(0.00);
        //Act
        Produto resultado = driver.alterar(produto);
        //Assert
        RuntimeException thrown = assertThrows(
          RuntimeException.class,
                () -> driver.alterar(produto)
        );

        assertEquals("Preço inválido", thrown.getMessage());
    }

}
