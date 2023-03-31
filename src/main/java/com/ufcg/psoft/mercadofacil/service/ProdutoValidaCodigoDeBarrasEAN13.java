package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class ProdutoValidaCodigoDeBarrasEAN13  implements ProdutoValidaCodigoDeBarras{

    @Override
    public boolean validaCodigoDeBarras(String codigoDeBarras) {
        boolean result = false;
        if (codigoDeBarras.length() == 13) {
            int soma = 0;

            String[] codigo = codigoDeBarras.split("");
            for (int i = 11; i >= 0; i--) {
                int num = Integer.parseInt(codigo[i]);
                soma += ((i % 2) == 0) && i != 0 ? num * 3 : num;
            }

            int digitoVerificador = Integer.parseInt(codigo[12]);

            System.out.println(digitoVerificador + " " + soma);

            result = ((soma + digitoVerificador) % 10 == 0);
        }

        return result;
    }
}
