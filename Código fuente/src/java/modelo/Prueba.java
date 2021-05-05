/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ACER
 */
public class Prueba {
    public static void main(String[] args) {
        String autor = "Antoine de Saint Exupéry";
        
        String nombre = autor.split(" ", 2)[0];
        String apellidos = autor.split(" ", 2)[1];
        
        System.out.println("nombre: "+nombre);
        System.out.println("apellidos: "+apellidos);
    }
}
