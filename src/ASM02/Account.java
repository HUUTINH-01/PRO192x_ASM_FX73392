package ASM02;

import java.util.regex.Pattern;

/**
 * 銀行口座を表すクラス。
 * 口座番号、残高、口座の種類などを管理します。
 */
public class Account {
    // --- フィールド (Fields) ---
    private String accountNumber;  // 6桁の口座番号
    private double balance;        // 口座の残高

    // --- コンストラクタ (Constructor) ---
    public Account(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        setBalance(balance);
    }

    // --- ゲッターとセッター (Getters & Setters) ---
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 6桁の口座番号を設定
     */
    public void setAccountNumber(String accountNumber) {
        if (!Pattern.matches("\\d{6}", accountNumber)) {
            throw new IllegalArgumentException("口座番号は6桁の数字でなければなりません。");
        }
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * 残高を設定（50,000VND未満は禁止）
     */
    public void setBalance(double balance) {
        if (balance < 50000) {
            throw new IllegalArgumentException("残高は50,000VND以上でなければなりません。");
        }
        this.balance = balance;
    }

    // --- メソッド (Methods) ---

    /**
     * この口座がプレミアムかどうかを判定
     * @return 1,000,000,000VND以上ならプレミアム
     */
    public boolean isPremiumAccount() {
        return balance >= 1_000_000_000;
    }

    /**
     * 口座情報を表示する
     */
    @Override
    public String toString() {
        return String.format("口座番号: %s | 残高: %, .0fVND", accountNumber, balance);
        //%s	Hiển thị accountNumber (chuỗi)
        //%, .0f	Hiển thị balance (double), có dấu phẩy và làm tròn không hiển thị số thập phân
        // %s       → accountNumber（文字列）を表示するプレースホルダ
        // %,.0f    → balance（金額/double）をカンマ区切りで表示、小数点なしで丸める

    }
}
