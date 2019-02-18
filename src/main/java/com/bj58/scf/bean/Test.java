package com.bj58.scf.bean;

import com.bj58.jyfz.train.bean.TrainUser;
import com.bj58.jyfz.train.bean.course.Course;
import com.bj58.jyfz.train.contract.CourseService;
import com.bj58.jyfz.train.contract.TrainUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * @author zhangyining on 19/2/18 018.
 */
public class Test {

    /**
     * 测试类使用公司内部jar，大家测试时可以进行修改
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CourseService courseService = (CourseService) context.getBean("courseService");
        TrainUserService trainUserService = (TrainUserService) context.getBean("trainUserService");
        try {
            for (int i = 0;i < 10; i++){
                Course course = courseService.getCourseById(100002L);
                System.out.println(course);
                TrainUser trainUser = trainUserService.selectById(11L);
                System.out.println(trainUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
