package colecciones.Grafos;

public class Arista {
	private Vertice x;
	private Vertice y;

	public Arista(Vertice x, Vertice y) {
		this.x = x;
		this.y = y;
	}

	public Vertice getFirst() {
		return x;
	}

	public Vertice getSecond() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x.getId().toString() + "," + y.getId().toString() + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (this == other) {
			return true;
		}

		if (!(other instanceof Arista)) {
			return false;
		}

		Arista aux = (Arista) other;

		return (this.getFirst().equals(aux.getFirst())) && (this.getSecond().equals(aux.getSecond()));
	}

	public static void main(String[] args) {
		Arista a = new Arista(new Vertice(2), new Vertice(4));
		Arista b = new Arista(new Vertice(2), new Vertice(4));

		System.out.println(a.toString());
		System.out.println(b.toString());

		System.out.println(a.equals(b));
		//System.out.println(a.equals(a));
	}
}