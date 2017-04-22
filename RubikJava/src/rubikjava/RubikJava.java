/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikjava;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rafael Gonzalez Martin 70911449B
 * @author Pablo Espinosa Bermejo 70883640D
 */

/**
 * La función de evaluación que hemos utilizado es:
 *      p=g+h
 * 
 * -P es el un valor que tiene cada uno de los "estados" del cubo o nodo(si lo consideramos como un arbol) es la suma de G+H 
 * -G es lo que consideramos el "peso" que en nuestro caso es el numero de movimientos que se han hecho para llegar a dicho estado
 * cada movimiento suma un peso de valor 1 (consideramos todos los movimientos iguales)
 * -H(heurística) es la distancia hasta nuestro "goal"(lo declaramos como objetivo) y es el cubo resuelto, se calcula haciendo la suma de
 * pegatinas que no estan en el lugar correcto a mayor numero de pegatinas desordenadas, mas lejos estamos, cuando el numero
 * de pegatinas es igual a 0 es que hemos llegado a nuestra meta/goal
 * 
 */
public class RubikJava {

    private static Cubo inicial, objetivo, solucion;
    private static ArrayList<Cubo> abiertos, cerrados, soluciones;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        abiertos = new ArrayList<Cubo>();
        cerrados = new ArrayList<Cubo>();
        soluciones = new ArrayList<Cubo>();

        // Inicial del principio
//        inicial= new Cubo(new Cara("nor","nev","sem","nem"),new Cara("sob","sea","seb","nen"),new Cara("nom","sen","soa","ser"),new Cara("son","non","nob","sor"),new Cara("ner","neb","noa","sev"),new Cara("som","sov","nea","nov"));
//        Un movimiento
//        inicial= new Cubo(new Cara("nor","nev","sor","sev"),new Cara("sea","nen","nea","sen"),new Cara("noa","ner","soa","ser"),new Cara("nov","son","sov","non"),new Cara("nob","neb","sob","seb"),new Cara("som","nom","sem","nem"));
//        3 movimientos incluido lateral
          inicial = new Cubo(new Cara("nor", "nea", "nom", "som"), new Cara("nen", "seb", "sev", "sob"), new Cara("sor", "nob", "soa", "non"), new Cara("ner", "ser", "sen", "sem"), new Cara("nov", "neb", "sov", "sea"), new Cara("nem", "noa", "nev", "son"));
//          4 movimientos
//        inicial = new Cubo(new Cara("som", "son", "sor", "sob"), new Cara("ner", "nob", "nem", "neb"), new Cara("nen", "nom", "ser", "sev"), new Cara("nov", "sen", "nor", "nea"), new Cara("noa", "nev", "soa", "seb"), new Cara("sem", "sea", "sov", "non"));

//      7 movimientos
//        inicial = new Cubo(new Cara("non", "sov", "nob", "sem"), new Cara("seb", "sea", "soa", "som"), new Cara("nom", "nov", "nea", "sen"), new Cara("nen", "sev", "ser", "nor"), new Cara("ner", "nem", "nev", "noa"), new Cara("sob", "sor", "son", "neb"));

