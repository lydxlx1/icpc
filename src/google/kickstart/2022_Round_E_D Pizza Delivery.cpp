#define LOCAL

#include <iostream>
#include <list>
#include <vector>
#include <bitset>
#include <cassert>
#include <algorithm>
#include <set>
#include <map>
#include <unordered_set>
#include <unordered_map>
#include <sstream>
#include <functional>
#include <random>
#include <cstring>

using namespace std;

template<typename A, typename B>
string to_string(pair<A, B> p);

template<typename A, typename B, typename C>
string to_string(tuple<A, B, C> p);

template<typename A, typename B, typename C, typename D>
string to_string(tuple<A, B, C, D> p);

string to_string(const string &s) {
    return '"' + s + '"';
}

string to_string(const char *s) {
    return to_string((string) s);
}

string to_string(bool b) {
    return (b ? "true" : "false");
}

string to_string(vector<bool> v) {
    bool first = true;
    string res = "{";
    for (int i = 0; i < static_cast<int>(v.size()); i++) {
        if (!first) {
            res += ", ";
        }
        first = false;
        res += to_string(v[i]);
    }
    res += "}";
    return res;
}

template<size_t N>
string to_string(bitset<N> v) {
    string res = "";
    for (size_t i = 0; i < N; i++) {
        res += static_cast<char>('0' + v[i]);
    }
    return res;
}

template<typename A>
string to_string(A v) {
    bool first = true;
    string res = "{";
    for (const auto &x: v) {
        if (!first) {
            res += ", ";
        }
        first = false;
        res += to_string(x);
    }
    res += "}";
    return res;
}

template<typename A, typename B>
string to_string(pair<A, B> p) {
    return "(" + to_string(p.first) + ", " + to_string(p.second) + ")";
}

template<typename A, typename B, typename C>
string to_string(tuple<A, B, C> p) {
    return "(" + to_string(get<0>(p)) + ", " + to_string(get<1>(p)) + ", " + to_string(get<2>(p)) + ")";
}

template<typename A, typename B, typename C, typename D>
string to_string(tuple<A, B, C, D> p) {
    return "(" + to_string(get<0>(p)) + ", " + to_string(get<1>(p)) + ", " + to_string(get<2>(p)) + ", " +
           to_string(get<3>(p)) + ")";
}

void debug_out() { cerr << endl; }

template<typename Head, typename... Tail>
void debug_out(Head H, Tail... T) {
    cerr << " " << to_string(H);
    debug_out(T...);
}


#ifdef LOCAL
#define debug(...) cerr << "[" << #__VA_ARGS__ << "]:", debug_out(__VA_ARGS__)
#else
#define debug(...) 42
#endif


//int inf = 1000000000;

int N, P, M, Ar, Ac;
int dx[] = {-1, 0, 0, 1};
int dy[] = {0, 1, -1, 0};
int rev_dir[] = {3, 2, 1, 0};
char op[10];
int op_cost[10];
int X[100], Y[100], C[100];

long long dp[22][12][12][1111];
long long INF = 1LL << 62;
int people_label[22][22];

inline long long cost(long long current_coin, int dir) {
    if (op[dir] == '+')
        return current_coin + op_cost[dir];
    else if (op[dir] == '-')
        return current_coin - op_cost[dir];
    else if (op[dir] == '*')
        return current_coin * op_cost[dir];
    else {
        if (current_coin % op_cost[dir] == 0 || current_coin > 0)
            return current_coin / op_cost[dir];
        else
            return -(-current_coin / op_cost[dir]) - 1;
    }
}


int main(void) {
    mt19937 rng(0);


    int T;
    cin >> T;
    for (int _case = 1; _case <= T; _case++) {
        cin >> N >> P >> M >> Ar >> Ac;
        Ar--; Ac--;
        for (int i = 0; i < 4; i++) {
            cin >> op[i] >> op_cost[i];
        }
        memset(people_label, -1, sizeof(people_label));
        for (int i = 0; i < P; i++) {
            cin >> X[i] >> Y[i] >> C[i];
            X[i]--;
            Y[i]--;
            people_label[X[i]][Y[i]] = i;
        }

        long long ans = -INF;
        for (int i = 0; i <= M; i++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    for (int mask = 0; mask < (1 << P); mask++) {
                        long long &res = dp[i][x][y][mask];
                        if (i == 0) {
                            if (x == Ar && y == Ac && mask == 0) {
                                res = 0;
                            } else {
                                res = -INF;
                            }
                        } else {
                            res = dp[i - 1][x][y][mask];
                            if (people_label[x][y] != -1 && (mask & (1 << people_label[x][y])) && dp[i][x][y][mask - (1 << people_label[x][y])] > -INF) {
                                res = max(res, dp[i][x][y][mask - (1 << people_label[x][y])] + C[people_label[x][y]]);
                            }

                            for (int k = 0; k < 4; k++) {
                                int pre_x = x + dx[k];
                                int pre_y = y + dy[k];
                                if (pre_x >= 0 && pre_x < N && pre_y >= 0 && pre_y < N && dp[i - 1][pre_x][pre_y][mask] > -INF) {
                                    res = max(res, cost(dp[i - 1][pre_x][pre_y][mask], rev_dir[k]));
                                }
                            }
                        }

                        if (mask == (1 << P) - 1) {
                            ans = max(ans, res);
                        }
                    }
                }
            }
        }

        cout << "Case #" << _case << ": " << (ans > -INF ? to_string(ans) : "IMPOSSIBLE") << "\n";

    }

    return 0;
}
