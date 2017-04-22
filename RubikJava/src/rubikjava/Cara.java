/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikjava;

/**
 *
 * @author pabloespi
 */
public class Cara {
    private String [] pieza;

//    public Cara() {
//        this.pieza = new String[4];
//    }

    public Cara(String a,String b,String c,String d){
        pieza= new String[4];
        this.pieza[0]=a;
        this.pieza[1]=b;
        this.pieza[2]=c;
        this.pieza[3]=d;
    }

    public String[] getPieza() {
        return pieza;
    }

    public void setPieza(String[] pieza) {
        this.pieza = pieza;
    }
    
    

    
    
}
