package ASM02;

import java.util.ArrayList;
import java.util.List;

/**
 * 銀行顧客を表すクラス。
 * Userクラスを継承し、複数の口座(Account)を管理します。
 */
public class Customer extends User {
    // --- フィールド (Fields) ---
    private List<Account> accounts;  // 顧客の口座リスト

    // --- コンストラクタ (Constructor) ---
    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }

    // --- メソッド (Methods) ---

    /**
     * 顧客が持つすべての口座を取得
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * 新しい口座を追加（重複チェックあり）
     */
    public boolean addAccount(Account newAccount) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return false; // すでに存在
            }
        }
        accounts.add(newAccount);
        return true;
    }

    /**
     * 顧客がプレミアムかどうかを判定
     * 1つでもプレミアム口座を持っていればOK
     */
    public boolean isPremium() {
        for (Account acc : accounts) {
            if (acc.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 顧客の全口座の合計残高を取得
     */
    public double getBalance() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();// 各口座の残高を加算
        }
        return total; // 合計を返す
    }

    /**
     * 顧客情報とすべての口座を表示
     */
    public void displayInformation() {
        String type = isPremium() ? "プレミアム" : "ノーマル";
        System.out.printf("個人番号: %s | 氏名: %s | 種類: %s | 合計残高: %, .0fVND\n", getCustomerId(), getName(), type, getBalance());

        if (accounts.isEmpty()) {
            System.out.println("→ この顧客は口座を持っていません。");
        } else {
            int index = 1;
            for (Account acc : accounts) {
                System.out.printf("  %d. %s\n", index++, acc.toString());
            }
        }
    }
}
