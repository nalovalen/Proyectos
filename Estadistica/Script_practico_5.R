########################
#  Practico 5 
########################

rm(list = ls()) #Removemos todos los objetos


#-------------
#Ejercicio 1
#-------------

tiempo <- c(1.17, 1.61, 1.16, 1.38, 3.53, 1.23, 0.82, 0.96, 2.01, 0.15, 2.11, 0.71, 0.02, 1.59, 0.19, 1.91, 2.16, 0.92, 0.75, 2.59, 3.07, 1.1, 3.76, 0.47, 4.75)

sort(tiempo)
#c) Una opciÃ³n es construir intervalos de longitud lint

lint<- round( (max(tiempo)-min(tiempo))/5,2)
lint


#Construimos una tabla "a mano" con los siguientes intervalos 

a1<- min(tiempo)

a2<-a1+lint

a3<- a2+lint

a4<- a3+lint

a5 <- a4+lint

a6<- a5+lint 

a1; a2; a3; a4; a5; a6

#Para la tabla de frecuencias 

f1<- sum(  a1<=   sort(tiempo) & sort(tiempo)< a2  )
f2 <-  sum(  a2<=   sort(tiempo) & sort(tiempo)< a3  )
f3 <-  sum(  a3<=   sort(tiempo) & sort(tiempo)< a4  )
f4 <-  sum(  a4<=   sort(tiempo) & sort(tiempo)< a5  )
f5 <-  sum(  a5<=   sort(tiempo) & sort(tiempo)<= a6  )

f1; f2; f3; f4; f5


#d) 

( sum( sort(tiempo) < 3.8)/length(tiempo))*100

sum( sort(tiempo) >=1.92)


#e) f3/length(tiempo)


# g) Resumen estadÃ­stico

summary(tiempo)

sd(tiempo)# desvÃ­o

cvar=  ( sd(tiempo)/mean(tiempo))*100 #Coeficiente de variaciÃ³n
cvar

mad(tiempo)#mediana de los desvÃ­os absolutos

quantile(tiempo, .75)


dinter<- ( quantile(tiempo, .75)- quantile(tiempo, .25) )/1.349 # Distancia intercuartÃ­lica con factor de correcciÃ³n

dinter



#h) Diagrama de caja e histograma

boxplot(tiempo, main="Diagrama de caja para 25 tiempos de procesamiento")

hist(tiempo, breaks=4, main="Histograma para 25 tiempos de procesamiento")



#-------------  
#Ejercicio 2
#-------------
#https://rdrr.io/rforge/Lock5Data/man/StudentSurvey.html

Alumnos=read.csv("StudentSurvey.csv",header=TRUE) 
attach(Alumnos)

#fix(Alumnos) #Vemos la base de datos
#Primer acercamiento a los datos

summary(Alumnos)
dim(Alumnos)
#moda
library(modeest)
moda_anio <- mfv(Alumnos$Anio); moda_anio

#otra forma de calcular la moda sin llamar a la biblioteca modeest
mode <- function(x) {
  return(names(which.max(table(x))))
}

moda_genero <- mode(Alumnos$Genero); moda_genero
moda_año <- mode(Alumnos$Anio); moda_año

#a) 
names(Alumnos) #Muestra los nombres de las variables (columnas)

#b)
#Asignamos los nombres de las variables en castellano
colnames(Alumnos)<-c("Anio","Genero","Fuma","Premio","MayorSAT","Ejercicio","TV","Altura","Peso","Hermanos","Orden de Nac.","LenguaSAT","MatSAT","SAT","GPA","Pulso","Piercings")
names(Alumnos)

#c) 

Altura2 <- 2.54* Alumnos$Altura; Altura2  #transformación de pulgadas a centÃ­metros
mean(Altura2, na.rm=TRUE) #na.rm=TRUE remueve los NA o not avaiable 

#Si quisieramos agregar una nueva columna a la base de datos original

#altura2 = data.frame(Altura2); altura2
#names(altura2)<- 'altura_cm'
#altura2
#concatenamos ambos dataframe
#alumnos1= data.frame(Alumnos, altura2); alumnos1


# En general si queremos filtrar los NA en toda la base

Alumnos_NA <- na.omit(Alumnos); Alumnos_NA
dim(Alumnos_NA)
#fix(Alumnos_NA)
 
