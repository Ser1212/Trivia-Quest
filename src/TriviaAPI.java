import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.lang.String;

public class TriviaAPI {


    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://opentdb.com/api.php?amount=10&type=multiple")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(TriviaAPI::Parse)
                .join();

    }

    public static String Parse(String responseBody){
        String modified_response_body = responseBody.substring(29);
        System.out.println(modified_response_body);
        JSONArray questions = new JSONArray(modified_response_body);
        for(int i = 0; i < questions.length(); i++){
            JSONObject question = questions.getJSONObject(i);
            String Category = question.getString("category");
            System.out.println(Category);
            String difficulty = question.getString("difficulty");
            System.out.println(difficulty);
            String instance_of_question = question.getString("question");
            instance_of_question.replace("/&quot","\"\"");
            System.out.println(instance_of_question);
            String correct_answer = question.getString("correct_answer");
            correct_answer.replace("/&quot","\"\"");
            System.out.println(correct_answer);
            JSONArray incorrect_answers_array = question.getJSONArray("incorrect_answers");
            System.out.println(incorrect_answers_array);
            //String incorrect_answers = incorrect_answers_array.get

        }
        return null;
    }
}
