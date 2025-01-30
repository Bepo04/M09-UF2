# Espera amb Wait
![imgthreads](img/threads.png)


[ENUNCIAT](https://drive.google.com/file/d/1bSs_J3MTGCBnwk-vCZE4GtIIbJ8sHGno/view)

## Preguntes teòriques
### Perquè s'atura l'execució al cap d'un temps?
El programa, a grans trets, gestiona l'execució dels fils de la segïent forma:
- El mètode run() crida amb 50% de probabiltats a ferReserva() o a cancelaReserva().
- El mètode ferReserva() posa en espera qualsevol fil que entri fins que hi hagi alguna plaça disponible.
- El mètode cancelaReserva() fa un notifyAll() quan efectua la cancel·lació, és a dir, si un assistent cancel·la la seva plaça, desbloqueja tots els fils.

Veient aquest funcionament, dedueixo que, en el moment en que queden 0 places disponibles, i els 10 assistents entren a ferReserva() queden tots a l'espera, i no n'hi ha cap que pugui desactivar la opció. El problema recau en que els encarregats de fer el desbloqueig son els propis fils, susceptibles a bloquejar-se.


### Que passaria si enlloc de una probabilitat de 50/50 fos de 70/30 i 30/70?
#### Codi original (50/50)
```java
@Override
public void run() {
    while (true) {
        if (rnd.nextBoolean()) {
            esdeveniment.ferReserva(this);
        } else {
            esdeveniment.cancelaReserva(this);
        }
        try {
            sleep(rnd.nextInt(1001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### Versió 70/30 (ferReserva/cancelaReserva)
```java
@Override
public void run() {
    while (true) {
        if (rnd.nextInt(100) < 70) {
            esdeveniment.ferReserva(this);
        } else {
            esdeveniment.cancelaReserva(this);
        }
        try {
            sleep(rnd.nextInt(1001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
##### Sortida
```bash
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-9 ha fet una reserva. Places disponibles: 3
Assistent-8 ha fet una reserva. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-6 ha fet una reserva. Places disponibles: 0
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-9 ha cancel·lat una reserva. Places disponibles: 1
Assistent-4 ha fet una reserva. Places disponibles: 0
Assistent-8 ha cancel·lat una reserva. Places disponibles: 1
Assistent-3 ha fet una reserva. Places disponibles: 0
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-4 ha cancel·lat una reserva. Places disponibles: 1
Assistent-2 ha fet una reserva. Places disponibles: 0
```
Com que ara té més probabilitats de caure en ferReserva(), el bloqueig es dóna abans.


#### Versió 30/70 (ferReserva/cancelaReserva)
```java
@Override
public void run() {
    while (true) {
        if (rnd.nextInt(100) >= 70) {
            esdeveniment.ferReserva(this);
        } else {
            esdeveniment.cancelaReserva(this);
        }
        try {
            sleep(rnd.nextInt(1001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

##### Sortida
```bash
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
Assistent-9 ha fet una reserva. Places disponibles: 4
Assistent-8 ha fet una reserva. Places disponibles: 3
Assistent-7 ha fet una reserva. Places disponibles: 2
Assistent-6 ha fet una reserva. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-4 ha fet una reserva. Places disponibles: 0
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
Assistent-4 ha cancel·lat una reserva. Places disponibles: 2
Assistent-8 ha cancel·lat una reserva. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-0 ha fet una reserva. Places disponibles: 2
Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
Assistent-3 ha fet una reserva. Places disponibles: 2
Assistent-1 ha fet una reserva. Places disponibles: 1
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-3 ha cancel·lat una reserva. Places disponibles: 2
Assistent-9 ha cancel·lat una reserva. Places disponibles: 3
Assistent-8 ha fet una reserva. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-4 ha fet una reserva. Places disponibles: 0
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 ha cancel·lat una reserva. Places disponibles: 1
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-5 ha fet una reserva. Places disponibles: 0
Assistent-1 ha cancel·lat una reserva. Places disponibles: 1
Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
Assistent-8 ha cancel·lat una reserva. Places disponibles: 3
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-0 ha fet una reserva. Places disponibles: 2
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-4 ha cancel·lat una reserva. Places disponibles: 3
Assistent-5 ha cancel·lat una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 ha fet una reserva. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 ha fet una reserva. Places disponibles: 2
Assistent-7 ha cancel·lat una reserva. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 ha cancel·lat una reserva. Places disponibles: 4
Assistent-4 ha fet una reserva. Places disponibles: 3
Assistent-1 ha fet una reserva. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-4 ha cancel·lat una reserva. Places disponibles: 4
Assistent-9 ha fet una reserva. Places disponibles: 3
Assistent-1 ha cancel·lat una reserva. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 ha cancel·lat una reserva. Places disponibles: 5
Assistent-6 ha fet una reserva. Places disponibles: 4
Assistent-3 ha fet una reserva. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 ha fet una reserva. Places disponibles: 2
Assistent-2 ha fet una reserva. Places disponibles: 1
Assistent-5 ha cancel·lat una reserva. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 ha fet una reserva. Places disponibles: 1
Assistent-6 ha cancel·lat una reserva. Places disponibles: 2
Assistent-2 ha cancel·lat una reserva. Places disponibles: 3
Assistent-4 ha fet una reserva. Places disponibles: 2
Assistent-6 ha fet una reserva. Places disponibles: 1
Assistent-4 ha cancel·lat una reserva. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 ha cancel·lat una reserva. Places disponibles: 3
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 ha cancel·lat una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 ha fet una reserva. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 ha fet una reserva. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 ha fet una reserva. Places disponibles: 1
Assistent-1 ha fet una reserva. Places disponibles: 0
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-9 ha cancel·lat una reserva. Places disponibles: 1
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-5 ha fet una reserva. Places disponibles: 0
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-2 ha cancel·lat una reserva. Places disponibles: 1
Assistent-7 ha fet una reserva. Places disponibles: 0
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-1 ha cancel·lat una reserva. Places disponibles: 1
Assistent-9 ha fet una reserva. Places disponibles: 0
Assistent-3 ha cancel·lat una reserva. Places disponibles: 1
Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
Assistent-5 ha cancel·lat una reserva. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 ha cancel·lat una reserva. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 ha fet una reserva. Places disponibles: 3
Assistent-4 ha fet una reserva. Places disponibles: 2
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-8 ha fet una reserva. Places disponibles: 0
Assistent-4 ha cancel·lat una reserva. Places disponibles: 1
Assistent-6 ha fet una reserva. Places disponibles: 0
Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
Assistent-0 ha cancel·lat una reserva. Places disponibles: 2
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 ha fet una reserva. Places disponibles: 1
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-9 ha cancel·lat una reserva. Places disponibles: 2
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 ha fet una reserva. Places disponibles: 1
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-1 ha fet una reserva. Places disponibles: 0
Assistent-6 ha cancel·lat una reserva. Places disponibles: 1
Assistent-8 ha cancel·lat una reserva. Places disponibles: 2
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 ha cancel·lat una reserva. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 ha fet una reserva. Places disponibles: 3
Assistent-4 ha cancel·lat una reserva. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 ha fet una reserva. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 ha fet una reserva. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-1 ha cancel·lat una reserva. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 ha cancel·lat una reserva. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 ha fet una reserva. Places disponibles: 3
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 ha cancel·lat una reserva. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 ha fet una reserva. Places disponibles: 3
Assistent-0 ha cancel·lat una reserva. Places disponibles: 4
Assistent-2 ha fet una reserva. Places disponibles: 3
Assistent-3 ha fet una reserva. Places disponibles: 2
Assistent-3 ha cancel·lat una reserva. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 ha fet una reserva. Places disponibles: 2
Assistent-0 ha fet una reserva. Places disponibles: 1
Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 ha fet una reserva. Places disponibles: 1
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-2 ha cancel·lat una reserva. Places disponibles: 2
Assistent-8 ha fet una reserva. Places disponibles: 1
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-8 ha cancel·lat una reserva. Places disponibles: 2
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-1 ha cancel·lat una reserva. Places disponibles: 3
Assistent-0 ha cancel·lat una reserva. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 ha cancel·lat una reserva. Places disponibles: 5
Assistent-2 ha fet una reserva. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 ha fet una reserva. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 ha cancel·lat una reserva. Places disponibles: 4
Assistent-2 ha cancel·lat una reserva. Places disponibles: 5
Assistent-6 ha fet una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 ha fet una reserva. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-4 ha fet una reserva. Places disponibles: 2
Assistent-9 ha fet una reserva. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-6 ha cancel·lat una reserva. Places disponibles: 2
Assistent-1 ha fet una reserva. Places disponibles: 1
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-1 ha cancel·lat una reserva. Places disponibles: 2
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-9 ha cancel·lat una reserva. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 ha cancel·lat una reserva. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 ha cancel·lat una reserva. Places disponibles: 5
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 ha fet una reserva. Places disponibles: 3
Assistent-8 ha fet una reserva. Places disponibles: 2
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 ha fet una reserva. Places disponibles: 1
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-6 ha fet una reserva. Places disponibles: 0
Assistent-9 ha cancel·lat una reserva. Places disponibles: 1
Assistent-6 ha cancel·lat una reserva. Places disponibles: 2
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-8 ha cancel·lat una reserva. Places disponibles: 2
Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 ha fet una reserva. Places disponibles: 2
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 ha cancel·lat una reserva. Places disponibles: 3
Assistent-1 ha cancel·lat una reserva. Places disponibles: 4
Assistent-7 ha cancel·lat una reserva. Places disponibles: 5
Assistent-8 ha fet una reserva. Places disponibles: 4
Assistent-8 ha cancel·lat una reserva. Places disponibles: 5
Assistent-9 ha fet una reserva. Places disponibles: 4
Assistent-5 ha fet una reserva. Places disponibles: 3
Assistent-5 ha cancel·lat una reserva. Places disponibles: 4
Assistent-1 ha fet una reserva. Places disponibles: 3
Assistent-0 ha fet una reserva. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-2 ha fet una reserva. Places disponibles: 1
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-1 ha cancel·lat una reserva. Places disponibles: 2
Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 ha cancel·lat una reserva. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 ha cancel·lat una reserva. Places disponibles: 5
Assistent-3 ha fet una reserva. Places disponibles: 4
Assistent-3 ha cancel·lat una reserva. Places disponibles: 5
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 ha fet una reserva. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 ha fet una reserva. Places disponibles: 2
Assistent-2 ha fet una reserva. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 ha cancel·lat una reserva. Places disponibles: 2
Assistent-8 ha cancel·lat una reserva. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 ha cancel·lat una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 ha fet una reserva. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-4 ha fet una reserva. Places disponibles: 2
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-7 ha fet una reserva. Places disponibles: 1
Assistent-8 ha fet una reserva. Places disponibles: 0
Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-9 ha fet una reserva. Places disponibles: 0
Assistent-4 ha cancel·lat una reserva. Places disponibles: 1
Assistent-3 ha fet una reserva. Places disponibles: 0
Assistent-8 ha cancel·lat una reserva. Places disponibles: 1
Assistent-5 ha fet una reserva. Places disponibles: 0
Assistent-6 ha cancel·lat una reserva. Places disponibles: 1
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
Assistent-7 ha fet una reserva. Places disponibles: 0
Assistent-9 ha cancel·lat una reserva. Places disponibles: 1
Assistent-1 ha fet una reserva. Places disponibles: 0
Assistent-5 ha cancel·lat una reserva. Places disponibles: 1
Assistent-6 ha fet una reserva. Places disponibles: 0
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 ha cancel·lat una reserva. Places disponibles: 1
Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
Assistent-3 ha cancel·lat una reserva. Places disponibles: 3
Assistent-1 ha cancel·lat una reserva. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 ha fet una reserva. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-3 ha fet una reserva. Places disponibles: 2
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-2 ha cancel·lat una reserva. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 ha cancel·lat una reserva. Places disponibles: 4
Assistent-2 ha fet una reserva. Places disponibles: 3
Assistent-5 ha fet una reserva. Places disponibles: 2
Assistent-8 ha fet una reserva. Places disponibles: 1
Assistent-8 ha cancel·lat una reserva. Places disponibles: 2
Assistent-5 ha cancel·lat una reserva. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-2 ha cancel·lat una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 ha fet una reserva. Places disponibles: 3
Assistent-4 ha fet una reserva. Places disponibles: 2
Assistent-3 ha cancel·lat una reserva. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-9 ha fet una reserva. Places disponibles: 2
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-1 ha cancel·lat una reserva. Places disponibles: 3
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
Assistent-7 ha fet una reserva. Places disponibles: 2
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
Assistent-5 ha fet una reserva. Places disponibles: 1
Assistent-2 ha fet una reserva. Places disponibles: 0
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-4 ha cancel·lat una reserva. Places disponibles: 1
Assistent-3 ha fet una reserva. Places disponibles: 0
Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
Assistent-0 ha fet una reserva. Places disponibles: 0
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
```
Com podem veure, succeeix el cas contrari. Ara és poc probable que els 10 fils s'aturin a l'hora, pel que l'execució dura molt més

### Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?
Necessitem que cada assistent sigui representat per un fil, i per tant, per una instància de Assistent. Volem tenir aquests assistents controlats i que cada un sigui únic. Sense una llista no podriem comprovar si un fil ja té una reserva o no, i per tant, no podriem controlar que hi ha una espera i una gestió real per a que no s'ompli l'esdeveniment.