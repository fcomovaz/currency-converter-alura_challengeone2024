import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        boolean still_converting = true;

        System.out.println("\n*********************************************************");
        System.out.println("******Welcome to the Currency Converter by fcomovaz******");
        System.out.println("*********************************************************\n");

        System.out.println("It's quite simple:");
        System.out.println("1. Enter the amount you want to convert");
        System.out.println("2. Enter the currency you want to convert from in 3-letter format (e.g. USD, EUR, GBP)");
        System.out.println("3. Enter the currency you want to convert to in 3-letter format (e.g. USD, EUR, GBP)");
        System.out.println("4. Get the result\n");

        do {

            // Connect and receive response
            APIConnect api = new APIConnect();
            String response = api.getResponse();

            // Parse the JSON response
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();

            APIRecord record = gson.fromJson(response, APIRecord.class);
            // System.out.println(record);
            Currency currency = new Currency(record);

            // Ask for user input
            System.out.println("Enter the amount to convert (numerical values):");
            double amount = Double.parseDouble(System.console().readLine());
            System.out.println("Enter the currency to convert from : ");
            String currency_from = System.console().readLine();
            System.out.println("Enter the currency to convert to: ");
            String currency_to = System.console().readLine();

            // convert to uppercase
            currency_from = currency_from.toUpperCase();
            currency_to = currency_to.toUpperCase();

            // Check if the currency is valid
            currency.setConversionRate(currency_from, currency_to);
            currency.setAmount(amount);
            currency.convertCurrency();

            // Print the result
            System.out.println(currency.getOriginalCurrency() + " " + currency.getAmount() + " is equal to "
                    + currency.getConvertedCurrency() + " " + currency.getConvertedAmount());

            // Ask if the user wants to convert again
            System.out.println("Do you want to convert another currency? (yes/no)");
            String answer = System.console().readLine();
            answer = answer.toLowerCase();
            if (answer.equals("no")) {
                still_converting = false;
            }

        } while (still_converting);

        System.out.println("=========================================================");
        System.out.println("=Thank you for using the Currency Converter by fcomovaz!=");
        System.out.println("=========================================================");
    }
}
