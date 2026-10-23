
import java.util.*;

class Solution {
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] color = new int[n];

        // W -> R -> B -> W
        for (int i = 0; i < n; i++) {
            char c = colors.charAt(i);
            if (c == 'W') color[i] = 0;
            else if (c == 'R') color[i] = 1;
            else color[i] = 2;
        }

        int root = -1;

        for (int u = 0; u < n; u++) {
            int parentCount = 0;
            boolean invalid = false;

            for (int v : graph[u]) {
                if (color[v] == color[u]) {
                    return -1;
                }

                // Previous color in the cycle means parent
                if (color[v] == (color[u] + 2) % 3) {
                    parentCount++;
                }
            }

            if (parentCount > 1) {
                return -1;
            }

            if (parentCount == 0) {
                if (graph[u].size() > 2 || root != -1) {
                    return -1;
                }
                root = u;
            }
        }

        return root;
    }
}
