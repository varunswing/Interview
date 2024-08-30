public static void bfs(int[][] grid, int startX, int startY) {
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    Queue<int[]> queue = new LinkedList<>();
    
    // Define directions: up, down, left, right
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    // Add starting cell to queue and mark as visited
    queue.add(new int[]{startX, startY});
    visited[startX][startY] = true;
    
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int x = current[0];
        int y = current[1];
        System.out.print(grid[x][y] + " "); // Do something with the current cell
        
        // Explore neighbors
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            // Check if neighbor is within grid bounds and not visited
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                queue.add(new int[]{newX, newY});
                visited[newX][newY] = true;
            }
        }
    }
}