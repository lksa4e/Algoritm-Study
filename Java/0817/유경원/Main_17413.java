import java.io.*;

public class Main_17413 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                int j = str.indexOf('>', i);
                sb.append(str.substring(i, j + 1));
                i = j;
            }
            else {
                int j = str.indexOf('<', i);
                if (j == -1) j = str.length();
                
                String sub = str.substring(i, j);
                String[] arr = sub.split(" ");
                
                for(int k = 0; k < arr.length; k++) {
                    StringBuilder t = new StringBuilder(arr[k]);
                    sb.append(t.reverse());
                    
                    if (k < arr.length - 1) sb.append(" ");
                }
                i = j - 1;
            }
        }
        System.out.println(sb);
    }
}