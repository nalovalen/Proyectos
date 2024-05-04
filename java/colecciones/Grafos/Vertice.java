package colecciones.Grafos;

import java.util.ArrayList;


public class Vertice {

	/**
	 * vertex identifier
	 */
	private final Integer id;

	/**
	 * list of adjacent vertices
	 */
	private ArrayList<Vertice> adyacentes;

	/**
	 *
	 * @param id identifier for the new vertex.
	 */
	public Vertice(Integer id) {
		this.id = id;
		adyacentes = new ArrayList<>();
	}

	/**
	 *
	 * @return vertex identifier.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *
	 * @return list of vertices adjacent to this.
	 */
	public ArrayList<Vertice> getAdyacentes() {
		return adyacentes;
	}

	/**
	 *
	 * @param a new vertex adjacent to this.
	 * @return list of vertices adjacent to this.
	 */
	public boolean agregarAdyacente(Vertice a) {
		return adyacentes.add(a);
	}

	/**
	 *
	 * @param a adjacent vertex to delete.
	 * @return 	list of vertices adjacent to this.
	 */
	public boolean eliminarAdyacente(Vertice a) {
		return adyacentes.remove(a);
	}

	/**
	 * show vertices by console
	 */
	@Override
	public String toString() {
		String res = "[" + id.toString() + "] -> ";
		for (int i = 0; i < adyacentes.size(); i++) {
			res += adyacentes.get(i).getId() + " -> ";
		}
		return res + "null";
	}

	/**
	 * @param other object which I am going to see if it is equal to this.
	 * @return if the other element contains the same or is this.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (this == other) {
			return true;
		}

		if (!(other instanceof Vertice)) {
			return false;
		}

		Vertice aux = (Vertice) other;

		if (!this.getId().equals(aux.getId())) {
			return false;
		}

		if (adyacentes.size() != aux.getAdyacentes().size()) {
			return false;
		}

		for (int i = 0; i < adyacentes.size(); i++) {
			if (!adyacentes.get(i).getId().equals(aux.getAdyacentes().get(i).getId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return new identifier for vertex this.
	 */
	@Override
	public int hashCode() {
		return id * 5;
	}
}
