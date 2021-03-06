package beans;
import java.util.LinkedList;

public class Prato {
	String nome;
	double preco;
	LinkedList <Ingrediente> receita;
	int codigo;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public LinkedList<Ingrediente> getReceita() {
		return receita;
	}
	
	public void setReceita(LinkedList<Ingrediente> receita) {
		this.receita = receita;
	}
	
	public Prato(String nome, double preco, LinkedList<Ingrediente> receita, int codigo) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.receita = receita;
		this.codigo = codigo;
	}

}
