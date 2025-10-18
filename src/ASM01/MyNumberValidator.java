package ASM01;

import java.util.Scanner;

/**
 * 個人番号の検証と情報抽出を行うクラス。
 */
public class MyNumberValidator {

    static Scanner scanner = new Scanner(System.in);

    /**
     * ユーザーから個人番号を入力させ、形式と都道府県コードの妥当性をチェックする。
     *
     * @return 有効な12桁の個人番号（文字列）
     */
    public static String inputValidMyNumber() {
        while (true) {
            try {
                System.out.print("個人番号を入力してください（12桁）: ");
                String myNumBer = scanner.next();

                // 12桁の数字であるか確認
                if (myNumBer.matches("\\d{12}")) {
                    String provinceName = getProvinceName(myNumBer);

                    // 都道府県コードが正しいかチェック
                    if (!provinceName.equals("無効な省コードです！")) {
                        System.out.println(">>> 有効な個人番号です！ <<<");
                        return myNumBer;
                    } else {
                        System.out.println(">>> あなたの個人番号コードは正しくありません！もう一度入力してください。 <<<");
                    }
                } else {
                    System.out.println(">>> 個人番号は12桁の数字でなければなりません！もう一度入力してください。 <<<");
                }
            } catch (Exception e) {
                System.out.println("データ入力エラー！もう一度やり直してください。");
                scanner.nextLine(); // 入力バッファをクリア
            }
        }
    }

    /**
     * 個人番号の先頭3桁から、該当する都道府県名を取得する。
     *
     * @param myNumber 個人番号（12桁）
     * @return 都道府県名、または無効な場合はエラーメッセージ
     */
    public static String getProvinceName(String myNumber) {
        String code = myNumber.substring(0, 3);

        switch (code) {
            case "001": return "Hà Nội";
            case "002": return "Hà Giang";
            case "004": return "Cao Bằng";
            case "006": return "Bắc Kạn";
            case "008": return "Tuyên Quang";
            case "010": return "Lào Cai";
            case "011": return "Điện Biên";
            case "012": return "Lai Châu";
            case "014": return "Sơn La";
            case "015": return "Yên Bái";
            case "017": return "Hoà Bình";
            case "019": return "Thái Nguyên";
            case "020": return "Lạng Sơn";
            case "022": return "Quảng Ninh";
            case "024": return "Bắc Giang";
            case "025": return "Phú Thọ";
            case "026": return "Vĩnh Phúc";
            case "027": return "Bắc Ninh";
            case "030": return "Hải Dương";
            case "031": return "Hải Phòng";
            case "033": return "Hưng Yên";
            case "034": return "Thái Bình";
            case "035": return "Hà Nam";
            case "036": return "Nam Định";
            case "037": return "Ninh Bình";
            case "038": return "Thanh Hoá";
            case "040": return "Nghệ An";
            case "042": return "Hà Tĩnh";
            case "044": return "Quảng Bình";
            case "045": return "Quảng Trị";
            case "046": return "Thừa Thiên Huế";
            case "048": return "Đà Nẵng";
            case "049": return "Quảng Nam";
            case "051": return "Quảng Ngãi";
            case "052": return "Bình Định";
            case "054": return "Phú Yên";
            case "056": return "Khánh Hoà";
            case "058": return "Ninh Thuận";
            case "060": return "Bình Thuận";
            case "062": return "Kon Tum";
            case "064": return "Gia Lai";
            case "066": return "Đắk Lắk";
            case "067": return "Đắk Nông";
            case "068": return "Lâm Đồng";
            case "070": return "Bình Phước";
            case "072": return "Tây Ninh";
            case "074": return "Bình Dương";
            case "075": return "Đồng Nai";
            case "077": return "Bà Rịa - Vũng Tàu";
            case "079": return "TP. Hồ Chí Minh";
            case "080": return "Long An";
            case "082": return "Tiền Giang";
            case "083": return "Bến Tre";
            case "084": return "Trà Vinh";
            case "086": return "Vĩnh Long";
            case "087": return "Đồng Tháp";
            case "089": return "An Giang";
            case "091": return "Kiên Giang";
            case "092": return "Cần Thơ";
            case "093": return "Hậu Giang";
            case "094": return "Sóc Trăng";
            case "095": return "Bạc Liêu";
            case "096": return "Cà Mau";
            default: return "無効な省コードです！"; // コードが存在しない場合
        }
    }

    /**
     * 個人番号の4桁目以降から、性別と生年を解析して返す。
     *
     * @param myNumber 個人番号（12桁）
     * @return 性別と生年に関する説明文（例：性別: 男性 | 生年: 2002）
     */
    public static String getGenderAndBirthYear(String myNumber) {
        char genderCode = myNumber.charAt(3);              // 第4桁は性別と世代コード
        String year = myNumber.substring(4, 6);            // 第5〜6桁は年の下2桁

        switch (genderCode) {
            case '0': return "性別: 男性 | 生年: 19" + year;
            case '1': return "性別: 女性 | 生年: 19" + year;
            case '2': return "性別: 男性 | 生年: 20" + year;
            case '3': return "性別: 女性 | 生年: 20" + year;
            case '4': return "性別: 男性 | 生年: 21" + year;
            case '5': return "性別: 女性 | 生年: 21" + year;
            case '6': return "性別: 男性 | 生年: 22" + year;
            case '7': return "性別: 女性 | 生年: 22" + year;
            case '8': return "性別: 男性 | 生年: 23" + year;
            case '9': return "性別: 女性 | 生年: 23" + year;
            default:  return "性別・生年情報が無効です。"; // 未定義コードの場合
        }
    }
}
