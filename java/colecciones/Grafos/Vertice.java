package colecciones.Grafos;
import java.util.ArrayList;

public class Vertice {

	private final Integer id;
	private ArrayList<Vertice> adyacentes;

	public Vertice (Integer id) {
		this.id = id;
		adyacentes = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public ArrayList<Vertice> getAdyacentes() {
		return adyacentes;
	}

	public boolean agregarAdyacente(Vertice a) {
		return adyacentes.add(a);
	}

	public boolean eliminarAdyacente(Vertice a) {
		return adyacentes.remove(a);
	}

	@Override
	public String toString() {
		String res = "[" + id.toString() + "] -> ";
		for (int i = 0; i < adyacentes.size(); i++) {
			res += adyacentes.get(i).getId() + " -> ";
		}
		return res + "null";
	}

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

	public static void main (String[] args) {
		Vertice x = new Vertice(4);
		x.agregarAdyacente(new Vertice(6));
		x.agregarAdyacente(new Vertice(8));

		System.out.println(x.toString());

		//x.eliminarAdyacente(new Vertice(8));

		//System.out.println(x.toString());

		System.out.println(x.equals(x));

		Vertice y = new Vertice(1);
		y.agregarAdyacente(new Vertice(3));
		y.agregarAdyacente(new Vertice(7));

		System.out.println(y.toString());

		System.out.println(x.equals(y));
	}
}