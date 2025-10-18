package ASM02;

import java.util.List;
import java.util.Scanner;

/**
 * 電子銀行システムのメインクラス。
 * ユーザーの入力に応じて Bank クラスの各種機能を提供する。
 */
public class Main {

    public static final String AUTHOR = "フウティン";
    public static final String VERSION = "v2.0.0";

    // ユーザー入力を受け取るための Scanner インスタンス
    private static final Scanner scanner = new Scanner(System.in);
    // 銀行データの操作を行う Bank クラスのインスタンス
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        printHeader();  // タイトルバナーを表示
        int choice;

        // ユーザーが「0（終了）」を選ぶまでループする
        do {
            mainMenu();           // メインメニューを表示
            choice = getChoice(); // 入力された選択肢を取得
            handleChoice(choice); // 選択肢に応じて処理を実行
        } while (choice != 0);    // 「0」でプログラム終了

        System.out.println("ご利用ありがとうございました。");
        scanner.close(); // Scanner を閉じる（リソース解放）
    }

    /**
     * プログラムのヘッダー情報（著者名・バージョン）を表示
     */
    private static void printHeader() {
        System.out.println("------------+----------+---------");
        System.out.printf("|   電子銀行  | %s@%s　| \n", AUTHOR, VERSION);
        System.out.println("------------+----------+---------");
    }

    /**
     * メインメニューの選択肢を表示
     */
    private static void mainMenu() {
        System.out.println("    >>> 機能を選択してください <<<   ");
        System.out.println("| 1. 顧客を追加する");
        System.out.println("| 2. 顧客に口座を追加する");
        System.out.println("| 3. 顧客一覧を表示する");
        System.out.println("| 4. 個人番号で顧客を検索する");
        System.out.println("| 5. 名前で顧客を検索する");
        System.out.println("| 0. 終了");
        System.out.println("---------------------------------");
        System.out.print("選択してください: ");
    }

    /**
     * ユーザーの選択肢を整数として取得（バリデーションあり）
     *
     * @return 選択肢（無効な入力時は -1 を返す）
     */
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine()); // 数値に変換
        } catch (NumberFormatException e) {
            System.out.println("数字を入力してください。");
            return -1;
        }
    }

    /**
     * 入力された選択肢に応じて処理を振り分ける
     *
     * @param choice ユーザーの選択肢
     */
    // ユーザーの選択に応じた処理を実行する
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1: addCustomer();// 顧客を追加する
                break;
            case 2: addAccountToCustomer(); // 顧客に新しい口座を追加する
                break;
            case 3: bank.displayAllCustomers();// 登録された全ての顧客情報を表示する
                break;
            case 4: findCustomerById();// 個人番号（マイナンバー）で顧客を検索する
                break;
            case 5: findCustomerByName(); // 名前で顧客をあいまい検索する
                break;
            case 0:// 0が選択された場合は何もしない（メインループで終了処理あり）
                break;
            default:
                System.out.println("無効な選択です。もう一度お試しください。");// 有効でない選択肢に対する警告メッセージ
        }
    }

    /**
     * 新しい顧客を登録する
     */
    /**
     * 新しい顧客を追加するためのメソッド。
     * ユーザーから名前と個人番号を入力させ、バリデーション後に顧客リストへ追加。
     */
    private static void addCustomer() {
        // 顧客の名前を入力するプロンプト
        System.out.print("顧客の名前を入力してください: ");
        String name = scanner.nextLine();

        // 12桁の個人番号（マイナンバー）を入力するプロンプト
        System.out.print("個人番号（12桁）を入力してください: ");
        String customerId = scanner.nextLine();

        try {
            Customer customer = new Customer(name, customerId);// 顧客オブジェクトを生成（この時点で個人番号のバリデーションが行われる）
            if (bank.addCustomer(customer)) {// 顧客リストに追加（既に存在する個人番号は追加不可）
                System.out.println("顧客を追加しました。");
            } else {
                System.out.println("この個人番号は既に登録されています。");
            }
        } catch (IllegalArgumentException e) {
            // 個人番号が無効な場合など、例外メッセージを表示
            System.out.println("エラー: " + e.getMessage());
        }
    }


    /**
     * 指定された顧客に新しい口座を追加する
     */
    private static void addAccountToCustomer() {
        System.out.print("口座を追加したい顧客の個人番号を入力してください: ");
        String customerId = scanner.nextLine();

        Customer customer = bank.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("顧客が見つかりません。");
            return;
        }

        System.out.print("6桁の口座番号を入力してください: ");
        String accountNumber = scanner.nextLine();

        System.out.print("口座の初期残高を入力してください（最低50,000VND）: ");
        double balance;

        try {
            balance = Double.parseDouble(scanner.nextLine()); // 残高入力を double に変換
        } catch (NumberFormatException e) {
            System.out.println("金額が正しくありません。");
            return;
        }

        try {
            Account newAccount = new Account(accountNumber, balance);
            boolean success = bank.addAccount(customerId, newAccount);
            if (success) {
                System.out.println("新しい口座を追加しました。");
            } else {
                System.out.println("同じ口座番号が既に存在します。");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }

    /**
     * 個人番号（customerId）によって顧客を検索する
     */
    /**
     * 個人番号を使って顧客を検索し、情報を表示するメソッド。
     */
    private static void findCustomerById() {
        // ユーザーに個人番号（12桁）を入力してもらう
        System.out.print("検索する顧客の個人番号を入力してください: ");
        String customerId = scanner.nextLine();

        // Bankクラスのメソッドを使って顧客を検索
        Customer customer = bank.findCustomerById(customerId);

        // 該当する顧客が見つかった場合
        if (customer != null) {
            customer.displayInformation(); // 顧客情報を表示（Customer クラスのメソッド）
        } else {
            // 見つからなかった場合のエラーメッセージ
            System.out.println("該当する顧客が見つかりません。");
        }
    }


    /**
     * 名前（キーワード検索）によって顧客を検索する
     * 名前の一部をキーワードとして顧客を検索し、一致する顧客情報を表示するメソッド。
     */
    private static void findCustomerByName() {
        // 検索キーワード（顧客名の一部）をユーザーから入力
        System.out.print("検索キーワード（名前）を入力してください: ");
        String keyword = scanner.nextLine();

        // 入力されたキーワードで顧客リストを検索（部分一致）
        List<Customer> matched = bank.findCustomersByName(keyword);

        // 一致する顧客が存在しない場合の処理
        if (matched.isEmpty()) {
            System.out.println("該当する顧客が見つかりません。");
        } else {
            // 一致する顧客情報をすべて表示
            for (Customer c : matched) {
                c.displayInformation(); // 顧客情報の表示（Customer クラス内で定義）
                System.out.println("---------------------------------");
            }
        }
    }
}
