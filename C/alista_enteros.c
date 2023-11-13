#include <stdio.h>

#define Nmax 250 

typedef struct{
    int a [Nmax];
    int cant;
}Tdata;

int es_vacia (Tdata arre);
void crear (Tdata a);
Tdata agregar (Tdata* arre, int elemento);
int ins (Tdata* arre, int elem, int pos);
int agregarFin (Tdata* arre, int elem);
int eliminarComienzo(Tdata* arre);
int eliminar(Tdata* arre, int pos);
int obtener (Tdata arre, int pos);
Tdata reversa(Tdata arre);


//concatena dos listas, si falla devuelve solo la primer lista
Tdata concat(Tdata arre1, Tdata arre2){
    if(arre1.cant + arre2.cant>Nmax){
        return arre1;
    }else{
        for(int i=0;i<arre2.cant;i++){
            int elem=arre2.a[i];
            agregarFin(&arre1,elem); 
        }
    }
}

//devuelve la lista al revez
Tdata reversa(Tdata arre){
    Tdata reverse;
    crear (reverse);
    for(int i=arre.cant-1;i>=0;i--){
        agregar(&reverse,arre.a[i]);
    }
}

//obtiene un elemento en una posicion dada
int obtener (Tdata arre, int pos){
   return arre.a[pos-1];
}

//elimina un elemento de una posicion dada
// 0 si no se pudo borrar el elemento
// 1 si se pudo borrar el elemento
int eliminar(Tdata* arre, int pos){
    if (pos> arre->cant || es_vacia(*arre) || pos<=0){
        return 0;
    }else{
        for (int i=pos;i< arre->cant;i++){
            arre->a[i-1]= arre->a[i];
        }
        arre->cant--;
        return 1;
    }
}

//elimina un elemento del comienzo de la lista
// 0 si no pude realizar la suprecion
// 1 si logro realizar la suprecion
int eliminarComienzo(Tdata* arre){
    if(es_vacia(*arre)){
        return 0;
    }else{
        for(int i=1;i<arre->cant;i++){
            arre->a[i-1]=arre->a[i];
        }
        arre->cant--;
        return 1;
    }
}

//agrega un elemento al final
// 0 si el elemento no se pudo agregar
// 1 si el elemento se agrego
int agregarFin (Tdata* arre, int elem){
    if(arre->cant < Nmax){
        int i=arre->cant;
        arre->a[i]=elem;
        arre->cant=arre->cant++;
        return 1;
    }else{
        return 0;
    }
}

// inserta un elemento en una posicion dada
// 0 si no pudo insertar la posicion
// 1 si el elemento se inserto correctamente
int ins (Tdata* arre, int elem, int pos){
    if (arre->cant < pos || pos < 0 || arre->cant >= Nmax-1){
        return 0;
    }else{
        for (int i=pos;i<=arre->cant;i++){
            int aux = arre->a[i];
            arre->a[i]=elem;
        }
        arre->cant=arre->cant++;
        return 1;
    }
}

//inserta un elemento en la primer posicion
Tdata agregar (Tdata* arre, int elemento){
    int i=0;
    int aux;
    while (i < arre->cant){
        aux = arre->a[i];
        arre->a[i] = elemento;
    }
    arre->cant= arre->cant+1;
}


//crea un arreglo vacio
void crear(Tdata a){
    a.cant=0;
}


//revisa si el arreglo esta vacio o no
int es_vacia (Tdata arre){
    if(arre.cant==0){
        return 1;
    }else{
        return 0;
    }
};
