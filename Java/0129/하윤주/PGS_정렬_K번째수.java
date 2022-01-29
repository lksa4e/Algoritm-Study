import java.io.*;
import java.util.*;

/**
 * 
 * [0129] PGS 정렬 H-Index
 * 정렬
 * 
 * 착한 정렬 문제
 *
 */

public class PGS_정렬_K번째수 {
    int[] arr;
    
    public int[] solution(int[] array, int[][] commands) {
        arr = array;
        int size = commands.length;
        int[] answer = new int[size];
        
        for (int i=0; i<size; i++) {
            int[] command = commands[i];
            // 배열을 자른 뒤 정렬
            int[] slicedArray = sliceAndSortArray(command);
            // k번째 수 고르기
            answer[i] = pickNumber(slicedArray, command[2]);
        }
        return answer;
    }
    
    public int[] sliceAndSortArray(int[] command) {
    	// copyOfRange() 메서드로 특정 구간 슬라이싱
        int[] sliced = Arrays.copyOfRange(arr, command[0]-1, command[1]);
        // 정렬
        Arrays.sort(sliced);
        return sliced;
    }
    
    // K번째 수 고르기
    public int pickNumber(int[] sliced, int idx) {
        return sliced[idx-1];
    }
}