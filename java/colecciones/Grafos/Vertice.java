package colecciones.Grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *  Representa un nodo en un grafo.
 */
public class Vertice {
  /**
   * Obtiene el identificador de vertice.
   * @return el identificador del vertice.
   */
  public Integer getId() {
    return id;
  }

  /**
   * identificador del vertice.
   */
  private final Integer id;

  /**
   * Obtiene la lista de vertices adyacentes a este.
   * @return lista de vertices adyacentes a este.
   */
  public List<Vertice> getAdyacentes() {
    return adyacentes;
  }

  /**
   * lista de vertices adyacentes a this.
   */
  private List<Vertice> adyacentes;

  /**
   * Construye un vertice con el identificador pasado como parametro.
   * @param id identificador de vertice.
   */
  public Vertice(Integer id) {
    this.id = id;
    adyacentes = new ArrayList<>();
  }

  /**
   * Conecta este vertice al vertice especificado.
   * @param v Vertice a unir con this.
   * @return {@code true} sii la conexión con el vértice especificado fue agregada.
   */
  public boolean agregarAdyacente(Vertice v) {
    if (adyacentes == null) {
      adyacentes = new ArrayList<Vertice>();
    }
    if (!adyacentes.contains(v)) {
      adyacentes.add(v);
      return true;
    }
    return false;
  }

  /**
   * desconecta este vertice del vertice especificado.
   * @param v Vertice a desconectar.
   * @return {@code true} sii la conexión con el vertice especificado fue eliminada.
   */
  public boolean eliminarAdyacente(Vertice v) {
    if (adyacentes.size() != 0) {
      return adyacentes.remove(v);
    }
    return false;
  }

  /**
   * El hashCode para cada Vertice esta definido a partir,
   * de la suma de los hashCode de los id de sus adyacentes.
   * @return valor de hash para este vertice.
   */
  @Override
  public int hashCode() {
    Integer hash = id.hashCode();

    for (int i = 0; i < adyacentes.size(); i++) {
      hash += adyacentes.get(i).getId().hashCode();
    }

    return hash;
  }

/**
 * Compara los id de los vertices y si comparten los mismos adyacentes.
 * @param otro a comparar con este vertice.
 * @return true si el objeto especificado es igual a este vértice.
 */
@Override
public boolean equals(Object otro) {
    if (this == otro) {
        return true;
    }
    if (otro == null || !(otro instanceof Vertice)) {
        return false;
    }
    Vertice otroVertice = (Vertice) otro;
    return id.equals(otroVertice.id) && equalsList(otroVertice.getAdyacentes());
}

/**
 * Compara si comparten los mismos adyacentes.
 * @param otrasAdyacentes a comparar con la lista de vertices.
 * @return true si las listas contienen los mismos adyacentes.
 */
private boolean equalsList(List<Vertice> otrasAdyacentes) {
    if (adyacentes == null && otrasAdyacentes == null) {
        return true;
    }
    if (adyacentes == null || otrasAdyacentes == null) {
        return false;
    }
    if (adyacentes.size() != otrasAdyacentes.size()) {
        return false;
    }
    for (int i = 0; i < adyacentes.size(); i++) {
        if (!otrasAdyacentes.contains(adyacentes.get(i))) {
          return false;
        }
    }
    return true;
  }


  /**
   * La representacion de Vertices como {@code String}.
   * El primer nodo es el id del Vertice principal.
   * Luego conectado mediante "->" se encuentran los Vertices adyacentes.
   * @return Representacion del vértice en forma de String.
   */
  @Override
  public String toString() {
    String strg = "[" + this.id + "] ->";
    int i = 0;
    while (i < adyacentes.size()) {
      strg += "[" + adyacentes.get(i).getId() + "] -> ";
      i++;
    }
    strg += " Nil";
    return strg;
  }

}
