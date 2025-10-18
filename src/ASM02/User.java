package ASM02;
import ASM01.MyNumberValidator;

/**
 * ユーザー情報を表す基本クラス。
 * Customerクラスなどが継承する。
 */
public class User {
    private String name;           // ユーザーの名前
    private String customerId;     // 個人番号（12桁）

    public User(String name, String customerId) {
        this.name = name;
        setCustomerId(customerId); // 妥当性チェック付きで設定
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    /**
     * 個人番号を設定（12桁かつ有効な都道府県コードである必要あり）
     */
    public void setCustomerId(String customerId) {
        // 妥当でなければ例外を投げる
        if (!isValid(customerId)) {
            throw new IllegalArgumentException("個人番号は12桁の数字でなければなりません。");
        }
        this.customerId = customerId;
    }

    /**
     * 個人番号の妥当性をチェック（形式＋都道府県コード）
     */
    private static boolean isValid(String myNumber) {
        if (myNumber == null || !myNumber.matches("\\d{12}")) {// null または 12桁の数字でない場合は無効
            return false;
        }
        // 都道府県コードが有効であるかを確認
        return !MyNumberValidator.getProvinceName(myNumber).equals("無効な省コードです！");
    }
}
