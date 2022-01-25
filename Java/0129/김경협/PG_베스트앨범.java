import java.util.*;

/*
 * 프로그래머스 해시 - 베스트앨범
 * 
 *  맵으로 노래 장르 별 몇 번씩 재생됐는지를 집계 했다.
 *  
 *  그리고 노래 별로 Inner class Song을 통해서 재생 횟수와 원래 index를 저장해서
 *  재생 횟수 내림차순으로 (같으면 원래 index가 작은 순) Priority Queue에 넣어줬다.
 *  그리고 각 곡이 무슨 장르인지도 알아야 하기 때문에 최종적으로 Map<String, PQ>와 같은 방식으로 넣었다.
 *  
 *  가장 재생 횟수가 높은 장르부터 2곡씩 빼서 결과에 넣어줬다. 장르에서 곡이 한 곡 있는 경우에는 한 곡만 넣었다.
 *  
 */

public class PG_베스트앨범 {
	static class Song implements Comparable<Song> {
        int plays;
        int originIndex;
        
        Song(int plays, int originIndex) {
            this.plays = plays;
            this.originIndex = originIndex;
        }
        
        @Override
        public int compareTo(Song s) {	// PQ를 위한 compareTo 함수, 재생 횟수 내림차순 -> 같으면 원래 인덱스 오름차순
            return this.plays == s.plays ? (this.originIndex - s.originIndex) : s.plays - this.plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> genrePlays = new HashMap<>();				// 장르 별 재생 횟수
        Map<String, PriorityQueue<Song>> songsOrder = new HashMap<>();	// 장르 별 노래 저장
       
        int size = genres.length;
        
        // 장르 별 재생 횟수 집계
        for(int i = 0; i < size; i++) {
            String curr = genres[i];
            if(genrePlays.get(curr) == null)
                genrePlays.put(curr, plays[i]);
            else
                genrePlays.put(curr, genrePlays.get(curr) + plays[i]);
            
            // 노래 별 순서대로 저장
            if(songsOrder.get(curr) == null)
                songsOrder.put(curr, new PriorityQueue<Song>());
            songsOrder.get(curr).offer(new Song(plays[i], i));
        }
                
        // 장르 별 줄세우기
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(genrePlays.entrySet());
        entryList.sort((a, b)->{	// map의 value 값으로 내림차순 하기
            return b.getValue() - a.getValue();
        });
        
        List<Integer> answerList = new ArrayList<>(); // 결과 값 저장을 위한 list
        
        // entry set에서 큰 순서대로 2개씩 곡 빼기. 한 곡만 있으면 하나만
        for(Map.Entry<String, Integer> entry : entryList) {
            answerList.add(songsOrder.get(entry.getKey()).poll().originIndex);
            if(!songsOrder.get(entry.getKey()).isEmpty())
                answerList.add(songsOrder.get(entry.getKey()).poll().originIndex);
        }
        
        int[] answer = new int[answerList.size()];
        size = answerList.size();
        for(int i = 0; i < size; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}
