package Hamburgueria;

public class Produtos {
	
	private String nome;
	private double preco;
	private String descricao;
	
	//CONSTRUTOR
	public Produtos(String _nome, String _descricao, double _preco) {
		this.nome = _nome;
		this.preco = _preco;
		this.descricao = _descricao;
	}

	//GETS e SETS
	public String getNome() {
		return nome;
	}

	public void setNome(String _nome) {
		this.nome = _nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double _preco) {
		if(_preco > 0.0) {
			this.preco = _preco;
		}
		else {
			throw new IllegalArgumentException("Erro: Não é permitido adicionar um produto com preço negativo.");
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String _descricao) {
		this.descricao = _descricao;
	}
	
}
