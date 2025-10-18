package ASM02;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 銀行全体を管理するクラス。
 * 顧客の登録、検索、口座の追加などを担当します。
 */
public class Bank {

    // --- フィールド (Fields) ---
    private String id;                       // 銀行のユニークID（UUIDで生成）
    private List<Customer> customers;       // 登録されている顧客のリスト

    // --- コンストラクタ (Constructor) ---
    public Bank() {
        this.id = UUID.randomUUID().toString();  // 一意なIDを自動生成
        this.customers = new ArrayList<>();      // 顧客リストを初期化（空）
    }

    // --- ゲッター (Getters) ---
    public String getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    // --- 顧客操作メソッド (Customer Operations) ---

    /**
     * 個人番号で顧客がすでに存在するか確認
     */
    public boolean isCustomerExisted(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 顧客をリストに追加（重複不可）
     */
    public boolean addCustomer(Customer newCustomer) {
        if (!isCustomerExisted(newCustomer.getCustomerId())) {
            customers.add(newCustomer);
            return true;
        }
        return false;
    }

    /**
     * 顧客に口座を追加（対象の顧客が存在する場合のみ）
     */
    public boolean addAccount(String customerId, Account account) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer.addAccount(account);  // Customerクラスのメソッドを利用
            }
        }
        return false;  // 顧客が見つからなかった
    }

    // --- 検索機能 (Search Functions) ---

    /**
     * 個人番号で顧客を検索（完全一致）
     */
    public Customer findCustomerById(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 名前で顧客を部分一致検索（大文字小文字を無視）
     */
    public List<Customer> findCustomersByName(String keyword) {
        List<Customer> matched = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(c);
            }
        }
        return matched;
    }

    // --- 表示機能 (Display) ---

    /**
     * すべての顧客情報を表示する
     */
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("現在、登録された顧客がいません。");
            return;
        }

        System.out.println("=== 顧客一覧 ===");
        for (Customer c : customers) {
            c.displayInformation();  // 顧客の詳細情報を表示
            System.out.println("--------------------------------");
        }
    }
}
