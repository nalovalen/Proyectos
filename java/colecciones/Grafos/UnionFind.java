package colecciones.Grafos;

public class UnionFind {

    /**
     * parent list
     */
    private int[] parent;


    /**
   *
   * @param n representative number
   */
    public UnionFind(int n) {
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
   *
   * @param x number of which I am looking for the father.
   * @return the father of the number.
   */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
   *
   * @param x number that I am going to join to the same parent.
   * @param y number that I am going to join to the same parent.
   */
    public void union(int x, int y) {
       parent[y] = x;
    }

    /**
   *
   * @param x number where the father looked for.
   * @param y number where the father looked for.
   * @return if the given numbers have the same parent.
   */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
