package br.com.meli.desafio_quality.exception;

/*
// Author: Yan Broetto
*/
public class DistrictNotFoundException  extends RuntimeException{

    public DistrictNotFoundException(String name) {
        super("District " + name + " not found.");
    }
}
