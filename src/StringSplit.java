import java.util.HashMap;
import java.util.Map;

public class StringSplit {
    public static Map<String, String>  parseKV(String input) {
// TODO:
        Map<String,String> res = new HashMap<>();
        int index = 0;
        while(index<input.length()){
            int start = index;
            int indexE=-1;
            int indexS=-1;
            //find the index
            while(index<input.length() && input.charAt(index)!=';'){
                index++;
            }
            //find the index of =
            for (int i=start;i<index;i++){
                if(input.charAt(i)=='=') indexE=i;
//if(sb.charAt(i)==""") indexS=i;
            }
            //if there are some error
            if(indexE==-1) index++;
            //if not, put them in map
            else{
                StringBuilder sbKey = new StringBuilder();
                for(int j=start;j<indexE;j++){
                    sbKey.append(input.charAt(j));
                }
                StringBuilder sbValue= new StringBuilder();
                for(int p=indexE+1;p<index;p++){
                    if(input.charAt(p)=='"') continue;
                else
                sbValue.append(input.charAt(p));
                }
            res.put(sbKey.toString(),sbValue.toString());
            }
        }
        return res;
    }



    public static void main(String[] args) {
            String s = new String("k1=v1;k2=v2;k3=v3;k4=v41,v42");
            System.out.println(StringSplit.parseKV(s));
        }

}
