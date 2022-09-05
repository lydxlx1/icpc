#include <iostream>
#include <vector>
#include <bitset>
#include <cassert>
#include <algorithm>
#include <set>
#include <unordered_set>
#include <sstream>
#include <random>
using namespace std;

int inf = 1000000000;

int main(void) {
    mt19937 rng(0);

    int T; cin >> T;

    for (int _case=1; _case<=T; _case++) {
        int N; cin >> N;

        set<int> A;
        set<int> B;
        for (int i=0; A.size() < N && i<32; i++) {
            if ((1LL << i) <= inf) {
                A.insert(1 << i);
            }
        }
        for (int val=inf; A.size() < N; val--) {
            if (A.find(val) == A.end()) {
                A.insert(val);
            }
        }
        assert(A.size() == N);

        for (int i : A) cout << i << ' ';
        cout << endl;

        for (int i=0; i<N; i++) {
            int tmp; cin >> tmp;
            B.insert(tmp);
        }

        long long sum = 0;
        for (int i : A) sum += i;
        for (int i : B) sum += i;

        set<int> C;
        C.insert(A.begin(), A.end());
        C.insert(B.begin(), B.end());

        assert(sum % 2 == 0);
        long long half_sum = sum / 2;

        set<int> ans;
        for (auto it=C.rbegin(); half_sum > 0 && it!=C.rend(); it++) {
            if (half_sum >= *it) {
                ans.insert(*it);
                half_sum -= *it;
            }
        }
        assert(half_sum == 0);
        for (int i : ans) cout << i << ' ';
        cout << endl;
    }

    return 0;
}

