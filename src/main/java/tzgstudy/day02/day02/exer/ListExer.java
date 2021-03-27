package tzgstudy.day02.day02.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListExer {

    /*
    区分List中remove（int index） 和remove（Object obj）
     */
    @Test
    public void testListRemove(){

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        updateObjectList(list);
        System.out.println("list = " + list);

    }

    /**
     * remove的是索引
     * @param list
     */
    private void updateIndexList(List list){
        list.remove(2);
    }

    /**
     * remove的在集合中等于该值的数值 当有重复数据时移除第一个
     * @param list
     */
    private void updateObjectList(List list){
        list.remove(new Integer(2));
    }

}
