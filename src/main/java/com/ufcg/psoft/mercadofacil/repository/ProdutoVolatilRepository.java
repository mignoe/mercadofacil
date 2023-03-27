package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoVolatilRepository implements ProdutoRepository<Produto, Long> {

    List<Produto> lotes = new ArrayList<>();

    @Override
    public Produto save(Produto lote) {
        lotes.add(lote);
        return lotes.stream().findFirst().get();
    }

    @Override
    public Produto find(Long id) {
        return lotes.get(Integer.parseInt("" + id));
    }

    public List<Produto> findAll() {
        return lotes;
    }

    @Override
    public Produto update(Produto lote) {
        lotes.clear();
        lotes.add(lote);
        return lotes.stream().findFirst().orElse(null);
    }

    public void delete(Produto lote) {
        lotes.clear();
    }

    public void deleteAll() {
        lotes.clear();
    }

}
