#include<stdio.h>
#include<string.h>

int EsVocal(char a);
int EsConsonante(char a);
char car;
char msg [40];

int main(){
    printf("Ingrese una letra: \n");
    scanf("%s",&car);

    if(EsVocal(car)==1){
	    strcpy (msg,"es vocal");
    }else{
		if(EsConsonante(car)==1){
		    strcpy(msg,"es consonante");
		}else{
			strcpy(msg,"no es ni consonante ni vocal");
		}
	    }
printf("%s", msg);
return 0;
}

int EsVocal (char a){  
	if((a =='a')||  (a =='e')||  (a =='i')||  (a =='o')||  (a =='u')||  (a =='A')||  (a =='E')||  (a =='I')|| (a =='U')||  (a =='U')){
        return 1;
    }else{
	    return 0;
	}
}

 int EsConsonante (char a){
     if ((a>='A') && (a<='Z') ||  (a>='a') && (a<='z')&& !EsVocal(a)){
        return 1;
	}else{
		return 0;
	}
 }