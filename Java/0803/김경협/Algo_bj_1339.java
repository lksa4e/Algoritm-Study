package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algo_bj_1339 {
	static List<char[]> words = new ArrayList<>();
	static List<int[]> wordCounts = new ArrayList<>();
	static int longWordLen = Integer.MIN_VALUE;
	
	static int getNum(int[] num) {
		int i = 0;
		for(int n = num.length - 1; n >= 0; n--){
			if(num[n] == 0)
				i *= 10;
			else {
				i *= 10;
				i += num[n];
			}
		}
		
		return i;
	}
	
	static void count(char[] str) {
		
		for (int i = str.length - 1; i >= 0; i--) {
			if(str[i] == ' ') continue;
			char target = str[i];
			// i번 index가 10^i을 의미함
			int[] wordCount = new int[longWordLen];
			
			
			// 모든 단어 탐색하면서 현재 target이 된 문자열을 찾는다.
			for(char[] word : words) {
				for (int j = word.length - 1; j >= 0; j--) {
					if(word[j] == ' ')
						continue;
					else if(word[j] == target) {
						// 찾고 나면 찾은 문자열에 해당하는 자릿수를 count해서 wordCount를 증가시키고 
						// 그 자리를 빈칸으로 만든다.
						wordCount[word.length - (j + 1)]++;
						word[j] = ' ';
					}
				}
			}
			
			wordCounts.add(wordCount);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int wordAmount = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < wordAmount; i++) {
			words.add(br.readLine().toCharArray());
			longWordLen = Math.max(longWordLen, words.get(i).length);
		}
		
		// 단어별로 단어 맨 뒤에서부터 읽으면서 알파벳 카운팅
		for(char[] word : words)
			count(word);
		
		// wordCount의 i 번째 index는 10의 i승을 의미한다
		// int로 다시 받아옴. ex) 0 0 2 0 1 이렇게 저장되어 있으면 10200이 되도록
		Integer[] results = new Integer[wordCounts.size()];
		for (int i = 0; i < wordCounts.size(); i++) {
			results[i] = getNum(wordCounts.get(i));
		}
		
		// arrays의 sort는 primitve 타입의 내림차순을 지원하지 않아서 
		// Integer 타입으로 results를 선언한후 sort 내림차순 해줌
		Arrays.sort(results, Collections.reverseOrder());	
		
		
		// 가장 큰 수부터 9 곱하고, 8곱하고 반복
		int sum = 0, multiplier = 9;
		for(int n : results)
			sum += n * multiplier--;
		
		System.out.println(sum);
	}

}
