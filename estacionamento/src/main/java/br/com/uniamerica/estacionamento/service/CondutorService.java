package br.com.uniamerica.estacionamento.service;


import br.com.uniamerica.estacionamento.configs.ValidaCpf;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service // Está indicando que a classe é uma service ( variações e verificações ).
public class CondutorService { // Criando o CondutorService

    @Autowired // AutoWired é usado para injetar automaticamente dependências em um objeto.
    private CondutorRepository condutorRepository;

    @Autowired
    private ValidaCpf validarCPF;

    // Abaixo temos uma classe para validar esse condutor, tendo validações para:
    // nome do condutor.
    // CPF do condutor.
    // telefone do condutor.
    @Transactional(rollbackFor = Exception.class)
    public void validaCondutor (Condutor condutor)
    {
        Assert.isTrue(condutor.getNome().length() <= 50,"Nome maior do que 50 caracteres");

        Assert.isTrue(!condutor.getNome().equals(""), "Nome não pode ser nulo.");
        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");

        Assert.isTrue(!condutor.getTelefone().equals(""), "Telefone não pode ser nulo.");
        Assert.isTrue(condutor.getTelefone().length() <  17, "Tamanho de telefone inválido.");


        if (this.validarCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf inválido");
        }

        this.condutorRepository.save(condutor);
    }
    // Abaixo temos um atualizaCondutor, para verificar as informações do condutor quando forem atualizadas.
    // Temos as mesmas verificações do validaCondutor.
    @Transactional(rollbackFor = Exception.class)
    public void atualizaCondutor (Condutor condutor)
    {
        Assert.isTrue(condutor.getNome().length() <= 50,"Nome maior do que 50 caracteres");

        Assert.isTrue(!condutor.getNome().equals(""), "Nome não pode ser nulo.");
        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");

        Assert.isTrue(!condutor.getTelefone().equals(""), "Telefone não pode ser nulo.");
        Assert.isTrue(condutor.getTelefone().length() <  17, "Tamanho de telefone inválido.");

        final Condutor condutorBancoDeDados = this.condutorRepository.findById(condutor.getId()).orElse(null);
        condutor.setCadastro(condutor.getCadastro());
        this.condutorRepository.save(condutor);
    }


}
