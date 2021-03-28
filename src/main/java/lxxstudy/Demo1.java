package lxxstudy;

import com.google.common.base.Supplier;
import org.junit.Test;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Consumer;

public class Demo1 {
    @Test
    public void stringReplace() {
        String str = "  We we how olo Are  asd  asd 213 asd Happy hello.";
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
        int[] list3={1,2,3,4,5};
        for (int i = 0; i <5 ; i++) {
            list1.add(i);
            list2.add(i);
        }
        for (int i = 0; i < list3.length; i++) {

        }

        for (Object o : list2) {

        }
        System.out.println(list1);
        UpdateList1(list1);
        System.out.println(list1);
        System.out.println("============+====");
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

    @Test
    public void hashsetTest(){
            /*
    一、Set：存储无序的、不可重复的数据
    以HashSet为例说明：
    1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。

    2. 不可重复性：保证添加的元素按照equals()判断时，不能返回true.即：相同的元素只能添加一个。

    二、添加元素的过程：以HashSet为例：
        我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
        此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
        数组此位置上是否已经有元素：
            如果此位置上没有其他元素，则元素a添加成功。 --->情况1
            如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
                如果hash值不相同，则元素a添加成功。--->情况2
                如果hash值相同，进而需要调用元素a所在类的equals()方法：
                       equals()返回true,元素a添加失败
                       equals()返回false,则元素a添加成功。--->情况2

        对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。
        jdk 7 :元素a放到数组中，指向原来的元素。
        jdk 8 :原来的元素在数组中，指向元素a
        总结：七上八下

        HashSet底层：数组+链表的结构。

     */

        HashSet<Object> set = new HashSet<>();
        set.add("123");
        System.out.println(set);
        set.add(222);
        set.add(222);
        set.add(2.22);
        set.add("sdf");
        set.add(new User("Tom",12));
        set.add(new User());
        set.add(444);
        System.out.println(set);
        System.out.println(set.size());
        Iterator<Object> iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for(Object it:set){
            System.out.println(it);
        }

        //set.fori
        for (int i = 0; i < set.size(); i++) {
            //System.out.println(iterator.next());
        }
    }
    //LinkedHashSet的使用
    //LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个
    //数据和后一个数据。
    //优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet
    @Test
    public void linkhashset(){
        LinkedHashSet<Object> set = new LinkedHashSet<>();
        set.add(111);
        set.add(111);
        set.add(new User());
        set.add(new User("123",222));
        Iterator<Object> iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (Object o : set) {
            System.out.println("for加强"+o);
        }
    }
    /*
    1.向TreeSet中添加的数据，要求是相同类的对象。
    2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）
    3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
    4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
     */

    @Test
    public void treesettest(){
        TreeSet<Object> set = new TreeSet<>();
        //set.add(123);
        //set.add(456);
        //set.add("123");    失败：不能添加不同类的对象
        set.add(new User("name",88));
        set.add(new User("name",888));
        set.add(new User("name",888));
        set.add(new User("name2",999));
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void listtest(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(123);
        list.add(123);
        list.add("111");
        list.add(new User());
        list.add(new User("asd",90));
        list.add(new String("wahaha"));
        list.add(false);
        Iterator<Object> iterator = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(iterator.next());
        }
        int[] arr = {1,2,3,4,5};
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("+++++");
        Iterator<Object> iterator1 = list.iterator();
        while (iterator1.hasNext()){
            System.out.println("while:"+iterator1.next());
        }
        String[] strs = new String[]{"ddd","ccc","bbb","aaa"};
    }
    @Test
    public void forEach(){
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(456);
        arrayList.add(789);
        int[] ints = new int[]{1,2,3,4};
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println("while"+"----"+iterator.next());
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("for"+"----"+ arrayList.get(i));
        }
        for (Integer integer : arrayList) {
            System.out.println("for增强"+"----"+integer);
        }
        int i = arrayList.indexOf(3);
        int i1 = arrayList.indexOf(456);
        int i2 = arrayList.lastIndexOf(123);
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
        arrayList.set(2,999);
        arrayList.remove(3);
        arrayList.add(888);
        List<Integer> arrayList2 = arrayList.subList(1, 2);
        arrayList.add(2,222);
        List<Integer> list_a = Arrays.asList(777, 3);
        arrayList.addAll(list_a);
        System.out.println(arrayList);
        //System.out.println(arrayList2);
        arrayList.forEach(System.out::println);
        arrayList.forEach(o -> System.out.println(o));
    }

    // Java 匿名类、lambda表达式、方法引用 的使用
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        printList(list);
        List<String> list2 = new ArrayList<>();
        list2.add("string");
        printList2(list2);
        List<Boolean> list3 = new ArrayList<>();
        list3.add(true);
        printList3(list3);
    }

    /* printList() 的作用是使用 forEach() 方法打印List内容 */

    // 使用匿名类
    private static void printList(List<?> list) {
        list.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
    }

    // 使用lambda表达式替代匿名类的使用
    private static void printList2(List<?> list) {
        list.forEach(o -> System.out.println(o));
    }

    // 对于已有的方法，可使用方法引用，这里直接引用println() 静态方法 打印内容
    private static void printList3(List<?> list) {
        list.forEach(System.out::println);
    }
}

