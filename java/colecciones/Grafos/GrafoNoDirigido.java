package colecciones.Grafos;

import java.util.ArrayList;

public class GrafoNoDirigido implements Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<Arista> aristas;

	public GrafoNoDirigido() {
		vertices = new ArrayList<>();
		aristas = new ArrayList<>();
	}

	private ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public boolean esVacio() {
		return (vertices.size() == 0);
	}

	public int cantVertices() {
		return vertices.size();
	}

	public int cantArcos() {
		return aristas.size();
	}

	public boolean hayArco(Vertice a, Vertice b) {
		return aristas.contains(new Arista(a,b));
	}

	public boolean insertarVertice(Vertice a) {
		if (vertices.contains(a)) {
			return true;
		}

		return vertices.add(a);
	}

	public boolean insertarArco(Arista a) {
		if (aristas.contains(a)) {
			return true;
		}
        
		int first = -1;
		int second = -1;

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == a.getFirst().getId()) {
				first = i;
			}

			if (vertices.get(i).getId() == a.getSecond().getId()) {
				second = i;
			}
		}

		Vertice v1 = vertices.get(first);
		Vertice v2 = vertices.get(second);
        
		vertices.get(first).agregarAdyacente(v2);
		vertices.get(second).agregarAdyacente(v1);
        
		return aristas.add(a) && aristas.add(new Arista(a.getSecond(), a.getFirst())); //como es no dirgido es simétrico
	}

	public boolean borrarVertice(Vertice a) throws IllegalArgumentException {
		if (vertices.size() == 0) {
			throw new IllegalArgumentException("no hay vertices!");
		}

		int index = -1;

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == a.getId()) {
				index = i;
				break;
			}
		}

		Vertice v = vertices.get(index);
        //lo borro de la lista de adyacencias
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getAdyacentes().contains(v)) {
				vertices.get(i).getAdyacentes().remove(v);
			}
		}
        
        //lo borro de las aristas
		for (int i = 0; i < aristas.size(); i++) {
			if ((aristas.get(i).getFirst().getId() == v.getId()) || (aristas.get(i).getSecond().getId() == v.getId())) {
				Arista w = aristas.get(i);
				aristas.remove(new Arista(w.getSecond(), w.getFirst()));
				aristas.remove(w);
			}
		}

		vertices.remove(v);
		return true;
	}

	public boolean borrarArco(Arista a) {
		Vertice v1 = null;
		Vertice v2 = null;
        
        //obtengo los vértices de la arista:
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == a.getFirst().getId()) {
				v1 = vertices.get(i);
			}

			if (vertices.get(i).getId() == a.getSecond().getId()) {
				v2 = vertices.get(i);
			}
		}

		//borro los adyacentes:
		v1.getAdyacentes().remove(v2);
		v2.getAdyacentes().remove(v1);

		//borro la arista:
		Arista a1 = null;
		Arista a2 = null;

		for (int i = 0; i < aristas.size(); i++) {
			if (aristas.get(i).getFirst().getId() == v1.getId() && aristas.get(i).getSecond().getId() == v2.getId()) {
				a1 = aristas.get(i);
			}

			if (aristas.get(i).getFirst().getId() == v2.getId() && aristas.get(i).getSecond().getId() == v1.getId()) {
				a2 = aristas.get(i);
			}
		}

		aristas.remove(a1);
		aristas.remove(a2);
		return true;
	}

	public boolean pertenece(Vertice a) { //hay que arreglarlo un poco (puede fallar por la lista de adyacencias)
		return vertices.contains(a);
	}

	private static void dfs(Vertice a, ArrayList<Integer> marked) {
		System.out.println(a.getId().toString());
		marked.add(a.getId());

		for (int i = 0; i < a.getAdyacentes().size(); i++) {
			if (!marked.contains(a.getAdyacentes().get(i).getId())) {
				dfs(a.getAdyacentes().get(i), marked);
			}
		}
	}

    @SuppressWarnings("unused")
	private static void dfsCaminosSimples(Vertice a, Vertice b, ArrayList<Integer> marked, int[] count) {
		if (!marked.contains(a.getId())) {
			marked.add(a.getId());

		    if (a.getId() == b.getId()) {
			    count[0]++;
		    }

		    if (a.getAdyacentes().size() > 0) {
			    for (int i = 0; i < a.getAdyacentes().size(); i++) {
				    dfsCaminosSimples(a.getAdyacentes().get(i), b, marked, count);
			    }
		    }
		    marked.remove(marked.size() -1);
		}
	}

	@Override
	public String toString() {
		String res = "";

		if (vertices.size() == 0) {
			res = "Vertices: null\n";
		} else {
			res = "Vertices:\n";
		    for (int i = 0; i < vertices.size(); i++) {
			   res += vertices.get(i).toString() + "\n";
		    }
		}

		if (aristas.size() == 0) {
			res += "\nAristas: null";
		} else {
			res += "\nAristas: {";
		    for (int i = 0; i < aristas.size(); i++) {
			    res += aristas.get(i).toString() + ",";
		    }
		    res = res.substring(0, res.length() - 1);
		    res += "}";
		}

		return res;
	}


	@SuppressWarnings("unused")
	private boolean esConexo() throws IllegalArgumentException {
		if (vertices.size() == 0) {
			throw new IllegalArgumentException("no hay vertices!");
		}

		ArrayList<Integer> marked = new ArrayList<>();

		for (int i = 0; i < vertices.size(); i++) {
			dfs(vertices.get(i), marked);

			for (int j = 0; j < vertices.size(); j++) {
				if (!marked.contains(vertices.get(j).getId())) {
					return false;
				}
			}
			marked.clear();
		}

		return true;
	}

	@Override
	public boolean equals() {
		throw new UnsupportedOperationException("implementar!");
	}

	public int cantCaminosSimples (Vertice origen, Vertice destino){
		int[] count = new int[1];
		count[0] = 0;

		dfsCaminos(origen, destino, count, new ArrayList<Integer>());

		return count[0];
	}

	public void dfsCaminos(Vertice actual, Vertice destino, int[] count, ArrayList<Integer> marked){
		marked.add(actual.getId());

		if (actual.getId() == destino.getId()) {
			count[0]++;
		}

		for (int i = 0; i < actual.getAdyacentes().size(); i++) {
			if (!marked.contains(actual.getAdyacentes().get(i).getId())) {
				dfsCaminos(actual.getAdyacentes().get(i),destino,count,marked);
			}
		}

		marked.remove(actual.getId());
	}

    //Solución del teórico: (coloreo todos los que no son adyacentes de un nodo y luego continuo) sin terminar
	public static ArrayList<ArrayList<Vertice>> coloreoGrafo(GrafoNoDirigido graph) {
		//contiene los nodos coloreados:
		ArrayList<Vertice> colored = new ArrayList<>();
		//contiene los conjuntos de colores. Cada conjunto tiene los nodos coloreados de su color.
		ArrayList<ArrayList<Vertice>> colours = new ArrayList<>();

		//creo un nuevo color:
		ArrayList<Vertice> newColor = new ArrayList<>();
        //para cada nodo de graph:
		for (int i = 0; i < graph.getVertices().size(); i++) {
			Vertice v = graph.getVertices().get(i);
			//si v no está coloreado
			if (!colored.contains(v)) {
				//si v no es adyacente a ningun nodo en newColor:
				for (int j = 0; j < newColor.size(); j++) {
					if (v.getAdyacentes().contains(colored.get(j))) {
						break;
					}
				}
				colored.add(v);
				newColor.add(v);
				colours.add(newColor); //****
			}
		}
		return colours;
	}
}