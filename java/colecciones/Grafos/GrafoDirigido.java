package colecciones.Grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class GrafoDirigido implements Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<AristaCosto> aristas;

	public GrafoDirigido() {
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
		return aristas.contains(new AristaCosto(a,b));
	}

	public boolean insertarVertice(Vertice a) {
		if (vertices.contains(a)) {
			return true;
		}

		return vertices.add(a);
	}

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

		Vertice v2 = vertices.get(second);

		if (!vertices.get(first).getAdyacentes().contains(v2)) {
			vertices.get(first).agregarAdyacente(v2);
		}
        
		return aristas.add(a);
	}

	public boolean borrarVertice(Vertice a) {
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
			if ((aristas.get(i).getFirst().getId() == v.getId())) {
				AristaCosto w = aristas.get(i);
				aristas.remove(w);
			}
		}

		for (int i = 0; i < aristas.size(); i++) {
			if (aristas.get(i).getSecond().getId() == v.getId()) {
				AristaCosto w = aristas.get(i);
				aristas.remove(w);
			}
		} //PD: lo cambie pq hay una situación en la que no podía borrar todas las aristas: (2,3),(3,1) -> no borraba (3,1)

		vertices.remove(v);
		return true;
	}

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

		for (int i = 0; i < aristas.size(); i++) {
			if (aristas.get(i).getFirst().getId() == v1.getId() && aristas.get(i).getSecond().getId() == v2.getId()) {
				a1 = aristas.get(i);
			}
		}

		aristas.remove(a1);
		return true;
	}

	public boolean pertenece(Vertice a) {
		return vertices.contains(a);
	}

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

	public boolean equals() {
		throw new UnsupportedOperationException("Implementar!");
	}

	public static void warshall(int[][] graph, int[][] d, int[][] path){
		int n = graph.length; // cantidad de nodos
        // se inicializa dist y path
        for (int i=0;i<n;i++){
           for (int j=0;j<n;j++){
               d[i][j] = graph[i][j];
               path[i][j] = -1;
               if (0 < graph[i][j] &&  graph[i][j] < Integer.MAX_VALUE){
                   path[i][j] = i;
               }
            }
        }
        // se considera cada camino pasando por un posible k
        for(int k = 0 ; k < n; k++){
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){ // si hay un nuevo minimo se cambia el valor
                    if (d[i][k] + d[k][j] < d[i][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        path[i][j] = k;
                    }
                }
            }
	    }
    }

    //calcular la clausura transitiva: (mínima cantidad de arcos que tengo que agregar para que la relación sea transitiva)
    public static int[][] transitiveClosure(int[][] graph) {
        int n = graph.length;
        int[][] reach = new int[n][n];

        // Inicializar la matriz de cierre transitivo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reach[i][j] = graph[i][j];
            }
        }

        // Algoritmo de Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (reach[i][k] == 1 && reach[k][j] == 1) {
                    	reach[i][j] = 1;
                    }
                }
            }
        }

        return reach;
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


	public List<Integer> ordenarTopologicamente() {

        // Inicializar la pila y el conjunto de vértices marcados
        Stack<Integer> pila = new Stack<>();
        ArrayList<Integer> visitados = new ArrayList<>();

        // Recorrer el grafo
        for (int u = 0; u < this.getVertices().size(); u++) {
            if (!visitados.contains(vertices.get(u).getId())) {
                ordenarTopologicamenteDFS(this, vertices.get(u), pila, visitados);
            }
        }

        // Devolver la pila como lista
        List<Integer> ordenamientoTopologico = new ArrayList<>(pila);
        Collections.reverse(ordenamientoTopologico);
        return ordenamientoTopologico;
    }

    private static void ordenarTopologicamenteDFS(GrafoDirigido grafo, Vertice u, Stack<Integer> pila, ArrayList<Integer> visitados) {

        // Marcar el vértice como visitado
        visitados.add(u.getId());

        // Recorrer los adyacentes de u
        for (int i = 0; i < u.getAdyacentes().size(); i++) {
            if (!visitados.contains(u.getAdyacentes().get(i).getId())) {
                ordenarTopologicamenteDFS(grafo, u.getAdyacentes().get(i), pila, visitados);
            }
        }

        // Apilar el vértice actual
        pila.push(u.getId());
    }

    public boolean esCiclicoUtil(Vertice v, ArrayList<Integer> marked, ArrayList<Integer> enRecorrido) {
    	if (enRecorrido.contains(v.getId())) {
    		return true;
    	}

    	if (marked.contains(v.getId())) {
    		return false;
    	}

    	marked.add(v.getId());
    	enRecorrido.add(v.getId());

    	for (int i = 0; i < v.getAdyacentes().size(); i++) {
    		if (esCiclicoUtil(v.getAdyacentes().get(i), marked, enRecorrido)) {
    			return true;
    		}
    	}

    	enRecorrido.remove(v.getId());
    	return false;
    }

    public boolean tieneCicloUtil () {
    	ArrayList<Integer> marked = new ArrayList<>();
    	ArrayList<Integer> enRecorrido = new ArrayList<>();

    	for (int i = 0; i < vertices.size(); i++) {
    		if (esCiclicoUtil(vertices.get(i), marked, enRecorrido)) {
    			return true;
    		}
    	}

    	return false;
    }

    public void ordenTopologicoUtil(Vertice v, ArrayList<Integer> marked, Stack<Integer> pila) {
    	marked.add(v.getId());

    	for (int i = 0; i < v.getAdyacentes().size(); i++) {
    		if (!marked.contains(v.getAdyacentes().get(i).getId())) {
    			ordenTopologicoUtil(v.getAdyacentes().get(i), marked, pila);
    		}
    	}

    	pila.push(v.getId());
    }

    public void ordenTopologico2() {
    	ArrayList<Integer> marked = new ArrayList<>();
    	Stack<Integer> pila = new Stack<>();

    	for (int i = 0; i < vertices.size(); i++) {
    		if (!marked.contains(vertices.get(i).getId())) {
    			ordenTopologicoUtil(vertices.get(i), marked, pila);
    		}
    	}

    	while (!pila.empty()) {
    		System.out.println(pila.pop() + ", ");
    	}
    }

   
}