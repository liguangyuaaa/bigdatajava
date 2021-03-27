package lxxstudy;

import org.junit.Test;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;

public class Demo1 {
    @Test
    public void stringReplace() {
        String str = "  We we how olo Are  asd  asd 213 asd Happy.";
        String[] words = str.split(" ");

        System.out.println(words.length);
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if(StringUtils.isNotBlank(word)){
                if (word.contains(".")) {
                    String result = word.replace(".", "。");
                    sb.append(result);
                } else {
                    sb.append(word);
                    sb.append("%20");
                }
            }
        }

        System.out.println(sb.toString());
    }

    @Test
    public void arrayDemo01(){
        //数组定长
        int[] arraya =new int[10];
        double[] arrayb = new double[10];
        String[] arrayc =new String[10];
        int[] arrayd ={1,2,3};
        String str = "hello world how are you!";
        String[] words = str.split(" ");
        //数组名称.fori
        System.out.println(words);
    }
    /*
    区分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testlistremove(){
        ArrayList<Object> list1 = new ArrayList<>();
        ArrayList<Object> list2 = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list1.add(i);
            list2.add(i);
        }
        System.out.println(list1);
        UpdateList1(list1);
        System.out.println(list1);
        System.out.println("================");
        System.out.println(list2);
        UpdateList2(list2);
        System.out.println(list2);
    }
    private void UpdateList1(ArrayList<Object> list) {
        System.out.println(new Integer(2));
        System.out.println(new Integer(5));
        list.remove(new Integer(2));
        list.remove(new Integer(5));
    }
    private void UpdateList2(ArrayList<Object> list) {
        list.remove(2);
    }
}

