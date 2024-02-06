/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FabricaSuerter;

/**
 *
 * @author juanc
 */
public class Suerter {

    int cantidadDeMangas, cantidadContornos, caja, suerterUnitario;

    public Suerter() {

        this.cantidadContornos = 0;
        this.cantidadContornos = 0;
        this.suerterUnitario = 0;
        this.caja = 0;

    }

    public synchronized void procesoMangas() throws InterruptedException {

        //cantidad de mangas
        this.cantidadDeMangas++;

        if (this.cantidadDeMangas >= 2) {
            this.notifyAll();
        }

        while (this.cantidadDeMangas > 40) {
            //esperando que al menos 2 mangas salga de la cesta de mangas

            this.wait();
        }

    }

    public synchronized void procesoContorno() throws InterruptedException {

        //cantidad de contornos
        this.cantidadContornos++;
        if (this.cantidadContornos > 0) {
            this.notifyAll();
        }

        while (this.cantidadContornos > 20) {
            //esperando que al menos 1 contorno salga de la cesta de contornos

            this.wait();
        }

    }

    public synchronized void procesoArmado() throws InterruptedException {

        //esperando una cantidad minima de un contono +  2 mangas
        while (this.cantidadContornos < 1 && this.cantidadDeMangas < 2) {
            //aca espera

            this.wait();

        }

        //modifca los valores
        this.cantidadContornos--;
        this.cantidadDeMangas--;
        this.cantidadDeMangas--;
        this.suerterUnitario++;

        if (suerterUnitario == 10) {
            this.caja++;

            this.suerterUnitario = 0;

        }
        System.out.println("Cantidad de cajas " + this.caja + " suerters sin cajas " + this.suerterUnitario);

    }

}
