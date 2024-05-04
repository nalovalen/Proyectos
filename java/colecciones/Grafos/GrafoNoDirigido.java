package colecciones.Grafos;

import java.util.ArrayList;

public class GrafoNoDirigido implements Grafo {
	/**
	 * list of graph vertices
	 */
	private static ArrayList<Vertice> vertices;
	/**
	 * list of graph edges
	 */
	private static ArrayList<AristaCosto> aristas;

	public GrafoNoDirigido() {
		vertices = new ArrayList<Vertice>();
		aristas = new ArrayList<AristaCosto>();
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
		return aristas.contains(new AristaCosto(a, b));
	}

	/**
	 * @param a is the vertex to insert
	 * @return true if the vertex was inserted with I exist and false otherwise
	 */
	public boolean insertarVertice(Vertice a) {
		if (vertices.contains(a)) {
			return true;
		}

		return vertices.add(a);
	}

	/**
	 * @param a is the edge to insert
	 * @return true if the edge was inserted successfully false otherwise
	 */
	public boolean insertarArco(AristaCosto a) {
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
		boolean res = aristas.add(a);
		boolean res1 = aristas.add(new AristaCosto(a.getSecond(), a.getFirst(), a.getCosto()));
		return  res && res1;  //como es no dirgido es simétrico
	}

	/**
	 * @param a is the vertex to delete from the graph
	 * @return true if the vertex was deleted successfully and false otherwise
	 * @throws IllegalArgumentException if the vertex to be deleted does not exist
	 */
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
				AristaCosto w = aristas.get(i);
				aristas.remove(new AristaCosto(w.getSecond(), w.getFirst(), w.getCosto()));
				aristas.remove(w);
			}
		}

		vertices.remove(v);
		return true;
	}

	/**
	 * @param a is the edge to delete from the graph
	 * @return true if the edge was successfully deleted and false otherwise
	 */
	public boolean borrarArco(AristaCosto a) {
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
		AristaCosto a1 = null;
		AristaCosto a2 = null;

		for (int i = 0; i < aristas.size(); i++) {
			if (aristas.get(i).getFirst().getId() == v1.getId() && aristas.get(i).getSecond().getId() == v2.getId()) {
				a1 = (AristaCosto) aristas.get(i);
			}

			if (aristas.get(i).getFirst().getId() == v2.getId() && aristas.get(i).getSecond().getId() == v1.getId()) {
				a2 = (AristaCosto) aristas.get(i);
			}
		}

		aristas.remove(a1);
		aristas.remove(a2);
		return true;
	}

	/**
	 * @param a is the exit vertex
	 * @param b is the vertex of arrival
	 * @return is the weight of going from vertex a to vertex b in the graph
	 */
	public Number getCostoArista(Vertice a, Vertice b) {
		for (int i = 0; i < aristas.size(); i++) {
			AristaCosto w = (AristaCosto) aristas.get(i);

			if (w.getFirst().getId() == a.getId() && w.getSecond().getId() == b.getId()) {
				return w.getCosto();
			}

			if (w.getSecond().getId() == b.getId() && w.getFirst().getId() == a.getId()) {
				return w.getCosto();
			}
		}
		return 0;
	}

	/**
	 * @param a is the vertex to search in the graph
	 * @return true if the vertex is in the graph and false otherwise
	 */
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

	//revisar bien
	public static ArrayList<ArrayList<Integer>> graphColoring(GrafoNoDirigido graph) {
		//contiene los nodos coloreados:
		ArrayList<Integer> colored = new ArrayList<>();
		//contiene los conjuntos de colores. Cada conjunto tiene los nodos coloreados de su color.
		ArrayList<ArrayList<Integer>> colours = new ArrayList<>();

		for (int k = 0; k < graph.getVertices().size(); k++) {
			//dentro de este for lo que hago es pintar de un mismo color todos los nodos que no son adyacentes entre sí

			//creo un nuevo color:
		    ArrayList<Integer> newColor = new ArrayList<>();
            //para cada nodo de graph:
		    for (int i = 0; i < graph.getVertices().size(); i++) {
			    Vertice v = (Vertice) graph.getVertices().get(i); //nodo
			    Integer id = v.getId();                 //id del nodo
			    //si v no está coloreado
			    if (!colored.contains(id)) {
			    	Boolean sameColor = false; //dice si algún adyacente a v ya esta pintado con newColor
				    //si v no es adyacente a ningun nodo en newColor:
				    if (newColor.size() > 0) {
				    	for (int j = 0; j < v.getAdyacentes().size(); j++) {
				    	    Integer adjId = v.getAdyacentes().get(j).getId();
					        if (newColor.contains(adjId)) {
						        sameColor = true;
						        break;
					        }
				        }
				    }

				    if (!sameColor) {
				    	colored.add(id);
				        newColor.add(id);
				    }
			    }
		    }
		    colours.add(newColor);
		}
		return colours;
	}

	public static void main(String[] args) {
		GrafoNoDirigido grafo = new GrafoNoDirigido();
        /*
		grafo.insertarVertice(new Vertice(1));
		grafo.insertarVertice(new Vertice(2));
		grafo.insertarVertice(new Vertice(3));
		grafo.insertarVertice(new Vertice(4));

		grafo.insertarArco(new Arista(new Vertice(1), new Vertice(2)));
		grafo.insertarArco(new Arista(new Vertice(1), new Vertice(3)));
		grafo.insertarArco(new Arista(new Vertice(4), new Vertice(3)));
        */
        //Ejemplo de la teoría:
        grafo.insertarVertice(new Vertice(1));
		grafo.insertarVertice(new Vertice(2));
		grafo.insertarVertice(new Vertice(3));
		grafo.insertarVertice(new Vertice(4));
		grafo.insertarVertice(new Vertice(5));
		grafo.insertarVertice(new Vertice(6));
		grafo.insertarVertice(new Vertice(7));
		grafo.insertarVertice(new Vertice(8));

		grafo.insertarArco(new AristaCosto(new Vertice(1), new Vertice(5),1));
		grafo.insertarArco(new AristaCosto(new Vertice(1), new Vertice(2), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(1), new Vertice(3), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(1), new Vertice(4), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(2), new Vertice(4), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(3), new Vertice(2), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(5), new Vertice(7), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(5), new Vertice(6), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(7), new Vertice(8), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(6), new Vertice(7), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(6), new Vertice(8), 1));
		grafo.insertarArco(new AristaCosto(new Vertice(2), new Vertice(8), 1));

		System.out.println(grafo.toString());

		ArrayList<ArrayList<Integer>> colores = graphColoring(grafo);

		for (int i = 0; i < colores.size(); i++) {
			ArrayList<Integer> color = colores.get(i);
			System.out.println("color" + i + ": ");
			for (int j = 0; j < color.size(); j++) {
				System.out.println(color.get(j));
			}
		}
	}
}