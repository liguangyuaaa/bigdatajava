package lgystudy.day2_08_202103_29_30_31.exer1;

import day2_08_202103_29_30_31.src.com.atguigu.exer1.DAO;
import day2_08_202103_29_30_31.src.com.atguigu.exer1.User;
import lgystudy.day2_07_20210328.java.UserLgy;

import java.util.List;

public class DAOTestLgy {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();
        dao.save("1001",new User(1001,34,"周杰伦"));
        dao.save("1002",new User(1002,20,"昆凌"));
        dao.save("1003",new User(1003,25,"蔡依林"));

        dao.update("1003",new User(1003,30,"方文山"));
        dao.delete("1002");

        List<User> list = dao.list();
        list.forEach(System.out::println);

    }
}
