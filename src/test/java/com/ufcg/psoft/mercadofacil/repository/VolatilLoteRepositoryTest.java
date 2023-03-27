package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.VolatilLoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class VolatilLoteRepositoryTest {


    @Autowired
    VolatilLoteRepository driver;


    Lote lote;
    Lote resultado;
    Produto produto;

    Lote loteExtra;
    Produto produtoExtra;



    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(1L)
                .nome("Produto Base")
                .codigoBarra("123456789")
                .fabricante("Fabricante Base")
                .preco(125.36)
                .build();
        lote = Lote.builder()
                .id(1L)
                .numeroDeItens(100)
                .produto(produto)
                .build();

        produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
    }


    @AfterEach
    void tearDown() {
        produto = null;
        driver.deleteAll();
    }


    @Test
    @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
    void salvarPrimeiroLote() {
        resultado = driver.save(lote);

        assertEquals(driver.findAll().size(),1);
        assertEquals(resultado.getId().longValue(), lote.getId().longValue());
        assertEquals(resultado.getProduto(), produto);
    }


    @Test
    @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
    void salvarSegundoLoteOuPosterior() {

        driver.save(lote);
        resultado = driver.save(loteExtra);

        assertEquals(driver.findAll().size(),2);
        assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
        assertEquals(resultado.getProduto(), produtoExtra);
    }

    @Test
    @DisplayName("Dar update em lote inexistente")
    void updateEmLoteInexistente() {

        driver.save(lote);

        resultado = driver.update(loteExtra);

        assertEquals(resultado, null);
        assertEquals(driver.findAll().size(),2);
    }

    @Test
    @DisplayName("Delete de um lote")
    void deleteDeUmLote() {
        driver.save(lote);
        driver.save(loteExtra);

        driver.delete(lote);

        assertEquals(driver.findAll().size(),1);
    }

    @Test
    @DisplayName("Encontrar lote")
    void findLote() {

        driver.save(lote);
        driver.save(loteExtra);

        resultado = driver.find(lote.getId());

        assertEquals(resultado, lote);
    }

    @Test
    @DisplayName("Encontrar lote que não está no repositório")
    void findLoteForaDoRepositorio() {

        driver.save(lote);
        driver.save(loteExtra);

        resultado = driver.find(50L);

        assertEquals(resultado, null);
    }

    @Test
    @DisplayName("Deletando um lote")
    void deletandoUmLote() {
        driver.save(lote);
        driver.save(loteExtra);

        driver.delete(lote);

        assertEquals(driver.findAll().size(), 1);
        assertEquals(driver.find(loteExtra.getId()), loteExtra);
    }

    @Test
    @DisplayName("Deletando tudo de uma vez")
    void deletandoTudoDeUmaVez() {
        driver.save(lote);
        driver.save(loteExtra);

        driver.deleteAll();

        assertEquals(driver.findAll().size(), 0);
    }

    @Test
    @DisplayName("Deletando tudo um por um")
    void deletandoTudoUmPorUm() {
        driver.save(lote);
        driver.save(loteExtra);

        driver.delete(lote);
        driver.delete(loteExtra);

        assertEquals(driver.findAll().size(), 0);
    }
}
