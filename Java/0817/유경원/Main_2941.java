import java.io.*;

public class Main_2941 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        String[] cro = {"dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="};
        
        for(String c : cro) str = str.replace(c,"a");
        
        System.out.println(str.length());
    }
}