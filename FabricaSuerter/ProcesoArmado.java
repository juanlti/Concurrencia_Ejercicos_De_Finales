/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FabricaSuerter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class ProcesoArmado extends Thread {

    int cantArmado;
    Suerter unSueter;

    public ProcesoArmado(Suerter unSueter, int cantArmado) {
        this.cantArmado = cantArmado;
        this.unSueter = unSueter;
    }

    public void run() {

        int cant = 0;

        while (cant < this.cantArmado) {
            try {

                this.unSueter.procesoArmado();
                cant++;
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoArmado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
