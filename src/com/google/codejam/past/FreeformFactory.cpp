#include <iostream>
#include <algorithm>
using namespace std;

int _case = 0;
int n;
int t;
char a[10][10];
char b[10][10];

int p1[10];
int p2[10];

int main(void) {
	cin >> t;
	for (_case=1; _case<=t; _case++) {
		cin >> n;

		int min = 1 << 29;
		for (int i=0; i<n; i++) cin >> a[i];

		for (int mask=0; mask<(1 << (n * n)); mask++) {
			int cost = 0;

			for (int i=0; i<n; i++) for (int j=0; j<n; j++) b[i][j] = a[i][j];
			for (int i=0; i<n*n; i++)
				if ((1 << i) & mask) {
					int u = i % n;
					int v = i / n;
//					if (u == 1 && v == 1) cout << "mask = " << mask << endl;
					if (b[u][v] == '0') cost++;
					b[u][v] = '1';
				}

			bool ok = true;
			for (int i=0; i<n; i++) p1[i] = i;
			do {
				for (int j=0; j<n; j++) p2[j] = j;
				do {
					//for (int k=0;k<n; k++) cout << p2[k] << ' ';
					//cout << endl;

					bool visited[5] = {0};
					for (int u=0; u<n; u++) {
						bool hit = false;
						for (int v=0; v<n; v++) {
							if (b[p1[u]][p2[v]] == '1' && !visited[p2[v]]) {
								visited[p2[v]] = true;
								hit = true;
								break;
							}
						}
						if (!hit) {
							ok = false;
							goto here;
						}
					}
				} while(next_permutation(p2, p2+n));
			} while(next_permutation(p1, p1+n));

here:;

			if (ok && cost < min) min = cost;
		}

		printf("Case #%d: %d\n", _case, min);
	}

	return 0;
}
