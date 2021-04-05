import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            int n = Integer.parseInt(br.readLine());
            int[] prices = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                prices[i] = Integer.parseInt(st.nextToken());
            }
            int nums = 0;
            int money = 0;
            int profit = 0;
            int i = n-1;
            while(i>0){
                System.out.println(i);
                int iprice = prices[i];
                int iday = i;
                if(iprice<=prices[i-1]){
                    i--;
                    continue;
                }
                while(iprice>prices[i-1]){
                    i--;
                    if(i<=0){
                        break;
                    }
                }
                if(i>=0){
                    for(int j=i; j<iday; j++){
                        money=money+prices[i];
                        nums++;
                    }
                    profit = profit+iprice*nums - money;
                }
                nums = 0;
                money = 0;
            }
            sb.append("#"+tc+" "+profit+"\ns");
        }
        System.out.println(sb);
    }
}
