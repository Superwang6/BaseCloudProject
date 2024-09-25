package cn.fudges.test;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;

/**
 * @author 王平远
 * @since 2024/9/18
 */

public class Test {

    public static void main(String[] args) {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();
        Long aLong = defaultIdentifierGenerator.nextId(new Object());
        System.out.println(aLong);
    }
}
