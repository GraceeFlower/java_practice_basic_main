package com.thoughtworks;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  static class Item {
    String id;
    String name;
    String count;
    Double price;

    public Item(String id, String name, String count, Double price) {
      this.id = id;
      this.name = name;
      this.count = count;
      this.price = price;
    }
  }
  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    // 此处补全代码
    ArrayList<Item> menu = getMenu(selectedItems);
    int[] subtotals = getSubtotal(menu);
    String receipt = printReceipt(menu, subtotals);

    return receipt;
  }

  public static ArrayList getMenu(String selectedItems) {
    ArrayList<Item> menu = new ArrayList<Item>();
    String[] items = selectedItems.split(",");
    for (int i = 0; i < items.length; i++) {
      String[] newItem = items[i].split(" x ");
      for (int k = 0; k < getItemIds().length; k++) {
        if (newItem[0].equals(getItemIds()[k])) {
          Item itemInfo = new Item(newItem[0], getItemNames()[k],
              newItem[1], getItemPrices()[k]);
          menu.add(itemInfo);
          break;
        }
      }
    }
    return menu;
  }

  public static int[] getSubtotal(ArrayList<Item> menu) {
    int[] subtotals = new int[menu.size()];

    for (int i = 0; i < subtotals.length; i++) {
      String countStr = menu.get(i).count;
      Double count = Double.valueOf(countStr);
      int subtotal = (int) (count *  menu.get(i).price);
      subtotals[i] = subtotal;
    }
    return subtotals;
  }

  public static String printReceipt(ArrayList<Item> menu, int[] subtotals) {
    String receipt = "============= 订餐明细 =============\n";
    for (int i = 0; i < menu.size(); i++) {
      receipt = receipt + menu.get(i).name + " x "
          + menu.get(i).count + " = " + subtotals[i] + "元\n";
    }
    receipt += "===================================";
    return receipt;
  }

  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }
}
