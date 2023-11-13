#Ejercicio 1
#Esperanza= 37.5; desv=21.6
#Resolviendo Espe=alpha/lambda; desv=raiz de (alpha/lambda^2)





#a)---- 
alpha=(37.5/21.6)^2; alpha
lambda=37.5/(21.6)^2; lambda

x=seq(0, 100, 0.01)
x11()
plot(x, dgamma(x, shape=alpha, scale=1/lambda),  type='l', ylab='densidad', main= 'Densidad de una variable X~Gamma(3.01, 0.08)')
#b)----
qgamma(.75, shape=alpha, scale=1/lambda)





#c)----
#Computo de R=800 cuantiles basadas en muestras con n=50 
R=800                           #Numero de replicas
n= 25                       # Numero de observaciones de la muestra
cuantil <- rep(0,R)         # Vector de ceros de longitud R
set.seed(34)                     # semilla
for(i in 1:R){
cuantil [i] <- quantile( rgamma(n, shape=alpha, scale=1/lambda) , .75)# Computo de la mediana
}

mean(cuantil)
hist(cuantil)

#e)----
sd(cuantil)

