public class Part2 {

    public int howMany(String a ,String b){
        int howmany = 0;
        int start  = 0;
        int len = a.length();
        while (true){
            int pos = b.indexOf(a,start);
           if(pos != -1){
              howmany++;
              start = pos + len;
           }
           else{ break;}
        }
        return howmany;
        }

    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            System.out.println(index);
        }
    }
    public void test() {
        //findAbc("abcd");
        findAbc("abcabcabcabca");
    }

    public void testHowMany() {
         System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
         System.out.println(howMany("AA", "ATAAAA")); }

    public static void main (String[] args) {
        Part2 pt = new Part2();
        //pt.testHowMany();
        pt.test();
    }



    }
