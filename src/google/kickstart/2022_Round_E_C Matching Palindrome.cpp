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


int inf = 1000000000;


vector<bool> isPrefixPalindrom(string s) {
    vector<bool> f(s.size(), false);
    long long B = 29;
    long long MOD = 999999999999989LL;

    long long a = 0;
    long long b = 0;
    long long pow = 1;
    for (int i = 0; i < s.size(); i++) {
        a = (a * B + s[i] - 'a' + 1) % MOD;
        b = (b + (s[i] - 'a' + 1) * pow) % MOD;
        pow = pow * B % MOD;
        f[i] = a == b;
    }
    return f;
}

int main(void) {
    mt19937 rng(0);

    int T;
    cin >> T;
    for (int _case = 1; _case <= T; _case++) {
        int N;
        cin >> N;
        string s;
        cin >> s;

        vector<bool> prefix = isPrefixPalindrom(s);
        reverse(s.begin(), s.end());
        vector<bool> suffix = isPrefixPalindrom(s);
        reverse(s.begin(), s.end());
        reverse(suffix.begin(), suffix.end());

//        debug(prefix);
//        debug(suffix);

        for (int i=0; i<prefix.size(); i++) {
            if (prefix[i] && (i == prefix.size() - 1 || suffix[i + 1])) {
                printf("Case #%d: %s\n", _case, s.substr(0, i + 1).c_str());
                break;
            }
        }
    }


    return 0;
}
