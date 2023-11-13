archivo <- "C:/Users/HP/Desktop/datosclima.csv"
clima <- read.csv(archivo)
attach(Datos)
#Ejercicio 1
#b)---
#Computo de R=500 cuantiles basadas en muestras con n=0 
R=500                       #Numero de replicas
n= 30                       # Numero de observaciones de la muestra
vec <- rep(0,R)        			# Vector de ceros de longitud R
vec2 <- rep(0,R)		        # Vector de ceros de longitud R
set.seed(2)                 # semilla

for(i in 1:R){
  vec [i] <- sd(rnorm(n)) #Computo de la media
}

mean(vec)
sd(vec)

#c)---
hist(vec, breaks=20, main="Histograma de estimador") #Es aproximadamente simetrico 
boxplot(vec, breaks=20, main="Diagrama de caja del estimador")
#2 valores atipicos
outliers(vec) #Valores Atipicos

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

#Ejercicio 2
#A)---
#i)---
#RESUMEN DESCRIPTIVO
summary(clima$n)  #sumario del flujo vehicular
summary(clima$tavg)   #sumario de la temperatura media
summary(clima$prcp)   #sumario de las precipitaciones
table(clima$dia)
table(clima$tipo_dia)


#RESUMEN VISUAL
hist(clima$n)
hist(clima$tavg)
hist(clima$prcp)
pie(table(clima$dia))
pie(table(clima$tipo_dia))
#diagramas de caja
boxplot(clima$n, breaks=20, main="Diagrama de caja del flujo vehicular")
boxplot(clima$tavg, breaks=20, main="Diagrama de caja de Temperatura media")
boxplot(clima$prcp, breaks=20, main="Diagrama de caja de las precipitaciones")

#ii)---
#Valor de prcp donde por debajo esta el 25% de obs
precipitacion <- clima$prcp
quantile(precipitacion, .25, na.rm = TRUE)

#flujo vehicular medio
mean(clima$n)

#Temperatura media max en el periodo de tiempo registrado
max(clima$tavg)

#dias sin precipitaciones
f<- function(x) {
  cantprec<-length(clima$prcp)
  cantDiasSinPrec = 0
  for (i in 1:cantprec) {
    if (x[i] == 0) {
      cantDiasSinPrec = cantDiasSinPrec + 1
    }
  }
return(cantDiasSinPrec)
}
f(clima$prcp)

#iii)---

#Distribucion vehicular
hist(clima$n[clima$tipo_dia == "Fin de semana"], breaks = 15, main = "Distribucion del flujo vehicular en fin de semana", xlab = "Flujo vehicular", ylab= "Cantidad de observaciones")
hist(clima$n[clima$tipo_dia == "Lunes a viernes"], breaks = 15, main = "Distribucion del flujo vehicular de Lunes a viernes", xlab = "Flujo vehicular", ylab= "Cantidad de observaciones")

#valores atipicos
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
outliers(clima$n[clima$tipo_dia == "Fin de semana"])
outliers(clima$n[clima$tipo_dia == "Lunes a viernes"])

#iv)---
#Patron
plot(clima$n, clima$prcp, main= "Diagrama de dispersion \n flujo vehicular vs precipitacion")
#No hay existencia alguna de un patron de asociacion lineal entre flujo vehicular y precipitacion

#v)---
#variable nueva segun el flujo
clima$tipo_flujo <- ifelse(clima$n > 120000, "Alto", "Bajo")

#tabla de datos no agrupados
table(clima$tipo_flujo)

#b)---
#i)---

#Datos:
n <- length(clima$prcp)
n
X_barra <- mean(clima$prcp)
X_barra

#intervalo de rechazo: 
qnorm(0.99)

#ii)---
#Cuantiles:
#Z sub α/2 = Z sub 0.005:
qnorm(1 - 0.005)

#-Z sub α/2 = -Z sub 0.005:
qnorm(0.005)

#iii)---

#datos:
#cantidad de datos de la variable: "flujo vehicular Alto" = 49
cantDatosAlto <- length(clima$tipo_flujo[clima$tipo_flujo == "Alto"])
cantDatosAlto

#p-valor: 
p_valor <- 2*(1-pnorm(0.8432740427))
p_valor
