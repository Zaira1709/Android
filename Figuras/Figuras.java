/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

/**
 *
 * @author zaira
 */
public class Figuras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Fig fig= new Fig();
        fig.dibujarCuadrado(3);
        fig.dibujaRectangulo(3,7);
        fig.dibujaTrianguloEquilatero(8);
        System.out.println("Triangulo Escaleno");
        fig.dibujarTrianguloEscaleno(3);
        fig.dibujarRombo(3, 2);
        fig.dibujarHexagono(3);
        fig.dibujaroctagono(3);

    }
    
}
