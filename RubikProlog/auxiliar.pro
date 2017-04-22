/*Movimientos    Delante,Detrás,Arriba,Abajo,Izq.,Dcha*/
	
	/*Derecha Arriba*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NEV,SOR,SEV),f(SEA,NEN,NEA,SEN),f(NOA,NER,SOA,SER),f(NOV,SON,SOV,NON),f(NOB,NEB,SOB,SEB),f(SOM,NOM,SEM,NEM))).
	
	/*Derecha Abajo*/
	mueve(c(f(NOR,NER,SOR,SER),f(NON,NEN,SON,SEN),f(NOA,NEA,SOA,SEA),f(NOV,NEV,SOV,SEV),f(NOB,NEB,SOB,SEB),f(NOM,NEM,SOM,SEM)),c(f(NOR,NEA,SOR,SEA),f(SEV,NEN,NEV,SEN),f(NOA,SON,SOA,NON),f(NOV,NER,SOV,SER),f(NOB,NEB,SOB,SEB),f(NEM,SEM,SOM,SOM))).
	
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




/* PRUEBAS



mejorsol(Lim_ini):-
        	resuelve([c(f(noa,nom,som,soa),f(sem,sob,nov,nea),f(son,sen,nen,ner),f(nev,neb,sor,non),f(nob,sev,ser,seb),f(sea,sov,nor,nem))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
   
Un movimiento de desorden

mejorsol(Lim_ini):-
        	resuelve([c(f(nor,ner,sob,seb),f(non,nen,som,sem),f(noa,nea,soa,sea),f(sov,nov,sev,nev),f(nob,neb,son,sen),f(nom,nem,sor,ser))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
   
   
Dos movimientos

mejorsol(Lim_ini):-
        	resuelve([c(f(nor,nev,sem,nem),f(sea,nen,sob,seb),f(noa,ner,soa,ser),f(son,non,nov,sov),f(nob,neb,sor,sev),f(som,nom,nea,sen))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
            
            
Tres Movimientos

mejorsol(Lim_ini):-
        	resuelve([c(f(nor,nev,sem,nem),f(sob,sea,seb,nen),f(nom,sen,soa,ser),f(son,non,nob,sor),f(ner,neb,noa,sev),f(som,sov,nea,nov))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).
            
            
Bastantes movimientos de desorden

mejorsol(Lim_ini):-
        	resuelve([c(f(sov,nen,soa,seb),f(nem,ser,son,nom),f(som,non,sob,noa),f(nor,nov,sea,sev),f(nev,sen,ner,neb),f(nob,nea,sor,sem))],c(f(nor,ner,sor,ser),f(non,nen,son,sen),f(noa,nea,soa,sea),f(nov,nev,sov,sev),f(nob,neb,sob,seb),f(nom,nem,som,sem)),1,Lim_ini).



*/