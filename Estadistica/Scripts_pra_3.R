#---------------------------------------------
# Estadística (1937) Practico 3  del 2024
# Algunos scripts
#---------------------------------------------

# Limpiando la memoria 
rm(list=ls())


# Bibliotecas requeridas




#------------
# Ejercicio 1 
#------------

#Función de densidad

densidad <- function(x){
  if(x>=1){
    y=(4/(x^5))}
  else{
    y=0}
  return(y)
}

x <- seq(-1/2,3,0.1)# Creamos las coordenadas x e y a graficar.

y = lapply(x, densidad) 

plot(x, y, type='l', main='Función de densidad f') #type='l' produce un suavizado uniendo los puntos de la grafica con lineas.



#------------
# Ejercicio 5 
#------------


#Inciso a) 

#Densidad de la N(3,7)
x <- seq(-3,9,0.1)
y<-dnorm(x,mean=3,sd=sqrt(7)) 
plot(x,y,type = "l", main='Gráfico de la densidad de una N(3,7)', lwd = 2, col = "blue")# lw da el grosor



#Densidad de la gamma(2,1)
x<-seq(0,10,by=0.05) 
y<-dgamma(x,shape=2,scale=1/1) # shape es alpha, scale=1/lambda; o bien rate=\lambda
plot(x,y,type = "l", lwd = 2, col = "violet",  main='Gráfico de la densidad de una gamma(0.1,5)')


#Densidad de la gamma(0.1,5)
x<-seq(0,1,by=0.05) 
y<-dgamma(x,shape=0.1,scale=1/5) # shape es alpha, scale=1/lambda; o bien rate=\lambda
plot(x,y,type = "l", lwd = 1, col = "green",  main='Gráfico de la densidad de una gamma(0.1,5)')

# Para Z con distribución N(0,1), calcular y graficar la P(-0.5< Z< 1)

proba<- pnorm(1)-pnorm(-0.5)

x<-seq(-2,2,by=0.05) 
y<-dnorm(x,0,1) 
plot(x,y,type = "l", lwd = 2, col = "violet")

x2 <- seq(-0.5,1, 0.01)
y2 <-  dnorm(x2) 
x2 = c(-0.5,x2,1)
y2 = c(0,y2,0)
polygon(x2,y2, col="blue", border=NA)


# Para X con distribución N(5,2), calcular y graficar la P(X>=1.2)

proba<- 1-pnorm(1.2, mean=5, sd=sqrt(2))
proba

x<-seq(-1,12,by=0.05) 
y<-dnorm(x,mean=5, sd=sqrt(2))
 
plot(x,y,type = "l", lwd = 2, col = "black")

x2 <- seq(1.2,20, 0.01)
y2 <-  dnorm(x2,mean=5, sd=sqrt(2))
x2 = c(1.2,x2,20)
y2 = c(0,y2,0)
polygon(x2,y2, col="blue", border=NA)




# Algunos enlaces útiles:

# https://stat.ethz.ch/R-manual/R-devel/library/stats/html/Normal.html

#https://stat.ethz.ch/R-manual/R-devel/library/stats/html/GammaDist.html



