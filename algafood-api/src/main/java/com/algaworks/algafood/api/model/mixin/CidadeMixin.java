package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.core.validation.Grupos;
import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;


public abstract class CidadeMixin {


	@JsonIgnoreProperties(value = "nome")
	private Estado estado;
}
