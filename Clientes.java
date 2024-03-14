import java.util.Scanner;

public class Clientes {
	
	private String nome;
	private String endereco;
	private String cpf;
	private String email;
	private String telefone;
	private int id;
	private int qntVendas;

	Scanner sc = new Scanner(System.in);
	
	//Construtor
	public Clientes(String nome, String endereco, String cpf, String email, String telefone, int id) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.id = id;
	}

	public Clientes() {
		
	}

	public Clientes(int qnt){
		this.qntVendas = qnt;
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getQntVendas(){
		return qntVendas;
	}

	//Metodos
	public void qntVendas(int qnt){
		this.qntVendas = qnt;
	}

	public void cadastrar(int i){
		System.out.printf("Informe o nome do cliente: ");
		nome = sc.nextLine();
		System.out.printf("Informe o cpf do cliente: ");
		cpf = sc.nextLine();
		System.out.printf("Informe o endereço do cliente: ");
		endereco = sc.nextLine();
		System.out.printf("Informe o e-mail do cliente:");
		email = sc.nextLine();
		System.out.printf("Informe o telefone do cliente: ");
		telefone = sc.nextLine();
		this.id=i;
		System.out.println("Id cadastrado, o ID do cliente é: " + this.id);
	}

	public void alterar_cadastro(){
		int op;
		int sair=0;
		do{
			System.out.printf("Qual dado será alterado?\n");
			System.out.printf("1-Nome\n2-Endereço\n3-EMAIL\n4-Telefone\n");
			op = sc.nextInt();
			sc.nextLine();
			switch(op) {
				
				case 1:
				System.out.println("O nome atual é: " + nome);
				System.out.printf("Informe o novo nome: ");
				nome = sc.nextLine();
				break;

				case 2:
				System.out.println("O endereço atual é: " + endereco);
				System.out.printf("Informe o novo endereço: ");
				endereco = sc.nextLine();
				break;
									
				case 3:					
				System.out.println("O EMAIL atual é: " + email);
				System.out.printf("Informe o novo EMAIL: ");
				email = sc.nextLine();
				break;
									
				case 4:
				System.out.println("O telefone atual é: " + telefone);
				System.out.printf("Informe o novo telefone: ");
				telefone = sc.nextLine();
				break;
								
			}
				
			System.out.printf("Deseja alterar outro dado? (1-Sim/2-Não)");
			sair = sc.nextInt();
			sc.nextLine();
		}while(sair==1);
	}

	public String toString(){
		return "Nome: " + nome + ". Endereço: " + endereco + ". E-mail: " + email + ". Telefone: " + telefone + ". CPF: " + cpf + ". Compras realizadas: " + qntVendas +  ". Id da conta: " + String.format("%d", id);
	}
	
}