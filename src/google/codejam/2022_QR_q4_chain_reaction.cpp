#include <iostream>
#include <vector>
#include <bitset>
#include <algorithm>
#include <set>
#include <unordered_set>
#include <sstream>
using namespace std;


long long INF = 1LL << 50;
long long ans;
int N;
vector<vector<int> > edges;
vector<long long> F;

long long dfs(int u, int parent) {
    long long smallest = INF;
    for (int v : edges[u]) {
        if (v == parent) continue;
        long long tmp = dfs(v, u);
        ans += tmp;
        smallest = min(smallest, tmp);
    }
    long long ret;
    if (smallest == INF) {
        ret = F[u];
    } else {
        ans -= smallest;
        ret = max(F[u], smallest);
    }
    return ret;
}


int main(void) {
    int T; cin >> T;
    for (int _case=1; _case<=T; _case++) {
        cin >> N;
        F.clear(); edges.clear();
        F.resize(N + 1, 0);
        edges.resize(N + 1, vector<int>());
        for (int i=1; i<=N; i++) cin >> F[i];
        for (int i=1; i<=N; i++) {
            int j; cin >> j;
            edges[j].push_back(i);
        }

        ans = 0;
        long long tmp = dfs(0, -1);
        ans += tmp;
        cout << "Case #" << _case << ": " << ans << "\n";
    }

    return 0;
}

