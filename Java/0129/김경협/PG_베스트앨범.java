import java.util.*;

/*
 * ���α׷��ӽ� �ؽ� - ����Ʈ�ٹ�
 * 
 *  ������ �뷡 �帣 �� �� ���� ����ƴ����� ���� �ߴ�.
 *  
 *  �׸��� �뷡 ���� Inner class Song�� ���ؼ� ��� Ƚ���� ���� index�� �����ؼ�
 *  ��� Ƚ�� ������������ (������ ���� index�� ���� ��) Priority Queue�� �־����.
 *  �׸��� �� ���� ���� �帣������ �˾ƾ� �ϱ� ������ ���������� Map<String, PQ>�� ���� ������� �־���.
 *  
 *  ���� ��� Ƚ���� ���� �帣���� 2� ���� ����� �־����. �帣���� ���� �� �� �ִ� ��쿡�� �� � �־���.
 *  
 */

public class PG_����Ʈ�ٹ� {
	static class Song implements Comparable<Song> {
        int plays;
        int originIndex;
        
        Song(int plays, int originIndex) {
            this.plays = plays;
            this.originIndex = originIndex;
        }
        
        @Override
        public int compareTo(Song s) {	// PQ�� ���� compareTo �Լ�, ��� Ƚ�� �������� -> ������ ���� �ε��� ��������
            return this.plays == s.plays ? (this.originIndex - s.originIndex) : s.plays - this.plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> genrePlays = new HashMap<>();				// �帣 �� ��� Ƚ��
        Map<String, PriorityQueue<Song>> songsOrder = new HashMap<>();	// �帣 �� �뷡 ����
       
        int size = genres.length;
        
        // �帣 �� ��� Ƚ�� ����
        for(int i = 0; i < size; i++) {
            String curr = genres[i];
            if(genrePlays.get(curr) == null)
                genrePlays.put(curr, plays[i]);
            else
                genrePlays.put(curr, genrePlays.get(curr) + plays[i]);
            
            // �뷡 �� ������� ����
            if(songsOrder.get(curr) == null)
                songsOrder.put(curr, new PriorityQueue<Song>());
            songsOrder.get(curr).offer(new Song(plays[i], i));
        }
                
        // �帣 �� �ټ����
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(genrePlays.entrySet());
        entryList.sort((a, b)->{	// map�� value ������ �������� �ϱ�
            return b.getValue() - a.getValue();
        });
        
        List<Integer> answerList = new ArrayList<>(); // ��� �� ������ ���� list
        
        // entry set���� ū ������� 2���� �� ����. �� � ������ �ϳ���
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
