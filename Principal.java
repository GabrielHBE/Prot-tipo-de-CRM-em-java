import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Principal{
    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);
		
		List<Clientes>ListaClientes = new ArrayList<>();
		List<Produtos>ListaProdutos = new ArrayList<>();
		List<Venda>ListaVendas = new ArrayList<>();
		List<Fornecedores>ListaFornecedores = new ArrayList<>();
		List<ClienteP>ListaClientesP = new ArrayList<>();

		String arquivo_clientes = "C:\\Users\\Gabriel\\Desktop\\MostrarClientes.txt";
		String arquivo_fornecedores = "C:\\Users\\Gabriel\\Desktop\\MostrarFornecedores.txt";
		String arquivo_produtos = "C:\\Users\\Gabriel\\Desktop\\MostrarProdutos.txt";
		String arquivo_vendas = "C:\\Users\\Gabriel\\Desktop\\MostrarVendas.txt";
		
		int op=0,op2=0;
		int qntVendas=0;
		int qntClientes=0;
		int qntProdutos=0;
		int qntFornecedores=0;
		int i=0,k=0;
		int idC=0;
		int idCp=0;
		int idP=0;
		int idF=0;


		do{
			//Inicializando o menu
			System.out.println();
			System.out.println("\t---MENU---");
			System.out.println("1-Cadasrar clientes               \t9-Ver qual produto é mais vendido");
			System.out.println("2-Cadasrar produtos               \t10-Ver qual cliente mais comprou");
			System.out.println("3-Realizar venda                  \t11-Remover cadastro de um cliente");
			System.out.println("4-Mostrar catálogo de produtos    \t12-Remover cadastro de um produto");
			System.out.println("5-Mostrar catálogo de clientes    \t13-Cadastrar fornecedor");
			System.out.println("6-Mostrar vendas realizadas       \t14-Ver fornecedores");
			System.out.println("7-Alterar cadastro de um cliente  \t15-Remover fornecedor");
			System.out.println("8-Alterar cadastro de um produto  \t16-Gerar arquivos");
			System.out.printf("\nInforme o número corespondente: ");
			op = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			switch(op) {
			
				case 1: //cadastrar cliente
					System.out.println("O cliente tem prioridades? (1-Sim/2-Não)");
					op2=sc.nextInt();
					sc.nextLine();
					switch(op2){
						case 1:
							ClienteP clientep = new ClienteP();
							clientep.cadastrar(idCp);
							ListaClientesP.add(clientep);
							idCp++;
						break;

						case 2:
							Clientes clientes = new Clientes();
							clientes.cadastrar(idC);
							ListaClientes.add(clientes);
							idC++;
						break;

						default:
							System.out.println("Opção inválida");
						break;
					}
					qntClientes++;
				break;

				case 2: //Cadastrar produto
					if(qntFornecedores==0){
						System.out.println("Nao possui nenhum fornecedor registrado");
					}else{
						Produtos produtos = new Produtos();
						produtos.cadastrar(ListaFornecedores, idP);
						ListaProdutos.add(produtos);
						qntProdutos++;
						idP++;
					}
				break;

				case 3: //realizar venda
					if(qntClientes==0 && qntProdutos>=1){
						System.out.println("Nenhum cliente cadastrado!");
					}else if(qntProdutos==0 && qntClientes>=1){
						System.out.println("Nenhum produto cadastrado!");
					}else if(qntProdutos==0 && qntClientes==0){
						System.out.println("Nenhum produto e cliente foram cadastrados!");
					}else{
						Venda vendas = new Venda();
						vendas.cadastrar_venda(ListaClientes, ListaProdutos, ListaFornecedores, ListaClientesP);
						qntVendas++;
						ListaVendas.add(vendas);
					}	
				break;

				case 4: //Mostrar produtos
					if(qntProdutos==0){
						System.out.printf("Nenhum produto cadastrado!\n");
					}else if(qntProdutos==1){
						System.out.println("Foi cadastrado 1 produto sendo ele:");
						for (Produtos produto : ListaProdutos) {
							System.out.println(produto);
						}
					}else{
						System.out.printf("Foram cadastrados %d produtos, sendo eles: \n",qntProdutos);
						for (Produtos produto : ListaProdutos) {
							System.out.println(produto);
						}
					}
				break;

				case 5: //Mostrar clientes
					if(qntClientes==0){
						System.out.println("Nenhum cliente cadastrado!");
					}else if(qntClientes==1){
						System.out.println("Foi cadastrado 1 cliente, sendo ele: ");
						for (ClienteP clientep : ListaClientesP) {
							System.out.println(clientep);
						}
						for (Clientes cliente : ListaClientes) {
							System.out.println(cliente);
						}
					}else{
						System.out.printf("Foram cadastrados %d clientes, sendo eles:\n",qntClientes);
						for (ClienteP clientep : ListaClientesP) {
							System.out.println(clientep);
						}
						for (Clientes cliente : ListaClientes) {
							System.out.println(cliente);
						}
					}
				break;

				case 6: //Mostrar vendas
					if(qntVendas==0){
						System.out.println("Nenhuma venda feita até o momento");
					}else if(qntVendas==1){
						System.out.println("Foi realizada 1 venda até o momento, senda ela:");
						for(Venda vendas : ListaVendas) {
							System.out.println(vendas);
						}
					}else{
						System.out.printf("Foram realizadas %d vendas, são elas:\n",qntVendas);
						for(Venda vendas : ListaVendas) {
							System.out.println(vendas);
						}
					}
				break;

				case 7: //Alterar cadastro de clientes
					
					if(qntClientes==0){
						System.out.println("Nenhum cliente cadastrado até o momento!");
					}else{
						System.out.printf("Informe o nome do cliente que será alterado o cadastro: ");
						String PesquisaAlteracaoCliente = sc.nextLine();

						do{
							i=BuscarNomeCliente(ListaClientes, PesquisaAlteracaoCliente);
							if(i==-1){
								i=BuscarNomeClienteP(ListaClientesP, PesquisaAlteracaoCliente);
								k=1;
							}

							if(i>=0 && k==0){
								System.out.println("Cliente encontrado");
								ListaClientes.get(i).alterar_cadastro();
							}else if(i>=0 && k==1){
								System.out.println("Cliente encontrado");
								ListaClientesP.get(i).alterar_cadastro();
							}else{
								System.out.printf("Nenhum cliente encontrado. Informe o nome novamente: ");
								PesquisaAlteracaoCliente = sc.nextLine();
							}

						}while(i<0);
					}
				break;

				case 8: //Alterar cadastro de produto
					if(qntProdutos==0){
						System.out.println("Nenhum produto cadastrado até o momento!");
					}else{
						System.out.printf("Informe o nome do produto que será alterado: ");
						String PesquisaAlteracaoProduto = sc.nextLine();

						do{
							i=BuscarNomeProduto(ListaProdutos, PesquisaAlteracaoProduto);
							if(i>=0){
								System.out.println("Produto encontrado");
								ListaProdutos.get(i).aletrar_cadastro();
							}else if(i<0){
								System.out.printf("Nenhum produto encontrado. Informe novamente o nome: ");
								PesquisaAlteracaoProduto = sc.nextLine();
							}

						}while(i<0);
						
					}
				break;
				
				case 9: //Verifica qual foi o produto mais vendido
					i=0;
					int result=0;
					int maisVendido=0;
					if(qntVendas==0){
						System.out.println("Nenhuma venda realizada até o momento");
					}else{
						
						for(i=0;i<ListaProdutos.size();i++){
							if(ListaProdutos.get(i).getQntVendido()>maisVendido){
								maisVendido = ListaProdutos.get(i).getQntVendido();
								result = i;
							}
						}
							System.out.println("O produto mais vendido é: " + ListaProdutos.get(result).getNome() + " com " + maisVendido + " unidades");
						
					}
				break;

				case 10: //verifica qual foi cliente que mais comprou
					k=0;
					maisVendido=0;
					result=0;
					if(qntVendas==0){
						System.out.println("Nenhuma venda realizada até o momento");
					}else{
						
						for(i=0;i<ListaProdutos.size();i++){
							if(ListaClientes.get(i).getQntVendas()>maisVendido){
								maisVendido = ListaClientes.get(i).getQntVendas();
								result = i;
							}
						}

						System.out.println("O cliente que mais comprou foi: " + ListaClientes.get(result).getNome() + " com " + maisVendido + " compras");	
						
					}
				break;

				case 11: //Remove o cadastro de um cliente
					k=0;
					if(qntClientes==0){
						System.out.println("Nenhum cliente cadastrado");
					}else{
						System.out.printf("Informe o nome do cliente que será removido o cadastro: ");
						String NomeRemoveCliente = sc.nextLine();

						i = BuscarNomeCliente(ListaClientes, NomeRemoveCliente);
						if(i==-1){
							i=BuscarNomeClienteP(ListaClientesP,NomeRemoveCliente);
							k=1;
						}

						if(i>=0 && k==0){
							ListaClientes.remove(i);
							System.out.println("Cliente removido com sucesso");
							qntClientes--;
						}else if(i>=0 && k==1){
							ListaClientesP.remove(i);
							System.out.println("Cliente removido com sucesso");
							qntClientes--;
						}else{
							System.out.println("nenhum cliente encontrado");
						}
					}
				break;

				case 12: //Remove o cadastro de um produto
					if(qntProdutos==0){
						System.out.println("Nenhum produto cadastrado");
					}else{
						System.out.printf("Informe o nome do produo que será removido o cadastro: ");
						String NomeRemoveCliente = sc.nextLine();

						i = BuscarNomeProduto(ListaProdutos, NomeRemoveCliente);

						if(i>=0){
							ListaProdutos.remove(i);
							System.out.println("Produto removido com sucesso");
							qntProdutos--;
						}else{
							System.out.println("Nenhum produto encontrado");
						}
					}
				break;
				
				case 13: //Cadastra um fornecedor
					Fornecedores fornecedor = new Fornecedores();
					fornecedor.cadastrar(idF);
					ListaFornecedores.add(fornecedor);
					System.out.println("Fornecedor cadastrado com sucesso");
					qntFornecedores++;
					idF++;
				break;

				case 14: //Mostra os fornecedores
					if(qntFornecedores==0){
						System.out.println("Nenhum fornecedor cadastrado");
					}else{
						for(Fornecedores fornecedores : ListaFornecedores){
							System.out.println(fornecedores);
						}
					}
				break;

				case 15: //Remove um fornecedor
					if(qntFornecedores==0){
						System.out.println("Nenhum fornecedor cadastrado");
					}else{
						System.out.printf("Informe o nome do produo que será removido o cadastro: ");
						String NomeRemoveFornecedor = sc.nextLine();

						i = BuscarNomeFornecedor(ListaFornecedores, NomeRemoveFornecedor);

						if(i>=0){
							ListaFornecedores.remove(i);
							System.out.println("Fornecedor removido com sucesso");
							qntFornecedores--;
						}else{
							System.out.println("Nenhum fornecedor encontrado");
						}
					}
				break;

				case 16:
					int cont=1;
					System.out.println("Qual arquivo será gerado?");
					System.out.println("1-Lista dos clientes");
					System.out.println("2-Lista dos produtos");
					System.out.println("3-Lista dos fornecedores");
					System.out.println("4-Lista das vendas realizadas");
					op2=sc.nextInt();
					sc.nextLine();

					switch(op2){
						case 1:
							if(qntClientes==0){
								System.out.println("Nenhum cliente cadastrado");
							}else{
								//Remove o conteudo anterior do arquivo
								try {
									// Cria um objeto FileWriter com append falso (para sobrescrever o conteúdo)
									FileWriter escritor = new FileWriter(arquivo_clientes, false);
									// Escreve uma string vazia no arquivo
									escritor.write("");
									// Fecha o objeto FileWriter
									escritor.close();
									
									System.out.println("Conteúdo do arquivo excluído com sucesso.");
								} catch (IOException e) {
									System.out.println("Ocorreu um erro ao tentar excluir o conteúdo do arquivo: " + e.getMessage());
								}

								try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo_clientes))){
									bw.write("Clientes prioritarios: ");
									bw.newLine();
									bw.newLine();
									cont=1;
									for(i=0;i<ListaClientesP.size();i++){
										bw.write("Cliente " + cont);
										bw.newLine();
										bw.write("Nome: " + ListaClientesP.get(i).getNome());
										bw.newLine();
										bw.write("CPF: " + ListaClientesP.get(i).getCpf());
										bw.newLine();
										bw.write("E-Mail: " + ListaClientesP.get(i).getEmail());
										bw.newLine();
										bw.write("Endereço: " + ListaClientesP.get(i).getEndereco());
										bw.newLine();
										bw.write("Telefone: " + ListaClientesP.get(i).getTelefone());
										bw.newLine();
										bw.write("Quantidade de compras: " + ListaClientesP.get(i).getQntVendas());
										bw.newLine();
										bw.write("Porcentagem de desconto: " + ListaClientesP.get(i).getDesconto());
										bw.newLine();
										bw.write("ID da conta: " + ListaClientesP.get(i).getId());
										bw.newLine();
										bw.write("-------------------------------------------");
										bw.newLine();
										bw.newLine();
										cont++;
									}
									bw.write("Clientes normais: ");
									bw.newLine();
									bw.newLine();
									cont=1;
									for(i=0;i<ListaClientes.size();i++){
										bw.write("Cliente " + cont);
										bw.newLine();
										bw.write("Nome: " + ListaClientes.get(i).getNome());
										bw.newLine();
										bw.write("CPF: " + ListaClientes.get(i).getCpf());
										bw.newLine();
										bw.write("E-Mail: " + ListaClientes.get(i).getEmail());
										bw.newLine();
										bw.write("Endereço: " + ListaClientes.get(i).getEndereco());
										bw.newLine();
										bw.write("Telefone: " + ListaClientes.get(i).getTelefone());
										bw.newLine();
										bw.write("Quantidade de compras: " + ListaClientes.get(i).getQntVendas());
										bw.newLine();
										bw.write("ID da conta: " + ListaClientes.get(i).getId());
										bw.newLine();
										bw.write("-------------------------------------------");
										bw.newLine();
										bw.newLine();
										cont++;
									}
									System.out.println("Arquivo gerado com sucesso");
								}
								catch(IOException e){
									e.printStackTrace();
								}
							}
						break;

						case 2:
							if(qntProdutos==0){
								System.out.println("Nenhum produto cadastrado");
							}else{
								//Remove o conteudo anterior do arquivo
								try {
									// Cria um objeto FileWriter com append falso (para sobrescrever o conteúdo)
									FileWriter escritor = new FileWriter(arquivo_produtos, false);
									// Escreve uma string vazia no arquivo
									escritor.write("");
									// Fecha o objeto FileWriter
									escritor.close();
									
									System.out.println("Conteúdo do arquivo excluído com sucesso.");
								} catch (IOException e) {
									System.out.println("Ocorreu um erro ao tentar excluir o conteúdo do arquivo: " + e.getMessage());
								}

								try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo_produtos))){
									cont=1;
									for(i=0;i<ListaProdutos.size();i++){
										bw.write("Produto " + cont);
										bw.newLine();
										bw.write("Nome: " + ListaProdutos.get(i).getNome());
										bw.newLine();
										bw.write("Quantidade no estoque: " + ListaProdutos.get(i).getQuantidade());
										bw.newLine();
										bw.write("Preço: " + ListaProdutos.get(i).getPreco());
										bw.newLine();
										bw.write("Quantidade ventido: " + ListaProdutos.get(i).getQntVendido());
										bw.newLine();
										bw.write("ID do produto: " + ListaProdutos.get(i).getId());
										bw.newLine();
										bw.write("-------------------------------------------");
										bw.newLine();
										bw.newLine();
										cont++;
									}
									System.out.println("Arquivo gerado com sucesso");
								}
								catch(IOException e){
									e.printStackTrace();
								}
							}
							
						break;

						case 3:
							if(qntFornecedores==0){
								System.out.println("Nenhum fornecedor cadastdado");
							}else{
								//Remove o conteudo anterior do arquivo
								try {
									// Cria um objeto FileWriter com append falso (para sobrescrever o conteúdo)
									FileWriter escritor = new FileWriter(arquivo_fornecedores, false);
									// Escreve uma string vazia no arquivo
									escritor.write("");
									// Fecha o objeto FileWriter
									escritor.close();
									
									System.out.println("Conteúdo do arquivo excluído com sucesso.");
								} catch (IOException e) {
									System.out.println("Ocorreu um erro ao tentar excluir o conteúdo do arquivo: " + e.getMessage());
								}

								try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo_fornecedores))){
									cont=1;
									for(i=0;i<ListaFornecedores.size();i++){
										bw.write("Fornecedor " + cont);
										bw.newLine();
										bw.write("Empresa: " + ListaFornecedores.get(i).getEmpresa());
										bw.newLine();
										bw.write("Tipo de produto: " + ListaFornecedores.get(i).getTipo_de_produto());
										bw.newLine();
										bw.write("Reputação: " + ListaFornecedores.get(i).getReputacao());
										bw.newLine();
										bw.write("Id do fornecedor: " + ListaFornecedores.get(i).getId());
										bw.newLine();
										bw.write("-------------------------------------------");
										bw.newLine();
										bw.newLine();
										cont++;
									}
									System.out.println("Arquivo gerado com sucesso");
								}
								catch(IOException e){
									e.printStackTrace();
								}
							}
						break;

						case 4:
							if(qntVendas==0){
								System.out.println("Nenhuma venda realizada");
							}else{
								//Remove o conteudo anterior do arquivo
								try {
									// Cria um objeto FileWriter com append falso (para sobrescrever o conteúdo)
									FileWriter escritor = new FileWriter(arquivo_vendas, false);
									// Escreve uma string vazia no arquivo
									escritor.write("");
									// Fecha o objeto FileWriter
									escritor.close();
									
									System.out.println("Conteúdo do arquivo excluído com sucesso.");
								} catch (IOException e) {
									System.out.println("Ocorreu um erro ao tentar excluir o conteúdo do arquivo: " + e.getMessage());
								}

								try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo_vendas))){
									cont=1;
									for(i=0;i<ListaVendas.size();i++){
										bw.write("Venda " + cont);
										bw.newLine();
										bw.write("Nome do cliente: " + ListaVendas.get(i).getCliente());
										bw.newLine();
										bw.write("Nome do produto: " + ListaVendas.get(i).getProduto());
										bw.newLine();
										bw.write("Quantidade ventida: " + ListaVendas.get(i).getQnt());
										bw.newLine();
										bw.write("-------------------------------------------");
										bw.newLine();
										bw.newLine();
										cont++;
									}
									System.out.println("Arquivo gerado com sucesso");
								}
								catch(IOException e){
									e.printStackTrace();
								}
							}
						break;

						default:
							System.out.println("Opção inválida");
						break;
					}
					

				break;
				
				default:
					System.out.println("Opção inválida");
				break;
	
			}

			System.out.println("\n------------------------------------");
						
		}while(op<=16);
		System.out.println("Programa finalizado");
		
		sc.close();

    }

	//Funções-----------------------------

	//Busca a posição do cliente na lista
	public static int BuscarNomeCliente(List <Clientes> lista, String nome){
		int i=0;
		int result=-1;
		for (i=0;i<lista.size();i++){
			if(lista.get(i).getNome().equalsIgnoreCase(nome)){
				result = i;
			}
		}
		return result;
	}

	//Busca a posição do clienteP na lista
	public static int BuscarNomeClienteP(List <ClienteP> lista, String nome){
		int i=0;
		int result=-1;
		for (i=0;i<lista.size();i++){
			if(lista.get(i).getNome().equalsIgnoreCase(nome)){
				result = i;
			}
		}
		return result;
	}

	//Busca a posição do produto na lista
	public static int BuscarNomeProduto(List <Produtos> lista, String nome){
		int i=0;
		int result=-1;
		for (i=0;i<lista.size();i++){
			if(lista.get(i).getNome().equalsIgnoreCase(nome)){
				result = i;
			}
		}
		return result;
	}

	//Encontra a posição do fornecedor na lista
	public static int BuscarNomeFornecedor(List <Fornecedores> lista, String nome){
		int i=0;
		int result=-1;
		for (i=0;i<lista.size();i++){
			if(lista.get(i).getEmpresa().equalsIgnoreCase(nome)){
				result = i;
			}
		}
		return result;
	}

}