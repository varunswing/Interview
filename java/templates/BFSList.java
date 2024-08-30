public static void bfs(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    
    queue.add(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
        int current = queue.poll();
        System.out.print(current + " "); // Do something with the current node
        
        // Explore neighbors
        List<Integer> neighbors = graph.get(current);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}