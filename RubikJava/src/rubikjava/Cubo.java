/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikjava;

import java.util.ArrayList;

/**
 *
 * @author pabloespi
 */
public class Cubo {
    private Cara[] caras;
    private int h,g,p;
    private ArrayList<String> movimientos;

    public Cubo(String s,Cara a, Cara b,Cara c, Cara d,Cara e, Cara f){
        caras=new Cara[6];
        this.caras[0]=a;
        this.caras[1]=b;
        this.caras[2]=c;
        this.caras[3]=d;
        this.caras[4]=e;
        this.caras[5]=f;
    }
    public Cubo(Cara a, Cara b,Cara c, Cara d,Cara e, Cara f) {
        Cubo objetivo=new Cubo("",new Cara("nor","ner","sor","ser"),new Cara("non","nen","son","sen"),new Cara("noa","nea","soa","sea"),new Cara("nov","nev","sov","sev"),new Cara("nob","neb","sob","seb"),new Cara("nom","nem","som","sem"));

        caras=new Cara[6];
        this.caras[0]=a;
        this.caras[1]=b;
        this.caras[2]=c;
        this.caras[3]=d;
        this.caras[4]=e;
        this.caras[5]=f;
        
        int k=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){

                  if(!this.getCaras()[i].getPieza()[j].equals(objetivo.getCaras()[i].getPieza()[j])){
                      //System.out.printf("A: "+a.getCaras()[i].getPieza()[j] + " B: " + a.getCaras()[i].getPieza()[j]+"\n");
                       k++;
                      
                  }
            }
        }
        
        this.h=k;
        this.movimientos=new ArrayList<String>();
        this.g=movimientos.size();
        this.p=this.g+this.h;
        
    }
    
    public Cubo(Cara a, Cara b,Cara c, Cara d,Cara e, Cara f,ArrayList<String> as, String s) {
        Cubo objetivo=new Cubo(new Cara("nor","ner","sor","ser"),new Cara("non","nen","son","sen"),new Cara("noa","nea","soa","sea"),new Cara("nov","nev","sov","sev"),new Cara("nob","neb","sob","seb"),new Cara("nom","nem","som","sem"));

        caras=new Cara[6];
        this.caras[0]=a;
        this.caras[1]=b;
        this.caras[2]=c;
        this.caras[3]=d;
        this.caras[4]=e;
        this.caras[5]=f;
        
        int k=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){

                  if(!this.getCaras()[i].getPieza()[j].equals(objetivo.getCaras()[i].getPieza()[j])){
                      //System.out.printf("A: "+a.getCaras()[i].getPieza()[j] + " B: " + a.getCaras()[i].getPieza()[j]+"\n");
                       k++;
                      
                  }
            }
        }
        
        this.h=k;
        this.movimientos=new ArrayList<String>();
        this.movimientos.addAll(as);
        this.movimientos.add(s);
        this.g=this.movimientos.size();
        this.p=this.g+this.h;
        
    }

    public Cara[] getCaras() {
        return caras;
    }

    public void setCaras(Cara[] caras) {
        this.caras = caras;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<String> movimientos) {
        this.movimientos = movimientos;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
    
    
    /**
     * Calcula las diferencias de un cubo respecto a otro
     * @param b
     * @return 
     */
    public int nDiferencias(Cubo b){
        int d=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){

                  if(!this.getCaras()[i].getPieza()[j].equals(b.getCaras()[i].getPieza()[j])){
                      //System.out.printf("A: "+a.getCaras()[i].getPieza()[j] + " B: " + a.getCaras()[i].getPieza()[j]+"\n");
                       d++;
                      
                  }
            }
        }
        
        //this.h=d;
        return d;
        
    }
    
    public void imprimir(){
        String[] ncaras={"adelante","atras","arriba","abajo","izquierda","derecha"};
        System.out.println("########---ESTADO---########");
        for(int i=0;i<6;i++){
            System.out.printf(ncaras[i]+": ");
            for(int j=0;j<4;j++){
                
                System.out.printf(this.getCaras()[i].getPieza()[j]+",");
                  
            }
            System.out.println("");
        }
    }

    void imprimirCamino() {
        System.out.println("######---CAMINO---######");
        
        System.out.println("Nº Pasos: " + this.movimientos.size());
        for (String cadena : this.movimientos) {
            System.out.println(cadena);
        }
    }
    
}
