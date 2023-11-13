package colecciones.cola;

import java.util.Collection;

/**
* Implementación basada en arreglos de tamaño fijo de la interfaz {@code Cola}.
* @see colecciones.cola.Cola
*/
public class ColaArregloFijo<T> implements Cola<T> {

	/**
	* Capacidad máxima por defecto ({@value #CAPACIDAD_POR_DEFECTO})
	*/
	public static final int CAPACIDAD_POR_DEFECTO = 20;
	private Object[] arreglo;
	private int elementos = 0;

	/**
	* Construye una nueva cola vacía con una capacidad máxima de {@value #CAPACIDAD_POR_DEFECTO}.
	*/
	public ColaArregloFijo() {
		this(CAPACIDAD_POR_DEFECTO);
	}

	/**
	* Construye una nueva cola vacía con una capacidad determinada mayor a 0.
	* @param capacidad la capacidad de la cola
	* @throws IllegalArgumentException si {@code capacidad} es menor o igual a 0 
	*/
	public ColaArregloFijo(int capacidad) {
		if (capacidad <= 0)
			throw new IllegalArgumentException("la capacidad debe ser un numero positivo (" + capacidad + ")");
		arreglo = new Object[capacidad];
	}

	/**
	* Construye una cola a partir de elementos en una colección.
	* Los elementos en la colección se encolan de izquierda a derecha.
	* La capacidad de la cola va a ser suficiente para por lo menos contener todos los elementos de la colección.
	* @param elems los elementos a agregar a la cola
	*/
	public ColaArregloFijo(Collection<T> elems) {
		if (elems == null)
			throw new IllegalArgumentException("elems es null");
		arreglo = new Object[Math.max(elems.size(), CAPACIDAD_POR_DEFECTO)];
		for (T e : elems) {
			encolar(e);	
		}
	}
	@Override
	public boolean esVacia() {
		if (this.elementos==0) 
			return true;
		return false;
	}

	@Override
	public int elementos() {
		return this.elementos;
	}

	@Override
	public boolean encolar(T elem) {
		if (this.elementos==CAPACIDAD_POR_DEFECTO) {
			return false;
		}else{
			for (int i = this.elementos; i >0; i--) {
				this.arreglo[i]=this.arreglo[i-1];
			}
			this.arreglo[0]= elem;
			this.elementos++;
			return true;
		}
	}

	@Override
	public T desencolar() {
		if(esVacia())
			throw new IllegalStateException("ColaVacia");
		T aux = (T) this.arreglo[this.elementos-1];
		this.elementos--;
		return aux;
	}

	@Override
	public T primero() {
		if(esVacia())
			throw new IllegalStateException("Cola Vacia");
		return (T) this.arreglo[0];
	}

	@Override
	public void vaciar() {
		this.elementos=0;
	}

	@Override
	public boolean repOK() {
		if(this.elementos > 0 || this.elementos<CAPACIDAD_POR_DEFECTO){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String toString() {
		if(esVacia())
			return "";
		StringBuilder aux = new StringBuilder();
		aux.append("Cola :");
		for (int i = 0; i < arreglo.length; i++) {
			aux.append(this.arreglo[i] + ", ");
		}
		return  aux.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other==null) 
			return false;
		if(other==this)
			return true;
		if(!(other instanceof ColaArregloFijo))
			return false;
		ColaArregloFijo<T> aux = (ColaArregloFijo<T>) other;
		if(this.elementos!=aux.elementos)
		return false;
		for (int i = 0; i < this.elementos; i++) {
		if(!(this.arreglo[i].equals(aux.arreglo[i]))){
			return false;
		}
	}
	return true;
	}

	/**
	* Permite obtener un elemento del arreglo en un indice determinado realizando el casteo necesario.
	* @param index el indice del elemento a obtener
	*/
	@SuppressWarnings("unchecked")
   	private T elemento(int index) {
        	return (T) arreglo[index];
    	}

}
