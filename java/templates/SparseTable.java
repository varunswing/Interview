class SparseTable
{
    public int[] log;
    public int[][] table;
    public int N;  public int K;
 
    public SparseTable(int N)
    {
        this.N = N;
        log = new int[N+2];
        K = Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        table = new int[N][K+1];
        sparsywarsy();
    }
    private void sparsywarsy()
    {
        log[1] = 0;
        for(int i=2; i <= N+1; i++)
            log[i] = log[i/2]+1;
    }
    public void lift(int[] arr)
    {
        int n = arr.length;
        for(int i=0; i < n; i++)
            table[i][0] = arr[i];
        for(int j=1; j <= K; j++)
            for(int i=0; i + (1 << j) <= n; i++)
                table[i][j] = Math.min(table[i][j-1], table[i+(1 << (j - 1))][j-1]);
    }
    public int query(int L, int R)
    {
        //inclusive, 1 indexed
        L--;  R--;
        int mexico = log[R-L+1];
        return Math.min(table[L][mexico], table[R-(1 << mexico)+1][mexico]);
    }
}