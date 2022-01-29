import java.io.*;
import java.util.*;

/**
 * [0126] 프로그래머스 해시 베스트앨범
 * 해시맵, 정렬
 * 
 * sol)
 * 해시맵에 장르 문자열을 key로, 총 재생횟수, 재생횟수 1위 번호, 재생횟수 2위 번호를 담은 int형 배열을 value로 담은 뒤,
 * Map.Entry 타입 리스트에 담아 value를 기준으로 정렬한다
 *
 */

public class PGS_해시_베스트앨범 {
	
    int genreSize, playSize, cnt;
    Map<String, int[]> bestAlbum = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        playSize = genres.length;
        // 각 장르 별 최다 재생횟수 2 곡을 저장
        comparePlays(genres, plays);
        // 장르 총 재생횟수를 기준으로 맵을 정렬한 뒤 출력
        int[] answer = selectBestAlbum();
        return answer;
    }
    
    public void comparePlays(String[] genres, int[] plays) {
        for (int i=0; i<playSize; i++) {
            String genre = genres[i];
            int play = plays[i];
            int[] genrePlay = bestAlbum.get(genre);
            
            if (genrePlay != null) {             // 등장한 적 있는 장르
                genrePlay[0] += play;            // 총 재생횟수
                
                int first = genrePlay[1];
                int second = genrePlay[2];
                
                if (second == -1) cnt++;         // 정답 배열의 길이를 카운트하기 위한 변수
                
                if (second == -1 || plays[second]<play) {    // 두번째 최다 재생횟수가 저장되지 않은 장르이거나 두번째 최다 재생횟수보다 재생횟수 많으면
                    if (plays[first]<play){                  // 첫번째 최다 재생횟수보다도 많으면
                        genrePlay[2] = first;                // 재생횟수 1위로
                        genrePlay[1] = i;                    // 1위에 있던음악은 2위로
                    } else {
                        genrePlay[2] = i;                    // 첫번째 최다 재생횟수보다는 작으면 2위로
                    }
                }
                bestAlbum.put(genre, genrePlay);
            } else {                             // 처음 등장하는 장르
                int[] v = {play, i, -1};         // 총 재생횟수, 현재 음악, 빈 값으로 배열 초기화하여 value로 설정
                bestAlbum.put(genre, v);
                cnt++;
            }
        }
    }
    
    public int[] selectBestAlbum() {
    	// 장르의 총 재생횟수로 맵 정렬한 뒤 리스트에 담음
        List<Map.Entry<String, int[]>> album = new LinkedList<>(bestAlbum.entrySet());
        Collections.sort(album, (a, b) -> b.getValue()[0] - a.getValue()[0]);
        
        int[] result = new int[cnt];
        
        int j=0;
        for (Map.Entry<String, int[]> curGenre : album) {
            int[] value = curGenre.getValue();
            
            for (int i=1; i<=2; i++) {
                int idx = value[i];                    // 각 장르의 재생횟수 1위와 2위에 대해
                if (idx != -1) result[j++] = idx;      // 빈값이 아니면 정답 배열로 출력
            }
        }
        
        return result;
    }
}