class LazySegTree
{
    //definitions
    private int NULL = -1;
    private int[] tree;
    private int[] lazy;
    private int length;
 
    public LazySegTree(int N)
    {
        length = N;   int b;
        for(b=0; (1<<b) < length; b++);
        tree = new int[1<<(b+1)];
        lazy = new int[1<<(b+1)];
    }
    public int query(int left, int right)
    {
        //left and right are 0-indexed
        return get(1, 0, length-1, left, right);
    }
    private int get(int v, int currL, int currR, int L, int R)
    {
        if(L > R)
            return NULL;
        if(L <= currL && currR <= R)
            return tree[v];
        propagate(v);
        int mid = (currL+currR)/2;
        return comb(get(v*2, currL, mid, L, Math.min(R, mid)),
                get(v*2+1, mid+1, currR, Math.max(L, mid+1), R));
    }
    public void update(int left, int right, int delta)
    {
        add(1, 0, length-1, left, right, delta);
    }
    private void add(int v, int currL, int currR, int L, int R, int delta)
    {
        if(L > R)
            return;
        if(currL == L && currR == R)
        {
            //exact covering
            tree[v] += delta;
            lazy[v] += delta;
            return;
        }
        propagate(v);
        int mid = (currL+currR)/2;
        add(v*2, currL, mid, L, Math.min(R, mid), delta);
        add(v*2+1, mid+1, currR, Math.max(L, mid+1), R, delta);
        tree[v] = comb(tree[v*2], tree[v*2+1]);
    }
    private void propagate(int v)
    {
        //tree[v] already has lazy[v]
        if(lazy[v] == 0)
            return;
        tree[v*2] += lazy[v];
        lazy[v*2] += lazy[v];
        tree[v*2+1] += lazy[v];
        lazy[v*2+1] += lazy[v];
        lazy[v] = 0;
    }
    private int comb(int a, int b)
    {
        return Math.max(a,b);
    }
}