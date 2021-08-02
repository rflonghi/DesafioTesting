package br.com.meli.desafio_quality.exception;

/*
// Author: Michel Andrew
*/
public class HouseNotFoundException extends RuntimeException{

    public HouseNotFoundException(Long id) {
        super("House " + id + " not found.");
    }
}
