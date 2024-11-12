public static void dfs(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> stack = new LinkedList<>();
    
    stack.add(start);
    visited.add(start);
    
    while (!stack.isEmpty()) {
        int current = stack.poll();
        System.out.print(current + " "); // Do something with the current node
        
        // Explore neighbors
        List<Integer> neighbors = graph.get(current);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}