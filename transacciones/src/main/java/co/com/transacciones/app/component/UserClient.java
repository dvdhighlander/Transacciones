package co.com.transacciones.app.component;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class UserClient {

	private final WebClient webClient;

	public UserClient(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:8080").build();
	}
	
	public Mono<ClientDTO> findbyId(int id){
		return webClient.get().uri("/clientes/{id}",id).retrieve().
			    onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
			            clientResponse -> {
			              return Mono.error(new Exception("User with code: " + id + " do not exists"));
			            })
				.bodyToMono(ClientDTO.class);
	}
}
