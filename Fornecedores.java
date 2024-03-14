import java.util.Scanner;

public class Fornecedores{

    private String tipo_de_produto;
    private String empresa;
    private int reputacao;
    private int id;
    Scanner sc = new Scanner(System.in);

    //Construtor
    public Fornecedores(String tipo_de_produto, String empresa, int reputacao, int id) {
        this.tipo_de_produto = tipo_de_produto;
        this.empresa = empresa;
        this.reputacao = reputacao;
        this.id = id;
    }
    
    public Fornecedores(){

    }

    //getter e setters
    public String getTipo_de_produto() {
        return tipo_de_produto;
    }

    public void setTipo_de_produto(String tipo_de_produto) {
        this.tipo_de_produto = tipo_de_produto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getReputacao() {
        return reputacao;
    }

    public void setReputacao(int reputacao) {
        this.reputacao = reputacao;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    //Metodos
    public void cadastrar(int i){
        System.out.printf("Informe o tipo de produto do fornecedor: ");
        tipo_de_produto = sc.nextLine();
        System.out.printf("Informe a empresa que o fornecedor atua: ");
        empresa = sc.nextLine();
        this.id+=i;
    }

    public void aumentar_reputacao(int i){
        this.reputacao+=i;
    }

    public String toString(){

        if(reputacao>1){
            return "Empresa: " + empresa + ". Tipo de produto: " + tipo_de_produto + ". Reputação: " + String.format("%d", reputacao) +" pontos. Id: " + String.format("%d", id);
        }else{
            return "Empresa: " + empresa + ". Tipo de produto: " + tipo_de_produto + ". Reputação: " + String.format("%d", reputacao) +" ponto. Id: " + String.format("%d", id);
        }
    }

}
