package burguer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import burguer.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>, QueryByExampleExecutor<Pedido> {


}