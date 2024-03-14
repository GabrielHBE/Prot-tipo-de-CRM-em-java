import java.util.List;
import java.util.Scanner;

public class Venda{
	
	private String cliente;
	private String produto;
	private double valorUnitario;
	private double valorTotal;
	private int qnt;

	Scanner sc = new Scanner(System.in);
	
	//Construtor
	public Venda(String cliente, String produto, int qnt) {
		this.cliente = cliente;
		this.produto = produto;
		this.qnt = qnt;
	}

	public Venda(){

	}

	//Getters e Setters
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	public double getValorTotal(){
		return valorTotal;
	}

	public void setValorToral(double valorTotal){
		this.valorTotal = valorTotal;
	}

	public double getValorUnitario(){
		return valorUnitario;
	}

	
	//Metodos
	public String toString(){
		return "Nome do cliente: " + cliente + ", Produto: " + produto + ", Quantidade: " + String.format("%d",qnt) + ", Preço unitário do protudo: " + String.format("%.2f",valorUnitario) + ", Valor total: "  + String.format("%.2f",valorTotal);
	}

	public void cadastrar_venda(List <Clientes> listaC, List <Produtos> listaP, List <Fornecedores> listaF, List <ClienteP> listaCp){

		int i=0, result=0,j=0,k=0;;
		int clientep=0;

		System.out.printf("Informe o nome do cliente: ");
		this.cliente = sc.nextLine();
		do{
			//Verifica se o cliente faz parte da lista de clientes normais
			for(i=0;i<listaC.size();i++){
				if(listaC.get(i).getNome().equalsIgnoreCase(this.cliente)){
					System.out.println("Cliente encontrado");
					result=1;
					clientep=0; 
				}
			}
			//Verifica se o cliente faz parte da lista de clientes piroritarios
			for(i=0;i<listaCp.size();i++){
				if(listaCp.get(i).getNome().equalsIgnoreCase(this.cliente)){
					System.out.println("Cliente encontrado");
					result=1;
					clientep=1; //Caso faça parte o indicador se torna verdadeiro
					j=i;//Guarda a posição do cliente prioritário para usar a seguir
				}
			}

			//Caso o cliente não seja encontrado pede para informar o nome novamente
			if(result==0){
				System.out.println("Cliente não encontado");
				System.out.printf("Informe o nome do cliente novamente: ");
				this.cliente = sc.nextLine();
			}

		}while(result==0);
		result=0;

		System.out.printf("Informe o nome do produto: ");
		this.produto = sc.nextLine();
		
		if(clientep==0){ //Caso o cliente não seja prioritario será feito o passo a passo a seguir
			do{
				//Procura o produto 
				for(i=0;i<listaP.size();i++){
					if(listaP.get(i).getNome().equalsIgnoreCase(this.produto)){
						System.out.println("Produto encontrado");
						result=1; //Caso ache o produto o resultado é verdadeiro
						k=i;
						System.out.printf("Informe a quantidade que será consumido do estoque:");
						this.qnt = sc.nextInt();
						sc.nextLine();
						//Verifica se a quantidade informada não é maior que o estoque disponivel
						while (this.qnt>listaP.get(i).getQuantidade()) {
							System.out.println("Esse produto não possui essa quantidade no estoque");
							System.out.printf("Quantidade no estoque: %d\n", listaP.get(i).getQuantidade());
							System.out.printf("Informe uma quantidade igual ou superior ao do estoque: ");
							this.qnt = sc.nextInt();
							sc.nextLine();
						}
						valorUnitario = listaP.get(i).getPreco();
						valorTotal = this.qnt*this.valorUnitario;

						//Aumenta a reputação do fornecedor do produto 
						String nome_fornecedor_produto=listaP.get(i).getFornecedor();
						for(i=0;i<listaF.size();i++){
							if(listaF.get(i).getEmpresa().equalsIgnoreCase(nome_fornecedor_produto)){
								listaF.get(i).aumentar_reputacao(this.qnt);
							}
						}
					}else{
						result=0; //Caso não ache o produto o resultado é falso
					}
				}
				if(result==0){
					System.out.println("Produto não encontrado");
					System.out.printf("Informe o nome do produto novamente");
					this.produto = sc.nextLine();
				}
			}while(result==0);
		}else if(clientep==1){//Se o cliente não é normal, é o um cliente prioritário
			do{
				for(i=0;i<listaP.size();i++){
					//Procura o nome do produto
					if(listaP.get(i).getNome().equalsIgnoreCase(this.produto)){
						System.out.println("Produto encontrado");
						result=1; //Caso o produto seja encontrado o resultado é verdadeiro
						k=i;
						System.out.printf("Informe a quantidade que será consumido do estoque:");
						this.qnt = sc.nextInt();
						sc.nextLine();
						//Verifica se a quantidade informada é maior que o estoque
						while (this.qnt>listaP.get(i).getQuantidade()) {
							System.out.println("Esse produto não possui essa quantidade no estoque");
							System.out.printf("Quantidade no estoque: %d\n", listaP.get(i).getQuantidade());
							System.out.printf("Informe uma quantidade igual ou superior ao do estoque: ");
							this.qnt = sc.nextInt();
							sc.nextLine();
						}
						//Como o cliente é priorita e possui um desconto, o desconto é aplicado no valor unitário
						valorUnitario = listaP.get(i).getPreco() - (listaP.get(i).getPreco()*(listaCp.get(j).getDesconto()/100));
						valorTotal = this.qnt*this.valorUnitario;

						//Aumenta a reputação do fornecedor do produto 
						String nome_fornecedor_produto=listaP.get(i).getFornecedor();
						for(i=0;i<listaF.size();i++){
							if(listaF.get(i).getEmpresa().equalsIgnoreCase(nome_fornecedor_produto)){
								listaF.get(i).aumentar_reputacao(this.qnt);
							}
						}
					}
				}
				if(result==0){
					System.out.println("Produto não encontrado");
					System.out.printf("Informe o nome do produto novamente");
					this.produto = sc.nextLine();
				}
			}while(result==0);
		}

		//Mostra o resumo da venda
		System.out.println("Resumo da venda:");
		System.out.println("Nome do cliente: " + this.cliente);
		System.out.println("Nome do produto: " + this.produto);
		System.out.println("Valor unitário do produto: " + valorUnitario);
		System.out.println("Valor total do pedido: " + valorTotal);
		listaP.get(k).qntVendas(this.qnt);

	}
}

