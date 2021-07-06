package HashTable;
//给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodIt
//emi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。 
//
// 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中
//的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。 
//
// 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。 
//
// 
//
// 示例 1： 
//
// 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David",
//"3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","
//Ceviche"]]
//输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1
//","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
//解释：
//点菜展示表如下所示：
//Table,Beef Burrito,Ceviche,Fried Chicken,Water
//3    ,0           ,2      ,1            ,0
//5    ,0           ,1      ,0            ,1
//10   ,1           ,0      ,0            ,0
//对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
//而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
//餐桌 10：Corina 点了 "Beef Burrito" 
// 
//
// 示例 2： 
//
// 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],[
//"Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","
//Canadian Waffles"]]
//输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
// 
//解释：
//对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
//而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
// 
//
// 示例 3： 
//
// 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melis
//sa","2","Soda"]]
//输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= orders.length <= 5 * 10^4 
// orders[i].length == 3 
// 1 <= customerNamei.length, foodItemi.length <= 20 
// customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。 
// tableNumberi 是 1 到 500 范围内的整数。 
// 
// Related Topics 数组 哈希表 字符串 有序集合 排序 
// 👍 42 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class DisplayTableOfFoodOrdersInARestaurant1418 {
    // 难倒是不难 就是写起来又臭又长
    public static List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        Map<Integer, Map<String, Integer>> table = new HashMap<>();
        Set<String> foodSet = new HashSet<>();
        for (List<String> list : orders) {
            int t = Integer.parseInt(list.get(1));  // 桌号
            Map<String, Integer> item = table.getOrDefault(t, new HashMap<>());
//            Map<String, Integer> item = table.get(t);
//            if (item == null)
//                item = new HashMap<>();
            String food = list.get(2);  // 菜品
            foodSet.add(food);
            item.put(food, item.getOrDefault(food, 0) + 1); // 菜品送到桌号
            table.put(t, item);
        }
        List<String> foodList = new ArrayList<>(foodSet);
        Collections.sort(foodList);
        int size = foodList.size();
        Map<String, Integer> foodMap = new HashMap<>();
        int index = 0;
        for(String food : foodList) {
            foodMap.put(food, index++);
        }
//        System.out.println(foodMap);
        List<String> title = new ArrayList<>(){{
            add("Table");
            this.addAll(foodList);
        }};
        res.add(title);
        for(int t : table.keySet()) {
            Map<String, Integer> foodNum = table.get(t);
            List<String> resItem = new ArrayList<>();
            resItem.add(String.valueOf(t));
            int[] cur = new int[size];
            for(String foodName : foodNum.keySet()) {
                int num = foodNum.get(foodName);
                int mapIndex = foodMap.get(foodName);
                cur[mapIndex] = num;
            }
            for(int num : cur) {
                resItem.add(String.valueOf(num));
            }
            res.add(resItem);
        }
//        System.out.println(table);
        res.sort((strings, t1) -> {
            if (strings.get(0).equals("Table")) return -1;
            if (t1.get(0).equals("Table")) return 1;
            return Integer.parseInt(strings.get(0)) - Integer.parseInt(t1.get(0));
        });
        return res;
    }


    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>(){{
            add(new ArrayList<>(){{
                add("Laura");
                add("2");
                add("Bean Burrito");
            }});
            add(new ArrayList<>(){{
                add("Jhon");
                add("2");
                add("Beef Burrito");
            }});
            add(new ArrayList<>(){{
                add("Melissa");
                add("2");
                add("Soda");
            }});
        }};
        System.out.println(displayTable(orders));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
