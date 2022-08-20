package br.unitins.unimacy.model.email;

import java.io.Serial;
import java.time.LocalDateTime;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.pessoa.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ForgotPassword extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private String codigo;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    private LocalDateTime dataHoraLimite;
    private Boolean utilizado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataHoraLimite() {
        return dataHoraLimite;
    }

    public void setDataHoraLimite(LocalDateTime dataHoraLimite) {
        this.dataHoraLimite = dataHoraLimite;
    }

    public Boolean getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Boolean utilizado) {
        this.utilizado = utilizado;
    }

}