#De ahora en más accedemos a las variables sin llamar al  data frame 

attach(Alumnos)

#d)
table(Alumnos$Anio) #Aparece una frecuencia de dos que no pertenece a ninguna categorÃ­a
counts.año <- table(Alumnos$Anio); counts.año
pie(counts.año, col=c("blue","green","red", "brown" ), main="Gráfico de Torta para la varible Añoo")
Alumnos$Anio

mean(Anio)

#observar que en AÃ±o hay dos celdas con "", que filtraremos

library(tidyverse)#para utilizar el operador pipe %>% y filtrar
Alumnosfilt1<- Alumnos%>%filter(Anio != "")# La variable Año tiene celdas con """"
Añofilt<- Alumnosfilt1$Anio
table(Añofilt)
counts.Añofilt <- table(Añofilt)
x11()#para que muestre los gráficos en otra ventana
pie(counts.Añofilt, col=c("blue","green","red", "brown" ), main="GrÃ¡fico de Torta para AÃ±o")


#e) Genero y fuma

Fuma2 <- gsub("Yes", "Si", Alumnos$Fuma); Fuma2
counts <- table(Fuma2,Genero); counts
prop<- prop.table(table(Fuma2,Alumnos$Genero), margin = 2) * 100; prop


barplot(counts,col=c("blue","red"),main="Fuma vs. Genero" ) 

#más completo, agregamos porcentajes

bp<- barplot(prop,col=c("blue","red"),main="Distribución de Fuma según Género (%)", beside=TRUE, axes=FALSE, xlab="Genero" , ylab="Frecuencia (%)",  ylim=c(0,100)) 
axis(2, at=seq(0,100,10))
legend("topright", legend=c("No", "Si"), bty="n", fill=c("blue", "red"))
text(bp, 0, round(prop, 1), cex=1, pos=3)




#Observar que la proporción ( 0.1398964) de hombres (M) que fuma es mayor que la proporción (16/(16+153))de mujeres  que fuma(F)


#f)

boxplot(Altura)# Hay un valor atípico que también puede observarse haciendo

quantile(Altura,.75, na.rm=TRUE)+(3/2)*IQR(Altura, na.rm=TRUE)

sort(Altura)

#El dato 83 sobrepasa este lÃ­mite


# g) Altura versus año

boxplot(Altura~Anio) #comparar y discutir; observar que en AÃ±o hay dos celdas con ""

library(tidyverse)#para utilizar el operador pipe %>% y filtrar

Alumnosfilt1<- Alumnos%>%filter(Anio != "")# La variable aÃ±o tiene celdas con """"

boxplot(Alumnosfilt1$Altura~Alumnosfilt1$Anio,  xlab="Año" , ylab="Altura") #comparar y discutir; observar que hay dos celdas con ""

#h) Altura versus Peso


plot(Altura, Peso) 

#-------------  
#Ejercicio 3
#-------------

datos = c(43, 37, 50, 51, 58, 105, 52, 45, 45, 10)

#a)
summary(datos)
sd(datos)

#b)
boxplot(datos)
quantile(datos,.75, na.rm=TRUE)+(3/2)*IQR(datos, na.rm=TRUE)
quantile(datos,.25, na.rm=TRUE)-(3/2)*IQR(datos, na.rm=TRUE)
sort(datos)


#c)
#quitemos el valor 10 y 105, ya que son valores atípicos

datosfilt<- datos[!datos %in% c(10,105)]; datosfilt

summary(datosfilt)

sd(datosfilt)


#-------------  
#Ejercicio 4
#-------------

usuarios = c(17.2, 22.1, 18.5, 17.2, 18.6, 14.8, 21.7, 15.8, 16.3, 22.8,24.1, 13.3, 16.2, 17.5, 19, 23.9, 14.8, 22.2, 21.7, 20.7, 13.5, 15.8, 13.1, 16.1, 21.9, 23.9, 19.3, 12.0, 19.9, 19.4, 15.4, 16.7, 19.5, 16.2, 16.9, 17.1, 20.2, 13.4, 19.8, 17.7, 19.7, 18.7, 17.6, 15.9, 15.2, 17.1, 15.0, 18.8, 21.6, 11.9)

length(usuarios)

#b)

