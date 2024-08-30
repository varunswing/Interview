public static long gcd(long a, long b)
{
    if(a > b)
        a = (a+b)-(b=a);
    if(a == 0L)
        return b;
    return gcd(b%a, a);
}

long lcm = (a*b)/gcd(a, b);