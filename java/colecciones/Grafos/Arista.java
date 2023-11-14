package colecciones.Grafos;

public class Arista {
  /**
   * Obtiene el primer vertice de una arista.
   * @return el primer vertice de una arista.
   */
  public Vertice getPrimero() {
    return primero;
  }

  /**
   * Obtiene el segundo vertice de una arista.
   * @return el segundo vertice de una arista.
   */
  public Vertice getSegundo() {
    return segundo;
  }

  /**
   * Primer vertice de una arista.
   */
  private final Vertice primero;
  /**
   * Segundo vertice de una arista
   */
  private final Vertice segundo;

  /**
   * Constructor de una arista.
   * @param primero Primer vertice de la arista.
   * @param segundo Segundo vertice de la arista.
   */
  public Arista(Vertice primero, Vertice segundo) {
    this.primero = primero;
    this.segundo = segundo;
  }

  /**
   * El hashCode para cada Vertice esta definido a partir,
   * de la suma de los hashCode de sus atributos primero y segundo.
   * @return valor de hash para esta arista.
   */
  @Override
  public int hashCode() {
    int hash = primero.hashCode() + segundo.hashCode();
    return hash;
  }

  /**
   * Compara los atributos primero y segundo entre si.
   * @param obj a comparar con esta arista.
   * @return true si el objeto especificado es igual a esta arista.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
			return false;
    }
		if (obj == this) {
			return true;
    }
		if (!(obj instanceof Arista)) {
			return false;
    }
		Arista otraArista = (Arista) obj;
    boolean p = this.getPrimero().equals(otraArista.getPrimero()) && this.getSegundo().equals(otraArista.getSegundo());
    boolean q = this.getPrimero().equals(otraArista.getSegundo()) && this.getSegundo().equals(otraArista.getPrimero());
    return p || q;
  }

  /**
   * @return Representacion de la arista en forma de String.
   */
  @Override
  public String toString() {
    return "[" + primero.getId() + "] <-> [" + segundo.getId() + "]";
  }

}
