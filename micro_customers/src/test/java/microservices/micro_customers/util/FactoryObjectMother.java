package microservices.micro_customers.util;

import microservices.micro_customers.application.core.domain.*;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.application.core.domain.tipos.*;
import net.datafaker.Faker;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

// Padrão Object Mother
public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private final Faker faker = new Faker();

    private FactoryObjectMother() { }

    // Padrão Singleton
    public static synchronized FactoryObjectMother singleton() {
        if (singletonFactory == null) {
            singletonFactory = new FactoryObjectMother();
        }
        return singletonFactory;
    }

    public static String gerarString(int numeroCaracteres) {
        return RandomStringUtils.randomAlphabetic(numeroCaracteres);
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoFixoBuilder() {
        return TelefoneVo.builder()
            .telefone(faker.phoneNumber().phoneNumber())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoCelularBuilder() {
        return TelefoneVo.builder()
            .telefone(faker.phoneNumber().cellPhone())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    public Telefone.TelefoneBuilder gerarTelefoneFixoBuilder() {
        return Telefone.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public Telefone.TelefoneBuilder gerarTelefoneCelularBuilder() {
        return Telefone.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    private String gerarNumeroAleatorioComOnzeDigitos() {
        Random random = new Random();

        // Gerando um número aleatório entre 10000000000 e 99999999999
        var numeroAleatorio = random.nextLong(90000000001L) + 10000000000L;

        // Formatando o número como string com 11 dígitos
        return String.format("%011d", numeroAleatorio);
    }

    // Padrão Builder
    public CustomerEntity.CustomerEntityBuilder gerarCustomerEntityBuilder() {
        var telefone1 = gerarTelefoneVoFixoBuilder().build();
        var telefone2 = gerarTelefoneVoCelularBuilder().build();

        return CustomerEntity.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(faker.cpf().valid())
            .dataNascimento(LocalDate.of(2000, 10, 27))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(faker.internet().emailAddress())
            .telefones(Set.of(telefone1, telefone2))
                .cep(faker.address().zipCode())
                .estado(faker.address().state())
                .cidade(faker.address().city())
                .bairro(faker.lorem().characters(10, 20))
                .logradouro(faker.address().streetAddress())
                .numero(faker.address().buildingNumber())
                .complemento(faker.lorem().characters(30, 200));
    }

    public Endereco.EnderecoBuilder gerarEnderecoBuilder() {
        return Endereco.builder()
            .cep(faker.address().zipCode())
            .estado(faker.address().state())
            .cidade(faker.address().city())
            .bairro(faker.lorem().characters(10, 20))
            .logradouro(faker.address().streetAddress())
            .numero(faker.address().buildingNumber())
            .complemento(faker.lorem().characters(30, 200));
    }

    public Customer.CustomerBuilder gerarCustomerBuilder() {
        var telefone1 = gerarTelefoneFixoBuilder().build();
        var telefone2 = gerarTelefoneCelularBuilder().build();

        return Customer.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(new CadastroPessoaFisica(faker.cpf().valid()))
            .dataNascimento(new DataNascimento(LocalDate.of(2000, 10, 27)))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(new CorreioEletronico(faker.internet().emailAddress()))
            .telefones(Set.of(telefone1, telefone2))
           .endereco(gerarEnderecoBuilder().build());
    }

    public CadastroPessoaFisica.CadastroPessoaFisicaBuilder gerarCadastroPessoaFisicaValidoBuilder() {
        return CadastroPessoaFisica.builder()
            .cpf(faker.cpf().valid());
    }

    public CadastroPessoaFisica.CadastroPessoaFisicaBuilder gerarCadastroPessoaFisicaInvalidoBuilder() {
        return CadastroPessoaFisica.builder()
            .cpf(faker.cpf().invalid());
    }

    public CorreioEletronico.CorreioEletronicoBuilder gerarCorreioEletronicoValidoBuilder() {
        return CorreioEletronico.builder()
            .email(faker.internet().emailAddress());
    }

    public CorreioEletronico.CorreioEletronicoBuilder gerarCorreioEletronicoInvalidoBuilder() {
        return CorreioEletronico.builder()
            .email(faker.lorem().characters(15, 30));
    }

}
