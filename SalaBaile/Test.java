/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaBaile;

/**
 *
 * @author juanc
 */
public class Test {

    public static void main(String[] args) {

        Sala unaSala = new Sala();
        String[] nombresMujeres = {"María", "Laura", "Ana", "Sofía", "Elena"};

        String[] nombresHombres = {"Juan", "Carlos", "Pedro", "Luis", "Miguel"};

        SraBailarin[] unaFilaSra = new SraBailarin[5];
        SrBailarin[] unaFilaSr = new SrBailarin[5];

        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {

                //tienen parejas
                unaFilaSra[i] = new SraBailarin(nombresMujeres[i], unaSala, nombresHombres[i]);
                unaFilaSr[i] = new SrBailarin(nombresHombres[i], unaSala, nombresMujeres[i]);

            } else {
                // no tiene parejas
                unaFilaSra[i] = new SraBailarin(nombresMujeres[i], unaSala, "sin pareja");

                unaFilaSr[i] = new SrBailarin(nombresHombres[i], unaSala, "sin pareja");

            }
        }

        for (int i = 0;
                i < 5; i++) {
            unaFilaSra[i].start();
            unaFilaSr[i].start();
        }

    }

}
