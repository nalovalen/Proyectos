package colecciones.Grafos;

public interface Grafo {

	public boolean esVacio();

	public int cantVertices();

	public int cantArcos();

	public boolean hayArco(Vertice a, Vertice b);

	public boolean insertarVertice(Vertice a);

	public boolean insertarArco(AristaCosto a);

	public boolean borrarVertice(Vertice a);

	public boolean borrarArco(AristaCosto a);

	public boolean pertenece(Vertice a);

	public String toString();

	public boolean equals();
}