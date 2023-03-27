package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;

public interface ProdutoRepository<T, E> {
    Produto save(Produto produto);

    public Produto find(Long id);

    public Produto update(Produto produto);
}
