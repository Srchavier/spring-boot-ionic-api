package com.cursomc.builder;

import com.cursomc.dto.ProdutoDTO;
import com.cursomc.model.Produto;

public class ProdutoBuilder {

	public static ProdutoDTO builder(Produto produto) {
		return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor());
	}

	public static Produto builderProduto(ProdutoDTO produtoDto) {
		return new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getValor());
	}

}
