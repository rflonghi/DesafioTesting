package br.com.meli.desafio_quality.exception;

public class DistrictNotFoundException  extends RuntimeException{

    public DistrictNotFoundException(String name) {
        super("District " + name + " not found.");
    }
}
