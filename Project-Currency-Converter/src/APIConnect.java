import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConnect {
    // this contains the API key
    private String API_KEY = "2a2fd6a5996c1c3332dd22a3";
    // this contains the base URL
    private String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // add a variable to store the response
    private String response;


    public APIConnect() {

        // Connect with the simplest case
        try {
            // add client
            HttpClient client = HttpClient.newHttpClient();

            // create request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + API_KEY + "/latest/USD"))
                    .build();

            // send request
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            
            // print response
            // System.out.println("\n" + response.body());

            this.response = response.body();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse() {
        return this.response;
    }

}
