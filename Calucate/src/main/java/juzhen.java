public class juzhen {

    /*
    给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
    * */
    public static int numSubmat(int[][] mat) {
        int ans = 0;
        int m = mat.length;
        int n = mat[0].length;
        int maxZ[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            int maxZuo = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) { // 此节点左边连续不为1的组合数
                    maxZuo++;
                } else {
                    maxZuo = 0;
                }
                maxZ[i][j] = maxZuo;
            }
        }
        for (int i = 0; i< m; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(maxZ[i][j] + " ");
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k >= 0; k--) {
                    if (maxZ[k][j] == 0){
                        break;
                    }
                    ans += maxZ[k][j];
                }
            }
        }
        System.out.println();
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        int mat[][] = new int[][] {{1,0,1},{1,1,0},{1,1,0}};
        numSubmat(mat);
    }
}
