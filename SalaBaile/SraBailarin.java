/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaBaile;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class SraBailarin extends Thread {

    Sala unaSala;
    String nombre, nombrePareja;

    public SraBailarin(String nombre, Sala unaSala, String nombrePareja) {
        this.unaSala = unaSala;
        this.nombre = nombre;
        this.nombrePareja = nombrePareja;

    }

    public void run() {

        try {
            //tiempo
          
            this.unaSala.ingresoSra(this.nombre, this.nombrePareja);

            this.unaSala.bailar(this.nombre, true);
            this.unaSala.sraFinalizandoBaile(nombre);

        } catch (InterruptedException ex) {
            Logger.getLogger(SraBailarin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
