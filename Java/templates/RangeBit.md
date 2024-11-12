class RangeBit
{
    //FenwickTree and RangeBit are faster than LazySegTree by constant factor
    final int[] value;
    final int[] weightedVal;
 
    public RangeBit(int treeTo)
    {
        value = new int[treeTo+2];
        weightedVal = new int[treeTo+2];
    }
    private void updateHelper(int index, int delta)
    {
        int weightedDelta = index*delta;
        for(int j = index; j < value.length; j += j & -j)
        {
            value[j] += delta;
            weightedVal[j] += weightedDelta;
        }
    }
    public void update(int from, int to, int delta)
    {
        updateHelper(from, delta);
        updateHelper(to + 1, -delta);
    }
    private int query(int to)
    {
        int res = 0;
        int weightedRes = 0;
        for (int j = to; j > 0; j -= j & -j)
        {
            res += value[j];
            weightedRes += weightedVal[j];
        }
        return ((to + 1)*res)-weightedRes;
    }
    public int query(int from, int to)
    {
        if (to < from)
            return 0;
        return query(to) - query(from - 1);
    }
}