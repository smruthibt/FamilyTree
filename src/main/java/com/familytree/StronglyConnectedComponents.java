package com.familytree;

import java.util.*;

public class StronglyConnectedComponents {
    public static List<List<String>> findSCCs(Map<String, Person> graph) {
        Set<String> vertices = graph.keySet();
        int numVertices = vertices.size();
        Map<String, Integer> vertexToIndex = new HashMap<>();
        int index = 0;
        for (String vertex : vertices) {
            vertexToIndex.put(vertex, index++);
        }

        int[] disc = new int[numVertices];
        int[] low = new int[numVertices];
        boolean[] onStack = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();
        List<List<String>> sccs = new ArrayList<>();

        for (String vertex : vertices) {
            int vertexIndex = vertexToIndex.get(vertex);
            if (disc[vertexIndex] == 0) {
                tarjan(vertex, vertexIndex, disc, low, onStack, stack, graph, vertexToIndex, sccs);
            }
        }

        return sccs;
    }

    private static void tarjan(String u, int uIndex, int[] disc, int[] low, boolean[] onStack,
                               Stack<Integer> stack, Map<String, Person> graph, Map<String, Integer> vertexToIndex,
                               List<List<String>> sccs) {
        disc[uIndex] = low[uIndex] = ++time;
        stack.push(uIndex);
        onStack[uIndex] = true;

        Person person = graph.get(u);
        for (String v : person.getNeighbors().stream().map(Person::getName).toList()) {
            int vIndex = vertexToIndex.get(v);
            if (disc[vIndex] == 0) {
                tarjan(v, vIndex, disc, low, onStack, stack, graph, vertexToIndex, sccs);
                low[uIndex] = Math.min(low[uIndex], low[vIndex]);
            } else if (onStack[vIndex]) {
                low[uIndex] = Math.min(low[uIndex], disc[vIndex]);
            }
        }

        if (low[uIndex] == disc[uIndex]) {
            List<String> scc = new ArrayList<>();
            while (true) {
                int vIndex = stack.pop();
                onStack[vIndex] = false;
                scc.add(getVertexFromIndex(vertexToIndex, vIndex));
                if (uIndex == vIndex) {
                    break;
                }
            }
            sccs.add(scc);
        }
    }

    private static String getVertexFromIndex(Map<String, Integer> vertexToIndex, int index) {
        for (Map.Entry<String, Integer> entry : vertexToIndex.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static int time = 0;

}
