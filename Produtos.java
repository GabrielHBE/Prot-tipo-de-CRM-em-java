import java.util.List;
import java.util.Scanner;

public class Produtos{
	
	private String nome;
	private String fornecedor;
	private int quantidade;
	private double preco;
	private int id;
	private int qntVendido;

	Scanner sc = new Scanner(System.in);
	
	//Construtores
	public Produtos(String nome, int quantidade, double preco, int id, String fornecedor) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.id = id;
		this.fornecedor = fornecedor;
	}

	public Produtos(int qnt){
		this.qntVendido = qnt;
	}
	
	public Produtos() {
		
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getQntVendido(){
		return qntVendido;
	}

	public double totalEstoque(){
		return this.quantidade*preco;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	//Metodos
	public void Venda(int qnt){
		this.quantidade-=qnt;
	}

	public void qntVendas(int qnt){
		this.qntVendido+=qnt;
	}

	public void cadastrar(List<Fornecedores> listaFornecedores, int p){
		int op;
		System.out.printf("Informe o nome do produto: ");
		nome = sc.nextLine();
		System.out.printf("Informe o preço do produto: ");
		preco = sc.nextDouble();
		sc.nextLine();
		System.out.printf("O produto já possui uma quantidade no estoque? (1-Sim/2-Não)");
		op = sc.nextInt();
		sc.nextLine();
		id++; //Gera o ID do produto
		if(op==1) { 
			System.out.printf("Informe a quantidade inicial deste produto: ");
			quantidade = sc.nextInt();
			sc.nextLine();
		}
		System.out.printf("Informe o fornecedor desse produto: ");
		fornecedor = sc.nextLine();
		int i=0;
		int result=0;
		do{
			for(i=0;i<listaFornecedores.size();i++){
				if(listaFornecedores.get(i).getEmpresa().equalsIgnoreCase(fornecedor)){
					System.out.println("Fornecedor encontrado");
					result=1;
				}
			}

			if(result==0){
				System.out.printf("Fornecedor nao encontrado. Informe o novamente: ");
				fornecedor = sc.nextLine();
			}
		}while(result==0);
		System.out.printf("Produto cadastrado!\nO id do produto é: %d",id);
		this.id+=p;
	}

	public void aletrar_cadastro(){
		int sair, op;
		do{
			System.out.printf("Qual dado será alterado?\n");
			System.out.printf("1-Nome\n2-Quantidade\n3-Preço\n");
			op = sc.nextInt();
			sc.nextLine();
			switch(op) {
		
			case 1:
				System.out.println("O nome atual é: " + nome);
				System.out.printf("Informe o novo nome: ");
				nome = sc.nextLine();
				break;
			case 2:
				System.out.println("A quantidade atual é: " + quantidade);
				System.out.printf("Informe a nova quantidade: ");
				quantidade = sc.nextInt();
				sc.nextLine();
				break;
			case 3:
				System.out.println("O preço atual é: " + preco);
				System.out.printf("Informe o novo preço: ");
				preco = sc.nextDouble();
				sc.nextLine();
				break;
			}
			System.out.println("Dado alterado com sucesso!");
			System.out.printf("Deseja alterar outro dado? (1-Sim/2-não)");
			sair = sc.nextInt();
			sc.nextLine();
		}while(sair==1);
	}

	public String toString(){
		return "Nome: " + nome + ". Quantidade: " + String.format("%d", quantidade) + ". Preço: " + String.format("%.2f",preco) + ". Valor total do estoque: " + String.format("%.2f", totalEstoque()) + ". Id do produto: " + String.format("%d", id) + ". Fornecedor do produto: " + fornecedor;
	}

}