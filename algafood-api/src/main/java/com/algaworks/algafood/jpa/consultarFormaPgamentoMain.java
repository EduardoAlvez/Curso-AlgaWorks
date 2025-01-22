/*
 * package com.algaworks.algafood.jpa;
 * 
 * 
 * import java.util.List;
 * 
 * import org.springframework.boot.WebApplicationType; import
 * org.springframework.boot.builder.SpringApplicationBuilder; import
 * org.springframework.context.ApplicationContext;
 * 
 * import com.algaworks.algafood.AlgafoodApiApplication; import
 * com.algaworks.algafood.domain.model.FormaPagamento; import
 * com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
 * 
 * public class consultarFormaPgamentoMain {
 * 
 * public static void main(String[] args) {
 * 
 * ApplicationContext app = new SpringApplicationBuilder(
 * AlgafoodApiApplication.class) .web(WebApplicationType.NONE) .run(args);
 * 
 * FormaPagamentoRepository formaPagamentoRepository = app.getBean(
 * FormaPagamentoRepository.class);
 * 
 * List<FormaPagamento> todasFormasDePagamentos =
 * formaPagamentoRepository.todasFormaPagamentos();
 * 
 * for (FormaPagamento formaPagamento : todasFormasDePagamentos) {
 * System.out.println(formaPagamento.getDescricao()); }
 * 
 * 
 * 
 * } }
 */