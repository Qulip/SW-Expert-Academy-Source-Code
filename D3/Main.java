package floyed_washal;  //자바 패키지 이름 설정

import java.util.*;     //필요 패키지 선언
import java.io.*;

public class Main {
    static int INF = 99999999;  //노드의 거리를 비교하기 위하여 무한대의 크기를 설정
    static int[][] p;           //노드가 이동간 거치는 노드를 체크하기 위한 배열
    static LinkedHashSet<Integer> lh;   //최단경로의 노드값들을 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//입력 설정
        System.out.print("노드의 갯수를 입력해 주세요 : ");
        int node_nums = Integer.parseInt(br.readLine());        //노드의 갯수 결정
        int[][] nodes = new int[node_nums+1][node_nums+1];      //노드의 수+1만큼 2차원 배열 선언
        p = new int[node_nums+1][node_nums+1];                  //위와 동일

        for(int i=1; i<=node_nums; i++){                        //연결되지 않은 노드는 무한대로 설정하기 위하여
            Arrays.fill(nodes[i], INF);                         //미리 자신에서 자신으로 가는것 말고는 모두 무한대 설정
            nodes[i][i]=0;
        }

        System.out.print("몇개의 길을 입력할지 정해주세요 : ");
        int way_nums = Integer.parseInt(br.readLine());         //연결할 거리의 갯수 입력

        System.out.println("시작, 도착, 거리 순으로 입력해주세요");
        for(int i=1; i<=way_nums; i++){                         //위의 수만큼 2차원 배열에 입력
            System.out.print(i+". : ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            int way = Integer.parseInt(st.nextToken());
            nodes[start][finish] = way;
        }

        int[][] dist = floyd_warshal(nodes);                     //플로이드 워셜을 통하여 노드간의 거리 설정

        while(true){                                            //종료전까지 출발, 도착지를 검색할수 있게 While문 사용
            lh = new LinkedHashSet<>();                         //매 loop마다 새로 저장
            StringBuilder sb = new StringBuilder();             //출력하기 위한 String만들기

            System.out.print("출발지와 도착지를 입력해주세요(종료 : -1) : ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());       //첫 입력이 -1일경우 while문 탈출
            if(start==-1) {
                break;
            }
            int finish = Integer.parseInt(st.nextToken());

            path(start,finish);                                 //어떠한 순서로 갈지 결정

            Iterator<Integer> iter = lh.iterator();             //Set에 저장된 값을 모두 String으로 합치기
            while (iter.hasNext()){
                sb.append(iter.next()+" ");
            }
            sb.append("순으로 이동합니다.");
            System.out.println(sb);
        }
    }

    static int[][] floyd_warshal(int[][] nodes){     //플로이드 워셜알고리즘
        for(int i=1; i<nodes.length; i++) {
            for(int j=1; j<nodes.length; j++) {
                for(int k=1; k<nodes.length; k++) {
                    if(nodes[j][k] > nodes[j][i] + nodes[i][k]) {
                        p[j][k] = i;
                        nodes[j][k] = nodes[j][i] + nodes[i][k];
                    }
                }
            }
        }
        return nodes;
    }

    static void path(int start, int finish){    //플로이드 워셜 알고리즘에 따른 경로확인
        lh.add(start);                          //시작점은 바로 저장
        int tmp = p[start][finish];
        if(tmp!=0){                             //지나간 지점이 존재한다면
            path(start, tmp);                   //시작점부터 지나간 지점까지 지나간점이 있는지 체크
            path(tmp, finish);                  //지나간 지점부터 도착점까지 지나간점이 있는지 체크
        }
        lh.add(finish);                         //저장된 순서에 따라 출력 되므로 도착점은 나중에 저장
    }

}

/*
예시 입력
5
10
1 2 1
2 1 9
2 3 3
2 4 2
1 4 1
1 5 5
5 1 3
4 5 3
4 3 2
3 4 4
5 3

 */