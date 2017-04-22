/*****************************************************************************

		Copyright (c) Rafael Gonzalez & Pablo Espinosa

 Project:  RUBIK
 FileName: RUBIK.PRO
 Purpose: No commercial purposes
 Written by: Rafael Gonzalez
 	     Pablo Espinosa
******************************************************************************/

include "rubik.inc"

domains
	posicion=symbol
	cara=f(posicion,posicion,posicion,posicion)
	cubo=c(cara,cara,cara,cara,cara,cara)
	lista=cubo*
	limite=integer
	
predicates
	/* esquina(posicion,posicion,posicion)*/
	mueve(cubo,cubo)
	resuelve(lista,cubo,limite,limite)
	escribe(lista)
	mejorsol(integer)
	miembro(cubo,lista)
clauses
	/*
	esquina(NEr,SEa,NOm)
	esquina(NOr,SOa,NEb)
	esquina(SEr,SOm,NEv)
	esquina(SEb,SOr,NOv)
	esquina(NEa,NEm,NOn)
	esquina(NOa,Nen,NOb)
	esquina(SEn,SOb,SOv)
	esquina(SOn,SEm,SEv)
	*/
	
	
	/*Movimientos    Delante,Detrás,Arriba,Abajo,Izq.,Dcha*/
	
	/*Derecha Arriba*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NEV,SOR,SEV),f(SEA,NEN,NEA,SEN),f(NOA,NER,SOA,SER),f(NOV,SON,SOV,NON),f(NOB,NEB,SOB,SEB),f(SOM,NOM,SEM,NEM))).
	
	/*Derecha Abajo*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NEA,SOR,SEA),f(SEV,NEN,NEV,SEN),f(NOA,SON,SOA,NON),f(NOV,NER,SOV,SER),f(NOB,NEB,SOB,SEB),f(NEM,SEM,NOM,SOM))).
	
	/*Izquierda Arriba*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOV,NER,SOV,SER),f(NON,SOA,SON,NOA),f(NOR,NEA,SOR,SEA),f(SEN,NEV,NEN,SEV),f(NEB,SEB,NOB,SOB),f(NOM,NEM,SOM,SEM))).
	
	/*Izquierda Abajo*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOA,NER,SOA,SER),f(NON,SOV,SON,NOV),f(SEN,NEA,NEN,SEA),f(NOR,NEV,SOR,SEV),f(SOB,NOB,SEB,NEB),f(NOM,NEM,SOM,SEM))).
	
	/*Delante,Detrás,Arriba,Abajo,Izq.,Dcha*/
	
	/*Arriba Derecha*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOB,NEB,SOR,SER),f(NOM,NEM,SON,SEN),f(NEA,SEA,NOA,SOA),f(NOV,NEV,SOV,SEV),f(NON,NEN,SOB,SEB),f(NOR,NER,SOM,SEM))).
	
	/*Arriba Izquierda*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOM,NEM,SOR,SER),f(NOB,NEB,SON,SEN),f(SOA,NOA,SEA,NEA),f(NOV,NEV,SOV,SEV),f(NOR,NER,SOB,SEB),f(NON,NEN,SOM,SEM))).
	
	/*Abajo Derecha*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NER,SOB,SEB),f(NON,NEN,SOM,SEM),f(NOA,NEA,SOA,SEA),f(SOV,NOV,SEV,NEV),f(NOB,NEB,SON,SEN),f(NOM,NEM,SOR,SER))).
	
	/*Abajo Izquierda*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NER,SOM,SEM),f(NON,NEN,SOB,SEB),f(NOA,NEA,SOA,SEA),f(NEV,SEV,NOV,SOV),f(NOB,NEB,SOR,SER),f(NOM,NEM,SON,SEN))).

    
    /*Lateral*/
	
	/*Delante derecha*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(SOR,NOR,SER,NER),f(NON,NEN,SON,SEN),f(NOA,NEA,SEB,NEB),f(SOM,NOM,SOV,SEV),f(NOB,NOV,SOB,NEV),f(SOA,NEM,SEA,SEM))).
	
	/*Delante izquierda*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NER,SER,NOR,SOR),f(NON,NEN,SON,SEN),f(NOA,NEA,NOM,SOM),f(NEB,SEB,SOV,SEV),f(NOB,SEA,SOB,SOA),f(NEV,NEM,NOV,SEM))).
	
	/*Atras Derecha*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NER,SOR,SER),f(NEN,SEN,NON,SON),f(SOB,NOB,SOA,SEA),f(NOV,NEV,SEM,NEM),f(SOV,NEB,SEV,SEB),f(NOM,NOA,SOM,NEA))).
	
	/*Atras Izquierda*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NER,SOR,SER),f(SON,NON,SEN,NEN),f(NEM,SEM,SOA,SEA),f(NOV,NEV,NOB,SOB),f(NEA,NEB,NOA,SEB),f(NOM,SEV,SOM,SOV))).



	/*Estados repetidos */
        miembro(E,[E|_]).
        miembro(E,[_|T]):-
        	miembro(E,T).
        	
        	
        	
        	
        	
	/*Resolucion de algoritmo */
     resuelve(Lista,Destino,_,_):-
        	Lista=[H|T],
        	Destino=H,
        	escribe(Lista).
        	
        resuelve(Lista,Destino,Lim_ant,Limite):-
        	Lista=[H|T],
        	not(miembro(H,T)),
        	mueve(H,Hfinal),
        	Nlista=[Hfinal|Lista],
        	Nue_Lim=Lim_ant+1,
        	Nue_Lim<=Limite,
        	resuelve(Nlista,Destino,Nue_Lim,Limite).
        	
        /*Escritura de la lista */
        escribe([]).
        escribe([H|T]):-
        	escribe(T),
        	write(H,'\n').
        	
  
  	/* Un movimiento
	mejorsol(Lim_ini):-
        	resuelve([c(f(ser,nor,sea,sen),f(non,son,sor,nob),f(sev,nea,nev,neb),f(nom,sov,noa,nov),f(sem,som,nen,ner),f(soa,nem,sob,seb))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
	*/
	/* 3 Movimientos incluido lateral*/
	mejorsol(Lim_ini):-
        	resuelve([c(f(nor,nea,nom,som),f(nen,seb,sev,sob),f(sor,nob,soa,non),f(ner,ser,sen,sem),f(nov,neb,sov,sea),f(nem,noa,nev,son))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
	
	 

        /*	
        mejorsol(Lim_ini):-
        	resuelve([c(f(nor,ner,sor,ser),f(nen,non,sen,son),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(neb,nob,seb,sob),f(nom,nem,som,sem))],c(f(nor,ner,sor,ser),f(nen,non,sen,son),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(neb,nob,seb,sob),f(nom,nem,som,sem)),1,Lim_ini).
       	*/
        
        mejorsol(Lim_ini):-
        	Nue_lim=Lim_ini+1,
        	mejorsol(Nue_lim).
	
	
  /*rubik():-!.
*/
goal
	mejorsol(1).
  /*rubik().*/
