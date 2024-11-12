public static void pull(TreeMap<Integer, Integer> map, int k, int v)
{
    //assumes map[k] >= v
    //map[k] -= v
    int lol = map.get(k);
    if(lol == v)
        map.remove(k);
    else
        map.put(k, lol-v);
}