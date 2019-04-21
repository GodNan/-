import com.css.bdpfnew.BdpfnewApplication;
import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import com.css.bdpfnew.repository.SupermanRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BdpfnewApplication.class)
public class SupermanRepositoryTest {

    @Autowired
    private SupermanRepository supermanRepository;

    @Test
    public void baseTest() throws Exception {
        Pageable pageable = new PageRequest(0,10);
//        Superman man = new Superman();
//        man.setUserName("Jay");
//        man.setAddress("123456");
//        man.setBirthDay(new Date());
//        supermanRepository.save(man);
//        Superman superman = supermanRepository.findById("8a15b87463710bd20163710c00b80000");
//        System.out.println(superman);
        supermanRepository.method();
        Page<Superman> page = supermanRepository.findAll(pageable);
        String ids = "8a15b87463710bd20163710c00b80000,";
        String[] split = ids.split(",");
        List<Superman> repository = supermanRepository.findByIdIn(split);
        System.out.println(repository);
    }
}