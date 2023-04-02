package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

        // Mockito.when(produtoRepository.)
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

        RuntimeException thrown = assertThrows(
          RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Preço inválido", thrown.getMessage());
    }

    @Nested
    class CodigoDeBarras {
        @Test
        @DisplayName("Quando o código de barras é válido")
        void mudarCodigoDeBarras () {
            /* AAA */
            //Arrange
            produto.setCodigoBarra("4012345678901");
            //Act
            Produto resultado = driver.alterar(produto);
            //Assert
            assertEquals(resultado.getCodigoBarra(), "4012345678901");
        }

        @Test
        @DisplayName("Quando o código de barras é inválido (dígito de verificação errado)")
        void mudarCodigoDeBarrasParaDigitoDeVerificacaoInvalido () {
            /* AAA */
            //Arrange
            // O dígito de verificação está errado.
            produto.setCodigoBarra("4012345678902");
            //Act
            RuntimeException thrown = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());
        }

        @Test
        @DisplayName("Quando o código de barras é inválido (número de dígitos menor que 13)")
        void mudarCodigoDeBarrasParaTamanhoMenor () {
            /* AAA */
            //Arrange

            produto.setCodigoBarra("406");
            //Act
            RuntimeException thrown = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());
        }

        @Test
        @DisplayName("Quando o código de barras é inválido (número de dígitos maior que 13)")
        void mudarCodigoDeBarrasParaTamanhoMaior () {
            /* AAA */
            //Arrange
            // Codigo de verificação também estaria errado
            produto.setCodigoBarra("1234567891011123");
            //Act
            RuntimeException thrown = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());

            /* AAA */
            //Arrange
            // Codigo de verificação correto (considerando os 13 primeiros dígitos).
            produto.setCodigoBarra("1234567891015123");
            //Act
            RuntimeException thrown2 = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());
        }

        @Test
        @DisplayName("Quando o código de barras é inválido (número de dígitos é 0 que é diferente de 13)")
        void mudarCodigoDeBarrasParaTamanhoZero () {
            /* AAA */
            //Arrange
            // Codigo de verificação também estaria errado
            produto.setCodigoBarra("");
            //Act
            RuntimeException thrown = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());
        }

        @Test
        @DisplayName("Teste com tamanho inválido, mas último dígito válido")
        void testTamanhoInvalidoDigitoValido() {
            /* AAA */
            //Arrange
            // Codigo de verificação está correto
            produto.setCodigoBarra("430008518181");
            //Act
            RuntimeException thrown = assertThrows(
                    RuntimeException.class,
                    () -> driver.alterar(produto)
            );

            //Assert
            assertEquals("Código de barras inválido", thrown.getMessage());

        }
    }

}
