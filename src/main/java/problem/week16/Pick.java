package problem.week16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/random-pick-index/">398 - 随机数索引</a>
 */
public class Pick
{

    private final Map<Integer, ArrayList<Integer>> map;
    private final Random                           random;

    public Pick(int[] nums)
    {
        random = new Random();
        map    = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            int key = nums[i];
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(i);
        }
    }

    public int pick(int target)
    {
        ArrayList<Integer> list = map.get(target);
        int                size = list.size();
        // return list.get(random.nextInt(size));
        return list.get((int) (random.nextDouble() * size));
    }
}
