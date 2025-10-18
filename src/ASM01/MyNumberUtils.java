package ASM01;

import java.util.Scanner;

/**
 * 個人番号に関連する操作メニューを提供するユーティリティクラス。
 */
public class MyNumberUtils {

    static Scanner scanner = new Scanner(System.in);

    /**
     * 個人番号をもとに、詳細情報を表示するサブメニューを処理するメソッド。
     * 出生地、生年・性別、識別コードなどを選択して表示できる。
     * @param myNumber ユーザーが入力した12桁の個人番号
     */
    public static void handleSubMenu(String myNumber) {
        // 個人番号の長さをチェック（12桁未満は無効）
        if (myNumber.length() < 12) {
            System.out.println("無効な個人番号です。再入力してください。");
            return;
        }

        int subChoice;
        do {
            // サブメニューを表示し、ユーザーの選択を取得
            subChoice = displaySubMenu();

            switch (subChoice) {
                case 1:
                    // 選択肢1: 出生地（都道府県名）を表示
                    System.out.println("出生地: " + MyNumberValidator.getProvinceName(myNumber));
                    break;

                case 2:
                    // 選択肢2: 生年と性別を表示
                    System.out.println(MyNumberValidator.getGenderAndBirthYear(myNumber));
                    break;

                case 3:
                    // 選択肢3: 個人識別コード（下6桁）を抽出して表示
                    String idCode = myNumber.substring(6);
                    System.out.println("識別コード: " + idCode);
                    break;

                case 0:
                    // 選択肢0: メニューを終了し、前のメニューに戻る
                    break;

                default:
                    // 上記以外の選択肢: 無効な選択としてエラーメッセージを表示
                    System.out.println("無効な選択です。もう一度入力してください。");
            }

        } while (subChoice != 0); // ユーザーが0を選択するまで繰り返す
    }

    // 出生地、生年・性別、個人識別コードの確認メニューを提供する
    public static int displaySubMenu() {
        // サブメニューのヘッダーを表示
        System.out.println("\n------------+----------+---------");
        System.out.println("   >>> 個人情報を確認する <<<");
        System.out.println("| 1. 出生地を確認する");
        System.out.println("| 2. 生年・性別を確認する");
        System.out.println("| 3. 個人識別コードを確認する");
        System.out.println("| 0. 戻る");
        System.out.println("------------+----------+---------");
        System.out.print("オプションを入力してください: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("エラー：有効な数字を入力してください！");
            return -1;// 無効な入力を示すために -1 を返す
        }
    }
}
