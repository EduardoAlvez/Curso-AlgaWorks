package com.algaworks.algafood.api.exceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_ERRO_PADRAO = "Ocorreu um erro interno inesperado do sistema. " +
            "Tente novamente e se o erro problema persistir, entre em contato com o administrador do sistema";


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> Exception(Exception ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemaTipo problemaTipo = ProblemaTipo.ERRO_DO_SISTEMA;
        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, MSG_ERRO_PADRAO).build();
        return handleExceptionInternal(ex,problema, new HttpHeaders(), httpStatus, request);
    }


    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemaTipo problemaTipo = ProblemaTipo.DADOS_INVALIDOS;
        String detalhe = "Um ou mais campos estao invalidos. Faca o preenchimento corret oe tente novamente.";

        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, detalhe)
                .mensagemUsuario(detalhe)
                .build();
        return handleExceptionInternal(ex,problema, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemaTipo problemaTipo = ProblemaTipo.DADOS_INVALIDOS;
        String detalhe = "Um ou mais campos estao invalidos. Faca o preenchimento correto e tente novamente.";

        // ARMAZENA OS CAMPOS VIOLADOS
        BindingResult bindingResult = ex.getBindingResult();

        List<Problema.Campo> campos = bindingResult.getFieldErrors().stream()
                .map(fieldError -> Problema.Campo.builder()
                        .name(fieldError.getField())
                        .mensagemUsuario(fieldError.getDefaultMessage())
                        .build()
                )
                .collect(Collectors.toList());

        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, detalhe)
                .mensagemUsuario(detalhe)
                .campos(campos)
                .build();


        return handleExceptionInternal(ex,problema, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = (HttpStatus) status;
        ProblemaTipo problemaTipo = ProblemaTipo.RECURSO_NAO_ENCONTRADA;
        String detalhe = String.format("O recurso '%s', que voce tentou acessar, e inexistente ou esta errado.",ex.getResourcePath());

        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();
        return handleExceptionInternal(ex,problema, headers, httpStatus, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontraException(EntidadeNaoEncontradaException ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemaTipo problemaTipo = ProblemaTipo.RECURSO_NAO_ENCONTRADA;
        String detalhe = ex.getMessage();

        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();
//        Problema problema = Problema.builder()
//                .tipo("localhost:8080/entidade-nao-encontrada")
//                .titulo("Entidade nao encontrada.")
//                .status(httpStatus.value())
//                .detalhe(ex.getMessage())
//                .build();
        return handleExceptionInternal(ex,problema, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ProblemaTipo problemaTipo = ProblemaTipo.ENTIDADE_EM_USO;
        String detalhe = ex.getMessage();

        Problema problema = createProblemaBuilder(httpStatus, problemaTipo, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();

        return handleExceptionInternal(ex,problema, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request){
        return handleExceptionInternal(ex,ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return  handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemaTipo problemaTipo = ProblemaTipo.MENSAGEM_INCOMPREENSIVEL;
        String detalhe = "Esceveu alguma cosia errada ai fi, da teus pulos, mexe nessa sintaxe ai!";
        Problema problema = createProblemaBuilder((HttpStatus) status, problemaTipo, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path =  ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));


        String detalhe = String.format("A propiedade '%s', nao existe, corrija e tente novamente", path);
        Problema problema = createProblemaBuilder((HttpStatus) status, ProblemaTipo.MENSAGEM_INCOMPREENSIVEL, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        String path =  ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));


        String detalhe = String.format("A propiedade '%s', recebeu o valor '%s', que e do tipo invalido," +
                " Corrija seu bundao, e informe o valor compativel com o tipo '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problema problema = createProblemaBuilder((HttpStatus) statusCode, ProblemaTipo.MENSAGEM_INCOMPREENSIVEL, detalhe)
                .mensagemUsuario(MSG_ERRO_PADRAO)
                .build();

        return handleExceptionInternal(ex, problema, headers, statusCode, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null){
            body = Problema.builder()
//                    .hora(LocalDateTime.now())
                    .mensagemUsuario(MSG_ERRO_PADRAO)
                    .status(statusCode.value())
                    .detalhe(ex.getMessage())
                    .build();

        }else if (body instanceof String){
            body = Problema.builder()
//                    .hora(LocalDateTime.now())
                    .mensagemUsuario(MSG_ERRO_PADRAO)
                    .status(statusCode.value())
                    .detalhe((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


    // METODOS UTILITARIOS
    private  Problema.ProblemaBuilder createProblemaBuilder(HttpStatus status, ProblemaTipo problemaTipo, String detalhe){

        return Problema.builder()
                .status(status.value())
                .detalhe(detalhe)
                .timeStamp(LocalDateTime.now())
                .titulo(problemaTipo.getTitulo())
                .tipo(problemaTipo.getUri());
    }
}
