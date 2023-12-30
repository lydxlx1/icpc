#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <cmath>

using namespace std;


void doit() {
    int m;
    cin >> m;
    vector<int> cnt(100);
    while (m--) {
        int t, v;
        cin >> t >> v;
        if (t == 1) {
            cnt[v]++;
        } else if (t == 2) {
            bool ok = true;
            int remaining = 0;
            for (int i = 0; v; i++, v /= 2) {
                remaining += cnt[i];
                if (v % 2 == 1) {
                    if (remaining == 0) {
                        ok = false;
                        break;
                    }
                    remaining--;
                }
                remaining /= 2;
            }
            if (ok) cout << "YES\n";
            else cout << "NO\n";
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    doit();
    return 0;
}
