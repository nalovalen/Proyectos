package colecciones.Grafos;

public class AristaCosto {
	/**
	 * It is the first component of the list
	 */
	private Vertice x;
	/**
	 * It is the second component of the list
	 */
	private Vertice y;
	/**
	 * is the cost of the edge
	 */
	private Number costo;

	/**
	 * @param x first component of the edge
	 * @param y second component of the edge
	 */
	public AristaCosto(Vertice x, Vertice y) {
		this.x = x;
		this.y = y;
		this.costo = 0;
	}

	/**
	 * @param x first component of the edge
	 * @param y second component of the edge
	 * @param costo weight of the new edge
	 */
	public AristaCosto(Vertice x, Vertice y, Number costo) {
		this.x = x;
		this.y = y;
		this.costo = costo;
	}

	/**
	 * @return the first vertex of the edge
	 */
	public Vertice getFirst() {
		return x;
	}

	/**
	 * @return the second vertex of the edge
	 */
	public Vertice getSecond() {
		return y;
	}

	/**
	 * @return the weight of the edge
	 */
	public Number getCosto() {
		return costo;
	}

	/**
	 * @return the string representation of an edge
	 */
	@Override
	public String toString() {
		return "(" + x.getId().toString() + "," + y.getId().toString() + ")" + " costo: " + costo;
	}

	/**
	 * @return true if the "other" object is equal to the edge and false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (this == other) {
			return true;
		}

		if (!(other instanceof AristaCosto)) {
			return false;
		}

		AristaCosto aux = (AristaCosto) other;
		boolean res = this.getFirst().equals(aux.getFirst());
		boolean res1 = this.getSecond().equals(aux.getSecond());
		boolean res2 = costo == aux.getCosto();
		return res && res1 && res2;
	}

	/**
	 * @return the new hascode for the edge
	 */
	@Override
	public int hashCode() {
		return x.getId() * y.getId();
	}
}
