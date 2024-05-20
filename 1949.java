import java.util.*;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int maxTrailLength;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            visited = new boolean[N][N];

            int highest = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    highest = Math.max(highest, map[i][j]);
                }
            }

            maxTrailLength = 0;
            List<int[]> peaks = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == highest) {
                        peaks.add(new int[]{i, j});
                    }
                }
            }

            for (int[] peak : peaks) {
                dfs(peak[0], peak[1], map[peak[0]][peak[1]], 1, false);
            }

            System.out.println("#" + t + " " + maxTrailLength);
        }

        sc.close();
    }

    static void dfs(int x, int y, int currentHeight, int length, boolean modified) {
        maxTrailLength = Math.max(maxTrailLength, length);
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                if (map[nx][ny] < currentHeight) {
                    dfs(nx, ny, map[nx][ny], length + 1, modified);
                } else if (!modified && map[nx][ny] - K < currentHeight) {
                    int originalHeight = map[nx][ny];
                    map[nx][ny] = currentHeight - 1;
                    dfs(nx, ny, map[nx][ny], length + 1, true);
                    map[nx][ny] = originalHeight;
                }
            }
        }

        visited[x][y] = false;
    }
}