media = mean(usuarios); media
desvio = sd(usuarios); desvio
varianza = var(usuarios); varianza
#varianza = desvio^2; varianza

#c)
summary(usuarios)
boxplot(usuarios, ylab= 'n°de usuarios (en miles)', main = 'Distribución de número de usuarios \n de una red')
#no se observan valores atípicos
#d)

Q1 = summary(usuarios)[2]; Q1
Q3 = summary(usuarios)[5]; Q3
RangoIC = Q3 - Q1; RangoIC
#RIQ = IQR(usuarios); RIQ

#El vector atipicos guarda aquellos valores outliers
atipicos = vector()
for (i in 1:length(usuarios)){
  if (usuarios[i] < (Q1-(3/2)*RangoIC) | usuarios[i] > (Q3+(3/2)*RangoIC)){
    atipicos[i] = usuarios[i]
  }
}

atipicos

#podemos convertir la acción anterior en una función

outliers <- function(x){
  # x es un vector unidimensional de tamaño n=lenght(x)
  Q1 = quantile(x,.25, na.rm=TRUE)
  Q3 = quantile(x,.75, na.rm=TRUE)
  RangoIC = Q3 - Q1
  atipicos = vector()
  for (i in 1:length(x)){
    if (x[i] < (Q1-(3/2)*RangoIC) | x[i] > (Q3+(3/2)*RangoIC)){
      atipicos[i] = x[i]
    }
  }
  atipicos = atipicos[!is.na(atipicos)]
  return(atipicos)
}

#aplicamos la función outliers donde el argumento x ahora es usuarios
outliers(usuarios)

#introduzcamos un valor atípico a los datos
usuarios_atipico = c(usuarios, 50); usuarios_atipico

outliers(usuarios_atipico)

#e)
hist(usuarios)

#-------------  
#Ejercicio 5
#-------------

presupuesto = c(5, 41, 74, 200, 225, 113, 70, 40, 72, 160, 20, 68, 30, 120, 70, 15, 7, 60, 19, 68, 40, 132, 52, 65, 60, 100, 116, 70, 5, 75, 50, 80, 65, 35, 125)

length(presupuesto)




#a) 
summary(presupuesto)

#b)
x11()
par(mfrow=c(2,1))
boxplot(presupuesto)
points(mean(presupuesto), pch=25, bg="red") #agrega la media al boxplot, notar que es mayor que la mediana (linea de la caja)
hist(presupuesto)
dev.off()

#¿cuál es el valor atípico que se observa en el boxplot?

outliers(presupuesto) #correr la función outliers del ejercicio 4

#Otra forma de ver la distribución de las observaciones dentro del boxplot
#x11()
#par(mfrow=c(2,1))
#boxplot(presupuesto)
#stripchart(presupuesto,
#          method = "jitter",
#           pch = 19,
#           col = 2:4,
#           vertical = TRUE,
#           add = TRUE)
#hist(presupuesto)
#dev.off()
#-------------  
#Ejercicio 6----
#-------------
peso <- c( 1443, 1568, 1465, 1811, 1109, 1136, 1040)
combustible <- c( 43, 46, 44, 39, 59, 55, 59)

data = data.frame(peso, combustible); data 

##a)----
#Estadísticos de posición
summary(data)
#Estadísticos de dispersión
sd_peso = round(sd(peso),2); sd_peso
sd_combustible = round(sd(combustible),2); sd_combustible
var_peso = var(peso); var_peso
var_combustible = var(combustible); var_combustible

##b)----
#Coeficiente de variación

cv_peso = 100*sd_peso/mean(peso); cv_peso
cv_combustible = 100*sd_combustible/mean(combustible); cv_combustible

##c)----

plot(peso, combustible, main='Diagrama de dispersión \n combustible vs. peso')

##d)----

#-------------  
#Ejercicio 7----
#-------------

X = c(30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 15, 15, 15, 10, 10, 10, 6, 6, 5, 5, 5, 4, 4, 4, 4, 4, 1, 1, 1)
Y = c(0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 4, 1, 2, 0, 2, 1, 0, 1, 0, 6, 3, 1)

Data = data.frame(X,Y); Data

plot(X,Y, col="red", main = 'Frecuencia de ejecución del software vs.\n n°de gusanos en el sistema ')

cor(Data) 

