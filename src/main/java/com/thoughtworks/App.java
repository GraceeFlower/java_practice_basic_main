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
  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    ArrayList<Dish> menu = getMenu(selectedItems);
    int[] subtotals = getSubtotal(menu);
    String receipt = printReceipt(menu, subtotals);

    return receipt;
  }

  public static ArrayList<Dish> getMenu(String selectedItems) {
    ArrayList<Dish> menu = new ArrayList<>();
    String[] items = selectedItems.split(",");
    for (String item : items) {
      String[] newItem = item.split(" x ");
      for (int k = 0; k < getItemIds().length; k++) {
        if (newItem[0].equals(getItemIds()[k])) {
          Dish itemInfo = new Dish(newItem[0], getItemNames()[k],
              newItem[1], getItemPrices()[k]);
          menu.add(itemInfo);
          break;
        }
      }
    }
    return menu;
  }

  public static int[] getSubtotal(ArrayList<Dish> menu) {
    int[] subtotals = new int[menu.size()];

    for (int i = 0; i < subtotals.length; i++) {
      String countStr = menu.get(i).count;
      double count = Double.parseDouble(countStr);
      int subtotal = (int) (count *  menu.get(i).price);
      subtotals[i] = subtotal;
    }
    return subtotals;
  }

  public static String printReceipt(ArrayList<Dish> menu, int[] subtotals) {
    String receipt = "============= 订餐明细 =============\n";
    String discountMsg = calculatePrice(menu, subtotals);

    for (int i = 0; i < menu.size(); i++) {
      receipt = receipt + menu.get(i).name + " x "
          + menu.get(i).count + " = " + subtotals[i] + "元\n";
    }
    receipt += discountMsg + "===================================";
    return receipt;
  }
  
  public static String calculatePrice(ArrayList<Dish> menu, int[] subtotals) {
    int totalPrice = 0;
    for (int i: subtotals) {
      totalPrice += i;
    }
    int fullOffPrice = calculateMoneyOff(totalPrice);
    ArrayList<Integer> halfOffPrice = calculateRateDiscount(menu, totalPrice);
    int realPrice = totalPrice;
    String discountMessage = "";

    if (halfOffPrice.get(halfOffPrice.size() - 1) < fullOffPrice) {
      realPrice = halfOffPrice.get(halfOffPrice.size() - 1);
      for (int i = 0; i < halfOffPrice.size() - 2; i++) {
        discountMessage += menu.get(halfOffPrice.get(i)).name + (i  == halfOffPrice.size() - 3 ? "" : "，");
      }
      discountMessage = "指定菜品半价(" + discountMessage + ")，省" + halfOffPrice.get(halfOffPrice.size() - 2) + "元\n";
    } else {
      discountMessage = "满30减6元，省6元\n";
      realPrice = fullOffPrice;
    }
    String message = "-----------------------------------\n";
    if (realPrice != totalPrice) {
      message += "使用优惠:\n" + discountMessage
          + "-----------------------------------\n";
    }
    message += "总计：" + realPrice + "元\n";
    return message;
  }

  public static int calculateMoneyOff(int realMoney) {
    int discountPrice = realMoney;
    if (realMoney >= 30) {
      discountPrice -= 6;
    }
    return discountPrice;
  }

  public static ArrayList<Integer> calculateRateDiscount(ArrayList<Dish> menu, int realMoney) {
    ArrayList<Integer> discountInfo = new ArrayList<>();
    int discountPrice = realMoney;

    for (int i = 0; i < menu.size(); i++) {
      for (int j = 0; j < getHalfPriceIds().length; j++) {
        if (menu.get(i).id.equals(getHalfPriceIds()[j])) {
          discountInfo.add(i);
          double moneyOff =  menu.get(i).price / 2;
          discountPrice -= (int) moneyOff;
        }
      }
    }
    discountInfo.add(realMoney - discountPrice);
    discountInfo.add(discountPrice);
    return discountInfo;
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
