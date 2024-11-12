public static void push(TreeMap<Integer, Integer> map, int k, int v)
{
    //map[k] += v;
    if(!map.containsKey(k))
        map.put(k, v);
    else
        map.put(k, map.get(k)+v);
}