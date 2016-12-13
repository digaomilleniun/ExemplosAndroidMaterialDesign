package animation.rpires.com.br.exemplosmaterialdesign.domain;

/**
 * Created by rpires on 13/12/2016.
 */

public class Carro {

    private String modelo;

    private String marca;

    private int foto;

    public Carro(String modelo, String marca, int foto) {
        this.modelo = modelo;
        this.marca = marca;
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
