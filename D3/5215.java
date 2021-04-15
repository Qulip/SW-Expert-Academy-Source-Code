import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int n,l,rst;
    static int[] kcal, grade;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            kcal = new int[n];
            grade = new int[n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                grade[i] = Integer.parseInt(st.nextToken());
                kcal[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0,0, 0);
            sb.append("#"+tc+" "+rst+"\n");
        }
        System.out.println(sb);
    }
    static void dfs(int now_kcal,int now_grade, int depth){
        for(int i = depth; i<kcal.length; i++){
            int temp = now_kcal + kcal[i];
            if(temp < l){
                if(rst<now_grade+grade[i]){
                    rst = now_grade+grade[i];
                }
                dfs(temp,now_grade+grade[i], i+ 1);
            }
            dfs(now_kcal,now_grade,i+1);
        }
    }
}
/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400

 */
