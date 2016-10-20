package brytskyi.week3.xmldomparser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 14.10.16.
 */
public class ClassTOwrite {

    private int num;
    private String mname;
    private double salary;
    private int[] nums = new int[]{1, 2, 3, 4, 5};

    public ClassTOwrite(int num, String mname, double salary) {
        this.num = num;
        this.mname = mname;
        this.salary = salary;
    }

    public ClassTOwrite() {
    }

    @Override
    public String toString() {
        return "ClassTOwrite{" +
                "num=" + num +
                ", mname='" + mname + '\'' +
                ", salary=" + salary +
                ", nums=" + Arrays.toString(nums) +
                '}';
    }
}
