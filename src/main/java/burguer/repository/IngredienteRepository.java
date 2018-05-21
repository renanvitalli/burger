package burguer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import burguer.entity.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>, QueryByExampleExecutor<Ingrediente> {

}
