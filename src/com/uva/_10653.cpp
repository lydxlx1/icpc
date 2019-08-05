// UVa 10653 - Bombs! NO they are Mines!!
// Double-ended BFS

#include <iostream>
#include <string>
#include <cstdio>
#include <cstdlib>
#include <cstring>
using namespace std;

int r, c;
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
bool blocked[1001][1001];

int dist[2][1001][1001];
int queue[2][1001*1001][2];
int head[2], tail[2];

int bfs(int start_x, int start_y, int end_x, int end_y) {
    int inf = 1 << 29;
    for (int i=0; i<r; i++)
        for (int j=0; j<c; j++) 
            dist[0][i][j] = dist[1][i][j] = inf;
            
    dist[0][start_x][start_y] = dist[1][end_x][end_y] = 0;
    queue[0][0][0] = start_x;
    queue[0][0][1] = start_y;
    queue[1][0][0] = end_x;
    queue[1][0][1] = end_y;
    head[0] = head[1] = 0;
    tail[0] = tail[1] = 1;
    
    int which = 0;
    while (head[which] < tail[which]) {
        int x = queue[which][head[which]][0];
        int y = queue[which][head[which]++][1];
        if (dist[1 - which][x][y] < inf) {
            return dist[which][x][y] + dist[1 - which][x][y];
        }
        
        for (int k=0; k<4; k++) {
            int xx = x + dx[k];
            int yy = y + dy[k];
            if (xx >= 0 && xx < r && yy >= 0 && yy < c && !blocked[xx][yy] && dist[which][xx][yy] >= inf) {
                dist[which][xx][yy] = dist[which][x][y] + 1;
                queue[which][tail[which]][0] = xx;
                queue[which][tail[which]++][1] = yy;
            }
        }
        which = 1 - which;
    }
    return -1;
}

int main()
{
    while(scanf("%d%d", &r, &c)) {
        if (r == 0 && c == 0) {
            break;
        }
        
        memset(blocked, 0, sizeof(blocked));
        int num_rows;
        scanf("%d", &num_rows);
        for (int k=0; k<num_rows; k++) {
            int i, num_cols;
            scanf("%d", &i);
            for (scanf("%d", &num_cols); num_cols; num_cols--) {
                int j;
                scanf("%d", &j);
                blocked[i][j] = true;
            }
        }
        int start_x, start_y, end_x, end_y;
        scanf("%d%d%d%d", &start_x, &start_y, &end_x, &end_y);
        printf("%d\n", bfs(start_x, start_y, end_x, end_y));
    }
    return 0;
}