        objetivo = new Cubo(new Cara("nor", "ner", "sor", "ser"), new Cara("non", "nen", "son", "sen"), new Cara("noa", "nea", "soa", "sea"), new Cara("nov", "nev", "sov", "sev"), new Cara("nob", "neb", "sob", "seb"), new Cara("nom", "nem", "som", "sem"));
        AStar(inicial, objetivo);
    }

    public static void AStar(Cubo i, Cubo f) {

        Cubo derechaArriba, derechaAbajo, izquierdaArriba, izquierdaAbajo, arribaDerecha, arribaIzquierda, abajoDerecha, abajoIzquierda;

        abiertos.add(i);
        int k = 0;
        System.out.println("Trabajando...");
        while (!abiertos.isEmpty()) {
            Cubo menor = abiertos.get(0);
            for (Cubo prueba : abiertos) {
                if (prueba.getP() < menor.getP()) {
                    menor = prueba;
                }
            }

            abiertos.remove(menor);
            cerrados.add(menor);

            int flagBorrar = 0;

            if (!soluciones.isEmpty()) {
                if ((menor.getP()) >= (soluciones.get(soluciones.size() - 1).getP())) {
                    flagBorrar = 1;
                    break;
                }
            }

            if (flagBorrar == 0) {
                derechaArriba = derechaArriba(menor);
                derechaAbajo = derechaAbajo(menor);
                izquierdaArriba = izquierdaArriba(menor);
                izquierdaAbajo = izquierdaAbajo(menor);
                arribaDerecha = arribaDerecha(menor);
                arribaIzquierda = arribaIzquierda(menor);
                abajoDerecha = abajoDerecha(menor);
                abajoIzquierda = abajoIzquierda(menor);
                
                abiertos.add(derechaArriba);
                abiertos.add(derechaAbajo);
                abiertos.add(izquierdaArriba);
                abiertos.add(izquierdaAbajo);
                abiertos.add(arribaDerecha);
                abiertos.add(arribaIzquierda);
                abiertos.add(abajoDerecha);
                abiertos.add(abajoIzquierda);

                Iterator<Cubo> it = abiertos.iterator();
                while (it.hasNext()) {
                    Cubo iCubo = it.next();
                    if (iCubo.getH() == 0) {
                        soluciones.add(iCubo);
                        iCubo.imprimirCamino();
                        k++;
                        break;
                    }
                    for (Cubo alcanzado : cerrados) {
                        if (iCubo.nDiferencias(alcanzado) == 0) {
                            if (iCubo.getP() > alcanzado.getP()) {
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            }
            //System.out.println("Tamaño prueba: " + abiertos.size());
        }
    }

    public static Cubo derechaArriba(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        atras = new Cara(c.getCaras()[2].getPieza()[3], c.getCaras()[1].getPieza()[1], c.getCaras()[2].getPieza()[1], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[2].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[1].getPieza()[2], c.getCaras()[3].getPieza()[2], c.getCaras()[1].getPieza()[0]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[3], c.getCaras()[5].getPieza()[1]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Derecha Arriba");

        return nuevo;
    }

    public static Cubo derechaAbajo(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        atras = new Cara(c.getCaras()[3].getPieza()[3], c.getCaras()[1].getPieza()[1], c.getCaras()[3].getPieza()[1], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[1].getPieza()[2], c.getCaras()[2].getPieza()[2], c.getCaras()[1].getPieza()[0]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[3].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[1], c.getCaras()[5].getPieza()[3], c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[2]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Derecha Abajo");

        return nuevo;
    }

    public static Cubo izquierdaArriba(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[3].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[2].getPieza()[2], c.getCaras()[1].getPieza()[2], c.getCaras()[2].getPieza()[0]);
        arriba = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[1].getPieza()[3], c.getCaras()[3].getPieza()[1], c.getCaras()[1].getPieza()[1], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[1], c.getCaras()[4].getPieza()[3], c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[2]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Izquierda Arriba");

        return nuevo;
    }

    public static Cubo izquierdaAbajo(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[2].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[3].getPieza()[2], c.getCaras()[1].getPieza()[2], c.getCaras()[3].getPieza()[0]);
        arriba = new Cara(c.getCaras()[1].getPieza()[3], c.getCaras()[2].getPieza()[1], c.getCaras()[1].getPieza()[1], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[3], c.getCaras()[4].getPieza()[1]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Izquierda Abajo");

        return nuevo;
    }

    public static Cubo arribaDerecha(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[1], c.getCaras()[2].getPieza()[3], c.getCaras()[2].getPieza()[0], c.getCaras()[2].getPieza()[2]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[3].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Arriba Derecha");

        return nuevo;
    }

    public static Cubo arribaIzquierda(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[2].getPieza()[1], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[3], c.getCaras()[2].getPieza()[1]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[3].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Arriba Izquierda");

        return nuevo;
    }

    public static Cubo abajoDerecha(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[3].getPieza()[2], c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[3], c.getCaras()[3].getPieza()[1]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Abajo Derecha");

        return nuevo;
    }

    public static Cubo abajoIzquierda(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[3].getPieza()[1], c.getCaras()[3].getPieza()[3], c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[2]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[1], c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Abajo Izquierda");

        return nuevo;
    }

    public static Cubo delanteDerecha(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[3], c.getCaras()[0].getPieza()[1]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[4].getPieza()[3], c.getCaras()[4].getPieza()[1]);
        abajo = new Cara(c.getCaras()[5].getPieza()[2], c.getCaras()[5].getPieza()[0], c.getCaras()[3].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[3].getPieza()[0], c.getCaras()[4].getPieza()[2], c.getCaras()[3].getPieza()[1]);
        derecha = new Cara(c.getCaras()[2].getPieza()[2], c.getCaras()[5].getPieza()[1], c.getCaras()[2].getPieza()[3], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Delante Derecha");

        return nuevo;
    }

    public static Cubo delanteIzquierda(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[1], c.getCaras()[0].getPieza()[3], c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[2]);
        atras = new Cara(c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[1], c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[3]);
        arriba = new Cara(c.getCaras()[2].getPieza()[0], c.getCaras()[2].getPieza()[1], c.getCaras()[5].getPieza()[0], c.getCaras()[5].getPieza()[2]);
        abajo = new Cara(c.getCaras()[4].getPieza()[1], c.getCaras()[4].getPieza()[3], c.getCaras()[3].getPieza()[2], c.getCaras()[3].getPieza()[3]);
        izquierda = new Cara(c.getCaras()[4].getPieza()[0], c.getCaras()[2].getPieza()[3], c.getCaras()[4].getPieza()[2], c.getCaras()[2].getPieza()[2]);
        derecha = new Cara(c.getCaras()[3].getPieza()[1], c.getCaras()[5].getPieza()[1], c.getCaras()[3].getPieza()[0], c.getCaras()[5].getPieza()[3]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Delante Izquierda");

        return nuevo;
    }

    public static Cubo atrasDerecha(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[1], c.getCaras()[1].getPieza()[3], c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[2]);
        arriba = new Cara(c.getCaras()[4].getPieza()[2], c.getCaras()[4].getPieza()[0], c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[5].getPieza()[3], c.getCaras()[5].getPieza()[1]);
        izquierda = new Cara(c.getCaras()[3].getPieza()[2], c.getCaras()[4].getPieza()[1], c.getCaras()[3].getPieza()[3], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[2].getPieza()[0], c.getCaras()[5].getPieza()[2], c.getCaras()[2].getPieza()[1]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Atras Derecha");

        return nuevo;
    }

    public static Cubo atrasIzquierda(Cubo c) {
        Cubo nuevo;
        Cara delante, atras, arriba, abajo, izquierda, derecha;

        delante = new Cara(c.getCaras()[0].getPieza()[0], c.getCaras()[0].getPieza()[1], c.getCaras()[0].getPieza()[2], c.getCaras()[0].getPieza()[3]);
        atras = new Cara(c.getCaras()[1].getPieza()[2], c.getCaras()[1].getPieza()[0], c.getCaras()[1].getPieza()[3], c.getCaras()[1].getPieza()[1]);
        arriba = new Cara(c.getCaras()[5].getPieza()[1], c.getCaras()[5].getPieza()[3], c.getCaras()[2].getPieza()[2], c.getCaras()[2].getPieza()[3]);
        abajo = new Cara(c.getCaras()[3].getPieza()[0], c.getCaras()[3].getPieza()[1], c.getCaras()[4].getPieza()[0], c.getCaras()[4].getPieza()[2]);
        izquierda = new Cara(c.getCaras()[2].getPieza()[1], c.getCaras()[4].getPieza()[1], c.getCaras()[2].getPieza()[0], c.getCaras()[4].getPieza()[3]);
        derecha = new Cara(c.getCaras()[5].getPieza()[0], c.getCaras()[3].getPieza()[3], c.getCaras()[5].getPieza()[2], c.getCaras()[3].getPieza()[2]);

        nuevo = new Cubo(delante, atras, arriba, abajo, izquierda, derecha, c.getMovimientos(), "Atras Izquierda");

        return nuevo;
    }

}
