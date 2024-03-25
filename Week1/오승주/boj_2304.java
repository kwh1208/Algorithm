import java.io.*;
import java.util.*;


class Pillar {
    int location;
    int height;

    public Pillar(int location, int height) {
        this.location = location;
        this.height = height;
    }
}

class PillarComparator implements Comparator<Pillar> {
    @Override
    public int compare(Pillar p1, Pillar p2) {
        return p1.location - p2.location;
    }
}

public class Solution_for_2304 {
    static ArrayList<Pillar> pillars = new ArrayList<>();

    static int maxHeight = 0;
    static int midIndex = 0;

    static int areaSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 기둥들을 입력받고 높이를 기준으로 오름차순 정렬함
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            pillars.add(new Pillar(L, H));
        }
        pillars.sort(new PillarComparator());

        // 가장 높은 기둥의 높이와 순서를 구함
        for (int i = 0; i < pillars.size(); i++) {
            if (maxHeight < pillars.get(i).height) {
                maxHeight = pillars.get(i).height;
                midIndex = i;
            }
        }
        //System.out.printf("midIndex: %d \n", midIndex);
        //System.out.printf("maxHeight: %d \n", maxHeight);


        // 앞에서부터 midIndex까지
        int pillarHeight = 0;
        int pillarIndex = 0;
        for (int i = 0; i <= midIndex; i++) {

            // 높거나 같은 기둥이 나온 경우
            if (pillarHeight <= pillars.get(i).height) {
                int width = pillars.get(i).location - pillars.get(pillarIndex).location;

                areaSum += (width * pillarHeight);

                pillarHeight = pillars.get(i).height;
                pillarIndex = i;
            }
        }
        //System.out.println(areaSum);

        // 가장 높은 기둥의 넓이를 더해줌
        areaSum += maxHeight;
        //System.out.println(areaSum);

        
        // 뒤에서부터 midIndex까지
        pillarHeight = 0;
        pillarIndex = N-1;
        for (int i = pillarIndex; midIndex <= i; i--) {
            // 더 높은 기둥이 나온 경우
            if (pillarHeight <= pillars.get(i).height) {
                int width = pillars.get(pillarIndex).location - pillars.get(i).location;

                areaSum += (width * pillarHeight);

                pillarHeight = pillars.get(i).height;
                pillarIndex = i;
            }
        }

        System.out.println(areaSum);
    }
}
