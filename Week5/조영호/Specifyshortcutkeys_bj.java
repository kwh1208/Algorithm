/* 문제 */
// 한글 프로그램의 메뉴에는 총 N개의 옵션이 있다. 
// 각 옵션들은 한 개 또는 여러 개의 단어로 옵션의 기능을 설명하여 놓았다. 
// 그리고 우리는 위에서부터 차례대로 각 옵션에 단축키를 의미하는 대표 알파벳을 지정하기로 하였다. 
// 단축키를 지정하는 법은 아래의 순서를 따른다.

// 1. 먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다. 만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정한다.
// 2. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
// 3. 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.
// 4. 위의 규칙을 첫 번째 옵션부터 N번째 옵션까지 차례대로 적용한다.

/* 입력 */
// 첫째 줄에 옵션의 개수 N(1 ≤ N ≤ 30)이 주어진다. 
// 둘째 줄부터 N+1번째 줄까지 각 줄에 옵션을 나타내는 문자열이 입력되는데 하나의 옵션은 5개 이하의 단어로 표현되며, 각 단어 역시 10개 이하의 알파벳으로 표현된다. 
// 단어는 공백 한 칸으로 구분되어져 있다.

/* 출력 */
// N개의 줄에 각 옵션을 출력하는데 단축키로 지정된 알파벳은 좌우에 [] 괄호를 씌워서 표현한다.

// 문자열을 단어 단위로 분리하고 앞 글자들을 먼저 체크합니다. 이 과정에서 단축키 지정이 안됐다면, 다음 과정을 진행하게 됩니다
// 맞는 과정은 앞 글자들 체크 후 -> 왼쪽 단어부터 전부 체크 -> 그 다음 단어 전부 체크 ... 마지막 단어 전부 체크

/* 문제 접근 */
// 가장 먼저 단축키로 지정된 알파벳들의 사용여부를 체크할 배열을 둡니다.
// 이후 각 문자열들을 바로바로 처리합니다. 따로 저장해서 한 번에 출력하지 않았습니다.
// 문자열들은 단어 단위로 분리
// 대문자와 소문자가 섞여있어서, 여기서 처리해도 되지않느냐고 할 수 있지만, 이후 출력할 때 원문 출력이 필요하므로, 원문의 유지가 필요
// 1단계 과정인 각 단어별 앞 글자 체크
// 앞 단어가 사용이 안됐다면, 바로 사용
// 사용이 안됐다면 다음 단어를 확인
// 1단계에서 단축키 지정을 못했다면 2단계를 진행
// 왼쪽 단어부터 확인합니다. 맨 앞글자는 이미 확인했으니 그 다음 단어부터 확인
// 마지막은 출력
// 단어별로 체크 & 위에서 저장한 좌표에 도달하면 괄호와 같이 출력
// 그리고 위에서 단어별로 나누면서 공백이 사라졌기 때문에, 공백을 출력하는 것을 잊으면 안됩니다.

import java.util.*;
import java.io.*;

public class Specifyshortcutkeys_bj {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(bf.readLine());
        // 가장 먼저 단축키로 지정된 알파벳들의 사용여부 체크할 배열
		boolean[] alpha = new boolean[26];
		
		for(int i = 0; i < n; i++) {
            // 문자열을 단어 단위로 분리
			String[] str = bf.readLine().split(" "); 
            // 단축키로 지정될 위치
            int position_x = 0;
			int position_y = 0;
			// 단축키를 지정했는지 여부
            boolean check = false;
            
			for(int j = 0; j < str.length; j++) {
				// 각 단어별 맨 앞글자 체크
                int first = Character.toLowerCase(str[j].charAt(0)) - 'a';
                // 지정된 적 없으며, 해당 글자가 단축키가 아니라면
				if(!check && !alpha[first]) {
                     // 위치 설정
					position_x = j;
					position_y = 0;
                    // 단축키 설정 완료, 해당 알파벳 사용처리
					check = true;
					alpha[first] = true;
                    // break; // 있어도 되고 없어도 되고
				}
			}
			// 앞 글자로는 단축키 지정이 안됐을 때
			if(!check) { 
            // 왼쪽 단어부터 체크
			for(int j = 0; j < str.length; j++) 
					for(int k = 1; k < str[j].length(); k++) { 
            // 앞 글자는 위에서 봤으므로 그 다음부터
			int next = Character.toLowerCase(str[j].charAt(k)) - 'a';
            // 확인할 글자
            // 이번 글자가 단축키가 아닐경우
				if(!alpha[next]) { 
						alpha[next] = true;
                        // 위치 설정
						position_x = j;
						position_y = k;
						check = true;
                        // 설정하고 과정 자체를 종료
						break;
						}
					}
				}

            // 출력과정
			for(int j = 0; j < str.length; j++) {
				for(int k = 0; k < str[j].length(); k++) {
                    // 단축키 지정됐음 -> 이후 좌표가 같음 -> [] 출력
					if(check && j == position_x && k == position_y) {
						bw.write("[" + str[j].charAt(k) + "]");
					} 
                    // 최초 값으로 0 0으로 두어서 이리 했음
                    // -1 -1으로 준다면 check 확인 필요 없음
                    // 다만 시간 늘어나는 거 확인함
    		        // 좌표지정된 단어 이외에는 그냥 출력
                    else bw.write(str[j].charAt(k) + "");
				}
                // 단어 분리과정에서 공백을 지웠으므로 출력
				bw.write(" "); 
			}
			bw.write("\n");
			
		}		

		bw.flush();
		bw.close();
	}

}