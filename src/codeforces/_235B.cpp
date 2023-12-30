#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <cmath>

using namespace std;


/**
 * A consecutive n O's contribute to n^2 score,
 * which can be rewritten as n^2 = choose(n, 2) * 2 + n.
 * The former, choose(n, 2), can be credited to each pair of O's in the substring.
 * The latter, n, can be credited to each O in the substring.
 * According to the linearity of expectation, we can calculate the expectation of the two parts separately.
 *
 * (a) The latter is part is trivial, which is just equal to P_1 + ... + P_n, where P_i is the probability of S[i] == 'O'.
 * (b) To compute the former, we can SUM UP the expected number of O-pairs ending at each position-i, S[.. i].
 *     We iterate each of such pair [j, i], where 1 <= j < i, then the expectation is
 *     P[j] * P[j+1] * ... * P[i-1] * P[i],
 *     since all S[j..i] must be all O's.
 *     It follows that the expected number of O-pairs ending at position-i is
 *     P[i] * P[i-1]
 *     P[i] * P[i-1] * P[i-2]
 *     P[i] * P[i-1] * P[i-2] * P[i-3]
 *     ...
 *     P[i] * P[i-1] * P[i-2] * P[i-3] * ... * P[2]
 *     P[i] * P[i-1] * P[i-2] * P[i-3] * ... * P[2] * P[1]
 */
int main() {
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;
    double ans = 0;
    double prefix_product_sum = 0;
    double A;

    for (int i = 0; i < n; i++) {
        cin >> A;
        ans += 2 * (prefix_product_sum * A);
        prefix_product_sum = prefix_product_sum * A + A;
        ans += A;
    }

    printf("%.10f\n", ans);
    return 0;
}
