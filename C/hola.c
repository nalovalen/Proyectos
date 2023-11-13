#include <stdio.h>
#define n 10
#define m 10

typedef struct{
    int a [n][m];
    int cantcol;
    int cantfil;
}Tarre;
Tarre matriz;
int i,j;
void cargar(Tarre *h);
void Mostrar(Tarre l);

int main(){
    cargar(&matriz);
    Mostrar(matriz);
    return 0;
}

void cargar(Tarre *h){
    int z,i,j;
    z=0;
    while(z==0){
        printf("Ingrese la cantidad de filas \n");
        scanf("%d",&(h->cantfil));
        printf("Ingrese la cantidad de columnas \n");
        scanf("%d",&(h->cantcol));
        if(h->cantcol>10 || h->cantfil>10){
            printf("Ingrese una cantidad correcta de filas y de columnas \n");
        }else{
            z=1;
        }
    }
    for(i=0;i<(h->cantfil);i++){
        for(j=0;j<h->cantcol;j++){
            printf("ingrese el numero: \n");
            scanf("%d",&(h->a[i][j]));
        }
    }
}

void Mostrar(Tarre l){
    int j;
    for(i=0;i<(l.cantfil);i++){
        for(j=0;j<l.cantcol;j++){
            printf("%d |", l.a[i][j]);
        }
        printf("\n");
    }
}