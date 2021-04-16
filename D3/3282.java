package swea3282;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int[] v, c;
    static int n, k, rst;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            v = new int[n];
            c = new int[n];
            rst=0;
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }
            chkbest(0,0, 0);
            sb.append("#"+tc+" "+rst+"\n");
        }
        System.out.print(sb);
    }
    static void chkbest(int depth,int now_v, int now_c){
        if(now_v>k){
            return;
        }
        if(depth == n){
            rst = Math.max(rst, now_c);
            return;
        }
        chkbest(depth+1,now_v+v[depth],now_c+c[depth]);
        chkbest(depth,now_v,now_c);
    }
}
/*
1
4 5
1 2
3 2
4 4
2 3

 */