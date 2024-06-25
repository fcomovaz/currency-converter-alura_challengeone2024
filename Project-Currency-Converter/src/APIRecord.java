import java.util.HashMap;

public record APIRecord(
        String result,
        String base_code,
        HashMap<String, Double> conversion_rates) {
}