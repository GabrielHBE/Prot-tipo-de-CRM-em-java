public class ClienteP extends Clientes {

    public double desconto;

    //Constutores
    public ClienteP(String nome, String endereco, String cpf, String email, String telefone, int id,double desconto) {
        super(nome, endereco, cpf, email, telefone, id);
        this.desconto = desconto;
    }
    public ClienteP(double desconto) {
        this.desconto = desconto;
    }
    public ClienteP(int qnt,double desconto) {
        super(qnt);
        this.desconto = desconto;
    }
    public ClienteP(){

    }

    //Getters e Setters
    public double getDesconto() {
        return desconto;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    //Metodos
    public String toString(){
		return "Nome: " + getNome() + ". Endereço: " + getEndereco() + ". E-mail: " + getEmail() + ". Telefone: " + getTelefone() + ". CPF: " + getCpf() + ". Compras realizadas: " + getQntVendas() + ". Porcentagem de desconto: " + desconto + ". Id da conta: " + String.format("%d", getId());
	}

    @Override
    public void cadastrar(int i){
		System.out.printf("Informe o nome do cliente: ");
		String nome = sc.nextLine();
        setNome(nome);
		System.out.printf("Informe o cpf do cliente: ");
		String cpf = sc.nextLine();
        setCpf(cpf);
		System.out.printf("Informe o endereço do cliente: ");
		String endereco = sc.nextLine();
        setEndereco(endereco);
		System.out.printf("Informe o e-mail do cliente:");
		String email = sc.nextLine();
        setEmail(email);
		System.out.printf("Informe o telefone do cliente: ");
		String telefone = sc.nextLine();
        setTelefone(telefone);
        System.out.printf("Informe a porcentagem de desconto do cliente: ");
        desconto = sc.nextDouble();
        sc.nextLine();
		setId(i);
		System.out.println("Id cadastrado, o ID do cliente é: " + getId());
	}

    @Override
    public void alterar_cadastro(){
		int op;
		int sair=0;
		do{
			System.out.printf("Qual dado será alterado?\n");
			System.out.printf("1-Nome\n2-Endereço\n3-EMAIL\n4-Telefone\n4-Desconto\n");
			op = sc.nextInt();
			sc.nextLine();
			switch(op) {
				
				case 1:
                    System.out.println("O nome atual é: " + getNome());
                    System.out.printf("Informe o novo nome: ");
                    String nome = sc.nextLine();
                    setNome(nome);
				break;

				case 2:
                    System.out.println("O endereço atual é: " + getEndereco());
                    System.out.printf("Informe o novo endereço: ");
                    String endereco = sc.nextLine();
                    setEndereco(endereco);
				break;
									
				case 3:					
                    System.out.println("O EMAIL atual é: " + getEmail());
                    System.out.printf("Informe o novo EMAIL: ");
                    String email = sc.nextLine();
                    setEmail(email);
				break;
									
				case 4:
                    System.out.println("O telefone atual é: " + getTelefone());
                    System.out.printf("Informe o novo telefone: ");
                    String telefone = sc.nextLine();
                    setTelefone(telefone);
				break;

                case 5:
                    System.out.println("O desconto atual é: " + desconto);
                    System.out.printf("Informe o novo desconto");
                    desconto = sc.nextDouble();
                    sc.nextLine();
                break;

								
			}
				
			System.out.printf("Deseja alterar outro dado? (1-Sim/2-Não)");
			sair = sc.nextInt();
			sc.nextLine();
		}while(sair==1);
	}
    
}
