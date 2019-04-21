import com.css.bdpfnew.repository.AreaRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/7/6 11:23
 * @Version: 1.0
 * @Description:
 */
//@RunWith(SpringRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringBootTest(classes = BdpfnewApplication.class)
public class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void baseTest() throws Exception {
        List list = areaRepository.lableValueAreaByParent("11");
        System.out.println(list);
    }

    public static void main(String[] args) {
        String cityid = "11";

        String areaCode = "110102123123";

        System.out.println(Double.valueOf(cityid)/10000000000L);
    }

}
