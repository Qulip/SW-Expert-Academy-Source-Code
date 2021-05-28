package swea1263;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException{
        File file = new File("E:/IdeaProjects/11WeekStudy/src/input(swea1263).txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node_num = Integer.parseInt(st.nextToken());
            int[][] node_ways = new int[node_num][node_num];

            ArrayList<swea1263.Node>[] list = new ArrayList[node_num+1];
            for(int i = 1; i<=node_num; i++){
                list[i] = new ArrayList<>();
            }

            for(int i = 1; i<=node_num; i++){
                for(int j = 1; j<=node_num; j++){
                    int temp = Integer.parseInt(st.nextToken());
                    if(i==j){
                        temp = 0;
                    }else if(temp==0){
                        temp = INF;
                    }
                    list[i].add(new swea1263.Node(j, temp));
                }
            }
            /*          플로이드 워셜 하지만 너무 느리고 답 틀림
            for(int i=1; i<node_num; i++){
                for(int j=1; j<node_num; j++){
                    for(int l=1; l<node_num; l++){
                        node_ways[j][l] = Math.min(node_ways[j][l], node_ways[j][i]+node_ways[i][l]);
                    }
                }
            }*/
            int rst = Integer.MAX_VALUE;
            for(int i=1; i<=node_num; i++){
                int temp = dijkstra(list, node_num, i);
                rst = Math.min(rst, temp);
            }
            sb.append("#"+tc+" "+rst+"\n");
        }
        System.out.println(sb);
    }

    private static int dijkstra(ArrayList<swea1263.Node>[] list, int node_nums, int start){
        PriorityQueue<swea1263.Node> queue = new PriorityQueue<>();
        boolean[] chk = new boolean[node_nums+1];
        int[] dist = new int[node_nums+1];
        queue.add(new swea1263.Node(start, 0));
        Arrays.fill(dist,INF);
        dist[start] = 0;

        while (!queue.isEmpty()){
            swea1263.Node cur_node = queue.poll();
            int cur = cur_node.end;

            if(chk[cur]){
                continue;
            }
            chk[cur] = true;

            for (swea1263.Node node : list[cur]) {
                if (dist[node.end] > dist[cur] + node.weight) {
                    dist[node.end] = dist[cur] + node.weight;
                    queue.add(new swea1263.Node(node.end, dist[node.end]));
                }
            }
        }
        int rst = 0;
        for(int i=1; i<=node_nums; i++){
            rst = rst + dist[i];
        }
        return rst;
    }
}

class Node implements Comparable<swea1263.Node>{
    int end, weight;

    Node(int end, int weight){
        this.weight = weight;
        this.end = end;
    }

    @Override
    public int compareTo(swea1263.Node o) {
        return this.weight-o.weight;
    }
}
/*
OUTPUT WILL
#1 2
#2 3
#3 25
#4 37
#5 16
#6 11
#7 21
#8 20
#9 715
#10 1449

 */