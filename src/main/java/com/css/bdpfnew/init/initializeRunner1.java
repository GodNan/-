package com.css.bdpfnew.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.css.bdpfnew.model.dto.DtoDCodeType;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.model.entity.bdpfnew.DCodeType;
import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.service.CodeAreaService;
import com.css.bdpfnew.service.CodeAreaService2;
import com.css.bdpfnew.service.DCodeTypeService;
import com.css.bdpfnew.service.SupermanService;
import com.css.bdpfnew.service.SysPermissionService;
import com.css.bdpfnew.service.impl.SysPermissionServiceImpl;






@Component
@Order(1)
public class initializeRunner1 implements CommandLineRunner{
    @Resource
    CodeAreaService codeAreaService;
	/*@Autowired
    private StringRedisTemplate stringRedisTemplate;
   
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
	private CodeAreaService2 codeAreaService2; 
    
    @Autowired
    private SysPermissionService sysPermissionService;
   @Autowired
    private SupermanService ssss;*/
    
	/*@Autowired
	private DCodeTypeService dCodeTypeService;*/

	@Override
    public void run(String... args) throws Exception {
		 //List<CodeAreaEntity> codeareas= codeAreaService.getCodeAreaList();
		 //System.out.println(codeareas.get(0).getAreaName());
     /*   System.out.println("The OrderRunner1 start to initialize ...");
        DCodeType d=dCodeTypeService.findByUuid("6e868870bd2e45e2980619c9acdaa8dd");
        System.out.println(d.getRemark());
        DCodeType d2=dCodeTypeService.findByUuid("6e868870bd2e45e2980619c9acdaa8dd");
        System.out.println(d2.getRemark());*/
      //  d2.setRemark("测试1");
     //   System.out.println(d2.toString());
   //     DtoDCodeType c=new DtoDCodeType();
    //   BeanUtils.copyProperties(c, d2);
      
    //    System.out.println(c.toString());
    //    dCodeTypeService.update(c);
     //   List<DCodeType> l =dCodeTypeService.findAll();
     //   List<DCodeType> l2 =dCodeTypeService.findAll();
    //    System.out.println(l2.get(0));
        
        
        
        
        
        
        /*     String key="com.css.bdpfnew.service.impl.CodeAreaServiceImplfindCodeAreaByAreaCode110105025029";
        stringRedisTemplate.opsForValue().set("bbb", "222",30, TimeUnit.SECONDS);
      
      String a=ssss.findByUuid("8a15b87463b445530163b446a96f0000").toString();
        String a2=ssss.findByUuid("8a15b87463b445530163b446a96f0000").toString();
        
     
        
       SysPermission q=sysPermissionService.findByUuid("8a15b87463b445530163b446a96f0000");
        System.out.println(q.getMenuName());
        SysPermission q2=sysPermissionService.findByUuid("8a15b87463b445530163b446a96f0000");
        System.out.println(q2.getMenuName());*/
        
        /*   CodeArea c=codeAreaService.findCodeAreaByAreaCode("110105025035");
      System.out.println("--------");
      CodeArea c2=codeAreaService.findCodeAreaByAreaCode("110105025035");
      System.out.println(c2.getAreaName());*/
     
    /*  Superman s=ssss.findById("8a15b87463710bd20163710c00b80000");
      System.out.println(s.getUserName());
      Superman s2=ssss.findById("8a15b87463710bd20163710c00b80000");
      System.out.println(s2.getUserName());
      Superman s3=ssss.findById("8a15b87463710bd20163710c00b80000");
      System.out.println(s3.getUserName());
      List<SysPermission> l=sysPermissionService.findAll();
      List<SysPermission> l2=sysPermissionService.findAll();
      */
    /*  CodeArea a1= (CodeArea) redisTemplate.opsForValue()
      .get("com.css.bdpfnew.service.impl.CodeAreaServiceImplfindCodeAreaByAreaCode110105025035");
      System.out.println(a1.getAreaName());
      String b1= stringRedisTemplate.opsForValue()
    			.get("com.css.bdpfnew.service.impl.CodeAreaServiceImplfindCodeAreaByAreaCode110105025035");
      System.out.println(b1);*/

    /*    CodeArea codeArea2=codeAreaService2.findByAreaCode("110105025029");
        System.out.println(codeArea2.getAreaCode()+"=="+codeArea2.getAreaName()+"=="+codeArea2.getMemo());
        CodeArea codeArea3=codeAreaService2.findByAreaCode("110105025029");
        System.out.println(codeArea3.getAreaCode()+"=="+codeArea3.getAreaName()+"=="+codeArea3.getMemo());
      Gson g=new Gson();
       String json=(String) redisTemplate.opsForValue().get(key);
        CodeArea a=g.fromJson((String)redisTemplate.opsForValue().get(key) , CodeArea.class) ;
    	System.out.println(a.getAreaName());
       String b= stringRedisTemplate.opsForValue().get(key);
       System.out.println(b);
       a.setMemo("aaaa");
       CodeArea aa=codeAreaService2.update(a);
       System.out.println(aa.getAreaName()+"=="+aa.getMemo());
       System.out.println(codeAreaService2.findByAreaCode("110105025029").getMemo());*/
      
    /* CodeArea codeArea2=codeAreaService2.findByAreaCode("110105025035");
     System.out.println(codeArea2.getAreaCode()+"=="+codeArea2.getAreaName()+"=="+codeArea2.getMemo());
     Thread.sleep(30*1000);
     CodeArea codeArea3=codeAreaService2.findByAreaCode("110105025035");
     System.out.println(codeArea3.getAreaCode()+"=="+codeArea3.getAreaName()+"=="+codeArea3.getMemo());
     Thread.sleep(65*1000);
     CodeArea codeArea4=codeAreaService2.findByAreaCode("110105025035");
     System.out.println(codeArea4.getAreaCode()+"=="+codeArea4.getAreaName()+"=="+codeArea4.getMemo());*/
     /*   System.out.println("FindAll");
        List<CodeArea> codeareas= codeAreaService.getCodeAreaList();
        System.out.println("FindOne");
        CodeArea c=codeAreaService.findCodeAreaByAreaCode("110105025035");*/
        /*  redisTemplate.opsForValue().set("cccccc",c);
       Gson gson=new Gson();
        stringRedisTemplate.opsForValue().set("dd", gson.toJson(codeAreaService.findCodeAreaByAreaCode("110105025035")));
        CodeArea codeAr=gson.fromJson(stringRedisTemplate.opsForValue().get("dd"),CodeArea.class);
        System.out.println(codeAr.getAreaName());
       String a= stringRedisTemplate.opsForValue()
		.get("com.css.bdpfnew.service.impl.CodeAreaServiceImplfindCodeAreaByAreaCode110105025000");
        System.out.println(a);*/
       // Thread.sleep(30*1000);
       
    }
}
