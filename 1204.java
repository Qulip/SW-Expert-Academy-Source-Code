import java.io.*;
import java.util.StringTokenizer;

public class Solution{
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++){
            int[] time = new int[101];
            int num = Integer.parseInt(input.readLine());
            for(int i=0; i<3; i++){
                StringTokenizer st = new StringTokenizer(input.readLine());
                while(st.hasMoreTokens()){
                    int temp = Integer.parseInt(st.nextToken());
                    time[temp]++;
                }
            }
            System.out.println(tc);
            int result=0;
            num=0;
            for(int i=0;i<101; i++){
                if(num<time[i]){
                    num=time[i];
                    result=i;
                }
            }
            sb.append("#"+tc+" "+result);
        }
        System.out.println(sb);
    }
}
