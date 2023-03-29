package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoVolatilRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAlterarImplService implements  ProdutoAlterarService{
    private ProdutoValidaCodigoDeBarrasEAN13 validadorCodigoDeBarrasEAN13 = new ProdutoValidaCodigoDeBarrasEAN13();

    private ProdutoRepository<Produto, Long> produtoRepository = new ProdutoVolatilRepository();

    @Override
    public Produto alterar(Produto produtoAlterado) {

        if (produtoAlterado.getPreco() <= 0) {
            throw new RuntimeException("Preço inválido");
        }


        if (!validadorCodigoDeBarrasEAN13.validaCodigoDeBarras(produtoAlterado.getCodigoBarra())) {
            throw new RuntimeException("Código de barras inválido");
        }

        return produtoRepository.update(produtoAlterado);
    }
}
