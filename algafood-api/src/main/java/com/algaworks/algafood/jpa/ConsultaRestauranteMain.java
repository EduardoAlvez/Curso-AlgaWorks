/*
 * package com.algaworks.algafood.jpa;
 * 
 * import java.util.List;
 * 
 * import org.springframework.boot.WebApplicationType; import
 * org.springframework.boot.builder.SpringApplicationBuilder; import
 * org.springframework.context.ApplicationContext;
 * 
 * import com.algaworks.algafood.AlgafoodApiApplication; import
 * com.algaworks.algafood.domain.model.Restaurante; import
 * com.algaworks.algafood.domain.repository.RestauranteRepository;
 * 
 * public class ConsultaRestauranteMain {
 * 
 * public static void main(String[] args) {
 * 
 * // INICIA A APLICAÇÃO ApplicationContext app = new SpringApplicationBuilder(
 * AlgafoodApiApplication.class) // USA NONE PRA DEFINIR QUE NÃO E UMA APLICAÇÃO
 * WEB .web(WebApplicationType.NONE) .run(args);
 * 
 * RestauranteRepository restauranteRepo =
 * app.getBean(RestauranteRepository.class);
 * 
 * List<Restaurante> todasrestaurantes = restauranteRepo.todasRestaurantes();
 * for (Restaurante restaurante : todasrestaurantes) {
 * System.out.printf("%s - %.2f - %s\n",restaurante.getNome(),
 * restaurante.getTaxafrete(), restaurante.getCozinha().getNome()); }
 * 
 * // todasCozinhas.stream().forEach(c -> System.out.println(c.getNome()));
 * 
 * } }
 */