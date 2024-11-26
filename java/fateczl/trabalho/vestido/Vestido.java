package fateczl.trabalho.vestido;

public class Vestido {
    private int id;
    private String cor;
    private String tamanho;
    private String material;
    private double preco;
    private String observacao;

    public Vestido(){
        this.id = 0;
        this.cor = "";
        this.tamanho = "";
        this.material = "";
        this.preco = 0.00;
        this.observacao = "";
    }

    public void setId  (int id){
        this.id = id;
    }
    public void setCor (String cor){
        this.cor = cor;
    }
    public void setTamanho (String tamanho){
        this.tamanho = tamanho;
    }
    public void setMaterial (String material){
        this.material = material;
    }
    public void setPreco (double preco){
        this.preco = preco;
    }
    public void setObservacao (String observacao){this.observacao = observacao;}

    public  int getId(){return  id;}
    public String getCor(){
        return  cor;
    }
    public String getTamanho(){
        return  tamanho;
    }
    public String getMaterial(){
        return material;
    }
    public double getPreco(){
        return preco;
    }
    public String getObservacao(){
        return observacao;
    }
}
