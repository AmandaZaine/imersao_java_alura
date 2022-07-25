import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os melhores filmes
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=50d3f2de42bafba16194f5d29ea1f53b&language=en-US&page=1";
        URI uri = URI.create(url);
        HttpClient client =  HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println("https://image.tmdb.org/t/p/w500" + filme.get("backdrop_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }

    }
}
