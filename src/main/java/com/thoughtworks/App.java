package com.thoughtworks;

import java.util.Arrays;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  class item {
    String id;
    String name;
    int count;
    double price;

    public item(String id, String name, int count, double price) {
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
    String[] items = selectedItems.split(",");
    String[] ids = getItemIds();
    String[] itemNames = new String[items.length];
    String[] itemCounts = new String[items.length];
    double[] itemPrices = new double[items.length];

    for (int i = 0, j = 0; i < items.length; i++) {
      String itemId = items[i].split(" x ")[0];
      String itemCount = items[i].split(" x ")[1];
      itemCounts[0] = itemCount;
      for (int k = 0; k < ids.length; k++) {
        if (itemId.equals(ids[k])) {
          itemNames[j] = getItemNames()[k];
          itemPrices[j] = getItemPrices()[k];
          j++;
          break;
        }
      }
    }
    return Arrays.toString(itemNames);
  }

  public static String getItemId() {
    String itemId = "0";
    return itemId;
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


//public class Item {
//  String id;
//  String name;
//  Double prices;
//
//  public Item(String id, String name, Double prices) {
//    this.id = id;
//    this.name = name;
//    this.prices = prices;
//  }
//
//  @Override
//  public String toString() {
//    return "Item{" +
//        "id='" + id + '\'' +
//        ", name='" + name + '\'' +
//        ", prices=" + prices +
//        '}';
//  }
//
//  public String getId() {
//    return id;
//  }
//
//  public void setId(String id) {
//    this.id = id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public Double getPrices() {
//    return prices;
//  }
//
//  public void setPrices(Double prices) {
//    this.prices = prices;
//  }
//}
