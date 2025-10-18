package ASM01;

import java.util.Random;
import java.util.Scanner;


 /// 認証コード（簡単・高度）によるユーザー認証を処理するクラス

public class AuthService {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    ///認証機能を選択するメニュー
    public static boolean selectAuthenticationMode() {
        System.out.println("\n >>> 認証モードを選択してください <<<");
        System.out.println("|1. 簡単 (3桁の数字)");
        System.out.println("|2. 高度 (6文字の複雑なコード)");
        System.out.println("|0. 戻る");
        System.out.println("------------+----------+---------");

        System.out.print("あなたの選択: ");

        int mode = scanner.nextInt();
        scanner.nextLine();

        switch (mode) {
            case 1:
                return easyAuthentication();//通常認証
            case 2:
                return hardAuthentication();//高度認証
            case 0:
                System.out.println("認証をキャンセルしました。メインメニューに戻ります！");
                return false;
            default:
                System.out.println("無効な選択です！もう一度選んでください。");
                return selectAuthenticationMode();
        }
    }

    /// 通常認証関数
    public static boolean easyAuthentication() {
        int code = 100 + random.nextInt(900);// 100〜999の間のランダムな認証コードを生成する
        System.out.println("\n>>> あなたの認証コードは: " + code);// 認証コードを日本語で表示する（「あなたの認証コードは: xxx」）


        //誤り回数
        int wrongCount = 0;
        while (true) {
            try {
                System.out.print("認証コードを入力してください: ");
                int inputCode = scanner.nextInt();

                if (inputCode == code) {// 入力されたコードが生成されたコードと一致するか確認する
                    System.out.println(">>> 認証に成功しました！"); // 認証に成功したことを表示する
                    return true; // 成功時に true を返す
                } else {
                    wrongCount++;
                    System.out.println("コードが間違っています。もう一度試してください！");
                    //誤り回数が3回です。
                    if (wrongCount >= 3) {
                        System.out.println("3回以上間違えました。メインメニューに戻ります！");
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("エラー：数字を入力してください！");
                scanner.nextLine(); // バッファをクリア
            }
        }
    }

    ///高度認証関数
    public static boolean hardAuthentication() {
        // 認証コードに使う文字のセット（アルファベット大文字・小文字・数字）
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String code = "";   // 空の認証コードを初期化する

        for (int i = 0; i < 6; i++) {   // 6文字のランダムな認証コードを生成する
            code += chars.charAt(random.nextInt(chars.length()));
        }


        System.out.println("\n>>> あなたの認証コードは: " + code); // 認証コードを表示する（日本語のメッセージ付き）

        //誤り回数
        int wrongCount = 0;
        while (true) {
            System.out.print("認証コードを入力してください: ");
            String input = scanner.next();

            //文字列の比較
            if (code.equals(input)) {
                System.out.println(">>> 認証に成功しました！ <<<");
                return true;
            } else {
                wrongCount++;
                System.out.println("コードが間違っています。もう一度試してください！");
                if (wrongCount >= 3) {
                    System.out.println("3回以上間違えました。メインメニューに戻ります！");
                    return false;
                }
            }
        }
    }
}
