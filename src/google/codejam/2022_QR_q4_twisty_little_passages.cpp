#include <iostream>
#include <vector>
#include <bitset>
#include <algorithm>
#include <set>
#include <unordered_set>
#include <sstream>
#include <random>
using namespace std;


int main(void) {
    mt19937 rng(0);

    int T; cin >> T;

    for (int _case=1; _case<=T; _case++) {
        int N, K;
        cin >> N >> K;
        vector<int> degree(N + 1, -1);

        int x, d; cin >> x >> d;
        degree[x] = d;

        double avg = 0;
        int cnt = 0;

        for (int i=0; i<K; i++) {
            if (i % 2 == 0) {
                x = rng() % N + 1;
                cout << "T " << x << endl;
                cin >> x >> d;
                degree[x] = d;
                avg += d;
                cnt += 1;
            } else {
                cout << "W" << endl;
                cin >> x >> d;
                degree[x] = d;
            }
        }
        avg /= cnt;

        double ans = 0;
        int remaining = 0;
        for (int i=1; i<=N; i++) {
            if (degree[i] != -1) {
                ans += degree[i];
            } else {
                remaining += 1;
            }
        }
        ans += avg * remaining;
        cout << "E " << (long long)(round(ans) / 2) << endl;
    }

    return 0;
}

