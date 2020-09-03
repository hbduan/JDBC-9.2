package com.duan;

import com.duan.dao.IUserDao;
import com.duan.domain.QueryVo;
import com.duan.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @描述
 * @创建人 Duanhaibo
 * @创建时间 2020/9/2
 * @修改人和其它信息
 */
public class UserDaoCurdTest {
        private InputStream in ;
        private SqlSessionFactory factory;
        private SqlSession session;
        private IUserDao userDao;

        @Before
        public void setUp() throws Exception {
// 1.读取配置文件
            in = Resources.getResourceAsStream("com.duan/SqlMapConfig.xml");
// 2.创建构建者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
// 3.创建SqlSession工厂对象
            factory = builder.build(in);
// 4.创建SqlSession对象
            session = factory.openSession();
// 5.创建Dao的代理对象
            userDao = session.getMapper(IUserDao.class);
        }


    @After
    public void tearDown() throws Exception {
        session.commit();
//7.释放资源
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindOne() {
// 6.执行操作
        User user = userDao.findById(41);
        System.out.println(user);
        assert user.getUsername().equals("张三");
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("华泰");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());
// 1.执行保存方法
        int id = userDao.saveUser(user);
// 2. 验证保存结果
        User savedUser = userDao.findById(id);
        assert user.getUsername().equals("华泰");
    }

    @Test
    public void testUpdateUser() {
        int id = 46;
//1.根据id查询
        User user = userDao.findById(id);
//2.更新操作
        user.setAddress("北京市大丰区");
        int res = userDao.updateUser(user);
// 3. 验证保存结果
        User savedUser = userDao.findById(id);
        assert user.getAddress().equals("北京市大丰区");
    }


    @Test
    public void testDeleteUser() {
        int id = 46;
//1.根据id查询
        int status = userDao.deleteUser(id);
//2.更新操作

        System.out.println(status);
//        assert user.getUsername().equals("张三");
        User savedUser = userDao.findById(id);
        assert savedUser==null;
//                equals("北京市大丰区");
    }

    @Test
    public void testFindByName() {
// 6.执行操作
        String name="张三";
        List<User> users = userDao.findByName(name);
        for(User u:users){
            System.out.println(u.toString());
        }

//        assert user.getUsername().equals("张三");
    }

    @Test
    public void testCount(){
            int total=userDao.count();
            assert total==10;

    }

    @Test
    public void testFindByQueryVo(){
        QueryVo vo=new QueryVo();
        vo.setName("");
        vo.setAddress("北京大丰区");
    }

}
