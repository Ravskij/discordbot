import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RateBYN {
    public static String[] TakeRates() {
        try {
            URL urlUSD = new URL("https://api.nbrb.by/exrates/rates/USD?parammode=2");
            URL urlEUR = new URL("https://api.nbrb.by/exrates/rates/EUR?parammode=2");
            URL urlRUB = new URL("https://api.nbrb.by/exrates/rates/RUB?parammode=2");
            BufferedReader inputUSD = new BufferedReader(new InputStreamReader(urlUSD.openStream()));
            BufferedReader inputEUR = new BufferedReader(new InputStreamReader(urlEUR.openStream()));
            BufferedReader inputRUB = new BufferedReader(new InputStreamReader(urlRUB.openStream()));

            String[] rates = new String[3];
            String[] jsonUSDelements = inputUSD.readLine().split(":");
            for (int i = 0; i < jsonUSDelements.length; i++) {
                if (jsonUSDelements[i].contains("Cur_OfficialRate")) {
                    String rateStr = jsonUSDelements[i+1].replace(",", "").replace("}", "").trim();
                    System.out.println("1 USD = " + rateStr + " BYN");
                    rates[0] = rateStr;
                    break;
                }
            }
            inputUSD.close();
            String[] jsonEURelements = inputEUR.readLine().split(":");
            for (int i = 0; i < jsonEURelements.length; i++) {
                if (jsonEURelements[i].contains("Cur_OfficialRate")) {
                    String rateStr = jsonEURelements[i+1].replace(",", "").replace("}", "").trim();
                    System.out.println("1 EUR = " + rateStr + " BYN");
                    rates[1] = rateStr;
                    break;
                }
            }
            inputEUR.close();
            String[] jsonRUBelements = inputRUB.readLine().split(":");
            for (int i = 0; i < jsonUSDelements.length; i++) {
                if (jsonRUBelements[i].contains("Cur_OfficialRate")) {
                    String rateStr = jsonRUBelements[i+1].replace(",", "").replace("}", "").trim();
                    System.out.println("100 RUB = " + rateStr + " BYN");
                    rates[2] = rateStr;
                    break;
                }
            }
            inputRUB.close();
            return rates;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[3];
    }
}
