package lgystudy.day2_06_20210327.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListExerLgy {
    // 分区List中remove（int index）和remove（Object obj)
    @Test
    public void testListRemove(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);
    }
    private void updateList(List list) {
//        list.remove(2);
        list.remove(new Integer(2));
    }
}