/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImpresoraRemota;



/**
 *
 * @author juanc
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        Impresora unaImpresora = new Impresora();
        char[] dato = {'G', 'F', 'E', 'D', 'C', 'B', 'A','X','1','2','3','4','5', 'D', 'C', 'B', 'A','X','1','2'};
        // char[] dato = {'A', 'B', 'B', 'D', 'C', 'F', 'G'};
        ProcesoImpresora unaImpresion = new ProcesoImpresora(unaImpresora, dato);

        ProcesoCanalPrimario unPCP = new ProcesoCanalPrimario(unaImpresora);
        ProcesoCanalSecundario unPCS = new ProcesoCanalSecundario(unaImpresora);
        ProcesoImprimir imprimir = new ProcesoImprimir(unaImpresora);
      
        imprimir.start();
        unaImpresion.start();
        unPCP.start();
        unPCS.start();

    }

}
