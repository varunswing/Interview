```json
{
	/*
    Place your snippets for java here. Each snippet is defined under a snippet name and has a prefix,
    body and description. The prefix is what is used to trigger the snippet and the body will be expanded
    and inserted. Possible variables are:
	$1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders.
     Placeholders with the same ids are connected.
	Example:
	"Print to console": {
		"prefix": "log",
		"body": [
			"console.log('$1');",
			"$2"
		],
		"description": "Log output to console"
	}
    */

	"Java snippet": {
		"prefix": [
			"include",
			"cpp"
		],
		"body": [
			"#include<bits/stdc++.h>",
			"using namespace std;\n",

			"//common file for PBDS",
			"#include<ext/pb_ds/assoc_container.hpp>",
			"//including tree_order_statistics_node_update",
			"#include<ext/pb_ds/tree_policy.hpp>",
			"//namespace",
			"using namespace __gnu_pbds; ",
			"//macro definition",
			"#define ordered_set tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update>\n",

			"#define gc getchar_unlocked",
			"#define fo(i,n) for(int i=0;i<n;i++)",
			"#define of(i,n) for(int i=n-1; i>=0; i--)",  
			"#define forn(i,k,n) for(int i=k;k<n?i<n:i>n;k<n?i+=1:i-=1)",
			"#define ll long long",
			"#define si(x)\tscanf(\"%d\",&x)",
			"#define sl(x)\tscanf(\"%lld\",&x)",
			"#define ss(s)\tscanf(\"%s\",s)",
			"#define pi(x)\tprintf(\"%d\\n\",x)",
			"#define pl(x)\tprintf(\"%lld\\n\",x)",
			"#define ps(s)\tprintf(\"%s\\n\",s)",
			"#define deb(x) cout << #x << \"=\" << x << endl",
			"#define deb2(x, y) cout << #x << \"=\" << x << \",\" << #y << \"=\" << y << endl",
			"#define pb push_back",
			"#define mp make_pair",
			"#define F first",
			"#define S second",
			"#define all(x) x.begin(), x.end()",
			"#define clr(x) memset(x, 0, sizeof(x))",
			"#define srt(x) sort(all(x))",
			"#define srtrev(c) sort(all(c)); reverse(all(c))",
			"#define tr(it, a) for(auto it = a.begin(); it != a.end(); it++)",
			"#define PI 3.1415926535897932384626",
			"typedef pair<int, int>\tpii;",
			"typedef pair<ll, ll>\tpl;",
			"typedef vector<int>\t\tvi;",
			"typedef vector<ll>\t\tvl;",
			"typedef vector<pii>\t\tvpii;",
			"typedef vector<pl>\t\tvpl;",
			"typedef vector<vi>\t\tvvi;",
			"typedef vector<vl>\t\tvvl;",
			"mt19937_64 rang(chrono::high_resolution_clock::now().time_since_epoch().count());",
			"int rng(int lim) {",
			"\tuniform_int_distribution<int> uid(0,lim-1);",
			"\treturn uid(rang);",
			"}",
			"int mpow(int base, int exp); ",
			"void ipgraph(int n, int m);",
			"void dfs(int u, int par);\n",

			"const int mod = 1'000'000'007;",
			"const int N = 3e5, M = N;",
			"//=======================\n",

			"vi g[N];",
			"int a[N];\n",

			"void solve() {",
			"\tint i, j, n, m;",
			"\t $0",
			"}\n",

			"int main() {",
				"\tios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);",
				"\tsrand(chrono::high_resolution_clock::now().time_since_epoch().count());\n",

				"\tint t = 1;",
				"\tcin >> t;",
				"\twhile(t--){",
				"\t\tsolve();",
				"\t}\n",

				"\treturn 0;",
			"}\n",

			"int mpow(int base, int exp) {",
			"\tbase %= mod;",
			"\tint result = 1;",
			"\twhile (exp > 0) {",
				"\t\tif (exp & 1) result = ((ll)result * base) % mod;",
				"\t\tbase = ((ll)base * base) % mod;",
				"\t\texp >>= 1;",
			"\t}",
			"\treturn result;",
			"}\n",

			"void ipgraph(int n, int m){",
				"\tint i, u, v;",
				"\twhile(m--){",
					"\t\tcin>>u>>v;",
					"\t\tu--, v--;",
					"\t\tg[u].pb(v);",
					"\t\tg[v].pb(u);",
				"\t}",
			"}\n",

			"void dfs(int u, int par){",
				"\tfor(int v:g[u]){",
					"\t\tif (v == par) continue;",
					"\t\tdfs(v, u);",
				"\t}",
			"}"
		],
		"description": "Log output to console"
	},
	"sysout":{
		"prefix": "sysout",
		"body": [
			"System.out.println($0);"
		],
		"description": "cin"
	},
    "System.out":{
		"prefix": "print",
		"body": [
			"System.out.println($0);"
		],
		"description": "cin"
	},
    "print":{
		"prefix": "System.out",
		"body": [
			"System.out.println($0);"
		],
		"description": "cin"
	},
    "sysout without line":{
		"prefix": "System.l",
		"body": [
			"System.out.print($0);"
		],
		"description": "cin"
	},
	"scanner":{
		"prefix": "sc",
		"body": [
			"Scanner sc = new Scanner(System.in);"
		],
		"description": "cout"
	},
	"int fxn":{
		"prefix": "pi",
		"body": [
			"public int $0(){",
			"}"
		],
		"description": "cout"
	},
	"void fxn":{
		"prefix": "pv",
		"body": [
			"public void $0(){",
			"}"
		],
		"description": "cout"
	},
	"void static fxn":{
		"prefix": "psv",
		"body": [
			"public static void $0(){",
			"\t",
			"}"
		],
		"description": "cout"
	},"string fxn":{
		"prefix": "ps",
		"body": [
			"public String $0(){",
			"\t",
			"}"
		],
		"description": "cout"
	},
	"for loop":{
		"prefix": "fo",
		"body": [
			"for(i=0; i<n; i++){",
			"\t$0",
			"}"
		],
		"description": "For Loop"
	},
	"for loop decreasing":{
		"prefix": "of",
		"body": [
			"for(i=n-1; i>=0; i--){",
			"\t$0",
			"}"
		],
		"description": "For Loop"
	},
	"main":{
		"prefix": "main",
		"body": [
			"public static void main(String args[]){",
			"\t$0",
			"}"
		],
		"description": "basic template"
	}
}
```