package microservices.micro_customers.adapter.mapper;

import groovy.transform.ToString;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - MapperOutImpl")
class MapperOutImplUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private MapperOut mapperOut;

    @Nested
    @DisplayName("ToCustomerEntity")
    class TestToCustomerEntity {

        @Test
        @DisplayName("customer nulo")
        void dadoCustomerNulo_quandoToCustomerEntity_entaoLancarException() {
            Executable acao = () -> mapperOut.toCustomerEntity(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customer válido")
        void dadoCustomerValido_quandoToCustomerEntity_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder().build();
            var entity = mapperOut.toCustomerEntity(customer);
            Assertions.assertTrue(entity instanceof CustomerEntity);

            Assertions.assertEquals(customer.getCustomerId(), entity.getCustomerId());
            Assertions.assertEquals(customer.getNomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(customer.getCpf().getCpf(), entity.getCpf());
            Assertions.assertEquals(customer.getDataNascimento().getDataNascimentoLocalDate(), entity.getDataNascimento());
            Assertions.assertEquals(customer.getStatusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(customer.getEmail().getEmail(), entity.getEmail());

            Assertions.assertEquals(customer.getTelefones().size(), entity.getTelefones().size());

            Assertions.assertEquals(customer.getEndereco().getCep(), entity.getCep());
            Assertions.assertEquals(customer.getEndereco().getEstado(), entity.getEstado());
            Assertions.assertEquals(customer.getEndereco().getCidade(), entity.getCidade());
            Assertions.assertEquals(customer.getEndereco().getBairro(), entity.getBairro());
            Assertions.assertEquals(customer.getEndereco().getLogradouro(), entity.getLogradouro());
            Assertions.assertEquals(customer.getEndereco().getNumero(), entity.getNumero());
            Assertions.assertEquals(customer.getEndereco().getComplemento(), entity.getComplemento());
        }

        @Test
        @DisplayName("telefones nulo")
        void dadoCustomerComTelefonesNulo_quandoToCustomerEntity_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder().telefones(null).build();
            var entity = mapperOut.toCustomerEntity(customer);
            Assertions.assertTrue(entity instanceof CustomerEntity);
        }
    }

}
