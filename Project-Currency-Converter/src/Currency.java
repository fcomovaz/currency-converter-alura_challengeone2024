import java.util.HashMap;

public class Currency {
    private String base_code = "USD";
    private String converted_currency;
    private HashMap<String, Double> conversion_rates;
    private double conversion_rate_base;
    private double conversion_rate_converted;
    private double converted_amount;
    private double amount;

    // public Currency(String base_code, HashMap<String, Double> conversion_rates) {
    // this.base_code = base_code;
    // this.conversion_rates = conversion_rates;
    // }

    // make a constructor that takes an APIRecord object
    public Currency(APIRecord record) {
        this.base_code = record.base_code();
        this.conversion_rates = record.conversion_rates();
    }

    public boolean isValidCurrency(String currency) {
        return conversion_rates.containsKey(currency);
    }

    public void convertCurrency() {
        this.converted_amount = this.amount * conversion_rate_converted / conversion_rate_base;

    }

    public boolean isSameCurrency() {
        return base_code.equals(converted_currency);
    }

    public String toString() {
        return "Currency: " + base_code + "\n" + conversion_rates;
    }

    // getters
    public String getConversionRates() {
        return conversion_rates.keySet().toString();
    }

    public double getConvertedAmount() {
        return converted_amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getOriginalCurrency() {
        return base_code;
    }

    public String getConvertedCurrency() {
        return converted_currency;
    }

    // setters
    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public void setConvertedCurrency(String converted_currency) {
        this.converted_currency = converted_currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setConversionRate(String base_code, String converted_currency) {
        this.base_code = base_code;
        this.converted_currency = converted_currency;
        base_code = base_code.toUpperCase();
        converted_currency = converted_currency.toUpperCase();
        if (isValidCurrency(converted_currency)) {
            this.conversion_rate_base = conversion_rates.get(base_code);
            this.conversion_rate_converted = conversion_rates.get(converted_currency);

        } else {
            throw new IllegalArgumentException("Sorry, Invalid currency");
        }
    }

}
