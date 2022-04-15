package api.appusuario.services.dto.carro;

import javax.validation.constraints.NotNull;

public class CarroBuscaDTO {
    
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @NotNull
    private String chassi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    


}
