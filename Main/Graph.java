// https://www.geeksforgeeks.org/implementing-generic-graph-in-java/

import java.util.*;
public class Graph {
    //adjacent list is created for each vertex,
    //where each vertex is put in as well as its own adjacent list
    protected Map<Integer, List<Integer>> adjacentList;

    public Graph() {
        adjacentList = new HashMap<>();
    }

    // add vertex to the adjacent list, with its own adjacent list
    public void addVertex(int vertex) {
        adjacentList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(int vertex) {
        //changes the values in the adjacent list into an array list so it can be accessed via index
        List<List<Integer>> values = new ArrayList<>(adjacentList.values());

        //iterates through that array list of values and removes the vertex from all values' adjacent lists
        for (int i = 0; i < values.size(); i++) {
            List<Integer> edges = values.get(i);
            edges.remove(Integer.valueOf(vertex));
        }
        //removes the vertex from the main adjacent list as well
        adjacentList.remove(vertex);
    }

    // adds edge between two vertices by adding each one to the others adjacent list
    public void addEdge(int v1, int v2) {
        adjacentList.putIfAbsent(v1, new ArrayList<>());
        adjacentList.putIfAbsent(v2, new ArrayList<>());
        adjacentList.get(v1).add(v2);
        adjacentList.get(v2).add(v1);
    }

    // gets adjacent lists of both vertices,
    // if each list is not null, then it removes that key from the map/adjacent list
    public void removeEdge(int v1, int v2) {
        List<Integer> edgesV1 = adjacentList.get(v1);
        List<Integer> edgesV2 = adjacentList.get(v2);
        if (edgesV1 != null) {
            edgesV1.remove(Integer.valueOf(v2));
        }
        if (edgesV2 != null) {
            edgesV2.remove(Integer.valueOf(v1));
        }
    }

    // checks if a vertex has another vertex adjacent to it
    public boolean hasVertex(int vertex) {
        if (adjacentList.containsKey(vertex)) {
            return true;
        }
        return false;
    }

    // checks if two vertices have an edge between them
    public boolean hasEdge(int v1, int v2) {
        // if v1 has v2 in its adjacent list, then return true
        if(adjacentList.containsKey(v1) && adjacentList.containsKey(v2)) {
            return adjacentList.get(v1).contains(v2);
        }
        return false;
    }

    public List<Integer> getAdjVert(int vertex) {
        return adjacentList.get(vertex);
    }
}
