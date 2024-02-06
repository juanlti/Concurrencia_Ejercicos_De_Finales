/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImpresoraRemota;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class ProcesoImpresora extends Thread {

    Impresora unaImpresora;
    char[] dato;
    int longitudDato, detenerse;

    public ProcesoImpresora(Impresora unaImpresora, char[] dato) {

        this.unaImpresora = unaImpresora;
        this.longitudDato = dato.length;
        this.dato = dato;
        this.detenerse = -1;
    }

    public void run() {

        //imprimir
        while (longitudDato > 0) {
            try {

                this.longitudDato = this.unaImpresora.imprimir(this.dato, this.longitudDato);

            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoImpresora.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
          System.out.println("");
        System.out.println("Proceso Impresora estado: finalizado");

    }

}
