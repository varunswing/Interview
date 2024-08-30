class LCA
{
    public int N, root;
    public ArrayDeque<Integer>[] edges;
    private int[] enter;
    private int[] exit;
    private int LOG = 17; //change this
    private int[][] dp;
 
    public LCA(int n, ArrayDeque<Integer>[] edges, int r)
    {
        N = n;   root = r;
        enter = new int[N+1];
        exit = new int[N+1];
        dp = new int[N+1][LOG];
        this.edges = edges;
        int[] time = new int[1];
        //change to iterative dfs if N is large
        dfs(root, 0, time);
        dp[root][0] = 1;
        for(int b=1; b < LOG; b++)
            for(int v=1; v <= N; v++)
                dp[v][b] = dp[dp[v][b-1]][b-1];
    }
    private void dfs(int curr, int par, int[] time)
    {
        dp[curr][0] = par;
        enter[curr] = ++time[0];
        for(int next: edges[curr])
            if(next != par)
                dfs(next, curr, time);
        exit[curr] = ++time[0];
    }
    public int lca(int x, int y)
    {
        if(isAnc(x, y))
            return x;
        if(isAnc(y, x))
            return y;
        int curr = x;
        for(int b=LOG-1; b >= 0; b--)
        {
            int temp = dp[curr][b];
            if(!isAnc(temp, y))
                curr = temp;
        }
        return dp[curr][0];
    }
    private boolean isAnc(int anc, int curr)
    {
        return enter[anc] <= enter[curr] && exit[anc] >= exit[curr];
    }
}