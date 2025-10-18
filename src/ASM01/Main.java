package ASM01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 電子個人番号システム全体を制御するメインクラス。
 */
public class Main {

    // 作者情報とプログラムのバージョン
    public static final String AUTHOR = "フウティン";
    public static final String VERSION = "v1.0.0";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        printTitle(); // プログラムのタイトルを表示

        do {
            choice = mainMenu(); // メニューを表示してユーザーの選択を取得

            switch (choice) {
                case 1:
                    // 個人番号を処理する前にユーザー認証を行う
                    if (AuthService.selectAuthenticationMode()) {
                        String myNumber;
                        do {
                            myNumber = MyNumberValidator.inputValidMyNumber(); // 有効な個人番号を入力
                        } while (myNumber == null); // 安全のため、inputValidMyNumberは常に有効な値を返す

                        MyNumberUtils.handleSubMenu(myNumber); // 個人番号を処理するサブメニューを表示
                    }
                    break;

                case 0:
                    System.out.println("ご利用いただきありがとうございます！");
                    break;

                default:
                    System.out.println("無効な選択です。もう一度選んでください。");
            }
        } while (choice != 0);
    }

    // プログラムのタイトルを表示
    public static void printTitle() {
        System.out.println("------------+----------+---------");
        System.out.printf("|   電子銀行  | %s@%s　| \n", AUTHOR, VERSION);
        System.out.println("------------+----------+---------");
    }

    // メインメニューを表示してユーザーの選択を取得
    public static int mainMenu() {
        System.out.println("|   >>> 機能を選択してください <<<");
        System.out.println("| 1. 個人識別番号を入力する");
        System.out.println("| 0. 終了する");
        System.out.println("------------+----------+---------");
        System.out.print("選択を入力してください: ");

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // 不正な入力データをバッファから削除
            System.out.println("無効な入力です。数字を入力してください！");
            return -1; // 無効な値を返す
        }
    }
}
