/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImpresoraRemota;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author juanc
 */
public class Impresora {

    private final Lock lockImpresora;
    private final Condition ocupadoPorUnBuffer, imprimiPrimario, imprimeSecundario, utilizandoImpresora;
    int j, k, posicionDatoCompleto;
    boolean seguir, corte, cargaPrimario, cargaSecundario;
    char[] imprimirConPrioridad, imprimirConSecundario;
    char datoCargado;

    public Impresora() {

        lockImpresora = new ReentrantLock();
        ocupadoPorUnBuffer = lockImpresora.newCondition();

        imprimiPrimario = lockImpresora.newCondition();
        imprimeSecundario = lockImpresora.newCondition();
        utilizandoImpresora = lockImpresora.newCondition();

        datoCargado = ' ';
        corte = false;

        imprimirConPrioridad = new char[]{'#', '#', '#'};
        imprimirConSecundario = new char[]{'#', '#', '#', '#', '#'};

        cargaPrimario = false;
        cargaSecundario = false;

        posicionDatoCompleto = 0;
        j = 0;
        k = 0;

        seguir = false;
        System.out.println("Buffer con prioridad, tiene " + imprimirConPrioridad.length + "  lugares disponibles");
        System.out.println("Buffer sin prioridad, tiene " + imprimirConSecundario.length + " lugares disponibles");

    }

    public int imprimir(char[] dato, int longitud) throws InterruptedException {
        lockImpresora.lock();

        try {
            if (this.posicionDatoCompleto < longitud) {
                //hilo de peticiones,
                //verifica disponibilidad en el canal primero
                while (j < imprimirConPrioridad.length && this.posicionDatoCompleto < longitud) {
                    //memoria con prioridad

                    imprimirConPrioridad[j] = dato[this.posicionDatoCompleto];

                    this.posicionDatoCompleto++;
                    j++;

                }

                while (k < imprimirConSecundario.length && this.posicionDatoCompleto < longitud) {
                    //memoria sin prioridad

                    imprimirConSecundario[k] = dato[this.posicionDatoCompleto];
                    k++;
                    this.posicionDatoCompleto++;

                }

                //TEST DE CARGA
            /*
                 for (int i = 0; i < j; i++) {

                 System.out.println("VALORES P " + imprimirConPrioridad[i]);

                 }
                 for (int i = 0; i < k; i++) {

                 System.out.println("VALORES P/s " + imprimirConSecundario[i]);

                 }
                 System.out.println("carga de datos finalizada");
                 */
                System.out.println("");
                System.out.println("Comienzo de impresiones con buffer primario");
                System.out.println("");
                this.j--;

                while (j >= 0) {

                    datoCargado = imprimirConPrioridad[j];

                    //buffer contiene al menos un dato
                    //esperando el proceso
                    //  this.imprimirDisponible = true;
                    imprimiPrimario.signal();
                    this.cargaPrimario = true;

                    utilizandoImpresora.await();

                    imprimirConPrioridad[j] = '#';

                    j--;

                }

                this.j = 0;
                System.out.println("");
                System.out.println("Comienzo de impresiones con buffer secundario");
                System.out.println("");
                //termina la prioridad de canal primario
                int posK = 0;
                while (posK < this.k) {

                    datoCargado = imprimirConSecundario[posK];
                    //buffer contiene al menos un dato
                    //esperando el proceso

                    imprimeSecundario.signal();
                    this.cargaSecundario = true;

                    utilizandoImpresora.await();

                    imprimirConSecundario[posK] = '#';

                    posK++;

                }
                this.k = 0;

            } else {
                // todos los datos fueron cargados en los buffer
                // no es necesario repetir el proceso de carga
                imprimiPrimario.signal();

                datoCargado = ' ';
                longitud = -1;
                this.corte = true;
                cargaPrimario = true;
                this.cargaSecundario = true;

                utilizandoImpresora.signal();
                this.ocupadoPorUnBuffer.signal();
                this.imprimeSecundario.signal();

            }
        } finally {
            lockImpresora.unlock();
        }

        return longitud;

    }

    public boolean canalPrimario() throws InterruptedException {

        lockImpresora.lock();

        try {
            while (this.cargaPrimario == false) {

                imprimiPrimario.await();

            }
            this.cargaPrimario = false;

            this.seguir = true;
            this.ocupadoPorUnBuffer.signal();

        } finally {
            lockImpresora.unlock();
        }

        return this.corte;

    }

    public boolean canalSecundario() throws InterruptedException {
        lockImpresora.lock();

        try {
            while (this.cargaSecundario == false) {

                this.imprimeSecundario.await();

            }

            this.cargaSecundario = false;

            this.seguir = true;
            this.ocupadoPorUnBuffer.signal();

        } finally {
            lockImpresora.unlock();
        }

        return this.corte;

    }

    public boolean datosImpresos() throws InterruptedException {
        lockImpresora.lock();
        try {
            while (seguir == false) {
                this.ocupadoPorUnBuffer.await();

            }
            if (this.datoCargado != ' ') {
                System.out.print(" - " + datoCargado + " - ");
            }

            this.seguir = false;
            utilizandoImpresora.signal();
        } finally {
            lockImpresora.unlock();
        }
        return this.corte;

    }

}
