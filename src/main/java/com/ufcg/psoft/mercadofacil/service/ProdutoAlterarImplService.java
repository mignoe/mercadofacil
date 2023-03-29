package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoVolatilRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAlterarImplService implements  ProdutoAlterarService{

    private ProdutoRepository<Produto, Long> produtoRepository = new ProdutoVolatilRepository();

    @Override
    public Produto alterar(Produto produtoAlterado) {
        if (produtoAlterado.getPreco() <= 0) {
            throw new RuntimeException("Preço inválido");
        }

        if (! codigoDeBarrasValido(produtoAlterado.getCodigoBarra())) {
            throw new RuntimeException("Código de barras inválido");
        }

        return produtoRepository.update(produtoAlterado);
    }

    private boolean codigoDeBarrasValido(String codigoDeBarras) {
        int soma = 0;

        String[] codigo = codigoDeBarras.split("");
        for (int i = codigoDeBarras.length() - 2; i >= 0; i--) {
            int num = Integer.parseInt(codigo[i]);
            soma += ((i % 2) == 0) ? num * 3 : num;
        }

        int digitoVerificador = Integer.parseInt(codigo[12]);

        System.out.println(digitoVerificador + " " + soma);

        return ( (soma + digitoVerificador) % 10 != 0);
    }
}
