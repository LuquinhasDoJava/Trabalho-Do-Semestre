package fateczl.trabalho.aluguel;

import java.time.LocalDate;

public class Aluguel {
    private int cliente_id;
    private int vestido_id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double precoTotal;
    private boolean status;

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getVestido_id() {
        return vestido_id;
    }

    public void setVestido_id(int vestido_id) {
        this.vestido_id = vestido_id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
